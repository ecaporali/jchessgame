package au.com.aitcollaboration.chessgame.model.player;

import au.com.aitcollaboration.chessgame.model.board.Square;
import au.com.aitcollaboration.chessgame.controller.Game;
import au.com.aitcollaboration.chessgame.model.pieces.PieceMoves;
import au.com.aitcollaboration.chessgame.model.pieces.PlayerMoves;

public class HumanPlayer extends Player {

    public HumanPlayer(String name) {
        super(name);
    }

    @Override
    public void play(Game game) {

        Square fromSquare = game.getFromSquare(pieces);

        PlayerMoves playerMoves = game.getPlayerMoves(pieces);

        PieceMoves pieceMoves = playerMoves.getPieceMoves(fromSquare.getPiece());

        game.showPracticalMoves(pieceMoves);

        Square toSquare = game.getToSquareFrom(pieceMoves);

        game.movePiece(fromSquare, toSquare);
    }
}
