package psychicpoker;

import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * User: Admin
 * Date: 31.01.13
 * Time: 22:35
 * To change this template use File | Settings | File Templates.
 */
public class Psychic {

    public List<Hand> bestHands(String handStr, String deckStr) {
        Card[] handCards = Hand.stringToCards(handStr);
        Card[] deckCards = Hand.stringToCards(deckStr);

        List<Hand> hands = new ArrayList<Hand>();
        for(int i=0; i<Hand.HAND_SIZE; i++) {
            for(Collection<Card> combination: createCombinations(handCards, Hand.HAND_SIZE - i)) {
                List<Card> cards = new ArrayList<Card>(Hand.HAND_SIZE);
                cards.addAll(combination);
                cards.addAll(Arrays.asList(Arrays.copyOf(deckCards, i)));
                Hand hand = new Hand(cards);
                hands.add(hand);
            }
        }

        return hands;
    }

    public static <T> Iterable<Collection<T>> createCombinations(final T[] elements, final int k) {
        return new Iterable<Collection<T>>() {
            public Iterator<Collection<T>> iterator() {
                return new CombinationIterator<T>(elements, k);
            }
        };
    }
}
