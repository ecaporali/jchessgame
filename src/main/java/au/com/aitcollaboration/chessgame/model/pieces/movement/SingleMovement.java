package au.com.aitcollaboration.chessgame.model.pieces.movement;

import au.com.aitcollaboration.chessgame.Color;
import au.com.aitcollaboration.chessgame.model.game.structure.Board;
import au.com.aitcollaboration.chessgame.model.game.structure.Position;
import au.com.aitcollaboration.chessgame.model.game.structure.Square;
import au.com.aitcollaboration.chessgame.model.moves.PieceMoves;
import au.com.aitcollaboration.chessgame.model.pieces.Piece;

public class SingleMovement implements MovingBehaviour {

    @Override
    public PieceMoves getMoves(Board board, Piece piece) {
        Square square = board.getCurrentSquare(piece);

        PieceMoves pieceMoves = new PieceMoves(square);

        if (square == null)
            return pieceMoves;

        int[][] commonMoves = piece.commonMoves();
        int[] singleStepMove = commonMoves[0];
        int[] doubleStepMove = commonMoves[1];

        for (int[] currentMove : commonMoves) {
            if (currentMove == doubleStepMove && (pieceHasMoved(square, board) || pieceMoves.isEmpty()))
                continue;

            int myX = currentMove[0];
            int myY = currentMove[1];

            Position position = square.nextPosition(myX, myY);

            Square nextSquare = board.getSquareAtPosition(position);

            Color pieceColor = piece.getColor();

            if (nextSquare == null || nextSquare.containsSamePieceColor(pieceColor))
                continue;

            if (isEatingMove(singleStepMove, doubleStepMove, currentMove)) {
                if (nextSquare.containsOpponentPieceColor(pieceColor))
                    pieceMoves.add(nextSquare);
            } else {
                if (!nextSquare.hasPiece())
                    pieceMoves.add(nextSquare);
            }
        }
        return pieceMoves;
    }

    private boolean isEatingMove(int[] progressMove, int[] firstMove, int[] currentMove) {
        return progressMove != currentMove && firstMove != currentMove;
    }

    private boolean pieceHasMoved(Square square, Board board) {
        return !board.isSecondRank(square);
    }
}
