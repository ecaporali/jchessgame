package au.com.aitcollaboration.chessgame.board;

import au.com.aitcollaboration.chessgame.pieces.*;
import au.com.aitcollaboration.chessgame.player.Color;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class Board {

    private Square[][] grid;
    private Map<Color, Pieces> piecesMap;

    public static int BOARD_SIZE = 8;

    public Board() {
        this.piecesMap = new HashMap<Color, Pieces>(2);
        this.grid = new Square[BOARD_SIZE][BOARD_SIZE];
        createBoard();
        createPieces();
        mapColouredPieces();
    }

    private void createBoard() {
        for (int row = 0; row < BOARD_SIZE; row++) {
            for (int col = 0; col < BOARD_SIZE; col++) {
                grid[row][col] = new Square(row, col);
            }
        }
    }

    private void createPieces() {

        grid[0][0].positionPiece(new Rook(Color.BLACK));
        grid[0][1].positionPiece(new Knight(Color.BLACK));
        grid[0][2].positionPiece(new Bishop(Color.BLACK));
        grid[0][3].positionPiece(new King(Color.BLACK));
        grid[0][4].positionPiece(new Queen(Color.BLACK));
        grid[0][5].positionPiece(new Bishop(Color.BLACK));
        grid[0][6].positionPiece(new Knight(Color.BLACK));
        grid[0][7].positionPiece(new Rook(Color.BLACK));

        for (int j = 0; j < BOARD_SIZE; j++) {
            grid[1][j].positionPiece(new Pawn(Color.BLACK));
        }

        grid[7][0].positionPiece(new Rook(Color.WHITE));
        grid[7][1].positionPiece(new Knight(Color.WHITE));
        grid[7][2].positionPiece(new Bishop(Color.WHITE));
        grid[7][3].positionPiece(new Queen(Color.WHITE));
        grid[7][4].positionPiece(new King(Color.WHITE));
        grid[7][5].positionPiece(new Bishop(Color.WHITE));
        grid[7][6].positionPiece(new Knight(Color.WHITE));
        grid[7][7].positionPiece(new Rook(Color.WHITE));

        for (int j = 0; j < BOARD_SIZE; j++) {
            grid[6][j].positionPiece(new Pawn(Color.WHITE));
        }
    }

    private void mapColouredPieces() {

        Pieces blackPieces = new Pieces();
        Pieces whitePieces = new Pieces();

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

    public Pieces getColoredPieces(Color color) {
        return piecesMap.get(color);
    }

    public Map<Color, Pieces> getPiecesMap() {
        return Collections.unmodifiableMap(piecesMap);
    }
}
