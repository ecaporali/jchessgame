package au.com.aitcollaboration.chessgame.service;

import au.com.aitcollaboration.chessgame.exceptions.InvalidPieceException;
import au.com.aitcollaboration.chessgame.exceptions.KingInCheckException;
import au.com.aitcollaboration.chessgame.exceptions.KingInDangerException;
import au.com.aitcollaboration.chessgame.exceptions.PieceCannotBeMovedException;
import au.com.aitcollaboration.chessgame.model.game.structure.Square;
import au.com.aitcollaboration.chessgame.model.moves.PieceMoves;
import au.com.aitcollaboration.chessgame.model.moves.PlayerMoves;
import au.com.aitcollaboration.chessgame.model.pieces.Piece;
import au.com.aitcollaboration.chessgame.model.pieces.Pieces;

public class ValidationService {

    public void validateMove(Square fromSquare, Square kingSquare, Pieces pieces, PlayerMoves opponentMoves, PieceMoves currentPieceMoves) throws Exception {
        Piece currentPiece = fromSquare.getPiece();

        if (currentPieceMoves.isEmpty())
            throw new PieceCannotBeMovedException();

        if (!pieces.contains(currentPiece))
            throw new InvalidPieceException();

        if (isKingInDanger(fromSquare, kingSquare, opponentMoves))
            throw new KingInDangerException();

        if (isKingInCheck(kingSquare, opponentMoves))
            if (kingCannotBeSaved(fromSquare, kingSquare, currentPieceMoves, opponentMoves))
                throw new KingInCheckException();
    }

    private boolean isKingInDanger(Square fromSquare, Square kingSquare, PlayerMoves opponentMoves) {
        return opponentMoves.isKingInDanger(fromSquare, kingSquare);
    }

    private boolean isKingInCheck(Square kingSquare, PlayerMoves opponentMoves) {
        return opponentMoves.isKingInCheck(kingSquare);
    }

    private boolean kingCannotBeSaved(Square fromSquare, Square kingSquare, PieceMoves currentPieceMoves, PlayerMoves opponentMoves) {
        return !fromSquare.equals(kingSquare) &&
                opponentMoves.canEatKing(currentPieceMoves);
    }
}
