package com.visenergy.iec104;

import com.rabbitmq.client.*;
import com.visenergy.iec104.util.ChangeUtils;
import com.visenergy.iec104.util.RabbitMqUtils;
import net.sf.json.JSONObject;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.Socket;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Client {
    private static Log log = LogFactory.getLog(Client.class);

	public static void main(String args[]) {

		try {
            //系统初始化
            Init.start();
			// 向本机的4700端口发出客户请求
			Socket socket = new Socket("192.168.100.110", 2405);
			log.debug("与192.168.100.110建立连接");
			// 由Socket对象得到输出流，并构造PrintWriter对象
			OutputStream os = socket.getOutputStream();

            Runnable runnable = new Runnable() {
                public void run() {
                    try {
                        log.debug("发送总召命令");
                        os.write(ChangeUtils.hexStringToBytes("680E0000000064010600010000000014"));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            };
            ScheduledExecutorService service = Executors.newSingleThreadScheduledExecutor();
            // 第二个参数为首次执行的延时时间，第三个参数为定时执行的间隔时间
            service.scheduleAtFixedRate(runnable, 60, 180, TimeUnit.SECONDS);

            Connection conn = RabbitMqUtils.newConnection();
            Channel channel = conn.createChannel();
            //监听总召命令
            Consumer consumer = new DefaultConsumer(channel){
                @Override
                public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                    log.debug("有新客户端连接，发送总召命令");
                    os.write(ChangeUtils.hexStringToBytes("680E0000000064010600010000000014"));
                }
            };
            channel.basicConsume("PV_CALL",true,consumer);

            // 由Socket对象得到输入流，并构造相应的BufferedReader对象
			InputStream is = socket.getInputStream();

			while(true){
                try {
                    Apdu apdu = new Apdu(new DataInputStream(is));
                    if (apdu.getApciType() == Apdu.ApciType.I_FORMAT) {
                        Asdu asdu = apdu.getAsdu();
                        //处理I命令
                        handleData(asdu.getTypeId(),asdu.getInformationObjects());
                        //返回S确认命令
                        int receiveSeqNum = apdu.getSendSeqNumber() + 1;
                        byte[] recNum = new byte[2];
                        recNum[0] = (byte) (receiveSeqNum << 1);
                        recNum[1] = (byte) (receiveSeqNum >> 7);
                        String recStr = ChangeUtils.toHexString(recNum);
                        os.write(ChangeUtils.hexStringToBytes("68040100" + recStr));
                        log.debug("确认消息，S类型，下一条的接受序号：" + recStr);
                    }else if (apdu.getApciType() == Apdu.ApciType.STARTDT_ACT) {
                        os.write(ChangeUtils.hexStringToBytes("68040B000000"));
                        log.debug("确认启动消息，U类型");
                    }else if (apdu.getApciType() == Apdu.ApciType.STOPDT_ACT) {
                        os.write(ChangeUtils.hexStringToBytes("680423000000"));
                        log.debug("确认停止消息，U类型");
                    }else if (apdu.getApciType() == Apdu.ApciType.TESTFR_ACT) {
                        os.write(ChangeUtils.hexStringToBytes("680483000000"));
                        log.debug("确认测试消息，U类型");
                    }else{
                        log.warn("未知的报文：" + apdu.getApciType());
                    }
                }catch (Exception e){
                    log.error("异常错误",e);
                    break;
                }
			}
			os.close(); // 关闭Socket输出流

			is.close(); // 关闭Socket输入流

			socket.close(); // 关闭Socket

		} catch (Exception e) {
			log.error("Error",e); // 出错，则打印出错信息
		}
	}


    public static void handleData(int typeId,InformationObject[] infoObjs) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                try{
                    for (int i = 0; i < infoObjs.length; i++) {
                        if(typeId == 1){//单点遥信处理
                            int firstAddress = infoObjs[i].getInformationObjectAddress();
                            int len = infoObjs[i].getInformationElements().length;
                            for (int j = 0; j < len; j++) {
                                int address = firstAddress + j;
                                if(address >356 || address < 0){
                                    log.warn("遥信信息超出定义范围：" + address);
                                }
                                JSONObject yxObjJson =  Init.yxJsonObj.getJSONObject(address+"");
                                if(yxObjJson != null){
                                    String yxObjName = null;
                                    if(address>= 0 && address < 32){
                                        yxObjName = "NO101";
                                    }else if(address>= 32 && address < 64 ){
                                        yxObjName = "NO102";
                                    }else if(address>= 64 && address < 96){
                                        yxObjName = "NO204";
                                    }else if(address>= 96 && address < 128){
                                        yxObjName = "NO205";
                                    }else if(address>= 128 && address < 160){
                                        yxObjName = "NO201";
                                    }else if(address>= 160 && address < 192){
                                        yxObjName = "NO202";
                                    }else if(address>= 192 && address < 224){
                                        yxObjName = "NO203";
                                    }else if(address>= 224 && address < 256){
                                        yxObjName = "NO302";
                                    }else if(address>= 256 && address < 288){
                                        yxObjName = "NO401";
                                    }else if(address>= 288 && address < 320){
                                        yxObjName = "NO402";
                                    }else if(address>= 320 && address < 352){
                                        yxObjName = "NO501";
                                    }else {
                                        //通讯状态
                                        String yxsObjName = null;
                                        //通讯状态值
                                        int flagInt = ((IeSinglePointWithQuality) infoObjs[i].getInformationElements()[j][0]).isOn() ? 1 : 0;
                                        //遍历数据池中所有遥信状态对象
                                        for (Map.Entry<String, YxStatusObject> entry : DataProcessPool.yxsPool.entrySet()) {

                                            yxsObjName = null;
                                            //判断地址，确认通讯状态属于哪些逆变器
                                            if (address == 352){
                                                if ("NO101".equals(entry.getKey()) || "NO102".equals(entry.getKey())){//101栋
                                                    yxsObjName = entry.getKey();
                                                }else {
                                                    log.debug("通讯状态与逆变器" + entry.getKey() + "不匹配");
                                                }
                                            }else if (address == 353){//301栋
                                                if ("NO201".equals(entry.getKey()) || "NO202".equals(entry.getKey()) || "NO203".equals(entry.getKey()) || "NO204".equals(entry.getKey()) || "NO205".equals(entry.getKey())){
                                                    yxsObjName = entry.getKey();
                                                }else {
                                                    log.debug("通讯状态与逆变器" + entry.getKey() + "不匹配");
                                                }
                                            }else if (address == 354){//302栋
                                                if ("NO302".equals(entry.getKey())){
                                                    yxsObjName = entry.getKey();
                                                }else {
                                                    log.debug("通讯状态与逆变器" + entry.getKey() + "不匹配");
                                                }
                                            }else if (address == 355){//306栋
                                                if ("NO401".equals(entry.getKey()) || "NO402".equals(entry.getKey())){
                                                    yxsObjName = entry.getKey();
                                                }else {
                                                    log.debug("通讯状态与逆变器" + entry.getKey() + "不匹配");
                                                }
                                            }else if (address == 356){//401栋
                                                if ("NO501".equals(entry.getKey())){
                                                    yxsObjName = entry.getKey();
                                                }else {
                                                    log.debug("通讯状态与逆变器" + entry.getKey() + "不匹配");
                                                }
                                            }else {
                                                log.debug("无效的通讯状态地址");
                                            }

                                            //遥信状态对象
                                            if (yxsObjName != null){
                                                log.debug("遥信状态对象 ：" + yxsObjName + " ,信息体地址：" + address + "，执行方法：set" + yxObjJson.getString("name") + "，值：" + ((IeSinglePointWithQuality) infoObjs[i].getInformationElements()[j][0]).isOn());
                                                setValue(3, yxsObjName, "set" + yxObjJson.getString("name"), flagInt, yxObjJson.getString("type"));
                                            }else {
                                                log.warn("遥信状态对象无名称! ");
                                            }
                                        }
                                    }

                                    //遥信对象
                                    if (yxObjName != null) {
                                        log.debug("遥信对象 ：" + yxObjName + " ,信息体地址：" + address + "，执行方法：set" + yxObjJson.getString("name") + "，值：" + ((IeSinglePointWithQuality) infoObjs[i].getInformationElements()[j][0]).isOn());
                                        int flagInt = ((IeSinglePointWithQuality) infoObjs[i].getInformationElements()[j][0]).isOn() ? 1 : 0;
                                        setValue(1, yxObjName, "set" + yxObjJson.getString("name"), flagInt, yxObjJson.getString("type"));
                                    } else {
                                        log.warn("遥信对象无名称！");
                                    }

                                }else{
                                    log.warn("找不到的遥信编号: " + address);
                                }
                            }
                        }else if(typeId == 13){//浮点型遥测处理
                            int firstAddress = infoObjs[i].getInformationObjectAddress();
                            int len = infoObjs[i].getInformationElements().length;
                            for (int j = 0; j < len; j++) {
                                int address = firstAddress + j;
                                if (address < 16385 || address > 16909){
                                    log.warn("遥测信息超出定义范围：" + address);
                                }

                                JSONObject ycObjJson =  Init.ycJsonObj.getJSONObject(address+"");
                                if(ycObjJson != null){
                                    String ycObjName = null;
                                    if(address>= 16385 && address < 16437){
                                        ycObjName = "NO101";
                                    }else if(address>= 16437 && address < 16489 ){
                                        ycObjName = "NO102";
                                    }else if(address>= 16498 && address < 16546){
                                        ycObjName = "NO204";
                                    }else if(address>= 16546 && address < 16594){
                                        ycObjName = "NO205";
                                    }else if(address>= 16594 && address < 16636){
                                        ycObjName = "NO201";
                                    }else if(address>= 16636 && address < 16678){
                                        ycObjName = "NO202";
                                    }else if(address>= 16678 && address < 16720){
                                        ycObjName = "NO203";
                                    }else if(address>= 16720 && address < 16762){
                                        ycObjName = "NO302";
                                    }else if(address>= 16762 && address < 16810){
                                        ycObjName = "NO401";
                                    }else if(address>= 16810 && address < 16858){
                                        ycObjName = "NO402";
                                    }else if(address>= 16858 && address < 16910){
                                        ycObjName = "NO501";
                                    }else{
                                        //遥测，气象数据
                                        for(Map.Entry<String,YcObject> entry : DataProcessPool.ycPool.entrySet()){
                                            log.debug("遥测对象 ："+ entry.getKey() +" ,信息体地址："+ address +"，执行方法：set" + ycObjJson.getString("name") +"，值：" + ((IeShortFloat)infoObjs[i].getInformationElements()[j][0]).getValue());
                                            setValue(2,entry.getKey(),"set" + ycObjJson.getString("name"),((IeShortFloat)infoObjs[i].getInformationElements()[j][0]).getValue(),ycObjJson.getString("type"));
                                        }
                                    }
                                    if(ycObjName != null){
                                        log.debug("遥测对象 ："+ ycObjName +" ,信息体地址："+ address +"，执行方法：set" + ycObjJson.getString("name") +"，值：" + ((IeShortFloat)infoObjs[i].getInformationElements()[j][0]).getValue());
                                        //往YcObject对象中的属性set值

                                        //判断是否是遥信状态数据(并网、PV连接、警告状态)，是：将值set进遥信状态对象(YxStatusObject)对应的属性中
                                        if ("CONNECT_STATUS".equals(ycObjJson.getString("name"))){
                                            setValue(2,ycObjName,"set" + ycObjJson.getString("name"),(int)((IeShortFloat)infoObjs[i].getInformationElements()[j][0]).getValue(),ycObjJson.getString("type"));
                                            setValue(3,ycObjName,"set" + ycObjJson.getString("name"),(int)((IeShortFloat)infoObjs[i].getInformationElements()[j][0]).getValue(),ycObjJson.getString("type"));
                                        }else if("PV_CONNECT_STATUS".equals(ycObjJson.getString("name"))){
                                            setValue(2,ycObjName,"set" + ycObjJson.getString("name"),(int)((IeShortFloat)infoObjs[i].getInformationElements()[j][0]).getValue(),ycObjJson.getString("type"));
                                            setValue(3,ycObjName,"set" + ycObjJson.getString("name"),(int)((IeShortFloat)infoObjs[i].getInformationElements()[j][0]).getValue(),ycObjJson.getString("type"));
                                        }else if("WARNING_STATUS".equals(ycObjJson.getString("name"))){
                                            setValue(2,ycObjName,"set" + ycObjJson.getString("name"),(int)((IeShortFloat)infoObjs[i].getInformationElements()[j][0]).getValue(),ycObjJson.getString("type"));
                                            setValue(3,ycObjName,"set" + ycObjJson.getString("name"),(int)((IeShortFloat)infoObjs[i].getInformationElements()[j][0]).getValue(),ycObjJson.getString("type"));
                                        }else {
                                            setValue(2,ycObjName,"set" + ycObjJson.getString("name"),((IeShortFloat)infoObjs[i].getInformationElements()[j][0]).getValue(),ycObjJson.getString("type"));
                                        }
                                    }else{
                                        log.warn("遥测对象无名称！");
                                    }
                                }else{
                                    log.warn("找不到的遥测编号: " + address);
                                }
                            }
                        }else {
                            log.debug("类型标识:" + typeId + "未处理");
                        }
                    }
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        };

        new Thread(runnable).start();
    }

    /**
     *
     * @param typeObj
     * @param ycSerial
     * @param methodName
     * @param methodValue
     * @throws NoSuchMethodException
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     */
    public static void setValue(int typeObj,String ycSerial,String methodName,Object methodValue,String mt) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Object obj = null;
        if (typeObj == 1){
            obj = DataProcessPool.yxPool.get(ycSerial);
        }else if(typeObj == 2){
            obj = DataProcessPool.ycPool.get(ycSerial);
        }else{
            obj = DataProcessPool.yxsPool.get(ycSerial);
        }
        //执行反射方法
        setByReflect(obj,methodName,methodValue,mt);
    }

    /**
     *
     * @param obj
     * @param methodName
     * @param methodValue
     * @throws NoSuchMethodException
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     */
    public static void setByReflect(Object obj,String methodName,Object methodValue,String mt) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Method objMethod = null;
        if (!"set".equals(methodName)){
            if("DECIMAL".equals(mt)){
                objMethod = obj.getClass().getMethod(methodName,double.class);
            }else if("int".equals(mt)){
                objMethod = obj.getClass().getMethod(methodName,int.class);
            }else if("String".equals(mt)){
                objMethod = obj.getClass().getMethod(methodName,String.class);
            }else{
                log.warn("不存在的方法类型，无法生成反射方法。");
            }
        }else {
            log.warn("YcObject对象中不存在该set方法");
        }

        if(objMethod != null){
            objMethod.invoke(obj,methodValue);
        }else{
            log.warn("反射方法不存在");
        }
    }
}
