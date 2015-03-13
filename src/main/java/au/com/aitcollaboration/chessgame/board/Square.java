package au.com.aitcollaboration.chessgame.board;

import au.com.aitcollaboration.chessgame.pieces.Piece;
import au.com.aitcollaboration.chessgame.player.Color;

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

    public Piece getPiece() {
        return piece;
    }

    public boolean matches(Piece piece){
        return this.piece.equals(piece);
    }

    public int incrementX(int myX){
        return this.myX + myX;
    }

    public int incrementY(int myY){
        return this.myY + myY;
    }
}
