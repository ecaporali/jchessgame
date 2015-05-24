package au.com.aitcollaboration.chessgame.view.exceptions;

import au.com.aitcollaboration.chessgame.support.UIMessages;

public class InvalidPositionException extends Exception {

    public InvalidPositionException() {
        super(UIMessages.INVALID_POSITION_EXCEPTION);
    }
}
