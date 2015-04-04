package au.com.aitcollaboration.chessgame.pieces;

import au.com.aitcollaboration.chessgame.pieces.moving.RangeMovement;
import au.com.aitcollaboration.chessgame.player.Color;

public class King extends Piece {

    public King(Color color) {
        super(color, new RangeMovement());
    }

    @Override
    public int[][] commonMoves() {
        return new int[][]{
                {1, 1}, {1, -1}, {-1, -1}, {-1, 1}, {1, 0}, {0, 1}, {-1, 0}, {0, -1}
        };
    }

    @Override
    public String toString() {
        return "K";
    }
}
