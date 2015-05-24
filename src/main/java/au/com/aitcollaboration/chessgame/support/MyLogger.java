package au.com.aitcollaboration.chessgame.support;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MyLogger {

    private static final Logger logger = LoggerFactory.getLogger(MyLogger.class);
    private static final boolean DEBUG = true;

    public static void debug(Exception e){
        if(DEBUG)
            logger.debug("Exception in: ", e);
    }
}
