package au.com.aitcollaboration.chessgame;

import au.com.aitcollaboration.chessgame.model.game.structure.Board;
import au.com.aitcollaboration.chessgame.controller.Game;
import au.com.aitcollaboration.chessgame.controller.Rules;
import au.com.aitcollaboration.chessgame.model.pieces.Pieces;
import au.com.aitcollaboration.chessgame.model.player.Player;
import au.com.aitcollaboration.chessgame.model.player.Players;
import au.com.aitcollaboration.chessgame.service.ValidationService;
import au.com.aitcollaboration.chessgame.view.ConsoleView;

import java.util.Map;

public class App {

    public static void main(String[] args) {

        Board board = new Board();
        Game game = new Game(board, new ConsoleView());
        Rules rules = new Rules(board, new ValidationService());

        Map<Color, Player> colorPlayerMap = game.getPlayersMap();
        Map<Color, Pieces> colorPiecesMap = game.getPiecesMap();
        Players players = new Players(colorPlayerMap, colorPiecesMap);

        do {
            players.play(game, rules);
        } while (!rules.isGameOver());
    }
}
