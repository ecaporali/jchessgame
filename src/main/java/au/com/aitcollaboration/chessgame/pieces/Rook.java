package au.com.aitcollaboration.chessgame.pieces;

import au.com.aitcollaboration.chessgame.pieces.moving.IncrementalMovement;
import au.com.aitcollaboration.chessgame.player.Color;

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
