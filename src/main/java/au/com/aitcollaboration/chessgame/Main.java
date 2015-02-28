package au.com.aitcollaboration.chessgame;

import au.com.aitcollaboration.chessgame.board.Board;
import au.com.aitcollaboration.chessgame.game.Game;
import au.com.aitcollaboration.chessgame.game.Rules;
import au.com.aitcollaboration.chessgame.player.Player;
import au.com.aitcollaboration.chessgame.utils.In;

public class Main {

    private static Game game;

    static {
        game = new Game(new Board(), new Rules(), new Player[2], new In());
    }

    public static void main(String[] args) {
        //start game
        game.start();
    }

    public static void setGame(Game game) {
        Main.game = game;
    }
}
