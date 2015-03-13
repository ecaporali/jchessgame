package au.com.aitcollaboration.chessgame.pieces;

import au.com.aitcollaboration.chessgame.board.Board;
import au.com.aitcollaboration.chessgame.board.Square;
import au.com.aitcollaboration.chessgame.player.Color;

public class King extends Piece {

    public King(Color color) {
        super(color);
    }

    @Override
    public int[][] validMoves() {
        return new int[][]{
                {1, 1}, {1, -1}, {-1, -1}, {-1, 1}, {1, 0}, {0, 1}, {-1, 0}, {0, -1}
        };
    }

    @Override
    public int[][] moveOn(Board board) {
        Square square = board.getSquare(this);

        int[][] validMoves = this.validMoves();

        for (int row = 0; row < validMoves.length; row++){
            int myX = validMoves[row][0];
            int myY = validMoves[row][1];

            int incrementedX = square.incrementX(myX);
            int incrementedY = square.incrementY(myY);
            if(board.getPieceAt(incrementedX, incrementedY)){

            }
            else if()
        }
    }
}
