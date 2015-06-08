package au.com.aitcollaboration.chessgame.model.game.structure;

import au.com.aitcollaboration.chessgame.Color;
import au.com.aitcollaboration.chessgame.exceptions.InvalidCoordinatesException;
import au.com.aitcollaboration.chessgame.model.moves.PlayerMoves;
import au.com.aitcollaboration.chessgame.model.pieces.*;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class Board {

    private final Square[][] grid;
    private final Map<Color, Pieces> piecesMap;
    private final List<Square[][]> movesHistory;
    public static final int BOARD_SIZE = 8;

    public Board() {
        this.piecesMap = new HashMap<>(2);
        this.movesHistory = new LinkedList<>();
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

    public void addToMoveHistory(){
        movesHistory.add(getClonedGrid());
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

    private void buildPieceMap() {
        Pieces whitePieces = new Pieces(Color.WHITE);
        Pieces blackPieces = new Pieces(Color.BLACK);

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

    public Square[][] getClonedGrid() {
        return this.grid.clone();
    }

    public Map<Color, Pieces> getPiecesMap() {
        return piecesMap;
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

        //TODO: remove this method as the PieceMap should be immutable, only the board should get updated
        removePiece(toSquare);
        toSquare.setPiece(piece);
    }

    public Map<Pieces, PlayerMoves> getAllValidMoves() {
        Map<Pieces, PlayerMoves> possibleMoves = new HashMap<>();

        for (Pieces pieces : piecesMap.values()) {
            PlayerMoves playerMoves = pieces.getValidMovesOn(this);
            possibleMoves.put(pieces, playerMoves);
        }
        return possibleMoves;
    }

    /***** Used only for testing ******/
    public void clear() {
        for (Square[] squares : grid)
            for (Square square : squares)
                square.setPiece(null);
    }

    public int getMovesHistorySize() {
        return movesHistory.size();
    }
}
