package au.com.aitcollaboration.chessgame.pieces.movement;

import au.com.aitcollaboration.chessgame.board.Board;
import au.com.aitcollaboration.chessgame.pieces.Piece;
import au.com.aitcollaboration.chessgame.pieces.ValidMoves;

public interface MovingBehaviour {

    public ValidMoves getMoves(Board board, Piece piece);
}
