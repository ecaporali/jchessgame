package au.com.aitcollaboration.chessgame.configuration;

import au.com.aitcollaboration.chessgame.Color;
import au.com.aitcollaboration.chessgame.model.pieces.Pieces;
import au.com.aitcollaboration.chessgame.model.player.Player;

import java.util.Map;

public class InitialSetup {

    private Map<Color, Player> playerMap;
    private Map<Color, Pieces> piecesMap;

    public void setPlayerMap(Map<Color, Player> playerMap) {
        this.playerMap = playerMap;
    }

    public void setPiecesMap(Map<Color, Pieces> piecesMap) {
        this.piecesMap = piecesMap;
    }

    public Player getPlayerFrom(Color color){
        return playerMap.get(color);
    }

    public Pieces getPiecesFrom(Color color){
        return piecesMap.get(color);
    }
}
