import java.io.UnsupportedEncodingException;
import java.util.Arrays;

import static java.lang.System.out;

// public class Instance {
//     public static void main(String[] args) {
//         // byte[] bs = toGBK("中文");
//         // out.println(Arrays.toString(bs));
//         try {
//             process1();
//         } catch (Exception e) {
//             e.printStackTrace();
//         }

//     }

//     static byte[] toGBK(String s) {
//         try {
//             return s.getBytes("GBK");
//         } catch (UnsupportedEncodingException | NumberFormatException e) {
//             out.println(e);
//             return s.getBytes();
//         } finally {
//             out.println("End.");
//         }
//     }

//     static void process1(){
//             process2();   
//     }

//     static void process2() {
//         Integer.parseInt(null); // 会抛出 NumberFormatException
//     }
// }

public class Instance {
    public static void main(String[] args) {
        try {
            process1();
        } catch (Exception e) {
            e.printStackTrace(); // 输出方法调用栈
        }
    }

    static void process1() { // process1捕获了process2的NullPointerException异常，但马上又抛出IllegalArgumentException异常 
        try {
            process2();
        } catch (NullPointerException e) {
            throw new IllegalArgumentException(e); // 把捕获的异常作为传输传给新的异常类
        }
    }

    static void process2() {
        throw new NullPointerException();
    }
}