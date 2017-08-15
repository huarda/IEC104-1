package com.visenergy.iec104;

import com.flying.jdbc.SqlHelper;
import com.flying.jdbc.data.CommandType;
import com.flying.jdbc.data.Parameter;
import com.flying.jdbc.db.type.BaseTypes;
import com.flying.jdbc.util.DBConnection;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.lang.reflect.Field;
import java.util.UUID;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Created by zhonghuan on 2017/7/25.
 */
public class YxObject {
    private Log log = LogFactory.getLog(YxObject.class);

    private String BUILDING_ID;
    private String INVERTER_ID;
    private int VERSION_FAIL=-1;        //软件版本不匹配
    private int SYSTEM_FAIL=-1;         //系统故障
    private int NBI_EXP_FAIL=-1;        //逆变电流异常
    private int CYI_FAIL=-1;            //残余电流异常
    private int WDGG_FAIL=-1;           //温度过高
    private int FS_FAIL=-1;             //风扇故障
    private int SPI_FAIL=-1;            //SPI通讯异常
    private int JYZKD_FAIL=-1;          //绝缘阻抗低
    private int AFCI_FAIL=-1;           //AFCI自检失败
    private int ZLDH_FAIL=-1;           //直流电弧故障
    private int ZC3_FAIL=-1;            //组串3反向
    private int LYBHQ_FAIL=-1;          //浪涌保护器故障
    private boolean flag=false;

