package au.com.aitcollaboration.chessgame.game;

import au.com.aitcollaboration.chessgame.pieces.Moves;
import au.com.aitcollaboration.chessgame.player.Player;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Massimo on 18/01/2015.
 */
public class Rules {

    private Map<Player, Moves> possibleMoves;

    public Rules() {
        this.possibleMoves = new HashMap<Player, Moves>();
    }
}
