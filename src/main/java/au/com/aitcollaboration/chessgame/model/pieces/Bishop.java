package au.com.aitcollaboration.chessgame.model.pieces;

import au.com.aitcollaboration.chessgame.model.pieces.movement.IncrementalMovement;
import au.com.aitcollaboration.chessgame.model.player.Color;

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
