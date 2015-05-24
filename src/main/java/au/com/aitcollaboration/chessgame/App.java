package au.com.aitcollaboration.chessgame;

import au.com.aitcollaboration.chessgame.model.game.structure.Board;
import au.com.aitcollaboration.chessgame.controller.GameController;
import au.com.aitcollaboration.chessgame.controller.RulesController;
import au.com.aitcollaboration.chessgame.model.pieces.Pieces;
import au.com.aitcollaboration.chessgame.model.player.Player;
import au.com.aitcollaboration.chessgame.model.player.Players;
import au.com.aitcollaboration.chessgame.view.ConsoleView;

import java.util.Map;

public class App {

    public static void main(String[] args) {

        Board board = new Board();
        GameController gameController = new GameController(board, new ConsoleView());
        RulesController rulesController = new RulesController(board);

        Map<Color, Player> colorPlayerMap = gameController.getPlayersMap();
        Map<Color, Pieces> colorPiecesMap = gameController.getPiecesMap();
        Players players = new Players(colorPlayerMap, colorPiecesMap);

        do {
            players.play(gameController, rulesController);
        } while (!rulesController.isGameOver());
    }
}
