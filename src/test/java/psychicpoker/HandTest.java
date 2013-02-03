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

        assertTrue(Hand.build("TC JD QC KC AD").isStraight());
        assertTrue(Hand.build("2C 3C 4C 5C 6C").isStraight());

        assertTrue(Hand.build("2C 3C 4C 5C AC").isStraight());
    }

    @Test
    public void handCategory() {
        //Hand: TH JH QC QD QS Deck: QH KH AH 2S 6S Best hand: straight-flush
        assertEquals(Hand.Category.STRAIGHT_FLUSH, Hand.build("TH JH QH KH AH").getCategory());
        //Hand: 2H 2S 3H 3S 3C Deck: 2D 3D 6C 9C TH Best hand: four-of-a-kind
        assertEquals(Hand.Category.FOUR_OF_A_KIND, Hand.build("3H 3S 3C 2D 3D").getCategory());
        //Hand: 2H 2S 3H 3S 3C Deck: 2D 9C 3D 6C TH Best hand: full-house
        assertEquals(Hand.Category.FULL_HOUSE, Hand.build("2H 2S 3H 3S 3C").getCategory());
        //Hand: 2H AD 5H AC 7H Deck: AH 6H 9H 4H 3C Best hand: flush
        assertEquals(Hand.Category.FLUSH, Hand.build("AH 6H 9H 5H 7H").getCategory());
        //Hand: AC 2D 9C 3S KD Deck: 5S 4D KS AS 4C Best hand: straight
        assertEquals(Hand.Category.THREE_OF_KIND, Hand.build("KC 2C TC 2D 2H").getCategory());
        //Hand: KS AH 2H 3C 4H Deck: KC 2C TC 2D AS Best hand: three-of-a-kind
        assertEquals(Hand.Category.TWO_PAIRS, Hand.build("QH KS JS JD KD").getCategory());
        //Hand: AH 2C 9S AD 3C Deck: QH KS JS JD KD Best hand: two-pairs
        assertEquals(Hand.Category.ONE_PAIR, Hand.build("2H TC 4C 9S 9C").getCategory());
        //Hand: 6C 9C 8C 2D 7C Deck: 2H TC 4C 9S AH Best hand: one-pair
        assertEquals(Hand.Category.HIGHEST_CARD, Hand.build("6S KH 9H AD QH").getCategory());
        //Hand: 3D 5S 2H QD TD Deck: 6S KH 9H AD QH Best hand: highest-card
    }

    @Test
    public void bestHand() {
    }

    @Test
    public void groupByKind() {
        assertTrue(ArrayUtils.isEquals(new int[] {3, 2}, Hand.build("2C 5D 2S 5C 5H").groupByKind()));
        assertTrue(ArrayUtils.isEquals(new int[] {3, 1, 1}, Hand.build("2C KH 2D 2S AC").groupByKind()));
        assertTrue(ArrayUtils.isEquals(new int[] {1, 1, 1, 1, 1}, Hand.build("2C 3C 4C 5C 6C").groupByKind()));
    }
}
