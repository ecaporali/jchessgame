package au.com.aitcollaboration.chessgame.view.exceptions;

import au.com.aitcollaboration.chessgame.support.UIMessages;

public class KingInCheckException extends Throwable {

    public KingInCheckException(){
        super(UIMessages.KING_IN_CHECK_EXCEPTION);
    }
}
