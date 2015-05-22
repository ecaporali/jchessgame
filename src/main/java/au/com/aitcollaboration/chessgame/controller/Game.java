package au.com.aitcollaboration.chessgame.controller;

import au.com.aitcollaboration.chessgame.model.board.Board;
import au.com.aitcollaboration.chessgame.model.board.Position;
import au.com.aitcollaboration.chessgame.model.board.Square;
import au.com.aitcollaboration.chessgame.controller.exceptions.*;
import au.com.aitcollaboration.chessgame.model.pieces.Piece;
import au.com.aitcollaboration.chessgame.model.pieces.PieceMoves;
import au.com.aitcollaboration.chessgame.model.pieces.Pieces;
import au.com.aitcollaboration.chessgame.model.pieces.PlayerMoves;
import au.com.aitcollaboration.chessgame.model.player.Color;
import au.com.aitcollaboration.chessgame.model.player.ComputerPlayer;
import au.com.aitcollaboration.chessgame.model.player.HumanPlayer;
import au.com.aitcollaboration.chessgame.model.player.Player;
import au.com.aitcollaboration.chessgame.support.In;
import au.com.aitcollaboration.chessgame.support.UIMessages;
import au.com.aitcollaboration.chessgame.support.Utils;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class Game {

    private Board board;
    private Rules rules;
    private List<Board> movesHistory;

    public Game(Board board, Rules rules) {
        this.movesHistory = new LinkedList<Board>();
        this.board = board;
        this.rules = rules;
        showGreetings();
    }

    private void showGreetings() {
        System.out.println(UIMessages.GREETINGS);
    }

    public Map<Color, Player> getPlayersMap() {
        Color color = tossCoin();
        Color flippedColor = color.flip();

        Map<Color, Player> colorPlayerMap = new HashMap<Color, Player>(2);

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
        System.out.println(board);
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


    public void findPossibleMovesOnBoard() {
        rules.findAllPossibleMovesOn(board);
    }

    public void showCurrentPlayer(Player player) {
        System.out.println("\n" + player);
    }

    public Square getFromSquare() {
        Square square;
        do {
            int[] fromPos = getValidCoordinates(UIMessages.PIECE_TO_MOVE);
            square = getSquareFromCoordinates(fromPos);
            if (!square.hasPiece())
                System.out.println(UIMessages.PIECE_NOT_FOUND_EXCEPTION);
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
                System.out.println(e.getMessage());
            }
        }
        return coordinates;
    }

    public Map<Color, Pieces> getPiecesMap() {
        return board.getPiecesMap();
    }

    public void showPracticalMoves(PieceMoves pieceMoves) {
        System.out.println(pieceMoves);
    }

    public String getTextAnswer(String question) {
        return In.nextLine(question);
    }

    public int getNumericAnswer(String question) {
        return In.nextInt(question);
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

    public void showInvalidMoveSelected() {
        System.out.println(UIMessages.INVALID_MOVE_EXCEPTION);
    }

    public void validatePieceMove(Square fromSquare, Pieces pieces) {
        try {
            rules.validatePieceMove(fromSquare, board, pieces);
        } catch (PieceCannotBeMovedException e) {
            System.out.println(e.getMessage());
        } catch (KingInDangerException e) {
            System.out.println(e.getMessage());
        } catch (InvalidPieceException e) {
            System.out.println(e.getMessage());
        }catch(KingInCheckException e){
            System.out.println(e.getMessage());
        }
    }

    public PlayerMoves getPlayerMoves(Pieces pieces){
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
            showInvalidMoveSelected();
            toSquare = getToSquare();
        }
        return toSquare;
    }
}
