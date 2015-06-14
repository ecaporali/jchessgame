package au.com.aitcollaboration.chessgame.controller;

import au.com.aitcollaboration.chessgame.Color;
import au.com.aitcollaboration.chessgame.exceptions.InvalidMoveException;
import au.com.aitcollaboration.chessgame.exceptions.KingInCheckException;
import au.com.aitcollaboration.chessgame.exceptions.KingInDangerException;
import au.com.aitcollaboration.chessgame.exceptions.PieceCannotBeMovedException;
import au.com.aitcollaboration.chessgame.model.game.structure.Board;
import au.com.aitcollaboration.chessgame.model.game.structure.Square;
import au.com.aitcollaboration.chessgame.model.moves.PieceMoves;
import au.com.aitcollaboration.chessgame.model.moves.PlayerMoves;
import au.com.aitcollaboration.chessgame.model.pieces.Piece;
import au.com.aitcollaboration.chessgame.support.Utils;

import java.util.LinkedList;
import java.util.List;

public class Rules {

    private static final int MATCH_DRAW_COUNTER = 11;

    private List<Board> boardHistory;

    public Rules() {
        this.boardHistory = new LinkedList<>();
    }

    public boolean isCheckMate(Board board, Color color) {
        PlayerMoves playerMoves = board.calculateCurrentPlayerMoves(color);
        return playerMoves.hasEmptyMoves(board, color);
    }

    public boolean isMatchDraw() {
        int boardSize = boardHistory.size();

        if (boardSize < MATCH_DRAW_COUNTER)
            return false;

        int tenthLastBoardPos = boardSize - MATCH_DRAW_COUNTER;

        Board board = boardHistory.get(tenthLastBoardPos);

        return board.isEitherKingLastPieceStanding();
    }

    public PieceMoves validatePieceMoves(Square fromSquare, Board board) throws InvalidMoveException {
        Piece currentPiece = fromSquare.getPiece();

        PieceMoves currentPieceMoves = currentPiece.getValidMovesOn(board);

        if (currentPieceMoves.isEmpty())
            throw new PieceCannotBeMovedException();

        Color color = currentPiece.getColor();
        Square kingSquare = board.getCurrentKingSquare(color);

        PieceMoves validPieceMoves = currentPieceMoves.validateMoves(board, kingSquare);

        PlayerMoves opponentMoves = board.calculateOpponentPlayerMoves(color);

        if (validPieceMoves.isEmpty()) {
            if (opponentMoves.contains(kingSquare))
                throw new KingInCheckException();
            else
                throw new KingInDangerException();
        }

        return validPieceMoves;
    }

    public void addToHistory(Board board) {
        Board clonedBoard = Utils.deepCopyOf(board);
        boardHistory.add(clonedBoard);
    }
}
