package codility;

import java.io.IOException;
import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: Admin
 * Date: 16.02.13
 * Time: 15:17
 * To change this template use File | Settings | File Templates.
 */
public class Week5 {
    private static final BigInteger p = new BigInteger("13407807929942597099574024998205846127479365820592393377723561443721764030073546976801874298166903427690031858186486050853753882811946569946433649006084171");
    private static final BigInteger g = new BigInteger("11717829880366207009516117596335367088558084999998952205599979459063929499736583746670572176471460312928594829675428279466566527115212748467589894601965568");
    private static final BigInteger h = new BigInteger("3239475104050450443565264378728065788649097520952449527834792452971981976143292558073856937958553180532878928001494706097394108577585732452307673444020333");

    private static final int B = 1<<20;

    public static void main(String[] args) throws IOException {
/*
        Map<BigInteger, Integer> table = new HashMap<BigInteger, Integer>();
        for(int i=0; i<B; i++) {
            if(i%1000 == 0)
                System.out.println(i);
            table.put(g.modPow(BigInteger.valueOf(i), p).modInverse(p).multiply(h).mod(p), i);
        }

        BigInteger gB = g.modPow(BigInteger.valueOf(B), p);

        for(int i=0; i<B; i++) {
            if(i%1000 == 0)
                System.out.println(i);
            BigInteger k = gB.modPow(BigInteger.valueOf(i), p);
            if(table.containsKey(k)) {
                System.out.println("=====================================================");
                System.out.println(table.get(k));
                System.out.println(i);
                return;
            }
        }
*/
        long x1 = 787046;
        long x0 = 357984;
        long x = x0*B+x1;
        System.out.println(x);
        System.out.println(g.modPow(BigInteger.valueOf(x), p).subtract(h));
    }
}
