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
import java.util.*;
import java.util.concurrent.*;

/**
 * Created by zhonghuan on 2017/7/25.
 */
public class YcObject {
    private Log log = LogFactory.getLog(YcObject.class);

    private static String RABBITMQ_QUEUE = "PV_YC";

    private String ID;
    private String INVERTER_ID;
    private String SERIAL;
    private double ELEC_PROD_HOUR=0;
    private double ELEC_PROD_DAILY=0;
    private double ELEC_PROD_MONTH=0;
    private double ELEC_PROD_YEAR=0;
    private double ELEC_PROD_ALL=0;
    private double OUTPUT_P=0;
    private double CONNECT_P=0;
    private double PEAK_POWER=0;
    private double REACTIVE_P=0;
    private double PV1_U=0;
    private double PV1_I=0;
    private double PV2_U=0;
    private double PV2_I=0;
    private double PV3_U=0;
    private double PV3_I=0;
    private double PV4_U=0;
    private double PV4_I=0;
    private double PV5_U=0;
    private double PV5_I=0;
    private double PV6_U=0;
    private double PV6_I=0;
    private double PV7_U=0;
    private double PV7_I=0;
    private double PV8_U=0;
    private double PV8_I=0;
    private double AC_UA=0;
    private double AC_UB=0;
    private double AC_UC=0;
    private double AC_IA=0;
    private double AC_IB=0;
    private double AC_IC=0;
    private double MACHINE_TEMP=0;
    private double GRID_FRQ=0;
    private double CONVERT_EFF=0;
    private double CO2_CUTS=0;
    private double COAL_SAVE=0;
    private double CONVERT_BENF=0;
    private int CONNECT_STATUS=0;
    private int PV_CONNECT_STATUS=0;
    private int WARNING_STATUS=0;
    private double AMBIENT_TEMP=0;             //环境温度
    private double RADIANT_QUANTITY_1=0;       //辐射量1
    private double IRRADIANCE_1=0;             //辐照度1
    private double RADIANT_QUANTITY_2=0;       //辐射量2
    private double IRRADIANCE_2=0;             //辐照度2
    private double DAMPNESS=0;                 //湿度
    private double PRESSURE=0;                 //压力
    private double WIND_SPEED=0;               //风速
    private double WIND_DIR=0;                 //风向
    private double FULL_HOURS_DAY=0;           //日满发小时数
    private double FULL_HOURS_MON=0;           //月满发小时数
    private double FULL_HOURS_YEAR=0;          //年满发小时数
    private double FULL_HOURS_ALL=0;           //累计满发小时数
    private boolean flag=false;

    private Connection conn = null;
    private Channel channel = null;

