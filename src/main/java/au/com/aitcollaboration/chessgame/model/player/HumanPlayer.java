package au.com.aitcollaboration.chessgame.model.player;

import au.com.aitcollaboration.chessgame.controller.GameController;
import au.com.aitcollaboration.chessgame.controller.RulesController;
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
    public void play(GameController gameController, RulesController rulesController) {
        Square fromSquare = getFromSquare(gameController, rulesController);
        PlayerMoves playerMoves = rulesController.getPlayerMoves(pieces);

        PieceMoves pieceMoves = playerMoves.getPieceMoves(fromSquare.getPiece());

        gameController.showPracticalMoves(pieceMoves);

        Square toSquare = getToSquare(gameController, pieceMoves);

        gameController.movePiece(fromSquare, toSquare);
    }

    private Square getFromSquare(GameController gameController, RulesController rulesController) {
        PlayerMoves playerMoves = null;
        Square fromSquare = null;

        while (playerMoves == null) {
            try {
                fromSquare = gameController.getFromSquare();
                playerMoves = rulesController.getPlayerMoves(fromSquare, pieces);
            } catch (Exception e) {
                MyLogger.debug(e);
                gameController.showError(e.getMessage());
            }
        }

        return fromSquare;
    }

    private Square getToSquare(GameController gameController, PieceMoves pieceMoves) {
        Square toSquare = gameController.getToSquare();
        while (!pieceMoves.contains(toSquare)) {
            gameController.showError(UIMessages.INVALID_MOVE_EXCEPTION);
            toSquare = gameController.getToSquare();
        }
        return toSquare;
    }
}
