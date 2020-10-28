import javax.swing.plaf.synth.SynthStyle;

public class Instance {
    public static void main(String[] args) {
        int a = 1, b = 2;
        //
        // System.out.println(++a); // 2
        // System.out.println(b++); // 2
        // System.out.println(b); // 3

        /* // 移位运算
        // 1. 有符号的移位：移位时符号位不变
        a = b >> 1; // a == 1
        System.out.println(a);
        a = b >> 2; // a == 0
        System.out.println(a);

        a = b << 1; // a == 4
        System.out.println(a);
        a = b << 2; // a == 8
        System.out.println(a);

        b = -24;
        a = b >> 1;
        System.out.println(a); // 右移负数 符号不变 数字变为原来一半 a == -12
        a = b >> 2;
        System.out.println(a); // a == -6

        // 2. 无符号的移位：移位时高位补0
        b = -8;
        a = b >>> 1; // a == 2147483644
        System.out.println(a); */


        /*
         * // 按位运算 int n = 0;
         * 
         * n = 1 & 1; // 1 System.out.println(n); n = 1 & 0; // 0 System.out.println(n);
         * n = 0 & 0; // 0 System.out.println(n);
         * 
         * n = 0 | 1; // 1 System.out.println(n); n = 1 | 1; // 1 System.out.println(n);
         * 
         * n = ~0; System.out.println(n); // n n = ~1; System.out.println(n);
         * 
         * System.out.println(5 | 2);
         */
    }
}
