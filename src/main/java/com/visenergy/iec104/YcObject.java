package com.visenergy.iec104;

import com.flying.jdbc.SqlHelper;
import com.flying.jdbc.data.CommandType;
import com.flying.jdbc.data.Parameter;
import com.flying.jdbc.db.type.BaseTypes;
import com.flying.jdbc.util.DBConnection;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.UUID;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Created by zhonghuan on 2017/7/25.
 */
public class YcObject {
    private Log log = LogFactory.getLog(YcObject.class);

    private String ID;
    private String INVERTER_ID;
    private double ELEC_PROD_HOUR=-1;
    private double ELEC_PROD_DAILY=-1;
    private double ELEC_PROD_MONTH=-1;
    private double ELEC_PROD_YEAR=-1;
    private double ELEC_PROD_ALL=-1;
    private double OUTPUT_P=-1;
    private double CONNECT_P=-1;
    private double PEAK_POWER=-1;
    private double REACTIVE_P=-1;
    private double PV1_U=-1;
    private double PV1_I=-1;
    private double PV2_U=-1;
    private double PV2_I=-1;
    private double PV3_U=-1;
    private double PV3_I=-1;
    private double PV4_U=-1;
    private double PV4_I=-1;
    private double PV5_U=-1;
    private double PV5_I=-1;
    private double PV6_U=-1;
    private double PV6_I=-1;
    private double PV7_U=-1;
    private double PV7_I=-1;
    private double PV8_U=-1;
    private double PV8_I=-1;
    private double AC_UA=-1;
    private double AC_UB=-1;
    private double AC_UC=-1;
    private double AC_IA=-1;
    private double AC_IB=-1;
    private double AC_IC=-1;
    private double MACHINE_TEMP=-1;
    private double GRID_FRQ=-1;
    private double CONVERT_EFF=-1;
    private double CO2_CUTS=-1;
    private double COAL_SAVE=-1;
    private double CONVERT_BENF=-1;
    private int CONNECT_STATUS=-1;
    private int PV_CONNECT_STATUS=-1;
    private int WARNING_STATUS=-1;
    private double AMBIENT_TEMP=-1;
    private boolean flag=false;

