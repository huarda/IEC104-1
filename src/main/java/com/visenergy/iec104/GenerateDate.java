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
    private static float co2_cuts_101 = 9970f;
    //102
    private static float elec_day_102 = 5f;
    private static float elec_mon_102 = 200f;
    private static float elec_year_102 = 9000f;
    private static float elec_all_102 = 9000f;
    private static float co2_cuts_102 = 8973f;
    //204
    private static float elec_day_204 = 0f;
    private static float elec_mon_204 = 400f;
    private static float elec_year_204 = 8000f;
    private static float elec_all_204 = 8000f;
    private static float co2_cuts_204 = 7976f;
    //205
    private static float elec_day_205 = 10f;
    private static float elec_mon_205 = 300f;
    private static float elec_year_205 = 5000f;
    private static float elec_all_205 = 5000f;
    private static float co2_cuts_205 = 4985f;
    //201
    private static float elec_day_201 = 4f;
    private static float elec_all_201 = 6000f;
    private static float co2_cuts_201 = 5982f;
    //202
    private static float elec_day_202 = 3f;
    private static float elec_all_202 = 5000f;
    private static float co2_cuts_202 = 4985f;
    //203
    private static float elec_day_203 = 0f;
    private static float elec_all_203 = 5500f;
    private static float co2_cuts_203 = 5483.5f;
    //302
    private static float elec_day_302 = 10f;
    private static float elec_all_302 = 8000f;
    private static float co2_cuts_302 = 7976f;
    //401
    private static float elec_day_401 = 0f;
    private static float elec_mon_401 = 500f;
    private static float elec_year_401 = 6500f;
    private static float elec_all_401 = 6500f;
    private static float co2_cuts_401 = 6480.5f;
    //402
    private static float elec_day_402 = 20f;
    private static float elec_mon_402 = 600f;
    private static float elec_year_402 = 12000f;
    private static float elec_all_402 = 12000f;
    private static float co2_cuts_402 = 11964f;
    //501
    private static float elec_day_501 = 8f;
    private static float elec_mon_501 = 700f;
    private static float elec_year_501 = 7000f;
    private static float elec_all_501 = 7000f;
    private static float co2_cuts_501 = 6979f;
    public static void init(){
        map.put("elec_day_101", elec_day_101);
        map.put("elec_mon_101", elec_mon_101);
        map.put("elec_year_101", elec_year_101);
        map.put("elec_all_101", elec_all_101);
        map.put("co2_cuts_101", co2_cuts_101);

        map.put("elec_day_102", elec_day_102);
        map.put("elec_mon_102", elec_mon_102);
        map.put("elec_year_102", elec_year_102);
        map.put("elec_all_102", elec_all_102);
        map.put("co2_cuts_102", co2_cuts_102);

        map.put("elec_day_204", elec_day_204);
        map.put("elec_mon_204", elec_mon_204);
        map.put("elec_year_204", elec_year_204);
        map.put("elec_all_204", elec_all_204);
        map.put("co2_cuts_204", co2_cuts_204);

        map.put("elec_day_205", elec_day_205);
        map.put("elec_mon_205", elec_mon_205);
        map.put("elec_year_205", elec_year_205);
        map.put("elec_all_205", elec_all_205);
        map.put("co2_cuts_205", co2_cuts_205);

        map.put("elec_day_201", elec_day_201);
        map.put("elec_all_201", elec_all_201);
        map.put("co2_cuts_201", co2_cuts_201);

        map.put("elec_day_202", elec_day_202);
        map.put("elec_all_202", elec_all_202);
        map.put("co2_cuts_202", co2_cuts_202);

        map.put("elec_day_203", elec_day_203);
        map.put("elec_all_203", elec_all_203);
        map.put("co2_cuts_203", co2_cuts_203);

        map.put("elec_day_302", elec_day_302);
        map.put("elec_all_302", elec_all_302);
        map.put("co2_cuts_302", co2_cuts_302);

        map.put("elec_day_401", elec_day_401);
        map.put("elec_mon_401", elec_mon_401);
        map.put("elec_year_401", elec_year_401);
        map.put("elec_all_401", elec_all_401);
        map.put("co2_cuts_401", co2_cuts_401);

        map.put("elec_day_402", elec_day_402);
        map.put("elec_mon_402", elec_mon_402);
        map.put("elec_year_402", elec_year_402);
        map.put("elec_all_402", elec_all_402);
        map.put("co2_cuts_402", co2_cuts_402);

        map.put("elec_day_501", elec_day_501);
        map.put("elec_mon_501", elec_mon_501);
        map.put("elec_year_501", elec_year_501);
        map.put("elec_all_501", elec_all_501);
        map.put("co2_cuts_501", co2_cuts_501);
    }



    public static String getMinuatesData1(){
        init();
        StringBuffer sBuffer = new StringBuffer("68 EA 00 00 00 00 0D 1C 03 00 01 00");

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
        elec_all_101 = elec_year_101;
        log.info("NO101_ELEC_PROD_All：" + NO101_ELEC_PROD_All + "，elec_all_101：" + elec_all_101);
        sBuffer.append(" " + NO101_ELEC_PROD_All).append(" " + ChangeUtils.encode(elec_all_101) + "00");
        String NO101_Convert_Eff = "1C 40 00";
        float con_eff_101 = (float) Math.random()*8 + 90;
        log.info("NO101_Convert_Eff：" + NO101_Convert_Eff + "，con_eff_101：" + con_eff_101);
        sBuffer.append(" " + NO101_Convert_Eff).append(" " + ChangeUtils.encode(con_eff_101) + "00");
        String NO101_OUTPUT_P = "20 40 00";
        float output_p_101 = (float) (Math.random() + 4);
        log.info("NO101_OUTPUT_P：" + NO101_OUTPUT_P + "，output_p_101：" + output_p_101);
        sBuffer.append(" " + NO101_OUTPUT_P).append(" " + ChangeUtils.encode(output_p_101) + "00");
        String NO101_REACTIVE_P = "21 40 00";
        float reactive_p_101 = (float) (Math.random()*0.01);
        log.info("NO101_REACTIVE_P：" + NO101_REACTIVE_P + "，reactive_p_101：" + reactive_p_101);
        sBuffer.append(" " + NO101_REACTIVE_P).append(" " + ChangeUtils.encode(reactive_p_101) + "00");
        String NO101_CO2_CUTS = "04 40 00";
        co2_cuts_101 = (float) Math.random()*10 + map.get("co2_cuts_101");
        log.info("NO101_CO2_CUTS：" + NO101_CO2_CUTS + "，co2_cuts_101：" + co2_cuts_101);
        sBuffer.append(" " + NO101_CO2_CUTS).append(" " + ChangeUtils.encode(co2_cuts_101) + "00");
        String NO101_PV1_U = ChangeUtils.encodeInfomationAddress(16389);
        float pv1_u_101 = (float) (Math.random()*100 + 500);
        log.info("NO101_PV1_U：" + NO101_PV1_U + "，pv1_u_101：" + pv1_u_101);
        sBuffer.append(" " + NO101_PV1_U).append(ChangeUtils.encode(pv1_u_101) + "00");
        String NO101_PV1_I = ChangeUtils.encodeInfomationAddress(16390);
        float pv1_i_101 = (float) Math.random()*8;
        log.info("NO101_PV1_I：" + NO101_PV1_I + "，pv1_i_101：" + pv1_i_101);
        sBuffer.append(" " + NO101_PV1_I).append(ChangeUtils.encode(pv1_i_101) + "00");
        String NO101_AC_UA = ChangeUtils.encodeInfomationAddress(16404);
        float ac_ua_101 = (float) (Math.random()*100 + 200);
        log.info("NO101_AC_UA：" + NO101_AC_UA + "，ac_ua_101：" + ac_ua_101);
        sBuffer.append(" " + NO101_AC_UA).append(ChangeUtils.encode(ac_ua_101) + "00");
        String NO101_AC_UB = ChangeUtils.encodeInfomationAddress(16405);
        float ac_ub_101 = (float) (Math.random()*100 + 200);
        log.info("NO101_AC_UB：" + NO101_AC_UB + "，ac_ub_101：" + ac_ub_101);
        sBuffer.append(" " + NO101_AC_UB).append(ChangeUtils.encode(ac_ub_101) + "00");
        String NO101_AC_UC = ChangeUtils.encodeInfomationAddress(16406);
        float ac_uc_101 = (float) (Math.random()*100 + 200);
        log.info("NO101_AC_UC：" + NO101_AC_UC + "，ac_uc_101：" + ac_uc_101);
        sBuffer.append(" " + NO101_AC_UC).append(ChangeUtils.encode(ac_uc_101) + "00");
        String NO101_AC_IA = ChangeUtils.encodeInfomationAddress(16407);
        float ac_ia_101 = (float) (Math.random()*30 + 15);
        log.info("NO101_AC_IA：" + NO101_AC_IA + "，ac_ia_101：" + ac_ia_101);
        sBuffer.append(" " + NO101_AC_IA).append(ChangeUtils.encode(ac_ia_101) + "00");
        String NO101_AC_IB = ChangeUtils.encodeInfomationAddress(16408);
        float ac_ib_101 = (float) (Math.random()*30 + 15);
        log.info("NO101_AC_IB：" + NO101_AC_IB + "，ac_ib_101：" + ac_ib_101);
        sBuffer.append(" " + NO101_AC_IB).append(ChangeUtils.encode(ac_ib_101) + "00");
        String NO101_AC_IC = ChangeUtils.encodeInfomationAddress(16409);
        float ac_ic_101 = (float) (Math.random()*30 + 15);
        log.info("NO101_AC_IC：" + NO101_AC_IC + "，ac_ic_101：" + ac_ic_101);
        sBuffer.append(" " + NO101_AC_IC).append(ChangeUtils.encode(ac_ic_101) + "00");
        String NO101_MACHINE_TEMP = ChangeUtils.encodeInfomationAddress(16413);
        float machine_temp_101 = (float) (Math.random()*60 + 10);
        log.info("NO101_MACHINE_TEMP：" + NO101_MACHINE_TEMP + "，machine_temp_101：" + machine_temp_101);
        sBuffer.append(" " + NO101_MACHINE_TEMP).append(ChangeUtils.encode(machine_temp_101) + "00");
        String NO101_PEAK_POWER = ChangeUtils.encodeInfomationAddress(16415);
        float peak_power_101 = (float) (Math.random()*10 + 15);
        log.info("NO101_PEAK_POWER：" + NO101_PEAK_POWER + "，peak_power_101：" + peak_power_101);
        sBuffer.append(" " + NO101_PEAK_POWER).append(ChangeUtils.encode(peak_power_101) + "00");
        String NO101_CONNECT_P = ChangeUtils.encodeInfomationAddress(16418);
        float connect_p_101 = (float) (Math.random()*30);
        log.info("NO101_CONNECT_P：" + NO101_CONNECT_P + "，connect_p_101：" + connect_p_101);
        sBuffer.append(" " + NO101_CONNECT_P).append(ChangeUtils.encode(connect_p_101) + "00");

        return sBuffer.toString();
    }

    public static String getMinuatesData2(){
        init();
        StringBuffer sBuffer = new StringBuffer("68 A2 00 00 00 00 0D 13 03 00 01 00");

        //NO102
        String NO102_ELEC_PROD_DAY = "59 40 00";
        elec_day_102 = (float) Math.random()*10 + map.get("elec_day_102");
        log.info("NO102_ELEC_PROD_DAY：" + NO102_ELEC_PROD_DAY + "，elec_day_102：" + elec_day_102);
        sBuffer.append(" " + NO102_ELEC_PROD_DAY).append(" " + ChangeUtils.encode(elec_day_102) + "00");
        String NO102_ELEC_PROD_Month = "5A 40 00";
        elec_mon_102 = (float) Math.random()*10 + map.get("elec_mon_102");
        log.info("NO102_ELEC_PROD_Month：" + NO102_ELEC_PROD_Month + "，elec_mon_102：" + elec_mon_102);
        sBuffer.append(" " + NO102_ELEC_PROD_Month).append(" " + ChangeUtils.encode(elec_mon_102) + "00");
        String NO102_ELEC_PROD_Year = "5B 40 00";
        elec_year_102 = (float) Math.random()*10 + map.get("elec_year_102");
        log.info("NO102_ELEC_PROD_Year：" + NO102_ELEC_PROD_Year + "，elec_year_102：" + elec_year_102);
        sBuffer.append(" " + NO102_ELEC_PROD_Year).append(" " + ChangeUtils.encode(elec_year_102) + "00");
        String NO102_ELEC_PROD_All = "5C 40 00";
        elec_all_102 = elec_year_102;
        log.info("NO102_ELEC_PROD_All：" + NO102_ELEC_PROD_All + "，elec_all_102：" + elec_all_102);
        sBuffer.append(" " + NO102_ELEC_PROD_All).append(" " + ChangeUtils.encode(elec_all_102) + "00");
        String NO102_Convert_Eff = "50 40 00";
        float con_eff_102 = (float) Math.random()*8 + 90;
        log.info("NO102_Convert_Eff：" + NO102_Convert_Eff + "，con_eff_102：" + con_eff_102);
        sBuffer.append(" " + NO102_Convert_Eff).append(" " + ChangeUtils.encode(con_eff_102) + "00");
        String NO102_OUTPUT_P = "54 40 00";
        float output_p_102 = (float) (Math.random() + 4);
        log.info("NO102_OUTPUT_P：" + NO102_OUTPUT_P + "，output_p_102：" + output_p_102);
        sBuffer.append(" " + NO102_OUTPUT_P).append(" " + ChangeUtils.encode(output_p_102) + "00");
        String NO102_REACTIVE_P = "55 40 00";
        float reactive_p_102 = (float) (Math.random()*0.01);
        log.info("NO102_REACTIVE_P：" + NO102_REACTIVE_P + "，reactive_p_102：" + reactive_p_102);
        sBuffer.append(" " + NO102_REACTIVE_P).append(" " + ChangeUtils.encode(reactive_p_102) + "00");
        String NO102_CO2_CUTS = "38 40 00";
        co2_cuts_102 = (float) Math.random()*10 + map.get("co2_cuts_102");
        log.info("NO102_CO2_CUTS：" + NO102_CO2_CUTS + "，co2_cuts_102：" + co2_cuts_102);
        sBuffer.append(" " + NO102_CO2_CUTS).append(" " + ChangeUtils.encode(co2_cuts_102) + "00");
        String NO102_PV1_U = ChangeUtils.encodeInfomationAddress(16441);
        float pv1_u_102 = (float) (Math.random()*100 + 500);
        log.info("NO102_PV1_U：" + NO102_PV1_U + "，pv1_u_102：" + pv1_u_102);
        sBuffer.append(" " + NO102_PV1_U).append(ChangeUtils.encode(pv1_u_102) + "00");
        String NO102_PV1_I = ChangeUtils.encodeInfomationAddress(16442);
        float pv1_i_102 = (float) Math.random()*8;
        log.info("NO102_PV1_I：" + NO102_PV1_I + "，pv1_i_102：" + pv1_i_102);
        sBuffer.append(" " + NO102_PV1_I).append(ChangeUtils.encode(pv1_i_102) + "00");
        String NO102_AC_UA = ChangeUtils.encodeInfomationAddress(16456);
        float ac_ua_102 = (float) (Math.random()*100 + 200);
        log.info("NO102_AC_UA：" + NO102_AC_UA + "，ac_ua_102：" + ac_ua_102);
        sBuffer.append(" " + NO102_AC_UA).append(ChangeUtils.encode(ac_ua_102) + "00");
        String NO102_AC_UB = ChangeUtils.encodeInfomationAddress(16457);
        float ac_ub_102 = (float) (Math.random()*100 + 200);
        log.info("NO102_AC_UB：" + NO102_AC_UB + "，ac_ub_102：" + ac_ub_102);
        sBuffer.append(" " + NO102_AC_UB).append(ChangeUtils.encode(ac_ub_102) + "00");
        String NO102_AC_UC = ChangeUtils.encodeInfomationAddress(16458);
        float ac_uc_102 = (float) (Math.random()*100 + 200);
        log.info("NO102_AC_UC：" + NO102_AC_UC + "，ac_uc_102：" + ac_uc_102);
        sBuffer.append(" " + NO102_AC_UC).append(ChangeUtils.encode(ac_uc_102) + "00");
        String NO102_AC_IA = ChangeUtils.encodeInfomationAddress(16459);
        float ac_ia_102 = (float) (Math.random()*30 + 15);
        log.info("NO102_AC_IA：" + NO102_AC_IA + "，ac_ia_102：" + ac_ia_102);
        sBuffer.append(" " + NO102_AC_IA).append(ChangeUtils.encode(ac_ia_102) + "00");
        String NO102_AC_IB = ChangeUtils.encodeInfomationAddress(16460);
        float ac_ib_102 = (float) (Math.random()*30 + 15);
        log.info("NO102_AC_IB：" + NO102_AC_IB + "，ac_ib_102：" + ac_ib_102);
        sBuffer.append(" " + NO102_AC_IB).append(ChangeUtils.encode(ac_ib_102) + "00");
        String NO102_AC_IC = ChangeUtils.encodeInfomationAddress(16461);
        float ac_ic_102 = (float) (Math.random()*30 + 15);
        log.info("NO102_AC_IC：" + NO102_AC_IC + "，ac_ic_102：" + ac_ic_102);
        sBuffer.append(" " + NO102_AC_IC).append(ChangeUtils.encode(ac_ic_102) + "00");
        String NO102_MACHINE_TEMP = ChangeUtils.encodeInfomationAddress(16465);
        float machine_temp_102 = (float) (Math.random()*60 + 10);
        log.info("NO102_MACHINE_TEMP：" + NO102_MACHINE_TEMP + "，machine_temp_102：" + machine_temp_102);
        sBuffer.append(" " + NO102_MACHINE_TEMP).append(ChangeUtils.encode(machine_temp_102) + "00");
        String NO102_PEAK_POWER = ChangeUtils.encodeInfomationAddress(16467);
        float peak_power_102 = (float) (Math.random()*10 + 15);
        log.info("NO102_PEAK_POWER：" + NO102_PEAK_POWER + "，peak_power_102：" + peak_power_102);
        sBuffer.append(" " + NO102_PEAK_POWER).append(ChangeUtils.encode(peak_power_102) + "00");
        String NO102_CONNECT_P = ChangeUtils.encodeInfomationAddress(16470);
        float connect_p_102 = (float) (Math.random()*30);
        log.info("NO102_CONNECT_P：" + NO102_CONNECT_P + "，connect_p_102：" + connect_p_102);
        sBuffer.append(" " + NO102_CONNECT_P).append(ChangeUtils.encode(connect_p_102) + "00");

        return sBuffer.toString();
    }

    public static String getMinuatesData3() {
        init();
        StringBuffer sBuffer = new StringBuffer("68 A2 00 00 00 00 0D 13 03 00 01 00");

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
        elec_all_204 = elec_year_204;
        sBuffer.append(" " + NO204_ELEC_PROD_All).append(" " + ChangeUtils.encode(elec_all_204) + "00");
        String NO204_Convert_Eff = "8D 40 00";
        float con_eff_204 = (float) Math.random()*8 + 90;
        sBuffer.append(" " + NO204_Convert_Eff).append(" " + ChangeUtils.encode(con_eff_204) + "00");
        String NO204_OUTPUT_P = "91 40 00";
        float output_p_204 = (float) (Math.random() + 4);
        log.info("NO204_OUTPUT_P：" + NO204_OUTPUT_P + "，output_p_204：" + output_p_204);
        sBuffer.append(" " + NO204_OUTPUT_P).append(" " + ChangeUtils.encode(output_p_204) + "00");
        String NO204_REACTIVE_P = "92 40 00";
        float reactive_p_204 = (float) (Math.random()*0.01);
        log.info("NO204_REACTIVE_P：" + NO204_REACTIVE_P + "，reactive_p_204：" + reactive_p_204);
        sBuffer.append(" " + NO204_REACTIVE_P).append(" " + ChangeUtils.encode(reactive_p_204) + "00");
        String NO204_CO2_CUTS = "75 40 00";
        co2_cuts_204 = (float) Math.random()*10 + map.get("co2_cuts_204");
        log.info("NO204_CO2_CUTS：" + NO204_CO2_CUTS + "，co2_cuts_204：" + co2_cuts_204);
        sBuffer.append(" " + NO204_CO2_CUTS).append(" " + ChangeUtils.encode(co2_cuts_204) + "00");
        String NO204_PV1_U = ChangeUtils.encodeInfomationAddress(16502);
        float pv1_u_204 = (float) (Math.random()*100 + 500);
        log.info("NO204_PV1_U：" + NO204_PV1_U + "，pv1_u_204：" + pv1_u_204);
        sBuffer.append(" " + NO204_PV1_U).append(ChangeUtils.encode(pv1_u_204) + "00");
        String NO204_PV1_I = ChangeUtils.encodeInfomationAddress(16503);
        float pv1_i_204 = (float) Math.random()*8;
        log.info("NO204_PV1_I：" + NO204_PV1_I + "，pv1_i_204：" + pv1_i_204);
        sBuffer.append(" " + NO204_PV1_I).append(ChangeUtils.encode(pv1_i_204) + "00");
        String NO204_AC_UA = ChangeUtils.encodeInfomationAddress(16517);
        float ac_ua_204 = (float) (Math.random()*100 + 200);
        log.info("NO204_AC_UA：" + NO204_AC_UA + "，ac_ua_204：" + ac_ua_204);
        sBuffer.append(" " + NO204_AC_UA).append(ChangeUtils.encode(ac_ua_204) + "00");
        String NO204_AC_UB = ChangeUtils.encodeInfomationAddress(16518);
        float ac_ub_204 = (float) (Math.random()*100 + 200);
        log.info("NO204_AC_UB：" + NO204_AC_UB + "，ac_ub_204：" + ac_ub_204);
        sBuffer.append(" " + NO204_AC_UB).append(ChangeUtils.encode(ac_ub_204) + "00");
        String NO204_AC_UC = ChangeUtils.encodeInfomationAddress(16519);
        float ac_uc_204 = (float) (Math.random()*100 + 200);
        log.info("NO204_AC_UC：" + NO204_AC_UC + "，ac_uc_204：" + ac_uc_204);
        sBuffer.append(" " + NO204_AC_UC).append(ChangeUtils.encode(ac_uc_204) + "00");
        String NO204_AC_IA = ChangeUtils.encodeInfomationAddress(16520);
        float ac_ia_204 = (float) (Math.random()*30 + 15);
        log.info("NO204_AC_IA：" + NO204_AC_IA + "，ac_ia_204：" + ac_ia_204);
        sBuffer.append(" " + NO204_AC_IA).append(ChangeUtils.encode(ac_ia_204) + "00");
        String NO204_AC_IB = ChangeUtils.encodeInfomationAddress(16521);
        float ac_ib_204 = (float) (Math.random()*30 + 15);
        log.info("NO204_AC_IB：" + NO204_AC_IB + "，ac_ib_204：" + ac_ib_204);
        sBuffer.append(" " + NO204_AC_IB).append(ChangeUtils.encode(ac_ib_204) + "00");
        String NO204_AC_IC = ChangeUtils.encodeInfomationAddress(16522);
        float ac_ic_204 = (float) (Math.random()*30 + 15);
        log.info("NO204_AC_IC：" + NO204_AC_IC + "，ac_ic_204：" + ac_ic_204);
        sBuffer.append(" " + NO204_AC_IC).append(ChangeUtils.encode(ac_ic_204) + "00");
        String NO204_MACHINE_TEMP = ChangeUtils.encodeInfomationAddress(16526);
        float machine_temp_204 = (float) (Math.random()*60 + 10);
        log.info("NO204_MACHINE_TEMP：" + NO204_MACHINE_TEMP + "，machine_temp_204：" + machine_temp_204);
        sBuffer.append(" " + NO204_MACHINE_TEMP).append(ChangeUtils.encode(machine_temp_204) + "00");
        String NO204_PEAK_POWER = ChangeUtils.encodeInfomationAddress(16528);
        float peak_power_204 = (float) (Math.random()*10 + 10);
        log.info("NO204_PEAK_POWER：" + NO204_PEAK_POWER + "，peak_power_204：" + peak_power_204);
        sBuffer.append(" " + NO204_PEAK_POWER).append(ChangeUtils.encode(peak_power_204) + "00");
        String NO204_CONNECT_P = ChangeUtils.encodeInfomationAddress(16531);
        float connect_p_204 = (float) (Math.random()*17);
        log.info("NO204_CONNECT_P：" + NO204_CONNECT_P + "，connect_p_204：" + connect_p_204);
        sBuffer.append(" " + NO204_CONNECT_P).append(ChangeUtils.encode(connect_p_204) + "00");

        return sBuffer.toString();
    }

    public static String getMinuatesData4(){
        init();
        StringBuffer sBuffer = new StringBuffer("68 A2 00 00 00 00 0D 13 03 00 01 00");

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
        elec_all_205 = elec_year_205;
        sBuffer.append(" " + NO205_ELEC_PROD_All).append(" " + ChangeUtils.encode(elec_all_205) + "00");
        String NO205_Convert_Eff = "BD 40 00";
        float con_eff_205 = (float) Math.random()*8 + 90;
        sBuffer.append(" " + NO205_Convert_Eff).append(" " + ChangeUtils.encode(con_eff_205) + "00");
        String NO205_OUTPUT_P = "C1 40 00";
        float output_p_205 = (float) (Math.random() + 4);
        log.info("NO205_OUTPUT_P：" + NO205_OUTPUT_P + "，output_p_205：" + output_p_205);
        sBuffer.append(" " + NO205_OUTPUT_P).append(" " + ChangeUtils.encode(output_p_205) + "00");
        String NO205_REACTIVE_P = "C2 40 00";
        float reactive_p_205 = (float) (Math.random()*0.01);
        log.info("NO205_REACTIVE_P：" + NO205_REACTIVE_P + "，reactive_p_205：" + reactive_p_205);
        sBuffer.append(" " + NO205_REACTIVE_P).append(" " + ChangeUtils.encode(reactive_p_205) + "00");
        String NO205_CO2_CUTS = "A5 40 00";
        co2_cuts_205 = (float) Math.random()*10 + map.get("co2_cuts_205");
        log.info("NO205_CO2_CUTS：" + NO205_CO2_CUTS + "，co2_cuts_205：" + co2_cuts_205);
        sBuffer.append(" " + NO205_CO2_CUTS).append(" " + ChangeUtils.encode(co2_cuts_205) + "00");
        String NO205_PV1_U = ChangeUtils.encodeInfomationAddress(16550);
        float pv1_u_205 = (float) (Math.random()*100 + 500);
        log.info("NO205_PV1_U：" + NO205_PV1_U + "，pv1_u_205：" + pv1_u_205);
        sBuffer.append(" " + NO205_PV1_U).append(ChangeUtils.encode(pv1_u_205) + "00");
        String NO205_PV1_I = ChangeUtils.encodeInfomationAddress(16551);
        float pv1_i_205 = (float) Math.random()*8;
        log.info("NO205_PV1_I：" + NO205_PV1_I + "，pv1_i_205：" + pv1_i_205);
        sBuffer.append(" " + NO205_PV1_I).append(ChangeUtils.encode(pv1_i_205) + "00");
        String NO205_AC_UA = ChangeUtils.encodeInfomationAddress(16565);
        float ac_ua_205 = (float) (Math.random()*100 + 200);
        log.info("NO205_AC_UA：" + NO205_AC_UA + "，ac_ua_205：" + ac_ua_205);
        sBuffer.append(" " + NO205_AC_UA).append(ChangeUtils.encode(ac_ua_205) + "00");
        String NO205_AC_UB = ChangeUtils.encodeInfomationAddress(16566);
        float ac_ub_205 = (float) (Math.random()*100 + 200);
        log.info("NO205_AC_UB：" + NO205_AC_UB + "，ac_ub_205：" + ac_ub_205);
        sBuffer.append(" " + NO205_AC_UB).append(ChangeUtils.encode(ac_ub_205) + "00");
        String NO205_AC_UC = ChangeUtils.encodeInfomationAddress(16567);
        float ac_uc_205 = (float) (Math.random()*100 + 200);
        log.info("NO205_AC_UC：" + NO205_AC_UC + "，ac_uc_205：" + ac_uc_205);
        sBuffer.append(" " + NO205_AC_UC).append(ChangeUtils.encode(ac_uc_205) + "00");
        String NO205_AC_IA = ChangeUtils.encodeInfomationAddress(16568);
        float ac_ia_205 = (float) (Math.random()*30 + 15);
        log.info("NO205_AC_IA：" + NO205_AC_IA + "，ac_ia_205：" + ac_ia_205);
        sBuffer.append(" " + NO205_AC_IA).append(ChangeUtils.encode(ac_ia_205) + "00");
        String NO205_AC_IB = ChangeUtils.encodeInfomationAddress(16569);
        float ac_ib_205 = (float) (Math.random()*30 + 15);
        log.info("NO205_AC_IB：" + NO205_AC_IB + "，ac_ib_205：" + ac_ib_205);
        sBuffer.append(" " + NO205_AC_IB).append(ChangeUtils.encode(ac_ib_205) + "00");
        String NO205_AC_IC = ChangeUtils.encodeInfomationAddress(16570);
        float ac_ic_205 = (float) (Math.random()*30 + 15);
        log.info("NO205_AC_IC：" + NO205_AC_IC + "，ac_ic_205：" + ac_ic_205);
        sBuffer.append(" " + NO205_AC_IC).append(ChangeUtils.encode(ac_ic_205) + "00");
        String NO205_MACHINE_TEMP = ChangeUtils.encodeInfomationAddress(16574);
        float machine_temp_205 = (float) (Math.random()*60 + 10);
        log.info("NO205_MACHINE_TEMP：" + NO205_MACHINE_TEMP + "，machine_temp_205：" + machine_temp_205);
        sBuffer.append(" " + NO205_MACHINE_TEMP).append(ChangeUtils.encode(machine_temp_205) + "00");
        String NO205_PEAK_POWER = ChangeUtils.encodeInfomationAddress(16576);
        float peak_power_205 = (float) (Math.random()*23);
        log.info("NO205_PEAK_POWER：" + NO205_PEAK_POWER + "，peak_power_205：" + peak_power_205);
        sBuffer.append(" " + NO205_PEAK_POWER).append(ChangeUtils.encode(peak_power_205) + "00");
        String NO205_CONNECT_P = ChangeUtils.encodeInfomationAddress(16579);
        float connect_p_205 = (float) (Math.random()*22);
        log.info("NO205_CONNECT_P：" + NO205_CONNECT_P + "，connect_p_205：" + connect_p_205);
        sBuffer.append(" " + NO205_CONNECT_P).append(ChangeUtils.encode(connect_p_205) + "00");

        return sBuffer.toString();
    }


    public static String getMinuatesData5() {
        init();
        StringBuffer sBuffer = new StringBuffer("68 8A 00 00 00 00 0D 10 03 00 01 00");

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
        String NO201_OUTPUT_P = "DF 40 00";
        float output_p_201 = (float) (Math.random() + 2);
        log.info("NO201_OUTPUT_P：" + NO201_OUTPUT_P + "，output_p_201：" + output_p_201);
        sBuffer.append(" " + NO201_OUTPUT_P).append(" " + ChangeUtils.encode(output_p_201) + "00");
        String NO201_REACTIVE_P = "E5 40 00";
        float reactive_p_201 = (float) (Math.random()*0.01);
        log.info("NO201_REACTIVE_P：" + NO201_REACTIVE_P + "，reactive_p_201：" + reactive_p_201);
        sBuffer.append(" " + NO201_REACTIVE_P).append(" " + ChangeUtils.encode(reactive_p_201) + "00");
        String NO201_CO2_CUTS = "DE 40 00";
        co2_cuts_201 = (float) Math.random()*10 + map.get("co2_cuts_201");
        log.info("NO201_CO2_CUTS：" + NO201_CO2_CUTS + "，co2_cuts_201：" + co2_cuts_201);
        sBuffer.append(" " + NO201_CO2_CUTS).append(" " + ChangeUtils.encode(co2_cuts_201) + "00");
        String NO201_PV1_U = ChangeUtils.encodeInfomationAddress(16594);
        float pv1_u_201 = (float) (Math.random()*100 + 500);
        log.info("NO201_PV1_U：" + NO201_PV1_U + "，pv1_u_201：" + pv1_u_201);
        sBuffer.append(" " + NO201_PV1_U).append(ChangeUtils.encode(pv1_u_201) + "00");
        String NO201_PV1_I = ChangeUtils.encodeInfomationAddress(16600);
        float pv1_i_201 = (float) Math.random()*8;
        log.info("NO201_PV1_I：" + NO201_PV1_I + "，pv1_i_201：" + pv1_i_201);
        sBuffer.append(" " + NO201_PV1_I).append(ChangeUtils.encode(pv1_i_201) + "00");
        String NO201_AC_UA = ChangeUtils.encodeInfomationAddress(16620);
        float ac_ua_201 = (float) (Math.random()*100 + 200);
        log.info("NO201_AC_UA：" + NO201_AC_UA + "，ac_ua_201：" + ac_ua_201);
        sBuffer.append(" " + NO201_AC_UA).append(ChangeUtils.encode(ac_ua_201) + "00");
        String NO201_AC_UB = ChangeUtils.encodeInfomationAddress(16621);
        float ac_ub_201 = (float) (Math.random()*100 + 200);
        log.info("NO201_AC_UB：" + NO201_AC_UB + "，ac_ub_201：" + ac_ub_201);
        sBuffer.append(" " + NO201_AC_UB).append(ChangeUtils.encode(ac_ub_201) + "00");
        String NO201_AC_UC = ChangeUtils.encodeInfomationAddress(16622);
        float ac_uc_201 = (float) (Math.random()*100 + 200);
        log.info("NO201_AC_UC：" + NO201_AC_UC + "，ac_uc_201：" + ac_uc_201);
        sBuffer.append(" " + NO201_AC_UC).append(ChangeUtils.encode(ac_uc_201) + "00");
        String NO201_AC_IA = ChangeUtils.encodeInfomationAddress(16617);
        float ac_ia_201 = (float) (Math.random()*30 + 15);
        log.info("NO201_AC_IA：" + NO201_AC_IA + "，ac_ia_201：" + ac_ia_201);
        sBuffer.append(" " + NO201_AC_IA).append(ChangeUtils.encode(ac_ia_201) + "00");
        String NO201_AC_IB = ChangeUtils.encodeInfomationAddress(16618);
        float ac_ib_201 = (float) (Math.random()*30 + 15);
        log.info("NO201_AC_IB：" + NO201_AC_IB + "，ac_ib_201：" + ac_ib_201);
        sBuffer.append(" " + NO201_AC_IB).append(ChangeUtils.encode(ac_ib_201) + "00");
        String NO201_AC_IC = ChangeUtils.encodeInfomationAddress(16619);
        float ac_ic_201 = (float) (Math.random()*30 + 15);
        log.info("NO201_AC_IC：" + NO201_AC_IC + "，ac_ic_201：" + ac_ic_201);
        sBuffer.append(" " + NO201_AC_IC).append(ChangeUtils.encode(ac_ic_201) + "00");
        String NO201_MACHINE_TEMP = ChangeUtils.encodeInfomationAddress(16612);
        float machine_temp_201 = (float) (Math.random()*60 + 10);
        log.info("NO201_MACHINE_TEMP：" + NO201_MACHINE_TEMP + "，machine_temp_201：" + machine_temp_201);
        sBuffer.append(" " + NO201_MACHINE_TEMP).append(ChangeUtils.encode(machine_temp_201) + "00");
        String NO201_CONNECT_P = ChangeUtils.encodeInfomationAddress(16629);
        float connect_p_201 = (float) (Math.random()*14);
        log.info("NO201_CONNECT_P：" + NO201_CONNECT_P + "，connect_p_201：" + connect_p_201);
        sBuffer.append(" " + NO201_CONNECT_P).append(ChangeUtils.encode(connect_p_201) + "00");

        return sBuffer.toString();
    }

    public static String getMinuatesData6() {
        init();
        StringBuffer sBuffer = new StringBuffer("68 8A 00 00 00 00 0D 10 03 00 01 00");

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
        String NO202_OUTPUT_P = "09 41 00";
        float output_p_202 = (float) (Math.random() + 2);
        log.info("NO202_OUTPUT_P：" + NO202_OUTPUT_P + "，output_p_202：" + output_p_202);
        sBuffer.append(" " + NO202_OUTPUT_P).append(" " + ChangeUtils.encode(output_p_202) + "00");
        String NO202_REACTIVE_P = "0F 41 00";
        float reactive_p_202 = (float) (Math.random()*0.01);
        log.info("NO202_REACTIVE_P：" + NO202_REACTIVE_P + "，reactive_p_202：" + reactive_p_202);
        sBuffer.append(" " + NO202_REACTIVE_P).append(" " + ChangeUtils.encode(reactive_p_202) + "00");
        String NO202_CO2_CUTS = "08 41 00";
        co2_cuts_202 = (float) Math.random()*10 + map.get("co2_cuts_202");
        log.info("NO202_CO2_CUTS：" + NO202_CO2_CUTS + "，co2_cuts_202：" + co2_cuts_202);
        sBuffer.append(" " + NO202_CO2_CUTS).append(" " + ChangeUtils.encode(co2_cuts_202) + "00");
        String NO202_PV1_U = ChangeUtils.encodeInfomationAddress(16636);
        float pv1_u_202 = (float) (Math.random()*100 + 500);
        log.info("NO202_PV1_U：" + NO202_PV1_U + "，pv1_u_202：" + pv1_u_202);
        sBuffer.append(" " + NO202_PV1_U).append(ChangeUtils.encode(pv1_u_202) + "00");
        String NO202_PV1_I = ChangeUtils.encodeInfomationAddress(16642);
        float pv1_i_202 = (float) Math.random()*8;
        log.info("NO202_PV1_I：" + NO202_PV1_I + "，pv1_i_202：" + pv1_i_202);
        sBuffer.append(" " + NO202_PV1_I).append(ChangeUtils.encode(pv1_i_202) + "00");
        String NO202_AC_UA = ChangeUtils.encodeInfomationAddress(16662);
        float ac_ua_202 = (float) (Math.random()*100 + 200);
        log.info("NO202_AC_UA：" + NO202_AC_UA + "，ac_ua_202：" + ac_ua_202);
        sBuffer.append(" " + NO202_AC_UA).append(ChangeUtils.encode(ac_ua_202) + "00");
        String NO202_AC_UB = ChangeUtils.encodeInfomationAddress(16663);
        float ac_ub_202 = (float) (Math.random()*100 + 200);
        log.info("NO202_AC_UB：" + NO202_AC_UB + "，ac_ub_202：" + ac_ub_202);
        sBuffer.append(" " + NO202_AC_UB).append(ChangeUtils.encode(ac_ub_202) + "00");
        String NO202_AC_UC = ChangeUtils.encodeInfomationAddress(16664);
        float ac_uc_202 = (float) (Math.random()*100 + 200);
        log.info("NO202_AC_UC：" + NO202_AC_UC + "，ac_uc_202：" + ac_uc_202);
        sBuffer.append(" " + NO202_AC_UC).append(ChangeUtils.encode(ac_uc_202) + "00");
        String NO202_AC_IA = ChangeUtils.encodeInfomationAddress(16659);
        float ac_ia_202 = (float) (Math.random()*30 + 15);
        log.info("NO202_AC_IA：" + NO202_AC_IA + "，ac_ia_202：" + ac_ia_202);
        sBuffer.append(" " + NO202_AC_IA).append(ChangeUtils.encode(ac_ia_202) + "00");
        String NO202_AC_IB = ChangeUtils.encodeInfomationAddress(16660);
        float ac_ib_202 = (float) (Math.random()*30 + 15);
        log.info("NO202_AC_IB：" + NO202_AC_IB + "，ac_ib_202：" + ac_ib_202);
        sBuffer.append(" " + NO202_AC_IB).append(ChangeUtils.encode(ac_ib_202) + "00");
        String NO202_AC_IC = ChangeUtils.encodeInfomationAddress(16661);
        float ac_ic_202 = (float) (Math.random()*30 + 15);
        log.info("NO202_AC_IC：" + NO202_AC_IC + "，ac_ic_202：" + ac_ic_202);
        sBuffer.append(" " + NO202_AC_IC).append(ChangeUtils.encode(ac_ic_202) + "00");
        String NO202_MACHINE_TEMP = ChangeUtils.encodeInfomationAddress(16654);
        float machine_temp_202 = (float) (Math.random()*60 + 10);
        log.info("NO202_MACHINE_TEMP：" + NO202_MACHINE_TEMP + "，machine_temp_202：" + machine_temp_202);
        sBuffer.append(" " + NO202_MACHINE_TEMP).append(ChangeUtils.encode(machine_temp_202) + "00");
        String NO202_CONNECT_P = ChangeUtils.encodeInfomationAddress(16671);
        float connect_p_202 = (float) (Math.random()*13);
        log.info("NO202_CONNECT_P：" + NO202_CONNECT_P + "，connect_p_202：" + connect_p_202);
        sBuffer.append(" " + NO202_CONNECT_P).append(ChangeUtils.encode(connect_p_202) + "00");

        return sBuffer.toString();
    }

    public static String getMinuatesData7() {
        init();
        StringBuffer sBuffer = new StringBuffer("68 8A 00 00 00 00 0D 10 03 00 01 00");

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
        String NO203_OUTPUT_P = "33 41 00";
        float output_p_203 = (float) (Math.random() + 2);
        log.info("NO203_OUTPUT_P：" + NO203_OUTPUT_P + "，output_p_203：" + output_p_203);
        sBuffer.append(" " + NO203_OUTPUT_P).append(" " + ChangeUtils.encode(output_p_203) + "00");
        String NO203_REACTIVE_P = "39 41 00";
        float reactive_p_203 = (float) (Math.random()*0.01);
        log.info("NO203_REACTIVE_P：" + NO203_REACTIVE_P + "，reactive_p_203：" + reactive_p_203);
        sBuffer.append(" " + NO203_REACTIVE_P).append(" " + ChangeUtils.encode(reactive_p_203) + "00");
        String NO203_CO2_CUTS = "32 41 00";
        co2_cuts_203 = (float) Math.random()*10 + map.get("co2_cuts_203");
        log.info("NO203_CO2_CUTS：" + NO203_CO2_CUTS + "，co2_cuts_203：" + co2_cuts_203);
        sBuffer.append(" " + NO203_CO2_CUTS).append(" " + ChangeUtils.encode(co2_cuts_203) + "00");
        String NO203_PV1_U = ChangeUtils.encodeInfomationAddress(16678);
        float pv1_u_203 = (float) (Math.random()*100 + 500);
        log.info("NO203_PV1_U：" + NO203_PV1_U + "，pv1_u_203：" + pv1_u_203);
        sBuffer.append(" " + NO203_PV1_U).append(ChangeUtils.encode(pv1_u_203) + "00");
        String NO203_PV1_I = ChangeUtils.encodeInfomationAddress(16684);
        float pv1_i_203 = (float) Math.random()*8;
        log.info("NO203_PV1_I：" + NO203_PV1_I + "，pv1_i_203：" + pv1_i_203);
        sBuffer.append(" " + NO203_PV1_I).append(ChangeUtils.encode(pv1_i_203) + "00");
        String NO203_AC_UA = ChangeUtils.encodeInfomationAddress(16704);
        float ac_ua_203 = (float) (Math.random()*100 + 200);
        log.info("NO203_AC_UA：" + NO203_AC_UA + "，ac_ua_203：" + ac_ua_203);
        sBuffer.append(" " + NO203_AC_UA).append(ChangeUtils.encode(ac_ua_203) + "00");
        String NO203_AC_UB = ChangeUtils.encodeInfomationAddress(16705);
        float ac_ub_203 = (float) (Math.random()*100 + 200);
        log.info("NO203_AC_UB：" + NO203_AC_UB + "，ac_ub_203：" + ac_ub_203);
        sBuffer.append(" " + NO203_AC_UB).append(ChangeUtils.encode(ac_ub_203) + "00");
        String NO203_AC_UC = ChangeUtils.encodeInfomationAddress(16706);
        float ac_uc_203 = (float) (Math.random()*100 + 200);
        log.info("NO203_AC_UC：" + NO203_AC_UC + "，ac_uc_203：" + ac_uc_203);
        sBuffer.append(" " + NO203_AC_UC).append(ChangeUtils.encode(ac_uc_203) + "00");
        String NO203_AC_IA = ChangeUtils.encodeInfomationAddress(16701);
        float ac_ia_203 = (float) (Math.random()*30 + 15);
        log.info("NO203_AC_IA：" + NO203_AC_IA + "，ac_ia_203：" + ac_ia_203);
        sBuffer.append(" " + NO203_AC_IA).append(ChangeUtils.encode(ac_ia_203) + "00");
        String NO203_AC_IB = ChangeUtils.encodeInfomationAddress(16702);
        float ac_ib_203 = (float) (Math.random()*30 + 15);
        log.info("NO203_AC_IB：" + NO203_AC_IB + "，ac_ib_203：" + ac_ib_203);
        sBuffer.append(" " + NO203_AC_IB).append(ChangeUtils.encode(ac_ib_203) + "00");
        String NO203_AC_IC = ChangeUtils.encodeInfomationAddress(16703);
        float ac_ic_203 = (float) (Math.random()*30 + 15);
        log.info("NO203_AC_IC：" + NO203_AC_IC + "，ac_ic_203：" + ac_ic_203);
        sBuffer.append(" " + NO203_AC_IC).append(ChangeUtils.encode(ac_ic_203) + "00");
        String NO203_MACHINE_TEMP = ChangeUtils.encodeInfomationAddress(16696);
        float machine_temp_203 = (float) (Math.random()*60 + 10);
        log.info("NO203_MACHINE_TEMP：" + NO203_MACHINE_TEMP + "，machine_temp_203：" + machine_temp_203);
        sBuffer.append(" " + NO203_MACHINE_TEMP).append(ChangeUtils.encode(machine_temp_203) + "00");
        String NO203_CONNECT_P = ChangeUtils.encodeInfomationAddress(16713);
        float connect_p_203 = (float) (Math.random()*13);
        log.info("NO203_CONNECT_P：" + NO203_CONNECT_P + "，connect_p_203：" + connect_p_203);
        sBuffer.append(" " + NO203_CONNECT_P).append(ChangeUtils.encode(connect_p_203) + "00");

        return sBuffer.toString();
    }

    public static String getMinuatesData8() {
        init();
        StringBuffer sBuffer = new StringBuffer("68 8A 00 00 00 00 0D 10 03 00 01 00");

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
        String NO302_OUTPUT_P = "5D 41 00";
        float output_p_302 = (float) (Math.random() + 2);
        log.info("NO302_OUTPUT_P：" + NO302_OUTPUT_P + "，output_p_302：" + output_p_302);
        sBuffer.append(" " + NO302_OUTPUT_P).append(" " + ChangeUtils.encode(output_p_302) + "00");
        String NO302_REACTIVE_P = "63 41 00";
        float reactive_p_302 = (float) (Math.random()*0.01);
        log.info("NO302_REACTIVE_P：" + NO302_REACTIVE_P + "，reactive_p_302：" + reactive_p_302);
        sBuffer.append(" " + NO302_REACTIVE_P).append(" " + ChangeUtils.encode(reactive_p_302) + "00");
        String NO302_CO2_CUTS = "5C 41 00";
        co2_cuts_302 = (float) Math.random()*10 + map.get("co2_cuts_302");
        log.info("NO302_CO2_CUTS：" + NO302_CO2_CUTS + "，co2_cuts_302：" + co2_cuts_302);
        sBuffer.append(" " + NO302_CO2_CUTS).append(" " + ChangeUtils.encode(co2_cuts_302) + "00");
        String NO302_PV1_U = ChangeUtils.encodeInfomationAddress(16720);
        float pv1_u_302 = (float) (Math.random()*100 + 500);
        log.info("NO302_PV1_U：" + NO302_PV1_U + "，pv1_u_302：" + pv1_u_302);
        sBuffer.append(" " + NO302_PV1_U).append(ChangeUtils.encode(pv1_u_302) + "00");
        String NO302_PV1_I = ChangeUtils.encodeInfomationAddress(16726);
        float pv1_i_302 = (float) Math.random()*8;
        log.info("NO302_PV1_I：" + NO302_PV1_I + "，pv1_i_302：" + pv1_i_302);
        sBuffer.append(" " + NO302_PV1_I).append(ChangeUtils.encode(pv1_i_302) + "00");
        String NO302_AC_UA = ChangeUtils.encodeInfomationAddress(16746);
        float ac_ua_302 = (float) (Math.random()*100 + 200);
        log.info("NO302_AC_UA：" + NO302_AC_UA + "，ac_ua_302：" + ac_ua_302);
        sBuffer.append(" " + NO302_AC_UA).append(ChangeUtils.encode(ac_ua_302) + "00");
        String NO302_AC_UB = ChangeUtils.encodeInfomationAddress(16747);
        float ac_ub_302 = (float) (Math.random()*100 + 200);
        log.info("NO302_AC_UB：" + NO302_AC_UB + "，ac_ub_302：" + ac_ub_302);
        sBuffer.append(" " + NO302_AC_UB).append(ChangeUtils.encode(ac_ub_302) + "00");
        String NO302_AC_UC = ChangeUtils.encodeInfomationAddress(16748);
        float ac_uc_302 = (float) (Math.random()*100 + 200);
        log.info("NO302_AC_UC：" + NO302_AC_UC + "，ac_uc_302：" + ac_uc_302);
        sBuffer.append(" " + NO302_AC_UC).append(ChangeUtils.encode(ac_uc_302) + "00");
        String NO302_AC_IA = ChangeUtils.encodeInfomationAddress(16743);
        float ac_ia_302 = (float) (Math.random()*30 + 15);
        log.info("NO302_AC_IA：" + NO302_AC_IA + "，ac_ia_302：" + ac_ia_302);
        sBuffer.append(" " + NO302_AC_IA).append(ChangeUtils.encode(ac_ia_302) + "00");
        String NO302_AC_IB = ChangeUtils.encodeInfomationAddress(16744);
        float ac_ib_302 = (float) (Math.random()*30 + 15);
        log.info("NO302_AC_IB：" + NO302_AC_IB + "，ac_ib_302：" + ac_ib_302);
        sBuffer.append(" " + NO302_AC_IB).append(ChangeUtils.encode(ac_ib_302) + "00");
        String NO302_AC_IC = ChangeUtils.encodeInfomationAddress(16745);
        float ac_ic_302 = (float) (Math.random()*30 + 15);
        log.info("NO302_AC_IC：" + NO302_AC_IC + "，ac_ic_302：" + ac_ic_302);
        sBuffer.append(" " + NO302_AC_IC).append(ChangeUtils.encode(ac_ic_302) + "00");
        String NO302_MACHINE_TEMP = ChangeUtils.encodeInfomationAddress(16738);
        float machine_temp_302 = (float) (Math.random()*60 + 10);
        log.info("NO302_MACHINE_TEMP：" + NO302_MACHINE_TEMP + "，machine_temp_302：" + machine_temp_302);
        sBuffer.append(" " + NO302_MACHINE_TEMP).append(ChangeUtils.encode(machine_temp_302) + "00");
        String NO302_CONNECT_P = ChangeUtils.encodeInfomationAddress(16755);
        float connect_p_302 = (float) (Math.random()*13);
        log.info("NO302_CONNECT_P：" + NO302_CONNECT_P + "，connect_p_302：" + connect_p_302);
        sBuffer.append(" " + NO302_CONNECT_P).append(ChangeUtils.encode(connect_p_302) + "00");

        return sBuffer.toString();
    }

    public static String getMinuatesData9() {
        init();
        StringBuffer sBuffer = new StringBuffer("68 A2 00 00 00 00 0D 13 03 00 01 00");

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
        elec_all_401 = elec_year_401;
        sBuffer.append(" " + NO401_ELEC_PROD_All).append(" " + ChangeUtils.encode(elec_all_401) + "00");
        String NO401_Convert_Eff = "95 41 00";
        float con_eff_401 = (float) Math.random()*8 + 90;
        sBuffer.append(" " + NO401_Convert_Eff).append(" " + ChangeUtils.encode(con_eff_401) + "00");
        String NO401_OUTPUT_P = "99 41 00";
        float output_p_401 = (float) (Math.random() + 4);
        log.info("NO401_OUTPUT_P：" + NO401_OUTPUT_P + "，output_p_401：" + output_p_401);
        sBuffer.append(" " + NO401_OUTPUT_P).append(" " + ChangeUtils.encode(output_p_401) + "00");
        String NO401_REACTIVE_P = "9A 41 00";
        float reactive_p_401 = (float) (Math.random()*0.01);
        log.info("NO401_REACTIVE_P：" + NO401_REACTIVE_P + "，reactive_p_401：" + reactive_p_401);
        sBuffer.append(" " + NO401_REACTIVE_P).append(" " + ChangeUtils.encode(reactive_p_401) + "00");
        String NO401_CO2_CUTS = "7D 41 00";
        co2_cuts_401 = (float) Math.random()*10 + map.get("co2_cuts_401");
        log.info("NO401_CO2_CUTS：" + NO401_CO2_CUTS + "，co2_cuts_401：" + co2_cuts_401);
        sBuffer.append(" " + NO401_CO2_CUTS).append(" " + ChangeUtils.encode(co2_cuts_401) + "00");
        String NO401_PV1_U = ChangeUtils.encodeInfomationAddress(16766);
        float pv1_u_401 = (float) (Math.random()*100 + 500);
        log.info("NO401_PV1_U：" + NO401_PV1_U + "，pv1_u_401：" + pv1_u_401);
        sBuffer.append(" " + NO401_PV1_U).append(ChangeUtils.encode(pv1_u_401) + "00");
        String NO401_PV1_I = ChangeUtils.encodeInfomationAddress(16767);
        float pv1_i_401 = (float) Math.random()*8;
        log.info("NO401_PV1_I：" + NO401_PV1_I + "，pv1_i_401：" + pv1_i_401);
        sBuffer.append(" " + NO401_PV1_I).append(ChangeUtils.encode(pv1_i_401) + "00");
        String NO401_AC_UA = ChangeUtils.encodeInfomationAddress(16781);
        float ac_ua_401 = (float) (Math.random()*100 + 200);
        log.info("NO401_AC_UA：" + NO401_AC_UA + "，ac_ua_401：" + ac_ua_401);
        sBuffer.append(" " + NO401_AC_UA).append(ChangeUtils.encode(ac_ua_401) + "00");
        String NO401_AC_UB = ChangeUtils.encodeInfomationAddress(16782);
        float ac_ub_401 = (float) (Math.random()*100 + 200);
        log.info("NO401_AC_UB：" + NO401_AC_UB + "，ac_ub_401：" + ac_ub_401);
        sBuffer.append(" " + NO401_AC_UB).append(ChangeUtils.encode(ac_ub_401) + "00");
        String NO401_AC_UC = ChangeUtils.encodeInfomationAddress(16783);
        float ac_uc_401 = (float) (Math.random()*100 + 200);
        log.info("NO401_AC_UC：" + NO401_AC_UC + "，ac_uc_401：" + ac_uc_401);
        sBuffer.append(" " + NO401_AC_UC).append(ChangeUtils.encode(ac_uc_401) + "00");
        String NO401_AC_IA = ChangeUtils.encodeInfomationAddress(16784);
        float ac_ia_401 = (float) (Math.random()*30 + 15);
        log.info("NO401_AC_IA：" + NO401_AC_IA + "，ac_ia_401：" + ac_ia_401);
        sBuffer.append(" " + NO401_AC_IA).append(ChangeUtils.encode(ac_ia_401) + "00");
        String NO401_AC_IB = ChangeUtils.encodeInfomationAddress(16785);
        float ac_ib_401 = (float) (Math.random()*30 + 15);
        log.info("NO401_AC_IB：" + NO401_AC_IB + "，ac_ib_401：" + ac_ib_401);
        sBuffer.append(" " + NO401_AC_IB).append(ChangeUtils.encode(ac_ib_401) + "00");
        String NO401_AC_IC = ChangeUtils.encodeInfomationAddress(16786);
        float ac_ic_401 = (float) (Math.random()*30 + 15);
        log.info("NO401_AC_IC：" + NO401_AC_IC + "，ac_ic_401：" + ac_ic_401);
        sBuffer.append(" " + NO401_AC_IC).append(ChangeUtils.encode(ac_ic_401) + "00");
        String NO401_MACHINE_TEMP = ChangeUtils.encodeInfomationAddress(16790);
        float machine_temp_401 = (float) (Math.random()*60 + 10);
        log.info("NO401_MACHINE_TEMP：" + NO401_MACHINE_TEMP + "，machine_temp_401：" + machine_temp_401);
        sBuffer.append(" " + NO401_MACHINE_TEMP).append(ChangeUtils.encode(machine_temp_401) + "00");
        String NO401_PEAK_POWER = ChangeUtils.encodeInfomationAddress(16792);
        float peak_power_401 = (float) (Math.random()*10 + 15);
        log.info("NO401_PEAK_POWER：" + NO401_PEAK_POWER + "，peak_power_401：" + peak_power_401);
        sBuffer.append(" " + NO401_PEAK_POWER).append(ChangeUtils.encode(peak_power_401) + "00");
        String NO401_CONNECT_P = ChangeUtils.encodeInfomationAddress(16795);
        float connect_p_401 = (float) (Math.random()*30);
        log.info("NO401_CONNECT_P：" + NO401_CONNECT_P + "，connect_p_401：" + connect_p_401);
        sBuffer.append(" " + NO401_CONNECT_P).append(ChangeUtils.encode(connect_p_401) + "00");

        return sBuffer.toString();
    }

    public static String getMinuatesData10() {
        init();
        StringBuffer sBuffer = new StringBuffer("68 A2 00 00 00 00 0D 13 03 00 01 00");

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
        elec_all_402 = elec_year_402;
        sBuffer.append(" " + NO402_ELEC_PROD_All).append(" " + ChangeUtils.encode(elec_all_402) + "00");
        String NO402_Convert_Eff = "C5 41 00";
        float con_eff_402 = (float) Math.random()*8 + 90;
        sBuffer.append(" " + NO402_Convert_Eff).append(" " + ChangeUtils.encode(con_eff_402) + "00");
        String NO402_OUTPUT_P = "C9 41 00";
        float output_p_402 = (float) (Math.random() + 4);
        log.info("NO402_OUTPUT_P：" + NO402_OUTPUT_P + "，output_p_402：" + output_p_402);
        sBuffer.append(" " + NO402_OUTPUT_P).append(" " + ChangeUtils.encode(output_p_402) + "00");
        String NO402_REACTIVE_P = "CA 41 00";
        float reactive_p_402 = (float) (Math.random()*0.01);
        log.info("NO402_REACTIVE_P：" + NO402_REACTIVE_P + "，reactive_p_402：" + reactive_p_402);
        sBuffer.append(" " + NO402_REACTIVE_P).append(" " + ChangeUtils.encode(reactive_p_402) + "00");
        String NO402_CO2_CUTS = "AD 41 00";
        co2_cuts_402 = (float) Math.random()*10 + map.get("co2_cuts_402");
        log.info("NO402_CO2_CUTS：" + NO402_CO2_CUTS + "，co2_cuts_402：" + co2_cuts_402);
        sBuffer.append(" " + NO402_CO2_CUTS).append(" " + ChangeUtils.encode(co2_cuts_402) + "00");
        String NO402_PV1_U = ChangeUtils.encodeInfomationAddress(16814);
        float pv1_u_402 = (float) (Math.random()*100 + 500);
        log.info("NO402_PV1_U：" + NO402_PV1_U + "，pv1_u_402：" + pv1_u_402);
        sBuffer.append(" " + NO402_PV1_U).append(ChangeUtils.encode(pv1_u_402) + "00");
        String NO402_PV1_I = ChangeUtils.encodeInfomationAddress(16815);
        float pv1_i_402 = (float) Math.random()*8;
        log.info("NO402_PV1_I：" + NO402_PV1_I + "，pv1_i_402：" + pv1_i_402);
        sBuffer.append(" " + NO402_PV1_I).append(ChangeUtils.encode(pv1_i_402) + "00");
        String NO402_AC_UA = ChangeUtils.encodeInfomationAddress(16829);
        float ac_ua_402 = (float) (Math.random()*100 + 200);
        log.info("NO402_AC_UA：" + NO402_AC_UA + "，ac_ua_402：" + ac_ua_402);
        sBuffer.append(" " + NO402_AC_UA).append(ChangeUtils.encode(ac_ua_402) + "00");
        String NO402_AC_UB = ChangeUtils.encodeInfomationAddress(16830);
        float ac_ub_402 = (float) (Math.random()*100 + 200);
        log.info("NO402_AC_UB：" + NO402_AC_UB + "，ac_ub_402：" + ac_ub_402);
        sBuffer.append(" " + NO402_AC_UB).append(ChangeUtils.encode(ac_ub_402) + "00");
        String NO402_AC_UC = ChangeUtils.encodeInfomationAddress(16831);
        float ac_uc_402 = (float) (Math.random()*100 + 200);
        log.info("NO402_AC_UC：" + NO402_AC_UC + "，ac_uc_402：" + ac_uc_402);
        sBuffer.append(" " + NO402_AC_UC).append(ChangeUtils.encode(ac_uc_402) + "00");
        String NO402_AC_IA = ChangeUtils.encodeInfomationAddress(16832);
        float ac_ia_402 = (float) (Math.random()*30 + 15);
        log.info("NO402_AC_IA：" + NO402_AC_IA + "，ac_ia_402：" + ac_ia_402);
        sBuffer.append(" " + NO402_AC_IA).append(ChangeUtils.encode(ac_ia_402) + "00");
        String NO402_AC_IB = ChangeUtils.encodeInfomationAddress(16833);
        float ac_ib_402 = (float) (Math.random()*30 + 15);
        log.info("NO402_AC_IB：" + NO402_AC_IB + "，ac_ib_402：" + ac_ib_402);
        sBuffer.append(" " + NO402_AC_IB).append(ChangeUtils.encode(ac_ib_402) + "00");
        String NO402_AC_IC = ChangeUtils.encodeInfomationAddress(16834);
        float ac_ic_402 = (float) (Math.random()*30 + 15);
        log.info("NO402_AC_IC：" + NO402_AC_IC + "，ac_ic_402：" + ac_ic_402);
        sBuffer.append(" " + NO402_AC_IC).append(ChangeUtils.encode(ac_ic_402) + "00");
        String NO402_MACHINE_TEMP = ChangeUtils.encodeInfomationAddress(16838);
        float machine_temp_402 = (float) (Math.random()*60 + 10);
        log.info("NO402_MACHINE_TEMP：" + NO402_MACHINE_TEMP + "，machine_temp_402：" + machine_temp_402);
        sBuffer.append(" " + NO402_MACHINE_TEMP).append(ChangeUtils.encode(machine_temp_402) + "00");
        String NO402_PEAK_POWER = ChangeUtils.encodeInfomationAddress(16840);
        float peak_power_402 = (float) (Math.random()*10 + 15);
        log.info("NO402_PEAK_POWER：" + NO402_PEAK_POWER + "，peak_power_402：" + peak_power_402);
        sBuffer.append(" " + NO402_PEAK_POWER).append(ChangeUtils.encode(peak_power_402) + "00");
        String NO402_CONNECT_P = ChangeUtils.encodeInfomationAddress(16843);
        float connect_p_402 = (float) (Math.random()*30);
        log.info("NO402_CONNECT_P：" + NO402_CONNECT_P + "，connect_p_402：" + connect_p_402);
        sBuffer.append(" " + NO402_CONNECT_P).append(ChangeUtils.encode(connect_p_402) + "00");

        return sBuffer.toString();
    }

    public static String getMinuatesData11(){
        init();
        StringBuffer sBuffer = new StringBuffer("68 A2 00 00 00 00 0D 13 03 00 01 00");

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
        elec_all_501 = elec_year_501;
        sBuffer.append(" " + NO501_ELEC_PROD_All).append(" " + ChangeUtils.encode(elec_all_501) + "00");
        String NO501_Convert_Eff = "F5 41 00";
        float con_eff_501 = (float) Math.random()*8 + 90;
        sBuffer.append(" " + NO501_Convert_Eff).append(" " + ChangeUtils.encode(con_eff_501) + "00");
        String NO501_OUTPUT_P = "F9 41 00";
        float output_p_501 = (float) (Math.random() + 4);
        log.info("NO501_OUTPUT_P：" + NO501_OUTPUT_P + "，output_p_501：" + output_p_501);
        sBuffer.append(" " + NO501_OUTPUT_P).append(" " + ChangeUtils.encode(output_p_501) + "00");
        String NO501_REACTIVE_P = "FA 41 00";
        float reactive_p_501 = (float) (Math.random()*0.01);
        log.info("NO501_REACTIVE_P：" + NO501_REACTIVE_P + "，reactive_p_501：" + reactive_p_501);
        sBuffer.append(" " + NO501_REACTIVE_P).append(" " + ChangeUtils.encode(reactive_p_501) + "00");
        String NO501_CO2_CUTS = "DD 41 00";
        co2_cuts_501 = (float) Math.random()*10 + map.get("co2_cuts_501");
        log.info("NO501_CO2_CUTS：" + NO501_CO2_CUTS + "，co2_cuts_501：" + co2_cuts_501);
        sBuffer.append(" " + NO501_CO2_CUTS).append(" " + ChangeUtils.encode(co2_cuts_501) + "00");
        String NO501_PV1_U = ChangeUtils.encodeInfomationAddress(16862);
        float pv1_u_501 = (float) (Math.random()*100 + 500);
        log.info("NO501_PV1_U：" + NO501_PV1_U + "，pv1_u_501：" + pv1_u_501);
        sBuffer.append(" " + NO501_PV1_U).append(ChangeUtils.encode(pv1_u_501) + "00");
        String NO501_PV1_I = ChangeUtils.encodeInfomationAddress(16863);
        float pv1_i_501 = (float) Math.random()*8;
        log.info("NO501_PV1_I：" + NO501_PV1_I + "，pv1_i_501：" + pv1_i_501);
        sBuffer.append(" " + NO501_PV1_I).append(ChangeUtils.encode(pv1_i_501) + "00");
        String NO501_AC_UA = ChangeUtils.encodeInfomationAddress(16877);
        float ac_ua_501 = (float) (Math.random()*100 + 200);
        log.info("NO501_AC_UA：" + NO501_AC_UA + "，ac_ua_501：" + ac_ua_501);
        sBuffer.append(" " + NO501_AC_UA).append(ChangeUtils.encode(ac_ua_501) + "00");
        String NO501_AC_UB = ChangeUtils.encodeInfomationAddress(16878);
        float ac_ub_501 = (float) (Math.random()*100 + 200);
        log.info("NO501_AC_UB：" + NO501_AC_UB + "，ac_ub_501：" + ac_ub_501);
        sBuffer.append(" " + NO501_AC_UB).append(ChangeUtils.encode(ac_ub_501) + "00");
        String NO501_AC_UC = ChangeUtils.encodeInfomationAddress(16879);
        float ac_uc_501 = (float) (Math.random()*100 + 200);
        log.info("NO501_AC_UC：" + NO501_AC_UC + "，ac_uc_501：" + ac_uc_501);
        sBuffer.append(" " + NO501_AC_UC).append(ChangeUtils.encode(ac_uc_501) + "00");
        String NO501_AC_IA = ChangeUtils.encodeInfomationAddress(16880);
        float ac_ia_501 = (float) (Math.random()*30 + 15);
        log.info("NO501_AC_IA：" + NO501_AC_IA + "，ac_ia_501：" + ac_ia_501);
        sBuffer.append(" " + NO501_AC_IA).append(ChangeUtils.encode(ac_ia_501) + "00");
        String NO501_AC_IB = ChangeUtils.encodeInfomationAddress(16881);
        float ac_ib_501 = (float) (Math.random()*30 + 15);
        log.info("NO501_AC_IB：" + NO501_AC_IB + "，ac_ib_501：" + ac_ib_501);
        sBuffer.append(" " + NO501_AC_IB).append(ChangeUtils.encode(ac_ib_501) + "00");
        String NO501_AC_IC = ChangeUtils.encodeInfomationAddress(16882);
        float ac_ic_501 = (float) (Math.random()*30 + 15);
        log.info("NO501_AC_IC：" + NO501_AC_IC + "，ac_ic_501：" + ac_ic_501);
        sBuffer.append(" " + NO501_AC_IC).append(ChangeUtils.encode(ac_ic_501) + "00");
        String NO501_MACHINE_TEMP = ChangeUtils.encodeInfomationAddress(16886);
        float machine_temp_501 = (float) (Math.random()*60 + 10);
        log.info("NO501_MACHINE_TEMP：" + NO501_MACHINE_TEMP + "，machine_temp_501：" + machine_temp_501);
        sBuffer.append(" " + NO501_MACHINE_TEMP).append(ChangeUtils.encode(machine_temp_501) + "00");
        String NO501_PEAK_POWER = ChangeUtils.encodeInfomationAddress(16888);
        float peak_power_501 = (float) (Math.random()*10 + 15);
        log.info("NO501_PEAK_POWER：" + NO501_PEAK_POWER + "，peak_power_501：" + peak_power_501);
        sBuffer.append(" " + NO501_PEAK_POWER).append(ChangeUtils.encode(peak_power_501) + "00");
        String NO501_CONNECT_P = ChangeUtils.encodeInfomationAddress(16891);
        float connect_p_501 = (float) (Math.random()*30);
        log.info("NO501_CONNECT_P：" + NO501_CONNECT_P + "，connect_p_501：" + connect_p_501);
        sBuffer.append(" " + NO501_CONNECT_P).append(ChangeUtils.encode(connect_p_501) + "00");

        return sBuffer.toString();
    }

    public static void main(String[] args) {
        System.out.println(getMinuatesData1());
    }
}
