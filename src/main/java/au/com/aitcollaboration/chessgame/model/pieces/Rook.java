package au.com.aitcollaboration.chessgame.model.pieces;

import au.com.aitcollaboration.chessgame.model.pieces.movement.IncrementalMovement;
import au.com.aitcollaboration.chessgame.Color;

public class Rook extends Piece {

    public Rook(Color color) {
        super(color, new IncrementalMovement());
    }

    @Override
    public int[][] commonMoves() {
        return new int[][]{
                {1, 0}, {-1, 0}, {0, 1}, {0, -1}
        };
    }

    @Override
    public String toString() {
        return "R";
    }
}
