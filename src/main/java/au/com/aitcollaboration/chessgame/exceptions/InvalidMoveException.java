package au.com.aitcollaboration.chessgame.exceptions;

public class InvalidMoveException extends Exception {

    public InvalidMoveException(String errorMessage) {
        super(errorMessage);
    }
}
