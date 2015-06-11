package au.com.aitcollaboration.chessgame.model.player;

import au.com.aitcollaboration.chessgame.Color;
import au.com.aitcollaboration.chessgame.model.moves.PieceMoves;
import au.com.aitcollaboration.chessgame.support.Constants;
import au.com.aitcollaboration.chessgame.view.GameView;

public class ComputerPlayer extends Player {

    public ComputerPlayer(GameView gameView) {
        super(Constants.COMPUTER_NAME, Color.BLACK, gameView);
    }

    @Override
    public int[] getFromSquareCoordinate() {
        return new int[0];
    }

    @Override
    public int[] getToSquareCoordinate() {
        return new int[0];
    }

    @Override
    public void showPracticalMoves(PieceMoves pieceMoves) {
        //DO NOTHING FOR COMPUTER PLAYER
    }
}
