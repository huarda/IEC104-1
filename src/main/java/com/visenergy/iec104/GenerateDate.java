package com.visenergy.iec104;

import com.visenergy.iec104.util.ChangeUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Fuxudong on 2017/8/25.
 */
public class GenerateDate {
    private static Log log = LogFactory.getLog(GenerateDate.class);
    private static List<Map> list = new ArrayList<Map>();
    private static Map<String, Float> map = new HashMap<String, Float>();
    //101
    private static float elec_day_101 = 0f;
    private static float elec_mon_101 = 300f;
    private static float elec_year_101 = 10000f;
    private static float elec_all_101 = 10000f;
    //102
    private static float elec_day_102 = 5f;
    private static float elec_mon_102 = 200f;
    private static float elec_year_102 = 9000f;
    private static float elec_all_102 = 9000f;
    //204
    private static float elec_day_204 = 0f;
    private static float elec_mon_204 = 400f;
    private static float elec_year_204 = 8000f;
    private static float elec_all_204 = 8000f;
    //205
    private static float elec_day_205 = 10f;
    private static float elec_mon_205 = 300f;
    private static float elec_year_205 = 5000f;
    private static float elec_all_205 = 5000f;
    //201
    private static float elec_day_201 = 4f;
    private static float elec_all_201 = 6000f;
    //202
    private static float elec_day_202 = 3f;
    private static float elec_all_202 = 5000f;
    //203
    private static float elec_day_203 = 0f;
    private static float elec_all_203 = 5500f;
    //302
    private static float elec_day_302 = 10f;
    private static float elec_all_302 = 8000f;

    //401
    private static float elec_day_401 = 0f;
    private static float elec_mon_401 = 500f;
    private static float elec_year_401 = 6500f;
    private static float elec_all_401 = 6500f;
    //402
    private static float elec_day_402 = 20f;
    private static float elec_mon_402 = 600f;
    private static float elec_year_402 = 12000f;
    private static float elec_all_402 = 12000f;
    //501
    private static float elec_day_501 = 8f;
    private static float elec_mon_501 = 700f;
    private static float elec_year_501 = 7000f;
    private static float elec_all_501 = 7000f;
    static{
        map.put("elec_day_101", elec_day_101);
        map.put("elec_mon_101", elec_mon_101);
        map.put("elec_year_101", elec_year_101);
        map.put("elec_all_101", elec_all_101);
        map.put("elec_day_102", elec_day_102);
        map.put("elec_mon_102", elec_mon_102);
        map.put("elec_year_102", elec_year_102);
        map.put("elec_all_102", elec_all_102);
        map.put("elec_day_204", elec_day_204);
        map.put("elec_mon_204", elec_mon_204);
        map.put("elec_year_204", elec_year_204);
        map.put("elec_all_204", elec_all_204);
        map.put("elec_day_205", elec_day_205);
        map.put("elec_mon_205", elec_mon_205);
        map.put("elec_year_205", elec_year_205);
        map.put("elec_all_205", elec_all_205);
        map.put("elec_day_201", elec_day_201);
        map.put("elec_all_201", elec_all_201);
        map.put("elec_day_202", elec_day_202);
        map.put("elec_all_202", elec_all_202);
        map.put("elec_day_203", elec_day_203);
        map.put("elec_all_203", elec_all_203);

        map.put("elec_day_302", elec_day_302);
        map.put("elec_all_302", elec_all_302);
        map.put("elec_day_401", elec_day_401);
        map.put("elec_mon_401", elec_mon_401);
        map.put("elec_year_401", elec_year_401);
        map.put("elec_all_401", elec_all_401);
        map.put("elec_day_402", elec_day_402);
        map.put("elec_mon_402", elec_mon_402);
        map.put("elec_year_402", elec_year_402);
        map.put("elec_all_402", elec_all_402);
        map.put("elec_day_501", elec_day_501);
        map.put("elec_mon_501", elec_mon_501);
        map.put("elec_year_501", elec_year_501);
        map.put("elec_all_501", elec_all_501);
    }

