package au.com.aitcollaboration.chessgame.player;

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

    protected Player(String name, Color color) {
        this.name = name;
        this.color = color;
        this.stopWatch = new StopWatch();
        this.moves = new ArrayList<Moves>();
        this.pieces = new Pieces(color);

        System.out.println(this.name + " " + this.color);
    }
}
