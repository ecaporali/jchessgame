package au.com.aitcollaboration.chessgame.player;

import au.com.aitcollaboration.chessgame.pieces.ValidMoves;
import au.com.aitcollaboration.chessgame.pieces.Pieces;
import org.apache.commons.lang3.time.StopWatch;

import java.util.ArrayList;
import java.util.List;

public abstract class Player {

    protected String name;
    protected StopWatch stopWatch;
    protected Color color;
    protected Pieces pieces;
    protected List<ValidMoves> moves;

    private Player() {
        this.stopWatch = new StopWatch();
        this.moves = new ArrayList<ValidMoves>();
    }

    protected Player(String name, Color color, Pieces pieces) {
        this();
        this.name = name;
        this.color = color;
        this.pieces = pieces;

        System.out.println("\n" + this.name + " " + this.color);
    }

    protected void play() {

    }

    public void setPieces(Pieces pieces) {
        this.pieces = pieces;
    }
}
