package au.com.aitcollaboration.chessgame.game;

import au.com.aitcollaboration.chessgame.board.Board;
import au.com.aitcollaboration.chessgame.player.*;
import au.com.aitcollaboration.chessgame.support.In;
import au.com.aitcollaboration.chessgame.support.Utils;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.initMocks;
import static org.powermock.api.mockito.PowerMockito.mockStatic;
import static org.powermock.api.mockito.PowerMockito.when;

@RunWith(PowerMockRunner.class)
@PrepareForTest({Utils.class, In.class})
public class GameTest {

    @Mock
    private Board board;
    @Mock
    private Rules rules;
    @Mock
    private Players players;

    private Game game;

    @Before
    public void setUp() throws Exception {
        initMocks(this);
        mockStatic(Utils.class);
        mockStatic(In.class);

        game = new Game(board, rules, players);

        when(In.nextLine(anyString())).thenReturn("answer");
    }

    @Test
    public void testAddPlayerMethodHaveBeenCalled() {
        when(In.nextInt(anyString())).thenReturn(3);

        game.playersSetUp();

        verify(players, times(2)).add(any(Player.class));
    }

    @Test
    public void testAddPlayerMethodHaveBeenCalledForSinglePlayer() {
        when(In.nextInt(anyString())).thenReturn(1);

        game.playersSetUp();

        verify(players, times(2)).add(any(Player.class));
    }

    @Test
    public void testShouldNotCallRunGameWhenGameOverIsTrue() {
        game.setGameOver(true);
        game.runGame();

        verify(players, times(0)).play();
    }

    @Test
    public void testShouldCallRunGameWhenGameOverIsFalse() {
        when(rules.isCheckMate(board)).thenReturn(false);
        when(rules.isMatchDraw(any(List.class))).thenReturn(true);

        game.runGame();

        verify(players, times(1)).play();
    }

    @Test
    public void testShouldReturnGameOverTrueWhenIsCheckMateOrIsMatchDraw() {
        when(rules.isCheckMate(board)).thenReturn(false);
        when(rules.isMatchDraw(any(List.class))).thenReturn(true);

        boolean gameOver = game.isGameOver();

        assertThat(gameOver, is(true));
    }

    @Test
    public void testShouldReturnColorWhiteWhenTossCoinSideMatched() throws Exception {
        when(Utils.tossCoin(anyString())).thenReturn(true);

        Color color = game.tossCoin();

        assertEquals(color, Color.WHITE);
        assertFalse(color.equals(Color.BLACK));
        PowerMockito.verifyPrivate(game).invoke("getTextAnswer", anyString());
    }

    @Test
    public void testShouldReturnColorBlackWhenTossCoinSideNotMatched() throws Exception {
        when(Utils.tossCoin(anyString())).thenReturn(false);

        Color color = game.tossCoin();

        assertEquals(color, Color.BLACK);
        assertFalse(color.equals(Color.WHITE));
        PowerMockito.verifyPrivate(game).invoke("getTextAnswer", anyString());
    }
}
