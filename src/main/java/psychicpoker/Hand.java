package psychicpoker;

import com.google.common.base.Joiner;
import com.google.common.base.Objects;
import com.google.common.base.Splitter;
import org.apache.commons.lang.ArrayUtils;

import java.util.*;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkState;

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

    public static Card[] stringToCards(String str) {
        List<Card> cards = new ArrayList<Card>(HAND_SIZE);
        for (String s: Splitter.on(' ').split(str)){
            cards.add(Card.valueOf(s));
        }
        return cards.toArray(new Card[] {});
    }

    public static Hand valueOf(String str) {
        return new Hand(stringToCards(str));
    }

    public Hand(List<Card> cards) {
        this(cards.toArray(new Card[] {}));
    }

    public Hand(Card... cards) {
        checkArgument(cards.length == HAND_SIZE, "Invalid cards number");
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
        } else {
            int[] kindGroups = groupByKind();
            checkState(kindGroups.length >= 2, "There must be least two groups");
            if (kindGroups[0] == 4) {
                return Category.FOUR_OF_A_KIND;
            } else if (kindGroups[0] == 3 && kindGroups[1] == 2) {
                return Category.FULL_HOUSE;
            } else if(isFlush()) {
                return Category.FLUSH;
            } else if(isStraight()) {
                return Category.STRAIGHT;
            } else if(kindGroups[0] == 3) {
                return Category.THREE_OF_KIND;
            } else if(kindGroups[0] == 2 && kindGroups[1] == 2) {
                return Category.TWO_PAIRS;
            } else if(kindGroups[0] == 2) {
                return Category.ONE_PAIR;
            } else {
                return Category.HIGHEST_CARD;
            }
        }
    }

    public Card[] getCards() {
        return Arrays.copyOf(cards, HAND_SIZE);
    }

    public boolean equals(Object o) {
        if(!(o instanceof Card))
            return false;
        Hand hand = (Hand)o;
        return Arrays.equals(this.cards, hand.cards);
    }

    public int hashCode() {
        return Arrays.hashCode(this.cards);
    }

    public String toString() {
        return Joiner.on(' ').join(cards);
    }
}
