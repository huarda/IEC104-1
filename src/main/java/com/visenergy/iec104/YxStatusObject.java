package com.visenergy.iec104;

import com.flying.jdbc.SqlHelper;
import com.flying.jdbc.data.CommandType;
import com.flying.jdbc.data.Parameter;
import com.flying.jdbc.db.type.BaseTypes;
import com.flying.jdbc.util.DBConnection;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.visenergy.iec104.util.RabbitMqUtils;
import net.sf.json.JSONObject;
import org.apache.commons.collections.map.HashedMap;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * Created by Fuxdong on 2017/7/26 0026.
 */
public class YxStatusObject {
    private Log log = LogFactory.getLog(YxStatusObject.class);
    private static String RABBITMQ_QUEUE = "PV_YXSTATUS";

    private String INVERTER_ID = "";
    private String SERIAL = "";
    private int COMMUNICATE_STATUS = -1;
    private int CONNECT_STATUS = -1;
    private int PV_CONNECT_STATUS = -1;
    private int WARNING_STATUS = -1;
    private boolean flag=false;

    private Connection conn = null;
    private Channel channel = null;

    public YxStatusObject(String inverterId, String serial){
        this.INVERTER_ID = inverterId;
        this.SERIAL = serial;

        //初始化rabbitmq
        try {
            this.getChannel();
        } catch (IOException e) {
            log.error("初始化rabbitMq失败",e);
        } catch (TimeoutException e) {
            log.error("初始化rabbitMq失败",e);
        }
        Runnable runnable = new Runnable() {

            public void run() {
                if(flag==true){
                    String sql = "UPDATE T_PVMANAGE_INVERTER A SET A.COMMUNICATE_STATUS = ?,A.CONNECT_STATUS = ?," +
                            "A.PV_CONNECT_STATUS = ?,A.WARNING_STATUS = ?,A.TIME=? WHERE A.INVERTER_ID = ?";

                    DBConnection conn = SqlHelper.connPool.getConnection();
                    Parameter[] params = new Parameter[6];

                    params[0] = new Parameter("COMMUNICATE_STATUS", BaseTypes.INTEGER,COMMUNICATE_STATUS);
                    params[1] = new Parameter("CONNECT_STATUS", BaseTypes.INTEGER,CONNECT_STATUS);
                    params[2] = new Parameter("PV_CONNECT_STATUS", BaseTypes.INTEGER,PV_CONNECT_STATUS);
                    params[3] = new Parameter("WARNING_STATUS", BaseTypes.INTEGER,WARNING_STATUS);
                    params[4] = new Parameter("TIME", BaseTypes.TIMESTAMP,new Timestamp(System.currentTimeMillis()));
                    params[5] = new Parameter("INVERTER_ID", BaseTypes.VARCHAR,INVERTER_ID);

                    try {
                        //将最新数据更新到逆变器实时数据表
                        SqlHelper.executeNonQuery(conn, CommandType.Text, sql, params);
                        clear();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    SqlHelper.connPool.releaseConnection(conn);
                }else{
                    log.debug("未接收到数据");
                }
            }
        };
        ScheduledExecutorService service = Executors.newSingleThreadScheduledExecutor();
        // 第二个参数为首次执行的延时时间，第三个参数为定时执行的间隔时间
        service.scheduleAtFixedRate(runnable, 360, 360, TimeUnit.SECONDS);
    }

    public String getSERIAL() {
        return SERIAL;
    }

    public void setSERIAL(String SERIAL) {
        this.SERIAL = SERIAL;
    }

    public int getCOMMUNICATE_STATUS() {
        return COMMUNICATE_STATUS;
    }

    public void setCOMMUNICATE_STATUS(int COMMUNICATE_STATUS) {
        this.COMMUNICATE_STATUS = COMMUNICATE_STATUS;
        this.flag = true;
        this.sendRabbitMq("SERIAL","COMMUNICATE_STATUS",COMMUNICATE_STATUS);
    }

    public int getCONNECT_STATUS() {
        return CONNECT_STATUS;
    }

    public void setCONNECT_STATUS(int CONNECT_STATUS) {
        this.CONNECT_STATUS = CONNECT_STATUS;
        this.flag = true;
        this.sendRabbitMq("SERIAL","CONNECT_STATUS",CONNECT_STATUS);
    }

    public int getPV_CONNECT_STATUS() {
        return PV_CONNECT_STATUS;
    }

    public void setPV_CONNECT_STATUS(int PV_CONNECT_STATUS) {
        this.PV_CONNECT_STATUS = PV_CONNECT_STATUS;
        this.flag = true;
        this.sendRabbitMq("SERIAL","PV_CONNECT_STATUS",PV_CONNECT_STATUS);
    }

    public int getWARNING_STATUS() {
        return WARNING_STATUS;
    }

    public void setWARNING_STATUS(int WARNING_STATUS) {
        this.WARNING_STATUS = WARNING_STATUS;
        this.flag = true;
        this.sendRabbitMq("SERIAL","WARNING_STATUS",WARNING_STATUS);
    }

    public void clear(){
//        COMMUNICATE_STATUS = -1;
//        CONNECT_STATUS = -1;
//        PV_CONNECT_STATUS = -1;
//        WARNING_STATUS = -1;
        this.flag=false;
    }

    public Connection getConn() throws IOException, TimeoutException {
        if(conn == null){
            conn = RabbitMqUtils.newConnection();
        }
        return conn;
    }

    public Channel getChannel() throws IOException, TimeoutException {
        if(channel == null){
            channel = getConn().createChannel();
        }
        return channel;
    }

    public void sendRabbitMq(String ID,String name,Object value){
        Map map = new HashedMap();
        map.put("name",ID);
        map.put("SERIAL",getSERIAL());
        map.put(name,value);
        sendRabbitMq("lightTopology","inverterData",map);
    }


    public void sendRabbitMq(String module,String subModule,Map dataMap){
        Map<String,Object> resultMap = new HashedMap();
        resultMap.put("module",module);
        resultMap.put("subModule",subModule);
        resultMap.put("data",dataMap);

        try {
            RabbitMqUtils.sendMq(channel,RABBITMQ_QUEUE, JSONObject.fromObject(resultMap).toString());
        } catch (IOException e) {
            log.error("RabbitMq传输消息失败",e);
        } catch (TimeoutException e) {
            log.error("RabbitMq传输消息失败",e);
        }
    }
}
