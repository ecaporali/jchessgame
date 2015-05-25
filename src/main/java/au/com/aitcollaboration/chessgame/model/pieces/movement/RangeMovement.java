package au.com.aitcollaboration.chessgame.model.pieces.movement;

import au.com.aitcollaboration.chessgame.model.game.structure.Board;
import au.com.aitcollaboration.chessgame.model.game.structure.Position;
import au.com.aitcollaboration.chessgame.model.game.structure.Square;
import au.com.aitcollaboration.chessgame.model.pieces.Piece;
import au.com.aitcollaboration.chessgame.model.moves.PieceMoves;
import au.com.aitcollaboration.chessgame.Color;

public class RangeMovement implements MovingBehaviour {

    @Override
    public PieceMoves getMoves(Board board, Piece piece) {
        Square square = board.getCurrentSquare(piece);

        PieceMoves pieceMoves = new PieceMoves(square);

        if(square == null)
            return pieceMoves;

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
