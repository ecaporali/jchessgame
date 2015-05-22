package au.com.aitcollaboration.chessgame.model.pieces;

import au.com.aitcollaboration.chessgame.model.board.Board;
import au.com.aitcollaboration.chessgame.model.pieces.movement.MovingBehaviour;
import au.com.aitcollaboration.chessgame.model.player.Color;

public abstract class Piece {

    private MovingBehaviour movingBehaviour;
    private Color color;

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
}
