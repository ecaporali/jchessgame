package au.com.aitcollaboration.chessgame.controller;

import au.com.aitcollaboration.chessgame.Color;
import au.com.aitcollaboration.chessgame.exceptions.InvalidCoordinatesException;
import au.com.aitcollaboration.chessgame.exceptions.InvalidPositionException;
import au.com.aitcollaboration.chessgame.model.game.structure.Board;
import au.com.aitcollaboration.chessgame.model.game.structure.Position;
import au.com.aitcollaboration.chessgame.model.game.structure.Square;
import au.com.aitcollaboration.chessgame.model.pieces.King;
import au.com.aitcollaboration.chessgame.model.player.ComputerPlayer;
import au.com.aitcollaboration.chessgame.model.player.HumanPlayer;
import au.com.aitcollaboration.chessgame.model.player.Player;
import au.com.aitcollaboration.chessgame.model.player.Players;
import au.com.aitcollaboration.chessgame.support.Utils;
import au.com.aitcollaboration.chessgame.view.GameView;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.util.Map;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.anyString;
import static org.mockito.MockitoAnnotations.initMocks;
import static org.powermock.api.mockito.PowerMockito.mockStatic;
import static org.powermock.api.mockito.PowerMockito.when;

@RunWith(PowerMockRunner.class)
@PrepareForTest(Utils.class)
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

        game = new Game(board, rules, players);
    }

//    @Test
//    public void testGetPlayersMapReturnsTwoHumanPlayers() {
//        when(gameView.getNumericAnswer(any(String.class))).thenReturn(2);
//
//        Map<Color, Player> colorPlayerMap = game.getPlayersMap();
//
//        Player playerOne = colorPlayerMap.get(Color.WHITE);
//        Player playerTwo = colorPlayerMap.get(Color.BLACK);
//
//        assertThat(colorPlayerMap.size(), is(2));
//        assertEquals(HumanPlayer.class, playerOne.getClass());
//        assertEquals(HumanPlayer.class, playerTwo.getClass());
//    }
//
//    @Test
//    public void testGetPlayersMapReturnsOneHumanAndOneComputerPlayers() {
//        when(gameView.getNumericAnswer(anyString())).thenReturn(1);
//        when(Utils.tossCoin(any(String.class))).thenReturn(true);
//
//        Map<Color, Player> colorPlayerMap = game.getPlayersMap();
//
//        Player playerOne = colorPlayerMap.get(Color.WHITE);
//        Player playerTwo = colorPlayerMap.get(Color.BLACK);
//
//        assertThat(colorPlayerMap.size(), is(2));
//        assertEquals(playerOne.getClass(), HumanPlayer.class);
//        assertEquals(playerTwo.getClass(), ComputerPlayer.class);
//    }
//
//    @Test
//    public void testIsMultiPlayersShouldReturnTrueWhenMultiPlayersSelectionIsMoreThanOne() {
//        when(gameView.getNumericAnswer(any(String.class))).thenReturn(2);
//
//        boolean isMultiPlayers = game.isMultiPlayers();
//
//        assertTrue(isMultiPlayers);
//    }
//
//    @Test
//    public void testIsMultiPlayersShouldReturnFalseWhenMultiPlayersSelectionIsLessThanTwo() {
//        when(gameView.getNumericAnswer(anyString())).thenReturn(1);
//
//        boolean isMultiPlayers = game.isMultiPlayers();
//
//        assertFalse(isMultiPlayers);
//    }
//
//    @Test
//    public void testAddMoveToHistoryShouldIncreaseListSize() {
//        assertThat(game.getMovesHistorySize(), is(0));
//        game.addMoveToHistory();
//        game.addMoveToHistory();
//        assertThat(game.getMovesHistorySize(), is(2));
//    }
//
//    @Test
//    public void testTossCoinShouldReturnColorWhiteWhenTossCoinIsTrue() {
//        when(Utils.tossCoin(any(String.class))).thenReturn(true);
//
//        Color whiteColor = game.tossCoin();
//
//        assertThat(whiteColor, is(Color.WHITE));
//    }
//
//    @Test
//    public void testTossCoinShouldReturnBlackWhiteWhenTossCoinIsFalse() {
//        when(Utils.tossCoin(any(String.class))).thenReturn(false);
//
//        Color whiteColor = game.tossCoin();
//
//        assertThat(whiteColor, is(Color.BLACK));
//    }
//
//    @Test
//    public void testGetFromSquareShouldReturnSquareContainingAPiece() throws InvalidPositionException {
//        Square testSquare = new Square(0, 0);
//        testSquare.setPiece(new King(Color.BLACK));
//
//        when(gameView.getTextAnswer(anyString())).thenReturn("A1");
//        when(Utils.toBoardPosition(any(String.class))).thenReturn(new int[]{0, 0});
//        when(board.getSquareAtPosition(any(Position.class))).thenReturn(testSquare);
//
//        Square square = game.getFromSquare();
//
//        assertNotNull(square);
//        assertTrue(square.hasPiece());
//    }
//
//    @Test(expected = InvalidCoordinatesException.class)
//    public void testGetSquareFromCoordinatesShouldThrowInvalidCoordinatesExceptionWhenNullCoordinates() {
//        game.getSquareFromCoordinates(null);
//    }
//
//    @Test
//    public void testGetValidCoordinatesShouldReturnValidCoordinates() throws InvalidPositionException {
//        when(gameView.getTextAnswer(anyString())).thenReturn("A1");
//        when(Utils.toBoardPosition(any(String.class))).thenReturn(new int[]{0, 0});
//        int[] coordinates = game.getValidCoordinates("");
//
//        assertNotNull(coordinates);
//        assertEquals(coordinates.length, 2);
//    }
//
//    @Test
//    public void testShouldReturnColorWhiteWhenTossCoinSideMatched() throws Exception {
//        when(Utils.tossCoin(anyString())).thenReturn(true);
//
//        Color color = game.tossCoin();
//
//        assertEquals(color, Color.WHITE);
//        assertFalse(color.equals(Color.BLACK));
//        PowerMockito.verifyPrivate(game).invoke("getTextAnswer", anyString());
//    }
//
//    @Test
//    public void testShouldReturnColorBlackWhenTossCoinSideNotMatched() throws Exception {
//        when(Utils.tossCoin(anyString())).thenReturn(false);
//
//        Color color = game.tossCoin();
//
//        assertEquals(color, Color.BLACK);
//        assertFalse(color.equals(Color.WHITE));
//        PowerMockito.verifyPrivate(game).invoke("getTextAnswer", anyString());
//    }
}
