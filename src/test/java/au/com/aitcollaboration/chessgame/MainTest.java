package au.com.aitcollaboration.chessgame;

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
@PrepareForTest(Game.class)
public class MainTest {

    private Game game;

    @Before
    public void setUp() throws Exception {
        game = PowerMockito.mock(Game.class);
        Main.setGame(game);
    }

    @Test
    public void testStartMethodHaveBeenCalled() {
        Main.main(new String[0]);
        verify(game, times(1)).start();
    }
}
