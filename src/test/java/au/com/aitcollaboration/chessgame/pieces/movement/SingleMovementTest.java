package au.com.aitcollaboration.chessgame.pieces.movement;

import au.com.aitcollaboration.chessgame.board.Board;
import au.com.aitcollaboration.chessgame.board.Position;
import au.com.aitcollaboration.chessgame.board.Square;
import au.com.aitcollaboration.chessgame.pieces.Pawn;
import au.com.aitcollaboration.chessgame.pieces.Piece;
import au.com.aitcollaboration.chessgame.pieces.PieceMoves;
import au.com.aitcollaboration.chessgame.pieces.Rook;
import au.com.aitcollaboration.chessgame.player.Color;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class SingleMovementTest {

    private Piece pawn;

    private Board board;
    private SingleMovement singleMovement;

    @Before
    public void setUp() throws Exception {
        singleMovement = new SingleMovement();
        pawn = new Pawn(Color.BLACK);
        board = new Board();
        board.clear();
    }

    @Test
    public void testKnightShouldMoveAndStayInsideTheBoard() {
        Square firstSquare = getSquareAt(0, 0);

        firstSquare.setPiece(pawn);

        PieceMoves pieceMoves = singleMovement.getMoves(board, pawn);
        assertThat(pieceMoves.size(), is(1));
    }

    @Test
    public void testKnightShouldEatOpponentPiece() {
        Square firstSquare = getSquareAt(0, 0);
        Square opponentSquare = getSquareAt(1, 1);

        firstSquare.setPiece(pawn);
        opponentSquare.setPiece(new Rook(Color.WHITE));

        PieceMoves pieceMoves = singleMovement.getMoves(board, pawn);

        assertFalse(pieceMoves.isEmpty());
        assertThat(pieceMoves.size(), is(2));
    }

    @Test
    public void testKnightShouldNotEatSameColorPiece() {
        Square firstSquare = getSquareAt(0, 0);
        Square buddySquare = getSquareAt(1, 1);

        firstSquare.setPiece(pawn);
        buddySquare.setPiece(new Rook(Color.BLACK));

        PieceMoves pieceMoves = singleMovement.getMoves(board, pawn);

        assertFalse(pieceMoves.isEmpty());
        assertThat(pieceMoves.size(), is(1));
    }

    @Test
    public void testKnightShouldNotMoveOrEatWhenOpponentPieceIsInFront() {
        Square firstSquare = getSquareAt(0, 0);
        Square opponentSquare = getSquareAt(1, 0);

        firstSquare.setPiece(pawn);
        opponentSquare.setPiece(new Rook(Color.WHITE));

        PieceMoves pieceMoves = singleMovement.getMoves(board, pawn);

        assertTrue(pieceMoves.isEmpty());
    }

    @Test
    public void testKnightShouldNotMoveOrEatWhenBuddyPieceIsInFront() {
        Square firstSquare = getSquareAt(0, 0);
        Square buddySquare = getSquareAt(1, 0);

        firstSquare.setPiece(pawn);
        buddySquare.setPiece(new Rook(Color.BLACK));

        PieceMoves pieceMoves = singleMovement.getMoves(board, pawn);

        assertTrue(pieceMoves.isEmpty());
    }

    private Square getSquareAt(int x, int y) {
        return board.getSquareAtPosition(new Position(x, y));
    }
}