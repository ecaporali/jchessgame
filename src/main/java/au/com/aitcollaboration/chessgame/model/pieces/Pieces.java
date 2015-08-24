package au.com.aitcollaboration.chessgame.model.pieces;

import au.com.aitcollaboration.chessgame.model.game.structure.Board;
import au.com.aitcollaboration.chessgame.model.moves.PieceMoves;
import au.com.aitcollaboration.chessgame.model.moves.PlayerMoves;

import java.util.ArrayList;
import java.util.List;

public class Pieces {

    private final List<Piece> currentPieces;
    private final List<Piece> lostPieces;

    public Pieces() {
        this.currentPieces = new ArrayList<>(16);
        this.lostPieces = new ArrayList<>(16);
    }

    public void add(Piece piece) {
        currentPieces.add(piece);
    }

    public void remove(Piece piece) {
        this.currentPieces.remove(piece);
        this.lostPieces.add(piece);
    }

    public boolean contains(Piece piece) {
        return currentPieces.contains(piece);
    }

    public int size() {
        return currentPieces.size();
    }

    public Piece getPiece(Class pieceClass) {
        for (Piece piece : currentPieces) {
            if (piece.matches(pieceClass)) {
                return piece;
            }
        }
        return null;
    }

    public boolean isKingLastPieceStanding() {
        return currentPieces.size() == 1;
    }

    public PlayerMoves getValidMovesOn(Board board) {
        PlayerMoves playerMoves = new PlayerMoves();
        for (Piece piece : currentPieces) {
            PieceMoves pieceMoves = piece.getValidMovesOn(board);
            playerMoves.add(piece, pieceMoves);
        }
        return playerMoves;
    }

    public void clear() {
        currentPieces.clear();
        lostPieces.clear();
    }
}
