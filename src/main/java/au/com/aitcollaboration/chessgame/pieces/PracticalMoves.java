package au.com.aitcollaboration.chessgame.pieces;

import au.com.aitcollaboration.chessgame.board.Position;
import au.com.aitcollaboration.chessgame.board.Square;

import java.util.ArrayList;
import java.util.List;

public class PracticalMoves {

    private List<Square> practicalMoves;

    public PracticalMoves() {
        this.practicalMoves = new ArrayList<Square>();
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

    public boolean isKingInDanger(Square fromSquare, Square kingSquare) {
        return practicalMoves.contains(fromSquare) && practicalMoves.contains(kingSquare);
    }

    public boolean contains(Square square){
        return this.practicalMoves.contains(square);
    }

    @Override
    public String toString() {
        String s = "Valid Moves: ";
        for (Square square : practicalMoves) {
            if(square != null){
                Position position = square.getPosition();
                s += position.toString();
            }
        }
        return s;
    }

    public void clear() {
        practicalMoves.clear();
    }
}
