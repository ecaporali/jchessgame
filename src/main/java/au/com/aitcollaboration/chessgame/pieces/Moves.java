package au.com.aitcollaboration.chessgame.pieces;

import au.com.aitcollaboration.chessgame.board.Square;

import java.util.ArrayList;
import java.util.List;

public class Moves {

    private List<Square> possibleMoves;

    public Moves() {
        this.possibleMoves = new ArrayList<Square>();
    }

    public void add(Square square) {
        possibleMoves.add(square);
    }

    public void clear() {
        possibleMoves.clear();
    }
}
