package psychicpoker;

import org.apache.commons.lang.ArrayUtils;
import org.junit.Test;

import static junit.framework.Assert.*;


/**
 * Created with IntelliJ IDEA.
 * User: Admin
 * Date: 31.01.13
 * Time: 23:21
 * To change this template use File | Settings | File Templates.
 */
public class HandTest {

    @Test(expected = IllegalArgumentException.class)
    public void buildHandTooShort() {
        Hand.valueOf("2C 3D");
    }

    @Test(expected = IllegalArgumentException.class)
    public void buildHandIncorrectCard() {
        Hand.valueOf("1C");
    }

    @Test
    public void isFlush() {
        assertTrue(Hand.valueOf("2C 3C 4C 5C 6C").isFlush());
        assertFalse(Hand.valueOf("2C 3D 4C 5C 6C").isFlush());
        assertFalse(Hand.valueOf("2C 3D 4C 5S 6C").isFlush());
    }

    @Test
    public void isStraight() {
        assertFalse(Hand.valueOf("2C 2D 4C 5C 6C").isStraight());
        assertFalse(Hand.valueOf("TC JD QC 2C AD").isStraight());

        assertTrue(Hand.valueOf("TC JD QC KC AD").isStraight());
        assertTrue(Hand.valueOf("2C 3C 4C 5C 6C").isStraight());

        assertTrue(Hand.valueOf("2C 3C 4C 5C AC").isStraight());
    }

    @Test
    public void handCategory() {
        assertEquals(Hand.Category.STRAIGHT_FLUSH, Hand.valueOf("TH JH QH KH AH").getCategory());
        assertEquals(Hand.Category.FOUR_OF_A_KIND, Hand.valueOf("3H 3S 3C 2D 3D").getCategory());
        assertEquals(Hand.Category.FULL_HOUSE, Hand.valueOf("2H 2S 3H 3S 3C").getCategory());
        assertEquals(Hand.Category.FLUSH, Hand.valueOf("AH 6H 9H 5H 7H").getCategory());
        assertEquals(Hand.Category.STRAIGHT, Hand.valueOf("AC 2D 3S 5S 4D").getCategory());
        assertEquals(Hand.Category.THREE_OF_A_KIND, Hand.valueOf("KC 2C TC 2D 2H").getCategory());
        assertEquals(Hand.Category.TWO_PAIRS, Hand.valueOf("QH KS JS JD KD").getCategory());
        assertEquals(Hand.Category.ONE_PAIR, Hand.valueOf("2H TC 4C 9S 9C").getCategory());
        assertEquals(Hand.Category.HIGHEST_CARD, Hand.valueOf("6S KH 9H AD QH").getCategory());
    }

    @Test
    public void bestHand() {
    }

    @Test
    public void groupByKind() {
        assertTrue(ArrayUtils.isEquals(new int[] {3, 2}, Hand.valueOf("2C 5D 2S 5C 5H").groupByKind()));
        assertTrue(ArrayUtils.isEquals(new int[] {3, 1, 1}, Hand.valueOf("2C KH 2D 2S AC").groupByKind()));
        assertTrue(ArrayUtils.isEquals(new int[] {1, 1, 1, 1, 1}, Hand.valueOf("2C 3C 4C 5C 6C").groupByKind()));
    }
}
