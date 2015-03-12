package au.com.aitcollaboration.chessgame.game;

import au.com.aitcollaboration.chessgame.board.Board;
import au.com.aitcollaboration.chessgame.player.Color;
import au.com.aitcollaboration.chessgame.player.Players;
import au.com.aitcollaboration.chessgame.support.Constants;
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
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.initMocks;

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
        PowerMockito.mockStatic(Utils.class);
        PowerMockito.mockStatic(In.class);
        when(In.nextLine("question")).thenReturn("answer");
        game = new Game(board, rules, players);
    }

    @Test
    public void testAddPlayerMethodHaveBeenCalled(){
        game.playersSetUp();
        verify(players, times(2)).addHumanPlayer(anyString(), any(Color.class));
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
    public void testShouldReturnColorBlackWhenTossCoinSideNotMatched() throws Exception {
        String answer = Constants.COIN_HEAD;
        when(Utils.tossCoin(answer)).thenReturn(false);

        Color color = game.tossCoin();

        assertThat(color, is(Color.BLACK));
        PowerMockito.verifyPrivate(game).invoke("getAnswer", anyString());
    }
}
