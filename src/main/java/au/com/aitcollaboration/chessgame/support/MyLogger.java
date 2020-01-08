package au.com.aitcollaboration.chessgame.support;

import java.util.logging.Logger;
import java.util.logging.Level;

public class MyLogger {

    private static final Logger logger = Logger.getLogger(MyLogger.class.getSimpleName());

    public static void debug(Exception e) {
        if (logger.isLoggable(Level.FINEST))
            logger.log(Level.FINEST, e.getMessage(), e);
    }
}
