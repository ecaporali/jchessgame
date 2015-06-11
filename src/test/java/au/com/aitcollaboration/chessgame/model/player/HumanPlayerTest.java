package au.com.aitcollaboration.chessgame.model.player;

import au.com.aitcollaboration.chessgame.Color;
import au.com.aitcollaboration.chessgame.model.pieces.King;
import au.com.aitcollaboration.chessgame.model.pieces.Piece;
import au.com.aitcollaboration.chessgame.model.pieces.Pieces;
import au.com.aitcollaboration.chessgame.view.GameView;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

public class HumanPlayerTest {

    @Mock
    private Pieces pieces;
    @Mock
    private GameView gameView;

    private Player player;

    @Before
    public void setUp() throws Exception {
        initMocks(this);
        player = new HumanPlayer("", Color.BLACK, gameView);
    }

//    @Test
//    public void testPlayerShouldReturnTrueWhenIsOpponentPiecePlayer() throws Exception {
//        when(pieces.contains(any(Piece.class))).thenReturn(false);
//        Piece king = new King(Color.BLACK);
//        boolean ownPiece = player.isOwnPiece(king);
//
//        assertFalse(ownPiece);
//    }
//
//    @Test
//    public void testPlayerShouldReturnFalseWhenIsOpponentPiecePlayer() throws Exception {
//        when(pieces.contains(any(Piece.class))).thenReturn(true);
//        Piece king = new King(Color.BLACK);
//        boolean ownPiece = player.isOwnPiece(king);
//
//        assertTrue(ownPiece);
//    }
}
