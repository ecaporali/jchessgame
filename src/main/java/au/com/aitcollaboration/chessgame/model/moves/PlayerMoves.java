package au.com.aitcollaboration.chessgame.model.moves;

import au.com.aitcollaboration.chessgame.Color;
import au.com.aitcollaboration.chessgame.exceptions.InvalidPieceException;
import au.com.aitcollaboration.chessgame.exceptions.PieceCannotBeMovedException;
import au.com.aitcollaboration.chessgame.model.game.structure.Board;
import au.com.aitcollaboration.chessgame.model.game.structure.Square;
import au.com.aitcollaboration.chessgame.model.pieces.Piece;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PlayerMoves {

    private final Map<Piece, PieceMoves> pieceMovesMap;

    public PlayerMoves() {
        pieceMovesMap = new HashMap<>();
    }

    public void add(Piece piece, PieceMoves pieceMoves) {
        pieceMovesMap.put(piece, pieceMoves);
    }

    public boolean contains(Square square) {
        for (PieceMoves pieceMoves : pieceMovesMap.values())
            if (pieceMoves.contains(square))
                return true;

        return false;
    }

    public PieceMoves getValidPieceMovesFor(Piece piece) {
        return pieceMovesMap.get(piece);
    }

    public PieceMoves calculateValidPieceMoves(Piece piece, Board board, PlayerMoves opponentInitialMoves) throws InvalidPieceException,
            PieceCannotBeMovedException {

        if (this.isNotOwn(piece))
            throw new InvalidPieceException();

        PieceMoves currentPieceMoves = this.getValidPieceMovesFor(piece);

        if (currentPieceMoves.isEmpty())
            throw new PieceCannotBeMovedException();

        Color color = piece.getColor();
        Square kingSquare = board.getCurrentKingSquare(color);

        currentPieceMoves.toValidMoves(board, kingSquare, opponentInitialMoves);

        return currentPieceMoves;
    }

    private boolean isNotOwn(Piece piece) {
        return !pieceMovesMap.containsKey(piece);
    }

    public boolean hasEmptyMoves(Board board, Color color, PlayerMoves opponentInitialMoves) {
        List<PieceMoves> possibleMovesList = new ArrayList<>();
        Square kingSquare = board.getCurrentKingSquare(color);

        for (PieceMoves pieceMoves : pieceMovesMap.values()) {
            pieceMoves.toValidMoves(board, kingSquare, opponentInitialMoves);

            if (!pieceMoves.isEmpty())
                possibleMovesList.add(pieceMoves);
        }
        return possibleMovesList.isEmpty();
    }

    public int size() {
        int pieceMovesSize = 0;
        for (PieceMoves pieceMoves : pieceMovesMap.values())
            if(!pieceMoves.isEmpty())
                pieceMovesSize++;

        return pieceMovesSize;
    }

    public List<Square> getDangeringKingSquare(Square kingSquare) {
        List<Square> kingInCheckList = new ArrayList<>();
        for (PieceMoves pieceMoves : pieceMovesMap.values())
            if (pieceMoves.contains(kingSquare))
                kingInCheckList.add(pieceMoves.getCurrentSquare());

        return kingInCheckList;
    }
}
