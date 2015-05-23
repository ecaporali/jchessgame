package au.com.aitcollaboration.chessgame.controller;

import au.com.aitcollaboration.chessgame.Color;
import au.com.aitcollaboration.chessgame.model.game.structure.Board;
import au.com.aitcollaboration.chessgame.model.game.structure.Position;
import au.com.aitcollaboration.chessgame.model.game.structure.Square;
import au.com.aitcollaboration.chessgame.model.moves.PieceMoves;
import au.com.aitcollaboration.chessgame.model.moves.PlayerMoves;
import au.com.aitcollaboration.chessgame.model.pieces.Piece;
import au.com.aitcollaboration.chessgame.model.pieces.Pieces;
import au.com.aitcollaboration.chessgame.model.player.ComputerPlayer;
import au.com.aitcollaboration.chessgame.model.player.HumanPlayer;
import au.com.aitcollaboration.chessgame.model.player.Player;
import au.com.aitcollaboration.chessgame.support.UIMessages;
import au.com.aitcollaboration.chessgame.support.Utils;
import au.com.aitcollaboration.chessgame.view.GameView;
import au.com.aitcollaboration.chessgame.view.exceptions.*;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class Game {

    private Board board;
    private Rules rules;
    private List<Board> movesHistory;
    private GameView gameView;

    public Game(Board board, Rules rules, GameView gameView) {
        this.movesHistory = new LinkedList<>();
        this.board = board;
        this.rules = rules;
        this.gameView = gameView;
    }

    public Map<Color, Player> getPlayersMap() {
        Color color = tossCoin();
        Color flippedColor = color.flip();

        Map<Color, Player> colorPlayerMap = new HashMap<>(2);

        if (isMultiPlayers())
            colorPlayerMap.put(flippedColor, new HumanPlayer(getTextAnswer(UIMessages.INSERT_PLAYER_NAME)));
        else
            colorPlayerMap.put(flippedColor, new ComputerPlayer());

        colorPlayerMap.put(color, new HumanPlayer(getTextAnswer(UIMessages.INSERT_PLAYER_NAME)));

        return colorPlayerMap;
    }

    public boolean isMultiPlayers() {
        int numericAnswer = getNumericAnswer(UIMessages.SELECT_NUMBER_OF_PLAYERS);
        return numericAnswer > 1;
    }

    public void showBoard() {
        gameView.showBoard(board);
    }

    public boolean isGameOver() {
        return rules.isMatchDraw(movesHistory) ||
                rules.isCheckMate(board);
    }

    public void addMoveToHistory() {
        movesHistory.add(board);
    }

    public Color tossCoin() {
        String coinSide = getTextAnswer(UIMessages.CHOOSE_COIN_SIDE);
        boolean coinMatched = Utils.tossCoin(coinSide);
        return (coinMatched) ? Color.WHITE : Color.BLACK;
    }

    public String getTextAnswer(String message) {
        return gameView.getTextAnswer(message);
    }

    public int getNumericAnswer(String message) {
        return gameView.getNumericAnswer(message);
    }

    public void findPossibleMovesOnBoard() {
        rules.findAllPossibleMovesOn(board);
    }

    public void showCurrentPlayer(Player player) {
        gameView.showMessage("\n" + player);
    }

    public Square getFromSquare() {
        Square square;
        do {
            int[] fromPos = getValidCoordinates(UIMessages.PIECE_TO_MOVE);
            square = getSquareFromCoordinates(fromPos);
            if (!square.hasPiece())
                gameView.showError(UIMessages.PIECE_NOT_FOUND_EXCEPTION);
        } while (!square.hasPiece());

        return square;
    }

    public Square getToSquare() {
        int[] fromPos = getValidCoordinates(UIMessages.WHERE_TO_MOVE_PIECE);
        return getSquareFromCoordinates(fromPos);
    }

    public Square getSquareFromCoordinates(int[] coordinates) {
        if (coordinates == null || coordinates.length < 1)
            throw new InvalidCoordinatesException();

        return board.getSquareAtPosition(new Position(coordinates[0], coordinates[1]));
    }

    public int[] getValidCoordinates(String message) {
        int[] coordinates = null;

        while (coordinates == null) {
            String coordinate = getTextAnswer(message);
            try {
                coordinates = Utils.getConvertedPosition(coordinate);
            } catch (InvalidPositionException e) {
                gameView.showError(e.getMessage());
            }
        }
        return coordinates;
    }

    public Map<Color, Pieces> getPiecesMap() {
        return board.getPiecesMap();
    }

    public void showPracticalMoves(PieceMoves pieceMoves) {
        gameView.showMessage(pieceMoves.toString());
    }

    public List<Board> getMovesHistory() {
        return movesHistory;
    }

    public void movePiece(Square fromSquare, Square toSquare) {
        movesHistory.add(board);
        Piece piece = toSquare.getPiece();
        board.removePiece(piece);
        board.movePiece(fromSquare, toSquare);
    }

    public void validatePieceMove(Square fromSquare, Pieces pieces) {
        try {
            rules.validatePieceMove(fromSquare, board, pieces);
        } catch (PieceCannotBeMovedException | KingInDangerException | InvalidPieceException | KingInCheckException e) {
            gameView.showError(e.getMessage());
        }
    }

    public PlayerMoves getPlayerMoves(Pieces pieces) {
        return rules.getPlayerMoves(pieces);
    }

    public Square getFromSquare(Pieces pieces) {

        PlayerMoves playerMoves;
        Square fromSquare;
        do {
            fromSquare = getFromSquare();

            rules.runAllPossibleMoves(fromSquare, board);

            validatePieceMove(fromSquare, pieces);

            playerMoves = getPlayerMoves(pieces);

        } while (playerMoves.hasEmptyMoveFor(fromSquare.getPiece()));

        return fromSquare;
    }

    public Square getToSquareFrom(PieceMoves pieceMoves) {
        Square toSquare = getToSquare();
        while (!pieceMoves.contains(toSquare)) {
            gameView.showError(UIMessages.INVALID_MOVE_EXCEPTION);
            toSquare = getToSquare();
        }
        return toSquare;
    }
}
