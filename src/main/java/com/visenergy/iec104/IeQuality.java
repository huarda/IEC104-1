package com.visenergy.iec104;

import java.io.DataInputStream;
import java.io.IOException;

/**
 * 品质描述
 * 
 */
public class IeQuality extends IeAbstractQuality {

    public IeQuality(boolean overflow, boolean blocked, boolean substituted, boolean notTopical, boolean invalid) {
        super(blocked, substituted, notTopical, invalid);

        if (overflow) {
            value |= 0x01;
        }
    }

    IeQuality(DataInputStream is) throws IOException {
        super(is);
    }

    public boolean isOverflow() {
        return (value & 0x01) == 0x01;
    }

    @Override
    public String toString() {
        return "品质描述, 溢出: " + isOverflow() + ", " + super.toString();
    }
}
