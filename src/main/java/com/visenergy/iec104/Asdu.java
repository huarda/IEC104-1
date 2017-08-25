package com.visenergy.iec104;


import com.visenergy.iec104.util.ChangeUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.io.DataInputStream;
import java.io.IOException;

/**
 * Created by Fuxdong on 2017-5-10.
 *
 */
public class Asdu {
    private Log log = LogFactory.getLog(Apdu.class);

    private final int typeId;
    private final boolean isSequenceOfElements;
    private int vsq;
    private final int addressNum;
    private final int causeOfTransmission;
    private boolean test;
    private boolean negativeConfirm;
    private int originatorAddress;
    private final int commonAddress;
    private InformationObject[] informationObjects;
    private byte[] privateInformation;

    public Asdu(int typeId, boolean isSequenceOfElements, int causeOfTransmission, boolean test,
                boolean negativeConfirm, int originatorAddress, int commonAddress, InformationObject[] informationObjects) {

        this.typeId = typeId;
        this.isSequenceOfElements = isSequenceOfElements;
        this.causeOfTransmission = causeOfTransmission;
        this.test = test;
        this.negativeConfirm = negativeConfirm;

        this.commonAddress = commonAddress;
        this.originatorAddress = originatorAddress;
        this.informationObjects = informationObjects;
        this.privateInformation = null;

        if (isSequenceOfElements) {
            this.addressNum = informationObjects[0].getInformationElements().length;
        } else {
            this.addressNum = informationObjects.length;
        }
    }

    public Asdu(DataInputStream dataInputStream) throws Exception{
        //获取类型表示配置文件
        this.typeId = dataInputStream.readByte() & 0xff;
        if (Init.typeIdProp.getProperty(String.valueOf(typeId))== null || "".equals(Init.typeIdProp.getProperty(String.valueOf(typeId)))){
            log.error(new IOException("无效的类型标识："+typeId));
        }else{
            log.debug("类型标识：" + Init.typeIdProp.getProperty(String.valueOf(typeId)));
        }

        int vsqNum = dataInputStream.readByte() & 0xff;
        String vsqFormat = String.format("%08d",Integer.parseInt(Integer.toBinaryString(vsqNum)));
        //可变结构限定词，转为二进制后获取第8位
        vsq = Integer.parseInt(vsqFormat.substring(0,1));
        //可变结构限定词，获取第1-7位，代表信息数据数目
        addressNum = Integer.parseInt(vsqFormat.substring(1,8),2);
        if (vsq == 1) {
            isSequenceOfElements = true;
            log.debug("信息体地址连续：" +isSequenceOfElements+"，信息数据条数：" + addressNum);
        } else {
            isSequenceOfElements = false;
            log.debug("信息体地址连续：" +isSequenceOfElements+"，信息数据条数：" + addressNum);
        }
        int numberOfSequenceElements;
        int numberOfInformationObjects;
        //根据是否连续来确定信息对象数目、信息元素数目
        if (isSequenceOfElements) {
            numberOfSequenceElements = addressNum;
            numberOfInformationObjects = 1;
        }else {
            numberOfInformationObjects = addressNum;
            numberOfSequenceElements = 1;
        }
        byte[] cot = new byte[2];
        dataInputStream.readFully(cot);
        //传送原因
        causeOfTransmission = Integer.parseInt(ChangeUtils.byteAppend(cot),10);
        log.debug("传送原因：" + Init.causeProp.getProperty(String.valueOf(causeOfTransmission)));
        //公共地址
        byte[] commAddress = new byte[2];
        dataInputStream.readFully(commAddress);
        commonAddress = Integer.parseInt(ChangeUtils.byteAppend(commAddress));
        log.debug("公共地址：" + commonAddress);

        //信息体
        if (typeId < 128) {

            informationObjects = new InformationObject[numberOfInformationObjects];

            for (int i = 0; i < numberOfInformationObjects; i++) {
                informationObjects[i] = new InformationObject(dataInputStream, typeId, numberOfSequenceElements);
            }

            privateInformation = null;
        }else{
            log.debug("");
        }

    }

    public int getTypeId() {
        return typeId;
    }

    public boolean isSequenceOfElements() {
        return isSequenceOfElements;
    }

    public int getSequenceLength() {
        return addressNum;
    }

    public int getCauseOfTransmission() {
        return causeOfTransmission;
    }

    public boolean isTestFrame() {
        return test;
    }

    public boolean isNegativeConfirm() {
        return negativeConfirm;
    }

    public Integer getOriginatorAddress() {
        return originatorAddress;
    }

    public int getCommonAddress() {
        return commonAddress;
    }

    public InformationObject[] getInformationObjects() {
        return informationObjects;
    }

    public byte[] getPrivateInformation() {
        return privateInformation;
    }

    int encode(byte[] buffer, int i) {

        int origi = i;

        buffer[i++] = (byte) typeId;
        if (isSequenceOfElements) {
            buffer[i++] = (byte) (addressNum | 0x80);
        }else {
            buffer[i++] = (byte) addressNum;
        }

        if (test) {
            if (negativeConfirm) {
                buffer[i++] = (byte) (causeOfTransmission | 0xC0);
            }else {
                buffer[i++] = (byte) (causeOfTransmission | 0x80);
            }
        }else {
            if (negativeConfirm) {
                buffer[i++] = (byte) (causeOfTransmission | 0x40);
            }else {
                buffer[i++] = (byte) causeOfTransmission;
            }
        }

        buffer[i++] = (byte) originatorAddress;

        buffer[i++] = (byte) commonAddress;

        buffer[i++] = (byte) (commonAddress >> 8);

        if (informationObjects != null) {
            for (InformationObject informationObject : informationObjects) {
                i += informationObject.encode(buffer, i);
            }
        }else {
            System.arraycopy(privateInformation, 0, buffer, i, privateInformation.length);
            i += privateInformation.length;
        }
        return i - origi;
    }

    @Override
    public String toString() {

        StringBuilder builder = new StringBuilder();
        if (informationObjects != null) {
            for (InformationObject informationObject : informationObjects) {
                builder.append(informationObject.toString());
                builder.append("\n");
            }
        }else {
            builder.append("\nPrivate Information:\n");
            int l = 1;
            for (byte b : privateInformation) {
                if ((l != 1) && ((l - 1) % 8 == 0)) {
                    builder.append(' ');
                }
                if ((l != 1) && ((l - 1) % 16 == 0)) {
                    builder.append('\n');
                }
                l++;
                builder.append("0x");
                String hexString = Integer.toHexString(b & 0xff);
                if (hexString.length() == 1) {
                    builder.append(0);
                }
                builder.append(hexString + " ");
            }
        }

        return builder.toString();

    }
}
