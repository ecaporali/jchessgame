package au.com.aitcollaboration.chessgame.model.player;

import au.com.aitcollaboration.chessgame.controller.Game;
import au.com.aitcollaboration.chessgame.controller.Rules;
import au.com.aitcollaboration.chessgame.support.Constants;

public class ComputerPlayer extends Player {

    public ComputerPlayer() {
        super(Constants.COMPUTER_NAME);
    }

    @Override
    public void play(Game game, Rules rules) {

    }
}
