package au.com.aitcollaboration.chessgame.view;

import au.com.aitcollaboration.chessgame.support.UIMessages;

public class ConsoleView implements GameView{

    public ConsoleView(){
        showGreetings();
    }

    private void showGreetings() {
        System.out.println(UIMessages.GREETINGS);
    }
}
