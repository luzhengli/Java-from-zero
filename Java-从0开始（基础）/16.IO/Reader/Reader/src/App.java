import java.io.FileInputStream;
import java.io.FileReader;
import java.io.InputStream;
import java.io.Reader;
import java.nio.charset.StandardCharsets;

public class App {
    public static void main(String[] args) throws Exception {
        // 1.逐字符读取
        // try (Reader reader = new FileReader("src\\files\\test1.txt",
        // StandardCharsets.UTF_8)) {
        // int n;
        // while ((n = reader.read()) != -1) {
        // System.out.printf("%c", n);
        // }
        // }

        // 2.读取到char数组
        // try (Reader reader = new FileReader("src\\files\\test1.txt",
        // StandardCharsets.UTF_8)) {
        // char[] buffer = new char[3];
        // int n;
        // while ((n = reader.read(buffer)) != -1) {
        // System.out.println(buffer);
        // }
        // }

        // 3.Reader转换器
        // try (Reader reader = new java.io.InputStreamReader(new FileInputStream("src\\files\\test1.txt"),
        //         StandardCharsets.UTF_8);) {
        //     char[] buffer = new char[100];
        //     int n;
        //     while ((n = reader.read(buffer)) != -1) {
        //         System.out.println(buffer);
        //     }
        // }
    }
}
