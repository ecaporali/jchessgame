package au.com.aitcollaboration.chessgame.model.player;

import au.com.aitcollaboration.chessgame.model.game.structure.Board;
import au.com.aitcollaboration.chessgame.model.moves.PieceMoves;
import au.com.aitcollaboration.chessgame.model.pieces.Piece;
import au.com.aitcollaboration.chessgame.model.pieces.Pieces;
import au.com.aitcollaboration.chessgame.view.GameView;
import org.apache.commons.lang3.time.StopWatch;

public abstract class Player {

    protected String playerName;
    protected StopWatch stopWatch;
    protected Pieces pieces;
    protected GameView gameView;

    private Player() {
        this.stopWatch = new StopWatch();
    }

    protected Player(String playerName, GameView gameView) {
        this();
        this.playerName = playerName;
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
        return pieces.contains(piece);
    }

    public void setPieces(Pieces pieces) {
        this.pieces = pieces;
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

    public Pieces getPieces() {
        return pieces;
    }

    @Override
    public String toString() {
        return "\nPlayer " + pieces + ": " + playerName;
    }
}