    public YcObject(String inverterId,String serial){
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
                    //逆变器数据采集表(历史表)插入语句
                    String sql = "INSERT INTO T_PVMANAGE_INVERTER_COLLECT(INVERTER_ID,ELEC_PROD_HOUR,ELEC_PROD_DAILY," +
                            "ELEC_PROD_MONTH,ELEC_PROD_YEAR,ELEC_PROD_ALL,OUTPUT_P,CONNECT_P,PEAK_POWER,REACTIVE_P," +
                            "PV1_U,PV1_I,PV2_U,PV2_I,PV3_U,PV3_I,PV4_U,PV4_I,PV5_U,PV5_I,PV6_U,PV6_I,PV7_U,PV7_I,PV8_U,PV8_I," +
                            "AC_UA,AC_UB,AC_UC,AC_IA,AC_IB,AC_IC,MACHINE_TEMP,GRID_FRQ,CONVERT_EFF,CO2_CUTS," +
                            "COAL_SAVE,CONVERT_BENF,CONNECT_STATUS,PV_CONNECT_STATUS,WARNING_STATUS,AMBIENT_TEMP," +
                            "RADIANT_QUANTITY_1,IRRADIANCE_1,RADIANT_QUANTITY_2,IRRADIANCE_2,DAMPNESS,PRESSURE,WIND_SPEED,WIND_DIR," +
                            "FULL_HOURS_DAY,FULL_HOURS_MON,FULL_HOURS_YEAR,FULL_HOURS_ALL) " +
                            "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
                    //逆变器实时数据表更新语句
                    String inverter_data_now = "UPDATE T_PVMANAGE_INVERTER_COLLECT_C SET ELEC_PROD_HOUR=?,ELEC_PROD_DAILY=?," +
                            "ELEC_PROD_MONTH=?,ELEC_PROD_YEAR=?,ELEC_PROD_ALL=?,OUTPUT_P=?,CONNECT_P=?,PEAK_POWER=?," +
                            "REACTIVE_P=?,PV1_U=?,PV1_I=?,PV2_U=?,PV2_I=?,PV3_U=?,PV3_I=?,PV4_U=?,PV4_I=?,PV5_U=?,PV5_I=?," +
                            "PV6_U=?,PV6_I=?,PV7_U=?,PV7_I=?,PV8_U=?,PV8_I=?,AC_UA=?,AC_UB=?,AC_UC=?,AC_IA=?,AC_IB=?,AC_IC=?," +
                            "MACHINE_TEMP=?,GRID_FRQ=?,CONVERT_EFF=?,CO2_CUTS=?,COAL_SAVE=?,CONVERT_BENF=?,AMBIENT_TEMP=?," +
                            "RADIANT_QUANTITY_1=?,IRRADIANCE_1=?,RADIANT_QUANTITY_2=?,IRRADIANCE_2=?,DAMPNESS=?,PRESSURE=?,WIND_SPEED=?,WIND_DIR=?," +
                            "FULL_HOURS_DAY=?,FULL_HOURS_MON=?,FULL_HOURS_YEAR=?,FULL_HOURS_ALL=?,TIME=? " +
                            "WHERE INVERTER_ID = ?";
                    //环境数据采集
                    String metero_sql = "INSERT INTO T_PVMANAGE_METERO(WIND_SPEED,WIND_DIREC,PANEL_TEMP,AMBIEN_TEMP,RADIANT_QUANTITY_1,IRRADIANCE_1," +
                            "RADIANT_QUANTITY_2,IRRADIANCE_2,DAMPNESS,PRESSURE) VALUES(?,?,?,?,?,?,?,?,?,?)";

                    DBConnection conn = SqlHelper.connPool.getConnection();
                    Parameter[] params = new Parameter[54];
                    Parameter[] params_new_data = new Parameter[52];
                    Parameter[] params_metero_data = new Parameter[11];

                    //逆变器数据采集历史表
                    COAL_SAVE = ELEC_PROD_ALL*0.4;
                    CONVERT_BENF = (0.42+0.3)*ELEC_PROD_YEAR;
                    if ("1820FB1857737B3BA33A8FEE25164C98".equals(inverterId) || "4D5F552ED8CC4EE981720CBDDB7FEAC6".equals(inverterId)){
                        FULL_HOURS_DAY = ELEC_PROD_DAILY/41.58;
                        FULL_HOURS_MON = ELEC_PROD_MONTH/41.58;
                        FULL_HOURS_YEAR = ELEC_PROD_YEAR/41.58;
                        FULL_HOURS_ALL = ELEC_PROD_ALL/41.58;
                    }else if ("6E5CEB1058E8A530A499DDC363A134C7".equals(inverterId)){
                        FULL_HOURS_DAY = ELEC_PROD_DAILY/21.6;
                        FULL_HOURS_MON = ELEC_PROD_MONTH/21.6;
                        FULL_HOURS_YEAR = ELEC_PROD_YEAR/21.6;
                        FULL_HOURS_ALL = ELEC_PROD_ALL/21.6;
                    }else if ("6F31267F133D958AA4865C81AEA23225".equals(inverterId)){
                        FULL_HOURS_DAY = ELEC_PROD_DAILY/38.88;
                        FULL_HOURS_MON = ELEC_PROD_MONTH/38.88;
                        FULL_HOURS_YEAR = ELEC_PROD_YEAR/38.88;
                        FULL_HOURS_ALL = ELEC_PROD_ALL/38.88;
                    }else if ("7CBE845153C74C3B798618B7F1F81D9D".equals(inverterId) || "FB003754389C03E09BAC593D694102E8".equals(inverterId) ||
                            "FC8FC785AE2E7CF450BCFC876DABE6D8".equals(inverterId)){
                        FULL_HOURS_DAY = ELEC_PROD_DAILY/29.7;
                        FULL_HOURS_MON = ELEC_PROD_MONTH/29.7;
                        FULL_HOURS_YEAR = ELEC_PROD_YEAR/29.7;
                        FULL_HOURS_ALL = ELEC_PROD_ALL/29.7;
                    }else if ("977767AE5E946563CA859C0AA980FA39".equals(inverterId)){
                        FULL_HOURS_DAY = ELEC_PROD_DAILY/17.82;
                        FULL_HOURS_MON = ELEC_PROD_MONTH/17.82;
                        FULL_HOURS_YEAR = ELEC_PROD_YEAR/17.82;
                        FULL_HOURS_ALL = ELEC_PROD_ALL/17.82;
                    }else if ("AAA263543DC49259EBAED22960B4271F".equals(inverterId)){
                        FULL_HOURS_DAY = ELEC_PROD_DAILY/18.15;
                        FULL_HOURS_MON = ELEC_PROD_MONTH/18.15;
                        FULL_HOURS_YEAR = ELEC_PROD_YEAR/18.15;
                        FULL_HOURS_ALL = ELEC_PROD_ALL/18.15;
                    }else if ("C697C5B7684DFC9E10045763AD9721F3".equals(inverterId)){
                        FULL_HOURS_DAY = ELEC_PROD_DAILY/18;
                        FULL_HOURS_MON = ELEC_PROD_MONTH/18;
                        FULL_HOURS_YEAR = ELEC_PROD_YEAR/18;
                        FULL_HOURS_ALL = ELEC_PROD_ALL/18;
                    }else {
                        FULL_HOURS_DAY = ELEC_PROD_DAILY/16.2;
                        FULL_HOURS_MON = ELEC_PROD_MONTH/16.2;
                        FULL_HOURS_YEAR = ELEC_PROD_YEAR/16.2;
                        FULL_HOURS_ALL = ELEC_PROD_ALL/16.2;
                    }

                    params[0] = new Parameter("INVERTER_ID", BaseTypes.VARCHAR,INVERTER_ID);
                    params[1] = new Parameter("ELEC_PROD_HOUR", BaseTypes.DECIMAL,new BigDecimal(ELEC_PROD_HOUR).setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue());
                    params[2] = new Parameter("ELEC_PROD_DAILY", BaseTypes.DECIMAL,new BigDecimal(ELEC_PROD_DAILY).setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue());
                    params[3] = new Parameter("ELEC_PROD_MONTH", BaseTypes.DECIMAL,new BigDecimal(ELEC_PROD_MONTH).setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue());
                    params[4] = new Parameter("ELEC_PROD_YEAR", BaseTypes.DECIMAL,new BigDecimal(ELEC_PROD_YEAR).setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue());
                    params[5] = new Parameter("ELEC_PROD_ALL", BaseTypes.DECIMAL,new BigDecimal(ELEC_PROD_ALL).setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue());
                    params[6] = new Parameter("OUTPUT_P", BaseTypes.DECIMAL,new BigDecimal(OUTPUT_P).setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue());
                    params[7] = new Parameter("CONNECT_P", BaseTypes.DECIMAL,new BigDecimal(CONNECT_P).setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue());
                    params[8] = new Parameter("PEAK_POWER", BaseTypes.DECIMAL,new BigDecimal(PEAK_POWER).setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue() );
                    params[9] = new Parameter("REACTIVE_P", BaseTypes.DECIMAL,new BigDecimal(REACTIVE_P).setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue());
                    params[10] = new Parameter("PV1_U", BaseTypes.DECIMAL,new BigDecimal(PV1_U).setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue());
                    params[11] = new Parameter("PV1_I", BaseTypes.DECIMAL,new BigDecimal(PV1_I).setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue());
                    params[12] = new Parameter("PV2_U", BaseTypes.DECIMAL,new BigDecimal(PV2_U).setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue());
                    params[13] = new Parameter("PV2_I", BaseTypes.DECIMAL,new BigDecimal(PV2_I).setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue());
                    params[14] = new Parameter("PV3_U", BaseTypes.DECIMAL,new BigDecimal(PV3_U).setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue());
                    params[15] = new Parameter("PV3_I", BaseTypes.DECIMAL,new BigDecimal(PV3_I).setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue());
                    params[16] = new Parameter("PV4_U", BaseTypes.DECIMAL,new BigDecimal(PV4_U).setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue());
                    params[17] = new Parameter("PV4_I", BaseTypes.DECIMAL,new BigDecimal(PV4_I).setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue());
                    params[18] = new Parameter("PV5_U", BaseTypes.DECIMAL,new BigDecimal(PV5_U).setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue());
                    params[19] = new Parameter("PV5_I", BaseTypes.DECIMAL,new BigDecimal(PV5_I).setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue());
                    params[20] = new Parameter("PV6_U", BaseTypes.DECIMAL,new BigDecimal(PV6_U).setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue());
                    params[21] = new Parameter("PV6_I", BaseTypes.DECIMAL,new BigDecimal(PV6_I).setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue());
                    params[22] = new Parameter("PV7_U", BaseTypes.DECIMAL,new BigDecimal(PV7_U).setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue());
                    params[23] = new Parameter("PV7_I", BaseTypes.DECIMAL,new BigDecimal(PV7_I).setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue());
                    params[24] = new Parameter("PV8_U", BaseTypes.DECIMAL,new BigDecimal(PV8_U).setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue());
                    params[25] = new Parameter("PV8_I", BaseTypes.DECIMAL,new BigDecimal(PV8_I).setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue());
                    params[26] = new Parameter("AC_UA", BaseTypes.DECIMAL,new BigDecimal(AC_UA).setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue());
                    params[27] = new Parameter("AC_UB", BaseTypes.DECIMAL,new BigDecimal(AC_UB).setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue());
                    params[28] = new Parameter("AC_UC", BaseTypes.DECIMAL,new BigDecimal(AC_UC).setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue());
                    params[29] = new Parameter("AC_IA", BaseTypes.DECIMAL,new BigDecimal(AC_IA).setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue());
                    params[30] = new Parameter("AC_IB", BaseTypes.DECIMAL,new BigDecimal(AC_IB).setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue());
                    params[31] = new Parameter("AC_IC", BaseTypes.DECIMAL,new BigDecimal(AC_IC).setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue());
                    params[32] = new Parameter("MACHINE_TEMP",BaseTypes.DECIMAL, new BigDecimal(MACHINE_TEMP).setScale(1,BigDecimal.ROUND_HALF_UP).doubleValue());
                    params[33] = new Parameter("GRID_FRQ", BaseTypes.DECIMAL,new BigDecimal(GRID_FRQ).setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue());
                    params[34] = new Parameter("CONVERT_EFF", BaseTypes.DECIMAL,new BigDecimal(CONVERT_EFF).setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue());
                    params[35] = new Parameter("CO2_CUTS", BaseTypes.DECIMAL,new BigDecimal(CO2_CUTS).setScale(3,BigDecimal.ROUND_HALF_UP).doubleValue());
                    params[36] = new Parameter("COAL_SAVE", BaseTypes.DECIMAL,new BigDecimal(COAL_SAVE).setScale(3,BigDecimal.ROUND_HALF_UP).doubleValue());
                    params[37] = new Parameter("CONVERT_BENF", BaseTypes.DECIMAL,new BigDecimal(CONVERT_BENF).setScale(4,BigDecimal.ROUND_HALF_UP).doubleValue());
                    params[38] = new Parameter("CONNECT_STATUS", BaseTypes.INTEGER,CONNECT_STATUS);
                    params[39] = new Parameter("PV_CONNECT_STATUS", BaseTypes.INTEGER,PV_CONNECT_STATUS);
                    params[40] = new Parameter("WARNING_STATUS", BaseTypes.INTEGER,WARNING_STATUS);
                    params[41] = new Parameter("AMBIENT_TEMP",BaseTypes.DECIMAL,new BigDecimal(AMBIENT_TEMP).setScale(1,BigDecimal.ROUND_HALF_UP).doubleValue() );
                    params[42] = new Parameter("RADIANT_QUANTITY_1", BaseTypes.DECIMAL,new BigDecimal(RADIANT_QUANTITY_1).setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue());
                    params[43] = new Parameter("IRRADIANCE_1",BaseTypes.DECIMAL,new BigDecimal(IRRADIANCE_1).setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue() );
                    params[44] = new Parameter("RADIANT_QUANTITY_2", BaseTypes.DECIMAL,new BigDecimal(RADIANT_QUANTITY_2).setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue());
                    params[45] = new Parameter("IRRADIANCE_2",BaseTypes.DECIMAL,new BigDecimal(IRRADIANCE_1).setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue() );
                    params[46] = new Parameter("DAMPNESS",BaseTypes.DECIMAL,new BigDecimal(DAMPNESS).setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue());
                    params[47] = new Parameter("PRESSURE",BaseTypes.DECIMAL,new BigDecimal(PRESSURE).setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue());
                    params[48] = new Parameter("WIND_SPEED",BaseTypes.DECIMAL,new BigDecimal(WIND_SPEED).setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue());
                    params[49] = new Parameter("WIND_DIR",BaseTypes.DECIMAL,new BigDecimal(WIND_DIR).setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue());
                    params[50] = new Parameter("FULL_HOURS_DAY",BaseTypes.DECIMAL,new BigDecimal(FULL_HOURS_DAY).setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue());
                    params[51] = new Parameter("FULL_HOURS_MON",BaseTypes.DECIMAL,new BigDecimal(FULL_HOURS_MON).setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue());
                    params[52] = new Parameter("FULL_HOURS_YEAR",BaseTypes.DECIMAL,new BigDecimal(FULL_HOURS_YEAR).setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue());
                    params[53] = new Parameter("FULL_HOURS_ALL",BaseTypes.DECIMAL,new BigDecimal(FULL_HOURS_ALL).setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue());

                    //将数据存入逆变器实时数据表
                    params_new_data[0]  = params[1];
                    params_new_data[1]  = params[2];
                    params_new_data[2]  = params[3];
                    params_new_data[3]  = params[4];
                    params_new_data[4]  = params[5];
                    params_new_data[5]  = params[6];
                    params_new_data[6]  = params[7];
                    params_new_data[7]  = params[8];
                    params_new_data[8]  = params[9];
                    params_new_data[9]  = params[10];
                    params_new_data[10] = params[11];
                    params_new_data[11] = params[12];
                    params_new_data[12] = params[13];
                    params_new_data[13] = params[14];
                    params_new_data[14] = params[15];
                    params_new_data[15] = params[16];
                    params_new_data[16] = params[17];
                    params_new_data[17] = params[18];
                    params_new_data[18] = params[19];
                    params_new_data[19] = params[20];
                    params_new_data[20] = params[21];
                    params_new_data[21] = params[22];
                    params_new_data[22] = params[23];
                    params_new_data[23] = params[24];
                    params_new_data[24] = params[25];
                    params_new_data[25] = params[26];
                    params_new_data[26] = params[27];
                    params_new_data[27] = params[28];
                    params_new_data[28] = params[29];
                    params_new_data[29] = params[30];
                    params_new_data[30] = params[31];
                    params_new_data[31] = params[32];
                    params_new_data[32] = params[33];
                    params_new_data[33] = params[34];
                    params_new_data[34] = params[35];
                    params_new_data[35] = params[36];
                    params_new_data[36] = params[37];
                    params_new_data[37] = params[38];
                    params_new_data[38] = params[42];
                    params_new_data[39] = params[43];
                    params_new_data[40] = params[44];
                    params_new_data[41] = params[45];
                    params_new_data[42] = params[46];
                    params_new_data[43] = params[47];
                    params_new_data[44] = params[48];
                    params_new_data[45] = params[49];
                    params_new_data[46] = params[50];
                    params_new_data[47] = params[51];
                    params_new_data[48] = params[52];
                    params_new_data[49] = params[53];
                    params_new_data[50] = new Parameter("TIME", BaseTypes.TIMESTAMP,new Timestamp(System.currentTimeMillis()));
                    params_new_data[51] = params[0];

                    //气象数据
                    params_metero_data[0] = params[48];
                    params_metero_data[1] = params[49];
                    params_metero_data[2] = params[32];
                    params_metero_data[3] = params[41];
                    params_metero_data[4] = params[42];
                    params_metero_data[5] = params[43];
                    params_metero_data[6] = params[44];
                    params_metero_data[7] = params[45];
                    params_metero_data[8] = params[46];
                    params_metero_data[9] = params[47];

                    try {
                        SqlHelper.executeNonQuery(conn, CommandType.Text, sql, params);
                        //将最新数据更新到逆变器实时数据表
                        SqlHelper.executeNonQuery(conn, CommandType.Text, inverter_data_now, params_new_data);
                        //插入环境仪采集数据
                        SqlHelper.executeNonQuery(conn, CommandType.Text, metero_sql, params_metero_data);
//                        clear();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    SqlHelper.connPool.releaseConnection(conn);
                }else{
                    log.debug("未接收到数据");
                }
            }
        };
        ScheduledExecutorService service  = Executors.newSingleThreadScheduledExecutor();
        service.scheduleAtFixedRate(runnable,120,300, TimeUnit.SECONDS);
    }

    public void clear(){
        ELEC_PROD_HOUR=0;
        ELEC_PROD_DAILY=0;
        ELEC_PROD_MONTH=0;
        ELEC_PROD_YEAR=0;
        ELEC_PROD_ALL=0;
        OUTPUT_P=0;
        CONNECT_P=0;
        PEAK_POWER=0;
        REACTIVE_P=0;
        PV1_U=0;
        PV1_I=0;
        PV2_U=0;
        PV2_I=0;
        PV3_U=0;
        PV3_I=0;
        PV4_U=0;
        PV4_I=0;
        PV5_U=0;
        PV5_I=0;
        PV6_U=0;
        PV6_I=0;
        PV7_U=0;
        PV7_I=0;
        PV8_U=0;
        PV8_I=0;
        AC_UA=0;
        AC_UB=0;
        AC_UC=0;
        AC_IA=0;
        AC_IB=0;
        AC_IC=0;
        MACHINE_TEMP=0;
        GRID_FRQ=0;
        CONVERT_EFF=0;
        CO2_CUTS=0;
        COAL_SAVE=0;
        CONVERT_BENF=0;
        CONNECT_STATUS=0;
        PV_CONNECT_STATUS=0;
        WARNING_STATUS=0;
        AMBIENT_TEMP=0;
        RADIANT_QUANTITY_1=0;
        IRRADIANCE_1=0;
        RADIANT_QUANTITY_2=0;
        IRRADIANCE_2=0;
        DAMPNESS=0;
        PRESSURE=0;
        WIND_SPEED=0;
        WIND_DIR=0;
        this.flag=false;
    }

    public JSONObject toJsonString(){
        Map map = new HashMap();
        map.put("INVERTER_ID", INVERTER_ID);
        map.put("ELEC_PROD_HOUR", ELEC_PROD_HOUR);
        map.put("ELEC_PROD_DAILY", ELEC_PROD_DAILY);
        map.put("ELEC_PROD_ALL", ELEC_PROD_ALL);
        map.put("OUTPUT_P", OUTPUT_P);
        map.put("CONNECT_P", CONNECT_P);
        map.put("PEAK_POWER", PEAK_POWER);
        map.put("REACTIVE_P", REACTIVE_P);
        map.put("PV1_U", PV1_U);
        map.put("PV1_I", PV1_I);
        map.put("PV2_U", PV2_U);
        map.put("PV2_I", PV2_I);
        map.put("PV3_U", PV3_U);
        map.put("PV3_I", PV3_I);
        map.put("PV4_U", PV4_U);
        map.put("PV4_I", PV4_I);
        map.put("PV5_U", PV5_U);
        map.put("PV5_I", PV5_I);
        map.put("PV6_U", PV6_U);
        map.put("PV6_I", PV6_I);
        map.put("PV7_U", PV7_U);
        map.put("PV7_I", PV7_I);
        map.put("PV8_U", PV8_U);
        map.put("PV8_I", PV8_I);
        map.put("AC_UA", AC_UA);
        map.put("AC_UB", AC_UB);
        map.put("AC_UC", AC_UC);
        map.put("AC_IA", AC_IA);
        map.put("AC_IB", AC_IB);
        map.put("AC_IC", AC_IC);
        map.put("MACHINE_TEMP", MACHINE_TEMP);
        map.put("GRID_FRQ", GRID_FRQ);
        map.put("CONVERT_EFF", CONVERT_EFF);
        map.put("CO2_CUTS", CO2_CUTS);
        map.put("COAL_SAVE", COAL_SAVE);
        map.put("CONVERT_BENF", CONVERT_BENF);
        map.put("AMBIENT_TEMP", AMBIENT_TEMP);
        map.put("RADIANT_QUANTITY_1", RADIANT_QUANTITY_1);
        map.put("IRRADIANCE_1", IRRADIANCE_1);
        map.put("RADIANT_QUANTITY_2", RADIANT_QUANTITY_2);
        map.put("IRRADIANCE_2", IRRADIANCE_2);
        map.put("DAMPNESS", DAMPNESS);
        map.put("PRESSURE", PRESSURE);
        map.put("WIND_SPEED", WIND_SPEED);
        map.put("WIND_DIR", WIND_DIR);
        JSONObject jsonObject = JSONObject.fromObject(map);
        return jsonObject;
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

    public String getSERIAL() {
        return SERIAL;
    }

    public void setSERIAL(String SERIAL) {
        this.SERIAL = SERIAL;
    }

    public double getELEC_PROD_HOUR() {
        return ELEC_PROD_HOUR;
    }

    public void setELEC_PROD_HOUR(double ELEC_PROD_HOUR) {
        this.ELEC_PROD_HOUR = ELEC_PROD_HOUR;
        this.flag=true;
        this.sendRabbitMq("SERIAL","ELEC_PROD_HOUR",new BigDecimal(ELEC_PROD_HOUR).setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue());
    }

    public double getELEC_PROD_DAILY() {
        return ELEC_PROD_DAILY;
    }

    public void setELEC_PROD_DAILY(double ELEC_PROD_DAILY) {
        this.ELEC_PROD_DAILY = ELEC_PROD_DAILY;
        this.flag=true;
        this.sendRabbitMq("SERIAL","ELEC_PROD_DAILY",new BigDecimal(ELEC_PROD_DAILY).setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue());
    }

    public double getELEC_PROD_MONTH() {
        return ELEC_PROD_MONTH;
    }

    public void setELEC_PROD_MONTH(double ELEC_PROD_MONTH) {
        this.ELEC_PROD_MONTH = ELEC_PROD_MONTH;
        this.flag=true;
        this.sendRabbitMq("SERIAL","ELEC_PROD_MONTH",new BigDecimal(ELEC_PROD_MONTH).setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue());
    }

    public double getELEC_PROD_YEAR() {
        return ELEC_PROD_YEAR;
    }

    public void setELEC_PROD_YEAR(double ELEC_PROD_YEAR) {
        this.ELEC_PROD_YEAR = ELEC_PROD_YEAR;
        this.flag=true;
        this.sendRabbitMq("SERIAL","ELEC_PROD_YEAR",new BigDecimal(ELEC_PROD_YEAR).setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue());
    }

    public double getELEC_PROD_ALL() {
        return ELEC_PROD_ALL;
    }

    public void setELEC_PROD_ALL(double ELEC_PROD_ALL) {
        this.ELEC_PROD_ALL = ELEC_PROD_ALL;
        this.flag=true;
        this.sendRabbitMq("SERIAL","ELEC_PROD_ALL",new BigDecimal(ELEC_PROD_ALL).setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue());
    }

    public double getOUTPUT_P() {
        return OUTPUT_P;
    }

    public void setOUTPUT_P(double OUTPUT_P) {
        this.OUTPUT_P = OUTPUT_P;
        this.flag=true;
        this.sendRabbitMq("SERIAL","OUTPUT_P",new BigDecimal(OUTPUT_P).setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue());

    }

    public double getCONNECT_P() {
        return CONNECT_P;
    }

    public void setCONNECT_P(double CONNECT_P) {
        this.CONNECT_P = CONNECT_P;
        this.flag=true;
        this.sendRabbitMq("SERIAL","CONNECT_P",new BigDecimal(CONNECT_P).setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue());
    }

    public double getPEAK_POWER() {
        return PEAK_POWER;
    }

    public void setPEAK_POWER(double PEAK_POWER) {
        this.PEAK_POWER = PEAK_POWER;
        this.flag=true;
        this.sendRabbitMq("SERIAL","PEAK_POWER",new BigDecimal(PEAK_POWER).setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue());
    }

    public double getREACTIVE_P() {
        return REACTIVE_P;
    }

    public void setREACTIVE_P(double REACTIVE_P) {
        this.REACTIVE_P = REACTIVE_P;
        this.flag=true;
        this.sendRabbitMq("SERIAL","REACTIVE_P",new BigDecimal(REACTIVE_P).setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue());

    }

    public double getPV1_U() {
        return PV1_U;
    }

    public void setPV1_U(double PV1_U) {
        this.PV1_U = PV1_U;
        this.flag=true;
        this.sendRabbitMq("SERIAL","PV1_U",new BigDecimal(PV1_U).setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue());
    }

    public double getPV1_I() {
        return PV1_I;
    }

    public void setPV1_I(double PV1_I) {
        this.PV1_I = PV1_I;
        this.flag=true;
        this.sendRabbitMq("SERIAL","PV1_I",new BigDecimal(PV1_I).setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue());
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
        this.sendRabbitMq("SERIAL","AC_UA",new BigDecimal(AC_UA).setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue());
    }

    public double getAC_UB() {
        return AC_UB;
    }

    public void setAC_UB(double AC_UB) {
        this.AC_UB = AC_UB;
        this.flag=true;
        this.sendRabbitMq("SERIAL","AC_UB",new BigDecimal(AC_UB).setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue());
    }

    public double getAC_UC() {
        return AC_UC;
    }

    public void setAC_UC(double AC_UC) {
        this.AC_UC = AC_UC;
        this.flag=true;
        this.sendRabbitMq("SERIAL","AC_UC",new BigDecimal(AC_UC).setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue());
    }

    public double getAC_IA() {
        return AC_IA;
    }

    public void setAC_IA(double AC_IA) {
        this.AC_IA = AC_IA;
        this.flag=true;
        this.sendRabbitMq("SERIAL","AC_IA",new BigDecimal(AC_IA).setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue());
    }

    public double getAC_IB() {
        return AC_IB;
    }

    public void setAC_IB(double AC_IB) {
        this.AC_IB = AC_IB;
        this.flag=true;
        this.sendRabbitMq("SERIAL","AC_IB",new BigDecimal(AC_IB).setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue());
    }

    public double getAC_IC() {
        return AC_IC;
    }

    public void setAC_IC(double AC_IC) {
        this.AC_IC = AC_IC;
        this.flag=true;
        this.sendRabbitMq("SERIAL","AC_IC",new BigDecimal(AC_IC).setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue());
    }

    public double getMACHINE_TEMP() {
        return MACHINE_TEMP;
    }

    public void setMACHINE_TEMP(double MACHINE_TEMP) {
        this.MACHINE_TEMP = MACHINE_TEMP;
        this.flag=true;
        this.sendRabbitMq("SERIAL","MACHINE_TEMP",new BigDecimal(MACHINE_TEMP).setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue());
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
        this.sendRabbitMq("SERIAL","CONVERT_EFF",new BigDecimal(CONVERT_EFF).setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue());

    }

    public double getCO2_CUTS() {
        return CO2_CUTS;
    }

    public void setCO2_CUTS(double CO2_CUTS) {
        this.CO2_CUTS = CO2_CUTS;
        this.flag=true;
        this.sendRabbitMq("SERIAL","CO2_CUTS",new BigDecimal(CO2_CUTS).setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue());
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
        sendRabbitMq("AMBIENT_TEMP",new BigDecimal(AMBIENT_TEMP).setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue());
    }

    public double getRADIANT_QUANTITY_1() {
        return RADIANT_QUANTITY_1;
    }

    public void setRADIANT_QUANTITY_1(double RADIANT_QUANTITY_1) {
        this.RADIANT_QUANTITY_1 = RADIANT_QUANTITY_1;
        this.flag=true;
        sendRabbitMq("RADIANT_QUANTITY_1",new BigDecimal(RADIANT_QUANTITY_1).setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue());
    }

    public double getIRRADIANCE_1() {
        return IRRADIANCE_1;
    }

    public void setIRRADIANCE_1(double IRRADIANCE_1) {
        this.IRRADIANCE_1 = IRRADIANCE_1;
        this.flag=true;
        sendRabbitMq("IRRADIANCE_1",new BigDecimal(IRRADIANCE_1).setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue());
    }

    public double getRADIANT_QUANTITY_2() {
        return RADIANT_QUANTITY_2;
    }

    public void setRADIANT_QUANTITY_2(double RADIANT_QUANTITY_2) {
        this.RADIANT_QUANTITY_2 = RADIANT_QUANTITY_2;
        this.flag=true;
        sendRabbitMq("RADIANT_QUANTITY_2",new BigDecimal(RADIANT_QUANTITY_2).setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue());
    }

    public double getIRRADIANCE_2() {
        return IRRADIANCE_2;
    }

    public void setIRRADIANCE_2(double IRRADIANCE_2) {
        this.IRRADIANCE_2 = IRRADIANCE_2;
        this.flag=true;
        sendRabbitMq("IRRADIANCE_2",new BigDecimal(IRRADIANCE_2).setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue());
    }

    public double getDAMPNESS() {
        return DAMPNESS;
    }

    public void setDAMPNESS(double DAMPNESS) {
        this.DAMPNESS = DAMPNESS;
        this.flag=true;
        sendRabbitMq("DAMPNESS",new BigDecimal(DAMPNESS).setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue());
    }

    public double getPRESSURE() {
        return PRESSURE;
    }

    public void setPRESSURE(double PRESSURE) {
        this.PRESSURE = PRESSURE;
        this.flag=true;
        sendRabbitMq("PRESSURE",new BigDecimal(PRESSURE).setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue());
    }

    public double getWIND_SPEED() {
        return WIND_SPEED;
    }

    public void setWIND_SPEED(double WIND_SPEED) {
        this.WIND_SPEED = WIND_SPEED;
        this.flag=true;
        sendRabbitMq("WIND_SPEED",new BigDecimal(WIND_SPEED).setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue());
    }

    public double getWIND_DIR() {
        return WIND_DIR;
    }

    public void setWIND_DIR(double WIND_DIR) {
        this.WIND_DIR = WIND_DIR;
        this.flag=true;
        sendRabbitMq("WIND_DIR",new BigDecimal(WIND_DIR).setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue());
    }

    public double getFULL_HOURS_DAY() {
        return FULL_HOURS_DAY;
    }

    public void setFULL_HOURS_DAY(double FULL_HOURS_DAY) {
        this.FULL_HOURS_DAY = FULL_HOURS_DAY;
        this.flag=true;
    }

    public double getFULL_HOURS_MON() {
        return FULL_HOURS_MON;
    }

    public void setFULL_HOURS_MON(double FULL_HOURS_MON) {
        this.FULL_HOURS_MON = FULL_HOURS_MON;
        this.flag=true;
    }

    public double getFULL_HOURS_YEAR() {
        return FULL_HOURS_YEAR;
    }

    public void setFULL_HOURS_YEAR(double FULL_HOURS_YEAR) {
        this.FULL_HOURS_YEAR = FULL_HOURS_YEAR;
        this.flag=true;
    }

    public double getFULL_HOURS_ALL() {
        return FULL_HOURS_ALL;
    }

    public void setFULL_HOURS_ALL(double FULL_HOURS_ALL) {
        this.FULL_HOURS_ALL = FULL_HOURS_ALL;
        this.flag=true;
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

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    public void sendRabbitMq(String name,Object value) {
        Map map = new HashedMap();
        map.put(name,value);
        sendRabbitMq("lightTopology","topologyData",map);
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
            RabbitMqUtils.sendMq(channel,RABBITMQ_QUEUE,JSONObject.fromObject(resultMap).toString());
        } catch (IOException e) {
           log.error("RabbitMq传输消息失败",e);
        } catch (TimeoutException e) {
            log.error("RabbitMq传输消息失败",e);
        }
    }
}