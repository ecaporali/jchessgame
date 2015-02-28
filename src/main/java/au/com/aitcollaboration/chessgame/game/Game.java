package au.com.aitcollaboration.chessgame.game;

import au.com.aitcollaboration.chessgame.board.Board;
import au.com.aitcollaboration.chessgame.player.Color;
import au.com.aitcollaboration.chessgame.player.HumanPlayer;
import au.com.aitcollaboration.chessgame.player.Player;
import au.com.aitcollaboration.chessgame.utils.Constants;
import au.com.aitcollaboration.chessgame.utils.In;
import au.com.aitcollaboration.chessgame.utils.UIMessages;

import java.util.LinkedList;
import java.util.List;

public class Game {

    private Board board;
    private Rules rules;
    private Player[] players;
    private boolean gameOver;
    private List<Board> movesHistory;
    private In in;

    private Game(){
        gameOver = false;
        movesHistory = new LinkedList<Board>();
    }

    public Game(Board board, Rules rules, Player[] players, In in) {
        this();
        this.board = board;
        this.rules = rules;
        this.players = players;
        this.in = in;
    }

    public void start() {
        String coinSide = getAnswer(UIMessages.CHOOSE_COIN_SIDE);
        Color color = tossCoin(coinSide);
        playersSetUp(color);
    }

    private void playersSetUp(Color color){
        players[color.getPosition()] = new HumanPlayer(getAnswer(UIMessages.INSERT_PLAYER_NAME), color);
        Color flippedColor = color.flip();
        players[flippedColor.getPosition()] = new HumanPlayer(getAnswer(UIMessages.INSERT_PLAYER_NAME), flippedColor);
    }

    public boolean isGameOver(){
        return rules.isCheckMate(board) || rules.isMatchDraw(movesHistory);
    }

    private void addMoveToHistory(){
        movesHistory.add(board);
    }

    public Color tossCoin(String coinSide){
        int chance = (int) (Math.random() * 10);
        String coinSideResult = (chance > 4) ? Constants.COIN_HEAD : Constants.COIN_TAIL;
        boolean coinMatched = coinSideResult.equalsIgnoreCase(coinSide);

        return (coinMatched)? Color.WHITE : Color.BLACK;
    }

    public String getAnswer(String question){
        return In.nextLine(question);
    }
}
