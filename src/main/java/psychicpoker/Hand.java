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
            cards.add(Card.getInstance(s));
        }
        return new Hand(cards.toArray(new Card[] {}));
    }

    private Hand(Card... cards) {
        if(cards.length != HAND_SIZE)
            throw new IllegalArgumentException();
        this.cards = Arrays.copyOf(cards, HAND_SIZE);
        Arrays.sort(this.cards);
    }

    public boolean isFlush() {
        Set<Card.Suit> suits = new HashSet<Card.Suit>();
        for (Card card: cards) {
            suits.add(card.getSuit());
        }
        return suits.size() == 1;
    }

    public boolean isStraight() {
        return isAceHighStraight() || isAceLowStraight();
    }

    private boolean isAceHighStraight() {
        for(int i=0; i<cards.length; i++) {
            if ((cards[i].getFaceValue().ordinal() - i) != cards[0].getFaceValue().ordinal())
                return false;
        }
        return true;
    }

    private boolean isAceLowStraight() {
        return cards[4].getFaceValue() == Card.FaceValue.ACE &&
                cards[0].getFaceValue() == Card.FaceValue.TWO &&
                cards[1].getFaceValue() == Card.FaceValue.THREE &&
                cards[2].getFaceValue() == Card.FaceValue.FOUR &&
                cards[3].getFaceValue() == Card.FaceValue.FIVE;

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

    Category getCategory() {
        if(isStraight() && isFlush()) {
            return Category.STRAIGHT_FLUSH;
        } else if (groupByKind()[0] == 4) {
            return Category.FOUR_OF_A_KIND;
        } else if (groupByKind()[0] == 3 && groupByKind()[1] == 2) {
            return Category.FULL_HOUSE;
        } else if(isFlush()) {
            return Category.FLUSH;
        } else if(isStraight()) {
            return Category.STRAIGHT;
        } else if(groupByKind()[0] == 3) {
            return Category.THREE_OF_KIND;
        } else if(groupByKind()[0] == 2 && groupByKind()[1] == 2) {
            return Category.TWO_PAIRS;
        } else if(groupByKind()[0] == 2) {
            return Category.ONE_PAIR;
        } else {
            return Category.HIGHEST_CARD;
        }
    }

    public Card[] getCards() {
        return Arrays.copyOf(cards, HAND_SIZE);
    }
}
