package au.com.aitcollaboration.chessgame.controller;

import au.com.aitcollaboration.chessgame.Color;
import au.com.aitcollaboration.chessgame.model.game.structure.Board;
import au.com.aitcollaboration.chessgame.model.game.structure.Position;
import au.com.aitcollaboration.chessgame.model.game.structure.Square;
import au.com.aitcollaboration.chessgame.model.pieces.King;
import au.com.aitcollaboration.chessgame.model.player.ComputerPlayer;
import au.com.aitcollaboration.chessgame.model.player.HumanPlayer;
import au.com.aitcollaboration.chessgame.model.player.Player;
import au.com.aitcollaboration.chessgame.support.In;
import au.com.aitcollaboration.chessgame.support.Utils;
import au.com.aitcollaboration.chessgame.view.GameView;
import au.com.aitcollaboration.chessgame.view.exceptions.InvalidCoordinatesException;
import au.com.aitcollaboration.chessgame.view.exceptions.InvalidPositionException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.io.PrintStream;
import java.util.List;
import java.util.Map;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.initMocks;
import static org.powermock.api.mockito.PowerMockito.mockStatic;
import static org.powermock.api.mockito.PowerMockito.when;

@RunWith(PowerMockRunner.class)
@PrepareForTest({Utils.class, In.class})
public class GameControllerTest {

    @Mock
    private Board board;
    @Mock
    private GameView gameView;
    @Mock
    private PrintStream printStream;

    private PrintStream originalOut = System.out;

    private GameController gameController;

    @Before
    public void setUp() throws Exception {
        initMocks(this);
        mockStatic(Utils.class);
        mockStatic(In.class);

        setMockedSystemOut();

        gameController = new GameController(board, gameView);

        when(In.nextLine(anyString())).thenReturn("");
    }

    @Test
    public void testGetPlayersMapReturnsTwoHumanPlayers() {
        when(gameView.getNumericAnswer(any(String.class))).thenReturn(2);

        Map<Color, Player> colorPlayerMap = gameController.getPlayersMap();

        Player playerOne = colorPlayerMap.get(Color.WHITE);
        Player playerTwo = colorPlayerMap.get(Color.BLACK);

        assertThat(colorPlayerMap.size(), is(2));
        assertEquals(HumanPlayer.class, playerOne.getClass());
        assertEquals(HumanPlayer.class, playerTwo.getClass());
    }

    @Test
    public void testGetPlayersMapReturnsOneHumanAndOneComputerPlayers() {
        when(In.nextInt(anyString())).thenReturn(1);
        when(Utils.tossCoin(any(String.class))).thenReturn(true);

        Map<Color, Player> colorPlayerMap = gameController.getPlayersMap();

        Player playerOne = colorPlayerMap.get(Color.WHITE);
        Player playerTwo = colorPlayerMap.get(Color.BLACK);

        assertThat(colorPlayerMap.size(), is(2));
        assertEquals(playerOne.getClass(), HumanPlayer.class);
        assertEquals(playerTwo.getClass(), ComputerPlayer.class);
    }

    @Test
    public void testIsMultiPlayersShouldReturnTrueWhenMultiPlayersSelectionIsMoreThanOne() {
        when(gameView.getNumericAnswer(any(String.class))).thenReturn(2);

        boolean isMultiPlayers = gameController.isMultiPlayers();

        assertTrue(isMultiPlayers);
    }

    @Test
    public void testIsMultiPlayersShouldReturnFalseWhenMultiPlayersSelectionIsLessThanTwo() {
        when(In.nextInt(anyString())).thenReturn(1);

        boolean isMultiPlayers = gameController.isMultiPlayers();

        assertFalse(isMultiPlayers);
    }

    @Test
    public void testAddMoveToHistoryShouldIncreaseListSize() {
        assertThat(gameController.getMovesHistorySize(), is(0));
        gameController.addMoveToHistory();
        gameController.addMoveToHistory();
        assertThat(gameController.getMovesHistorySize(), is(2));
    }

    @Test
    public void testTossCoinShouldReturnColorWhiteWhenTossCoinIsTrue() {
        when(Utils.tossCoin(any(String.class))).thenReturn(true);

        Color whiteColor = gameController.tossCoin();

        assertThat(whiteColor, is(Color.WHITE));
    }

    @Test
    public void testTossCoinShouldReturnBlackWhiteWhenTossCoinIsFalse() {
        when(Utils.tossCoin(any(String.class))).thenReturn(false);

        Color whiteColor = gameController.tossCoin();

        assertThat(whiteColor, is(Color.BLACK));
    }

    @Test
    public void testGetFromSquareShouldReturnSquareContainingAPiece() throws InvalidPositionException {
        Square testSquare = new Square(0, 0);
        testSquare.setPiece(new King(Color.BLACK));

        when(In.nextLine(anyString())).thenReturn("A1");
        when(Utils.getConvertedPosition(any(String.class))).thenReturn(new int[]{0, 0});
        when(board.getSquareAtPosition(any(Position.class))).thenReturn(testSquare);

        Square square = gameController.getFromSquare();

        assertNotNull(square);
        assertTrue(square.hasPiece());
    }

    @Test(expected = InvalidCoordinatesException.class)
    public void testGetSquareFromCoordinatesShouldInvalidCoordinatesExceptionWhenNullCoordinates() {
        gameController.getSquareFromCoordinates(null);
    }

    @Test
    public void testGetValidCoordinatesShouldReturnValidCoordinates() throws InvalidPositionException {
        when(In.nextLine(anyString())).thenReturn("A1");
        when(Utils.getConvertedPosition(any(String.class))).thenReturn(new int[]{0, 0});
        int[] coordinates = gameController.getValidCoordinates("");

        assertNotNull(coordinates);
        assertEquals(coordinates.length, 2);
    }

    @Test
    public void testShouldReturnColorWhiteWhenTossCoinSideMatched() throws Exception {
        when(Utils.tossCoin(anyString())).thenReturn(true);

        Color color = gameController.tossCoin();

        assertEquals(color, Color.WHITE);
        assertFalse(color.equals(Color.BLACK));
        PowerMockito.verifyPrivate(gameController).invoke("getTextAnswer", anyString());
    }

    @Test
    public void testShouldReturnColorBlackWhenTossCoinSideNotMatched() throws Exception {
        when(Utils.tossCoin(anyString())).thenReturn(false);

        Color color = gameController.tossCoin();

        assertEquals(color, Color.BLACK);
        assertFalse(color.equals(Color.WHITE));
        PowerMockito.verifyPrivate(gameController).invoke("getTextAnswer", anyString());
    }

    private void setMockedSystemOut() {
        originalOut = System.out;
        System.setOut(printStream);
    }

    @After
    public void resetOut() {
        System.setOut(originalOut);
    }
}
