package au.com.aitcollaboration.chessgame.board;

import au.com.aitcollaboration.chessgame.pieces.Pieces;

import java.util.List;

/**
 * Created by Massimo on 18/01/2015.
 */
public class Board {

    private Square[][] grid;
    private List<Pieces> pieces;

    public static int BOARD_SIZE = 8;

    public Board() {
        this.grid = new Square[BOARD_SIZE][BOARD_SIZE];
        init();
    }

    private void init() {
        for (int myX = 0; myX < BOARD_SIZE; myX++) {
            for (int myY = 0; myY < BOARD_SIZE; myY++) {
                grid[myX][myY] = new Square().placeMyX(myX).placeMyY(myY);
            }
        }
    }
}
