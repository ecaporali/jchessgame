package au.com.aitcollaboration.chessgame.model.pieces;

import au.com.aitcollaboration.chessgame.model.game.structure.Board;
import au.com.aitcollaboration.chessgame.model.moves.PieceMoves;
import au.com.aitcollaboration.chessgame.model.pieces.movement.MovingBehaviour;
import au.com.aitcollaboration.chessgame.Color;

public abstract class Piece {

    private final Color color;
    private final MovingBehaviour movingBehaviour;

    protected Piece(Color color, MovingBehaviour movingBehaviour) {
        this.color = color;
        this.movingBehaviour = movingBehaviour;
    }

    public boolean matches(Color otherColor){
        return this.color.equals(otherColor);
    }

    public abstract int[][] commonMoves();

    public PieceMoves getValidMovesOn(Board board) {
        return movingBehaviour.getMoves(board, this);
    }

    public Color getColor() {
        return color;
    }

    public boolean matches(Class pieceClass){
        return this.getClass().equals(pieceClass);
    }
}
