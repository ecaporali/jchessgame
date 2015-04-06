package au.com.aitcollaboration.chessgame.game;

import au.com.aitcollaboration.chessgame.board.Board;
import au.com.aitcollaboration.chessgame.board.Position;
import au.com.aitcollaboration.chessgame.board.Square;
import au.com.aitcollaboration.chessgame.exceptions.InvalidPositionException;
import au.com.aitcollaboration.chessgame.exceptions.NullCoordinatesException;
import au.com.aitcollaboration.chessgame.pieces.Pieces;
import au.com.aitcollaboration.chessgame.player.Color;
import au.com.aitcollaboration.chessgame.player.ComputerPlayer;
import au.com.aitcollaboration.chessgame.player.HumanPlayer;
import au.com.aitcollaboration.chessgame.player.Player;
import au.com.aitcollaboration.chessgame.support.In;
import au.com.aitcollaboration.chessgame.support.UIMessages;
import au.com.aitcollaboration.chessgame.support.Utils;

import java.util.LinkedList;
import java.util.List;

public class Game {

    private Board board;
    private Rules rules;
    private Player[] players;
    private boolean gameOver;
    private List<Board> movesHistory;

    private Game() {
        gameOver = false;
        movesHistory = new LinkedList<Board>();
    }

    public Game(Board board, Rules rules, Player[] players) {
        this();
        this.board = board;
        this.rules = rules;
        this.players = players;
    }

    public void start() {
        showGreetings();
        playersSetUp();
        runGame();
    }

    private void showGreetings() {
        System.out.println(UIMessages.GREETINGS);
    }

    private void showBoard() {
        System.out.println(board);
    }

    public void playersSetUp() {
        boolean multiPlayers = isMultiPlayers();

        Color color = tossCoin();
        Color flippedColor = color.flip();

        Pieces playerOnePieces = board.getColoredPieces(color);
        Pieces playerTwoPieces = board.getColoredPieces(flippedColor);

        int playerOnePos = color.getPosition();
        int playerTwoPos = flippedColor.getPosition();

        players[playerOnePos] = new HumanPlayer(getTextAnswer(UIMessages.INSERT_PLAYER_NAME), color, playerOnePieces);

        if (multiPlayers)
            players[playerTwoPos] = new HumanPlayer(getTextAnswer(UIMessages.INSERT_PLAYER_NAME), flippedColor, playerTwoPieces);
        else
            players[playerTwoPos] = new ComputerPlayer(flippedColor, playerTwoPieces);
    }

    public boolean isMultiPlayers() {
        return getPlayersNumber() > 1;
    }

    private int getPlayersNumber() {
        while (true) {
            try {
                return getNumericAnswer(UIMessages.SELECT_NUMBER_OF_PLAYERS);
            } catch (NumberFormatException e) {
                // keep looping
            }
        }
    }

    public void runGame() {
        while (!gameOver) {
            for (Player player : players) {
                showBoard();
                showCurrentPlayer(player.toString());

                rules.getPossibleMovesOn(board);

                Square fromSquare = getFromSquare(UIMessages.PIECE_TO_MOVE);

                //TODO: might not need the following method
                player.play();

                Square toSquare = getToSquare(UIMessages.WHERE_TO_MOVE_PIECE);

                gameOver = isGameOver();
            }
        }
    }

    private void showCurrentPlayer(String playerNumber) {
        System.out.println("\n" + playerNumber);
    }

    private Square getFromSquare(String message) {
        Square square;
        do {
            int[] fromPos = getValidCoordinates(message);
            square = getSquareFromCoordinates(fromPos);
            if (!square.hasPiece())
                System.out.println(UIMessages.EMPTY_SQUARE_EXCEPTION_MSG);
        } while (!square.hasPiece());

        return square;
    }

    private Square getToSquare(String message){
        int[] fromPos = getValidCoordinates(message);
        return getSquareFromCoordinates(fromPos);
    }

    private Square getSquareFromCoordinates(int[] coordinates) {
        if(coordinates == null)
            throw new NullCoordinatesException();

        return board.getSquareAtPosition(new Position(coordinates[0], coordinates[1]));
    }

    private int[] getValidCoordinates(String message) {
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

    public boolean isGameOver() {
        return rules.isMatchDraw(movesHistory) ||
                rules.isCheckMate(board);
    }

    public void setGameOver(boolean gameOver) {
        this.gameOver = gameOver;
    }

    private void addMoveToHistory() {
        movesHistory.add(board);
    }

    public Color tossCoin() {
        String coinSide = getTextAnswer(UIMessages.CHOOSE_COIN_SIDE);
        boolean coinMatched = Utils.tossCoin(coinSide);
        return (coinMatched) ? Color.WHITE : Color.BLACK;
    }

    public String getTextAnswer(String question) {
        return In.nextLine(question);
    }

    public int getNumericAnswer(String question) throws NumberFormatException {
        return In.nextInt(question);
    }
}
