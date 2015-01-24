package au.com.aitcollaboration.chessgame.game;

import au.com.aitcollaboration.chessgame.board.Board;
import au.com.aitcollaboration.chessgame.player.Color;
import au.com.aitcollaboration.chessgame.player.HumanPlayer;
import au.com.aitcollaboration.chessgame.player.Player;

import java.util.LinkedList;
import java.util.List;

public class Game {

    private Board board;
    private Rules rules;
    private Player[] players;
    private boolean gameOver;
    private List<Board> movesHistory;

    private Game(){
        gameOver = false;
        movesHistory = new LinkedList<Board>();
    }

    public Game(Board board, Rules rules, Player[] players) {
        this();
        this.board = board;
        this.rules = rules;
        this.players = players;
    }

    public void start() {
        System.out.println("YEAHHHH");
        players[0] = new HumanPlayer("Jack", Color.BLACK);
        players[1] = new HumanPlayer("Frank", Color.WHITE);
    }
}
