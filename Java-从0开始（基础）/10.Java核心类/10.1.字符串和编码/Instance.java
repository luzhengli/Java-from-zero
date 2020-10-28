import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

public class Instance {
    public static void main(String[] args) throws UnsupportedEncodingException {
        // // 定义字符串的两种方式
        // // 方式1
        // String s1 = "Hello!";
        // // 方式2
        // String s2 = new String(new char[] { 'H', 'e', 'l', 'l', 'o', '!' });

        // System.out.println(s1 + s2);

        // // 字符串的特点：不可变
        // String s = "hello";

        // // 字符串的比较
        // String s3 = "hello";
        // String s4 = "HELLO".toLowerCase();
        // String s5 = "Hello".toUpperCase();
        // System.out.println(s3 == s4);
        // System.out.println(s3.equals(s4));
        // System.out.println(s3.equals(s4));

        // // 查看子串
        // System.out.println("Hello".contains("llo")); // true
        // System.out.println("Hello".indexOf("l")); // 2
        // System.out.println("Hello".lastIndexOf("l")); // 3
        // System.out.println("Hello".startsWith("He")); // true
        // System.out.println("Hello".endsWith("llo"));// true

        // // 去除空白字符
        // System.out.println(" \tHello\r\n ".trim());
        // System.out.println(" \tHello\r\n ".strip());
        // System.out.println(" \tHello\r\n ".stripLeading());
        // System.out.println(" \tHello\r\n ".stripTrailing());

        // // 判断空字符串和空白字符串
        // System.out.println("".isEmpty());
        // System.out.println(" ".isEmpty());
        // System.out.println(" \n".isBlank());
        // System.out.println("hi\n".isBlank());

        // // 字符串的替换
        // String s = "hello";
        // System.out.println(s.replace('l', 'w')); // "hewwo"，所有字符'l'被替换为'w'
        // System.out.println(s.replace("ll", "~~~")); // "he~~o"，所有子串"ll"被替换为"~~"

        // String ss = "A,,B;C ,D";
        // System.out.println(ss.replaceAll("[\\,\\;\\s]+", ",")); // "A,B,C,D"

        // // 分割字符串
        // String s = "A,B,C,D";
        // String[] ss = s.split("\\,"); // {"A", "B", "C", "D"}
        // for (String i : ss) {
        // System.out.println(i);
        // }

        // // 连接子串
        // String[] arr = { "A", "B", "C" };
        // System.out.println(String.join("-", arr)); // "A-B-C"
        // System.out.println(String.join("-", "a", "b", "c")); // "A-B-C"

        // // 格式字符串
        // String s = "Hi %s, your score is %d!";
        // System.out.println(s.formatted("Alice", 80));
        // System.out.println(String.format("Hi %s, your score is %.2f!", "Bob", 59.5));

        // 类型转换
        // System.out.println(String.valueOf(123)); // "123"
        // System.out.println(String.valueOf(45.67)); // "45.67"
        // System.out.println(String.valueOf(true)); // "true"
        // System.out.println(String.valueOf(new Object())); //
        // 类似java.lang.Object@636be97c

        // int n1 = Integer.parseInt("123"); // 123
        // int n2 = Integer.parseInt("ff", 16); // 按十六进制转换，255

        // boolean f1 = Boolean.parseBoolean("True");
        // boolean f2 = Boolean.parseBoolean("FALSE");

        // System.out.println(Integer.getInteger("java.version"));

        // char[] cs = "Hello".toCharArray(); // String -> char[]
        // String s = new String(cs); // char[] -> String

        // int[] scores = new int[] { 88, 77, 51, 66 };
        // Score s = new Score(scores);
        // s.printScores();
        // scores[2] = 99;
        // s.printScores();

        // 字符串编码
        byte[] b1 = "Hello".getBytes(); // 按系统默认编码转换，不推荐
        byte[] b2 = "Hello".getBytes("UTF-8"); // 按UTF-8编码转换
        byte[] b3 = "Hello".getBytes("GBK"); // 按GBK编码转换
        byte[] b4 = "Hello".getBytes(StandardCharsets.UTF_8); // 按UTF-8编码转换
        
        // 字符串解码
        String s1 = new String(b1); // 按GBK转换
        String s2 = new String(b2, "UTF-8"); // 按UTF-8转换
        String s3 = new String(b3, "GBK"); // 按GBK转换
        String s4 = new String(b4, StandardCharsets.UTF_8); // 按UTF-8转换
    }
}

class Score {
    private int[] scores;

    public Score(int[] scores) {
        this.scores = scores;
    }

    public void printScores() {
        System.out.println(Arrays.toString(scores));
    }
}