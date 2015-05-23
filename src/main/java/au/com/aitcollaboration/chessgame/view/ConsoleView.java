package au.com.aitcollaboration.chessgame.view;

import au.com.aitcollaboration.chessgame.model.game.structure.Board;
import au.com.aitcollaboration.chessgame.support.In;
import au.com.aitcollaboration.chessgame.support.UIMessages;
import au.com.aitcollaboration.chessgame.support.Utils;

public class ConsoleView implements GameView {

    public ConsoleView() {
        showGreetings();
    }

    private void showGreetings() {
        System.out.println(UIMessages.GREETINGS);
    }

    @Override
    public String getTextAnswer(String message) {
        return In.nextLine(message);
    }

    @Override
    public int getNumericAnswer(String message) {
        return In.nextInt(message);
    }

    @Override
    public void showMessage(String message) {
        System.out.println(message);
    }

    @Override
    public void showError(String error) {
        System.out.println(error);
    }

    @Override
    public void showBoard(Board board) {
        showMessage(Utils.boardToConsole(board));
    }
}
