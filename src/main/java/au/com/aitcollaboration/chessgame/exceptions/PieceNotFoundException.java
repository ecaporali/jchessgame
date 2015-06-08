package au.com.aitcollaboration.chessgame.exceptions;

import au.com.aitcollaboration.chessgame.support.UIMessages;

public class PieceNotFoundException extends InvalidMoveException {

    public PieceNotFoundException() {
        super(UIMessages.PIECE_NOT_FOUND_EXCEPTION);
    }
}
