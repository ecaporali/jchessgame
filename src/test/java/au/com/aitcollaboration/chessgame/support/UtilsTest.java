package au.com.aitcollaboration.chessgame.support;

import au.com.aitcollaboration.chessgame.Color;
import au.com.aitcollaboration.chessgame.exceptions.InvalidCoordinatesException;
import au.com.aitcollaboration.chessgame.exceptions.InvalidPositionException;
import au.com.aitcollaboration.chessgame.model.pieces.Bishop;
import au.com.aitcollaboration.chessgame.model.pieces.Knight;
import au.com.aitcollaboration.chessgame.model.pieces.Piece;
import au.com.aitcollaboration.chessgame.model.pieces.Queen;
import au.com.aitcollaboration.chessgame.model.pieces.Rook;
import org.junit.Test;

import static org.hamcrest.core.IsInstanceOf.instanceOf;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

public class UtilsTest {

    @Test
    public void toBoardPositionShouldReturnValidCoordinatesWhenPositionIsInBoardRange() throws Exception {
        int[] coordinates = Utils.toBoardPosition("A1");
        assertEquals(2, coordinates.length);
        assertEquals(7, coordinates[0]);
        assertEquals(0, coordinates[1]);
    }

    @Test(expected = InvalidPositionException.class)
    public void toBoardPositionShouldThrowInvalidPositionExceptionWhenAnswerIsEmpty() throws Exception {
        Utils.toBoardPosition("");
    }

    @Test(expected = InvalidPositionException.class)
    public void toBoardPositionShouldThrowInvalidPositionExceptionWhenAnswerIsNull() throws Exception {
        Utils.toBoardPosition(null);
    }

    @Test(expected = InvalidPositionException.class)
    public void toBoardPositionShouldThrowInvalidPositionExceptionWhenAnswerIsNotLengthOfTwo() throws Exception {
        Utils.toBoardPosition("A2A3");
    }

    @Test(expected = InvalidPositionException.class)
    public void toBoardPositionShouldThrowInvalidPositionExceptionWhenPositionIsNotInBoardRange() throws Exception {
        Utils.toBoardPosition("Z1");
    }

    @Test
    public void toGamePositionShouldReturnValidGamePosition() throws Exception {
        String gamePosition = Utils.toGamePosition(new int[]{7, 0});
        assertEquals(2, gamePosition.length());
        assertEquals("A1", gamePosition);
    }

    @Test(expected = InvalidCoordinatesException.class)
    public void toGamePositionShouldThrowInvalidCoordinatesExceptionWhenPositionIsNull() throws Exception {
        Utils.toGamePosition(null);
    }

    @Test(expected = InvalidCoordinatesException.class)
    public void toGamePositionShouldThrowInvalidCoordinatesExceptionWhenPositionLengthIsNotTwo() throws Exception {
        Utils.toGamePosition(new int[]{7, 0, 4, 5});
    }

    @Test
    public void isValidPieceToPromoteShouldReturnTrueIfInputDoesNotMatch() throws Exception {
        boolean chosenPiece = Utils.isValidPieceToPromote("Lion");
        assertFalse(chosenPiece);
    }

    @Test
    public void isValidPieceToPromoteShouldReturnTrueIfInputMatchesExistingPiece() throws Exception {
        boolean chosenQueen = Utils.isValidPieceToPromote("Queen");
        boolean chosenRook = Utils.isValidPieceToPromote("ROOK");
        boolean chosenBishop = Utils.isValidPieceToPromote("BiShOp");
        boolean chosenKnight = Utils.isValidPieceToPromote("knight");
        boolean chosenNone = Utils.isValidPieceToPromote("noNe");

        assertTrue(chosenQueen);
        assertTrue(chosenRook);
        assertTrue(chosenBishop);
        assertTrue(chosenKnight);
        assertTrue(chosenNone);
    }

    @Test
    public void getPieceInstanceFromClassShouldReturnNewInstanceOfChosenPiece() throws Exception {
        Piece blackRook = Utils.getPieceInstanceFromClass("Rook", Color.BLACK);
        Piece whiteQueen = Utils.getPieceInstanceFromClass("Queen", Color.WHITE);
        Piece blackBishop = Utils.getPieceInstanceFromClass("Bishop", Color.BLACK);
        Piece whiteKnight = Utils.getPieceInstanceFromClass("Knight", Color.WHITE);

        assertNotNull(blackRook);
        assertNotNull(whiteQueen);
        assertNotNull(blackBishop);
        assertNotNull(whiteKnight);

        assertThat(blackRook, instanceOf(Rook.class));
        assertThat(whiteQueen, instanceOf(Queen.class));
        assertThat(blackBishop, instanceOf(Bishop.class));
        assertThat(whiteKnight, instanceOf(Knight.class));

        assertTrue(blackRook.matches(Color.BLACK));
        assertTrue(whiteQueen.matches(Color.WHITE));
        assertTrue(blackBishop.matches(Color.BLACK));
        assertTrue(whiteKnight.matches(Color.WHITE));
    }

    @Test
    public void getPieceInstanceFromClassShouldReturnNullIfPieceClassIsNotFound() throws Exception {
        Piece blackLion = Utils.getPieceInstanceFromClass("Lion", Color.BLACK);
        Piece blackTiger = Utils.getPieceInstanceFromClass("Tiger", Color.BLACK);

        assertNull(blackLion);
        assertNull(blackTiger);
    }
}
