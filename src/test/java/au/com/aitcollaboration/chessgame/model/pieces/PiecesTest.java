package au.com.aitcollaboration.chessgame.model.pieces;

import au.com.aitcollaboration.chessgame.Color;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class PiecesTest {

    private Pieces pieces = new Pieces(Color.BLACK);

    @Test
    public void testPiecesShouldAddPiece() throws Exception {
        assertThat(pieces.size(), is(0));
        pieces.add(new King(Color.BLACK));
        pieces.add(new Queen(Color.BLACK));

        assertThat(pieces.size(), is(2));
    }

    @Test
    public void testPiecesShouldRemovePiece() throws Exception {
        Piece king = new King(Color.BLACK);
        Piece queen = new Queen(Color.BLACK);
        pieces.add(king);
        pieces.add(queen);
        assertThat(pieces.size(), is(2));

        pieces.remove(king);
        pieces.remove(queen);

        assertThat(pieces.size(), is(0));
    }

    @Test
    public void testPiecesIsSameColor() throws Exception {
        boolean sameColor = pieces.isColor(Color.BLACK);

        assertTrue(sameColor);
    }

    @Test
    public void testPiecesIsDifferentColor() throws Exception {
        boolean differentColor = pieces.isColor(Color.WHITE);

        assertFalse(differentColor);
    }

    @Test
    public void testPiecesShouldReturnSearchedPiece() throws Exception {
        Piece king = new King(Color.BLACK);
        pieces.add(king);
        Piece expectedKing = pieces.getKing();

        assertEquals(king, expectedKing);
    }

    @Test
    public void testPiecesShouldContainPiece() throws Exception {
        Piece king = new King(Color.BLACK);
        pieces.add(king);

        boolean containsPiece = pieces.contains(king);

        assertTrue(containsPiece);
    }

    @Test
    public void testPiecesShouldNotContainPiece() throws Exception {
        Piece king = new King(Color.BLACK);
        Piece queen = new Queen(Color.BLACK);
        pieces.add(king);

        boolean notContainsPiece = pieces.contains(queen);

        assertFalse(notContainsPiece);
    }
}
