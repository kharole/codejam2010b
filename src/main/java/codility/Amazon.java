package codility;

import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * User: Admin
 * Date: 29.01.13
 * Time: 19:13
 * To change this template use File | Settings | File Templates.
 */
public class Amazon {

    public static interface Stream {

        public char getNextChar();

        public boolean isEndOfStream();
    }

    private static final int OFFSET = 32;
    private static final int LEN = 95;

    public Integer[] findMostFrequentChars(Stream s, int limit) {
        if(limit > LEN)
            throw new IllegalArgumentException();

        int[] frequencies = new int[LEN];

        while (s.isEndOfStream()) {
            int c = (int)s.getNextChar();
            frequencies[c-OFFSET]--;
        }

        Map<Integer, Integer> freqMap = new TreeMap<Integer, Integer>();
        for (int i=0; i<frequencies.length; i++) {
            freqMap.put(frequencies[i], i+OFFSET);
        }

        Integer[] result = freqMap.values().toArray(new Integer[LEN]);

        return Arrays.copyOf(result, limit);
    }
}
