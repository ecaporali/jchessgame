package au.com.aitcollaboration.chessgame.controller;

import au.com.aitcollaboration.chessgame.Color;
import au.com.aitcollaboration.chessgame.exceptions.InvalidCoordinatesException;
import au.com.aitcollaboration.chessgame.model.game.structure.Board;
import au.com.aitcollaboration.chessgame.model.game.structure.Position;
import au.com.aitcollaboration.chessgame.model.game.structure.Square;
import au.com.aitcollaboration.chessgame.model.moves.PieceMoves;
import au.com.aitcollaboration.chessgame.model.moves.PlayerMoves;
import au.com.aitcollaboration.chessgame.model.pieces.King;
import au.com.aitcollaboration.chessgame.model.pieces.Piece;
import au.com.aitcollaboration.chessgame.model.player.Player;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.MockitoAnnotations.initMocks;
import static org.powermock.api.mockito.PowerMockito.doNothing;
import static org.powermock.api.mockito.PowerMockito.when;

public class GameTest {

    @Mock
    private Board board;
    @Mock
    private Rules rules;
    @Mock
    private Player player;
    @Mock
    private PlayerMoves playerMoves;

    private Game game;

    @Before
    public void setUp() throws Exception {
        initMocks(this);

        game = new Game(board, rules);
    }

    @Test
    public void playShouldStopWhenCheckMateIsTrue() throws Exception {
        prepareMatchersForPlayMethod();
        when(rules.isCheckMate(any(Color.class), any(Board.class))).thenReturn(true);

        Player[] players = new Player[]{player, player};
        game.play(players);

        verify(player).showCheckMateMessage();
        verify(player).showEndOfGameMessage();
    }

    @Test
    public void playShouldStopWhenMatchDrawIsTrue() throws Exception {
        prepareMatchersForPlayMethod();
        when(rules.isCheckMate(any(Color.class), any(Board.class))).thenReturn(false);
        when(rules.isMatchDraw()).thenReturn(true);

        Player[] players = new Player[]{player, player};
        game.play(players);

        verify(player).showMatchDrawMessage();
    }

    @Test
    public void validateFromSquareShouldNotReturnNullSquare() throws Exception {
        Square givenSquare = new Square(1, 1);

        when(player.getFromSquareCoordinate()).thenReturn(new int[]{1, 1});
        when(board.getSquareAtPosition(any(Position.class))).thenReturn(givenSquare);
        doNothing().when(rules).validateFromSquare(any(Square.class), any(PlayerMoves.class), any(Board.class));

        Square expectedSquare = game.validateFromSquare(player, getPlayerMovesMock());

        assertNotNull(expectedSquare);
        assertEquals(givenSquare, expectedSquare);
    }

    @Test
    public void getSquareShouldReturnAValidSquare() throws Exception {
        Square givenSquare = new Square(1, 1);
        when(board.getSquareAtPosition(any(Position.class))).thenReturn(givenSquare);

        Square expectedSquare = game.getSquare(new int[]{1, 1});
        assertNotNull(expectedSquare);
        assertEquals(givenSquare, expectedSquare);
    }

    @Test(expected = InvalidCoordinatesException.class)
    public void getSquareShouldThrowExceptionWhenCoordinatesAreNull() {
        Square givenSquare = new Square(1, 1);
        when(board.getSquareAtPosition(any(Position.class))).thenReturn(givenSquare);

        game.getSquare(null);
    }

    @Test(expected = InvalidCoordinatesException.class)
    public void getSquareShouldThrowExceptionWhenCoordinatesSizeIsNotOne() {
        Square givenSquare = new Square(1, 1);
        when(board.getSquareAtPosition(any(Position.class))).thenReturn(givenSquare);

        game.getSquare(new int[0]);
    }

    @Test
    public void getValidToSquareShouldReturnNotNullSquare() throws Exception {
        Square givenSquare = new Square(1, 1);
        givenSquare.setPiece(new King(Color.WHITE));

        when(player.getToSquareCoordinate()).thenReturn(new int[]{1, 1});
        when(board.getSquareAtPosition(any(Position.class))).thenReturn(givenSquare);

        Square expectedSquare = game.getValidToSquare(player, getPieceMovesMock(givenSquare));

        assertNotNull(expectedSquare);
        assertEquals(givenSquare, expectedSquare);
    }

    @Test
    public void movePieceShouldAddBoardToHistoryAndCallMoveMethod() throws Exception {
        doNothing().when(board).movePiece(any(Square.class), any(Square.class));
        doNothing().when(rules).addToHistory(any(Board.class));

        Square fromSquare = new Square(0, 0);
        Square toSquare = new Square(1, 1);

        game.movePiece(fromSquare, toSquare);

        verify(rules, times(1)).addToHistory(board);
        verify(board, times(1)).movePiece(fromSquare, toSquare);
    }

    private PlayerMoves getPlayerMovesMock() {
        PlayerMoves playerMoves = new PlayerMoves();
        PieceMoves pieceMoves = getPieceMovesMock(new Square(1, 1));
        playerMoves.add(new King(Color.BLACK), pieceMoves);

        return playerMoves;
    }

    private PieceMoves getPieceMovesMock(Square givenSquare) {
        Square square = new Square(0, 0);
        square.setPiece(new King(Color.BLACK));
        PieceMoves pieceMoves = new PieceMoves(square);
        pieceMoves.add(givenSquare);
        return pieceMoves;
    }

    private void prepareMatchersForPlayMethod() {
        Square toSquare = new Square(1, 1);
        PieceMoves pieceMoves = new PieceMoves(new Square(0, 0));
        pieceMoves.add(toSquare);

        when(playerMoves.getValidPieceMovesFor(any(Piece.class))).thenReturn(pieceMoves);
        when(board.calculateCurrentPlayerMoves(any(Color.class))).thenReturn(playerMoves);
        when(board.getSquareAtPosition(any(Position.class))).thenReturn(toSquare);
        when(player.getFromSquareCoordinate()).thenReturn(new int[]{0, 0});
        when(player.getToSquareCoordinate()).thenReturn(new int[]{1, 1});
        when(player.getColor()).thenReturn(Color.BLACK);
    }
}
