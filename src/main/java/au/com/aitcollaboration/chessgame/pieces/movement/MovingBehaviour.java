package au.com.aitcollaboration.chessgame.pieces.movement;

import au.com.aitcollaboration.chessgame.board.Board;
import au.com.aitcollaboration.chessgame.pieces.Piece;
import au.com.aitcollaboration.chessgame.pieces.PieceMoves;
import au.com.aitcollaboration.chessgame.pieces.PlayerMoves;

public interface MovingBehaviour {

    public PieceMoves getMoves(Board board, Piece piece);
}
