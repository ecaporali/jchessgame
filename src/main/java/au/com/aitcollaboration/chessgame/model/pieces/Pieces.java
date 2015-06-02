package au.com.aitcollaboration.chessgame.model.pieces;

import au.com.aitcollaboration.chessgame.Color;
import au.com.aitcollaboration.chessgame.model.game.structure.Board;
import au.com.aitcollaboration.chessgame.model.moves.PieceMoves;
import au.com.aitcollaboration.chessgame.model.moves.PlayerMoves;

import java.util.ArrayList;
import java.util.List;

public class Pieces {

    private Color color;
    private final List<Piece> currentPieces;
    private final List<Piece> lostPieces;

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

    public PlayerMoves getValidMovesOn(Board board) {
        PlayerMoves playerMoves = new PlayerMoves();
        for (Piece piece : currentPieces) {
            PieceMoves pieceMoves = piece.getValidMovesOn(board);
            playerMoves.add(piece, pieceMoves);
        }
        return playerMoves;
    }

    @Override
    public String toString() {
        return this.color.toString();
    }
}
