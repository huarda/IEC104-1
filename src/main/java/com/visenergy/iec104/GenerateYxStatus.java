package com.visenergy.iec104;

import com.visenergy.iec104.util.ChangeUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Created by Administrator on 2017/8/30.
 */
public class GenerateYxStatus {
    private static Log log = LogFactory.getLog(GenerateDate.class);

    public static String getMinuatesData() {
        StringBuffer sBuffer = new StringBuffer("68 F2 00 00 00 00 0D 1D 03 00 01 00");

        //NO101
        String NO101_CONNECT_STATUS = ChangeUtils.encodeInfomationAddress(16433);
        float connect_status_101 = 1f;
        log.info("NO101_CONNECT_STATUS：" + NO101_CONNECT_STATUS + "，connect_status_101：" + connect_status_101);
        sBuffer.append(" " + NO101_CONNECT_STATUS).append(ChangeUtils.encode(connect_status_101) + "00");
        String NO101_PV_CONNECT_STATUS = ChangeUtils.encodeInfomationAddress(16429);
        float pv_connect_status_101 = 1f;
        log.info("NO101_PV_CONNECT_STATUS：" + NO101_PV_CONNECT_STATUS + "，pv_connect_status_101：" + pv_connect_status_101);
        sBuffer.append(" " + NO101_PV_CONNECT_STATUS).append(ChangeUtils.encode(pv_connect_status_101) + "00");
        String NO101_WARNING_STATUS = ChangeUtils.encodeInfomationAddress(16414);
        float warning_status_101 = 1f;
        log.info("NO101_WARNING_STATUS：" + NO101_WARNING_STATUS + "，warning_status_101：" + warning_status_101);
        sBuffer.append(" " + NO101_WARNING_STATUS).append(ChangeUtils.encode(warning_status_101) + "00");

        //NO102
        String NO102_CONNECT_STATUS = ChangeUtils.encodeInfomationAddress(16485);
        float connect_status_102 = 1f;
        log.info("NO102_CONNECT_STATUS：" + NO102_CONNECT_STATUS + "，connect_status_102：" + connect_status_102);
        sBuffer.append(" " + NO102_CONNECT_STATUS).append(ChangeUtils.encode(connect_status_102) + "00");
        String NO102_PV_CONNECT_STATUS = ChangeUtils.encodeInfomationAddress(16481);
        float pv_connect_status_102 = 1f;
        log.info("NO102_PV_CONNECT_STATUS：" + NO102_PV_CONNECT_STATUS + "，pv_connect_status_102：" + pv_connect_status_102);
        sBuffer.append(" " + NO102_PV_CONNECT_STATUS).append(ChangeUtils.encode(pv_connect_status_102) + "00");
        String NO102_WARNING_STATUS = ChangeUtils.encodeInfomationAddress(16466);
        float warning_status_102 = 1f;
        log.info("NO102_WARNING_STATUS：" + NO102_WARNING_STATUS + "，warning_status_102：" + warning_status_102);
        sBuffer.append(" " + NO102_WARNING_STATUS).append(ChangeUtils.encode(warning_status_102) + "00");

        //204
        String NO204_CONNECT_STATUS = ChangeUtils.encodeInfomationAddress(16542);
        float connect_status_204 = 1f;
        log.info("NO204_CONNECT_STATUS：" + NO204_CONNECT_STATUS + "，connect_status_204：" + connect_status_204);
        sBuffer.append(" " + NO204_CONNECT_STATUS).append(ChangeUtils.encode(connect_status_204) + "00");
        String NO204_PV_CONNECT_STATUS = ChangeUtils.encodeInfomationAddress(16538);
        float pv_connect_status_204 = 1f;
        log.info("NO204_PV_CONNECT_STATUS：" + NO204_PV_CONNECT_STATUS + "，pv_connect_status_204：" + pv_connect_status_204);
        sBuffer.append(" " + NO204_PV_CONNECT_STATUS).append(ChangeUtils.encode(pv_connect_status_204) + "00");
        String NO204_WARNING_STATUS = ChangeUtils.encodeInfomationAddress(16527);
        float warning_status_204 = 1f;
        log.info("NO204_WARNING_STATUS：" + NO204_WARNING_STATUS + "，warning_status_204：" + warning_status_204);
        sBuffer.append(" " + NO204_WARNING_STATUS).append(ChangeUtils.encode(warning_status_204) + "00");

        //205
        String NO205_CONNECT_STATUS = ChangeUtils.encodeInfomationAddress(16590);
        float connect_status_205 = 1f;
        log.info("NO205_CONNECT_STATUS：" + NO205_CONNECT_STATUS + "，connect_status_205：" + connect_status_205);
        sBuffer.append(" " + NO205_CONNECT_STATUS).append(ChangeUtils.encode(connect_status_205) + "00");
        String NO205_PV_CONNECT_STATUS = ChangeUtils.encodeInfomationAddress(16586);
        float pv_connect_status_205 = 1f;
        log.info("NO205_PV_CONNECT_STATUS：" + NO205_PV_CONNECT_STATUS + "，pv_connect_status_205：" + pv_connect_status_205);
        sBuffer.append(" " + NO205_PV_CONNECT_STATUS).append(ChangeUtils.encode(pv_connect_status_205) + "00");
        String NO205_WARNING_STATUS = ChangeUtils.encodeInfomationAddress(16575);
        float warning_status_205 = 1f;
        log.info("NO205_WARNING_STATUS：" + NO205_WARNING_STATUS + "，warning_status_205：" + warning_status_205);
        sBuffer.append(" " + NO205_WARNING_STATUS).append(ChangeUtils.encode(warning_status_205) + "00");

        //201
        String NO201_PV_CONNECT_STATUS = ChangeUtils.encodeInfomationAddress(16633);
        float pv_connect_status_201 = 1f;
        log.info("NO201_PV_CONNECT_STATUS：" + NO201_PV_CONNECT_STATUS + "，pv_connect_status_201：" + pv_connect_status_201);
        sBuffer.append(" " + NO201_PV_CONNECT_STATUS).append(ChangeUtils.encode(pv_connect_status_201) + "00");
        String NO201_WARNING_STATUS = ChangeUtils.encodeInfomationAddress(16635);
        float warning_status_201 = 1f;
        log.info("NO201_WARNING_STATUS：" + NO201_WARNING_STATUS + "，warning_status_201：" + warning_status_201);
        sBuffer.append(" " + NO201_WARNING_STATUS).append(ChangeUtils.encode(warning_status_201) + "00");

        //NO202
        String NO202_PV_CONNECT_STATUS = ChangeUtils.encodeInfomationAddress(16675);
        float pv_connect_status_202 = 1f;
        log.info("NO202_PV_CONNECT_STATUS：" + NO202_PV_CONNECT_STATUS + "，pv_connect_status_202：" + pv_connect_status_202);
        sBuffer.append(" " + NO202_PV_CONNECT_STATUS).append(ChangeUtils.encode(pv_connect_status_202) + "00");
        String NO202_WARNING_STATUS = ChangeUtils.encodeInfomationAddress(16677);
        float warning_status_202 = 1f;
        log.info("NO202_WARNING_STATUS：" + NO202_WARNING_STATUS + "，warning_status_202：" + warning_status_202);
        sBuffer.append(" " + NO202_WARNING_STATUS).append(ChangeUtils.encode(warning_status_202) + "00");

        //203
        String NO203_PV_CONNECT_STATUS = ChangeUtils.encodeInfomationAddress(16717);
        float pv_connect_status_203 = 1f;
        log.info("NO203_PV_CONNECT_STATUS：" + NO203_PV_CONNECT_STATUS + "，pv_connect_status_203：" + pv_connect_status_203);
        sBuffer.append(" " + NO203_PV_CONNECT_STATUS).append(ChangeUtils.encode(pv_connect_status_203) + "00");
        String NO203_WARNING_STATUS = ChangeUtils.encodeInfomationAddress(16719);
        float warning_status_203 = 1f;
        log.info("NO203_WARNING_STATUS：" + NO203_WARNING_STATUS + "，warning_status_203：" + warning_status_203);
        sBuffer.append(" " + NO203_WARNING_STATUS).append(ChangeUtils.encode(warning_status_203) + "00");

        //302
        String NO302_PV_CONNECT_STATUS = ChangeUtils.encodeInfomationAddress(16759);
        float pv_connect_status_302 = 1f;
        log.info("NO302_PV_CONNECT_STATUS：" + NO302_PV_CONNECT_STATUS + "，pv_connect_status_302：" + pv_connect_status_302);
        sBuffer.append(" " + NO302_PV_CONNECT_STATUS).append(ChangeUtils.encode(pv_connect_status_302) + "00");
        String NO302_WARNING_STATUS = ChangeUtils.encodeInfomationAddress(16761);
        float warning_status_302 = 1f;
        log.info("NO302_WARNING_STATUS：" + NO302_WARNING_STATUS + "，warning_status_302：" + warning_status_302);
        sBuffer.append(" " + NO302_WARNING_STATUS).append(ChangeUtils.encode(warning_status_302) + "00");

        //401
        String NO401_CONNECT_STATUS = ChangeUtils.encodeInfomationAddress(16806);
        float connect_status_401 = 1f;
        log.info("NO401_CONNECT_STATUS：" + NO401_CONNECT_STATUS + "，connect_status_401：" + connect_status_401);
        sBuffer.append(" " + NO401_CONNECT_STATUS).append(ChangeUtils.encode(connect_status_401) + "00");
        String NO401_PV_CONNECT_STATUS = ChangeUtils.encodeInfomationAddress(16802);
        float pv_connect_status_401 = 1f;
        log.info("NO401_PV_CONNECT_STATUS：" + NO401_PV_CONNECT_STATUS + "，pv_connect_status_401：" + pv_connect_status_401);
        sBuffer.append(" " + NO401_PV_CONNECT_STATUS).append(ChangeUtils.encode(pv_connect_status_401) + "00");
        String NO401_WARNING_STATUS = ChangeUtils.encodeInfomationAddress(16791);
        float warning_status_401 = 1f;
        log.info("NO401_WARNING_STATUS：" + NO401_WARNING_STATUS + "，warning_status_401：" + warning_status_401);
        sBuffer.append(" " + NO401_WARNING_STATUS).append(ChangeUtils.encode(warning_status_401) + "00");

        //402
        String NO402_CONNECT_STATUS = ChangeUtils.encodeInfomationAddress(16854);
        float connect_status_402 = 1f;
        log.info("NO402_CONNECT_STATUS：" + NO402_CONNECT_STATUS + "，connect_status_402：" + connect_status_402);
        sBuffer.append(" " + NO402_CONNECT_STATUS).append(ChangeUtils.encode(connect_status_402) + "00");
        String NO402_PV_CONNECT_STATUS = ChangeUtils.encodeInfomationAddress(16850);
        float pv_connect_status_402 = 1f;
        log.info("NO402_PV_CONNECT_STATUS：" + NO402_PV_CONNECT_STATUS + "，pv_connect_status_402：" + pv_connect_status_402);
        sBuffer.append(" " + NO402_PV_CONNECT_STATUS).append(ChangeUtils.encode(pv_connect_status_402) + "00");
        String NO402_WARNING_STATUS = ChangeUtils.encodeInfomationAddress(16839);
        float warning_status_402 = 1f;
        log.info("NO402_WARNING_STATUS：" + NO402_WARNING_STATUS + "，warning_status_402：" + warning_status_402);
        sBuffer.append(" " + NO402_WARNING_STATUS).append(ChangeUtils.encode(warning_status_402) + "00");

        //501
        String NO501_CONNECT_STATUS = ChangeUtils.encodeInfomationAddress(16906);
        float connect_status_501 = 1f;
        log.info("NO501_CONNECT_STATUS：" + NO501_CONNECT_STATUS + "，connect_status_501：" + connect_status_501);
        sBuffer.append(" " + NO501_CONNECT_STATUS).append(ChangeUtils.encode(connect_status_501) + "00");
        String NO501_PV_CONNECT_STATUS = ChangeUtils.encodeInfomationAddress(16902);
        float pv_connect_status_501 = 1f;
        log.info("NO501_PV_CONNECT_STATUS：" + NO501_PV_CONNECT_STATUS + "，pv_connect_status_501：" + pv_connect_status_501);
        sBuffer.append(" " + NO501_PV_CONNECT_STATUS).append(ChangeUtils.encode(pv_connect_status_501) + "00");
        String NO501_WARNING_STATUS = ChangeUtils.encodeInfomationAddress(16887);
        float warning_status_501 = 1f;
        log.info("NO501_WARNING_STATUS：" + NO501_WARNING_STATUS + "，warning_status_501：" + warning_status_501);
        sBuffer.append(" " + NO501_WARNING_STATUS).append(ChangeUtils.encode(warning_status_501) + "00");


        return sBuffer.toString();
    }

