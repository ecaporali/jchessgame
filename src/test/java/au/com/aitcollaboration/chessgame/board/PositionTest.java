package au.com.aitcollaboration.chessgame.board;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class PositionTest {

    private Position position = new Position(0, 0);

    @Test
    public void testNextPositionShouldReturnValidPosition() throws Exception {
        Position expectedPosition = new Position(1, 1);
        Position nextPosition = position.nextPosition(1, 1);
        assertEquals(expectedPosition, nextPosition);
    }
}