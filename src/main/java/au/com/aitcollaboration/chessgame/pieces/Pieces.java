package au.com.aitcollaboration.chessgame.pieces;

import au.com.aitcollaboration.chessgame.player.Color;

import java.util.ArrayList;
import java.util.List;

public class Pieces {

    private Color color;
    private List<Piece> currentPieces;
    private List<Piece> lostPieces;

    private Pieces() {
        this.currentPieces = new ArrayList<Piece>(16);
        this.lostPieces = new ArrayList<Piece>(16);
    }

    public Pieces(Color color) {
        this();
        this.color = color;
    }

    public void add(Piece piece) {
        currentPieces.add(piece);
    }

    public void remove(Piece piece) {
        this.currentPieces.remove(piece);
        this.lostPieces.add(piece);
    }

    public boolean isColor(Color color) {
        return this.color.equals(color);
    }

    public boolean contains(Piece piece) {
        return currentPieces.contains(piece);
    }

    public int size() {
        return currentPieces.size();
    }

    public Color getColor() {
        return color;
    }

    public Piece getKing() {
        for (Piece piece : currentPieces) {
            if (piece.getClass().equals(King.class)) {
                return piece;
            }
        }
        return null;
    }
}
