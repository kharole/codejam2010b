package psychicpoker;

import org.apache.commons.lang.ArrayUtils;

import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * User: Admin
 * Date: 31.01.13
 * Time: 23:18
 * To change this template use File | Settings | File Templates.
 */
public class Hand {

    public static final int HAND_SIZE = 5;

    enum Category {
        STRAIGHT_FLUSH,
        FOUR_OF_A_KIND,
        FULL_HOUSE,
        FLUSH,
        STRAIGHT,
        THREE_OF_KIND,
        TWO_PAIRS,
        ONE_PAIR,
        HIGHEST_CARD
    }

    private Card[] cards;

    public static Hand build(String str) {
        List<Card> cards = new ArrayList<Card>(HAND_SIZE);
        for (String s: str.split(" ")){
            cards.add(new Card(s));
        }
        return new Hand(cards.toArray(new Card[] {}));
    }

    public Hand(Card... cards) {
        if(cards.length != HAND_SIZE)
            throw new IllegalArgumentException();

        this.cards = cards;
    }

    public boolean isFlush() {
        Set<Card.Suit> suits = new HashSet<Card.Suit>();
        for (Card card: cards) {
            suits.add(card.getSuit());
        }
        return suits.size() == 1;
    }

    public boolean isStraight() {
        return getStraightHighestCard() != null;
    }

    public Card getStraightHighestCard() {
        Card[] aceHighCards = new Card[HAND_SIZE];
        Card[] aceLowCards = new Card[HAND_SIZE];
        for (int i=0; i<HAND_SIZE; i++) {
            aceHighCards[i] = cards[i];
            aceLowCards[i] = lowAceCopy(cards[i]);
        }

        Arrays.sort(aceHighCards);
        Arrays.sort(aceLowCards);

        if(isStraightArray(aceHighCards))
            return aceHighCards[HAND_SIZE - 1];
        else if(isStraightArray(aceLowCards)) {
            return aceLowCards[HAND_SIZE - 1];
        } else {
            return null;
        }
    }

    private Card lowAceCopy(Card card) {
        if(card.getFaceValue() == Card.FaceValue.ACE) {
            return new Card(Card.FaceValue.LOW_ACE, card.getSuit());
        } else {
            return card;
        }
    }

    private boolean isStraightArray(Card[] cards) {
        for(int i=0; i<cards.length; i++) {
            if ((cards[i].getFaceValue().ordinal() - i) != cards[0].getFaceValue().ordinal())
                return false;
        }
        return true;
    }

    public int[] groupByKind() {
        Map<Card.FaceValue, Integer> faceValues = new HashMap<Card.FaceValue, Integer>();
        for (Card card: cards) {
            if(!faceValues.containsKey(card.getFaceValue())) {
                faceValues.put(card.getFaceValue(), 0);
            }
            int count = faceValues.get(card.getFaceValue());
            faceValues.put(card.getFaceValue(), count+1);
        }

        int[] result = ArrayUtils.toPrimitive(faceValues.values().toArray(new Integer[] {}));
        Arrays.sort(result);
        ArrayUtils.reverse(result);
        return result;
    }
}
