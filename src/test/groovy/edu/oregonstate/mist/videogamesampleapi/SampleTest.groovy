package edu.oregonstate.mist.videogamesampleapi

import edu.oregonstate.mist.videogamesampleapi.core.Sample
import org.junit.Test
import static org.junit.Assert.*

class SampleTest {
    @Test
    public void testSample() {
        assertTrue(new Sample().message == 'hello world')
    }
}
