package au.com.aitcollaboration.chessgame.model.player;

import au.com.aitcollaboration.chessgame.Color;
import au.com.aitcollaboration.chessgame.model.pieces.King;
import au.com.aitcollaboration.chessgame.model.pieces.Piece;
import au.com.aitcollaboration.chessgame.model.pieces.Pieces;
import au.com.aitcollaboration.chessgame.view.GameView;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import static org.junit.Assert.*;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Matchers.isNotNull;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

public class HumanPlayerTest {

    @Mock
    private Pieces pieces;
    @Mock
    private GameView gameView;

    private HumanPlayer player;

    @Before
    public void setUp() throws Exception {
        initMocks(this);
        player = new HumanPlayer("", Color.BLACK, gameView);
    }

    @Test
    public void getValidCoordinatesShouldReturnNotNullCoordinates() throws Exception {
        when(gameView.getTextAnswer(anyString())).thenReturn("a1");

        int[] coordinates = player.getValidCoordinates("");

        assertNotNull(coordinates);
        assertEquals(7, coordinates[0]);
        assertEquals(0, coordinates[1]);
    }
}
