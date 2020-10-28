import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Instance {
    public static void main(String[] args) {
        final Logger logger = LoggerFactory.getLogger(Instance.class);
        logger.info("Start process...");
        String s = "Test.";
        logger.info("The value of {} is {}", "s", s);
        logger.info("End process...");
    }
}
