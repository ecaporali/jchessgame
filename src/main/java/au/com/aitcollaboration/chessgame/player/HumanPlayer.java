package au.com.aitcollaboration.chessgame.player;

import au.com.aitcollaboration.chessgame.board.Square;
import au.com.aitcollaboration.chessgame.game.Game;
import au.com.aitcollaboration.chessgame.pieces.PieceMoves;
import au.com.aitcollaboration.chessgame.pieces.PlayerMoves;

public class HumanPlayer extends Player {

    public HumanPlayer(String name) {
        super(name);
    }

    @Override
    public void play(Game game) {

        Square fromSquare = game.getFromSquare(pieces);

        PlayerMoves playerMoves = game.getValidPiecesMoves(pieces);

        PieceMoves pieceMoves = playerMoves.getPieceMoves(fromSquare.getPiece());

        game.showPracticalMoves(pieceMoves);

        Square toSquare = game.getToSquareFrom(pieceMoves);

        game.movePiece(fromSquare, toSquare);
    }
}
