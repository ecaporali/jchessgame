package au.com.aitcollaboration.chessgame.controller;

import au.com.aitcollaboration.chessgame.Color;
import au.com.aitcollaboration.chessgame.exceptions.*;
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

    public boolean isCheckMate(Color currentColor, Board board) {
        PlayerMoves currentPlayerMoves = board.calculateCurrentPlayerMoves(currentColor);
        PlayerMoves opponentInitialMoves = board.calculateOpponentPlayerMoves(currentColor);
        return currentPlayerMoves.hasEmptyMoves(board, currentColor, opponentInitialMoves);
    }

    public boolean isMatchDraw() {
        int boardSize = boardHistory.size();

        if (boardSize < MATCH_DRAW_COUNTER)
            return false;

        int tenthLastBoardPos = boardSize - MATCH_DRAW_COUNTER;

        Board board = boardHistory.get(tenthLastBoardPos);

        return board.isEitherKingLastPieceStanding();
    }

    public void validateFromSquare(Square fromSquare, PlayerMoves playerMoves, Board board) throws PieceNotFoundException,
            InvalidPieceException, PieceCannotBeMovedException, KingInCheckException, KingInDangerException {

        if (!fromSquare.hasPiece())
            throw new PieceNotFoundException();

        Piece currentPiece = fromSquare.getPiece();
        Color color = currentPiece.getColor();

        PlayerMoves opponentInitialMoves = board.calculateOpponentPlayerMoves(color);

        //it might throw InvalidPieceException and PieceCannotBeMovedException
        PieceMoves validPieceMoves = playerMoves.calculateValidPieceMoves(currentPiece, board, opponentInitialMoves);

        Square kingSquare = board.getCurrentKingSquare(color);

        if (validPieceMoves.isEmpty()) {
            if (opponentInitialMoves.contains(kingSquare))
                throw new KingInCheckException();
            else
                throw new KingInDangerException();
        }
    }

    public void addToHistory(Board board) {
        Board clonedBoard = Utils.deepCopyOf(board);
        boardHistory.add(clonedBoard);
    }

    void addToHistoryTest(Board board) {
        boardHistory.add(board);
    }

    /*Used in Tests */
    int historySize() {
        return boardHistory.size();
    }
}
