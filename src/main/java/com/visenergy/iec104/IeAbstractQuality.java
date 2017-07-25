package com.visenergy.iec104;

import java.io.DataInputStream;
import java.io.IOException;

/**
 * 品质描述
 */
abstract class IeAbstractQuality extends InformationElement {

    protected int value;

    public IeAbstractQuality(boolean blocked, boolean substituted, boolean notTopical, boolean invalid) {

        value = 0;

        if (blocked) {
            value |= 0x10;
        }
        if (substituted) {
            value |= 0x20;
        }
        if (notTopical) {
            value |= 0x40;
        }
        if (invalid) {
            value |= 0x80;
        }

    }

    public IeAbstractQuality(DataInputStream is) throws IOException {
        value = (is.readByte() & 0xff);
    }

    @Override
    public int encode(byte[] buffer, int i) {
        buffer[i] = (byte) value;
        return 1;
    }

    public boolean isBlocked() {
        return (value & 0x10) == 0x10;
    }

    public boolean isSubstituted() {
        return (value & 0x20) == 0x20;
    }

    public boolean isNotTopical() {
        return (value & 0x40) == 0x40;
    }

    public boolean isInvalid() {
        return (value & 0x80) == 0x80;
    }

    @Override
    public String toString() {
        return "被封锁: " + isBlocked() + ", 被取代: " + isSubstituted() + ", 非当前值: " + isNotTopical()
                + ", 是否有效: " + isInvalid();
    }
}
