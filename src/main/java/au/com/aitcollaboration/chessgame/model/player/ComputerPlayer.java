package au.com.aitcollaboration.chessgame.model.player;

import au.com.aitcollaboration.chessgame.controller.Rules;
import au.com.aitcollaboration.chessgame.model.game.structure.Board;
import au.com.aitcollaboration.chessgame.model.game.structure.Square;
import au.com.aitcollaboration.chessgame.model.moves.PieceMoves;
import au.com.aitcollaboration.chessgame.support.Constants;
import au.com.aitcollaboration.chessgame.view.GameView;

public class ComputerPlayer extends Player {

    public ComputerPlayer(GameView gameView) {
        super(gameView);
        name = Constants.COMPUTER_NAME;
    }

    @Override
    public Square getFromSquare(Board board, Rules rules) {
        return null;
    }

    @Override
    public Square getToSquare(Board board, PieceMoves pieceMoves) {
        return null;
    }
}
