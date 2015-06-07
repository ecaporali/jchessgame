package au.com.aitcollaboration.chessgame;

import au.com.aitcollaboration.chessgame.configuration.GameConfig;
import au.com.aitcollaboration.chessgame.configuration.InitialSetup;
import au.com.aitcollaboration.chessgame.controller.Game;
import au.com.aitcollaboration.chessgame.controller.Rules;
import au.com.aitcollaboration.chessgame.model.game.structure.Board;
import au.com.aitcollaboration.chessgame.model.player.Players;
import au.com.aitcollaboration.chessgame.service.ValidationService;
import au.com.aitcollaboration.chessgame.view.GameView;
import au.com.aitcollaboration.chessgame.view.console.ConsoleView;

public class App {

    public static void main(String[] args) {

        Board board = new Board();
        GameView gameView = new ConsoleView();

        GameConfig gameConfig = new GameConfig(gameView, board);
        InitialSetup initialSetup = gameConfig.getInitialSetup();

        Players players = new Players(initialSetup);
        Rules rules = new Rules(new ValidationService());

        Game game = new Game(board, rules, players);

        do {
            game.play();
        } while (!rules.isGameOver());
    }
}
