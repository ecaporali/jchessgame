package au.com.aitcollaboration.chessgame.pieces;

import au.com.aitcollaboration.chessgame.board.Board;
import au.com.aitcollaboration.chessgame.board.Position;
import au.com.aitcollaboration.chessgame.board.Square;
import au.com.aitcollaboration.chessgame.player.Color;

public class King extends Piece {

    public King(Color color) {
        super(color);
    }

    @Override
    public int[][] commonMoves() {
        return new int[][]{
                {1, 1}, {1, -1}, {-1, -1}, {-1, 1}, {1, 0}, {0, 1}, {-1, 0}, {0, -1}
        };
    }

    @Override
    public void validMovesOn(Board board) {
        moves.clear();

        Square square = board.getSquareForPiece(this);

        int[][] validMoves = commonMoves();

        for (int[] moveAt : validMoves) {
            int myX = moveAt[0];
            int myY = moveAt[1];

            Position position = square.nextPosition(myX, myY);

            Square otherSquare = board.getSquareAtPosition(position);


            //shouldn't we check if otherSquare is null or not before using it?
            if (otherSquare.isMoveValid(color, this)) {
                moves.add(otherSquare);
            }
        }
    }
}
