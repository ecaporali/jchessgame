package au.com.aitcollaboration.chessgame.board;

import au.com.aitcollaboration.chessgame.pieces.King;
import au.com.aitcollaboration.chessgame.pieces.Pieces;
import au.com.aitcollaboration.chessgame.pieces.Queen;

import java.util.ArrayList;
import java.util.List;

public class Board {

    private Square[][] grid;
    private List<Pieces> piecesList;

    public static int BOARD_SIZE = 8;

    public Board() {
        this.piecesList = new ArrayList<Pieces>(2);
        this.grid = new Square[BOARD_SIZE][BOARD_SIZE];
        createBoard();
    }

    private void createBoard() {
        for (int myX = 0; myX < BOARD_SIZE; myX++) {
            for (int myY = 0; myY < BOARD_SIZE; myY++) {
                grid[myX][myY] = new Square().placeMyX(myX).placeMyY(myY);
            }
        }
    }

    public void positionPieces(){
        Pieces white = piecesList.get(0);
        grid[0][0].positionPiece(white.getPiece(King.class));
        Pieces black = piecesList.get(1);
        System.out.println(black.getPiece(Queen.class));
        System.out.println(white.getPiece(King.class));
    }

    public void add(Pieces pieces) {
        piecesList.add(pieces);
    }
}
