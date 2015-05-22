package au.com.aitcollaboration.chessgame.model.game.structure;

import au.com.aitcollaboration.chessgame.model.pieces.King;
import au.com.aitcollaboration.chessgame.model.pieces.Piece;
import au.com.aitcollaboration.chessgame.Color;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class SquareTest {

    private Piece king;

    private Square square;

    @Before
    public void setUp() throws Exception {
        king = new King(Color.BLACK);
        square = new Square(1, 1);
    }

    @Test
    public void testSquareShouldContainPiece() throws Exception {
        square.setPiece(king);

        boolean containsPiece = square.contains(king);

        assertTrue(containsPiece);
    }

    @Test
    public void testSquareShouldBeAtSamePosition() throws Exception {
        boolean isAtPosition = square.isAt(new Position(1, 1));
        assertTrue(isAtPosition);
    }

    @Test
    public void testSquareContainsSamePieceColor() throws Exception {
        square.setPiece(king);
        boolean samePieceColor = square.containsSamePieceColor(Color.BLACK);
        assertTrue(samePieceColor);
    }

    @Test
    public void testSquareContainsOpponentPieceColor() throws Exception {
        square.setPiece(king);
        boolean opponentPieceColor = square.containsOpponentPieceColor(Color.WHITE);
        assertTrue(opponentPieceColor);
    }

    @Test
    public void testSquareShouldGetNextPosition() throws Exception {
        Position position = new Position(2, 2);
        Position nextPosition = square.nextPosition(1, 1);
        assertEquals(position, nextPosition);
    }
}
