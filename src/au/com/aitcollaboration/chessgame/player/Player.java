package au.com.aitcollaboration.chessgame.player;

import au.com.aitcollaboration.chessgame.pieces.Moves;
import au.com.aitcollaboration.chessgame.pieces.Pieces;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;

/**
 * Created by Massimo on 18/01/2015.
 */
public abstract class Player {

    protected String name;
    protected Timer timer;
    protected Color color;
    protected Pieces pieces;
    protected List<Moves> moves;

    protected Player(String name, Color color) {
        this.name = name;
        this.color = color;
        this.timer = new Timer();
        this.moves = new ArrayList<Moves>();
        this.pieces = new Pieces(color);

        System.out.println(this.name + " " + this.color);
    }
}
