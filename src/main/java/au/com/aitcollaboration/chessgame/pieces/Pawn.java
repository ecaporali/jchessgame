package au.com.aitcollaboration.chessgame.pieces;

import au.com.aitcollaboration.chessgame.player.Color;

public class Pawn extends Piece {

    public Pawn(Color color) {
        super(color);
    }

    @Override
    public int[][] validMoves() {
        return new int[0][];
    }
}
