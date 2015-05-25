package au.com.aitcollaboration.chessgame.model.pieces.movement;

import au.com.aitcollaboration.chessgame.model.game.structure.Board;
import au.com.aitcollaboration.chessgame.model.game.structure.Position;
import au.com.aitcollaboration.chessgame.model.game.structure.Square;
import au.com.aitcollaboration.chessgame.model.pieces.Piece;
import au.com.aitcollaboration.chessgame.model.moves.PieceMoves;
import au.com.aitcollaboration.chessgame.Color;

public class SingleMovement implements MovingBehaviour {

    @Override
    public PieceMoves getMoves(Board board, Piece piece) {
        Square square = board.getCurrentSquare(piece);

        PieceMoves pieceMoves = new PieceMoves(square);

        if(square == null)
            return pieceMoves;

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
                    pieceMoves.add(nextSquare);
                }
            } else {
                if (!nextSquare.hasPiece()){
                    pieceMoves.add(nextSquare);
                }
            }
        }
        return pieceMoves;
    }

    private boolean isEatingMove(int[] firstMove, int[] currentMove) {
        return firstMove != currentMove;
    }
}
