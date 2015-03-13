package au.com.aitcollaboration.chessgame.pieces;

import au.com.aitcollaboration.chessgame.board.Board;
import au.com.aitcollaboration.chessgame.player.Color;

public class Bishop extends Piece {

    public Bishop(Color color) {
        super(color);
    }

    @Override
    public int[][] validMoves() {
        return new int[][]{
                {1, 1}, {-1, 1}, {1, -1}, {-1, -1}
        };
    }

    @Override
    public int[][] moveOn(Board board) {
        return new int[0][];


    }


}
