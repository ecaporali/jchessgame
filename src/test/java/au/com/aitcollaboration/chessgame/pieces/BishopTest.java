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

public class BishopTest {

    private Piece bishop;
    private Board board;

    @Before
    public void setUp() throws Exception {
        bishop = new Bishop(Color.BLACK);
        board = new Board();
        board.clear();
    }

    @Test
    public void testShouldMoveAndStayInsideTheBoardWhenMovementIsFree() {
        Square first = board.getSquareAtPosition(new Position(0, 0));
        first.positionPiece(bishop);

        Moves moves = bishop.getValidMovesOn(board);
        assertThat(moves.size(), is(7));
    }

    @Test
    public void testShouldEatOpponentPieceAndStop() {
        Piece opponentColor = new Rook(Color.WHITE);
        Square first = board.getSquareAtPosition(new Position(0, 0));
        Square random = board.getSquareAtPosition(new Position(5, 5));

        first.positionPiece(bishop);
        random.positionPiece(opponentColor);

        Moves moves = bishop.getValidMovesOn(board);

        assertFalse(moves.isEmpty());
        assertThat(moves.size(), is(5));
    }

    @Test
    public void testShouldNotEatSameColorPiece() {
        Piece sameColor = new Rook(Color.BLACK);
        Square first = board.getSquareAtPosition(new Position(0, 0));
        Square random = board.getSquareAtPosition(new Position(5, 5));

        first.positionPiece(bishop);
        random.positionPiece(sameColor);

        Moves moves = bishop.getValidMovesOn(board);

        assertFalse(moves.isEmpty());
        assertThat(moves.size(), is(4));
    }
}
