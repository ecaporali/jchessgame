package au.com.aitcollaboration.chessgame.game;

import au.com.aitcollaboration.chessgame.board.Board;
import au.com.aitcollaboration.chessgame.board.Square;
import au.com.aitcollaboration.chessgame.exceptions.InvalidPieceException;
import au.com.aitcollaboration.chessgame.exceptions.KingInDangerException;
import au.com.aitcollaboration.chessgame.exceptions.PieceCannotBeMovedException;
import au.com.aitcollaboration.chessgame.pieces.Piece;
import au.com.aitcollaboration.chessgame.pieces.PieceMoves;
import au.com.aitcollaboration.chessgame.pieces.Pieces;
import au.com.aitcollaboration.chessgame.pieces.PlayerMoves;
import au.com.aitcollaboration.chessgame.player.Color;

import java.util.*;

public class Rules {

    private Map<Pieces, PlayerMoves> possibleMoves;

    public Rules() {
        this.possibleMoves = new HashMap<Pieces, PlayerMoves>();
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

    public PlayerMoves getValidPiecesMoves(Pieces pieces) {
        return possibleMoves.get(pieces);
    }

    public void validatePieceMove(Square fromSquare, Board board, Pieces pieces) throws PieceCannotBeMovedException, InvalidPieceException, KingInDangerException {
        Piece king = pieces.getKing();
        Square kingSquare = board.getSquareForPiece(king);

        Piece currentPiece = fromSquare.getPiece();

        PieceMoves pieceMoves = currentPiece.getValidMovesOn(board);

        if (pieceMoves.isEmpty())
            throw new PieceCannotBeMovedException();

        if (!pieces.contains(currentPiece))
            throw new InvalidPieceException();

        if (isKingInDanger(fromSquare, kingSquare, pieces))
            throw new KingInDangerException();

        if (isKingInCheck(fromSquare, kingSquare))
            throw new KingInDangerException();

        PlayerMoves playerMoves = possibleMoves.get(pieces);
        playerMoves.add(currentPiece, pieceMoves);
    }

    public void runAllPossibleMoves(Square fromSquare, Board board) {
        Piece currentPiece = fromSquare.getPiece();
        fromSquare.setPiece(null);
        findAllPossibleMovesOn(board);
        fromSquare.setPiece(currentPiece);
    }

    public boolean isKingInDanger(Square fromSquare, Square kingSquare, Pieces pieces) {
        Collection<PlayerMoves> opponentMoves = getOpponentMoves(pieces);
        for (PlayerMoves playerMoves : opponentMoves) {
            if (playerMoves.isKingInDanger(fromSquare, kingSquare)) {
                return true;
            }
        }
        return false;
    }

    public Collection<PlayerMoves> getOpponentMoves(Pieces pieces){
        Map<Pieces, PlayerMoves> playerMovesMap = new HashMap<Pieces, PlayerMoves>(possibleMoves);
        playerMovesMap.remove(pieces);
        return playerMovesMap.values();
    }


    public boolean isKingInCheck(Square fromSquare, Square kingSquare) {
        //TODO: find out if King is under check or use isKingInDanger
        return false;
    }
}
