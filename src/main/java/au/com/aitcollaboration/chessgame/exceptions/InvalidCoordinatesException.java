package au.com.aitcollaboration.chessgame.exceptions;

import au.com.aitcollaboration.chessgame.support.UIMessages;

public class InvalidCoordinatesException extends IllegalArgumentException {

    public InvalidCoordinatesException(){
        super(UIMessages.NULL_COORDINATES_EXCEPTION);
    }
}
