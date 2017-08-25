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
public class GenerateHourDate {
    private static Log log = LogFactory.getLog(GenerateHourDate.class);
    private static List<Map> list = new ArrayList<Map>();
    private static Map<String, Float> map = new HashMap<String, Float>();
    //101
    private static float elec_day_101 = 1f;
    private static float elec_mon_101 = 0f;
    private static float elec_year_101 = 0f;
    private static float elec_all_101 = 0f;
    //102
    private static float elec_day_102 = 0f;
    private static float elec_mon_102 = 0f;
    private static float elec_year_102 = 0f;
    private static float elec_all_102 = 0f;
    //204
    private static float elec_day_204 = 0f;
    private static float elec_mon_204 = 0f;
    private static float elec_year_204 = 0f;
    private static float elec_all_204 = 0f;
    //205
    private static float elec_day_205 = 0f;
    private static float elec_mon_205 = 0f;
    private static float elec_year_205 = 0f;
    private static float elec_all_205 = 0f;
    //201
    private static float elec_day_201 = 0f;
    private static float elec_all_201 = 0f;
    //202
    private static float elec_day_202 = 0f;
    private static float elec_all_202 = 0f;
    //203
    private static float elec_day_203 = 0f;
    private static float elec_all_203 = 0f;
    //302
    private static float elec_day_302 = 0f;
    private static float elec_all_302 = 0f;

    //401
    private static float elec_day_401 = 0f;
    private static float elec_mon_401 = 0f;
    private static float elec_year_401 = 0f;
    private static float elec_all_401 = 0f;
    //402
    private static float elec_day_402 = 0f;
    private static float elec_mon_402 = 0f;
    private static float elec_year_402 = 0f;
    private static float elec_all_402 = 0f;
    //501
    private static float elec_day_501 = 0f;
    private static float elec_mon_501 = 0f;
    private static float elec_year_501 = 0f;
    private static float elec_all_501 = 0f;

