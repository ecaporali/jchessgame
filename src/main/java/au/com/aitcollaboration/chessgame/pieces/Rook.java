package au.com.aitcollaboration.chessgame.pieces;

import au.com.aitcollaboration.chessgame.player.Color;

public class Rook extends Piece {

    public Rook(Color color) {
        super(color);
    }

    @Override
    public int[][] validMoves() {
        return new int[0][];
    }
}
