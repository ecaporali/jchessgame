package au.com.aitcollaboration.chessgame.model.pieces.movement;

import au.com.aitcollaboration.chessgame.model.board.Board;
import au.com.aitcollaboration.chessgame.model.board.Position;
import au.com.aitcollaboration.chessgame.model.board.Square;
import au.com.aitcollaboration.chessgame.model.pieces.Piece;
import au.com.aitcollaboration.chessgame.model.pieces.PieceMoves;
import au.com.aitcollaboration.chessgame.model.player.Color;

public class IncrementalMovement implements MovingBehaviour {

    @Override
    public PieceMoves getMoves(Board board, Piece piece) {
        Square square = board.getSquareOf(piece);

        PieceMoves pieceMoves = new PieceMoves(square);

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

                pieceMoves.add(nextSquare);

                if (nextSquare.containsOpponentPieceColor(pieceColor))
                    break;
            }
        }
        return pieceMoves;
    }
}
