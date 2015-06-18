package au.com.aitcollaboration.chessgame.model.game.structure;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class PositionTest {

    private Position position = new Position(0, 0);

    @Test
    public void nextPositionShouldReturnNewPosition() throws Exception {
        Position expectedPosition = new Position(1, 1);
        Position nextPosition = position.nextPosition(1, 1);
        assertEquals(expectedPosition, nextPosition);
    }
}