    static {
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



    public static String getMinuatesData(){
        StringBuffer sBuffer = new StringBuffer("00 00 00 00 0D 0B 03 00 01 00");

        //风速等气象信息
        String RADIANT_QUANTITY_1 = "69 40 00";
        float radiant_1 = (float) Math.random()*30;
        sBuffer.append(" " + RADIANT_QUANTITY_1).append(" " + ChangeUtils.floatToHexstr(radiant_1) + "01");
        String IRRADIANCE_1 = "6A 40 00";
        float irr_1 = (float)Math.random()*50;
        sBuffer.append(" " + IRRADIANCE_1).append(" " + ChangeUtils.floatToHexstr(irr_1) + "01");
        String RADIANT_QUANTITY_2 = "6B 40 00";
        float randiant_2 = (float)Math.random()*30;
        sBuffer.append(" " + RADIANT_QUANTITY_2).append(" " + ChangeUtils.floatToHexstr(randiant_2) + "01");
        String IRRADIANCE_2 = "6C 40 00";
        float irr_2 = (float)Math.random()*50;
        sBuffer.append(" " + IRRADIANCE_2).append(" " + ChangeUtils.floatToHexstr(irr_2) + "01");
        String AMBIENT_TEMP = "6D 40 00";
        float ambient_temp = (float) Math.random()*30;
        sBuffer.append(" " + AMBIENT_TEMP).append(" " + ChangeUtils.floatToHexstr(ambient_temp) + "01");
        String DAMPNESS = "6E 40 00";
        float dampness = (float) Math.random()*20;
        sBuffer.append(" " + DAMPNESS).append(" " + ChangeUtils.floatToHexstr(dampness) + "01");
        String PRESSURE = "6F 40 00";
        float pressure = (float) Math.random()*30;
        sBuffer.append(" " + PRESSURE).append(" " + ChangeUtils.floatToHexstr(pressure) + "01");
        String WIND_SPEED = "70 40 00";
        float windSpeed = (float) Math.random()*10;
        sBuffer.append(" " + WIND_SPEED).append(" " + ChangeUtils.floatToHexstr(windSpeed) + "01");
        String WIND_DIR = "71 40 00";
        float windDir = (float) Math.random()*360;
        sBuffer.append(" " + WIND_DIR).append(" " + ChangeUtils.floatToHexstr(windDir) + "01");

        //NO101
        String NO101_ELEC_PROD_DAY = "25 40 00";
        elec_day_101 += (float) Math.random()*10 + map.get("elec_day_101");
        sBuffer.append(" " + NO101_ELEC_PROD_DAY).append(" " + ChangeUtils.floatToHexstr(elec_all_101) + "01");
        String NO101_ELEC_PROD_Month = "26 40 00";
        elec_mon_101 += (float) Math.random()*10 + map.get("elec_mon_101");
        sBuffer.append(" " + NO101_ELEC_PROD_Month).append(" " + ChangeUtils.floatToHexstr(elec_mon_101) + "01");

        sBuffer.append("ED 41 00 9A 19 6F 43 00");

        String NO101_ELEC_PROD_Year = "27 40 00";
        elec_year_101 += (float) Math.random()*10 + map.get("elec_year_101");
        String NO101_ELEC_PROD_All = "28 40 00";
        elec_all_101 += (float) Math.random()*10 + map.get("elec_all_101");
        String NO101_Convert_Eff = "1C 40 00";
        float con_eff_101 = (float) Math.random()*8 + 90;

        //NO102
        String NO102_ELEC_PROD_DAY = "59 40 00";
        elec_day_102 += (float) Math.random()*10 + map.get("elec_day_102");
        String NO102_ELEC_PROD_Month = "5A 40 00";
        elec_mon_102 += (float) Math.random()*10 + map.get("elec_mon_102");
        String NO102_ELEC_PROD_Year = "5B 40 00";
        elec_year_102 += (float) Math.random()*10 + map.get("elec_year_102");
        String NO102_ELEC_PROD_All = "5C 40 00";
        elec_all_102 += (float) Math.random()*10 + map.get("elec_all_102");
        String NO102_Convert_Eff = "50 40 00";
        float con_eff_102 = (float) Math.random()*8 + 90;

        //NO204
        String NO204_ELEC_PROD_DAY = "96 40 00";
        elec_day_204 = (float) Math.random()*10 + map.get("elec_day_204");
        String NO204_ELEC_PROD_Month = "97 40 00";
        elec_mon_204 = (float) Math.random()*10 + map.get("elec_mon_204");
        String NO204_ELEC_PROD_Year = "98 40 00";
        elec_year_204 = (float) Math.random()*10 + map.get("elec_year_204");
        String NO204_ELEC_PROD_All = "99 40 00";
        elec_all_204 = (float) Math.random()*10 + map.get("elec_all_204");
        String NO204_Convert_Eff = "8D 40 00";
        float con_eff_204 = (float) Math.random()*8 + 90;

        //NO205
        String NO205_ELEC_PROD_DAY = "C6 40 00";
        float elec_day_205 = (float) Math.random()*10;
        String NO205_ELEC_PROD_Month = "C7 40 00";
        String NO205_ELEC_PROD_Year = "C8 40 00";
        String NO205_ELEC_PROD_All = "C9 40 00";
        String NO205_Convert_Eff = "BD 40 00";

        //NO201
        String NO201_ELEC_PROD_DAY = "E8 40 00";
        float elec_day_201 = (float) Math.random()*10;
        String NO201_ELEC_PROD_All = "E7 40 00";
        String NO201_Convert_Eff = "F1 40 00";

        //NO202
        String NO202_ELEC_PROD_DAY = "12 41 00";
        float elec_day_202 = (float) Math.random()*10;
        String NO202_ELEC_PROD_All = "11 41 00";
        String NO202_Convert_Eff = "1B 41 00";

        //NO203
        String NO203_ELEC_PROD_DAY = "3C 41 00";
        float elec_day_203 = (float) Math.random()*10;
        String NO203_ELEC_PROD_All = "3B 41 00";
        String NO203_Convert_Eff = "45 41 00";

        //NO302
        String NO302_ELEC_PROD_DAY = "66 41 00";
        float elec_day_302 = (float) Math.random()*10;
        String NO302_ELEC_PROD_All = "65 41 00";
        String NO302_Convert_Eff = "6F 41 00";

        //NO401
        String NO401_ELEC_PROD_DAY = "9E 41 00";
        float elec_day_401 = (float) Math.random()*10;
        String NO401_ELEC_PROD_Month = "9F 41 00";
        String NO401_ELEC_PROD_Year = "A0 41 00";
        String NO401_ELEC_PROD_All = "A1 41 00";
        String NO401_Convert_Eff = "95 41 00";

        //NO402
        String NO402_ELEC_PROD_DAY = "CE 41 00";
        float elec_day_402 = (float) Math.random()*10;
        String NO402_ELEC_PROD_Month = "CF 41 00";
        String NO402_ELEC_PROD_Year = "D0 41 00";
        String NO402_ELEC_PROD_All = "D1 41 00";
        String NO402_Convert_Eff = "C5 41 00";

        //NO501
        String NO501_ELEC_PROD_DAY = "FE 41 00";
        float elec_day_501 = (float) Math.random()*10;
        String NO501_ELEC_PROD_Month = "FF 41 00";
        String NO501_ELEC_PROD_Year = "00 42 00";
        String NO501_ELEC_PROD_All = "01 42 00";
        String NO501_Convert_Eff = "F5 41 00";

        return sBuffer.toString();
    }

    public static String getProtocalStr(){
        String content = GenerateHourDate.getMinuatesData().replaceAll(" ","");

        String protocalStr = "68" + Integer.toHexString(content.length()/2).toUpperCase() + content;

        String regx = "(.{2})";

        protocalStr = protocalStr.replaceAll(regx,"$1 ");

        return protocalStr;
    }

    public static void main(String[] args) {
        System.out.println(getProtocalStr());
    }
}
