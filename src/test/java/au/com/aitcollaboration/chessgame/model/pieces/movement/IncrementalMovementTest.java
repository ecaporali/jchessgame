package au.com.aitcollaboration.chessgame.model.pieces.movement;

import au.com.aitcollaboration.chessgame.model.game.structure.Board;
import au.com.aitcollaboration.chessgame.model.game.structure.Position;
import au.com.aitcollaboration.chessgame.model.game.structure.Square;
import au.com.aitcollaboration.chessgame.model.moves.PieceMoves;
import au.com.aitcollaboration.chessgame.model.pieces.*;
import au.com.aitcollaboration.chessgame.Color;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;

public class IncrementalMovementTest {

    private Piece bishop;
    private Piece queen;
    private Piece rook;

    private Board board;
    private IncrementalMovement incrementalMovement;

    @Before
    public void setUp() throws Exception {
        incrementalMovement = new IncrementalMovement();
        bishop = new Bishop(Color.BLACK);
        queen = new Queen(Color.BLACK);
        rook = new Rook(Color.BLACK);
        board = new Board();
        board.clear();
    }

    @Test
    public void testBishopShouldMoveAndStayInsideTheBoardWhenMovementIsFree() {
        Square firstSquare = getSquareAt(0, 0);

        firstSquare.setPiece(bishop);

        PieceMoves pieceMoves = incrementalMovement.getMoves(board, bishop);
        assertThat(pieceMoves.size(), is(7));
    }

    @Test
    public void testQueenShouldMoveAndStayInsideTheBoardWhenMovementIsFree() {
        Square firstSquare = getSquareAt(0, 0);

        firstSquare.setPiece(queen);

        PieceMoves pieceMoves = incrementalMovement.getMoves(board, queen);
        assertThat(pieceMoves.size(), is(21));
    }

    @Test
    public void testRookShouldMoveAndStayInsideTheBoardWhenMovementIsFree() {
        Square firstSquare = getSquareAt(0, 0);

        firstSquare.setPiece(rook);

        PieceMoves pieceMoves = incrementalMovement.getMoves(board, rook);
        assertThat(pieceMoves.size(), is(14));
    }

    @Test
    public void testBishopShouldEatOpponentPieceAndStop() {
        Square firstSquare = getSquareAt(0, 0);
        Square opponentSquare = getSquareAt(5, 5);

        firstSquare.setPiece(bishop);
        opponentSquare.setPiece(new Rook(Color.WHITE));

        PieceMoves pieceMoves = incrementalMovement.getMoves(board, bishop);

        assertFalse(pieceMoves.isEmpty());
        assertThat(pieceMoves.size(), is(5));
    }

    @Test
    public void testQueenShouldEatOpponentPieceAndStop() {
        Square firstSquare = getSquareAt(0, 0);
        Square opponentSquare = getSquareAt(5, 5);

        firstSquare.setPiece(queen);
        opponentSquare.setPiece(new Rook(Color.WHITE));

        PieceMoves pieceMoves = incrementalMovement.getMoves(board, queen);

        assertFalse(pieceMoves.isEmpty());
        assertThat(pieceMoves.size(), is(19));
    }

    @Test
    public void testRookShouldEatOpponentPieceAndStop() {
        Square firstSquare = getSquareAt(0, 0);
        Square opponentSquare = getSquareAt(5, 0);

        firstSquare.setPiece(rook);
        opponentSquare.setPiece(new Rook(Color.WHITE));

        PieceMoves pieceMoves = incrementalMovement.getMoves(board, rook);

        assertFalse(pieceMoves.isEmpty());
        assertThat(pieceMoves.size(), is(12));
    }

    @Test
    public void testBishopShouldNotEatSameColorPiece() {
        Square firstSquare = getSquareAt(0, 0);
        Square buddySquare = getSquareAt(5, 5);

        firstSquare.setPiece(bishop);
        buddySquare.setPiece(new Rook(Color.BLACK));

        PieceMoves pieceMoves = incrementalMovement.getMoves(board, bishop);

        assertFalse(pieceMoves.isEmpty());
        assertThat(pieceMoves.size(), is(4));
    }

    @Test
    public void testQueenShouldNotEatSameColorPiece() {
        Square firstSquare = getSquareAt(0, 0);
        Square buddySquare = getSquareAt(5, 5);

        firstSquare.setPiece(queen);
        buddySquare.setPiece(new Rook(Color.BLACK));

        PieceMoves pieceMoves = incrementalMovement.getMoves(board, queen);

        assertFalse(pieceMoves.isEmpty());
        assertThat(pieceMoves.size(), is(18));
    }

    @Test
    public void testRookShouldNotEatSameColorPiece() {
        Square firstSquare = getSquareAt(0, 0);
        Square buddySquare = getSquareAt(5, 0);

        firstSquare.setPiece(rook);
        buddySquare.setPiece(new Rook(Color.BLACK));

        PieceMoves pieceMoves = incrementalMovement.getMoves(board, rook);

        assertFalse(pieceMoves.isEmpty());
        assertThat(pieceMoves.size(), is(11));
    }

    private Square getSquareAt(int x, int y) {
        return board.getSquareAtPosition(new Position(x, y));
    }
}