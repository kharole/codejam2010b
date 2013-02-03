package psychicpoker;

import org.apache.commons.lang.builder.HashCodeBuilder;

/**
 * Created with IntelliJ IDEA.
 * User: Admin
 * Date: 31.01.13
 * Time: 22:37
 * To change this template use File | Settings | File Templates.
 */
public class Card implements Comparable<Card> {

    enum FaceValue {

        TWO("2"),
        THREE("3"),
        FOUR("4"),
        FIVE("5"),
        SIX("6"),
        SEVEN("7"),
        EIGHT("8"),
        NINE("9"),
        TEN("T"),
        JACK("J"),
        QUEEN("Q"),
        KING("K"),
        ACE("A");

        private final String label;

        FaceValue(String label) {
            this.label = label;
        }

        public static FaceValue valueOfLabel(String label) {
            for(FaceValue faceValue: values()) {
                if(faceValue.label.equals(label)) {
                    return faceValue;
                }
            }
            throw new IllegalArgumentException();
        }

        public String getLabel() {
            return label;
        }
    };

    enum Suit { D, C, H, S; }

    private static Card[][] instances = new Card[FaceValue.values().length][Suit.values().length];

    static {
        for(FaceValue faceValue: FaceValue.values()) {
            for(Suit suit: Suit.values()) {
                instances[faceValue.ordinal()][suit.ordinal()] = new Card(faceValue, suit);
            }
        }
    }

    public static Card getInstance(String s) {
        if (s.length() != 2)
            throw new IllegalArgumentException();

        FaceValue faceValue = FaceValue.valueOfLabel(s.substring(0, 1));
        Suit suit = Suit.valueOf(s.substring(1, 2));

        return instances[faceValue.ordinal()][suit.ordinal()];
    }

    private FaceValue faceValue;
    private Suit suit;

    public Card(FaceValue faceValue, Suit suit) {
        this.faceValue = faceValue;
        this.suit = suit;
    }

    public FaceValue getFaceValue() {
        return faceValue;
    }

    public Suit getSuit() {
        return suit;
    }

    public int compareTo(Card card) {
        return this.getFaceValue().compareTo(card.getFaceValue());
    }

    public String toString() {
        return faceValue.getLabel() + suit.toString();
    }

    public boolean equals(Object o) {
        if(!(o instanceof Card))
            return false;
        if(o == null)
            return false;
        Card card = (Card)o;
        return this.faceValue == card.faceValue && this.suit == card.suit;

    }

    public int hashCode() {
        return new HashCodeBuilder().append(faceValue).append(suit).toHashCode();
    }
}