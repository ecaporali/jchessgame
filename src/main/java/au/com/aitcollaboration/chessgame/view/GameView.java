package au.com.aitcollaboration.chessgame.view;

import au.com.aitcollaboration.chessgame.model.game.structure.Board;

public interface GameView {

    String getTextAnswer(String message);

    int getNumericAnswer(String message);

    void showMessage(String message);

    void showError(String error);

    void showBoard(Board board);

    void showGreetings();
}
