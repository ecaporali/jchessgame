package au.com.aitcollaboration.chessgame.service;

import au.com.aitcollaboration.chessgame.model.game.structure.Square;
import au.com.aitcollaboration.chessgame.model.moves.PieceMoves;
import au.com.aitcollaboration.chessgame.model.moves.PlayerMoves;
import au.com.aitcollaboration.chessgame.model.pieces.Piece;
import au.com.aitcollaboration.chessgame.model.pieces.Pieces;
import au.com.aitcollaboration.chessgame.view.exceptions.InvalidPieceException;
import au.com.aitcollaboration.chessgame.view.exceptions.KingInCheckException;
import au.com.aitcollaboration.chessgame.view.exceptions.KingInDangerException;
import au.com.aitcollaboration.chessgame.view.exceptions.PieceCannotBeMovedException;

import java.util.Collection;

public class ValidationService {

    public void validateMove(Square fromSquare, Square kingSquare, Pieces pieces, Collection<PlayerMoves> opponentMoves, PieceMoves currentPieceMoves) throws Exception {
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

    private boolean isKingInDanger(Square fromSquare, Square kingSquare, Collection<PlayerMoves> opponentMoves) {
        for (PlayerMoves opponentPlayerMoves : opponentMoves)
            if (opponentPlayerMoves.isKingInDanger(fromSquare, kingSquare))
                return true;

        return false;
    }

    private boolean isKingInCheck(Square kingSquare, Collection<PlayerMoves> opponentMoves) {
        for (PlayerMoves opponentPlayerMoves : opponentMoves)
            if (opponentPlayerMoves.isKingInCheck(kingSquare))
                return true;

        return false;
    }

    private boolean kingCannotBeSaved(Square fromSquare, Square kingSquare, PieceMoves currentPieceMoves, Collection<PlayerMoves> opponentMoves) {
        if (fromSquare.equals(kingSquare))
            return false;


        for (PlayerMoves opponentPlayerMoves : opponentMoves)
            if (opponentPlayerMoves.canEatKing(currentPieceMoves))
                return true;

        return false;
    }
}
