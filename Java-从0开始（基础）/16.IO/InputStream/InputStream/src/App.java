import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class App {
    public static void main(String[] args) throws Exception {
        // readFile(); // 一个个字节读取FileInputStream
        // readFileBytes(); // 使用buffer的方式多个字节的读取FileInputStream
        // readByteArrayInputStream(); // 内存中构建输入流
        concatString(); // 内存中构建输入流，注意读取拼接字节拼接为字符串
    }

    static void readFile() throws IOException {

        try (InputStream input = new FileInputStream("README.md")) {
            int n;
            while ((n = input.read()) != -1) {
                System.out.println(n);
            }
        } // 编译器在此自动为我们写入finally并调用close()
    }

    static void readFileBytes() throws IOException {
        try (InputStream input = new FileInputStream("README.md")) {
            byte[] buffer = new byte[100]; // 设置缓冲区大小
            int n;
            while ((n = input.read(buffer)) != -1) {
                System.out.println("已经读取" + n + "个字节");
                // for (byte b : buffer) { // 遍历bytes
                // System.out.println(b);
                // }
            }
        }
    }

    static void readByteArrayInputStream() throws IOException {
        byte[] bytes = { 72, 101, 108, 108, 111, 33 };
        try (InputStream input = new ByteArrayInputStream(bytes)) {
            int n;
            while ((n = input.read()) != -1) {
                System.out.println((char) n); // H e l l o !
            }
        }

    }

    static void concatString() throws IOException {
        byte[] bytes = { 72, 101, 108, 108, 111, 33 };
        StringBuilder sb = new StringBuilder(16);
        try (InputStream input = new ByteArrayInputStream(bytes)) {
            int n;
            while ((n = input.read()) != -1) {
                sb.append((char) n);
            }
            System.out.println(sb.toString());
        }
    }
}
