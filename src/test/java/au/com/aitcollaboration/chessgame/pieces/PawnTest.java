package au.com.aitcollaboration.chessgame.pieces;

import au.com.aitcollaboration.chessgame.board.Board;
import au.com.aitcollaboration.chessgame.board.Position;
import au.com.aitcollaboration.chessgame.board.Square;
import au.com.aitcollaboration.chessgame.player.Color;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

public class PawnTest {

    private Piece pawn;
    private Board board;

    @Before
    public void setUp() throws Exception {
        pawn = new Pawn(Color.BLACK);
        board = new Board();
        board.clear();
    }

    @Test
    public void testShouldMoveOneSquareAhead() {
        Square pieceSquare = board.getSquareAtPosition(new Position(0, 0));
        pieceSquare.setPiece(pawn);

        ValidMoves validMoves = pawn.getValidMovesOn(board);
        assertThat(validMoves.size(), is(1));
    }

    @Test
    public void testShouldEatOpponentPiece() {
        Square pieceSquare = board.getSquareAtPosition(new Position(0, 1));
        Square randomSquare = board.getSquareAtPosition(new Position(1, 0));

        pieceSquare.setPiece(pawn);

        Piece opponentPiece = new Rook(Color.WHITE);

        randomSquare.setPiece(opponentPiece);

        ValidMoves validMoves = pawn.getValidMovesOn(board);

        assertFalse(validMoves.isEmpty());
        assertThat(validMoves.size(), is(2));
    }

    @Test
    public void testShouldNotEatSameColorPiece() {
        Piece sameColor = new Rook(Color.BLACK);
        Square pieceSquare = board.getSquareAtPosition(new Position(0, 1));
        Square randomSquare = board.getSquareAtPosition(new Position(1, 0));

        pieceSquare.setPiece(pawn);
        randomSquare.setPiece(sameColor);

        ValidMoves validMoves = pawn.getValidMovesOn(board);

        assertFalse(validMoves.isEmpty());
        assertThat(validMoves.size(), is(1));
    }

    @Test
    public void testShouldEatShouldStopWhenHavePieceInFront() {
        Square pieceSquare = board.getSquareAtPosition(new Position(0, 1));
        Square frontSquare = board.getSquareAtPosition(new Position(1, 1));

        pieceSquare.setPiece(pawn);

        Piece opponentPiece = new Rook(Color.WHITE);

        frontSquare.setPiece(opponentPiece);

        ValidMoves validMoves = pawn.getValidMovesOn(board);

        assertTrue(validMoves.isEmpty());
        assertThat(validMoves.size(), is(0));
    }
}
