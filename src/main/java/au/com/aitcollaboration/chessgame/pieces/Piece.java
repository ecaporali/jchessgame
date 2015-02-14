package au.com.aitcollaboration.chessgame.pieces;

import au.com.aitcollaboration.chessgame.player.Color;

public abstract class Piece {

    protected Color color;
    protected Moves moves;

    protected Piece(Color color) {
        this.color = color;

        //TODO Moves must be created every time a new turn is make
        this.moves = new Moves();
    }
}