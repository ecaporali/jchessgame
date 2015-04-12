package au.com.aitcollaboration.chessgame.player;

import au.com.aitcollaboration.chessgame.board.Square;
import au.com.aitcollaboration.chessgame.game.Game;
import au.com.aitcollaboration.chessgame.pieces.PracticalMoves;

public class HumanPlayer extends Player {

    public HumanPlayer(String name) {
        super(name);
    }

    @Override
    public void play(Game game) {
        Square fromSquare = game.getFromSquare();

        PracticalMoves practicalMoves = game.getValidMovesFor(pieces);

        game.showPracticalMoves(practicalMoves);

        Square toSquare = game.getToSquareOn(practicalMoves);

        game.movePiece(fromSquare, toSquare);
    }
}
