package au.com.aitcollaboration.chessgame.pieces;

import au.com.aitcollaboration.chessgame.board.Board;
import au.com.aitcollaboration.chessgame.board.Position;
import au.com.aitcollaboration.chessgame.board.Square;
import au.com.aitcollaboration.chessgame.player.Color;

public class Bishop extends Piece {

    public Bishop(Color color) {
        super(color);
    }

    @Override
    public int[][] commonMoves() {
        return new int[][]{
                {1, 1}, {-1, 1}, {1, -1}, {-1, -1}
        };
    }

    @Override
    public void validMovesOn(Board board) {
        moves.clear();

        Square square = board.getSquareForPiece(this);

        int[][] validMoves = commonMoves();

        for (int[] moveAt : validMoves) {
            int myX = 0;
            int myY = 0;

            while (true) {
                myX += moveAt[0];
                myY += moveAt[1];

                Position position = square.nextPosition(myX, myY);

                Square nextSquare = board.getSquareAtPosition(position);

                if (nextSquare == null || nextSquare.containsSamePiece(color))
                    break;

                moves.add(nextSquare);

                if(nextSquare.containsOpponentPiece(color))
                    break;
            }
        }
    }
}
