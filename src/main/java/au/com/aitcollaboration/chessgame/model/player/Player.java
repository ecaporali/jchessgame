package au.com.aitcollaboration.chessgame.model.player;

import au.com.aitcollaboration.chessgame.Color;
import au.com.aitcollaboration.chessgame.model.game.structure.Board;
import au.com.aitcollaboration.chessgame.model.moves.PieceMoves;
import au.com.aitcollaboration.chessgame.model.pieces.Piece;
import au.com.aitcollaboration.chessgame.view.GameView;
import org.apache.commons.lang3.time.StopWatch;

public abstract class Player {

    protected final String playerName;
    protected final StopWatch stopWatch;
    protected final Color color;
    protected final GameView gameView;

    protected Player(String playerName, Color color, GameView gameView) {
        this.stopWatch = new StopWatch();
        this.playerName = playerName;
        this.color = color;
        this.gameView = gameView;
        stopWatchSetup();
    }

    public abstract int[] getFromSquareCoordinate();

    public abstract int[] getToSquareCoordinate();

    public abstract void showPracticalMoves(PieceMoves pieceMoves);

    private void stopWatchSetup() {
        stopWatch.start();
        suspendWatch();
    }

    public void resumeWatch() {
        stopWatch.resume();
    }

    public void suspendWatch() {
        stopWatch.suspend();
    }

    public boolean isOwnPiece(Piece piece) {
        return piece.matches(color);
    }

    public void showBoard(Board board) {
        gameView.showBoard(board);
    }

    public String getTextAnswer(String message) {
        return gameView.getTextAnswer(message);
    }

    public void showError(String error) {
        gameView.showError(error);
    }

    public void showPlayerName() {
        gameView.showMessage(this.toString());
    }

    @Override
    public String toString() {
        return "\nPlayer " + color + ": " + playerName;
    }
}
