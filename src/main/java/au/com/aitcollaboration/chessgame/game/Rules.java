package au.com.aitcollaboration.chessgame.game;

import au.com.aitcollaboration.chessgame.board.Board;
import au.com.aitcollaboration.chessgame.board.Square;
import au.com.aitcollaboration.chessgame.exceptions.InvalidPieceException;
import au.com.aitcollaboration.chessgame.exceptions.KingInDangerException;
import au.com.aitcollaboration.chessgame.exceptions.PieceCannotBeMovedException;
import au.com.aitcollaboration.chessgame.pieces.Piece;
import au.com.aitcollaboration.chessgame.pieces.Pieces;
import au.com.aitcollaboration.chessgame.pieces.PracticalMoves;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Rules {

    private Map<Piece, PracticalMoves> possibleMoves;

    public Rules() {
        this.possibleMoves = new HashMap<Piece, PracticalMoves>();
    }

    public boolean isCheckMate(Board board) {
        return false;
    }

    public boolean isMatchDraw(List<Board> movesHistory) {
        return false;
    }


    public void findAllPossibleMovesOn(Board board) {
        possibleMoves.clear();

        Square[][] grid = board.getClonedGrid();

        for (Square[] squares : grid) {
            for (Square square : squares) {
                if (square.hasPiece()) {
                    Piece piece = square.getPiece();
                    PracticalMoves practicalMoves = piece.getValidMovesOn(board);
                    possibleMoves.put(piece, practicalMoves);
                }
            }
        }
    }

    public PracticalMoves getValidMovesForPiece(Piece piece) {
        return possibleMoves.get(piece);
    }

    public void validatePieceMove(Square fromSquare, Board board, Pieces pieces) throws PieceCannotBeMovedException, InvalidPieceException, KingInDangerException {
        Piece king = pieces.getKing();
        Square kingSquare = board.getSquareForPiece(king);

        Piece currentPiece = fromSquare.getPiece();
        PracticalMoves piecePracticalMoves = currentPiece.getValidMovesOn(board);

        if (piecePracticalMoves.isEmpty())
            throw new PieceCannotBeMovedException();

        if (!pieces.contains(currentPiece))
            throw new InvalidPieceException();

        if (isKingInDanger(fromSquare, kingSquare))
            throw new KingInDangerException();

        if (isKingInCheck(fromSquare, kingSquare))
            throw new KingInDangerException();

        possibleMoves.put(currentPiece, piecePracticalMoves);
    }

    public void runAllPossibleMoves(Square fromSquare, Board board) {
        Piece currentPiece = fromSquare.getPiece();
        fromSquare.setPiece(null);
        findAllPossibleMovesOn(board);
        fromSquare.setPiece(currentPiece);
    }

    public boolean isKingInDanger(Square fromSquare, Square kingSquare) {
        for (PracticalMoves practicalMoves : possibleMoves.values()) {
            if (practicalMoves.isKingInDanger(fromSquare, kingSquare)) {
                return true;
            }
        }
        return false;
    }


    public boolean isKingInCheck(Square fromSquare, Square kingSquare) {
        //TODO: find out if King is under check or use isKingInDanger
        return false;
    }
}
