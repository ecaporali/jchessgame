package au.com.aitcollaboration.chessgame.view.exceptions;

import au.com.aitcollaboration.chessgame.support.UIMessages;

public class PieceNotFoundException extends Exception {

    public PieceNotFoundException() {
        super(UIMessages.PIECE_NOT_FOUND_EXCEPTION);
    }
}