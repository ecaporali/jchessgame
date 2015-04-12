package au.com.aitcollaboration.chessgame;

import au.com.aitcollaboration.chessgame.board.Board;
import au.com.aitcollaboration.chessgame.game.Game;
import au.com.aitcollaboration.chessgame.game.Rules;
import au.com.aitcollaboration.chessgame.pieces.Pieces;
import au.com.aitcollaboration.chessgame.player.Color;
import au.com.aitcollaboration.chessgame.player.Player;
import au.com.aitcollaboration.chessgame.player.Players;

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
