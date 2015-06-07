package au.com.aitcollaboration.chessgame.model.player;

import au.com.aitcollaboration.chessgame.Color;
import au.com.aitcollaboration.chessgame.configuration.InitialSetup;
import au.com.aitcollaboration.chessgame.controller.Game;
import au.com.aitcollaboration.chessgame.controller.Rules;
import au.com.aitcollaboration.chessgame.model.game.structure.Board;
import au.com.aitcollaboration.chessgame.model.pieces.Pieces;
import au.com.aitcollaboration.chessgame.view.GameView;

public class Players {

    private final Player[] players;

    public Players(InitialSetup initialSetup) {
        this.players = new Player[2];
        addPlayer(Color.WHITE, initialSetup);
        addPlayer(Color.BLACK, initialSetup);
    }

    private void addPlayer(Color color, InitialSetup initialSetup) {
        Pieces pieces = initialSetup.getPiecesFrom(color);
        Player player = initialSetup.getPlayerFrom(color);
        player.setPieces(pieces);
        players[color.position()] = player;
    }

    public Player[] getPlayers() {
        return players.clone();
    }
}
