package psychicpoker;

/**
 * Created with IntelliJ IDEA.
 * User: Admin
 * Date: 31.01.13
 * Time: 22:37
 * To change this template use File | Settings | File Templates.
 */
public class Card implements Comparable<Card> {

    enum FaceValue {

        _2("2", 0),
        _3("3", 1),
        _4("4", 2),
        _5("5", 3),
        _6("6", 4),
        _7("7", 5),
        _8("8", 6),
        _9("9", 7),
        _10("T", 8),
        JACK("J", 9),
        QUEEN("Q", 10),
        KING("K", 11),
        ACE("A", 12);

        private final int order;
        private final String str;

        FaceValue(String str, int order) {
            this.str = str;
            this.order = order;
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

        public int getOrder() {
            return order;
        }
    };

    enum Suit {

        C("CLUBS"), D("DIAMONDS"), H("HEARTS"), S("SPADES");

        private final String descr;

        Suit(String descr) {
            this.descr = descr;
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
        return 0;
    }
}