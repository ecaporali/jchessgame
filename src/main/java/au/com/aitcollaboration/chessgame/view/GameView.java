package au.com.aitcollaboration.chessgame.view;

import au.com.aitcollaboration.chessgame.model.game.structure.Board;

public interface GameView {

    public String getTextAnswer(String message);

    public int getNumericAnswer(String message);

    public void showMessage(String message);

    public void showError(String error);

    public void showBoard(Board board);
}
