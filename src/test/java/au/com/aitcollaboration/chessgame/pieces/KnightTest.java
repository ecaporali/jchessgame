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

public class KnightTest {

    private Piece knight;
    private Board board;

    @Before
    public void setUp() throws Exception {
        knight = new Knight(Color.BLACK);
        board = new Board();
        board.clear();
    }

    @Test
    public void testShouldMoveAndStayInsideTheBoard() {
        Square first = board.getSquareAtPosition(new Position(0, 0));
        first.setPiece(knight);

        ValidMoves validMoves = knight.getValidMovesOn(board);
        assertThat(validMoves.size(), is(2));
    }

    @Test
    public void testShouldEatOpponentPiece() {
        Piece opponentColor = new Rook(Color.WHITE);
        Square first = board.getSquareAtPosition(new Position(0, 2));
        Square random = board.getSquareAtPosition(new Position(1, 0));

        first.setPiece(knight);
        random.setPiece(opponentColor);

        ValidMoves validMoves = knight.getValidMovesOn(board);

        assertFalse(validMoves.isEmpty());
        assertThat(validMoves.size(), is(4));
    }

    @Test
    public void testShouldNotEatSameColorPiece() {
        Piece sameColor = new Rook(Color.BLACK);
        Square first = board.getSquareAtPosition(new Position(0, 2));
        Square random = board.getSquareAtPosition(new Position(1, 0));

        first.setPiece(knight);
        random.setPiece(sameColor);

        ValidMoves validMoves = knight.getValidMovesOn(board);

        assertFalse(validMoves.isEmpty());
        assertThat(validMoves.size(), is(3));
    }
}
