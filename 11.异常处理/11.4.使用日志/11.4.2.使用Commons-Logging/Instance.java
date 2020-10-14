import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class Instance {
    public static void main(String[] args) {
        Log log = LogFactory.getLog(Instance.class);
        log.info("start...");
        log.warn("end.");
    }
}
