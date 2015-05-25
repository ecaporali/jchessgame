package au.com.aitcollaboration.chessgame.support;

import org.apache.log4j.Logger;

public class MyLogger {

    private static final Logger logger = Logger.getLogger(MyLogger.class);

    public static void debug(Exception e) {
        if (logger.isDebugEnabled())
            logger.debug(e.getMessage(), e);
    }
}
