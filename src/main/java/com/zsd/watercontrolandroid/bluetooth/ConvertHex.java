package com.zsd.watercontrolandroid.bluetooth;

import lombok.extern.slf4j.Slf4j;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author thomas
 */
@Slf4j
public class ConvertHex {
    static byte[] crc16_tab_h;
    static byte[] crc16_tab_l;
    static {
        System.load("/Users/thomas/Desktop/旺达水宝/WaterAndroid/app/libs/x86_64/libBleEncrypt.so");
        crc16_tab_h = new byte[] {
                0, -63, -127, 64, 1, -64, Byte.MIN_VALUE, 65, 1, -64,
                Byte.MIN_VALUE, 65, 0, -63, -127, 64, 1, -64, Byte.MIN_VALUE, 65,
                0, -63, -127, 64, 0, -63, -127, 64, 1, -64,
                Byte.MIN_VALUE, 65, 1, -64, Byte.MIN_VALUE, 65, 0, -63, -127, 64,
                0, -63, -127, 64, 1, -64, Byte.MIN_VALUE, 65, 0, -63,
                -127, 64, 1, -64, Byte.MIN_VALUE, 65, 1, -64, Byte.MIN_VALUE, 65,
                0, -63, -127, 64, 1, -64, Byte.MIN_VALUE, 65, 0, -63,
                -127, 64, 0, -63, -127, 64, 1, -64, Byte.MIN_VALUE, 65,
                0, -63, -127, 64, 1, -64, Byte.MIN_VALUE, 65, 1, -64,
                Byte.MIN_VALUE, 65, 0, -63, -127, 64, 0, -63, -127, 64,
                1, -64, Byte.MIN_VALUE, 65, 1, -64, Byte.MIN_VALUE, 65, 0, -63,
                -127, 64, 1, -64, Byte.MIN_VALUE, 65, 0, -63, -127, 64,
                0, -63, -127, 64, 1, -64, Byte.MIN_VALUE, 65, 1, -64,
                Byte.MIN_VALUE, 65, 0, -63, -127, 64, 0, -63, -127, 64,
                1, -64, Byte.MIN_VALUE, 65, 0, -63, -127, 64, 1, -64,
                Byte.MIN_VALUE, 65, 1, -64, Byte.MIN_VALUE, 65, 0, -63, -127, 64,
                0, -63, -127, 64, 1, -64, Byte.MIN_VALUE, 65, 1, -64,
                Byte.MIN_VALUE, 65, 0, -63, -127, 64, 1, -64, Byte.MIN_VALUE, 65,
                0, -63, -127, 64, 0, -63, -127, 64, 1, -64,
                Byte.MIN_VALUE, 65, 0, -63, -127, 64, 1, -64, Byte.MIN_VALUE, 65,
                1, -64, Byte.MIN_VALUE, 65, 0, -63, -127, 64, 1, -64,
                Byte.MIN_VALUE, 65, 0, -63, -127, 64, 0, -63, -127, 64,
                1, -64, Byte.MIN_VALUE, 65, 1, -64, Byte.MIN_VALUE, 65, 0, -63,
                -127, 64, 0, -63, -127, 64, 1, -64, Byte.MIN_VALUE, 65,
                0, -63, -127, 64, 1, -64, Byte.MIN_VALUE, 65, 1, -64,
                Byte.MIN_VALUE, 65, 0, -63, -127, 64 };
        crc16_tab_l = new byte[] {
                0, -64, -63, 1, -61, 3, 2, -62, -58, 6,
                7, -57, 5, -59, -60, 4, -52, 12, 13, -51,
                15, -49, -50, 14, 10, -54, -53, 11, -55, 9,
                8, -56, -40, 24, 25, -39, 27, -37, -38, 26,
                30, -34, -33, 31, -35, 29, 28, -36, 20, -44,
                -43, 21, -41, 23, 22, -42, -46, 18, 19, -45,
                17, -47, -48, 16, -16, 48, 49, -15, 51, -13,
                -14, 50, 54, -10, -9, 55, -11, 53, 52, -12,
                60, -4, -3, 61, -1, 63, 62, -2, -6, 58,
                59, -5, 57, -7, -8, 56, 40, -24, -23, 41,
                -21, 43, 42, -22, -18, 46, 47, -17, 45, -19,
                -20, 44, -28, 36, 37, -27, 39, -25, -26, 38,
                34, -30, -29, 35, -31, 33, 32, -32, -96, 96,
                97, -95, 99, -93, -94, 98, 102, -90, -89, 103,
                -91, 101, 100, -92, 108, -84, -83, 109, -81, 111,
                110, -82, -86, 106, 107, -85, 105, -87, -88, 104,
                120, -72, -71, 121, -69, 123, 122, -70, -66, 126,
                Byte.MAX_VALUE, -65, 125, -67, -68, 124, -76, 116, 117, -75,
                119, -73, -74, 118, 114, -78, -77, 115, -79, 113,
                112, -80, 80, -112, -111, 81, -109, 83, 82, -110,
                -106, 86, 87, -105, 85, -107, -108, 84, -100, 92,
                93, -99, 95, -97, -98, 94, 90, -102, -101, 91,
                -103, 89, 88, -104, -120, 72, 73, -119, 75, -117,
                -118, 74, 78, -114, -113, 79, -115, 77, 76, -116,
                68, -124, -123, 69, -121, 71, 70, -122, -126, 66,
                67, -125, 65, -127, Byte.MIN_VALUE, 64 };
    }

