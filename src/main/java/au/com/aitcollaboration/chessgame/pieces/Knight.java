package au.com.aitcollaboration.chessgame.pieces;

import au.com.aitcollaboration.chessgame.pieces.moving.RangeMovement;
import au.com.aitcollaboration.chessgame.player.Color;

public class Knight extends Piece {

    public Knight(Color color) {
        super(color, new RangeMovement());
    }

    @Override
    public int[][] commonMoves() {
        return new int[][]{
                {2, 1}, {2, -1}, {-2, 1}, {-2, -1}, {1, 2}, {1, -2}, {-1, -2}, {-1, 2}
        };
    }

    @Override
    public String toString() {
        return "N";
    }
}
