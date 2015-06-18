package au.com.aitcollaboration.chessgame.model.moves;

import au.com.aitcollaboration.chessgame.Color;
import au.com.aitcollaboration.chessgame.exceptions.InvalidPieceException;
import au.com.aitcollaboration.chessgame.exceptions.PieceCannotBeMovedException;
import au.com.aitcollaboration.chessgame.model.game.structure.Board;
import au.com.aitcollaboration.chessgame.model.game.structure.Square;
import au.com.aitcollaboration.chessgame.model.pieces.King;
import au.com.aitcollaboration.chessgame.model.pieces.Piece;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.any;
import static org.mockito.MockitoAnnotations.initMocks;
import static org.powermock.api.mockito.PowerMockito.doNothing;
import static org.powermock.api.mockito.PowerMockito.when;

public class PlayerMovesTest {

    @Mock
    private PieceMoves pieceMoves;
    @Mock
    private Board board;

    private PlayerMoves playerMoves;

    @Before
    public void setUp() throws Exception {
        initMocks(this);
        playerMoves = new PlayerMoves();
    }

    @Test
    public void containsShouldReturnTrueWhenPlayerMovesContainSquare() throws Exception {
        when(pieceMoves.contains(any(Square.class))).thenReturn(true);
        playerMoves.add(new King(Color.BLACK), pieceMoves);

        boolean contained = playerMoves.contains(new Square(0, 0));

        assertTrue(contained);
    }

    @Test
    public void containsShouldReturnFalseWhenPlayerMovesDoesNotContainSquare() throws Exception {
        when(pieceMoves.contains(any(Square.class))).thenReturn(false);
        playerMoves.add(new King(Color.BLACK), pieceMoves);

        boolean contained = playerMoves.contains(new Square(0, 0));

        assertFalse(contained);
    }

    @Test
    public void calculateValidPieceMovesShouldReturnOnlyValidPieceMovesOnBoard() throws Exception {
        Piece king = new King(Color.BLACK);
        playerMoves.add(king, pieceMoves);

        when(pieceMoves.isEmpty()).thenReturn(false);
        when(board.getCurrentKingSquare(any(Color.class))).thenReturn(new Square(1, 1));
        doNothing().when(pieceMoves).toValidMoves(any(Board.class), any(Square.class), any(PlayerMoves.class));

        PieceMoves expectedPieceMoves = playerMoves.calculateValidPieceMoves(king, board, new PlayerMoves());

        assertFalse(expectedPieceMoves.isEmpty());
    }

    @Test(expected = InvalidPieceException.class)
    public void calculateValidPieceMovesShouldThrowInvalidPieceExceptionWhenPieceIsOpponent() throws Exception {
        Piece king = new King(Color.BLACK);
        playerMoves.add(king, pieceMoves);

        playerMoves.calculateValidPieceMoves(new King(Color.WHITE), board, new PlayerMoves());
    }

    @Test(expected = PieceCannotBeMovedException.class)
    public void calculateValidPieceMovesShouldThrowPieceCannotBeMovedExceptionWhenPieceIsOpponent() throws Exception {
        when(pieceMoves.isEmpty()).thenReturn(true);
        Piece king = new King(Color.BLACK);
        playerMoves.add(king, pieceMoves);

        playerMoves.calculateValidPieceMoves(king, board, new PlayerMoves());
    }

    @Test
    public void hasEmptyMovesShouldReturnTrueWhenPlayerHasNoPieceMoves() throws Exception {
        boolean emptyMoves = playerMoves.hasEmptyMoves(board, Color.WHITE, new PlayerMoves());
        assertTrue(emptyMoves);
    }

    @Test
    public void hasEmptyMovesShouldReturnTrueWhenPlayerHasNoValidPieceMoves() throws Exception {
        playerMoves.add(new King(Color.WHITE), pieceMoves);

        doNothing().when(pieceMoves).toValidMoves(any(Board.class), any(Square.class), any(PlayerMoves.class));
        when(pieceMoves.isEmpty()).thenReturn(true);

        boolean emptyMoves = playerMoves.hasEmptyMoves(board, Color.WHITE, new PlayerMoves());
        assertTrue(emptyMoves);
    }

    @Test
    public void hasEmptyMovesShouldReturnFalseWhenPlayerHasStillValidPieceMoves() throws Exception {
        playerMoves.add(new King(Color.WHITE), pieceMoves);

        doNothing().when(pieceMoves).toValidMoves(any(Board.class), any(Square.class), any(PlayerMoves.class));
        when(pieceMoves.isEmpty()).thenReturn(false);

        boolean emptyMoves = playerMoves.hasEmptyMoves(board, Color.WHITE, new PlayerMoves());
        assertFalse(emptyMoves);
    }
}