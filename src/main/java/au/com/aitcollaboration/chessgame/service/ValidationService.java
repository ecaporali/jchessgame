package au.com.aitcollaboration.chessgame.service;

import au.com.aitcollaboration.chessgame.Color;
import au.com.aitcollaboration.chessgame.exceptions.InvalidPieceException;
import au.com.aitcollaboration.chessgame.exceptions.PieceCannotBeMovedException;
import au.com.aitcollaboration.chessgame.exceptions.PieceNotFoundException;
import au.com.aitcollaboration.chessgame.model.game.structure.Board;
import au.com.aitcollaboration.chessgame.model.game.structure.Square;
import au.com.aitcollaboration.chessgame.model.moves.PieceMoves;
import au.com.aitcollaboration.chessgame.model.moves.PlayerMoves;
import au.com.aitcollaboration.chessgame.model.pieces.Piece;
import au.com.aitcollaboration.chessgame.model.pieces.Pieces;
import au.com.aitcollaboration.chessgame.support.UIMessages;

import java.util.List;

public class ValidationService {

//    public void validateMove(Square fromSquare, Square kingSquare, Pieces pieces, PlayerMoves opponentMoves, PieceMoves currentPieceMoves)
//            throws PieceCannotBeMovedException, InvalidPieceException, KingInDangerException, KingInCheckException {
//        Piece currentPiece = fromSquare.getPiece();
//
//        if (currentPieceMoves.isEmpty())
//            throw new PieceCannotBeMovedException();
//
//        if (!pieces.contains(currentPiece))
//            throw new InvalidPieceException();
//
//        if (isKingInDanger(fromSquare, kingSquare, opponentMoves))
//            throw new KingInDangerException();
//
//        if (isKingInCheck(kingSquare, opponentMoves))
//            if (kingCannotBeSaved(fromSquare, kingSquare, currentPieceMoves, opponentMoves))
//                throw new KingInCheckException();
//    }

    public PieceMoves validateMove(Square fromSquare, Board board, Pieces pieces)
            throws PieceCannotBeMovedException, InvalidPieceException, PieceNotFoundException {

        Piece currentPiece = fromSquare.getPiece();

        if (currentPiece == null)
            throw new PieceNotFoundException();


        PieceMoves currentPieceMoves = currentPiece.getValidMovesOn(board);

        PieceMoves validPieceMoves = getValidPieceMoves (fromSquare, currentPieceMoves, board);


//        currentPieceMoves.getValidMoves(currentPiece.getColor(), board);


//        PieceMoves validPieceMoves = currentPiece.getValidMoves(board);

        if (!pieces.contains(currentPiece))
            throw new InvalidPieceException();

        if (validPieceMoves.isEmpty())
            throw new PieceCannotBeMovedException();

        return validPieceMoves;
    }

    private PieceMoves getValidPieceMoves(Square currentSquare, PieceMoves currentPieceMoves, Board board)
            throws PieceCannotBeMovedException, InvalidPieceException, PieceNotFoundException {

        PieceMoves validPieceMoved = new PieceMoves(currentSquare);

        List<Square> practicalMoves = currentPieceMoves.getPracticalMoves();

        Color color = currentSquare.getPiece().getColor();

        for (Square toSquare : practicalMoves) {

            Piece toPiece = toSquare.getPiece();

            board.movePiece(currentSquare, toSquare);

            PlayerMoves opponentPossibleMoves = board.getOpponentPossibleMoves(color.flip());

            board.undoMovePiece(currentSquare, toSquare, toPiece);

            Square kingSquare = board.getCurrentKingSquare(color);

            if (!opponentPossibleMoves.contains(kingSquare))
                validPieceMoved.add(currentSquare);

        }
        return validPieceMoved;
    }

//    private boolean isKingInDanger(Square fromSquare, Square kingSquare, PlayerMoves opponentMoves) {
//        return opponentMoves.isKingInDanger(fromSquare, kingSquare);
//    }
//
//    private boolean isKingInCheck(Square kingSquare, PlayerMoves opponentMoves) {
//        return opponentMoves.isKingInCheck(kingSquare);
//    }
//
//    private boolean kingCannotBeSaved(Square fromSquare, Square kingSquare, PieceMoves currentPieceMoves, PlayerMoves opponentMoves) {
//        return !fromSquare.equals(kingSquare) &&
//                opponentMoves.canEatKing(currentPieceMoves);
//    }
}