    public static native byte[] dataEncrypt(byte[] paramArrayOfByte1, byte[] paramArrayOfByte2, byte[] paramArrayOfByte3);

    public static String addHeadAndFoot(String paramString, short paramShort, byte[] paramArrayOfByte) {
        boolean bool;
        if (paramArrayOfByte != null) {
            bool = true;
        } else {
            bool = false;
        }
        // 0200
        String str = bytesToHexString(shortToByte(paramShort));
        StringBuilder stringBuilder2 = new StringBuilder();
        // 0200
        stringBuilder2.append(str);
        // 10EF
        stringBuilder2.append(getCmdLengthData(paramString));
        // 020010EF
        str = stringBuilder2.toString();
        stringBuilder2 = new StringBuilder();
        // 020010EF
        stringBuilder2.append(str);
        stringBuilder2.append(paramString);
        paramString = stringBuilder2.toString();
        log.info( "addHeadAndFoot: " + paramString);
        byte[] arrayOfByte = hexStringToBytes(paramString);//
        short s = (short)calcCrc16(arrayOfByte, 0, arrayOfByte.length, (short)-1);
        paramShort = s;
        if (bool) {
            paramShort = (short) calcCrc16(paramArrayOfByte, 0, paramArrayOfByte.length, s);
        }
        StringBuilder stringBuilder1 = new StringBuilder();
        stringBuilder1.append(paramString);
        stringBuilder1.append(bytesToHexString(shortToByte(paramShort)));
        paramString = stringBuilder1.toString();
        stringBuilder1 = new StringBuilder();
        stringBuilder1.append("A5");
        stringBuilder1.append(paramString);
        stringBuilder1.append("A6");
        paramString = stringBuilder1.toString();
        if (!bool) {
            return paramString;
        }
        stringBuilder1 = new StringBuilder();
        stringBuilder1.append(">>>发送命令原文：");
        stringBuilder1.append(paramString);
        log.info("addHeadAndFoot: ", stringBuilder1.toString());
        return bytesToHexString(dataEncrypt(hexStringToBytes(paramString), paramArrayOfByte));
    }

    public static int calcCrc16(byte[] paramArrayOfByte) { return calcCrc16(paramArrayOfByte, 0, paramArrayOfByte.length); }

    public static int calcCrc16(byte[] paramArrayOfByte, int paramInt1, int paramInt2) { return calcCrc16(paramArrayOfByte, paramInt1, paramInt2, (short)-1); }

    public static int calcCrc16(byte[] paramArrayOfByte, int paramInt1, int paramInt2, short paramShort) {
        // 0000000011111111
        int k = (0xFF00 & paramShort) >> '\b';
        // 0000000011111111
        int c = (char)(paramShort & 0xFF);
        int j = 0;
        int i = k;
        while (true) {
            k = i;
            if (j < paramInt2) {
                i = (c ^ paramArrayOfByte[paramInt1 + j]) & 0xFF;
                c = crc16_tab_h[i];
                i = crc16_tab_l[i];
                j++;
                c = k ^ c;
                continue;
            }
            break;
        }
        return (k & 0xFF) << 8 | c & 0xFF & 0xFFFF;
    }

