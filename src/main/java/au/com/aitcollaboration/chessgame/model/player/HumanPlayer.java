package au.com.aitcollaboration.chessgame.model.player;

import au.com.aitcollaboration.chessgame.controller.Game;
import au.com.aitcollaboration.chessgame.controller.Rules;
import au.com.aitcollaboration.chessgame.model.game.structure.Square;
import au.com.aitcollaboration.chessgame.model.moves.PieceMoves;
import au.com.aitcollaboration.chessgame.model.moves.PlayerMoves;
import au.com.aitcollaboration.chessgame.support.MyLogger;
import au.com.aitcollaboration.chessgame.support.UIMessages;

public class HumanPlayer extends Player {

    public HumanPlayer(String name) {
        super(name);
    }

    @Override
    public void play(Game game, Rules rules) {
        Square fromSquare = getFromSquare(game, rules);
        PlayerMoves playerMoves = rules.getPlayerMoves(pieces);

        PieceMoves pieceMoves = playerMoves.getPieceMoves(fromSquare.getPiece());

        game.showPracticalMoves(pieceMoves);

        Square toSquare = getToSquare(game, pieceMoves);

        game.movePiece(fromSquare, toSquare);
    }

    private Square getFromSquare(Game game, Rules rules) {
        PlayerMoves playerMoves = null;
        Square fromSquare = null;

        while (playerMoves == null) {
            try {
                fromSquare = game.getFromSquare();
                playerMoves = rules.getPlayerMoves(fromSquare, pieces);
            } catch (Exception e) {
                MyLogger.debug(e);
                game.showError(e.getMessage());
            }
        }

        return fromSquare;
    }

    private Square getToSquare(Game game, PieceMoves pieceMoves) {
        Square toSquare = game.getToSquare();
        while (!pieceMoves.contains(toSquare)) {
            game.showError(UIMessages.INVALID_MOVE_EXCEPTION);
            toSquare = game.getToSquare();
        }
        return toSquare;
    }
}
