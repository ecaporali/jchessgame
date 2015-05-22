package au.com.aitcollaboration.chessgame.model.player;

import au.com.aitcollaboration.chessgame.controller.Game;
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

    public void play(Game game) {
        for (Player player : players) {
            game.showBoard();
            game.showCurrentPlayer(player);
            player.play(game);
        }
    }
}
