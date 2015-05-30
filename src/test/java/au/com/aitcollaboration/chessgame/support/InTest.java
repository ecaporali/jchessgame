package au.com.aitcollaboration.chessgame.support;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.powermock.modules.junit4.PowerMockRunner;

import java.io.*;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertEquals;
import static org.mockito.MockitoAnnotations.initMocks;

@RunWith(PowerMockRunner.class)
public class InTest {

    @Mock
    private PrintStream printStream;

    private PrintStream originalOut = System.out;
    private InputStream originalIn = System.in;

    @Before
    public void setUp() throws Exception {
        initMocks(this);
        setMockedSystem();
    }

    @Test
    public void testNextLineShouldReturnUserInput() {
        String userInput = "answer";
        setIn(userInput);
        String answer = In.nextLine("question?");
        assertThat(answer, is(userInput));
    }

    @Test
    public void testNextIntShouldReturnUserInput() {
        String userInput = "1";
        setIn(userInput);
        int answer = In.nextInt("question?");
        assertThat(answer, is(Integer.valueOf(userInput)));
    }

    @Test
    public void testNextDoubleShouldReturnUserInput() {
        String userInput = "1.5";
        setIn(userInput);
        double answer = In.nextDouble("question?");
        assertEquals(answer, Double.valueOf(userInput), 0.01);
    }

    @Test
    public void testNextCharShouldReturnUserInput() {
        String userInput = "c";
        setIn(userInput);
        char answer = In.nextChar("question?");
        assertEquals(userInput, String.valueOf(answer));
    }

    @After
    public void tearDown() throws Exception {
        System.setOut(originalOut);
        System.setIn(originalIn);
    }

    private void setMockedSystem() {
        System.setOut(printStream);
    }

    private void setIn(String userInput) {
        In.setInputStream(new BufferedReader(new InputStreamReader(new ByteArrayInputStream(userInput.getBytes()))));
    }
}
