package au.com.aitcollaboration.chessgame.player;

import au.com.aitcollaboration.chessgame.board.Board;

public class Players {

    private Player[] players;

    public Players(Player[] players){
        this.players = players;
    }

    public void addHumanPlayer(String name, Color color){
        players[color.getPosition()] = new HumanPlayer(name, color);
    }

    public void addComputerPlayer(Color color){
        players[color.getPosition()] = new ComputerPlayer(color);
    }

    public void play(){
        for(Player player : players){
            player.play();
        }
    }

    public void initBoard(Board board){
        for(Player player : players){
            player.addPiecesTo(board);
        }

        board.positionPieces();
    }
}
