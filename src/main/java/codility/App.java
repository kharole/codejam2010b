package codility;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Formatter;
import java.util.List;

import static java.lang.Math.abs;

/**
 * Hello world!
 */
public class App {

    public static class Triplet {

        public int v1;
        public int v2;
        public int v3;

        public Triplet(int v1, int v2, int v3) {
            this.v1 = v1;
            this.v2 = v2;
            this.v3 = v3;
        }

        public int getTotal() {
            return v1 + v2 + v3;
        }

        public boolean isValid() {
            int d1 = abs(v2 - v3);
            int d2 = abs(v1 - v3);
            int d3 = abs(v2 - v1);
            return d1 <= 2 & d2 <= 2 & d3 <= 2;
        }

        public boolean isSurprising() {
            int d1 = abs(v2 - v3);
            int d2 = abs(v1 - v3);
            int d3 = abs(v2 - v1);
            return d1 == 2 || d2 == 2 || d3 == 2;
        }

        public int getMax() {
            int[] a = new int[]{v1, v2, v3};
            Arrays.sort(a);
            return a[2];
        }

        public String toString() {
            return String.format("(%d,%d,%d)", v1, v2, v3);
        }
    }

    public List<Triplet> countTriplets(int total) {
        List<Triplet> result = new ArrayList<Triplet>(total);
        for (int i = 0; i <= 10; i++) {
            for (int j = i; j <= 10; j++) {
                for (int k = j; k <= 10; k++) {
                    Triplet t = new Triplet(i, j, k);
                    if ((t.getTotal() == total) && t.isValid()) {
                        result.add(t);
                        System.out.println(t);
                    }
                }
            }
        }
        return result;
    }

    private int countGoodGooglers(int N, int S, int p, int[] totals) {
        int result = 0;
        for (int i = 0; i < N; i++) {
            List<Triplet> triplets = countTriplets(totals[i]);
            boolean unSurprisingMatch = false;
            for (Triplet triplet : triplets) {
                if (triplet.getMax() >= p && !triplet.isSurprising()) {
                    unSurprisingMatch = true;
                    break;
                }
            }
            boolean surprisingMatch = false;
            for (Triplet triplet : triplets) {
                if (triplet.getMax() >= p && triplet.isSurprising()) {
                    surprisingMatch = true;
                    break;
                }
            }

            if(unSurprisingMatch) {
                result++;
            } else if (surprisingMatch && S>0) {
                S--;
                result++;
            }

        }
        return result;
    }

    public static void main(String[] args) throws IOException {

        File in = new File("F:/code_jam/q2012b/sample.txt");
        File out = new File("F:/code_jam/q2012b/out.txt");
        out.delete();

        App a = new App();
        List<String> lines = FileUtils.readLines(in);

        int testCaseNumber = Integer.valueOf(lines.get(0));
        for (int i = 1; i <= testCaseNumber; i++) {
            String[] values = lines.get(i).split(" ");
            int N = Integer.valueOf(values[0]);
            int S = Integer.valueOf(values[1]);
            int p = Integer.valueOf(values[2]);

            int[] t = new int[N];
            for (int j = 0; j < N; j++) {
                t[j] = Integer.valueOf(values[j + 3]);
            }

            System.out.println(N);
            System.out.println(S);
            System.out.println(p);
            System.out.println(Arrays.toString(t));

            int c = a.countGoodGooglers(N, S, p, t);

            String testCaseResult = String.format("Case #%d: %d%n", i, c);
            System.out.print(testCaseResult);

            FileUtils.writeStringToFile(out, testCaseResult, true);

        }
    }
}
