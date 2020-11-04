package packg;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class copyFile {
    public static void main(String[] args) throws Exception {
        String source = args[0];
        String target = args[1];
        copy(source, target);
    }

    static void copy(String source, String target) throws IOException {
        try (InputStream input = new FileInputStream(source); OutputStream output = new FileOutputStream(target)) {
            input.transferTo(output);
        }
    }
}