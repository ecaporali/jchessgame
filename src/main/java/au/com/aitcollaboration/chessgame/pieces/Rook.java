package au.com.aitcollaboration.chessgame.pieces;

import au.com.aitcollaboration.chessgame.board.Board;
import au.com.aitcollaboration.chessgame.board.Position;
import au.com.aitcollaboration.chessgame.board.Square;
import au.com.aitcollaboration.chessgame.player.Color;

public class Rook extends Piece {

    public Rook(Color color) {
        super(color);
    }

    @Override
    public int[][] commonMoves() {
        return new int[][]{
                {1, 0}, {-1, 0}, {0, 1}, {0, -1}
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
            while (true) {
                Position position = square.nextPosition(myX, myY);

                Square otherSquare = board.getSquareAtPosition(position);

                if(otherSquare != null) {
                    if (otherSquare.isMoveValid(color)) {
                        moves.add(otherSquare);
                    }
                    myX += moveAt[0];
                    myY += moveAt[1];
                }
                else{
                    break;
                }
            }
        }
    }
}
