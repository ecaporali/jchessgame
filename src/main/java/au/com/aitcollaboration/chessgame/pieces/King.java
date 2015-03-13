package au.com.aitcollaboration.chessgame.pieces;

import au.com.aitcollaboration.chessgame.player.Color;

public class King extends Piece {

    public King(Color color) {
        super(color);
    }

    @Override
    public int[][] validMoves() {
        return new int[][]{
                {1, 1}, {1, -1}, {-1, -1}, {-1, 1}, {1, 0}, {0, 1}, {-1, 0}, {0, -1}
        };
    }
}
