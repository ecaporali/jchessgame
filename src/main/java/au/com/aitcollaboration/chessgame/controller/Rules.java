package au.com.aitcollaboration.chessgame.controller;

import au.com.aitcollaboration.chessgame.model.game.structure.Board;
import au.com.aitcollaboration.chessgame.model.game.structure.Square;
import au.com.aitcollaboration.chessgame.view.exceptions.InvalidPieceException;
import au.com.aitcollaboration.chessgame.view.exceptions.KingInCheckException;
import au.com.aitcollaboration.chessgame.view.exceptions.KingInDangerException;
import au.com.aitcollaboration.chessgame.view.exceptions.PieceCannotBeMovedException;
import au.com.aitcollaboration.chessgame.model.pieces.Piece;
import au.com.aitcollaboration.chessgame.model.moves.PieceMoves;
import au.com.aitcollaboration.chessgame.model.pieces.Pieces;
import au.com.aitcollaboration.chessgame.model.moves.PlayerMoves;
import au.com.aitcollaboration.chessgame.Color;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Rules {

    private Map<Pieces, PlayerMoves> possibleMoves;
    private MoveValidation moveValidation;

    public Rules() {
        this.possibleMoves = new HashMap<Pieces, PlayerMoves>();
        this.moveValidation = new MoveValidation();
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

        Map<Color, Pieces> piecesMap = board.getPiecesMap();
        Pieces whitePieces = piecesMap.get(Color.WHITE);
        Pieces blackPieces = piecesMap.get(Color.BLACK);

        for (Square[] squares : grid) {
            PlayerMoves playerMoves = new PlayerMoves();
            for (Square square : squares) {
                if (square.hasPiece()) {
                    Piece piece = square.getPiece();
                    PieceMoves pieceMoves = piece.getValidMovesOn(board);
                    playerMoves.add(piece, pieceMoves);
                    if (piece.matches(Color.WHITE))
                        possibleMoves.put(whitePieces, playerMoves);
                    else
                        possibleMoves.put(blackPieces, playerMoves);
                }
            }
        }
    }

    public PlayerMoves getPlayerMoves(Pieces pieces) {
        return possibleMoves.get(pieces);
    }

    public void validatePieceMove(Square fromSquare, Board board, Pieces pieces) throws PieceCannotBeMovedException, InvalidPieceException, KingInDangerException, KingInCheckException {
        Piece king = pieces.getKing();
        Square kingSquare = board.getSquareOf(king);

        Piece currentPiece = fromSquare.getPiece();

        PieceMoves currentPieceMoves = currentPiece.getValidMovesOn(board);

        Collection<PlayerMoves> opponentMoves = getOpponentMoves(pieces);

        if (currentPieceMoves.isEmpty())
            throw new PieceCannotBeMovedException();

        if (!pieces.contains(currentPiece))
            throw new InvalidPieceException();

        if (isKingInDanger(fromSquare, kingSquare, opponentMoves))
            throw new KingInDangerException();

        if (isKingInCheck(kingSquare, opponentMoves)) {
            if (kingCannotBeSaved(fromSquare, kingSquare, currentPieceMoves, opponentMoves)) {
                throw new KingInCheckException();
            }
        }

        PlayerMoves playerMoves = possibleMoves.get(pieces);
        playerMoves.add(currentPiece, currentPieceMoves);
    }

    private boolean kingCannotBeSaved(Square fromSquare, Square kingSquare, PieceMoves currentPieceMoves, Collection<PlayerMoves> opponentMoves) {
        if (fromSquare.equals(kingSquare))
            return false;



        for (PlayerMoves opponentPlayerMoves : opponentMoves)
            if (opponentPlayerMoves.canEatKing(currentPieceMoves))
                return true;

        return false;
    }


    public void runAllPossibleMoves(Square fromSquare, Board board) {
        Piece currentPiece = fromSquare.getPiece();
        fromSquare.setPiece(null);
        findAllPossibleMovesOn(board);
        fromSquare.setPiece(currentPiece);
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

    public Collection<PlayerMoves> getOpponentMoves(Pieces pieces) {
        Map<Pieces, PlayerMoves> playerMovesMap = new HashMap<Pieces, PlayerMoves>(possibleMoves);
        playerMovesMap.remove(pieces);
        return playerMovesMap.values();
    }
}
