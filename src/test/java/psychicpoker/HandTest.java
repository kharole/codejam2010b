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
        Hand.build("2C 3D");
    }

    @Test(expected = IllegalArgumentException.class)
    public void buildHandIncorrectCard() {
        Hand.build("1C");
    }

    @Test
    public void isFlush() {
        assertTrue(Hand.build("2C 3C 4C 5C 6C").isFlush());
        assertFalse(Hand.build("2C 3D 4C 5C 6C").isFlush());
        assertFalse(Hand.build("2C 3D 4C 5S 6C").isFlush());
    }

    @Test
    public void isStraight() {
        assertFalse(Hand.build("2C 2D 4C 5C 6C").isStraight());
        assertFalse(Hand.build("TC JD QC 2C AD").isStraight());
    }

    @Test
    public void straightHighestCard() {
        assertEquals(new Card("5C"), Hand.build("2C 3C 4C 5C AC").getStraightHighestCard());
        assertEquals(new Card("AD"), Hand.build("TC JD QC KC AD").getStraightHighestCard());
        assertEquals(new Card("6C"), Hand.build("2C 3C 4C 5C 6C").getStraightHighestCard());
    }

    @Test
    public void groupByKind() {
        assertTrue(ArrayUtils.isEquals(new int[] {3, 2}, Hand.build("2C 5D 2S 5C 5H").groupByKind()));
        assertTrue(ArrayUtils.isEquals(new int[] {3, 1, 1}, Hand.build("2C KH 2D 2S AC").groupByKind()));
        assertTrue(ArrayUtils.isEquals(new int[] {1, 1, 1, 1, 1}, Hand.build("2C 3C 4C 5C 6C").groupByKind()));
    }
}
