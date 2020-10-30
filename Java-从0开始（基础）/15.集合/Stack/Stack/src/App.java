import java.util.Deque;
import java.util.LinkedList;

public class App {
    public static void main(String[] args) throws Exception {
        String hex = toHex(12500);
        if (hex.equalsIgnoreCase("30D4")) { // 比较转换后的16进制字符串是否相同(忽略大小写)
            System.out.println("测试通过");
        } else {
            System.out.println("测试失败");
        }

    }

    static String toHex(int n) {
        String hex = "";
        int remainder = -1;
        Deque<String> stack = new LinkedList<>(); // 用Deque模拟stack 具体实现类用LinkedList
        while (n != 0) { // switch 表达式赋值（java14开始支持）
            remainder = n % 16;
            String temp = switch (remainder) {
                case 10 -> "A";
                case 11 -> "B";
                case 12 -> "C";
                case 13 -> "D";
                case 14 -> "E";
                case 15 -> "F";
                default -> String.valueOf(remainder);
            };
            stack.push(temp);
            n /= 16;
        }

        for (String s : stack) {
            hex = hex.concat(s);
        }

        return hex;
    }
}