    public static String getMinuatesData1(){
        StringBuffer sBuffer = new StringBuffer("68 F2 00 00 00 00 0D 1D 03 00 01 00");

        //风速等气象信息
        String RADIANT_QUANTITY_1 = "69 40 00";
        float radiant_1 = (float) Math.random()*30;
        log.info("RADIANT_QUANTITY_1：" + RADIANT_QUANTITY_1 + "，radiant_1：" + radiant_1);
        sBuffer.append(" " + RADIANT_QUANTITY_1).append(" " + ChangeUtils.encode(radiant_1) + "00");
        String IRRADIANCE_1 = "6A 40 00";
        float irr_1 = (float)Math.random()*50;
        log.info("IRRADIANCE_1：" + IRRADIANCE_1 + "，irr_1：" + irr_1);
        sBuffer.append(" " + IRRADIANCE_1).append(" " + ChangeUtils.encode(irr_1) + "00");
        String RADIANT_QUANTITY_2 = "6B 40 00";
        float randiant_2 = (float)Math.random()*30;
        log.info("RADIANT_QUANTITY_2：" + RADIANT_QUANTITY_2 + "，randiant_2：" + randiant_2);
        sBuffer.append(" " + RADIANT_QUANTITY_2).append(" " + ChangeUtils.encode(randiant_2) + "00");
        String IRRADIANCE_2 = "6C 40 00";
        float irr_2 = (float)Math.random()*50;
        log.info("IRRADIANCE_2：" + IRRADIANCE_2 + "，irr_2：" + irr_2);
        sBuffer.append(" " + IRRADIANCE_2).append(" " + ChangeUtils.encode(irr_2) + "00");
        String AMBIENT_TEMP = "6D 40 00";
        float ambient_temp = (float) Math.random()*30;
        log.info("AMBIENT_TEMP：" + AMBIENT_TEMP + "，ambient_temp：" + ambient_temp);
        sBuffer.append(" " + AMBIENT_TEMP).append(" " + ChangeUtils.encode(ambient_temp) + "00");
        String DAMPNESS = "6E 40 00";
        float dampness = (float) Math.random()*20;
        log.info("DAMPNESS：" + DAMPNESS + "，dampness：" + dampness);
        sBuffer.append(" " + DAMPNESS).append(" " + ChangeUtils.encode(dampness) + "00");
        String PRESSURE = "6F 40 00";
        float pressure = (float) Math.random()*30;
        log.info("PRESSURE：" + PRESSURE + "，pressure：" + pressure);
        sBuffer.append(" " + PRESSURE).append(" " + ChangeUtils.encode(pressure) + "00");
        String WIND_SPEED = "70 40 00";
        float windSpeed = (float) Math.random()*10;
        log.info("WIND_SPEED：" + WIND_SPEED + "，windSpeed：" + windSpeed);
        sBuffer.append(" " + WIND_SPEED).append(" " + ChangeUtils.encode(windSpeed) + "00");
        String WIND_DIR = "71 40 00";
        float windDir = (float) Math.random()*360;
        log.info("WIND_DIR：" + WIND_DIR + "，windDir：" + windDir);
        sBuffer.append(" " + WIND_DIR).append(" " + ChangeUtils.encode(windDir) + "00");

        //NO101
        String NO101_ELEC_PROD_DAY = "25 40 00";
        elec_day_101 = (float) Math.random()*10 + map.get("elec_day_101");
        log.info("NO101_ELEC_PROD_DAY：" + NO101_ELEC_PROD_DAY + "，elec_day_101：" + elec_day_101);
        sBuffer.append(" " + NO101_ELEC_PROD_DAY).append(" " + ChangeUtils.encode(elec_day_101) + "00");
        String NO101_ELEC_PROD_Month = "26 40 00";
        elec_mon_101 = (float) Math.random()*10 + map.get("elec_mon_101");
        log.info("NO101_ELEC_PROD_Month：" + NO101_ELEC_PROD_Month + "，elec_mon_101：" + elec_mon_101);
        sBuffer.append(" " + NO101_ELEC_PROD_Month).append(" " + ChangeUtils.encode(elec_mon_101) + "00");
        String NO101_ELEC_PROD_Year = "27 40 00";
        elec_year_101 = (float) Math.random()*10 + map.get("elec_year_101");
        log.info("NO101_ELEC_PROD_Year：" + NO101_ELEC_PROD_Year + "，elec_year_101：" + elec_year_101);
        sBuffer.append(" " + NO101_ELEC_PROD_Year).append(" " + ChangeUtils.encode(elec_year_101) + "00");
        String NO101_ELEC_PROD_All = "28 40 00";
        elec_all_101 = (float) Math.random()*10 + map.get("elec_all_101");
        log.info("NO101_ELEC_PROD_All：" + NO101_ELEC_PROD_All + "，elec_all_101：" + elec_all_101);
        sBuffer.append(" " + NO101_ELEC_PROD_All).append(" " + ChangeUtils.encode(elec_all_101) + "00");
        String NO101_Convert_Eff = "1C 40 00";
        float con_eff_101 = (float) Math.random()*8 + 90;
        log.info("NO101_Convert_Eff：" + NO101_Convert_Eff + "，con_eff_101：" + con_eff_101);
        sBuffer.append(" " + NO101_Convert_Eff).append(" " + ChangeUtils.encode(con_eff_101) + "00");

        //NO102
        String NO102_ELEC_PROD_DAY = "59 40 00";
        elec_day_102 = (float) Math.random()*10 + map.get("elec_day_102");
        sBuffer.append(" " + NO102_ELEC_PROD_DAY).append(" " + ChangeUtils.encode(elec_day_102) + "00");
        String NO102_ELEC_PROD_Month = "5A 40 00";
        elec_mon_102 = (float) Math.random()*10 + map.get("elec_mon_102");
        sBuffer.append(" " + NO102_ELEC_PROD_Month).append(" " + ChangeUtils.encode(elec_mon_102) + "00");
        String NO102_ELEC_PROD_Year = "5B 40 00";
        elec_year_102 = (float) Math.random()*10 + map.get("elec_year_102");
        sBuffer.append(" " + NO102_ELEC_PROD_Year).append(" " + ChangeUtils.encode(elec_year_102) + "00");
        String NO102_ELEC_PROD_All = "5C 40 00";
        elec_all_102 = (float) Math.random()*10 + map.get("elec_all_102");
        sBuffer.append(" " + NO102_ELEC_PROD_All).append(" " + ChangeUtils.encode(elec_all_102) + "00");
        String NO102_Convert_Eff = "50 40 00";
        float con_eff_102 = (float) Math.random()*8 + 90;
        sBuffer.append(" " + NO102_Convert_Eff).append(" " + ChangeUtils.encode(con_eff_102) + "00");

        //NO204
        String NO204_ELEC_PROD_DAY = "96 40 00";
        elec_day_204 = (float) Math.random()*10 + map.get("elec_day_204");
        sBuffer.append(" " + NO204_ELEC_PROD_DAY).append(" " + ChangeUtils.encode(elec_day_204) + "00");
        String NO204_ELEC_PROD_Month = "97 40 00";
        elec_mon_204 = (float) Math.random()*10 + map.get("elec_mon_204");
        sBuffer.append(" " + NO204_ELEC_PROD_Month).append(" " + ChangeUtils.encode(elec_mon_204) + "00");
        String NO204_ELEC_PROD_Year = "98 40 00";
        elec_year_204 = (float) Math.random()*10 + map.get("elec_year_204");
        sBuffer.append(" " + NO204_ELEC_PROD_Year).append(" " + ChangeUtils.encode(elec_year_204) + "00");
        String NO204_ELEC_PROD_All = "99 40 00";
        elec_all_204 = (float) Math.random()*10 + map.get("elec_all_204");
        sBuffer.append(" " + NO204_ELEC_PROD_All).append(" " + ChangeUtils.encode(elec_all_204) + "00");
        String NO204_Convert_Eff = "8D 40 00";
        float con_eff_204 = (float) Math.random()*8 + 90;
        sBuffer.append(" " + NO204_Convert_Eff).append(" " + ChangeUtils.encode(con_eff_204) + "00");

        //NO205
        String NO205_ELEC_PROD_DAY = "C6 40 00";
        elec_day_205 = (float) Math.random()*10 + map.get("elec_day_205");
        sBuffer.append(" " + NO205_ELEC_PROD_DAY).append(" " + ChangeUtils.encode(elec_day_205) + "00");
        String NO205_ELEC_PROD_Month = "C7 40 00";
        elec_mon_205 = (float) Math.random()*10 + map.get("elec_mon_205");
        sBuffer.append(" " + NO205_ELEC_PROD_Month).append(" " + ChangeUtils.encode(elec_mon_205) + "00");
        String NO205_ELEC_PROD_Year = "C8 40 00";
        elec_year_205 = (float) Math.random()*10 + map.get("elec_year_205");
        sBuffer.append(" " + NO205_ELEC_PROD_Year).append(" " + ChangeUtils.encode(elec_year_205) + "00");
        String NO205_ELEC_PROD_All = "C9 40 00";
        elec_all_205 = (float) Math.random()*10 + map.get("elec_all_205");
        sBuffer.append(" " + NO205_ELEC_PROD_All).append(" " + ChangeUtils.encode(elec_all_205) + "00");
        String NO205_Convert_Eff = "BD 40 00";
        float con_eff_205 = (float) Math.random()*8 + 90;
        sBuffer.append(" " + NO205_Convert_Eff).append(" " + ChangeUtils.encode(con_eff_205) + "00");
        return sBuffer.toString();
    }

