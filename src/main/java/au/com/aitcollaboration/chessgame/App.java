package au.com.aitcollaboration.chessgame;

import au.com.aitcollaboration.chessgame.configuration.GameConfig;
import au.com.aitcollaboration.chessgame.controller.Game;
import au.com.aitcollaboration.chessgame.controller.Rules;
import au.com.aitcollaboration.chessgame.model.game.structure.Board;
import au.com.aitcollaboration.chessgame.model.player.Player;
import au.com.aitcollaboration.chessgame.view.console.ConsoleView;

public class App {

    public static void main(String[] args) {
        GameConfig gameConfig = new GameConfig(new ConsoleView());
        Player[] players = gameConfig.createPlayers();

        Game game = new Game(new Board(), new Rules(), players);

        Player currentPlayer = players[0];
        do {
            Color color = currentPlayer.getColor();
            game.play(currentPlayer);
            currentPlayer = players[color.flip().position()];
        } while (game.isNotOver());
    }
}
