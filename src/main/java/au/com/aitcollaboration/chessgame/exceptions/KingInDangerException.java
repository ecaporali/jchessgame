package au.com.aitcollaboration.chessgame.exceptions;

import au.com.aitcollaboration.chessgame.support.UIMessages;

public class KingInDangerException extends InvalidMoveException {

    public KingInDangerException(){
        super(UIMessages.KING_IN_DANGER_EXCEPTION);
    }
}
