package au.com.aitcollaboration.chessgame.model.player;

import au.com.aitcollaboration.chessgame.controller.GameController;
import au.com.aitcollaboration.chessgame.controller.RulesController;
import au.com.aitcollaboration.chessgame.model.moves.PlayerMoves;
import au.com.aitcollaboration.chessgame.model.pieces.Piece;
import au.com.aitcollaboration.chessgame.model.pieces.Pieces;
import org.apache.commons.lang3.time.StopWatch;

import java.util.ArrayList;
import java.util.List;

public abstract class Player {

    protected String name;
    protected StopWatch stopWatch;
    protected Pieces pieces;
    protected List<PlayerMoves> moves;

    private Player() {
        this.stopWatch = new StopWatch();
        this.moves = new ArrayList<>();
    }

    protected Player(String name) {
        this();
        this.name = name;
        stopWatchSetup();
    }

    private void stopWatchSetup() {
        stopWatch.start();
        stopWatch.suspend();
    }

    public boolean isOwnPiece(Piece piece) {
        return pieces.contains(piece);
    }

    public void setPieces(Pieces pieces) {
        this.pieces = pieces;
    }

    @Override
    public String toString() {
        return "Player " + pieces + ": " + name;
    }

    public abstract void play(GameController gameController, RulesController rulesController);
}
