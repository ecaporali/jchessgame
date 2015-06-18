package au.com.aitcollaboration.chessgame.model.game.structure;

import au.com.aitcollaboration.chessgame.Color;
import au.com.aitcollaboration.chessgame.model.moves.PlayerMoves;
import au.com.aitcollaboration.chessgame.model.pieces.*;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Board {

    private final Square[][] grid;
    private final Map<Color, Pieces> piecesMap;
    public static final int BOARD_SIZE = 8;

    public Board() {
        this.piecesMap = new HashMap<>(2);
        this.grid = new Square[BOARD_SIZE][BOARD_SIZE];
        createBoard();
        createPieces();
        buildPieceMap();
    }

    private void createBoard() {
        for (int row = 0; row < BOARD_SIZE; row++)
            for (int col = 0; col < BOARD_SIZE; col++)
                grid[row][col] = new Square(row, col);
    }

    private void createPieces() {

        grid[0][0].setPiece(new Rook(Color.BLACK));
        grid[0][1].setPiece(new Knight(Color.BLACK));
        grid[0][2].setPiece(new Bishop(Color.BLACK));
        grid[0][3].setPiece(new Queen(Color.BLACK));
        grid[0][4].setPiece(new King(Color.BLACK));
        grid[0][5].setPiece(new Bishop(Color.BLACK));
        grid[0][6].setPiece(new Knight(Color.BLACK));
        grid[0][7].setPiece(new Rook(Color.BLACK));

        for (int j = 0; j < BOARD_SIZE; j++) {
            grid[1][j].setPiece(new Pawn(Color.BLACK));
        }

        grid[7][0].setPiece(new Rook(Color.WHITE));
        grid[7][1].setPiece(new Knight(Color.WHITE));
        grid[7][2].setPiece(new Bishop(Color.WHITE));
        grid[7][3].setPiece(new Queen(Color.WHITE));
        grid[7][4].setPiece(new King(Color.WHITE));
        grid[7][5].setPiece(new Bishop(Color.WHITE));
        grid[7][6].setPiece(new Knight(Color.WHITE));
        grid[7][7].setPiece(new Rook(Color.WHITE));

        for (int j = 0; j < BOARD_SIZE; j++) {
            grid[6][j].setPiece(new Pawn(Color.WHITE));
        }
    }

    private void buildPieceMap() {
        Pieces whitePieces = new Pieces();
        Pieces blackPieces = new Pieces();

        for (int row = 0; row <= 1; row++) {
            for (int col = 0; col < BOARD_SIZE; col++) {
                Square square = grid[row][col];
                blackPieces.add(square.getPiece());
            }
        }

        for (int row = 6; row < BOARD_SIZE; row++) {
            for (int col = 0; col < BOARD_SIZE; col++) {
                Square square = grid[row][col];
                whitePieces.add(square.getPiece());
            }
        }

        piecesMap.put(Color.WHITE, whitePieces);
        piecesMap.put(Color.BLACK, blackPieces);
    }

    public Square getCurrentSquare(Piece piece) {
        for (Square[] squares : grid)
            for (Square square : squares)
                if (square.contains(piece))
                    return square;

        return null;
    }


    public Square getSquareAtPosition(Position position) {
        for (Square[] squares : grid)
            for (Square square : squares)
                if (square.isAt(position))
                    return square;

        return null;
    }

    public void removePiece(Square square) {
        Piece piece = square.getPiece();
        for (Pieces pieces : piecesMap.values())
            if (pieces.contains(piece))
                pieces.remove(piece);
    }

    public void movePiece(Square fromSquare, Square toSquare) {
        Piece piece = fromSquare.getPiece();
        fromSquare.setPiece(null);

        removePiece(toSquare);
        toSquare.setPiece(piece);
    }

    public void doSimulateMovePiece(Square fromSquare, Square toSquare) {
        Piece piece = fromSquare.getPiece();
        fromSquare.setPiece(null);
        toSquare.setPiece(piece);
    }

    public void undoSimulateMovePiece(Square currentSquare, Square toSquare, Piece toPiece) {
        Piece currentPiece = toSquare.getPiece();
        currentSquare.setPiece(currentPiece);
        toSquare.setPiece(toPiece);
    }

    public PlayerMoves calculateCurrentPlayerMoves(Color color) {
        Pieces currentPieces = piecesMap.get(color);
        return currentPieces.getValidMovesOn(this);
    }

    public PlayerMoves calculateOpponentPlayerMoves(Color color) {
        Pieces opponentPieces = piecesMap.get(color.flip());
        return opponentPieces.getValidMovesOn(this);
    }

    public Square getCurrentKingSquare(Color color) {
        Pieces currentPieces = piecesMap.get(color);
        Piece king = currentPieces.getPiece(King.class);
        return this.getCurrentSquare(king);
    }

    public boolean isEitherKingLastPieceStanding() {
        for (Pieces pieces : piecesMap.values())
            if (pieces.isKingLastPieceStanding())
                return true;

        return false;
    }

    public boolean isSecondRank(Square pawnSquare) {
        for (int i = 0; i < BOARD_SIZE; i++) {
            Square upperRank = grid[1][i];
            Square lowerRank = grid[6][i];
            if (upperRank.equals(pawnSquare) || lowerRank.equals(pawnSquare))
                return true;
        }
        return false;
    }

    // Used only for testing //
    public void clear() {
        piecesMap.clear();
        piecesMap.put(Color.WHITE, new Pieces());
        piecesMap.put(Color.BLACK, new Pieces());

        for (Square[] squares : grid)
            for (Square square : squares)
                square.setPiece(null);
    }

    // Used only for testing //
    public final Pieces getPiecesBy(Color color) {
        return piecesMap.get(color);
    }

    public Square[][] getClonedGrid() {
        return Arrays.copyOf(this.grid, this.grid.length);
    }
}