    public static String getMinuatesData2(){
        StringBuffer sBuffer = new StringBuffer("68 E2 00 00 00 00 0D 1B 03 00 01 00");

        //NO201
        String NO201_ELEC_PROD_DAY = "E8 40 00";
        elec_day_201 = (float) Math.random()*10 + map.get("elec_day_201");
        sBuffer.append(" " + NO201_ELEC_PROD_DAY).append(" " + ChangeUtils.encode(elec_day_201) + "00");
        String NO201_ELEC_PROD_All = "E7 40 00";
        elec_all_201 = (float) Math.random()*10 + map.get("elec_all_201");
        sBuffer.append(" " + NO201_ELEC_PROD_All).append(" " + ChangeUtils.encode(elec_all_201) + "00");
        String NO201_Convert_Eff = "F1 40 00";
        float con_eff_201 = (float) Math.random()*8 + 90;
        sBuffer.append(" " + NO201_Convert_Eff).append(" " + ChangeUtils.encode(con_eff_201) + "00");

        //NO202
        String NO202_ELEC_PROD_DAY = "12 41 00";
        elec_day_202 = (float) Math.random()*10 + map.get("elec_day_202");
        sBuffer.append(" " + NO202_ELEC_PROD_DAY).append(" " + ChangeUtils.encode(elec_day_202) + "00");
        String NO202_ELEC_PROD_All = "11 41 00";
        elec_all_202 = (float) Math.random()*10 + map.get("elec_all_202");
        sBuffer.append(" " + NO202_ELEC_PROD_All).append(" " + ChangeUtils.encode(elec_all_202) + "00");
        String NO202_Convert_Eff = "1B 41 00";
        float con_eff_202 = (float) Math.random()*8 + 90;
        sBuffer.append(" " + NO202_Convert_Eff).append(" " + ChangeUtils.encode(con_eff_202) + "00");

        //NO203
        String NO203_ELEC_PROD_DAY = "3C 41 00";
        elec_day_203 = (float) Math.random()*10 + map.get("elec_day_203");
        sBuffer.append(" " + NO203_ELEC_PROD_DAY).append(" " + ChangeUtils.encode(elec_day_203) + "00");
        String NO203_ELEC_PROD_All = "3B 41 00";
        elec_all_203 = (float) Math.random()*10 + map.get("elec_all_203");
        sBuffer.append(" " + NO203_ELEC_PROD_All).append(" " + ChangeUtils.encode(elec_all_203) + "00");
        String NO203_Convert_Eff = "45 41 00";
        float con_eff_203 = (float) Math.random()*8 + 90;
        sBuffer.append(" " + NO203_Convert_Eff).append(" " + ChangeUtils.encode(con_eff_203) + "00");

        //NO302
        String NO302_ELEC_PROD_DAY = "66 41 00";
        elec_day_302 = (float) Math.random()*10 + map.get("elec_day_302");
        sBuffer.append(" " + NO302_ELEC_PROD_DAY).append(" " + ChangeUtils.encode(elec_day_302) + "00");
        String NO302_ELEC_PROD_All = "65 41 00";
        elec_all_302 = (float) Math.random()*10 + map.get("elec_all_302");
        sBuffer.append(" " + NO302_ELEC_PROD_All).append(" " + ChangeUtils.encode(elec_all_302) + "00");
        String NO302_Convert_Eff = "6F 41 00";
        float con_eff_302 = (float) Math.random()*8 + 90;
        sBuffer.append(" " + NO302_Convert_Eff).append(" " + ChangeUtils.encode(con_eff_302) + "00");

        //NO401
        String NO401_ELEC_PROD_DAY = "9E 41 00";
        elec_day_401 = (float) Math.random()*10 + map.get("elec_day_401");
        sBuffer.append(" " + NO401_ELEC_PROD_DAY).append(" " + ChangeUtils.encode(elec_day_401) + "00");
        String NO401_ELEC_PROD_Month = "9F 41 00";
        elec_mon_401 = (float) Math.random()*10 + map.get("elec_mon_401");
        sBuffer.append(" " + NO401_ELEC_PROD_Month).append(" " + ChangeUtils.encode(elec_mon_401) + "00");
        String NO401_ELEC_PROD_Year = "A0 41 00";
        elec_year_401 = (float) Math.random()*10 + map.get("elec_year_401");
        sBuffer.append(" " + NO401_ELEC_PROD_Year).append(" " + ChangeUtils.encode(elec_year_401) + "00");
        String NO401_ELEC_PROD_All = "A1 41 00";
        elec_all_401 = (float) Math.random()*10 + map.get("elec_all_401");
        sBuffer.append(" " + NO401_ELEC_PROD_All).append(" " + ChangeUtils.encode(elec_all_401) + "00");
        String NO401_Convert_Eff = "95 41 00";
        float con_eff_401 = (float) Math.random()*8 + 90;
        sBuffer.append(" " + NO401_Convert_Eff).append(" " + ChangeUtils.encode(con_eff_401) + "00");

        //NO402
        String NO402_ELEC_PROD_DAY = "CE 41 00";
        elec_day_402 = (float) Math.random()*10 + map.get("elec_day_402");
        sBuffer.append(" " + NO402_ELEC_PROD_DAY).append(" " + ChangeUtils.encode(elec_day_402) + "00");
        String NO402_ELEC_PROD_Month = "CF 41 00";
        elec_mon_402 = (float) Math.random()*10 + map.get("elec_mon_402");
        sBuffer.append(" " + NO402_ELEC_PROD_Month).append(" " + ChangeUtils.encode(elec_mon_402) + "00");
        String NO402_ELEC_PROD_Year = "D0 41 00";
        elec_year_402 = (float) Math.random()*10 + map.get("elec_year_402");
        sBuffer.append(" " + NO402_ELEC_PROD_Year).append(" " + ChangeUtils.encode(elec_year_402) + "00");
        String NO402_ELEC_PROD_All = "D1 41 00";
        elec_all_402 = (float) Math.random()*10 + map.get("elec_all_402");
        sBuffer.append(" " + NO402_ELEC_PROD_All).append(" " + ChangeUtils.encode(elec_all_402) + "00");
        String NO402_Convert_Eff = "C5 41 00";
        float con_eff_402 = (float) Math.random()*8 + 90;
        sBuffer.append(" " + NO402_Convert_Eff).append(" " + ChangeUtils.encode(con_eff_402) + "00");

        //NO501
        String NO501_ELEC_PROD_DAY = "FE 41 00";
        elec_day_501 = (float) Math.random()*10 + map.get("elec_day_501");
        sBuffer.append(" " + NO501_ELEC_PROD_DAY).append(" " + ChangeUtils.encode(elec_day_501) + "00");
        String NO501_ELEC_PROD_Month = "FF 41 00";
        elec_mon_501 = (float) Math.random()*10 + map.get("elec_mon_501");
        sBuffer.append(" " + NO501_ELEC_PROD_Month).append(" " + ChangeUtils.encode(elec_mon_501) + "00");
        String NO501_ELEC_PROD_Year = "00 42 00";
        elec_year_501 = (float) Math.random()*10 + map.get("elec_year_501");
        sBuffer.append(" " + NO501_ELEC_PROD_Year).append(" " + ChangeUtils.encode(elec_year_501) + "00");
        String NO501_ELEC_PROD_All = "01 42 00";
        elec_all_501 = (float) Math.random()*10 + map.get("elec_all_501");
        sBuffer.append(" " + NO501_ELEC_PROD_All).append(" " + ChangeUtils.encode(elec_all_501) + "00");
        String NO501_Convert_Eff = "F5 41 00";
        float con_eff_501 = (float) Math.random()*8 + 90;
        sBuffer.append(" " + NO501_Convert_Eff).append(" " + ChangeUtils.encode(con_eff_501) + "00");

        return sBuffer.toString();
    }

    public static void main(String[] args) {
        System.out.println(getMinuatesData1());
    }
}
