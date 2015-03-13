package au.com.aitcollaboration.chessgame.pieces;

import au.com.aitcollaboration.chessgame.board.Board;
import au.com.aitcollaboration.chessgame.player.Color;

public abstract class Piece {

    protected Color color;
    protected Moves moves;

    protected Piece(Color color) {
        this.color = color;
        this.moves = new Moves();
    }

    public boolean matches(Color color) {
        return this.color.equals(color);
    }

    public abstract int[][] commonMoves();

    public abstract void validMovesOn(Board board);

    public Moves getValidMovesOn(Board board) {
        validMovesOn(board);
        return moves;
    }
}
