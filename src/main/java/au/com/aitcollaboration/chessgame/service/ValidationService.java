package au.com.aitcollaboration.chessgame.service;

import au.com.aitcollaboration.chessgame.Color;
import au.com.aitcollaboration.chessgame.exceptions.*;
import au.com.aitcollaboration.chessgame.model.game.structure.Board;
import au.com.aitcollaboration.chessgame.model.game.structure.Square;
import au.com.aitcollaboration.chessgame.model.moves.PieceMoves;
import au.com.aitcollaboration.chessgame.model.moves.PlayerMoves;
import au.com.aitcollaboration.chessgame.model.pieces.Piece;
import au.com.aitcollaboration.chessgame.model.pieces.Pieces;

import java.util.List;

public class ValidationService {

    public PieceMoves validateMove(Square fromSquare, Board board, Pieces pieces)
            throws PieceCannotBeMovedException, InvalidPieceException, PieceNotFoundException, KingInDangerException, KingInCheckException {

        Piece currentPiece = fromSquare.getPiece();

        if (currentPiece == null)
            throw new PieceNotFoundException();


        PieceMoves currentPieceMoves = currentPiece.getValidMovesOn(board);

        PieceMoves validPieceMoves = getValidPieceMoves(fromSquare, board, currentPieceMoves);


//        currentPieceMoves.getValidMoves(currentPiece.getColor(), board);


//        PieceMoves validPieceMoves = currentPiece.getValidMoves(board);

        if (!pieces.contains(currentPiece))
            throw new InvalidPieceException();

        if (validPieceMoves.isEmpty())
            throw new PieceCannotBeMovedException();

        return validPieceMoves;
    }

    PieceMoves getValidPieceMoves(Square currentSquare, Board board, PieceMoves currentPieceMoves)
            throws KingInDangerException, KingInCheckException {

        PieceMoves validPieceMove = new PieceMoves(currentSquare);

        List<Square> practicalMoves = currentPieceMoves.getPracticalMoves();

        Color color = currentSquare.getPiece().getColor();

        PlayerMoves beforeOpponentMoves = board.getOpponentPossibleMoves(color.flip());

        Square kingSquare = board.getCurrentKingSquare(color);

        Piece king = kingSquare.getPiece();

        boolean kingInCheck = false;

        if (beforeOpponentMoves.contains(kingSquare))
            kingInCheck = true;

        int kingInDangerCounter = 0;

        for (Square possibleToSquare : practicalMoves) {

            Piece toPiece = possibleToSquare.getPiece();

            board.mockMovePiece(currentSquare, possibleToSquare);

            if (!possibleToSquare.contains(king))
                kingSquare = board.getCurrentKingSquare(color);

            PlayerMoves afterOpponentMoves = board.getOpponentPossibleMoves(color.flip());

            board.undoMockMovePiece(currentSquare, possibleToSquare, toPiece);

            if (!afterOpponentMoves.contains(kingSquare))
                validPieceMove.add(possibleToSquare);
            else
                kingInDangerCounter++;
        }

        if (!practicalMoves.isEmpty() && kingInDangerCounter == practicalMoves.size()) {
            if (kingInCheck)
                throw new KingInCheckException();
            else
                throw new KingInDangerException();
        }

        return validPieceMove;
    }
}
