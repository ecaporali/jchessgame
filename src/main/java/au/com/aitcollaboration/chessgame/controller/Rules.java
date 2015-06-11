package au.com.aitcollaboration.chessgame.controller;

import au.com.aitcollaboration.chessgame.exceptions.InvalidMoveException;
import au.com.aitcollaboration.chessgame.exceptions.KingInCheckException;
import au.com.aitcollaboration.chessgame.exceptions.KingInDangerException;
import au.com.aitcollaboration.chessgame.exceptions.PieceCannotBeMovedException;
import au.com.aitcollaboration.chessgame.model.game.structure.Board;
import au.com.aitcollaboration.chessgame.model.game.structure.Square;
import au.com.aitcollaboration.chessgame.model.moves.PieceMoves;
import au.com.aitcollaboration.chessgame.model.pieces.Piece;

public class Rules {

    public boolean isCheckMate(Board board) {
        return false;
    }

    public boolean isMatchDraw(Board board) {
        return false;
    }

    public PieceMoves getValidatedPieceMoves(Square fromSquare, Board board) throws InvalidMoveException {
        Piece currentPiece = fromSquare.getPiece();

        PieceMoves currentPieceMoves = currentPiece.getValidMovesOn(board);

        try {
            return currentPieceMoves.getValidatedMoves(board);
        } catch (KingInDangerException | KingInCheckException | PieceCannotBeMovedException e) {
            throw e;
        }
    }
}
