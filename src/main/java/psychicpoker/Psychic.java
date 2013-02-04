package psychicpoker;

import com.google.common.collect.*;

import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * User: Admin
 * Date: 31.01.13
 * Time: 22:35
 * To change this template use File | Settings | File Templates.
 */
public class Psychic {

    public static String analyze(String str) {
        String handStr = str.substring(0, 14);
        String deckStr = str.substring(15, 29);

        Hand bestHand = Ordering.natural().max(getAllHands(handStr, deckStr));

        return String.format("Hand: %s Deck: %s Best hand: %s", handStr, deckStr, bestHand.getCategory());
    }

    public static List<Hand> getAllHands(String handStr, String deckStr) {
        List<Card> handCards = ImmutableList.copyOf(Hand.stringToCards(handStr));
        List<Card> deckCards = ImmutableList.copyOf(Hand.stringToCards(deckStr));

        List<Hand> hands = new ArrayList<Hand>();
        for(int i=0; i<=Hand.HAND_SIZE; i++) {
            for(Collection<Card> combination: combinations(handCards, Hand.HAND_SIZE - i)) {
                List<Card> cards = new ArrayList<Card>(Hand.HAND_SIZE);
                cards.addAll(combination);
                cards.addAll(deckCards.subList(0, i));
                Hand hand = new Hand(cards);
                hands.add(hand);
            }
        }

        return hands;
    }

    public static <T> Iterable<Collection<T>> combinations(final List<T> elements, final int k) {
        return new Iterable<Collection<T>>() {
            public Iterator<Collection<T>> iterator() {
                return new CombinationIterator<T>(elements, k);
            }
        };
    }
}
