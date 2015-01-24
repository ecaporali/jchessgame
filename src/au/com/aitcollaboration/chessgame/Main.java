package au.com.aitcollaboration.chessgame;

import au.com.aitcollaboration.chessgame.board.Board;
import au.com.aitcollaboration.chessgame.game.Game;
import au.com.aitcollaboration.chessgame.game.Rules;
import au.com.aitcollaboration.chessgame.player.Player;

public class Main {

    public static void main(String[] args) {
        Board board = new Board();
        Rules rules = new Rules();
        Player[] players = new Player[2];

        //start game
        Game game = new Game(board, rules, players);
        game.start();
    }
}
