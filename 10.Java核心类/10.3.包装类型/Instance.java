public class Instance {
    public static void main(String[] args) {
        // 包装类型
        // Integer n1 = new Integer(5);
        // Integer n2 = Integer.valueOf(10);
        // Integer n3 = Integer.valueOf("20");

        // System.out.printf("n1=%d, n2=%d, n3=%d", n1.intValue(), n2.intValue(),
        // n3.intValue());

        // int n = n2.intValue();

        // 自动装箱/拆箱
        // Integer n1 = 5;
        // int n2 = n1;

        // 通过 Number 类调用包装类型的方法
        // Number num = Integer.valueOf(999);
        // byte b = num.byteValue();
        // int n = num.intValue();
        // long l = num.longValue();
        // float f = num.floatValue();
        // double d = num.doubleValue();

        // 实现无符号数
        byte x = -1;
        byte y = 127;
        short z = -2;
        System.out.println(Byte.toUnsignedInt(x)); // 255
        System.out.println(Byte.toUnsignedInt(y)); // 127
        System.out.println(Short.toUnsignedInt(z)); // 65534
    }
}