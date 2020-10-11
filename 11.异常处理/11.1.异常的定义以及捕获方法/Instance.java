import java.io.UnsupportedEncodingException;
import java.util.Arrays;

import static java.lang.System.out;

public class Instance {
    public static void main(String[] args) {
        byte[] bs = toGBK("中文");
        out.println(Arrays.toString(bs));
    }

    static byte[] toGBK(String s) {
        try {
            return s.getBytes("GBK");
        } catch (UnsupportedEncodingException e) {
            out.println(e);
            return s.getBytes();
        }
    }
}