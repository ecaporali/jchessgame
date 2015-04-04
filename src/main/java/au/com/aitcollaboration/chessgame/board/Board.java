package au.com.aitcollaboration.chessgame.board;

import au.com.aitcollaboration.chessgame.pieces.*;
import au.com.aitcollaboration.chessgame.player.Color;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class Board {

    private final Square[][] grid;
    private final Map<Color, Pieces> piecesMap;
    public static final int BOARD_SIZE = 8;

    public Board() {
        this.piecesMap = new HashMap<Color, Pieces>(2);
        this.grid = new Square[BOARD_SIZE][BOARD_SIZE];
        createBoard();
        createPieces();
        mapColouredPieces();
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
        grid[0][3].setPiece(new King(Color.BLACK));
        grid[0][4].setPiece(new Queen(Color.BLACK));
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

    private void mapColouredPieces() {

        Pieces blackPieces = new Pieces();
        Pieces whitePieces = new Pieces();

        for (Square[] squares : grid) {
            for (Square square : squares) {
                Piece piece = square.getPiece();

                if (piece != null) {

                    if (piece.matches(Color.BLACK)) {
                        blackPieces.add(piece);
                    } else {
                        whitePieces.add(piece);
                    }
                }
            }
        }

        piecesMap.put(Color.WHITE, whitePieces);
        piecesMap.put(Color.BLACK, blackPieces);
    }

    public Pieces getColoredPieces(Color color) {
        return piecesMap.get(color);
    }

    public Map<Color, Pieces> getPiecesMap() {
        return Collections.unmodifiableMap(piecesMap);
    }

    public Square[][] getClonedGrid() {
        return this.grid.clone();
    }

    public Square getSquareForPiece(Piece piece) {

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

    public void clear() {
        for (Square[] squares : grid)
            for (Square square : squares)
                square.setPiece(null);
    }

    public String display() {
        StringBuilder stringBuilder = new StringBuilder();

        for (int i = 0; i < 50; ++i)
            stringBuilder.append("\n");

        String divider = "---------------------------------";

        for (Square[] squares : grid) {
            stringBuilder.append(divider);
            stringBuilder.append("\n");
            for (Square square : squares) {
                stringBuilder.append(square.toString());
            }
            stringBuilder.append("|\n");
        }
        stringBuilder.append(divider);
        stringBuilder.append("\n");

        return stringBuilder.toString();
    }
}
