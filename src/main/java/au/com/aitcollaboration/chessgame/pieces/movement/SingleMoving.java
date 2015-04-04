package au.com.aitcollaboration.chessgame.pieces.movement;

import au.com.aitcollaboration.chessgame.board.Board;
import au.com.aitcollaboration.chessgame.board.Position;
import au.com.aitcollaboration.chessgame.board.Square;
import au.com.aitcollaboration.chessgame.pieces.Piece;
import au.com.aitcollaboration.chessgame.pieces.ValidMoves;
import au.com.aitcollaboration.chessgame.player.Color;

public class SingleMoving implements MovingBehaviour {

    @Override
    public ValidMoves getMoves(Board board, Piece piece) {
        ValidMoves validMoves = new ValidMoves();

        Square square = board.getSquareForPiece(piece);

        int[][] commonMoves = piece.commonMoves();
        int[] firstMove = commonMoves[0];

        for (int[] currentMove : commonMoves) {
            int myX = currentMove[0];
            int myY = currentMove[1];

            Position position = square.nextPosition(myX, myY);

            Square nextSquare = board.getSquareAtPosition(position);

            Color pieceColor = piece.getColor();

            if (nextSquare == null || nextSquare.containsSamePieceColor(pieceColor))
                continue;

            if (isEatingMove(firstMove, currentMove)) {
                if (nextSquare.containsOpponentPieceColor(pieceColor)){
                    validMoves.add(nextSquare);
                }
            } else {
                if (!nextSquare.hasPiece()){
                    validMoves.add(nextSquare);
                }
            }
        }
        return validMoves;
    }

    private boolean isEatingMove(int[] firstMove, int[] currentMove) {
        return firstMove != currentMove;
    }
}
