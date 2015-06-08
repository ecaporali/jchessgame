package au.com.aitcollaboration.chessgame.controller;

import au.com.aitcollaboration.chessgame.model.game.structure.Board;
import au.com.aitcollaboration.chessgame.service.ValidationService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import static org.junit.Assert.assertFalse;
import static org.mockito.MockitoAnnotations.initMocks;

public class RulesTest {

    @Mock
    private Board board;
    @Mock
    private ValidationService validationService;

    private Rules rules;

    @Before
    public void setUp() throws Exception {
        initMocks(this);
        rules = new Rules(validationService);
    }

    @Test
    public void testIsGameOverShouldReturnFalse() {
        boolean isGameOver = rules.isGameOver();
        assertFalse(isGameOver);
    }
}
