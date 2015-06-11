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

    public PieceMoves getValidatedMoves(Board board, Color color) throws KingInCheckException,
            KingInDangerException, PieceCannotBeMovedException {

        if (practicalMoves.isEmpty())
            throw new PieceCannotBeMovedException();

        PieceMoves validPieceMoves = new PieceMoves(currentSquare);

        PlayerMoves beforeOpponentMoves = board.getOpponentPlayerMoves(color);

        Square kingSquare = board.getCurrentKingSquare(color);

        Piece king = kingSquare.getPiece();

        for (Square toSquare : practicalMoves) {

            PlayerMoves afterOpponentMoves = this.virtualMovePiece(toSquare, board, color);

            if (currentSquare.contains(king))
                kingSquare = toSquare;

            if (afterOpponentMoves.contains(kingSquare))
                continue;

            validPieceMoves.add(toSquare);
        }

        if (validPieceMoves.isEmpty()) {
            //TODO: refactor this method and find new solution
            if (beforeOpponentMoves.contains(kingSquare))
                throw new KingInCheckException();
            else
                throw new KingInDangerException();
        }

        return validPieceMoves;
    }

    public PlayerMoves virtualMovePiece(Square toSquare, Board board, Color color) {
        Piece toPiece = toSquare.getPiece();

        board.mockMovePiece(currentSquare, toSquare);

        PlayerMoves afterOpponentMoves = board.getOpponentPlayerMoves(color);

        board.undoMockMovePiece(currentSquare, toSquare, toPiece);

        return afterOpponentMoves;
    }
}