    public YcObject(String inverterId){
        this.INVERTER_ID = inverterId;
        Runnable runnable = new Runnable() {

            public void run() {
                if(flag==true){
                    String sql =  "INSERT INTO T_PVMANAGE_INVERTER_COLLECT(ID,INVERTER_ID,ELEC_PROD_HOUR,ELEC_PROD_DAILY," +
                            "ELEC_PROD_MONTH,ELEC_PROD_YEAR,ELEC_PROD_ALL,OUTPUT_P,CONNECT_P,PEAK_POWER,REACTIVE_P," +
                            "PV1_U,PV1_I,PV2_U,PV2_I,PV3_U,PV3_I,PV4_U,PV4_I,PV5_U,PV5_I,PV6_U,PV6_I,PV7_U,PV7_I,PV8_U,PV8_I," +
                            "AC_UA,AC_UB,AC_UC,AC_IA,AC_IB,AC_IC,MACHINE_TEMP,GRID_FRQ,CONVERT_EFF,CO2_CUTS," +
                            "COAL_SAVE,CONVERT_BENF,CONNECT_STATUS,PV_CONNECT_STATUS,WARNING_STATUS,AMBIENT_TEMP) " +
                            "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

                    DBConnection conn = SqlHelper.connPool.getConnection();
                    Parameter[] params = new Parameter[43];

                    String id = UUID.randomUUID().toString().replaceAll("-", "").toUpperCase();
                    params[0] = new Parameter("ID", BaseTypes.VARCHAR,id);
                    params[1] = new Parameter("INVERTER_ID", BaseTypes.VARCHAR,INVERTER_ID);
                    params[2] = new Parameter("ELEC_PROD_HOUR", BaseTypes.DECIMAL,ELEC_PROD_HOUR);
                    params[3] = new Parameter("ELEC_PROD_DAILY", BaseTypes.DECIMAL,ELEC_PROD_DAILY);
                    params[4] = new Parameter("ELEC_PROD_MONTH", BaseTypes.DECIMAL,ELEC_PROD_MONTH);
                    params[5] = new Parameter("ELEC_PROD_YEAR", BaseTypes.DECIMAL,ELEC_PROD_YEAR);
                    params[6] = new Parameter("ELEC_PROD_ALL", BaseTypes.DECIMAL,ELEC_PROD_ALL);
                    params[7] = new Parameter("OUTPUT_P", BaseTypes.DECIMAL,OUTPUT_P);
                    params[8] = new Parameter("CONNECT_P", BaseTypes.DECIMAL,CONNECT_P);
                    params[9] = new Parameter("PEAK_POWER", BaseTypes.DECIMAL,PEAK_POWER );
                    params[10] = new Parameter("REACTIVE_P", BaseTypes.DECIMAL,REACTIVE_P);
                    params[11] = new Parameter("PV1_U", BaseTypes.DECIMAL,PV1_U);
                    params[12] = new Parameter("PV1_I", BaseTypes.DECIMAL,PV1_I);
                    params[13] = new Parameter("PV2_U", BaseTypes.DECIMAL,PV2_U);
                    params[14] = new Parameter("PV2_I", BaseTypes.DECIMAL,PV2_I);
                    params[15] = new Parameter("PV3_U", BaseTypes.DECIMAL,PV3_U);
                    params[16] = new Parameter("PV3_I", BaseTypes.DECIMAL,PV3_I);
                    params[17] = new Parameter("PV4_U", BaseTypes.DECIMAL,PV4_U);
                    params[18] = new Parameter("PV4_I", BaseTypes.DECIMAL,PV4_I);
                    params[19] = new Parameter("PV5_U", BaseTypes.DECIMAL,PV5_U);
                    params[20] = new Parameter("PV5_I", BaseTypes.DECIMAL,PV5_I);
                    params[21] = new Parameter("PV6_U", BaseTypes.DECIMAL,PV6_U);
                    params[22] = new Parameter("PV6_I", BaseTypes.DECIMAL,PV6_I);
                    params[23] = new Parameter("PV7_U", BaseTypes.DECIMAL,PV7_U);
                    params[24] = new Parameter("PV7_I", BaseTypes.DECIMAL,PV7_I);
                    params[25] = new Parameter("PV8_U", BaseTypes.DECIMAL,PV8_U);
                    params[26] = new Parameter("PV8_I", BaseTypes.DECIMAL,PV8_I);
                    params[27] = new Parameter("AC_UA", BaseTypes.DECIMAL,AC_UA);
                    params[28] = new Parameter("AC_UB", BaseTypes.DECIMAL,AC_UB);
                    params[29] = new Parameter("AC_UC", BaseTypes.DECIMAL,AC_UC);
                    params[30] = new Parameter("AC_IA", BaseTypes.DECIMAL,AC_IA);
                    params[31] = new Parameter("AC_IB", BaseTypes.DECIMAL,AC_IB);
                    params[32] = new Parameter("AC_IC", BaseTypes.DECIMAL,AC_IC);
                    params[33] = new Parameter("MACHINE_TEMP",BaseTypes.DECIMAL, MACHINE_TEMP);
                    params[34] = new Parameter("GRID_FRQ", BaseTypes.DECIMAL,GRID_FRQ);
                    params[35] = new Parameter("CONVERT_EFF", BaseTypes.DECIMAL,CONVERT_EFF);
                    params[36] = new Parameter("CO2_CUTS", BaseTypes.DECIMAL,CO2_CUTS);
                    params[37] = new Parameter("COAL_SAVE", BaseTypes.DECIMAL,COAL_SAVE);
                    params[38] = new Parameter("CONVERT_BENF", BaseTypes.DECIMAL,CONVERT_BENF);
                    params[39] = new Parameter("CONNECT_STATUS", BaseTypes.INTEGER,CONNECT_STATUS);
                    params[40] = new Parameter("PV_CONNECT_STATUS", BaseTypes.INTEGER,PV_CONNECT_STATUS);
                    params[41] = new Parameter("WARNING_STATUS", BaseTypes.INTEGER,WARNING_STATUS);
                    params[42] = new Parameter("AMBIENT_TEMP",BaseTypes.DECIMAL,AMBIENT_TEMP );

                    try {
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
        service.scheduleAtFixedRate(runnable, 0, 300, TimeUnit.SECONDS);
    }

    public void clear(){
        ELEC_PROD_HOUR=-1;
        ELEC_PROD_DAILY=-1;
        ELEC_PROD_MONTH=-1;
        ELEC_PROD_YEAR=-1;
        ELEC_PROD_ALL=-1;
        OUTPUT_P=-1;
        CONNECT_P=-1;
        PEAK_POWER=-1;
        REACTIVE_P=-1;
        PV1_U=-1;
        PV1_I=-1;
        PV2_U=-1;
        PV2_I=-1;
        PV3_U=-1;
        PV3_I=-1;
        PV4_U=-1;
        PV4_I=-1;
        PV5_U=-1;
        PV5_I=-1;
        PV6_U=-1;
        PV6_I=-1;
        PV7_U=-1;
        PV7_I=-1;
        PV8_U=-1;
        PV8_I=-1;
        AC_UA=-1;
        AC_UB=-1;
        AC_UC=-1;
        AC_IA=-1;
        AC_IB=-1;
        AC_IC=-1;
        MACHINE_TEMP=-1;
        GRID_FRQ=-1;
        CONVERT_EFF=-1;
        CO2_CUTS=-1;
        COAL_SAVE=-1;
        CONVERT_BENF=-1;
        CONNECT_STATUS=-1;
        PV_CONNECT_STATUS=-1;
        WARNING_STATUS=-1;
        AMBIENT_TEMP=-1;
        this.flag=false;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
        this.flag=true;
    }

    public String getINVERTER_ID() {
        return INVERTER_ID;
    }

    public void setINVERTER_ID(String INVERTER_ID) {
        this.INVERTER_ID = INVERTER_ID;
        this.flag=true;
    }

    public double getELEC_PROD_HOUR() {
        return ELEC_PROD_HOUR;
    }

    public void setELEC_PROD_HOUR(double ELEC_PROD_HOUR) {
        this.ELEC_PROD_HOUR = ELEC_PROD_HOUR;
        this.flag=true;
    }

    public double getELEC_PROD_DAILY() {
        return ELEC_PROD_DAILY;
    }

    public void setELEC_PROD_DAILY(double ELEC_PROD_DAILY) {
        this.ELEC_PROD_DAILY = ELEC_PROD_DAILY;
        this.flag=true;
    }

    public double getELEC_PROD_MONTH() {
        return ELEC_PROD_MONTH;
    }

    public void setELEC_PROD_MONTH(double ELEC_PROD_MONTH) {
        this.ELEC_PROD_MONTH = ELEC_PROD_MONTH;
        this.flag=true;
    }

    public double getELEC_PROD_YEAR() {
        return ELEC_PROD_YEAR;
    }

    public void setELEC_PROD_YEAR(double ELEC_PROD_YEAR) {
        this.ELEC_PROD_YEAR = ELEC_PROD_YEAR;
        this.flag=true;
    }

    public double getELEC_PROD_ALL() {
        return ELEC_PROD_ALL;
    }

    public void setELEC_PROD_ALL(double ELEC_PROD_ALL) {
        this.ELEC_PROD_ALL = ELEC_PROD_ALL;
        this.flag=true;
    }

    public double getOUTPUT_P() {
        return OUTPUT_P;
    }

    public void setOUTPUT_P(double OUTPUT_P) {
        this.OUTPUT_P = OUTPUT_P;
        this.flag=true;
    }

    public double getCONNECT_P() {
        return CONNECT_P;
    }

    public void setCONNECT_P(double CONNECT_P) {
        this.CONNECT_P = CONNECT_P;
        this.flag=true;
    }

    public double getPEAK_POWER() {
        return PEAK_POWER;
    }

    public void setPEAK_POWER(double PEAK_POWER) {
        this.PEAK_POWER = PEAK_POWER;
        this.flag=true;
    }

    public double getREACTIVE_P() {
        return REACTIVE_P;
    }

    public void setREACTIVE_P(double REACTIVE_P) {
        this.REACTIVE_P = REACTIVE_P;
        this.flag=true;
    }

    public double getPV1_U() {
        return PV1_U;
    }

    public void setPV1_U(double PV1_U) {
        this.PV1_U = PV1_U;
        this.flag=true;
    }

    public double getPV1_I() {
        return PV1_I;
    }

    public void setPV1_I(double PV1_I) {
        this.PV1_I = PV1_I;
        this.flag=true;
    }

    public double getPV2_U() {
        return PV2_U;
    }

    public void setPV2_U(double PV2_U) {
        this.PV2_U = PV2_U;
        this.flag=true;
    }

    public double getPV2_I() {
        return PV2_I;
    }

    public void setPV2_I(double PV2_I) {
        this.PV2_I = PV2_I;
        this.flag=true;
    }

    public double getPV3_U() {
        return PV3_U;
    }

    public void setPV3_U(double PV3_U) {
        this.PV3_U = PV3_U;
        this.flag=true;
    }

    public double getPV3_I() {
        return PV3_I;
    }

    public void setPV3_I(double PV3_I) {
        this.PV3_I = PV3_I;
        this.flag=true;
    }

    public double getPV4_U() {
        return PV4_U;
    }

    public void setPV4_U(double PV4_U) {
        this.PV4_U = PV4_U;
        this.flag=true;
    }

    public double getPV4_I() {
        return PV4_I;
    }

    public void setPV4_I(double PV4_I) {
        this.PV4_I = PV4_I;
        this.flag=true;
    }

    public double getPV5_U() {
        return PV5_U;
    }

    public void setPV5_U(double PV5_U) {
        this.PV5_U = PV5_U;
        this.flag=true;
    }

    public double getPV5_I() {
        return PV5_I;
    }

    public void setPV5_I(double PV5_I) {
        this.PV5_I = PV5_I;
        this.flag=true;
    }

    public double getPV6_U() {
        return PV6_U;
    }

    public void setPV6_U(double PV6_U) {
        this.PV6_U = PV6_U;
        this.flag=true;
    }

    public double getPV6_I() {
        return PV6_I;
    }

    public void setPV6_I(double PV6_I) {
        this.PV6_I = PV6_I;
        this.flag=true;
    }

    public double getPV7_U() {
        return PV7_U;
    }

    public void setPV7_U(double PV7_U) {
        this.PV7_U = PV7_U;
        this.flag=true;
    }

    public double getPV7_I() {
        return PV7_I;
    }

    public void setPV7_I(double PV7_I) {
        this.PV7_I = PV7_I;
        this.flag=true;
    }

    public double getPV8_U() {
        return PV8_U;
    }

    public void setPV8_U(double PV8_U) {
        this.PV8_U = PV8_U;
        this.flag=true;
    }

    public double getPV8_I() {
        return PV8_I;
    }

    public void setPV8_I(double PV8_I) {
        this.PV8_I = PV8_I;
        this.flag=true;
    }

    public double getAC_UA() {
        return AC_UA;
    }

    public void setAC_UA(double AC_UA) {
        this.AC_UA = AC_UA;
        this.flag=true;
    }

    public double getAC_UB() {
        return AC_UB;
    }

    public void setAC_UB(double AC_UB) {
        this.AC_UB = AC_UB;
        this.flag=true;
    }

    public double getAC_UC() {
        return AC_UC;
    }

    public void setAC_UC(double AC_UC) {
        this.AC_UC = AC_UC;
        this.flag=true;
    }

    public double getAC_IA() {
        return AC_IA;
    }

    public void setAC_IA(double AC_IA) {
        this.AC_IA = AC_IA;
        this.flag=true;
    }

    public double getAC_IB() {
        return AC_IB;
    }

    public void setAC_IB(double AC_IB) {
        this.AC_IB = AC_IB;
        this.flag=true;
    }

    public double getAC_IC() {
        return AC_IC;
    }

    public void setAC_IC(double AC_IC) {
        this.AC_IC = AC_IC;
        this.flag=true;
    }

    public double getMACHINE_TEMP() {
        return MACHINE_TEMP;
    }

    public void setMACHINE_TEMP(double MACHINE_TEMP) {
        this.MACHINE_TEMP = MACHINE_TEMP;
        this.flag=true;
    }

    public double getGRID_FRQ() {
        return GRID_FRQ;
    }

    public void setGRID_FRQ(double GRID_FRQ) {
        this.GRID_FRQ = GRID_FRQ;
        this.flag=true;
    }

    public double getCONVERT_EFF() {
        return CONVERT_EFF;
    }

    public void setCONVERT_EFF(double CONVERT_EFF) {
        this.CONVERT_EFF = CONVERT_EFF;
        this.flag=true;
    }

    public double getCO2_CUTS() {
        return CO2_CUTS;
    }

    public void setCO2_CUTS(double CO2_CUTS) {
        this.CO2_CUTS = CO2_CUTS;
        this.flag=true;
    }

    public double getCOAL_SAVE() {
        return COAL_SAVE;
    }

    public void setCOAL_SAVE(double COAL_SAVE) {
        this.COAL_SAVE = COAL_SAVE;
        this.flag=true;
    }

    public double getCONVERT_BENF() {
        return CONVERT_BENF;
    }

    public void setCONVERT_BENF(double CONVERT_BENF) {
        this.CONVERT_BENF = CONVERT_BENF;
        this.flag=true;
    }

    public int getCONNECT_STATUS() {
        return CONNECT_STATUS;
    }

    public void setCONNECT_STATUS(int CONNECT_STATUS) {
        this.CONNECT_STATUS = CONNECT_STATUS;
        this.flag=true;
    }

    public int getPV_CONNECT_STATUS() {
        return PV_CONNECT_STATUS;
    }

    public void setPV_CONNECT_STATUS(int PV_CONNECT_STATUS) {
        this.PV_CONNECT_STATUS = PV_CONNECT_STATUS;
        this.flag=true;
    }

    public int getWARNING_STATUS() {
        return WARNING_STATUS;
    }

    public void setWARNING_STATUS(int WARNING_STATUS) {
        this.WARNING_STATUS = WARNING_STATUS;
        this.flag=true;
    }

    public double getAMBIENT_TEMP() {
        return AMBIENT_TEMP;
    }

    public void setAMBIENT_TEMP(double AMBIENT_TEMP) {
        this.AMBIENT_TEMP = AMBIENT_TEMP;
        this.flag=true;
    }

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }
}