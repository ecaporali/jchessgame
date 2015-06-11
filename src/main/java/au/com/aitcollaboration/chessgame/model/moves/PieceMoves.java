package au.com.aitcollaboration.chessgame.model.moves;

import au.com.aitcollaboration.chessgame.Color;
import au.com.aitcollaboration.chessgame.exceptions.KingInCheckException;
import au.com.aitcollaboration.chessgame.exceptions.KingInDangerException;
import au.com.aitcollaboration.chessgame.exceptions.PieceCannotBeMovedException;
import au.com.aitcollaboration.chessgame.model.game.structure.Board;
import au.com.aitcollaboration.chessgame.model.game.structure.Position;
import au.com.aitcollaboration.chessgame.model.game.structure.Square;
import au.com.aitcollaboration.chessgame.model.pieces.Piece;

import java.util.ArrayList;
import java.util.List;

public class PieceMoves {

    private final Square currentSquare;
    private final List<Square> practicalMoves;

    public PieceMoves(Square currentSquare) {
        this.currentSquare = currentSquare;
        this.practicalMoves = new ArrayList<>();
    }

    public void add(Square square) {
        practicalMoves.add(square);
    }

    public boolean isEmpty() {
        return practicalMoves.size() == 0;
    }

    public int size() {
        return practicalMoves.size();
    }

    public boolean contains(Square square) {
        return this.practicalMoves.contains(square);
    }

    public Square getCurrentSquare() {
        return currentSquare;
    }

    @Override
    public String toString() {
        String s = "Valid Moves: ";
        for (Square square : practicalMoves) {
            if (square != null) {
                Position position = square.getPosition();
                s += position.toString();
            }
        }
        return s;
    }

    public PieceMoves getValidatedMoves(Board board) throws KingInCheckException,
            KingInDangerException, PieceCannotBeMovedException {

        PieceMoves validPieceMoves = new PieceMoves(currentSquare);

        if (practicalMoves.isEmpty())
            throw new PieceCannotBeMovedException();

        Color color = currentSquare.getPiece().getColor();

        PlayerMoves beforeOpponentMoves = board.getOpponentPlayerMoves(color.flip());

        Square kingSquare = board.getCurrentKingSquare(color);

        Piece king = kingSquare.getPiece();

        int kingInDangerCounter = 0;

        for (Square possibleToSquare : practicalMoves) {

            Piece toPiece = possibleToSquare.getPiece();

            board.mockMovePiece(currentSquare, possibleToSquare);

            if (possibleToSquare.contains(king))
                kingSquare = possibleToSquare;

            PlayerMoves afterOpponentMoves = board.getOpponentPlayerMoves(color.flip());

            board.undoMockMovePiece(currentSquare, possibleToSquare, toPiece);

            if (!afterOpponentMoves.contains(kingSquare))
                validPieceMoves.add(possibleToSquare);
            else
                kingInDangerCounter++;
        }

        if (!practicalMoves.isEmpty() && kingInDangerCounter == practicalMoves.size()) {
            if (beforeOpponentMoves.contains(kingSquare))
                throw new KingInCheckException();
            else
                throw new KingInDangerException();
        }

        return validPieceMoves;
    }
}
