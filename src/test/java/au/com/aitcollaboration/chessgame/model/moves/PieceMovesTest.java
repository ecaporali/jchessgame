package au.com.aitcollaboration.chessgame.model.moves;

import au.com.aitcollaboration.chessgame.Color;
import au.com.aitcollaboration.chessgame.model.game.structure.Board;
import au.com.aitcollaboration.chessgame.model.game.structure.Square;
import au.com.aitcollaboration.chessgame.model.pieces.*;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class PieceMovesTest {

    private Board board;
    private PieceMoves pieceMoves;

    @Before
    public void setUp() throws Exception {
        board = new Board();
        board.clear();
    }

    @Test
    public void toValidMovesShouldNotBeEmptyWhenKingIsNotUnderCheck() throws Exception {
        Piece whiteQueen = new Queen(Color.WHITE);
        Piece blackKing = new King(Color.BLACK);
        Piece blackQueen = new Queen(Color.BLACK);
        Piece blackPawn1 = new Pawn(Color.BLACK);
        Piece blackPawn2 = new Pawn(Color.BLACK);
        Piece blackPawn3 = new Pawn(Color.BLACK);
        Piece blackPawn4 = new Pawn(Color.BLACK);
        Piece blackPawn5 = new Pawn(Color.BLACK);

        Square[][] grid = board.getClonedGrid();
        grid[0][4].setPiece(blackKing);
        grid[1][4].setPiece(blackQueen);
        grid[1][3].setPiece(blackPawn1);
        grid[1][5].setPiece(blackPawn2);
        grid[2][4].setPiece(blackPawn3);
        grid[2][3].setPiece(blackPawn4);
        grid[2][5].setPiece(blackPawn5);

        grid[1][0].setPiece(whiteQueen);

        PlayerMoves opponentInitialMoves = board.calculateOpponentPlayerMoves(Color.BLACK);
        pieceMoves = blackQueen.getValidMovesOn(board);
        pieceMoves.toValidMoves(board, grid[0][4], opponentInitialMoves);

        assertFalse(pieceMoves.isEmpty());
        assertThat(2, is(pieceMoves.size()));
        assertTrue(pieceMoves.contains(grid[0][5]));
        assertTrue(pieceMoves.contains(grid[0][3]));
    }

    @Test
    public void toValidMovesShouldContainOnlySquareForSavingKingWhenKingIsUnderCheck() throws Exception {
        Piece whiteQueen = new Queen(Color.WHITE);
        Piece blackKing = new King(Color.BLACK);
        Piece blackQueen = new Queen(Color.BLACK);

        Square[][] grid = board.getClonedGrid();
        grid[0][4].setPiece(blackKing);
        grid[1][4].setPiece(blackQueen);

        grid[0][0].setPiece(whiteQueen);

        Pieces blackPieces = board.getPiecesBy(Color.BLACK);
        Pieces whitePieces = board.getPiecesBy(Color.WHITE);

        pieceMoves = blackQueen.getValidMovesOn(board);

        whitePieces.add(whiteQueen);
        blackPieces.add(blackKing);
        blackPieces.add(blackQueen);

        PlayerMoves opponentInitialMoves = board.calculateOpponentPlayerMoves(Color.BLACK);
        pieceMoves.toValidMoves(board, grid[0][4], opponentInitialMoves);

        assertFalse(pieceMoves.isEmpty());
        assertThat(1, is(pieceMoves.size()));
        assertTrue(pieceMoves.contains(grid[0][3]));
    }

    @Test
    public void toValidMovesShouldContainEmptyPieceMovesWhenKingIsInDanger() throws Exception {
        Piece whiteQueen = new Queen(Color.WHITE);
        Piece blackKing = new King(Color.BLACK);
        Piece blackBishop = new Bishop(Color.BLACK);

        Square[][] grid = board.getClonedGrid();
        grid[0][4].setPiece(blackKing);
        grid[0][3].setPiece(blackBishop);

        grid[0][0].setPiece(whiteQueen);

        Pieces blackPieces = board.getPiecesBy(Color.BLACK);
        Pieces whitePieces = board.getPiecesBy(Color.WHITE);

        pieceMoves = blackBishop.getValidMovesOn(board);
        whitePieces.add(whiteQueen);
        blackPieces.add(blackKing);
        blackPieces.add(blackBishop);

        PlayerMoves opponentInitialMoves = board.calculateOpponentPlayerMoves(Color.BLACK);
        pieceMoves.toValidMoves(board, grid[0][4], opponentInitialMoves);

        assertTrue(pieceMoves.isEmpty());
    }

    @Test
    public void toValidMovesShouldContainValidMovesWhenPieceIsKingAndItIsUnderCheck() throws Exception {
        Piece whiteQueen = new Queen(Color.WHITE);
        Piece blackKing = new King(Color.BLACK);

        Square[][] grid = board.getClonedGrid();
        grid[0][4].setPiece(blackKing);

        grid[0][0].setPiece(whiteQueen);

        Pieces blackPieces = board.getPiecesBy(Color.BLACK);
        Pieces whitePieces = board.getPiecesBy(Color.WHITE);

        pieceMoves = blackKing.getValidMovesOn(board);
        whitePieces.add(whiteQueen);
        blackPieces.add(blackKing);

        PlayerMoves opponentInitialMoves = board.calculateOpponentPlayerMoves(Color.BLACK);
        pieceMoves.toValidMoves(board, grid[0][4], opponentInitialMoves);

        assertFalse(pieceMoves.isEmpty());
        assertThat(3, is(pieceMoves.size()));
        assertTrue(pieceMoves.contains(grid[1][4]));
        assertTrue(pieceMoves.contains(grid[1][3]));
        assertTrue(pieceMoves.contains(grid[1][5]));
        assertFalse(pieceMoves.contains(grid[0][5]));
        assertFalse(pieceMoves.contains(grid[0][3]));
    }

    @Test
    public void toValidMovesShouldContainValidMovesWhenOpponentPieceCanBeEatenAndKingIsNotEitherInDangerOrUnderCheck() throws Exception {
        Piece whiteQueen = new Queen(Color.WHITE);
        Piece blackKing = new King(Color.BLACK);
        Piece blackRook = new Rook(Color.BLACK);

        Square[][] grid = board.getClonedGrid();
        grid[0][4].setPiece(blackKing);
        grid[0][0].setPiece(blackRook);

        grid[7][0].setPiece(whiteQueen);

        Pieces blackPieces = board.getPiecesBy(Color.BLACK);
        Pieces whitePieces = board.getPiecesBy(Color.WHITE);

        pieceMoves = blackRook.getValidMovesOn(board);
        whitePieces.add(whiteQueen);
        blackPieces.add(blackKing);
        blackPieces.add(blackRook);

        PlayerMoves opponentInitialMoves = board.calculateOpponentPlayerMoves(Color.BLACK);
        pieceMoves.toValidMoves(board, grid[0][4], opponentInitialMoves);

        assertFalse(pieceMoves.isEmpty());
        assertThat(10, is(pieceMoves.size()));
        assertTrue(pieceMoves.contains(grid[0][1]));
        assertTrue(pieceMoves.contains(grid[0][2]));
        assertTrue(pieceMoves.contains(grid[0][3]));
        assertTrue(pieceMoves.contains(grid[1][0]));
        assertTrue(pieceMoves.contains(grid[2][0]));
        assertTrue(pieceMoves.contains(grid[3][0]));
        assertTrue(pieceMoves.contains(grid[4][0]));
        assertTrue(pieceMoves.contains(grid[5][0]));
        assertTrue(pieceMoves.contains(grid[6][0]));
        assertTrue(pieceMoves.contains(grid[7][0]));
    }

    @Test
    public void toValidMovesShouldContainOnlyPieceMovesForKingWhenKingIsInUnderCheckAndNoPieceCanCoverIt() throws Exception {
        Piece whiteQueen = new Queen(Color.WHITE);
        Piece blackKing = new King(Color.BLACK);
        Piece blackRook = new Rook(Color.BLACK);

        Square[][] grid = board.getClonedGrid();
        grid[0][4].setPiece(blackKing);
        grid[0][3].setPiece(blackRook);

        grid[7][4].setPiece(whiteQueen);

        Pieces blackPieces = board.getPiecesBy(Color.BLACK);
        Pieces whitePieces = board.getPiecesBy(Color.WHITE);
        whitePieces.add(whiteQueen);
        blackPieces.add(blackKing);
        blackPieces.add(blackRook);

        PlayerMoves opponentInitialMoves = board.calculateOpponentPlayerMoves(Color.BLACK);
        pieceMoves = blackRook.getValidMovesOn(board);
        pieceMoves.toValidMoves(board, grid[0][4], opponentInitialMoves);

        assertTrue(pieceMoves.isEmpty());

        pieceMoves = blackKing.getValidMovesOn(board);
        pieceMoves.toValidMoves(board, grid[0][4], opponentInitialMoves);

        assertFalse(pieceMoves.isEmpty());
        assertTrue(pieceMoves.contains(grid[0][5]));
        assertTrue(pieceMoves.contains(grid[1][3]));
        assertTrue(pieceMoves.contains(grid[1][5]));
        assertFalse(pieceMoves.contains(grid[1][4]));
    }

    @Test
    public void toValidMovesShouldContainOnlyPieceMovesForKingWhenKingIsInUnderAndKingCanEatTheOpponentButKingWillBeInCheckMate() throws Exception {
        Piece whiteQueen = new Queen(Color.WHITE);
        Piece whiteRook = new Rook(Color.WHITE);
        Piece blackKing = new King(Color.BLACK);

        Square[][] grid = board.getClonedGrid();
        grid[0][4].setPiece(blackKing);

        grid[1][0].setPiece(whiteRook);
        grid[1][4].setPiece(whiteQueen);

        Pieces blackPieces = board.getPiecesBy(Color.BLACK);
        Pieces whitePieces = board.getPiecesBy(Color.WHITE);
        whitePieces.add(whiteQueen);
        whitePieces.add(whiteRook);
        blackPieces.add(blackKing);

        PlayerMoves opponentInitialMoves = board.calculateOpponentPlayerMoves(Color.BLACK);
        pieceMoves = blackKing.getValidMovesOn(board);
        pieceMoves.toValidMoves(board, grid[0][4], opponentInitialMoves);

        assertFalse(pieceMoves.isEmpty());
        assertThat(1, is(pieceMoves.size()));
        assertTrue(pieceMoves.contains(grid[1][4]));
    }
}