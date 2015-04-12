package au.com.aitcollaboration.chessgame.pieces.movement;

import au.com.aitcollaboration.chessgame.board.Board;
import au.com.aitcollaboration.chessgame.pieces.Piece;
import au.com.aitcollaboration.chessgame.pieces.PracticalMoves;

public interface MovingBehaviour {

    public PracticalMoves getMoves(Board board, Piece piece);
}
