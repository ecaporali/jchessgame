package au.com.aitcollaboration.chessgame.configuration;

import au.com.aitcollaboration.chessgame.Color;
import au.com.aitcollaboration.chessgame.model.player.ComputerPlayer;
import au.com.aitcollaboration.chessgame.model.player.HumanPlayer;
import au.com.aitcollaboration.chessgame.model.player.Player;
import au.com.aitcollaboration.chessgame.support.Constants;
import au.com.aitcollaboration.chessgame.support.Utils;
import au.com.aitcollaboration.chessgame.view.GameView;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import static junit.framework.TestCase.assertFalse;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;
import static org.mockito.Matchers.any;
import static org.mockito.MockitoAnnotations.initMocks;
import static org.powermock.api.mockito.PowerMockito.mockStatic;
import static org.powermock.api.mockito.PowerMockito.when;

@RunWith(PowerMockRunner.class)
@PrepareForTest(Utils.class)
public class GameConfigTest {

    @Mock
    private GameView gameView;

    private GameConfig gameConfig;

    @Before
    public void setUp() throws Exception {
        initMocks(this);
        mockStatic(Utils.class);
        gameConfig = new GameConfig(gameView);
    }

    @Test
    public void createPlayersShouldReturnTwoHumanPlayers() throws Exception {
        when(gameView.getNumericAnswer(any(String.class))).thenReturn(2);
        when(gameView.getTextAnswer(any(String.class))).thenReturn(Constants.COIN_TAIL);
        when(Utils.tossCoin()).thenReturn(Constants.COIN_TAIL);

        Player[] players = gameConfig.createPlayers();

        Player playerOne = players[0];
        Player playerTwo = players[1];

        assertThat(players.length, is(2));
        assertEquals(HumanPlayer.class, playerOne.getClass());
        assertEquals(HumanPlayer.class, playerTwo.getClass());
    }

    @Test
    public void createPlayersShouldReturnOneHumanAndOneComputerPlayers() throws Exception {
        when(gameView.getNumericAnswer(any(String.class))).thenReturn(1);
        when(gameView.getTextAnswer(any(String.class))).thenReturn(Constants.COIN_TAIL);
        when(Utils.tossCoin()).thenReturn(Constants.COIN_TAIL);

        Player[] players = gameConfig.createPlayers();

        Player playerOne = players[0];
        Player playerTwo = players[1];

        assertThat(players.length, is(2));
        assertEquals(HumanPlayer.class, playerOne.getClass());
        assertEquals(ComputerPlayer.class, playerTwo.getClass());
    }

    @Test
    public void isMultiPlayersShouldReturnTrueWhenMultiPlayersSelectionIsMoreThanOne() {
        when(gameView.getNumericAnswer(any(String.class))).thenReturn(2);

        boolean isMultiPlayers = gameConfig.isMultiPlayers();

        assertTrue(isMultiPlayers);
    }

    @Test
    public void isMultiPlayersShouldReturnFalseWhenMultiPlayersSelectionIsLessThanTwo() {
        when(gameView.getNumericAnswer(any(String.class))).thenReturn(1);

        boolean isMultiPlayers = gameConfig.isMultiPlayers();

        assertFalse(isMultiPlayers);
    }

    @Test
    public void testTossCoinShouldReturnColorWhiteWhenTossCoinIsTrue() {
        when(gameView.getTextAnswer(any(String.class))).thenReturn(Constants.COIN_TAIL);
        when(Utils.tossCoin()).thenReturn(Constants.COIN_TAIL);

        Color whiteColor = gameConfig.tossCoin();

        assertThat(whiteColor, is(Color.WHITE));
    }

    @Test
    public void testTossCoinShouldReturnBlackWhiteWhenTossCoinIsFalse() {
        when(gameView.getTextAnswer(any(String.class))).thenReturn(Constants.COIN_TAIL);
        when(Utils.tossCoin()).thenReturn(Constants.COIN_HEAD);

        Color whiteColor = gameConfig.tossCoin();

        assertThat(whiteColor, is(Color.BLACK));
    }
}