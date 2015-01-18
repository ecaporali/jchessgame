package au.com.aitcollaboration.chessgame.pieces;

import au.com.aitcollaboration.chessgame.player.Color;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Massimo on 18/01/2015.
 */
public class Pieces {

    private List<Piece> currentPieces;
    private List<Piece> lostPieces;

    public static int BISHOP_QTY = 2;
    public static int KNIGHT_QTY = 2;
    public static int ROOK_QTY = 2;
    public static int PAWN_QTY = 8;

    public Pieces(Color color) {
        this.currentPieces = new ArrayList<Piece>();
        this.lostPieces = new ArrayList<Piece>();
        createPieceSet(color);
    }

    private void createPieceSet(Color color) {
        currentPieces.add(new King(color));
        currentPieces.add(new Queen(color));

        for(int i = 0; i < BISHOP_QTY; i++){
            currentPieces.add(new Bishop(color));
        }

        for(int i = 0; i < KNIGHT_QTY; i++){
            currentPieces.add(new Knight(color));
        }

        for(int i = 0; i < ROOK_QTY; i++){
            currentPieces.add(new Rook(color));
        }

        for(int i = 0; i < PAWN_QTY; i++){
            currentPieces.add(new Pawn(color));
        }

        System.out.println(currentPieces.size());
    }
}