    public static String getMinuatesData2() {
        StringBuffer sBuffer = new StringBuffer("68 1E 00 00 00 00 01 05 03 00 01 00");

        String NO101_COMMUNICATE_STATUS = ChangeUtils.encodeInfomationAddress(352);
        float communicate_status_101 = 1f;
        log.info("NO101_COMMUNICATE_STATUS：" + NO101_COMMUNICATE_STATUS + "，communicate_status_101：" + communicate_status_101);
        sBuffer.append(" " + NO101_COMMUNICATE_STATUS).append("01");

        String NO204_COMMUNICATE_STATUS = ChangeUtils.encodeInfomationAddress(353);
        float communicate_status_204 = 1f;
        log.info("NO204_COMMUNICATE_STATUS：" + NO204_COMMUNICATE_STATUS + "，communicate_status_204：" + communicate_status_204);
        sBuffer.append(" " + NO204_COMMUNICATE_STATUS).append("01");

        String NO302_COMMUNICATE_STATUS = ChangeUtils.encodeInfomationAddress(354);
        float communicate_status_302 = 1f;
        log.info("NO302_COMMUNICATE_STATUS：" + NO302_COMMUNICATE_STATUS + "，communicate_status_302：" + communicate_status_302);
        sBuffer.append(" " + NO302_COMMUNICATE_STATUS).append("01");
        String NO401_COMMUNICATE_STATUS = ChangeUtils.encodeInfomationAddress(355);
        float communicate_status_401 = 1f;
        log.info("NO401_COMMUNICATE_STATUS：" + NO401_COMMUNICATE_STATUS + "，communicate_status_401：" + communicate_status_401);
        sBuffer.append(" " + NO401_COMMUNICATE_STATUS).append("01");

        String NO501_COMMUNICATE_STATUS = ChangeUtils.encodeInfomationAddress(356);
        float communicate_status_501 = 1f;
        log.info("NO501_COMMUNICATE_STATUS：" + NO501_COMMUNICATE_STATUS + "，communicate_status_501：" + communicate_status_501);
        sBuffer.append(" " + NO501_COMMUNICATE_STATUS).append("01");

        return sBuffer.toString();
    }

    public static void main(String[] args){
        String s1 = getMinuatesData();
        String s2 = getMinuatesData2();
        System.out.println(s1);
        System.out.println(s2);
    }
}
