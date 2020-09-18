/**
 * Instance
 */
public class Instance {

    public static void main(String[] args) {
        // 整型
        /* int i1 = 2147483647;
        int i2 = -2147483648;
        int i3 = 2_000_000_000; // 加下划线更容易识别
        System.out.println(i3);
        int i4 = 0xff0000; // 十六进制表示的16711680
        int i5 = 0b1000000000; // 二进制表示的512
        long l = 9000000000000000000L; // long型的结尾需要加L */

        // 浮点型
        /* float f1 = 3.14f;  // float 类型要加上 f后缀
        System.out.println(f1);
        float f2 = 3.14e38f; // 科学计数法表示的3.14x10^38
        double d = 1.79e308;
        double d2 = -1.79e308;
        double d3 = 4.9e-324; // 科学计数法表示的4.9x10^-324 */

        // // 字符型
        // char c1 = 'a';
        // // char c2 = '中';
        // System.out.println(c1);

        // int n = 100;
        // int sum = 0;
        // sum = (1+n)*n/2;
        // System.out.println(sum);

        // 强制类型转换
        // int n1 = (int) 12.3; // 12
        // int n2 = (int) 12.7; // 12
        // int n3 = (int) -12.7; // -12
        // int n4 = (int) (12.7 + 0.5); // 13
        // int n5 = (int) 1.2e20; // 2147483647
        // System.out.println(n1);
        // System.out.println(n2);
        // System.out.println(n3);
        // System.out.println(n4);
        // System.out.println(n5);
        
        double d = 1.6;
        int n = (int)(d+0.5);  
        System.out.println(n);
    }
}