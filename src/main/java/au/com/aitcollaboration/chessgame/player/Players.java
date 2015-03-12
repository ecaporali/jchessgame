package au.com.aitcollaboration.chessgame.player;

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
}
