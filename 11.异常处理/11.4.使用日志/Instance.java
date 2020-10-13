import java.io.UnsupportedEncodingException;
import java.util.logging.Logger;

public class Instance {
    public static void main(String[] args) {
        Logger logger = Logger.getLogger(Instance.class.getName());
        logger.info("Start process...");
        try {
            "".getBytes("invalidCharsetName");
        } catch (UnsupportedEncodingException e) {
            logger.severe(e.toString());
        }
        logger.fine("ok！"); // INFO 以下级别的日志不会被输出
        logger.info("Process end.");

    }
}
