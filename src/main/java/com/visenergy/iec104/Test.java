package com.visenergy.iec104;

import com.visenergy.iec104.util.ChangeUtils;
import com.visenergy.iec104.util.FileUtils;
import net.sf.json.JSONObject;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.util.Properties;

/**
 * Created by Fuxdong on 2017-5-8.
 */
public class Test {
    private static Log log = LogFactory.getLog(Test.class);

    public static void main(String[] args) throws Exception{
//        String text = new String("68 08 02 00 00 00 0A C0 01 00 00 00");

//        String text = new String("68 7A FC 13 7C 00 0D 0E 03 00 01 00 3A 41 00 85 EB 47 42 00 3B 41 00 D7 A6 01 46 00 3C 41 00 14 AE B5 41 00 3D 41 00 EB 51 E0 40 00 3E 41 00 C2 F5 E0 40 00 3F 41 00 AE 47 E1 40 00 40 41 00 5C 4F 6E 43 00 41 41 00 C2 35 6C 43 00 42 41 00 D7 63 6B 43 00 45 41 00 00 00 C5 42 00 46 41 00 48 E1 DA 3F 00 47 41 00 B1 72 D8 3F 00 48 41 00 CB A1 D5 3F 00 49 41 00 96 43 A3 40 00");
       /* String text = new String("68 E2 20 00 00 00 0D 1B 03 00 01 00 46 40 00 9A 99 CD 43 00 47 40 00 33 F3 CC 43 00 4A 40 00 33 B3 6E 43 00 4E 40 00 3D 0A 48 42 00 4F 40 00 3A B4 CC C0 00 54 40 00 E8 FB B5 41 00 56 40 00 69 91 B8 41 00 58 40 00 66 66 12 41 00 59 40 00 C2 75 44 43 00 5A 40 00 52 90 13 45 00 5B 40 00 D7 AA C5 46 00 5C 40 00 D7 AA C5 46 00 70 40 00 33 33 B3 3F 00 5D 40 00 33 33 16 44 00 5E 40 00 67 66 B6 40 00 5F 40 00 33 33 16 44 00 60 40 00 33 33 B3 40 00 04 40 00 C2 8B C3 46 00 09 40 00 CD EC 16 44 00 0A 40 00 33 33 B3 40 00 0B 40 00 CD EC 16 44 00 0C 40 00 CD CC AC 40 00 0D 40 00 CD CC 16 44 00 0E 40 00 00 00 B0 40 00 0F 40 00 CD CC 16 44 00 10 40 00 00 00 B0 40 00 11 40 00 CD 4C CE 43 00");
        String[] s = text.trim().split(" ");
        byte[] bytes = new byte[255];
        for (int i=0;i<s.length;i++){
            int a = Integer.parseInt(s[i],16);
            if(a>127){
                bytes[i] = (byte) a;
            }else {
                bytes[i]= Byte.parseByte(s[i],16);
            }
        }
        DataInputStream dis = new DataInputStream(new ByteArrayInputStream(bytes));
        Apdu apdu = new Apdu(dis);
*/
       IeShortFloat isf = new IeShortFloat(557.5f);
        byte[] b = new byte[4];
        isf.encode(b,0);
        System.out.println(String.format("%02d",ChangeUtils.toHexString(b)));
    }
}
