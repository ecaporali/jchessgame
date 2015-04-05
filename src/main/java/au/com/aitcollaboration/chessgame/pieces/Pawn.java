package au.com.aitcollaboration.chessgame.pieces;

import au.com.aitcollaboration.chessgame.pieces.movement.SingleMovement;
import au.com.aitcollaboration.chessgame.player.Color;

public class Pawn extends Piece {

    public Pawn(Color color) {
        super(color, new SingleMovement());
    }

    @Override
    public int[][] commonMoves() {
        return (this.getColor().equals(Color.BLACK))
                ? new int[][]{{1, 0}, {1, 1}, {1, -1}}
                : new int[][]{{-1, 0}, {1, -1}, {-1, -1}};
    }

    @Override
    public String toString() {
        return "P";
    }
}
