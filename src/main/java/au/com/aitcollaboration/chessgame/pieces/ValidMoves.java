package au.com.aitcollaboration.chessgame.pieces;

import au.com.aitcollaboration.chessgame.board.Position;
import au.com.aitcollaboration.chessgame.board.Square;

import java.util.ArrayList;
import java.util.List;

public class ValidMoves {

    private List<Square> possibleMoves;

    public ValidMoves() {
        this.possibleMoves = new ArrayList<Square>();
    }

    public void add(Square square) {
        possibleMoves.add(square);
    }

    public boolean isEmpty(){
        return possibleMoves.size() == 0;
    }

    public int size(){
        return possibleMoves.size();
    }

    @Override
    public String toString() {
        String s = "Valid Moves: ";
        for(Square square : possibleMoves){
            Position position = square.getPosition();
            s += position.toString();
        }
        return s;
    }
}
