package au.com.aitcollaboration.chessgame.pieces.movement;

import au.com.aitcollaboration.chessgame.board.Board;
import au.com.aitcollaboration.chessgame.board.Position;
import au.com.aitcollaboration.chessgame.board.Square;
import au.com.aitcollaboration.chessgame.pieces.*;
import au.com.aitcollaboration.chessgame.player.Color;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;

public class RangeMovementTest {

    private Piece knight;
    private Piece king;

    private Board board;
    private RangeMovement rangeMovement;

    @Before
    public void setUp() throws Exception {
        rangeMovement = new RangeMovement();
        knight = new Knight(Color.BLACK);
        king = new King(Color.BLACK);
        board = new Board();
        board.clear();
    }

    @Test
    public void testKnightShouldMoveAndStayInsideTheBoard() {
        Square firstSquare = getSquareAt(0, 0);

        firstSquare.setPiece(knight);

        PieceMoves pieceMoves = rangeMovement.getMoves(board, knight);
        assertThat(pieceMoves.size(), is(2));
    }

    @Test
    public void testKingShouldMoveAndStayInsideTheBoard() {
        Square firstSquare = getSquareAt(0, 0);

        firstSquare.setPiece(king);

        PieceMoves pieceMoves = rangeMovement.getMoves(board, king);
        assertThat(pieceMoves.size(), is(3));
    }

    @Test
    public void testKnightShouldEatOpponentPiece() {
        Square firstSquare = getSquareAt(0, 0);
        Square opponentSquare = getSquareAt(2, 1);

        firstSquare.setPiece(knight);
        opponentSquare.setPiece(new Rook(Color.WHITE));

        PieceMoves pieceMoves = rangeMovement.getMoves(board, knight);

        assertFalse(pieceMoves.isEmpty());
        assertThat(pieceMoves.size(), is(2));
    }

    @Test
    public void testKingShouldEatOpponentPieceAndStop() {
        Square firstSquare = getSquareAt(0, 0);
        Square opponentSquare = getSquareAt(1, 1);

        firstSquare.setPiece(king);
        opponentSquare.setPiece(new Rook(Color.WHITE));

        PieceMoves pieceMoves = rangeMovement.getMoves(board, king);

        assertFalse(pieceMoves.isEmpty());
        assertThat(pieceMoves.size(), is(3));
    }

    @Test
    public void testKnightShouldNotEatSameColorPiece() {
        Square firstSquare = getSquareAt(0, 0);
        Square buddySquare = getSquareAt(2, 1);

        firstSquare.setPiece(knight);
        buddySquare.setPiece(new Rook(Color.BLACK));

        PieceMoves pieceMoves = rangeMovement.getMoves(board, knight);

        assertFalse(pieceMoves.isEmpty());
        assertThat(pieceMoves.size(), is(1));
    }

    @Test
    public void testKingShouldNotEatSameColorPiece() {
        Square firstSquare = getSquareAt(0, 0);
        Square buddySquare = getSquareAt(1, 1);

        firstSquare.setPiece(king);
        buddySquare.setPiece(new Rook(Color.BLACK));

        PieceMoves pieceMoves = rangeMovement.getMoves(board, king);

        assertFalse(pieceMoves.isEmpty());
        assertThat(pieceMoves.size(), is(2));
    }

    private Square getSquareAt(int x, int y) {
        return board.getSquareAtPosition(new Position(x, y));
    }
}