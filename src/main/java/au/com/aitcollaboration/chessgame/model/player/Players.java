package au.com.aitcollaboration.chessgame.model.player;

import au.com.aitcollaboration.chessgame.Color;
import au.com.aitcollaboration.chessgame.controller.GameController;
import au.com.aitcollaboration.chessgame.controller.RulesController;
import au.com.aitcollaboration.chessgame.model.pieces.Pieces;

import java.util.Map;

public class Players {

    private Player[] players;

    public Players(Map<Color, Player> colorPlayerMap, Map<Color, Pieces> colorPiecesMap) {
        this.players = new Player[2];
        addPlayer(Color.WHITE, colorPlayerMap, colorPiecesMap);
        addPlayer(Color.BLACK, colorPlayerMap, colorPiecesMap);
    }

    private void addPlayer(Color color, Map<Color, Player> colorPlayerMap, Map<Color, Pieces> colorPiecesMap) {
        Pieces pieces = colorPiecesMap.get(color);
        Player player = colorPlayerMap.get(color);
        player.setPieces(pieces);
        players[color.position()] = player;
    }

    public void play(GameController gameController, RulesController rulesController) {
        for (Player player : players) {
            gameController.showBoard();
            gameController.showCurrentPlayer(player);
            player.stopWatch.resume();
            player.play(gameController, rulesController);
            player.stopWatch.suspend();
        }
    }
}
