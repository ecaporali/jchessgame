package au.com.aitcollaboration.chessgame.model.player;

import au.com.aitcollaboration.chessgame.controller.Rules;
import au.com.aitcollaboration.chessgame.model.game.structure.Board;
import au.com.aitcollaboration.chessgame.model.game.structure.Square;
import au.com.aitcollaboration.chessgame.model.moves.PieceMoves;
import au.com.aitcollaboration.chessgame.model.moves.PlayerMoves;
import au.com.aitcollaboration.chessgame.model.pieces.Piece;
import au.com.aitcollaboration.chessgame.model.pieces.Pieces;
import au.com.aitcollaboration.chessgame.support.Constants;
import au.com.aitcollaboration.chessgame.view.GameView;
import org.apache.commons.lang3.time.StopWatch;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class Player {

    protected String name;
    protected StopWatch stopWatch;
    protected Pieces pieces;
    protected GameView gameView;
    protected List<PlayerMoves> moves;

    private Player() {
        this.stopWatch = new StopWatch();
        this.moves = new ArrayList<>();
    }

    protected Player(GameView gameView) {
        this();
        this.gameView = gameView;
        stopWatchSetup();
    }

    private void stopWatchSetup() {
        stopWatch.start();
        suspendWatch();
    }

    public Map<String, Square> move(Board board, Rules rules) {
        Square fromSquare = getFromSquare(board, rules);
        PlayerMoves playerMoves = rules.getPlayerMoves(pieces);

        PieceMoves pieceMoves = playerMoves.getPieceMoves(fromSquare.getPiece());

        Square toSquare = getToSquare(board, pieceMoves);

        Map<String, Square> moveMap = new HashMap<>(2);
        moveMap.put(Constants.FROM_SQUARE, fromSquare);
        moveMap.put(Constants.TO_SQUARE, toSquare);

        return moveMap;
    }

    public boolean isOwnPiece(Piece piece) {
        return pieces.contains(piece);
    }

    public void setPieces(Pieces pieces) {
        this.pieces = pieces;
    }

//    public abstract void play(Rules rules);

    public void resumeWatch() {
        stopWatch.resume();
    }

    public void suspendWatch() {
        stopWatch.suspend();
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

    public abstract Square getFromSquare(Board board, Rules rules);

    public abstract Square getToSquare(Board board, PieceMoves pieceMoves);

    public void showPracticalMoves(PieceMoves pieceMoves) {
        gameView.showMessage(pieceMoves.toString());
    }

    public Pieces getPieces() {
        return pieces;
    }

    @Override
    public String toString() {
        return "Player " + pieces + ": " + name;
    }
}
