package tony;
import java.io.UnsupportedEncodingException;

public class StringBinUtil {
    public static byte[] strToByteArray(String str) {
        if (str == null) {
            return null;
        }
        byte[] byteArray = new byte[0];////-127 -123 -91
        try {
            byteArray = str.getBytes("utf8");//中文转换成byte类型后在Java里是负数
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return byteArray;
    }

    public static String byteArrayToString(byte[] byteArray) {
        StringBuilder builder = new StringBuilder(byteArray.length);
        String zeroFill[] = new String[]{"0", "00", "000", "0000", "00000", "000000", "0000000"};
        for (int i = 0; i < byteArray.length; i++) {
            String binString = Integer.toBinaryString(byteArray[i] & 0xff);
            int binLength = binString.length();
            if (binLength < 8) {
                int strLen = 8 - binLength - 1;
                String fill = zeroFill[strLen];
                builder.append(fill);
            }
            builder.append(binString);
        }
        return builder.toString();//0110100001100101011011000110110001101111111010001011111010010011111001011000010110100101
    }

    public static String intToBinStr(int len){
        String binString = Integer.toBinaryString(len);
        int binLength = binString.length();
        String fill = "";
        if (binLength < 15) {
            int strLen = 15 - binLength;
            for (int i=0;i<strLen;i++){
                fill=fill+"0";
            }
        }
        return fill+binString;
    }

    public static byte[] binStrToByteArray(String str) {
        if (str == null) {
            return null;
        }
        int length=str.length()/8;//0110100001100101011011000110110001101111111010001011111010010011111001011000010110100101
        byte[] byteArray = new byte[length];
        int byteLength = 0;
        for (int i = 0; i <= str.length() - 1; i++) {
            if (i % 8 == 0) {
                int end=i+8;
                int realEnd=end>str.length()?str.length():end;
                String iter = str.substring(i, realEnd);
                int val = Integer.parseInt(iter, 2);
                byte b = (byte) val;
                byteArray[byteLength] = b;
                byteLength++;
            }
        }
        return byteArray;
    }
}
