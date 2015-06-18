package au.com.aitcollaboration.chessgame.model.pieces;

import au.com.aitcollaboration.chessgame.Color;
import au.com.aitcollaboration.chessgame.model.game.structure.Board;
import au.com.aitcollaboration.chessgame.model.game.structure.Square;
import au.com.aitcollaboration.chessgame.model.moves.PlayerMoves;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class PiecesTest {

    private Pieces pieces = new Pieces();

    @Test
    public void addShouldAddPiece() throws Exception {
        assertThat(pieces.size(), is(0));
        pieces.add(new King(Color.BLACK));
        pieces.add(new Queen(Color.BLACK));

        assertThat(pieces.size(), is(2));
    }

    @Test
    public void removeShouldRemovePiece() throws Exception {
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
    public void getPieceShouldReturnSearchedPieceWhenPieceIsFound() throws Exception {
        Piece king = new King(Color.BLACK);
        pieces.add(king);
        Piece expectedKing = pieces.getPiece(King.class);

        assertEquals(king, expectedKing);
    }

    @Test
    public void getPieceShouldReturnNullWhenPieceIsNotFound() throws Exception {
        Piece queen = new Queen(Color.BLACK);
        pieces.add(queen);
        Piece expectedKing = pieces.getPiece(King.class);

        assertNull(expectedKing);
    }

    @Test
    public void containsShouldContainPieceWhenPieceIsInCurrentPieces() throws Exception {
        Piece king = new King(Color.BLACK);
        pieces.add(king);

        boolean containsPiece = pieces.contains(king);

        assertTrue(containsPiece);
    }

    @Test
    public void containsShouldNotContainPieceWhenPieceIsNotInCurrentPieces() throws Exception {
        Piece king = new King(Color.BLACK);
        Piece queen = new Queen(Color.BLACK);
        pieces.add(king);

        boolean notContainsPiece = pieces.contains(queen);

        assertFalse(notContainsPiece);
    }

    @Test
    public void getValidMovesOnBoardShouldReturnPlayerMoves() throws Exception {
        Board board = new Board();
        board.clear();
        Square[][] grid = board.getClonedGrid();
        Piece pawn = new Pawn(Color.BLACK);

        grid[1][0].setPiece(pawn);
        pieces.add(pawn);

        PlayerMoves playerMoves = pieces.getValidMovesOn(board);

        assertEquals(1, playerMoves.size());
    }

    @Test
    public void getValidMovesOnBoardShouldReturnEmptyPlayerMoves() throws Exception {
        Board board = new Board();
        board.clear();
        Square[][] grid = board.getClonedGrid();
        Piece blackPawn = new Pawn(Color.BLACK);
        Piece whitePawn = new Pawn(Color.WHITE);

        grid[1][0].setPiece(blackPawn);
        grid[2][0].setPiece(whitePawn);
        pieces.add(blackPawn);

        PlayerMoves playerMoves = pieces.getValidMovesOn(board);

        assertEquals(0, playerMoves.size());
    }
}
