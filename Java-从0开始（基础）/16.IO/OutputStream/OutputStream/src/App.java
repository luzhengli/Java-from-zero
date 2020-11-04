import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class App {
    public static void main(String[] args) throws Exception {
        // writeFileOutputStreamByByte(); // 一个个字节写入
        // writeFileOutputStreamByByteArray(); // 写入 byte[]
        // writeByteArrayOutputStream();
        writeAndRead();
    }

    static void writeFileOutputStreamByByte() throws IOException {
        try (OutputStream output = new FileOutputStream("out\\output1.txt")) {
            output.write(72); // H
            output.write(101); // e
            output.write(108); // l
            output.write(108); // l
            output.write(111); // o
        }
    }

    static void writeFileOutputStreamByByteArray() throws IOException {
        try (OutputStream output = new FileOutputStream("out\\output2.txt")) {
            output.write("World".getBytes("UTF-8"));
        }
    }

    static void writeByteArrayOutputStream() throws IOException {
        byte[] data;
        try (ByteArrayOutputStream output = new ByteArrayOutputStream()) {
            output.write("Hello ".getBytes("UTF-8"));
            output.write("World.".getBytes("UTF-8"));
            data = output.toByteArray();
        }
        System.out.println(new String(data, "UTF-8"));
    }

    static void writeAndRead() throws IOException {
        try (InputStream input = new FileInputStream("out\\output1.txt");
                OutputStream output = new FileOutputStream("out\\output.txt")) {
                    input.transferTo(output);
        }
    }
}
