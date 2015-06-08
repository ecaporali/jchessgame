package au.com.aitcollaboration.chessgame.controller;

import au.com.aitcollaboration.chessgame.exceptions.InvalidCoordinatesException;
import au.com.aitcollaboration.chessgame.exceptions.InvalidMoveException;
import au.com.aitcollaboration.chessgame.model.game.structure.Board;
import au.com.aitcollaboration.chessgame.model.game.structure.Position;
import au.com.aitcollaboration.chessgame.model.game.structure.Square;
import au.com.aitcollaboration.chessgame.model.moves.PieceMoves;
import au.com.aitcollaboration.chessgame.model.moves.PlayerMoves;
import au.com.aitcollaboration.chessgame.model.pieces.Piece;
import au.com.aitcollaboration.chessgame.model.pieces.Pieces;
import au.com.aitcollaboration.chessgame.model.player.Player;
import au.com.aitcollaboration.chessgame.model.player.Players;
import au.com.aitcollaboration.chessgame.support.MyLogger;
import au.com.aitcollaboration.chessgame.support.UIMessages;

public class Game {

    private Board board;
    private Rules rules;
    private Players players;

    public Game(Board board, Rules rules, Players players) {
        this.board = board;
        this.rules = rules;
        this.players = players;
    }

    public void play() {
        Player[] gamePlayers = players.getPlayers();

        for (Player player : gamePlayers) {
            player.showBoard(board);
            player.showPlayerName();
            player.resumeWatch();

            makeMove(player);

            player.suspendWatch();
        }
    }

    private void makeMove(Player player) {
        Square fromSquare = getFromSquare(player);

        PlayerMoves playerMoves = rules.getPlayerMoves(player.getPieces());

        PieceMoves pieceMoves = playerMoves.getPieceMoves(fromSquare.getPiece());

        player.showPracticalMoves(pieceMoves);

        Square toSquare = getToSquare(player, pieceMoves);

        movePiece(fromSquare, toSquare);
    }

    public Square getFromSquare(Player player) {
        PlayerMoves playerMoves = null;
        Square fromSquare = null;

        while (playerMoves == null) {
            try {
                fromSquare = getSquare(player.getFromSquareCoordinate());
                playerMoves = this.getPlayerMoves(fromSquare, player.getPieces());
            } catch (InvalidMoveException e) {
                player.showError(e.getMessage());
            } catch (Exception e){
                MyLogger.debug(e);
            }
        }

        return fromSquare;
    }

    public Square getToSquare(Player player, PieceMoves pieceMoves) {
        Square toSquare = getSquare(player.getToSquareCoordinate());
        while (!pieceMoves.contains(toSquare)) {
            player.showError(UIMessages.INVALID_MOVE_EXCEPTION);
            toSquare = getSquare(player.getToSquareCoordinate());
        }
        return toSquare;
    }

    private Square getSquare(int[] coordinates) {
        return getSquareCoordinates(coordinates);
    }

    private Square getSquareCoordinates(int[] coordinates) {
        if (coordinates == null || coordinates.length < 1)
            throw new InvalidCoordinatesException();

        return board.getSquareAtPosition(new Position(coordinates[0], coordinates[1]));
    }

    private void mockPieceMove(Square fromSquare, Board board, Rules rules) {
        Piece currentPiece = fromSquare.getPiece();
        if (currentPiece != null) {
            fromSquare.setPiece(null);
            rules.findAllPossibleMovesOnBoard(board);
            fromSquare.setPiece(currentPiece);
        }
    }

    private PlayerMoves getPlayerMoves(Square fromSquare, Pieces pieces) throws InvalidMoveException {
        mockPieceMove(fromSquare, board, rules);
        rules.validatePieceMove(fromSquare, pieces, board);
        return rules.getPlayerMoves(pieces);
    }

    public void movePiece(Square fromSquare, Square toSquare) {
        board.addToMoveHistory();
        board.movePiece(fromSquare, toSquare);
    }
}
