package psychicpoker;

import com.google.common.primitives.Ints;
import org.junit.Test;

import java.util.*;

import static junit.framework.Assert.*;
import static junit.framework.Assert.assertEquals;

public class PsychicTest {

    @Test
    public void analyze() {
        System.out.println(Psychic.analyze("TH JH QC QD QS QH KH AH 2S 6S"));
        System.out.println(Psychic.analyze("2H 2S 3H 3S 3C 2D 3D 6C 9C TH"));
        System.out.println(Psychic.analyze("2H 2S 3H 3S 3C 2D 9C 3D 6C TH"));
        System.out.println(Psychic.analyze("2H AD 5H AC 7H AH 6H 9H 4H 3C"));
        System.out.println(Psychic.analyze("AC 2D 9C 3S KD 5S 4D KS AS 4C"));
        System.out.println(Psychic.analyze("KS AH 2H 3C 4H KC 2C TC 2D AS"));
        System.out.println(Psychic.analyze("AH 2C 9S AD 3C QH KS JS JD KD"));
        System.out.println(Psychic.analyze("6C 9C 8C 2D 7C 2H TC 4C 9S AH"));
        System.out.println(Psychic.analyze("3D 5S 2H QD TD 6S KH 9H AD QH"));
    }

    @Test
    public void getAllHands() {
        List<Hand> hands = Psychic.getAllHands("TH JH QC QD QS", "QH KH AH 2S 6S");
        int l = 1 << Hand.HAND_SIZE;
        assertEquals(l, hands.size());
        assertTrue(hands.contains(Hand.valueOf("TH JH QC QD QS")));
        assertTrue(hands.contains(Hand.valueOf("TH JH QC QD QS")));
        assertTrue(hands.contains(Hand.valueOf("QH KH AH 2S QS")));
        assertTrue(hands.contains(Hand.valueOf("QH KH AH 2S 6S")));
    }
}
