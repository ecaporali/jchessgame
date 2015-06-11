package au.com.aitcollaboration.chessgame.controller;

import au.com.aitcollaboration.chessgame.model.game.structure.Board;
import org.junit.Before;
import org.mockito.Mock;

import static org.mockito.MockitoAnnotations.initMocks;

public class RulesTest {

    @Mock
    private Board board;

    private Rules rules;

    @Before
    public void setUp() throws Exception {
        initMocks(this);
        rules = new Rules();
    }
}
