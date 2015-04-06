package au.com.aitcollaboration.chessgame.game;

import au.com.aitcollaboration.chessgame.board.Board;
import au.com.aitcollaboration.chessgame.board.Square;
import au.com.aitcollaboration.chessgame.pieces.Piece;
import au.com.aitcollaboration.chessgame.pieces.ValidMoves;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Rules {

    private Map<Piece, ValidMoves> possibleMoves;

    public Rules() {
        this.possibleMoves = new HashMap<Piece, ValidMoves>();
    }

    public boolean isCheckMate(Board board) {
        return true;
    }

    public boolean isMatchDraw(List<Board> movesHistory) {
        return true;
    }


    public void getPossibleMovesOn(Board board) {
        possibleMoves.clear();

        Square[][] grid = board.getClonedGrid();

        for (Square[] squares : grid) {
            for (Square square : squares) {
                if (square.hasPiece()) {
                    Piece piece = square.getPiece();
                    ValidMoves validMoves = piece.getValidMovesOn(board);
                    possibleMoves.put(piece, validMoves);
                }
            }
        }
    }

    public ValidMoves getValidMovesFor(Piece piece) {
        return possibleMoves.get(piece);
    }
}
