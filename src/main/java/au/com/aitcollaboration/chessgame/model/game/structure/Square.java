package au.com.aitcollaboration.chessgame.model.game.structure;

import au.com.aitcollaboration.chessgame.Color;
import au.com.aitcollaboration.chessgame.model.pieces.Piece;

public class Square {

    private final Position position;
    private Piece piece;

    public Square(int myX, int myY) {
        position = new Position(myX, myY);
    }

    public boolean hasPiece() {
        return this.piece != null;
    }

    public void setPiece(Piece piece) {
        this.piece = piece;
    }

    public boolean isAt(Position position) {
        return this.position.equals(position);
    }

    public boolean contains(Piece piece) {
        return hasPiece() && this.piece.equals(piece);
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
