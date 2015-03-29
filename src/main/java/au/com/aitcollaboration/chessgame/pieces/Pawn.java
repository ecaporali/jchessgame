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
                ? new int[][]{{0, 1}, {1, 1}, {-1, 1}}
                : new int[][]{{0, -1}, {1, -1}, {-1, -1}};
    }

    @Override
    public void validMovesOn(Board board) {
        moves.clear();

        Square square = board.getSquareForPiece(this);

        int[][] validMoves = commonMoves();
        int[] firstMoves = validMoves[0];

        for (int[] moveAt : validMoves) {
            int myX = moveAt[0];
            int myY = moveAt[1];

            Position position = square.nextPosition(myX, myY);

            Square nextSquare = board.getSquareAtPosition(position);

            if (nextSquare != null) {
                if (firstMoves == moveAt) {
                    if (nextSquare.hasPiece()) {
                        moves.add(nextSquare);
                    }
                } else {
                    if (!nextSquare.hasPiece() && nextSquare.containsSamePiece(color)) {
                        moves.add(nextSquare);
                    }
                }
            }
        }
    }
}
