package psychicpoker;

import org.junit.Test;

import java.util.*;

import static junit.framework.Assert.*;
import static junit.framework.Assert.assertEquals;

/**
 * Created with IntelliJ IDEA.
 * User: Admin
 * Date: 03.02.13
 * Time: 11:40
 * To change this template use File | Settings | File Templates.
 */
public class PsychicTest {

    @Test
    public void bestHand() {
        Psychic psychic = new Psychic();

        List<Hand> bestHands;

        //Hand: TH JH QC QD QS Deck: QH KH AH 2S 6S Best hand: straight-flush
        bestHands = psychic.bestHands("TH JH QC QD QS", "QH KH AH 2S 6S");
        assertEquals(1, bestHands.size());
        assertEquals(Hand.valueOf("TH JH QH KH AH"), bestHands.get(0));

        //Hand: 2H 2S 3H 3S 3C Deck: 2D 3D 6C 9C TH Best hand: four-of-a-kind
        bestHands = psychic.bestHands("2H 2S 3H 3S 3C", "2D 3D 6C 9C TH");
        assertEquals(1, bestHands.size());
        assertEquals(Hand.valueOf("3H 3S 3C 2D 3D"), bestHands.get(0));

        //Hand: 2H 2S 3H 3S 3C Deck: 2D 9C 3D 6C TH Best hand: full-house
        bestHands = psychic.bestHands("2H 2S 3H 3S 3C", "2D 9C 3D 6C TH");
        assertEquals(1, bestHands.size());
        assertEquals(Hand.valueOf("2H 2S 3H 3S 3C"), bestHands.get(0));

        //Hand: 2H AD 5H AC 7H Deck: AH 6H 9H 4H 3C Best hand: flush
        bestHands = psychic.bestHands("2H AD 5H AC 7H", "AH 6H 9H 4H 3C");
        assertEquals(1, bestHands.size());
        assertEquals(Hand.valueOf("AH 6H 9H 5H 7H"), bestHands.get(0));

        //Hand: AC 2D 9C 3S KD Deck: 5S 4D KS AS 4C Best hand: straight
        bestHands = psychic.bestHands("AC 2D 9C 3S KD", "5S 4D KS AS 4C");
        assertEquals(1, bestHands.size());
        assertEquals(Hand.valueOf("AC 2D 3S 5S 4D"), bestHands.get(0));

        //Hand: KS AH 2H 3C 4H Deck: KC 2C TC 2D AS Best hand: three-of-a-kind
        bestHands = psychic.bestHands("KS AH 2H 3C 4H", "KC 2C TC 2D AS");
        assertEquals(1, bestHands.size());
        assertEquals(Hand.valueOf("KC 2C TC 2D 2H"), bestHands.get(0));

        //Hand: AH 2C 9S AD 3C Deck: QH KS JS JD KD Best hand: two-pairs
        bestHands = psychic.bestHands("AH 2C 9S AD 3C", "QH KS JS JD KD");
        assertEquals(1, bestHands.size());
        assertEquals(Hand.valueOf("QH KS JS JD KD"), bestHands.get(0));

        //Hand: 6C 9C 8C 2D 7C Deck: 2H TC 4C 9S AH Best hand: one-pair
        bestHands = psychic.bestHands("6C 9C 8C 2D 7C", "2H TC 4C 9S AH");
        assertEquals(1, bestHands.size());
        assertEquals(Hand.valueOf("2H TC 4C 9S 9C"), bestHands.get(0));

        //Hand: 3D 5S 2H QD TD Deck: 6S KH 9H AD QH Best hand: highest-card
        bestHands = psychic.bestHands("3D 5S 2H QD TD", "6S KH 9H AD QH");
        assertEquals(1, bestHands.size());
        assertEquals(Hand.valueOf("6S KH 9H AD QH"), bestHands.get(0));

    }

    @Test
    public void combinationsIterator() {
        List<Collection<Integer>> combinations = new ArrayList<Collection<Integer>>();
        for(Collection<Integer> combination: Psychic.createCombinations(new Integer[] {1,2,3,4,5}, 2)) {
            combinations.add(combination);
        }

        assertEquals(10, combinations.size());

        assertTrue(combinations.contains(Arrays.asList(new Integer[] {1, 2})));
        assertTrue(combinations.contains(Arrays.asList(new Integer[] {1, 3})));
        assertTrue(combinations.contains(Arrays.asList(new Integer[] {1, 4})));
        assertTrue(combinations.contains(Arrays.asList(new Integer[] {1, 5})));
        assertTrue(combinations.contains(Arrays.asList(new Integer[] {2, 3})));
        assertTrue(combinations.contains(Arrays.asList(new Integer[] {2, 4})));
        assertTrue(combinations.contains(Arrays.asList(new Integer[] {2, 5})));
        assertTrue(combinations.contains(Arrays.asList(new Integer[] {3, 4})));
        assertTrue(combinations.contains(Arrays.asList(new Integer[] {3, 5})));
        assertTrue(combinations.contains(Arrays.asList(new Integer[] {4, 5})));
    }


}
