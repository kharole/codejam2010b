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
        int[] faceValueOrders = new int[HAND_SIZE];
        int i = 0;
        for (Card card: cards) {
            faceValueOrders[i++] = card.getFaceValue().getOrder();
        }

        Arrays.sort(faceValueOrders);

        boolean result = true;
        for(i=0; i<faceValueOrders.length; i++) {
            result = result && ((faceValueOrders[i] - i) == faceValueOrders[0]);
        }

        return result;
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
