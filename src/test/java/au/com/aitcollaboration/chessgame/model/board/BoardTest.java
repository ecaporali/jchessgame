package au.com.aitcollaboration.chessgame.model.board;

import au.com.aitcollaboration.chessgame.model.pieces.King;
import au.com.aitcollaboration.chessgame.model.pieces.Pawn;
import au.com.aitcollaboration.chessgame.model.pieces.Piece;
import au.com.aitcollaboration.chessgame.model.pieces.Pieces;
import au.com.aitcollaboration.chessgame.model.player.Color;
import org.junit.Before;
import org.junit.Test;

import java.util.Map;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class BoardTest {

    private Board board;

    @Before
    public void setUp() throws Exception {
        board = new Board();
    }

    @Test
    public void testShouldContainAllPiecesAfterInit() {
        Square[][] grid = board.getClonedGrid();

        int whitePieceCount = 0;
        int blackPieceCount = 0;
        int emptySquareCount = 0;

        for (Square[] squares : grid) {
            for (Square square : squares) {
                Piece piece = square.getPiece();
                if (piece == null) {
                    emptySquareCount++;
                } else {
                    if (piece.matches(Color.BLACK)) {
                        blackPieceCount++;
                    } else {
                        whitePieceCount++;
                    }
                }
            }
        }

        assertThat(blackPieceCount, is(16));
        assertThat(whitePieceCount, is(16));
        assertThat(emptySquareCount, is(32));
    }

    @Test
    public void testShouldContainBothColoursInMap() throws Exception {
        Map<Color, Pieces> piecesMap = board.getPiecesMap();
        Pieces whitePieces = piecesMap.get(Color.WHITE);
        Pieces blackPieces = piecesMap.get(Color.BLACK);

        assertNotNull(whitePieces);
        assertNotNull(blackPieces);
        assertThat(whitePieces.size(), is(16));
        assertThat(blackPieces.size(), is(16));
    }

    @Test
    public void testShouldReturnSquareWhenPositionMatches() throws Exception {
        Position position = new Position(1, 5);
        Square square = board.getSquareAtPosition(position);

        assertNotNull(square);
        assertNotNull(square.getPiece());
        assertEquals(square.getPiece().getClass(), Pawn.class);
        assertTrue(square.getPosition().equals(position));
    }

    @Test
    public void testShouldReturnSquareWhenPieceMatches() throws Exception {
        Square[][] grid = board.getClonedGrid();

        Piece king = grid[0][3].getPiece();

        Square square = board.getSquareOf(king);

        assertNotNull(square);
        assertNotNull(square.getPiece());
        assertEquals(square.getPiece().getClass(), King.class);
        assertTrue(square.getPiece().equals(king));
    }

    @Test
    public void testClearShouldClearTheBoardFromPieces() throws Exception {
        board.clear();

        for (Square[] squares : board.getClonedGrid()) {
            for (Square square : squares) {
                assertFalse(square.hasPiece());
            }
        }
    }
}
