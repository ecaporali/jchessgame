package au.com.aitcollaboration.chessgame.player;

import au.com.aitcollaboration.chessgame.game.Game;
import au.com.aitcollaboration.chessgame.pieces.Piece;
import au.com.aitcollaboration.chessgame.pieces.Pieces;
import au.com.aitcollaboration.chessgame.pieces.PracticalMoves;
import org.apache.commons.lang3.time.StopWatch;

import java.util.ArrayList;
import java.util.List;

public abstract class Player {

    protected String name;
    protected StopWatch stopWatch;
    protected Pieces pieces;
    protected List<PracticalMoves> moves;

    private Player() {
        this.stopWatch = new StopWatch();
        this.moves = new ArrayList<PracticalMoves>();
    }

    protected Player(String name) {
        this();
        this.name = name;
    }

    public boolean isOwnPiece(Piece piece) {
        return pieces.contains(piece);
    }

    public void setPieces(Pieces pieces) {
        this.pieces = pieces;
    }

    @Override
    public String toString() {
        return "Player Name: " + name;
    }

    public Piece getKing(){
        return pieces.getKing();
    }

    public abstract void play(Game game);
}
