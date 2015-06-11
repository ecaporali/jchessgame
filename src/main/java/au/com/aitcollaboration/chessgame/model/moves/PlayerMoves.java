package au.com.aitcollaboration.chessgame.model.moves;

import au.com.aitcollaboration.chessgame.model.game.structure.Square;
import au.com.aitcollaboration.chessgame.model.pieces.Piece;

import java.util.HashMap;
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
        for(PieceMoves pieceMoves : pieceMovesMap.values())
            if(pieceMoves.contains(square))
                return true;

        return false;
//        PieceMoves pieceMoves = pieceMovesMap.get(square.getPiece());
//        return pieceMoves.contains(square)
    }
}
