package codility;

import org.junit.Test;


import static codility.App1.maxSubSeq;
import static codility.App1.maxSubSeq2;
import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertTrue;

/**
 * Created with IntelliJ IDEA.
 * User: Admin
 * Date: 16.01.13
 * Time: 23:48
 * To change this template use File | Settings | File Templates.
 */
public class App1Test {

    @Test
    public void testNumberOfTests() throws Exception {
        App1 a = new App1();
        System.out.println(a.maxPeopleCount(1, 3, 2));
        System.out.println(a.maxPeopleCount(1, 4, 2));
        System.out.println(a.maxPeopleCount(50, 1, 2));
        System.out.println(a.maxPeopleCount(50, 2, 2));
    }

    @Test
    public void testMaxSubSeq() throws Exception {
        assertEquals(11, maxSubSeq(new int[]{1, -4, 9, -1, 3}));
        assertEquals(23, maxSubSeq(new int[]{1, 2, -5, 6, 10, 7, -9}));
        assertEquals(1, maxSubSeq(new int[]{1}));
        assertEquals(-1, maxSubSeq(new int[]{-1, -2}));
        assertEquals(6, maxSubSeq(new int[]{-2, 1, -3, 4, -1, 2, 1, -5, 4}));
    }

    @Test
    public void testMaxSubSeq2() throws Exception {
        assertEquals(11, maxSubSeq2(new int[]{1, -4, 9, -1, 3}));
        assertEquals(1, maxSubSeq2(new int[]{1}));
        assertEquals(-1, maxSubSeq2(new int[]{-1, -2}));
        assertEquals(6, maxSubSeq2(new int[]{-2, 1, -3, 4, -1, 2, 1, -5, 4}));
        assertEquals(23, maxSubSeq2(new int[]{1, 2, -5, 6, 10, 7, -9}));
    }

}
