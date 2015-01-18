package au.com.aitcollaboration.chessgame.pieces;

import au.com.aitcollaboration.chessgame.player.Color;

/**
 * Created by Massimo on 18/01/2015.
 */
public abstract class Piece {

    protected Color color;
    protected Moves moves;

    protected Piece(Color color) {
        this.color = color;
        this.moves = new Moves();
    }
}