    private static String getCmdLengthData(String paramString) {
        byte[] arrayOfByte = shortToByte((short)(paramString.length() / 2));
        // 00010000^11111111 = 1110 1111
        arrayOfByte[1] = (byte)(arrayOfByte[0] ^ 0xFFFFFFFF);
        return bytesToHexString(arrayOfByte);
    }

    /* 获取蓝牙地址的函数 注释掉
    public String getLocalAddress() {
        return BluetoothAdapter.getDefaultAdapter().getAddress().replaceAll(":","");
    }
    */


    public static String timestampToString(long paramLong) { return (new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")).format(new Date(paramLong)); }

    public static byte[] hexStringToBytes(String paramString) {
        int i = paramString.length() / 2;
        byte[] arrayOfByte2 = new byte[i];
        byte[] arrayOfByte1 = paramString.getBytes();
        for (int b = 0; b < i; b++) {
            int b1 = b * 2;
            arrayOfByte2[b] = uniteBytes(arrayOfByte1[b1], arrayOfByte1[b1 + 1]);
        }
        return arrayOfByte2;
    }

    private static byte uniteBytes(byte paramByte1, byte paramByte2) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("0x");
        stringBuilder.append(new String(new byte[] { paramByte1 }));
        byte b = (byte)(Byte.decode(stringBuilder.toString()) << 4);
        stringBuilder = new StringBuilder();
        stringBuilder.append("0x");
        stringBuilder.append(new String(new byte[] { paramByte2 }));
        return (byte)(b | Byte.decode(stringBuilder.toString()));
    }

    public static byte[] shortToByte(short paramShort) {
        byte[] arrayOfByte = new byte[2];
        int i = 0;
        short s = paramShort;
        for (paramShort = 0; paramShort < arrayOfByte.length; paramShort++) {
            arrayOfByte[paramShort] = (new Integer(s & 0xFF)).byteValue();
            s >>= 8;
        }
        return arrayOfByte;
    }

    public static short byteToShort(byte[] paramArrayOfByte) {
        short s = (short)(paramArrayOfByte[0] & 0xFF);
        return (short)((short)((short)(paramArrayOfByte[1] & 0xFF) << 8) | s);
    }

    public static String bytesToHexString(byte[] paramArrayOfByte) {
        int i = paramArrayOfByte.length;
        StringBuilder stringBuilder = new StringBuilder();
        for (byte b = 0; b < i; b++) {
            String str = Integer.toHexString(paramArrayOfByte[b] & 0xFF);
            if (str.length() == 1) {
                stringBuilder.append('0');
            }
            stringBuilder.append(str);
        }
        return stringBuilder.toString().toUpperCase();
    }

    public static byte[] intToByteArray(int paramInt) {
        byte b1 = (byte)(paramInt >> 24 & 0xFF);
        byte b2 = (byte)(paramInt >> 16 & 0xFF);
        byte b3 = (byte)(paramInt >> 8 & 0xFF);
        return new byte[] { (byte)(paramInt & 0xFF), b3, b2, b1 };
    }

    private static byte[] getQ0(byte[] paramArrayOfByte) {
        if (paramArrayOfByte != null && paramArrayOfByte.length > 15) {
            byte[] arrayOfByte = new byte[8];
            System.arraycopy(paramArrayOfByte, 5, arrayOfByte, 0, 8);
            return arrayOfByte;
        }
        return null;
    }

    public static byte[] getDataEncrypt(byte[] paramArrayOfByte1, byte[] paramArrayOfByte2) {
        byte[] arrayOfByte = getCi(paramArrayOfByte2);
        paramArrayOfByte2 = getGc(paramArrayOfByte2);
        paramArrayOfByte1 = getQ0(paramArrayOfByte1);
        if (arrayOfByte != null && paramArrayOfByte2 != null) {
            if (paramArrayOfByte1 == null) {
                return null;
            }
            try {
                paramArrayOfByte1 = ConvertHex.dataEncrypt(arrayOfByte, paramArrayOfByte2, paramArrayOfByte1);
                if (paramArrayOfByte1 != null) {
                    int i = paramArrayOfByte1.length;
                    return (i != 16) ? null : paramArrayOfByte1;
                }
                return null;
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }
        return null;
    }

    private static byte[] getCi(byte[] paramArrayOfByte) { return (paramArrayOfByte != null && paramArrayOfByte.length > 39) ? new byte[] { paramArrayOfByte[33], 18, paramArrayOfByte[34], 52, paramArrayOfByte[35], 86, paramArrayOfByte[36], 120 } : null; }

    private static byte[] getGc(byte[] paramArrayOfByte) {
        paramArrayOfByte = getNc(paramArrayOfByte);
        if (paramArrayOfByte != null && paramArrayOfByte.length == 8) {
            byte b = paramArrayOfByte[2];
            paramArrayOfByte[2] = paramArrayOfByte[3];
            paramArrayOfByte[3] = b;
            paramArrayOfByte[2] = (byte)(paramArrayOfByte[2] ^ 0x55);
            paramArrayOfByte[3] = (byte)(paramArrayOfByte[3] ^ 0xAA);
            return paramArrayOfByte;
        }
        return null;
    }

    private static byte[] getNc(byte[] paramArrayOfByte) {
        if (paramArrayOfByte != null && paramArrayOfByte.length > 39) {
            byte[] arrayOfByte = new byte[8];
            System.arraycopy(paramArrayOfByte, 5, arrayOfByte, 0, 8);
            return arrayOfByte;
        }
        return null;
    }

    public static int bytesToInt(byte[] paramArrayOfByte) {
        byte b1 = paramArrayOfByte[0];
        byte b2 = paramArrayOfByte[1];
        byte b3 = paramArrayOfByte[2];
        return (paramArrayOfByte[3] & 0xFF) << 24 | b1 & 0xFF | (b2 & 0xFF) << 8 | (b3 & 0xFF) << 16;
    }

    public static byte[] getDesEncryptData(String paramString, byte[] paramArrayOfByte) { return hexStringToBytes(bytesToHexString(dataEncrypt(hexStringToBytes(paramString), paramArrayOfByte)).substring(2, paramString.length() - 6)); }

    public static boolean checkDataAuthValid(String paramString) {
        int i = paramString.length();
        return (i < 16) ? false : (paramString.substring(0, 2).equals("A5") ? (!paramString.substring(i - 2).equals("A6") ? false : (!!paramString.substring(6, 10).equals(getCmdLengthData(paramString.substring(10, i - 6))))) : false);
    }

    public static boolean checkDataCrcValid(String paramString, byte[] paramArrayOfByte) {
        int i = paramString.length();
        int j = i - 6;
        String str = paramString.substring(j, i - 2);
        if (paramArrayOfByte == null) {
            byte[] arrayOfByte = hexStringToBytes(paramString.substring(2, j));
            return bytesToHexString(shortToByte((short)calcCrc16(arrayOfByte, 0, arrayOfByte.length, (short)-1))).equals(str);
        } else {
            // 显然不可能
            return true;
        }
        //byte[] arrayOfByte = hexStringToBytes(bytesToHexString(dataEncrypt(hexStringToBytes(arrayOfByte), paramArrayOfByte)).substring(2, arrayOfByte.length() - 6));
        //short s = (short)calcCrc16(arrayOfByte, 0, arrayOfByte.length, (short)-1);
        //return bytesToHexString(shortToByte((short)calcCrc16(paramArrayOfByte, 0, paramArrayOfByte.length, s))).equals(str);
    }

    public static byte[] dataEncrypt(byte[] paramArrayOfByte1, byte[] paramArrayOfByte2) {
        if (paramArrayOfByte1 != null && paramArrayOfByte1.length > 8 && paramArrayOfByte2 != null && paramArrayOfByte2.length == 16) {
            byte b = 6;
            try {
                while (b < paramArrayOfByte1.length - 3) {
                    byte b2 = (byte) (b - 6);
                    byte b1 = b2;
                    if (b2 > 15) {
                        b1 = (byte) (b2 - b2 / 16 * 16);
                    }
                    b2 = paramArrayOfByte1[b];
                    paramArrayOfByte1[b] = (byte)(paramArrayOfByte2[b1] ^ b2);
                    b++;
                }
                return paramArrayOfByte1;
            } catch (Exception n) {
                return null;
            }
        }
        return null;
    }
}
