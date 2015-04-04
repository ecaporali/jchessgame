package au.com.aitcollaboration.chessgame.game;

import au.com.aitcollaboration.chessgame.board.Board;
import au.com.aitcollaboration.chessgame.pieces.ValidMoves;
import au.com.aitcollaboration.chessgame.player.Player;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Rules {

    private Map<Player, ValidMoves> possibleMoves;

    public Rules() {
        this.possibleMoves = new HashMap<Player, ValidMoves>();
    }

    public boolean isCheckMate(Board board) {
        return false;
    }

    public boolean isMatchDraw(List<Board> movesHistory) {
        return false;
    }


}
