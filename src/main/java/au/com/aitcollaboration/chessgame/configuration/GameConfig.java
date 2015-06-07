package au.com.aitcollaboration.chessgame.configuration;

import au.com.aitcollaboration.chessgame.Color;
import au.com.aitcollaboration.chessgame.model.game.structure.Board;
import au.com.aitcollaboration.chessgame.model.player.ComputerPlayer;
import au.com.aitcollaboration.chessgame.model.player.HumanPlayer;
import au.com.aitcollaboration.chessgame.model.player.Player;
import au.com.aitcollaboration.chessgame.support.UIMessages;
import au.com.aitcollaboration.chessgame.support.Utils;
import au.com.aitcollaboration.chessgame.view.GameView;

import java.util.HashMap;
import java.util.Map;

public class GameConfig {

    private InitialSetup initialSetup;
    private GameView gameView;

    public GameConfig(GameView gameView, Board board) {
        this.initialSetup = new InitialSetup();
        this.gameView = gameView;
        buildPlayersMap();
        buildPiecesMap(board);
    }

    void buildPlayersMap() {
        Color color = tossCoin();
        Color flippedColor = color.flip();

        Map<Color, Player> playerMap = new HashMap<>(2);

        if (isMultiPlayers())
            playerMap.put(flippedColor, new HumanPlayer(gameView));
        else
            playerMap.put(flippedColor, new ComputerPlayer(gameView));

        playerMap.put(color, new HumanPlayer(gameView));

        initialSetup.setPlayerMap(playerMap);
    }

    private void buildPiecesMap(Board board) {
        initialSetup.setPiecesMap(board.getPiecesMap());
    }

    boolean isMultiPlayers() {
        int numericAnswer = gameView.getNumericAnswer(UIMessages.SELECT_NUMBER_OF_PLAYERS);
        return numericAnswer > 1;
    }

    Color tossCoin() {
        String coinSide = gameView.getTextAnswer(UIMessages.CHOOSE_COIN_SIDE);
        boolean coinMatched = Utils.tossCoin(coinSide);
        return (coinMatched) ? Color.WHITE : Color.BLACK;
    }

    public InitialSetup getInitialSetup() {
        return initialSetup;
    }
}
