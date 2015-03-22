package au.com.aitcollaboration.chessgame.pieces;

import au.com.aitcollaboration.chessgame.board.Board;
import au.com.aitcollaboration.chessgame.board.Position;
import au.com.aitcollaboration.chessgame.board.Square;
import au.com.aitcollaboration.chessgame.player.Color;

public class Knight extends Piece {

    public Knight(Color color) {
        super(color);
    }

    @Override
    public int[][] commonMoves() {
        return new int[][]{
                {2, 1}, {2, -1}, {-2, 1}, {-2, -1}, {1, 2}, {1, -2}, {-1, -2}, {-1, 2}
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

            if (otherSquare.isMoveValid(color, this)) {
                moves.add(otherSquare);
            }
        }
    }


}
