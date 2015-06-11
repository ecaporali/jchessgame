package au.com.aitcollaboration.chessgame.configuration;

import au.com.aitcollaboration.chessgame.Color;
import au.com.aitcollaboration.chessgame.model.player.ComputerPlayer;
import au.com.aitcollaboration.chessgame.model.player.HumanPlayer;
import au.com.aitcollaboration.chessgame.model.player.Player;
import au.com.aitcollaboration.chessgame.support.Constants;
import au.com.aitcollaboration.chessgame.support.UIMessages;
import au.com.aitcollaboration.chessgame.support.Utils;
import au.com.aitcollaboration.chessgame.view.GameView;
import org.apache.commons.lang3.StringUtils;

public class GameConfig {

    private GameView gameView;

    public GameConfig(GameView gameView) {
        this.gameView = gameView;
    }

    public Player[] createPlayers() {
        Color color = tossCoin();

        boolean multiPlayers = isMultiPlayers();

        if (!multiPlayers)
            color = Color.WHITE;

        Color flippedColor = color.flip();
        Player[] players = new Player[2];

        players[color.position()] = new HumanPlayer(getPlayerName(UIMessages.PLAYER_ONE_NAME), color, gameView);

        if (multiPlayers)
            players[flippedColor.position()] = new HumanPlayer(getPlayerName(UIMessages.PLAYER_TWO_NAME), flippedColor, gameView);
        else
            players[flippedColor.position()] = new ComputerPlayer(gameView);

        return players;
    }

    private String getPlayerName(String question) {
        String playerName = gameView.getTextAnswer(question);
        while (!isValidPlayerName(playerName)) {
            gameView.showError(UIMessages.WRONG_PLAYER_NAME);
            playerName = gameView.getTextAnswer(question);
        }
        return playerName;
    }

    private boolean isValidPlayerName(String playerName) {
        return StringUtils.isNotBlank(playerName);
    }

    boolean isMultiPlayers() {
        int numericAnswer = gameView.getNumericAnswer(UIMessages.SELECT_NUMBER_OF_PLAYERS);
        return numericAnswer > 1;
    }

    Color tossCoin() {
        String chosenSide = getChosenCoinSide();
        String winningSide = Utils.tossCoin().toUpperCase();
        boolean isWinningSide = StringUtils.equalsIgnoreCase(winningSide, chosenSide);

        String message = (isWinningSide) ? UIMessages.RIGHT_COIN_SIDE : UIMessages.WRONG_COIN_SIDE;

        gameView.showMessage(message + " " + UIMessages.DISPLAY_WINNING_SIDE + winningSide);
        return (isWinningSide) ? Color.WHITE : Color.BLACK;
    }

    String getChosenCoinSide() {
        String chosenSide = gameView.getTextAnswer(UIMessages.CHOOSE_COIN_SIDE);
        while (!isCorrectCoinSide(chosenSide)) {
            gameView.showError(UIMessages.INVALID_COIN_INPUT);
            chosenSide = gameView.getTextAnswer(UIMessages.CHOOSE_COIN_SIDE);
        }
        return chosenSide;
    }

    private boolean isCorrectCoinSide(String coinSide) {
        return StringUtils.equalsIgnoreCase(coinSide, Constants.COIN_HEAD) ||
                StringUtils.equalsIgnoreCase(coinSide, Constants.COIN_TAIL);
    }
}
