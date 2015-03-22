package au.com.aitcollaboration.chessgame.pieces;

import au.com.aitcollaboration.chessgame.board.Board;
import au.com.aitcollaboration.chessgame.board.Position;
import au.com.aitcollaboration.chessgame.board.Square;
import au.com.aitcollaboration.chessgame.player.Color;

public class Pawn extends Piece {

    public Pawn(Color color) {
        super(color);
    }

    @Override
    public int[][] commonMoves() {
        return (this.color.equals(Color.BLACK))
                ? new int[][]{{0, 1}}
                : new int[][]{{0, -1}};
    }

    public int[][] eatingMoves() {
        return (this.color.equals(Color.BLACK))
                ? new int[][]{{1, 1}, {-1, 1}}
                : new int[][]{{1, -1}, {-1, -1}};
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

            if (otherSquare.isPawnMoveValid()) {
                moves.add(otherSquare);
            }
        }

        int[][] eatingMoves = eatingMoves();

        for (int[] moveAt : eatingMoves) {
            int myX = moveAt[0];
            int myY = moveAt[1];

            Position position = square.nextPosition(myX,myY);

            Square otherSquare = board.getSquareAtPosition(position);

            if (otherSquare.canPawnEat(color)) {
                moves.add(otherSquare);
            }
        }
    }
}
