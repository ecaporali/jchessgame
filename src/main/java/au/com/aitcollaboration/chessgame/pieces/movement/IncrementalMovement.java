package au.com.aitcollaboration.chessgame.pieces.movement;

import au.com.aitcollaboration.chessgame.board.Board;
import au.com.aitcollaboration.chessgame.board.Position;
import au.com.aitcollaboration.chessgame.board.Square;
import au.com.aitcollaboration.chessgame.pieces.Piece;
import au.com.aitcollaboration.chessgame.pieces.PracticalMoves;
import au.com.aitcollaboration.chessgame.player.Color;

public class IncrementalMovement implements MovingBehaviour {

    @Override
    public PracticalMoves getMoves(Board board, Piece piece) {
        PracticalMoves practicalMoves = new PracticalMoves();

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

                practicalMoves.add(nextSquare);

                if (nextSquare.containsOpponentPieceColor(pieceColor))
                    break;
            }
        }
        return practicalMoves;
    }
}
