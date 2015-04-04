package au.com.aitcollaboration.chessgame.pieces.moving;

import au.com.aitcollaboration.chessgame.board.Board;
import au.com.aitcollaboration.chessgame.board.Position;
import au.com.aitcollaboration.chessgame.board.Square;
import au.com.aitcollaboration.chessgame.pieces.Piece;
import au.com.aitcollaboration.chessgame.pieces.ValidMoves;
import au.com.aitcollaboration.chessgame.player.Color;

public class IncrementalMovement implements MovingBehaviour {

    @Override
    public ValidMoves getMoves(Board board, Piece piece) {
        ValidMoves validMoves = new ValidMoves();

        Square square = board.getSquareForPiece(piece);

        int[][] commonMoves = piece.commonMoves();

        for (int[] moveAt : commonMoves) {
            int myX = 0;
            int myY = 0;

            while (true) {
                myX += moveAt[0];
                myY += moveAt[1];

                Position position = square.nextPosition(myX, myY);

                Square nextSquare = board.getSquareAtPosition(position);

                Color pieceColor = piece.getColor();

                if (nextSquare == null || nextSquare.containsSamePieceColor(pieceColor))
                    break;

                validMoves.add(nextSquare);

                if (nextSquare.containsOpponentPieceColor(pieceColor))
                    break;
            }
        }
        return validMoves;
    }
}
