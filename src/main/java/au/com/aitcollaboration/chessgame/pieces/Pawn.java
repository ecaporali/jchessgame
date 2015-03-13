package au.com.aitcollaboration.chessgame.pieces;

import au.com.aitcollaboration.chessgame.board.Board;
import au.com.aitcollaboration.chessgame.player.Color;

public class Pawn extends Piece {

    public Pawn(Color color) {
        super(color);
    }

    @Override
    public int[][] validMoves() {
        int[][] moves;

        if(this.color.getPosition() == 0){
            moves = new int[][]{
                    {0, 1}
            };
        }
        else{
            moves = new int[][]{
                    {0, -1}
            };
        }
        return moves;
    }

    @Override
    public int[][] moveOn(Board board) {
        return new int[0][];
    }


}
