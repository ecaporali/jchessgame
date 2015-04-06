package au.com.aitcollaboration.chessgame.player;

public class Players {

    private Player[] players;

    public Players(Player[] players) {
        this.players = players;
    }

    public void add(Player player) {
        players[player.color.getPosition()] = player;
    }

    public void play() {

    }
}
