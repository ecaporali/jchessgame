package au.com.aitcollaboration.chessgame.pieces;

import au.com.aitcollaboration.chessgame.board.Board;
import au.com.aitcollaboration.chessgame.player.Color;

public class Pawn extends Piece {

    public Pawn(Color color) {
        super(color);
    }

    @Override
    public int[][] commonMoves() {
        return (this.color.equals(Color.BLACK))
                ? new int[][]{{0, 1}, {1, 1}, {-1, 1}}
                : new int[][]{{0, -1}, {1, -1}, {-1, -1}};
    }

    @Override
    public void validMovesOn(Board board) {

    }
}
