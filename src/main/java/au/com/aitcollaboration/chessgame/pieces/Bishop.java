package au.com.aitcollaboration.chessgame.pieces;

import au.com.aitcollaboration.chessgame.pieces.movement.IncrementalMovement;
import au.com.aitcollaboration.chessgame.player.Color;

public class Bishop extends Piece {

    public Bishop(Color color) {
        super(color, new IncrementalMovement());
    }

    @Override
    public int[][] commonMoves() {
        return new int[][]{
                {1, 1}, {-1, 1}, {1, -1}, {-1, -1}
        };
    }

    @Override
    public String toString() {
        return "B";
    }
}
