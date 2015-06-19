package au.com.aitcollaboration.chessgame.model.game.structure;

import au.com.aitcollaboration.chessgame.Color;
import au.com.aitcollaboration.chessgame.model.moves.PlayerMoves;
import au.com.aitcollaboration.chessgame.model.pieces.King;
import au.com.aitcollaboration.chessgame.model.pieces.Pawn;
import au.com.aitcollaboration.chessgame.model.pieces.Piece;
import au.com.aitcollaboration.chessgame.model.pieces.Pieces;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class BoardTest {

    private Board board;

    @Before
    public void setUp() throws Exception {
        board = new Board();
    }

    @Test
    public void createPiecesShouldCreateAppropriatePieces() {
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

        assertThat(16, is(blackPieceCount));
        assertThat(16, is(whitePieceCount));
        assertThat(32, is(emptySquareCount));
    }

    @Test
    public void buildPieceMapShouldContainBothColours() throws Exception {
        Pieces whitePieces = board.getPiecesBy(Color.WHITE);
        Pieces blackPieces = board.getPiecesBy(Color.BLACK);

        assertNotNull(whitePieces);
        assertNotNull(blackPieces);
        assertThat(16, is(whitePieces.size()));
        assertThat(16, is(blackPieces.size()));
    }

    @Test
    public void getSquareAtPositionShouldReturnSquareWhenPositionMatches() throws Exception {
        Position position = new Position(1, 5);
        Square square = board.getSquareAtPosition(position);

        assertNotNull(square);
        assertNotNull(square.getPiece());
        assertEquals(square.getPiece().getClass(), Pawn.class);
        assertTrue(square.getPosition().equals(position));
    }

    @Test
    public void getSquareAtPositionShouldReturnNullWhenPositionDoesNotMatch() throws Exception {
        Position position = new Position(11, 12);
        Square square = board.getSquareAtPosition(position);

        assertNull(square);
    }

    @Test
    public void getCurrentSquareShouldReturnSquareWhenPieceMatches() throws Exception {
        Square[][] grid = board.getClonedGrid();

        Piece king = grid[0][4].getPiece();

        Square square = board.getCurrentSquare(king);

        assertNotNull(square);
        assertNotNull(square.getPiece());
        assertEquals(square.getPiece().getClass(), King.class);
        assertTrue(square.getPiece().equals(king));
    }

    @Test
    public void getCurrentSquareShouldReturnNullWhenPieceDoesNotMatch() throws Exception {
        Piece king = new King(Color.WHITE);

        Square square = board.getCurrentSquare(king);

        assertNull(square);
    }

    @Test
    public void removePieceShouldRemovePieceWhenItIsFound() throws Exception {
        Square[][] grid = board.getClonedGrid();
        Square square = grid[0][3];

        board.removePiece(square);
        Pieces whitePieces = board.getPiecesBy(Color.WHITE);
        Pieces blackPieces = board.getPiecesBy(Color.BLACK);

        assertThat(15, is(blackPieces.size()));
        assertThat(16, is(whitePieces.size()));
    }

    @Test
    public void removePieceShouldNotRemovePieceWhenItIsNotFound() throws Exception {
        Square[][] grid = board.getClonedGrid();
        Square square = grid[4][0];

        board.removePiece(square);
        Pieces whitePieces = board.getPiecesBy(Color.WHITE);
        Pieces blackPieces = board.getPiecesBy(Color.BLACK);

        assertThat(16, is(blackPieces.size()));
        assertThat(16, is(whitePieces.size()));
    }

    @Test
    public void movePieceShouldMoveTheCurrentPieceToDestination() throws Exception {
        Square[][] grid = board.getClonedGrid();
        Square fromSquare = grid[1][0];
        Piece currentPiece = fromSquare.getPiece();

        Square toSquare = grid[2][0];

        assertTrue(fromSquare.hasPiece());
        assertFalse(toSquare.hasPiece());

        board.movePiece(fromSquare, toSquare);

        assertFalse(fromSquare.hasPiece());
        assertTrue(toSquare.hasPiece());

        Piece expectedPiece = toSquare.getPiece();

        assertEquals(currentPiece, expectedPiece);
    }

    @Test
    public void calculatePlayerMovesShouldReturnPlayerMoves() throws Exception {
        Color currentColor = Color.WHITE;

        PlayerMoves whitePlayerMoves = board.calculateCurrentPlayerMoves(currentColor);
        PlayerMoves blackPlayerMoves = board.calculateOpponentPlayerMoves(currentColor);

        assertEquals(10, blackPlayerMoves.size());
        assertEquals(10, whitePlayerMoves.size());
    }

    @Test
    public void getCurrentKingSquareShouldReturnKingSquare() throws Exception {
        Square[][] grid = board.getClonedGrid();
        Square blackKingSquare = grid[0][4];
        Square whiteKingSquare = grid[7][4];

        Square expectedWhiteKingSquare = board.getCurrentKingSquare(Color.WHITE);
        Square expectedBlackKingSquare = board.getCurrentKingSquare(Color.BLACK);

        Piece whiteKing = whiteKingSquare.getPiece();
        Piece blackKing = blackKingSquare.getPiece();

        assertTrue(whiteKing.matches(Color.WHITE));
        assertTrue(blackKing.matches(Color.BLACK));
        assertEquals(whiteKingSquare, expectedWhiteKingSquare);
        assertEquals(blackKingSquare, expectedBlackKingSquare);
    }

    @Test
    public void isEitherKingLastPieceStandingShouldReturnTrueWhenKingIsLastPiece() throws Exception {
        Pieces whitePieces = board.getPiecesBy(Color.WHITE);
        Pieces blackPieces = board.getPiecesBy(Color.BLACK);

        whitePieces.clear();
        blackPieces.clear();

        assertThat(0, is(whitePieces.size()));
        assertThat(0, is(blackPieces.size()));

        whitePieces.add(new King(Color.WHITE));
        blackPieces.add(new King(Color.BLACK));

        assertTrue(board.isEitherKingLastPieceStanding());
    }

    @Test
    public void isEitherKingLastPieceStandingShouldReturnFalseWhenThereAreOtherPiecesStanding() throws Exception {
        Pieces whitePieces = board.getPiecesBy(Color.WHITE);
        Pieces blackPieces = board.getPiecesBy(Color.BLACK);

        assertThat(16, is(whitePieces.size()));
        assertThat(16, is(blackPieces.size()));
        assertFalse(board.isEitherKingLastPieceStanding());
    }

    @Test
    public void isSecondRankShouldReturnTrueIfPawnHasNotMoved() throws Exception {
        Square[][] grid = board.getClonedGrid();
        Square blackPawnSquare = grid[1][0];
        Square whitePawnSquare = grid[6][0];

        assertTrue(board.isSecondRank(whitePawnSquare));
        assertTrue(board.isSecondRank(blackPawnSquare));
    }

    @Test
    public void isSecondRankShouldReturnFalseIfPawnHasAlreadyMoved() throws Exception {
        board.clear();
        Square[][] grid = board.getClonedGrid();

        Square blackPawnSquare = grid[2][0];
        Square whitePawnSquare = grid[5][0];

        blackPawnSquare.setPiece(new Pawn(Color.BLACK));
        whitePawnSquare.setPiece(new Pawn(Color.WHITE));

        assertFalse(board.isSecondRank(whitePawnSquare));
        assertFalse(board.isSecondRank(blackPawnSquare));
    }

    @Test
    public void clearShouldClearTheBoardFromPieces() throws Exception {
        board.clear();

        for (Square[] squares : board.getClonedGrid()) {
            for (Square square : squares) {
                assertFalse(square.hasPiece());
            }
        }
    }

    @Test
    public void isEdgeSquareShouldReturnTrueWhenSquareIsEitherOnFirstOrLastRow() throws Exception {
        Square[][] grid = board.getClonedGrid();

        boolean sevenZero = board.isEdgeSquare(grid[7][0]);
        boolean zeroFive = board.isEdgeSquare(grid[0][5]);
        boolean sevenTwo = board.isEdgeSquare(grid[7][2]);
        boolean zeroSeven = board.isEdgeSquare(grid[0][7]);

        assertTrue(sevenZero);
        assertTrue(sevenTwo);
        assertTrue(zeroFive);
        assertTrue(zeroSeven);
    }

    @Test
    public void isEdgeSquareShouldReturnFalseWhenSquareIsNotOnFirstOrLastRow() throws Exception {
        Square[][] grid = board.getClonedGrid();

        boolean sixZero = board.isEdgeSquare(grid[6][0]);
        boolean twoFive = board.isEdgeSquare(grid[2][5]);
        boolean threeTwo = board.isEdgeSquare(grid[3][2]);
        boolean oneSeven = board.isEdgeSquare(grid[1][7]);

        assertFalse(sixZero);
        assertFalse(threeTwo);
        assertFalse(twoFive);
        assertFalse(oneSeven);
    }

    @Test
    public void isSecondRankShouldReturnTrueWhenIsSecondRank() throws Exception {
        Square[][] grid = board.getClonedGrid();

        boolean oneFive = board.isSecondRank(grid[1][5]);
        boolean oneZero = board.isSecondRank(grid[1][0]);
        boolean sixThree = board.isSecondRank(grid[6][3]);
        boolean sixSeven = board.isSecondRank(grid[6][7]);

        assertTrue(oneFive);
        assertTrue(oneZero);
        assertTrue(sixThree);
        assertTrue(sixSeven);
    }

    @Test
    public void isSecondRankShouldReturnFalseWhenIsNotSecondRank() throws Exception {
        Square[][] grid = board.getClonedGrid();

        boolean twoFive = board.isSecondRank(grid[2][5]);
        boolean zeroSix = board.isSecondRank(grid[0][6]);
        boolean threeFour = board.isSecondRank(grid[3][4]);
        boolean sevenOne = board.isSecondRank(grid[7][1]);

        assertFalse(twoFive);
        assertFalse(zeroSix);
        assertFalse(threeFour);
        assertFalse(sevenOne);
    }
}
