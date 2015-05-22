package au.com.aitcollaboration.chessgame;

import au.com.aitcollaboration.chessgame.model.board.Board;
import au.com.aitcollaboration.chessgame.controller.Game;
import au.com.aitcollaboration.chessgame.controller.Rules;
import au.com.aitcollaboration.chessgame.model.pieces.Pieces;
import au.com.aitcollaboration.chessgame.model.player.Color;
import au.com.aitcollaboration.chessgame.model.player.Player;
import au.com.aitcollaboration.chessgame.model.player.Players;

import java.util.Map;

public class Main {

    public static void main(String[] args) {
        Game game = new Game(new Board(), new Rules());
        Map<Color, Player> colorPlayerMap = game.getPlayersMap();
        Map<Color, Pieces> colorPiecesMap = game.getPiecesMap();
        Players players = new Players(colorPlayerMap, colorPiecesMap);

        do {
            players.play(game);
        } while (!game.isGameOver());
    }
}
