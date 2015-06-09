package au.com.aitcollaboration.chessgame.model.moves;

import au.com.aitcollaboration.chessgame.model.game.structure.Position;
import au.com.aitcollaboration.chessgame.model.game.structure.Square;

import java.util.ArrayList;
import java.util.List;

public class PieceMoves {

    private final Square currentSquare;
    private final List<Square> practicalMoves;

    public PieceMoves(Square currentSquare) {
        this.currentSquare = currentSquare;
        this.practicalMoves = new ArrayList<>();
    }

    public void add(Square square) {
        practicalMoves.add(square);
    }

    public boolean isEmpty() {
        return practicalMoves.size() == 0;
    }

    public int size() {
        return practicalMoves.size();
    }

//    public boolean isKingInDanger(Square fromSquare, Square kingSquare) {
//        return practicalMoves.contains(fromSquare) && practicalMoves.contains(kingSquare);
//    }

    public boolean contains(Square square) {
        return this.practicalMoves.contains(square);
    }

    public Square getCurrentSquare() {
        return currentSquare;
    }

//    public boolean matches(Square opponentSquare) {
//        return currentSquare.equals(opponentSquare);
//    }

    @Override
    public String toString() {
        String s = "Valid Moves: ";
        for (Square square : practicalMoves) {
            if (square != null) {
                Position position = square.getPosition();
                s += position.toString();
            }
        }
        return s;
    }

//    public void clear() {
//        practicalMoves.clear();
//    }
//
//    public boolean isKingInCheck(Square kingSquare) {
//        return practicalMoves.contains(kingSquare);
//    }
//
//    public boolean canBeSacrificed(PieceMoves pieceMoves) {
//        //TODO: finish this
//        return false;
//    }
//
//    public List<Square> getPracticalMoves() {
//        return practicalMoves;
//    }

//    public PieceMoves getValidMoves(Color color, Board board) {
//        ValidationResult validationResult = new ValidationResult(currentSquare);
//
//        if(practicalMoves.isEmpty())
//            validationResult.addErrorMessage(UIMessages.PIECE_CANNOT_BE_MOVED_EXCEPTION);
//
//        for (Square toSquare : practicalMoves) {
//
//            Piece toPiece = toSquare.getPiece();
//
//            board.movePiece(currentSquare, toSquare);
//
//            PlayerMoves opponentPossibleMoves = board.getOpponentPossibleMoves(color.flip());
//
//            board.undoMovePiece(currentSquare, toSquare, toPiece);
//
//            Square kingSquare = board.getCurrentKingSquare(color);
//
//            if (opponentPossibleMoves.contains(kingSquare))
//                validationResult.addErrorMessage(UIMessages.KING_IN_DANGER_EXCEPTION);
//            else
//                validationResult.clearErrors();
//
//            validationResult.addValidSquare(toSquare);
//        }
//        return pieceMoves;
//    }

    public List<Square> getPracticalMoves() {
        return practicalMoves;
    }
}
