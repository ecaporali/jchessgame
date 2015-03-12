package au.com.aitcollaboration.chessgame.board;

import au.com.aitcollaboration.chessgame.pieces.Piece;

public class Square {

    private Piece piece;
    private int myX;
    private int myY;

    public Square(int myX, int myY) {
        this.myX = myX;
        this.myY = myY;
    }

    public boolean isAvailable() {
        return this.piece == null;
    }

    public void positionPiece(Piece piece) {
        this.piece = piece;
    }
}
