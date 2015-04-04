package au.com.aitcollaboration.chessgame.board;

import au.com.aitcollaboration.chessgame.pieces.Piece;
import au.com.aitcollaboration.chessgame.player.Color;

public class Square {

    private Piece piece;
    private Position position;

    public Square(int myX, int myY) {
        position = new Position(myX, myY);
    }

    public boolean hasPiece() {
        return this.piece != null;
    }

    public void setPiece(Piece piece) {
        this.piece = piece;
    }

    public boolean contains(Piece piece) {
        return hasPiece() && this.piece.equals(piece);
    }

    public boolean isAt(Position position) {
        return this.position.equals(position);
    }

    public boolean containsSamePieceColor(Color color) {
        return hasPiece() && piece.matches(color);
    }

    public boolean containsOpponentPieceColor(Color color) {
        return hasPiece() && !piece.matches(color);
    }

    public Position nextPosition(int myX, int myY) {
        return position.nextPosition(myX, myY);
    }

    public Piece getPiece() {
        return piece;
    }

    public Position getPosition() {
        return position;
    }

    @Override
    public String toString() {
        String s = (piece != null) ? piece.toString() : " ";
        return "| " + s + " ";
    }
}
