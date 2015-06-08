package au.com.aitcollaboration.chessgame.exceptions;

public class InvalidMoveException extends Exception {

    public InvalidMoveException(Exception e){
        super(e);
    }

    public InvalidMoveException(String message){
        super(message);
    }
}
