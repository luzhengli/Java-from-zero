import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.util.Properties;
import static java.lang.System.out;

public class App {
    public static void main(String[] args) throws Exception {
        // 1. 从文件流读取配置
        // Properties props = new Properties();
        // String f = "settings.properties";
        // FileInputStream fileStream = new FileInputStream(f);
        // props.load(fileStream);
        // String filePath = props.getProperty("last_open_file");
        // String interval = props.getProperty("auto_save_interval", "120"); 
        // out.println(filePath);
        // out.println(interval);

        // 2. 从内存的字节流读取配置
        // String settings = "# test" + "\n" + "course=Java" + "\n" + "last_open_date=2019-08-07T12:35:01";
        // ByteArrayInputStream input = new ByteArrayInputStream(settings.getBytes("UTF-8"));
        // Properties props = new Properties();
        // props.load(input);
        // String course = props.getProperty("course");
        // String lastOpenDate = props.getProperty("last_open_date");
        // String lastOpenFile = props.getProperty("lastOpenFile");
        // String autoSave = props.getProperty("auto_save", "120");
        // out.println(course);
        // out.println(lastOpenDate);
        // out.println(lastOpenFile);
        // out.println(autoSave);

        // 3. 写入配置文件
        
        Properties props = new Properties();
        props.setProperty("lang", "java");
        props.setProperty("version", "14");
        FileOutputStream fOut = new FileOutputStream("settings2.properties"); // 这里写入的中文注释会被转成 Unicode 编码
        props.store(fOut, "这是写入的注释");
    }
}
