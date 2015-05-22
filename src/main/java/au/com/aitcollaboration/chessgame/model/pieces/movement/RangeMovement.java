package au.com.aitcollaboration.chessgame.model.pieces.movement;

import au.com.aitcollaboration.chessgame.model.board.Board;
import au.com.aitcollaboration.chessgame.model.board.Position;
import au.com.aitcollaboration.chessgame.model.board.Square;
import au.com.aitcollaboration.chessgame.model.pieces.Piece;
import au.com.aitcollaboration.chessgame.model.pieces.PieceMoves;
import au.com.aitcollaboration.chessgame.model.player.Color;

public class RangeMovement implements MovingBehaviour {

    @Override
    public PieceMoves getMoves(Board board, Piece piece) {
        Square square = board.getSquareOf(piece);

        PieceMoves pieceMoves = new PieceMoves(square);

        int[][] commonMoves = piece.commonMoves();

        for (int[] moveAt : commonMoves) {
            int myX = moveAt[0];
            int myY = moveAt[1];

            Position position = square.nextPosition(myX, myY);

            Square nextSquare = board.getSquareAtPosition(position);

            Color pieceColor = piece.getColor();

            if (nextSquare == null || nextSquare.containsSamePieceColor(pieceColor))
                continue;

            pieceMoves.add(nextSquare);
        }
        return pieceMoves;
    }
}
