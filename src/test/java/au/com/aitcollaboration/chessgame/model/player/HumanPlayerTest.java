package au.com.aitcollaboration.chessgame.model.player;

import au.com.aitcollaboration.chessgame.Color;
import au.com.aitcollaboration.chessgame.controller.Game;
import au.com.aitcollaboration.chessgame.controller.Rules;
import au.com.aitcollaboration.chessgame.model.game.structure.Square;
import au.com.aitcollaboration.chessgame.model.moves.PieceMoves;
import au.com.aitcollaboration.chessgame.model.moves.PlayerMoves;
import au.com.aitcollaboration.chessgame.model.pieces.King;
import au.com.aitcollaboration.chessgame.model.pieces.Piece;
import au.com.aitcollaboration.chessgame.model.pieces.Pieces;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.initMocks;

public class HumanPlayerTest {

    @Mock
    private Pieces pieces;
    @Mock
    private Game game;
    @Mock
    private Rules rules;
    @Mock
    private PlayerMoves playerMoves;

    private Player player;

    @Before
    public void setUp() throws Exception {
        initMocks(this);
        player = new HumanPlayer("");
        player.setPieces(pieces);
    }

    @Test
    public void testPlayerShouldReturnTrueWhenIsOpponentPiecePlayer() throws Exception {
        when(pieces.contains(any(Piece.class))).thenReturn(false);
        Piece king = new King(Color.BLACK);
        boolean ownPiece = player.isOwnPiece(king);

        assertFalse(ownPiece);
    }

    @Test
    public void testPlayerShouldReturnFalseWhenIsOpponentPiecePlayer() throws Exception {
        when(pieces.contains(any(Piece.class))).thenReturn(true);
        Piece king = new King(Color.BLACK);
        boolean ownPiece = player.isOwnPiece(king);

        assertTrue(ownPiece);
    }

    @Test
    public void testMovePieceIsCalledWhenPlayerPlayGame() throws Exception {
        Square fromSquare = new Square(0, 0);
        Square toSquare = new Square(1, 1);
        PieceMoves pieceMoves = new PieceMoves(fromSquare);
        pieceMoves.add(toSquare);

        when(game.getFromSquare()).thenReturn(fromSquare);
        when(rules.getPlayerMoves(any(Square.class), any(Pieces.class))).thenReturn(playerMoves);
        when(rules.getPlayerMoves(pieces)).thenReturn(playerMoves);
        when(playerMoves.getPieceMoves(any(Piece.class))).thenReturn(pieceMoves);
        doAnswer(new Answer<Void>() {
            @Override
            public Void answer(InvocationOnMock invocationOnMock) throws Throwable {
                return null;
            }
        }).when(game).showPracticalMoves(pieceMoves);

        when(game.getToSquare()).thenReturn(toSquare);

        player.play(game, rules);

        verify(game).movePiece(fromSquare, toSquare);
    }
}
