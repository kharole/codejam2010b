package codility;

import java.io.IOException;
import java.math.BigInteger;
import java.util.Arrays;

/**
 * Created with IntelliJ IDEA.
 * User: Admin
 * Date: 03.03.13
 * Time: 15:44
 * To change this template use File | Settings | File Templates.
 */
public class Week6 {

    private static final BigInteger N1 = new BigInteger("179769313486231590772930519078902473361797697894230657273430081157732675805505620686985379449212982959585501387537164015710139858647833778606925583497541085196591615128057575940752635007475935288710823649949940771895617054361149474865046711015101563940680527540071584560878577663743040086340742855278549092581");
    private static final BigInteger с = new BigInteger("22096451867410381776306561134883418017410069787892831071731839143676135600120538004282329650473509424343946219751512256465839967942889460764542040581564748988013734864120452325229320176487916666402997509188729971690526083222067771600019329260870009579993724077458967773697817571267229951148662959627934791540");

    private static final BigInteger N2 = new BigInteger("648455842808071669662824265346772278726343720706976263060439070378797308618081116462714015276061417569195587321840254520655424906719892428844841839353281972988531310511738648965962582821502504990264452100885281673303711142296421027840289307657458645233683357077834689715838646088239640236866252211790085787877");
    private static final BigInteger N3 = new BigInteger("720062263747350425279564435525583738338084451473999841826653057981916355690188337790423408664187663938485175264994017897083524079135686877441155132015188279331812309091996246361896836573643119174094961348524639707885238799396839230364676670221627018353299443241192173812729276147530748597302192751375739387929");


    public static BigInteger bigIntSqRootCeil(BigInteger x)
            throws IllegalArgumentException {
        if (x.compareTo(BigInteger.ZERO) < 0) {
            throw new IllegalArgumentException("Negative argument.");
        }
        // square roots of 0 and 1 are trivial and
        // y == 0 will cause a divide-by-zero exception
        if (x == BigInteger.ZERO || x == BigInteger.ONE) {
            return x;
        } // end if
        BigInteger two = BigInteger.valueOf(2L);
        BigInteger y;
        // starting with y = x / 2 avoids magnitude issues with x squared
        for (y = x.divide(two);
             y.compareTo(x.divide(y)) > 0;
             y = ((x.divide(y)).add(y)).divide(two));
        if (x.compareTo(y.multiply(y)) == 0) {
            return y;
        } else {
            return y.add(BigInteger.ONE);
        }
    } // end bigIntSqRootCeil

    public String toHex(String arg) {
        return String.format("%040x", new BigInteger(arg.getBytes(/*YOUR_CHARSET?*/)));
    }

    public static String pcks15ToStr(BigInteger m) {
        byte[] a = m.toByteArray();
        for (int j=0; j<a.length; j++) {
            System.out.println(String.format("%02X ", a[j]));
        }

        if(a[0] != 0x02)
            throw new IllegalArgumentException();
        int i;
        for(i=0; i<a.length; i++) {
            if(a[i] == 0)
                break;
        }

        byte[] result = new byte[a.length-i-1];
        System.arraycopy(a, i+1, result, 0, result.length);
        return new String(result);
    }

    private static void challenge12() {
        BigInteger A = bigIntSqRootCeil(N1);
        BigInteger x = bigIntSqRootCeil(A.multiply(A).subtract(N1));
        BigInteger p = A.subtract(x);
        BigInteger q = A.add(x);
        System.out.println("Challenge #1:" + p);
        System.out.println(p.multiply(q));

        BigInteger phi = p.subtract(BigInteger.ONE).multiply(q.subtract(BigInteger.ONE));

        BigInteger e = new BigInteger("65537");

        BigInteger d = e.modInverse(phi);
        BigInteger m = с.modPow(d, N1);
        System.out.println(m);

        System.out.println(pcks15ToStr(m));
    }

    private static void challenge2() {
        BigInteger A = bigIntSqRootCeil(N2);
        BigInteger p = null;
        BigInteger q = null;

        for(int i=0; i<1<<20; i++) {
            BigInteger x = bigIntSqRootCeil(A.multiply(A).subtract(N2));
            p = A.subtract(x);
            q = A.add(x);
            if(p.multiply(q).compareTo(N2) == 0)
                break;
            A = A.add(BigInteger.ONE);
        }

        System.out.println("Challenge #2:" + p);
    }

    private static void challenge3_() {
        BigInteger A = bigIntSqRootCeil(N3.multiply(new BigInteger("6")));
        System.out.println(A);

        for(int i=-5000; i<5000; i++) {
            BigInteger t = A.multiply(A).multiply(new BigInteger("4")).subtract(N3.multiply(new BigInteger("24")));
            System.out.println(t);
            BigInteger x = bigIntSqRootCeil(t);
            System.out.println(x.multiply(x).compareTo(t));
        }


    }

    private static void challenge3() {
        BigInteger TWO_A = bigIntSqRootCeil(N3.multiply(new BigInteger("24")));
        BigInteger t = TWO_A.multiply(TWO_A).subtract(N3.multiply(new BigInteger("24")));
        BigInteger x = bigIntSqRootCeil(t);
        System.out.println(x.multiply(x).compareTo(t));

        BigInteger p = TWO_A.subtract(x).divide(new BigInteger("6"));
        BigInteger q = TWO_A.add(x).divide(new BigInteger("4"));
        System.out.println(p.multiply(q));
        System.out.println("Challenge #3:" + p);
        System.out.println("Challenge #3:" + q);
    }

    public static void main(String[] args) throws IOException {
        //challenge12();
        //challenge2();
        challenge3();
    }

}
