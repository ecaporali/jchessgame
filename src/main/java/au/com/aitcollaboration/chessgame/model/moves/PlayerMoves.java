package au.com.aitcollaboration.chessgame.model.moves;

import au.com.aitcollaboration.chessgame.Color;
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

    public boolean hasEmptyMoves(Board board, Color color) {
        List<PieceMoves> possibleMovesList = new ArrayList<>();
        Square kingSquare = board.getCurrentKingSquare(color);

        for (PieceMoves pieceMoves : pieceMovesMap.values()) {
            PieceMoves validPieceMoves = pieceMoves.validateMoves(board, kingSquare);

            if (!validPieceMoves.isEmpty())
                possibleMovesList.add(validPieceMoves);
        }
        return possibleMovesList.isEmpty();
    }
}
