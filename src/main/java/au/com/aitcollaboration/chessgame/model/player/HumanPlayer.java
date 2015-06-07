package au.com.aitcollaboration.chessgame.model.player;

import au.com.aitcollaboration.chessgame.controller.Rules;
import au.com.aitcollaboration.chessgame.exceptions.InvalidPositionException;
import au.com.aitcollaboration.chessgame.model.game.structure.Board;
import au.com.aitcollaboration.chessgame.model.game.structure.Square;
import au.com.aitcollaboration.chessgame.model.moves.PieceMoves;
import au.com.aitcollaboration.chessgame.model.moves.PlayerMoves;
import au.com.aitcollaboration.chessgame.model.pieces.Piece;
import au.com.aitcollaboration.chessgame.support.MyLogger;
import au.com.aitcollaboration.chessgame.support.UIMessages;
import au.com.aitcollaboration.chessgame.support.Utils;
import au.com.aitcollaboration.chessgame.view.GameView;

public class HumanPlayer extends Player {

    public HumanPlayer(GameView gameView) {
        super(gameView);
        name = gameView.getTextAnswer(UIMessages.INSERT_PLAYER_NAME);
    }

    @Override
    public Square getFromSquare(Board board, Rules rules) {
        PlayerMoves playerMoves = null;
        Square fromSquare = null;

        while (playerMoves == null) {
            try {
                fromSquare = this.getFromSquare(board);
                playerMoves = this.getPlayerMoves(fromSquare, board, rules);
            } catch (Exception e) {
                MyLogger.debug(e);
                showError(e.getMessage());
            }
        }

        return fromSquare;
    }

    @Override
    public Square getToSquare(Board board, PieceMoves pieceMoves) {
        showPracticalMoves(pieceMoves);
        Square toSquare = this.getToSquare(board);
        while (!pieceMoves.contains(toSquare)) {
            showError(UIMessages.INVALID_MOVE_EXCEPTION);
            toSquare = this.getToSquare(board);
        }
        return toSquare;
    }

    private Square getFromSquare(Board board) {
        int[] coordinates = getValidCoordinates(UIMessages.PIECE_TO_MOVE);
        return board.getSquareFromCoordinates(coordinates);
    }

    private Square getToSquare(Board board) {
        int[] coordinates = getValidCoordinates(UIMessages.WHERE_TO_MOVE_PIECE);
        return board.getSquareFromCoordinates(coordinates);
    }

    private int[] getValidCoordinates(String message) {
        int[] coordinates = null;

        while (coordinates == null) {
            String coordinate = getTextAnswer(message);
            try {
                coordinates = Utils.toBoardPosition(coordinate);
            } catch (InvalidPositionException e) {
                MyLogger.debug(e);
                showError(e.getMessage());
            }
        }
        return coordinates;
    }

    private void mockPieceMove(Square fromSquare, Board board, Rules rules) {
        Piece currentPiece = fromSquare.getPiece();
        if (currentPiece != null) {
            fromSquare.setPiece(null);
            rules.findAllPossibleMovesOnBoard(board);
            fromSquare.setPiece(currentPiece);
        }
    }

    private PlayerMoves getPlayerMoves(Square fromSquare, Board board, Rules rules) throws Exception {
        mockPieceMove(fromSquare, board, rules);
        rules.validatePieceMove(fromSquare, pieces, board);
        return rules.getPlayerMoves(pieces);
    }
}
