package au.com.aitcollaboration.chessgame.controller.exceptions;

import au.com.aitcollaboration.chessgame.support.UIMessages;

public class InvalidPositionException extends Throwable {

    public InvalidPositionException() {
        super(UIMessages.INVALID_POSITION_EXCEPTION);
    }
}
