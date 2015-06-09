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

//    public boolean isKingInDanger(Square fromSquare, Square kingSquare) {
//        for (PieceMoves pieceMoves : pieceMovesMap.values())
//            if (pieceMoves.isKingInDanger(fromSquare, kingSquare))
//                return true;
//
//        return false;
//    }
//
//    public boolean isKingInCheck(Square kingSquare) {
//        for (PieceMoves pieceMoves : pieceMovesMap.values())
//            if (pieceMoves.isKingInCheck(kingSquare))
//                return true;
//
//        return false;
//    }

//    public boolean hasEmptyMoveFor(Piece piece) {
//        return pieceMovesMap.get(piece) == null;
//    }
//
//    public PieceMoves getPieceMoves(Piece piece) {
//        return pieceMovesMap.get(piece);
//    }
//
//    public boolean canEatKing(PieceMoves currentPieceMoves) {
//        for (PieceMoves pieceMoves : pieceMovesMap.values())
//            if (currentPieceMoves.contains(pieceMoves.getCurrentSquare()) || currentPieceMoves.canBeSacrificed(pieceMoves))
//                return false;
//
//        return true;
//    }
}
