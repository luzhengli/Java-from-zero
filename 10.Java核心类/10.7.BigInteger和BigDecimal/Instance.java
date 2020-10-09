import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;

public class Instance {
    public static void main(String[] args) {
        // BigInteger l1 = new BigInteger("1234567890"); // 注意这里传入的数据必须包含在字符串中 因为 Java
        // 基本整形不支持这么大的数据
        // BigInteger l2 = new BigInteger("111222333444");
        // BigInteger sum = l1.add(l2);
        // System.out.println(sum);

        // BigInteger i = new BigInteger("123456789000");
        // System.out.println(i.multiply(i).longValueExact()); // 抛出 ArithmeticException

        // BigDecimal bd = new BigDecimal("123.4567");
        // System.out.println(bd.multiply(bd));
        // BigDecimal d1 = new BigDecimal("123.45");
        // BigDecimal d2 = new BigDecimal("123.4500");
        // BigDecimal d3 = new BigDecimal("1234500");
        // System.out.println(d1.scale()); // 2
        // System.out.println(d2.scale()); // 4
        // System.out.println(d3.scale()); // 0

        // System.out.println(d3.stripTrailingZeros().scale()); // -2

        // System.out.println(d1.stripTrailingZeros()); // 123.45
        // System.out.println(d2.stripTrailingZeros()); // 123.45
        // System.out.println(d3.stripTrailingZeros()); // 1.2345E+6

        // BigDecimal bd = new BigDecimal("123.4567");
        // System.out.println(bd.setScale(2, RoundingMode.HALF_DOWN)); // 123.46
        // System.out.println(bd.setScale(2, RoundingMode.DOWN)); //123.45

        // BigDecimal d1 = new BigDecimal("123.456");
        // BigDecimal d2 = new BigDecimal("23.456789");
        // System.out.println(d1.divide(d2, 10, RoundingMode.HALF_UP)); // 5.2631244626

        // BigDecimal d1 = new BigDecimal("12.75");
        // BigDecimal d2 = new BigDecimal("0.15");
        // BigDecimal remainder = d1.divideAndRemainder(d2)[1];

        // if (remainder.signum() == 0) {
        //     System.out.println("d1 可以整除 d2.");
        // } else {
        //     System.out.println("d1 无法整除 d2.");
        // }

        BigDecimal d1 = new BigDecimal("123.456");
        BigDecimal d2 = new BigDecimal("123.45600");
        System.out.println(d2.equals(d1)); // false
        System.out.println(d2.stripTrailingZeros().equals(d1)); // true
        System.out.println(d2.compareTo(d1)); // 0
    }
}