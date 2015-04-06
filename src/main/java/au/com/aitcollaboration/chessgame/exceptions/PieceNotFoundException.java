package au.com.aitcollaboration.chessgame.exceptions;

public class PieceNotFoundException extends Throwable {

    public PieceNotFoundException(String message){
        super(message);
    }
}
