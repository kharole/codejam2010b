package psychicpoker;

import org.apache.commons.lang.builder.HashCodeBuilder;

import java.util.Comparator;

/**
 * Created with IntelliJ IDEA.
 * User: Admin
 * Date: 31.01.13
 * Time: 22:37
 * To change this template use File | Settings | File Templates.
 */
public class Card implements Comparable<Card> {

    enum FaceValue {

        LOW_ACE(""),
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

        private final String str;

        FaceValue(String str) {
            this.str = str;
        }

        public static FaceValue find(String str) {
            for(FaceValue faceValue: values()) {
                if(faceValue.str.equals(str)) {
                    return faceValue;
                }
            }
            throw new IllegalArgumentException();
        }

        public String toString() {
            return str;
        }

    };

    enum Suit {

        D("DIAMONDS"), C("CLUBS"), H("HEARTS"), S("SPADES");

        private final String descr;

        Suit(String descr) {
            this.descr = descr;
        }

        public String getDescr() {
            return descr;
        }
    }

    private FaceValue faceValue;
    private Suit suit;

    public Card(FaceValue faceValue, Suit suit) {
        this.faceValue = faceValue;
        this.suit = suit;
    }

    public Card(String s) {
        if (s.length() != 2)
            throw new IllegalArgumentException();

        this.faceValue = FaceValue.find(s.substring(0,1));
        this.suit = Suit.valueOf(s.substring(1, 2));
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
        return faceValue.toString() + suit.toString();
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