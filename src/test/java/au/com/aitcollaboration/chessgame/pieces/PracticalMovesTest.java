package au.com.aitcollaboration.chessgame.pieces;

import au.com.aitcollaboration.chessgame.board.Square;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class PracticalMovesTest {

    @Test
    public void testValidMovesShouldAddMovesToList() throws Exception {
        PracticalMoves practicalMoves = new PracticalMoves();
        assertThat(practicalMoves.size(), is(0));

        for (int i = 0; i < 8; i++)
            practicalMoves.add(new Square(i, i));

        assertThat(practicalMoves.size(), is(8));
    }
}