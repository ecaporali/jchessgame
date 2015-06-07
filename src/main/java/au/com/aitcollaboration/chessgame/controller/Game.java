package au.com.aitcollaboration.chessgame.controller;

import au.com.aitcollaboration.chessgame.model.game.structure.Board;
import au.com.aitcollaboration.chessgame.model.game.structure.Square;
import au.com.aitcollaboration.chessgame.model.player.Player;
import au.com.aitcollaboration.chessgame.model.player.Players;
import au.com.aitcollaboration.chessgame.support.Constants;

import java.util.Map;

public class Game {

    private Board board;
    private Rules rules;
    private Players players;

    public Game(Board board, Rules rules, Players players) {
        this.board = board;
        this.rules = rules;
        this.players = players;
    }

    public void play() {
        Player[] gamePlayers = players.getPlayers();

        for (Player player : gamePlayers) {
            player.showBoard(board);
            player.showPlayerName();
            player.resumeWatch();

            Map<String, Square> moveMap = player.chooseMove(board, rules);

            movePiece(moveMap.get(Constants.FROM_SQUARE), moveMap.get(Constants.TO_SQUARE));

            player.suspendWatch();
        }
    }

    public void movePiece(Square fromSquare, Square toSquare) {
        board.addToMoveHistory();
        board.movePiece(fromSquare, toSquare);
    }
}
