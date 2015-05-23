package au.com.aitcollaboration.chessgame.view;

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
}
