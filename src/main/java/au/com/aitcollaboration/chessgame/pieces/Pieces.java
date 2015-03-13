package au.com.aitcollaboration.chessgame.pieces;

import java.util.ArrayList;
import java.util.List;

public class Pieces {

    private List<Piece> currentPieces;
    private List<Piece> lostPieces;

    public Pieces() {
        this.currentPieces = new ArrayList<Piece>(16);
        this.lostPieces = new ArrayList<Piece>(15);
    }

    public void add(Piece piece) {
        currentPieces.add(piece);
    }
}
