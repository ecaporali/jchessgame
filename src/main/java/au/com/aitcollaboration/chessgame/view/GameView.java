package au.com.aitcollaboration.chessgame.view;

public interface GameView {
    public String getTextAnswer(String message);
    public int getNumericAnswer(String message);
    public void showMessage(String message);
    public void showError(String error);
}