    public YxObject(){
    }
    public YxObject(String inverterId, String buildingId){
        this.INVERTER_ID = inverterId;
        this.BUILDING_ID = buildingId;

        Runnable runnable = new Runnable() {

            public void run() {
                if(flag==true){
                    String sql =  "INSERT INTO T_PVMANAGE_INVERTER_FAILURE(FA_ID,FA_NAME,BUILDING_ID,INVERTER_ID) " +
                            "VALUES(?,?,?,?)";

                    DBConnection conn = SqlHelper.connPool.getConnection();

                    YxObject yxTable=new YxObject();
                    Class yx=(Class) yxTable.getClass();
                    Field[] fields=yx.getDeclaredFields();
                    String failureDescription = null;

                    for (int i = 0; i <fields.length ; i++) {
                        Field f = fields[i];
                        Object val;
                        String name = null;
                        f.setAccessible(true); //设置些属性是可以访问的
                        try {
                            val = f.get(yxTable);//得到此属性的值
                            name = f.getName();
                            if ("VERSION_FAIL".equals(name)){
                                failureDescription = "软件版本不匹配";
                            }else if ("SYSTEM_FAIL".equals(name)){
                                failureDescription = "系统故障";
                            }else if ("NBI_EXP_FAIL".equals(name)){
                                failureDescription = "逆变电流异常";
                            }else if ("CYI_FAIL".equals(name)){
                                failureDescription = "残余电流异常";
                            }else if ("WDGG_FAIL".equals(name)){
                                failureDescription = "温度过高";
                            }else if ("FS_FAIL".equals(name)){
                                failureDescription = "风扇故障";
                            }else if ("SPI_FAIL".equals(name)){
                                failureDescription = "SPI通讯异常";
                            }else if ("JYZKD_FAIL".equals(name)){
                                failureDescription = "绝缘阻抗低";
                            }else if ("AFCI_FAIL".equals(name)){
                                failureDescription = "AFCI自检失败";
                            }else if ("ZLDH_FAIL".equals(name)){
                                failureDescription = "直流电弧故障";
                            }else if ("ZC3_FAIL".equals(name)){
                                failureDescription = "组串3反向";
                            }else if ("LYBHQ_FAIL".equals(name)){
                                failureDescription = "浪涌保护器故障";
                            }else{
                                log.debug("遥信对象里的其他属性：" + name);
                            }
                            if("0".equals(val)){
                                Parameter[] params = new Parameter[4];

                                String id = UUID.randomUUID().toString().replaceAll("-", "").toUpperCase();
                                params[0] = new Parameter("ID", BaseTypes.VARCHAR,id);
                                params[1] = new Parameter("FA_NAME", BaseTypes.VARCHAR,failureDescription);
                                params[2] = new Parameter("BUILDING_ID", BaseTypes.VARCHAR,BUILDING_ID);
                                params[3] = new Parameter("INVERTER_ID", BaseTypes.VARCHAR,INVERTER_ID);
                                SqlHelper.executeNonQuery(conn, CommandType.Text, sql, params);
                                log.debug("插入遥信故障信息到表中," + failureDescription);
                            }else {
                                log.debug("遥信信息：" + failureDescription + "：false");
                            }
                        }catch (Exception e){
                            e.printStackTrace();
                        }
                    }
                    clear();
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

    public void clear(){
        VERSION_FAIL=-1;
        SYSTEM_FAIL=-1;
        NBI_EXP_FAIL=-1;
        CYI_FAIL=-1;
        WDGG_FAIL=-1;
        FS_FAIL=-1;
        SPI_FAIL=-1;
        JYZKD_FAIL=-1;
        AFCI_FAIL=-1;
        ZLDH_FAIL=-1;
        ZC3_FAIL=-1;
        LYBHQ_FAIL=-1;
        this.flag=false;
    }
    public String getBUILDING_ID() {
        return BUILDING_ID;
    }

    public void setBUILDING_ID(String BUILDING_ID) {
        this.BUILDING_ID = BUILDING_ID;
    }

    public String getINVERTER_ID() {
        return INVERTER_ID;
    }

    public void setINVERTER_ID(String INVERTER_ID) {
        this.INVERTER_ID = INVERTER_ID;
    }

    public int getVERSION_FAIL() {
        return VERSION_FAIL;
    }

    public void setVERSION_FAIL(int VERSION_FAIL) {
        this.VERSION_FAIL = VERSION_FAIL;
        this.flag = true;
    }

    public int getSYSTEM_FAIL() {
        return SYSTEM_FAIL;
    }

    public void setSYSTEM_FAIL(int SYSTEM_FAIL) {
        this.SYSTEM_FAIL = SYSTEM_FAIL;
        this.flag = true;
    }

    public int getNBI_EXP_FAIL() {
        return NBI_EXP_FAIL;
    }

    public void setNBI_EXP_FAIL(int NBI_EXP_FAIL) {
        this.NBI_EXP_FAIL = NBI_EXP_FAIL;
        this.flag = true;
    }

    public int getCYI_FAIL() {
        return CYI_FAIL;
    }

    public void setCYI_FAIL(int CYI_FAIL) {
        this.CYI_FAIL = CYI_FAIL;
        this.flag = true;
    }

    public int getWDGG_FAIL() {
        return WDGG_FAIL;
    }

    public void setWDGG_FAIL(int WDGG_FAIL) {
        this.WDGG_FAIL = WDGG_FAIL;
        this.flag = true;
    }

    public int getFS_FAIL() {
        return FS_FAIL;
    }

    public void setFS_FAIL(int FS_FAIL) {
        this.FS_FAIL = FS_FAIL;
        this.flag = true;
    }

    public int getSPI_FAIL() {
        return SPI_FAIL;
    }

    public void setSPI_FAIL(int SPI_FAIL) {
        this.SPI_FAIL = SPI_FAIL;
        this.flag = true;
    }

    public int getJYZKD_FAIL() {
        return JYZKD_FAIL;
    }

    public void setJYZKD_FAIL(int JYZKD_FAIL) {
        this.JYZKD_FAIL = JYZKD_FAIL;
        this.flag = true;
    }

    public int getAFCI_FAIL() {
        return AFCI_FAIL;
    }

    public void setAFCI_FAIL(int AFCI_FAIL) {
        this.AFCI_FAIL = AFCI_FAIL;
        this.flag = true;
    }

    public int getZLDH_FAIL() {
        return ZLDH_FAIL;
    }

    public void setZLDH_FAIL(int ZLDH_FAIL) {
        this.ZLDH_FAIL = ZLDH_FAIL;
        this.flag = true;
    }

    public int getZC3_FAIL() {
        return ZC3_FAIL;
    }

    public void setZC3_FAIL(int ZC3_FAIL) {
        this.ZC3_FAIL = ZC3_FAIL;
        this.flag = true;
    }

    public int getLYBHQ_FAIL() {
        return LYBHQ_FAIL;
    }

    public void setLYBHQ_FAIL(int LYBHQ_FAIL) {
        this.LYBHQ_FAIL = LYBHQ_FAIL;
        this.flag = true;
    }
}
