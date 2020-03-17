package za.co.mtn.product.helpers;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;

public class LoggingHelper {

    private static LoggingHelper instance;
    private ObjectMapper objectMapper;

    private LoggingHelper() {
        objectMapper = new ObjectMapper();
    }

    public static LoggingHelper getInstance() {
        if (instance == null) {
            instance = new LoggingHelper();
        }

        return instance;
    }

    public void responseObject(Logger logger, Object object) {
        String x = "{}";
        try {
            x = objectMapper.writeValueAsString(object);
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
        }

        logger.info("{\"responseObject\":" + x + "}");
    }

    public void requestObject(Logger logger, Object object) {
        String x = "{}";
        try {
            x = objectMapper.writeValueAsString(object);
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
        }

        logger.info("{\"requestObject\":" + x + "}");
    }

}
