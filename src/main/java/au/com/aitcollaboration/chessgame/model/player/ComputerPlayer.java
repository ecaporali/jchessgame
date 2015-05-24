package au.com.aitcollaboration.chessgame.model.player;

import au.com.aitcollaboration.chessgame.controller.GameController;
import au.com.aitcollaboration.chessgame.controller.RulesController;
import au.com.aitcollaboration.chessgame.support.Constants;

public class ComputerPlayer extends Player {

    public ComputerPlayer() {
        super(Constants.COMPUTER_NAME);
    }

    @Override
    public void play(GameController gameController, RulesController rulesController) {

    }
}
