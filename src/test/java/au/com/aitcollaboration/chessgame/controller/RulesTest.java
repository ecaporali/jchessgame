package au.com.aitcollaboration.chessgame.controller;

import au.com.aitcollaboration.chessgame.Color;
import au.com.aitcollaboration.chessgame.exceptions.*;
import au.com.aitcollaboration.chessgame.model.game.structure.Board;
import au.com.aitcollaboration.chessgame.model.game.structure.Square;
import au.com.aitcollaboration.chessgame.model.moves.PieceMoves;
import au.com.aitcollaboration.chessgame.model.moves.PlayerMoves;
import au.com.aitcollaboration.chessgame.model.pieces.King;
import au.com.aitcollaboration.chessgame.model.pieces.Piece;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

public class RulesTest {

    @Mock
    private Board board;
    @Mock
    private PlayerMoves playerMoves;

    private Rules rules;

    @Before
    public void setUp() throws Exception {
        initMocks(this);
        rules = new Rules();
    }

    @Test
    public void isCheckMateShouldReturnTrueWhenOnePlayerIsInCheckMate() throws Exception {
        when(board.calculateCurrentPlayerMoves(any(Color.class))).thenReturn(playerMoves);
        when(playerMoves.hasEmptyMoves(any(Board.class), any(Color.class), any(PlayerMoves.class))).thenReturn(true);

        boolean isCheckMate = rules.isCheckMate(Color.BLACK, board);

        assertTrue(isCheckMate);
    }

    @Test
    public void isCheckMateShouldReturnFalseWhenOnePlayerHasPossibleMoves() throws Exception {
        when(board.calculateCurrentPlayerMoves(any(Color.class))).thenReturn(playerMoves);
        when(playerMoves.hasEmptyMoves(any(Board.class), any(Color.class),any(PlayerMoves.class))).thenReturn(false);

        boolean isCheckMate = rules.isCheckMate(Color.BLACK, board);

        assertFalse(isCheckMate);
    }

    @Test
    public void addToHistoryShouldIncrementHistoryList() throws Exception {
        rules.addToHistory(board);
        rules.addToHistory(board);

        assertThat(rules.historySize(), is(2));
    }

    @Test
    public void isMatchDrawShouldReturnTrueWhenAKingIsTheLastPieceInTheLastTenMoves() throws Exception {
        addToHistory(50);
        when(board.isEitherKingLastPieceStanding()).thenReturn(true);
        boolean expectedIsMatchDraw = rules.isMatchDraw();

        assertTrue(expectedIsMatchDraw);
    }

    @Test
    public void isMatchDrawShouldReturnFalseWhenAKingIsNotTheLastPieceInTheLastTenMoves() throws Exception {
        addToHistory(50);
        when(board.isEitherKingLastPieceStanding()).thenReturn(false);
        boolean expectedIsMatchDraw = rules.isMatchDraw();

        assertFalse(expectedIsMatchDraw);
    }

    @Test
    public void isMatchDrawShouldReturnFalseWhenHistoryMoveIsLessThanTen() throws Exception {
        addToHistory(9);
        boolean expectedIsMatchDraw = rules.isMatchDraw();

        assertFalse(expectedIsMatchDraw);
    }

    @Test
    public void validateFromSquareShouldAcceptMoveWhenFromSquareCanBeMoved() throws Exception {
        Square fromSquare = new Square(0, 0);
        Piece king = new King(Color.BLACK);
        fromSquare.setPiece(king);

        PieceMoves pieceMoves = new PieceMoves(fromSquare);

        Square square = new Square(1, 1);
        pieceMoves.add(square);

        when(playerMoves.contains(any(Square.class))).thenReturn(true);
        when(playerMoves.calculateValidPieceMoves(any(Piece.class), any(Board.class), any(PlayerMoves.class))).thenReturn(pieceMoves);

        rules.validateFromSquare(fromSquare, playerMoves, board);

        assertEquals(1, pieceMoves.size());
    }

    @Test(expected = PieceNotFoundException.class)
    public void validateFromSquareShouldThrowPieceNotFoundExceptionWhenFromSquareDoesNotHavePiece() throws Exception {
        Square fromSquare = new Square(0, 0);
        rules.validateFromSquare(fromSquare, playerMoves, board);
    }

    @Test(expected = InvalidPieceException.class)
    public void validateFromSquareShouldThrowInvalidPieceExceptionWhenCurrentPieceIsAnOpponentPiece() throws Exception {
        Square fromSquare = new Square(0, 0);
        Piece king = new King(Color.BLACK);
        fromSquare.setPiece(king);

        doThrow(InvalidPieceException.class).when(playerMoves).calculateValidPieceMoves(any(Piece.class), any(Board.class), any(PlayerMoves.class));

        rules.validateFromSquare(fromSquare, playerMoves, board);
    }

    @Test(expected = PieceCannotBeMovedException.class)
    public void validateFromSquareShouldThrowPieceCannotBeMovedExceptionWhenPieceMovesAreEmpty() throws Exception {
        Square fromSquare = new Square(0, 0);
        Piece king = new King(Color.BLACK);
        fromSquare.setPiece(king);

        doThrow(PieceCannotBeMovedException.class).when(playerMoves).calculateValidPieceMoves(any(Piece.class), any(Board.class), any(PlayerMoves.class));

        rules.validateFromSquare(fromSquare, playerMoves, board);
    }

    @Test(expected = KingInCheckException.class)
    public void validateFromSquareShouldThrowKingInCheckExceptionWhenPieceMovesAreEmpty() throws Exception {
        Square fromSquare = new Square(0, 0);
        Piece king = new King(Color.BLACK);
        fromSquare.setPiece(king);

        PieceMoves pieceMoves = new PieceMoves(fromSquare);

        PlayerMoves opponentMoves = new PlayerMoves();
        PieceMoves opponentPieceMoves = new PieceMoves(new Square(1, 1));
        opponentPieceMoves.add(fromSquare);
        opponentMoves.add(new King(Color.WHITE), opponentPieceMoves);

        when(board.calculateOpponentPlayerMoves(any(Color.class))).thenReturn(opponentMoves);
        when(board.getCurrentKingSquare(any(Color.class))).thenReturn(fromSquare);
        when(playerMoves.calculateValidPieceMoves(any(Piece.class), any(Board.class), any(PlayerMoves.class))).thenReturn(pieceMoves);

        rules.validateFromSquare(fromSquare, playerMoves, board);
    }

    @Test(expected = KingInDangerException.class)
    public void validateFromSquareShouldThrowKingInDangerExceptionWhenPieceMovesAreEmpty() throws Exception {
        Square fromSquare = new Square(0, 0);
        Piece king = new King(Color.BLACK);
        fromSquare.setPiece(king);

        PieceMoves pieceMoves = new PieceMoves(fromSquare);

        PlayerMoves opponentMoves = new PlayerMoves();

        when(board.calculateOpponentPlayerMoves(any(Color.class))).thenReturn(opponentMoves);
        when(board.getCurrentKingSquare(any(Color.class))).thenReturn(fromSquare);
        when(playerMoves.calculateValidPieceMoves(any(Piece.class), any(Board.class), any(PlayerMoves.class))).thenReturn(pieceMoves);

        rules.validateFromSquare(fromSquare, playerMoves, board);
    }

    private void addToHistory(int size) {
        for (int i = 0; i < size; i++) {
            rules.addToHistoryTest(board);
        }
    }
}
