package au.com.aitcollaboration.chessgame.model.player;

import au.com.aitcollaboration.chessgame.Color;
import au.com.aitcollaboration.chessgame.exceptions.InvalidPositionException;
import au.com.aitcollaboration.chessgame.model.moves.PieceMoves;
import au.com.aitcollaboration.chessgame.support.MyLogger;
import au.com.aitcollaboration.chessgame.support.UIMessages;
import au.com.aitcollaboration.chessgame.support.Utils;
import au.com.aitcollaboration.chessgame.view.GameView;

public class HumanPlayer extends Player {

    public HumanPlayer(String playerName, Color color, GameView gameView) {
        super(playerName, color, gameView);
    }

    @Override
    public int[] getFromSquareCoordinate() {
        return getValidCoordinates(UIMessages.PIECE_TO_MOVE);
    }

    @Override
    public int[] getToSquareCoordinate() {
        return getValidCoordinates(UIMessages.WHERE_TO_MOVE_PIECE);
    }

    @Override
    public void showPracticalMoves(PieceMoves pieceMoves) {
        gameView.showMessage(pieceMoves.toString());
    }

    private int[] getValidCoordinates(String message) {
        int[] coordinates = null;

        while (coordinates == null) {
            String coordinate = getTextAnswer(message);
            try {
                coordinates = Utils.toBoardPosition(coordinate);
            } catch (InvalidPositionException e) {
                MyLogger.debug(e);
                showError(e.getMessage());
            }
        }
        return coordinates;
    }
}
