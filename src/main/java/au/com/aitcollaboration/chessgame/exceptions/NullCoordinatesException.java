package au.com.aitcollaboration.chessgame.exceptions;

import au.com.aitcollaboration.chessgame.support.UIMessages;

public class NullCoordinatesException extends RuntimeException {

    public NullCoordinatesException(){
        super(UIMessages.NULL_COORDINATES_EXCEPTION_MSG);
    }
}
