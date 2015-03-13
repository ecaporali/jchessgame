package au.com.aitcollaboration.chessgame.player;

import au.com.aitcollaboration.chessgame.pieces.Pieces;
import au.com.aitcollaboration.chessgame.support.Constants;

public class ComputerPlayer extends Player {

    public ComputerPlayer(Color color, Pieces pieces) {
        super(Constants.COMPUTER_NAME, color, pieces);
    }
}
