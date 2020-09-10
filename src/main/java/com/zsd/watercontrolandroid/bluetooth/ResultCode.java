package com.zsd.watercontrolandroid.bluetooth;

/**
 * @author thomas
 * @version 1.0
 * @class ResultCode
 * @description TODO
 * @date 2020/9/6 9:05 上午
 */
public class ResultCode {

    private byte result;

    public ResultCode(byte paramByte) { this.result = paramByte; }

    public int bit0() {
        byte b1 = this.result;
        byte b = 1;
        if ((byte)(b1 & 0x01) == 0)
            b = 0;
        return b;
    }

    public int bit1() { return ((byte)(this.result & 0x2) == 0) ? 0 : 1; }

    public int bit2() { return ((byte)(this.result & 0x4) == 0) ? 0 : 1; }

    public int bit3() { return ((byte)(this.result & 0x8) == 0) ? 0 : 1; }

    public int bit4() { return ((byte)(this.result & 0x10) == 0) ? 0 : 1; }

    public int bit5() { return ((byte)(this.result & 0x20) == 0) ? 0 : 1; }

    public int bit6() { return ((byte)(this.result & 0x40) == 0) ? 0 : 1; }

    public int bit7() { return ((byte)(this.result & 0x80) == 0) ? 0 : 1; }

    public byte getResult() { return this.result; }

    public void setResult(byte paramByte) { this.result = paramByte; }

}
