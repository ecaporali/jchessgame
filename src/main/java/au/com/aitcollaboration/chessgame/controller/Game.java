package au.com.aitcollaboration.chessgame.controller;

import au.com.aitcollaboration.chessgame.Color;
import au.com.aitcollaboration.chessgame.exceptions.InvalidCoordinatesException;
import au.com.aitcollaboration.chessgame.exceptions.InvalidMoveException;
import au.com.aitcollaboration.chessgame.model.game.structure.Board;
import au.com.aitcollaboration.chessgame.model.game.structure.Position;
import au.com.aitcollaboration.chessgame.model.game.structure.Square;
import au.com.aitcollaboration.chessgame.model.moves.PieceMoves;
import au.com.aitcollaboration.chessgame.model.moves.PlayerMoves;
import au.com.aitcollaboration.chessgame.model.player.Player;
import au.com.aitcollaboration.chessgame.support.MyLogger;
import au.com.aitcollaboration.chessgame.support.UIMessages;

public class Game {

    private Board board;
    private Rules rules;

    public Game(Board board, Rules rules) {
        this.board = board;
        this.rules = rules;
    }

    public void play(Player[] players) {
        Player currentPlayer = players[Color.WHITE.position()];

        while (true) {
            Color color = currentPlayer.getColor();

            this.play(currentPlayer);

            Color opponentColor = color.flip();

            if (isCheckMate(opponentColor)) {
                Player loosingPlayer = players[opponentColor.position()];
                loosingPlayer.showCheckMateMessage();
                currentPlayer.showEndOfGameMessage();
                break;
            }

            if (isMatchDraw()) {
                currentPlayer.showMatchDrawMessage();
                break;
            }

            currentPlayer = players[opponentColor.position()];
        }

        currentPlayer.showBoard(board);
    }

    void play(Player player) {

        player.showBoard(board);
        player.showPlayerName();

        player.resumeWatch();

        PlayerMoves playerMoves = board.calculateCurrentPlayerMoves(player.getColor());

        this.makeMove(player, playerMoves);

        player.suspendWatch();
    }

    void makeMove(Player player, PlayerMoves playerMoves) {
        Square fromSquare = this.getValidFromSquare(player, playerMoves);

        PieceMoves validPieceMoves = playerMoves.getValidPieceMovesFor(fromSquare.getPiece());

        player.showPracticalMoves(validPieceMoves);

        Square toSquare = this.getValidToSquare(player, validPieceMoves);

        this.movePiece(fromSquare, toSquare);
    }

    Square getValidFromSquare(Player player, PlayerMoves playerMoves) {
        Square fromSquare;
        do {
            fromSquare = validateFromSquare(player, playerMoves);
        } while (fromSquare == null);

        return fromSquare;
    }

    Square validateFromSquare(Player player, PlayerMoves playerMoves) {
        Square fromSquare = this.getSquare(player.getFromSquareCoordinate());

        try {
            rules.validateFromSquare(fromSquare, playerMoves, board);
        } catch (InvalidMoveException e) {
            fromSquare = null;
            player.showError(e.getMessage());
        } catch (Exception e) {
            MyLogger.debug(e);
        }

        return fromSquare;
    }

    Square getValidToSquare(Player player, PieceMoves pieceMoves) {
        Square toSquare = this.getSquare(player.getToSquareCoordinate());
        while (!pieceMoves.contains(toSquare)) {
            player.showError(UIMessages.INVALID_MOVE_EXCEPTION);
            toSquare = this.getSquare(player.getToSquareCoordinate());
        }
        return toSquare;
    }

    Square getSquare(int[] coordinates) {
        if (coordinates == null || coordinates.length != 2)
            throw new InvalidCoordinatesException();

        return board.getSquareAtPosition(new Position(coordinates[0], coordinates[1]));
    }

    void movePiece(Square fromSquare, Square toSquare) {
        rules.addToHistory(board);
        board.movePiece(fromSquare, toSquare);
    }

    public boolean isCheckMate(Color color) {
        return rules.isCheckMate(color, board);
    }

    public boolean isMatchDraw() {
        return rules.isMatchDraw();
    }
}
