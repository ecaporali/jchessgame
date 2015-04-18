package au.com.aitcollaboration.chessgame.pieces;

import au.com.aitcollaboration.chessgame.board.Square;

import java.util.HashMap;
import java.util.Map;

public class PlayerMoves {

    private Map<Piece, PieceMoves> pieceMoveMap;

    public PlayerMoves() {
        pieceMoveMap = new HashMap<Piece, PieceMoves>();
    }

    public void add(Piece piece, PieceMoves pieceMoves) {
        pieceMoveMap.put(piece, pieceMoves);
    }

    public boolean isKingInDanger(Square fromSquare, Square kingSquare) {
        for (PieceMoves pieceMoves : pieceMoveMap.values()) {
            if (pieceMoves.isKingInDanger(fromSquare, kingSquare)) {
                return true;
            }
        }
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
}
