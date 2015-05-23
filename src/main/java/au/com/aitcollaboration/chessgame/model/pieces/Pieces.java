package au.com.aitcollaboration.chessgame.model.pieces;

import au.com.aitcollaboration.chessgame.Color;

import java.util.ArrayList;
import java.util.List;

public class Pieces {

    private Color color;
    private List<Piece> currentPieces;
    private List<Piece> lostPieces;

    private Pieces() {
        this.currentPieces = new ArrayList<>(16);
        this.lostPieces = new ArrayList<>(16);
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

    public Piece getPiece(Class pieceClass) {
        for (Piece piece : currentPieces) {
            if (piece.matches(pieceClass)) {
                return piece;
            }
        }
        return null;
    }

    public Piece getLostPiece(Class pieceClass) {
        for (Piece piece : lostPieces) {
            if (piece.matches(pieceClass)) {
                return piece;
            }
        }
        return null;
    }

    public boolean isKingLastPieceStanding() {
        return currentPieces.size() == 1;
    }

    public Piece resurrectPiece(Class pieceClass) {
        return getLostPiece(pieceClass);
    }
}
