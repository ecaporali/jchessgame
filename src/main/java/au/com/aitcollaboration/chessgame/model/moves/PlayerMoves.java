package au.com.aitcollaboration.chessgame.model.moves;

import au.com.aitcollaboration.chessgame.model.game.structure.Square;
import au.com.aitcollaboration.chessgame.model.pieces.Piece;

import java.util.HashMap;
import java.util.Map;

public class PlayerMoves {

    private Map<Piece, PieceMoves> pieceMoveMap;

    public PlayerMoves() {
        pieceMoveMap = new HashMap<>();
    }

    public void add(Piece piece, PieceMoves pieceMoves) {
        pieceMoveMap.put(piece, pieceMoves);
    }

    public boolean isKingInDanger(Square fromSquare, Square kingSquare) {
        for (PieceMoves pieceMoves : pieceMoveMap.values())
            if (pieceMoves.isKingInDanger(fromSquare, kingSquare))
                return true;

        return false;
    }

    public boolean isKingInCheck(Square kingSquare) {
        for (PieceMoves pieceMoves : pieceMoveMap.values())
            if (pieceMoves.isKingInCheck(kingSquare))
                return true;

        return false;
    }

    public boolean contains(Square square) {
        PieceMoves pieceMoves = pieceMoveMap.get(square.getPiece());
        return pieceMoves.contains(square);
    }

    public boolean hasEmptyMoveFor(Piece piece) {
        return pieceMoveMap.get(piece) == null;
    }

    public PieceMoves getPieceMoves(Piece piece) {
        return pieceMoveMap.get(piece);
    }

    public boolean canEatKing(PieceMoves currentPieceMoves) {
        for (PieceMoves pieceMoves : pieceMoveMap.values())
            if (currentPieceMoves.contains(pieceMoves.getCurrentSquare()) || currentPieceMoves.canBeSacrificed(pieceMoves))
                return false;

        return true;
    }
}
