package com.visenergy.iec104;

import java.io.DataInputStream;
import java.io.IOException;

/**
 * 单点信息
 * 
 */
public class IeSinglePointWithQuality extends IeAbstractQuality {

    public IeSinglePointWithQuality(boolean on, boolean blocked, boolean substituted, boolean notTopical,
            boolean invalid) {
        super(blocked, substituted, notTopical, invalid);

        if (on) {
            value |= 0x01;
        }
    }

    public IeSinglePointWithQuality(DataInputStream is) throws IOException {
        super(is);
    }

    public boolean isOn() {
        return (value & 0x01) == 0x01;
    }

    @Override
    public String toString() {
        return "单点, 是否开闸: " + isOn() + ", " + super.toString();
    }
}
