package au.com.aitcollaboration.chessgame.controller;

import au.com.aitcollaboration.chessgame.Color;
import au.com.aitcollaboration.chessgame.exceptions.InvalidCoordinatesException;
import au.com.aitcollaboration.chessgame.exceptions.InvalidMoveException;
import au.com.aitcollaboration.chessgame.exceptions.InvalidPieceException;
import au.com.aitcollaboration.chessgame.exceptions.PieceNotFoundException;
import au.com.aitcollaboration.chessgame.model.game.structure.Board;
import au.com.aitcollaboration.chessgame.model.game.structure.Position;
import au.com.aitcollaboration.chessgame.model.game.structure.Square;
import au.com.aitcollaboration.chessgame.model.moves.PieceMoves;
import au.com.aitcollaboration.chessgame.model.player.Player;
import au.com.aitcollaboration.chessgame.support.MyLogger;
import au.com.aitcollaboration.chessgame.support.UIMessages;

public class Game {

    private Board board;
    private Rules rules;
    private Player[] players;

    public Game(Board board, Rules rules, Player[] players) {
        this.board = board;
        this.rules = rules;
        this.players = players;
    }

    public void play(Player player) {

            player.showBoard(board);
            player.showPlayerName();

            player.resumeWatch();

            this.makeMove(player);

            player.suspendWatch();
    }

    private void makeMove(Player player) {
        PieceMoves pieceMoves = this.getValidPieceMoves(player);

        player.showPracticalMoves(pieceMoves);

        Square fromSquare = pieceMoves.getCurrentSquare();
        Square toSquare = this.getToSquare(player, pieceMoves);

        this.movePiece(fromSquare, toSquare);
    }

    PieceMoves getValidPieceMoves(Player player) {
        PieceMoves pieceMoves;
        do {
            pieceMoves = getPieceMoves(player);
        } while (pieceMoves == null);

        return pieceMoves;
    }

    PieceMoves getPieceMoves(Player player) {
        PieceMoves pieceMoves = null;
        try {
            pieceMoves = this.getValidPieceMovesFor(player);
        } catch (InvalidMoveException e) {
            player.showError(e.getMessage());
        } catch (Exception e) {
            MyLogger.debug(e);
        }

        return pieceMoves;
    }

    Square getFromSquare(Player player) throws PieceNotFoundException, InvalidPieceException {
        Square fromSquare = this.getSquare(player.getFromSquareCoordinate());

        if (!fromSquare.hasPiece())
            throw new PieceNotFoundException();
        if (!player.isOwnPiece(fromSquare.getPiece()))
            throw new InvalidPieceException();

        return fromSquare;
    }

    Square getToSquare(Player player, PieceMoves pieceMoves) {
        Square toSquare = this.getSquare(player.getToSquareCoordinate());
        while (!pieceMoves.contains(toSquare)) {
            player.showError(UIMessages.INVALID_MOVE_EXCEPTION);
            toSquare = this.getSquare(player.getToSquareCoordinate());
        }
        return toSquare;
    }

    Square getSquare(int[] coordinates) {
        if (coordinates == null || coordinates.length < 1)
            throw new InvalidCoordinatesException();

        return board.getSquareAtPosition(new Position(coordinates[0], coordinates[1]));
    }

    private PieceMoves getValidPieceMovesFor(Player player) throws InvalidMoveException {
        Square fromSquare = this.getFromSquare(player);
        return rules.validatePieceMoves(fromSquare, board);
    }

    void movePiece(Square fromSquare, Square toSquare) {
        rules.addToHistory(board);
        board.movePiece(fromSquare, toSquare);
    }

    //TODO: fix this problem
    public boolean isNotOver() {
        for (Player player : players) {
            Color color = player.getColor();
            if (rules.isCheckMate(board, color)) {
                player.showCheckMateMessage();
                player.showEndOfGameMessage();
                return false;
            }
            if (rules.isMatchDraw()) {
                player.showMatchDrawMessage();
                return false;
            }
        }
        return true;
    }
}
