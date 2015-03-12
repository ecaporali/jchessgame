package au.com.aitcollaboration.chessgame.player;

import au.com.aitcollaboration.chessgame.board.Board;
import au.com.aitcollaboration.chessgame.pieces.Moves;
import au.com.aitcollaboration.chessgame.pieces.Pieces;
import org.apache.commons.lang3.time.StopWatch;

import java.util.ArrayList;
import java.util.List;

public abstract class Player {

    protected String name;
    protected StopWatch stopWatch;
    protected Color color;
    protected Pieces pieces;
    protected List<Moves> moves;

    private Player() {
        this.stopWatch = new StopWatch();
        this.moves = new ArrayList<Moves>();
        this.pieces = new Pieces(color);
    }

    protected Player(String name, Color color) {
        this();
        this.name = name;
        this.color = color;

        System.out.println(this.name + " " + this.color);
    }

    protected void play() {

    }

    public void positionPiecesTo(Board board){
        board.positionPieces();
    }

    public void addPiecesTo(Board board) {
        board.add(pieces);
    }

}
