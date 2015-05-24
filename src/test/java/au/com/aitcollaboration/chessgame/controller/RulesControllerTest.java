package au.com.aitcollaboration.chessgame.controller;

import au.com.aitcollaboration.chessgame.model.game.structure.Board;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import static org.junit.Assert.assertFalse;
import static org.mockito.MockitoAnnotations.initMocks;

public class RulesControllerTest {

    @Mock
    private Board board;

    private RulesController rulesController;

    @Before
    public void setUp() throws Exception {
        initMocks(this);
        rulesController = new RulesController(board);
    }

    @Test
    public void testIsGameOverShouldReturnFalse() {
        boolean isGameOver = rulesController.isGameOver();
        assertFalse(isGameOver);
    }
}
