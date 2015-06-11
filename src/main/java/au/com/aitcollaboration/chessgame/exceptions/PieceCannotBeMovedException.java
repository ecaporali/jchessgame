package au.com.aitcollaboration.chessgame.exceptions;

import au.com.aitcollaboration.chessgame.support.UIMessages;

public class PieceCannotBeMovedException extends InvalidMoveException {

    public PieceCannotBeMovedException() {
        super(UIMessages.PIECE_CANNOT_BE_MOVED_EXCEPTION);
    }
}
