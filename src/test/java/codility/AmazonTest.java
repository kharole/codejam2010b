package codility;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created with IntelliJ IDEA.
 * User: Admin
 * Date: 29.01.13
 * Time: 19:13
 * To change this template use File | Settings | File Templates.
 */
public class AmazonTest {

    public static class StreamMock implements Amazon.Stream {

        private char[] ca;
        int i = 0;

        public StreamMock(String str) {
            this.ca = str.toCharArray();
        }

        public char getNextChar() {
            return ca[i++];
        }

        public boolean isEndOfStream() {
            return i < ca.length;
        }

    }

    @Test
    public void testFindMostFrequentChars() throws Exception {
        StreamMock s = new StreamMock(" aaabaa~");
        Amazon a = new Amazon();
        Integer[] f = a.findMostFrequentChars(s, 3);
        assertEquals(3, f.length);
        assertEquals((int)'a', (int)f[0]);
    }
}
