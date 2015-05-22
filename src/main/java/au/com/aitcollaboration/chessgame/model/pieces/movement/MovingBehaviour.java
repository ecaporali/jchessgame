package au.com.aitcollaboration.chessgame.model.pieces.movement;

import au.com.aitcollaboration.chessgame.model.board.Board;
import au.com.aitcollaboration.chessgame.model.pieces.Piece;
import au.com.aitcollaboration.chessgame.model.pieces.PieceMoves;

public interface MovingBehaviour {

    public PieceMoves getMoves(Board board, Piece piece);
}
