package au.com.aitcollaboration.chessgame;

/*////////////////////////////////////////////////////
 To run this class, click on edit configuration
 (the arrow pointing down near the run button on
 the top right corner of IntelliJ) and change the
 VM options for both the MainTest (class) and the
 MainTest.testMainMethodHaveBeenCalled (method).
 The VM option is as follow without quotes:
 "-noverify"

 This is needed cause PowerMock changes java byte code
 when testing static objects
*/////////////////////////////////////////////////////

import au.com.aitcollaboration.chessgame.game.Game;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@RunWith(PowerMockRunner.class)
@PrepareForTest(Main.class)
public class MainTest {

    private Game game;

    @Before
    public void setUp() throws Exception {
        game = PowerMockito.mock(Game.class);
        Main.setGame(game);
    }

    @Test
    public void testStartMethodHaveBeenCalled(){
        Main.main(new String[0]);
        verify(game, times(1)).start();
    }
}
