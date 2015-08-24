package au.com.aitcollaboration.chessgame.model.moves;

import au.com.aitcollaboration.chessgame.Color;
import au.com.aitcollaboration.chessgame.model.game.structure.Board;
import au.com.aitcollaboration.chessgame.model.game.structure.Position;
import au.com.aitcollaboration.chessgame.model.game.structure.Square;
import au.com.aitcollaboration.chessgame.model.pieces.Piece;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.Iterator;
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
        return practicalMoves.isEmpty();
    }

    public int size() {
        return practicalMoves.size();
    }

    public boolean contains(Square square) {
        return this.practicalMoves.contains(square);
    }

    public void toValidMoves(Board board, Square kingSquare, PlayerMoves opponentInitialMoves) {

        Piece king = kingSquare.getPiece();
        Color currentColor = king.getColor();

        List<Square> dangeringKingSquares = opponentInitialMoves.getDangeringKingSquare(kingSquare);

        Iterator<Square> iterator = practicalMoves.iterator();

        while (iterator.hasNext()) {
            Square possibleToSquare = iterator.next();

            PlayerMoves opponentPlayerMoves = this.calculateOpponentMoves(possibleToSquare, board, currentColor);

            if (currentSquare.contains(king))
                kingSquare = possibleToSquare;

            if (opponentPlayerMoves.contains(kingSquare))
                if (!dangeringKingSquares.contains(kingSquare))
                    iterator.remove();
        }
    }

    private PlayerMoves calculateOpponentMoves(Square toSquare, Board board, Color currentColor) {
        Piece tempToPiece = toSquare.getPiece();

        board.doSimulateMovePiece(currentSquare, toSquare);

        PlayerMoves opponentPlayerMoves = board.calculateOpponentPlayerMoves(currentColor);

        board.undoSimulateMovePiece(currentSquare, toSquare, tempToPiece);

        return opponentPlayerMoves;
    }

    public final Square getCurrentSquare() {
        return currentSquare;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        practicalMoves.stream().filter(square -> square != null).forEach(square -> {
            Position position = square.getPosition();
            builder.append(position.toString());
        });
        String moves = builder.toString();
        return (moves.isEmpty()) ? "" : "Valid Moves: " + moves;
    }
}
