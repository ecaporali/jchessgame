package au.com.aitcollaboration.chessgame.view;

import au.com.aitcollaboration.chessgame.model.game.structure.Board;

public class UIView implements GameView {

    @Override
    public String getTextAnswer(String message) {
        return null;
    }

    @Override
    public int getNumericAnswer(String message) {
        return 0;
    }

    @Override
    public void showMessage(String message) {

    }

    @Override
    public void showError(String error) {

    }

    @Override
    public void showBoard(Board board) {

    }
}
