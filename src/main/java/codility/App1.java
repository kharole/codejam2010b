package codility;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static java.lang.Math.abs;
import static java.lang.Math.cbrt;

/**
 * Hello world!
 */
public class App1 {

    public long maxPeopleCount(long l, long n, long c) {
        if (n == 0)
            return l * c;
        if (n == 1)
            return l * c * c;

        long failedCount = maxPeopleCount(l, n - 1, c);
        return maxPeopleCount(failedCount, n - 1, c);
    }

    public long findNumberOfTests(long L, long P, long C) {
        for (int i = 0; i <= 10; i++) {
            if (maxPeopleCount(L, i, C) >= P) {
                return i;
            }
        }
        throw new IllegalStateException();
    }

    public static int equi(int[] A) {
        return 0;
    }

    public static List<Integer> equiList(int[] A) {
        int l = A.length;
        List<Integer> result = new ArrayList<Integer>(l);

        int[] sumLeftToRight = Arrays.copyOf(A, l);
        int[] sumRightToLeft = Arrays.copyOf(A, l);

        for (int i = 1; i < l; i++) {
            sumLeftToRight[i] = sumLeftToRight[i - 1] + A[i];
            sumRightToLeft[l - 1 - i] = sumRightToLeft[l - i] + A[l - 1 - i];
        }

        for (int i = 0; i < l; i++) {
            if (sumLeftToRight[i] == sumRightToLeft[i])
                result.add(i);
        }

        return result;
    }

    public static int q_round_integers ( int Q ) {
        int result = 0;
        for(int i=0; i*i<Q; i++) {
            for(int j=i; j*j<Q; j++) {
                if(i*i+j*j == Q) {
                    int c= 1;
                    if(i!=0) c=c*2;//perm
                    if(j!=0) c=c*2;//i-sign
                    if(i!=j) c=c*2;//j-sign
                    result += c;
                }
            }
        }
        return result;
    }


    public static int q_round_integers2 ( int Q ) {
        int i;
        for(i=0; i*i<Q; i++) {}
        return i;
    }


    public int sqrt(int Q) {
        int i = 0;
        for(i=0; i*i<Q; i++) {}
        return i;
    }


//    System.out.println(i + "," + j);

    public static int arrayJmp ( int[] A ) {
        int result = 0;
        boolean[] visitedNodes = new boolean[A.length];
        int i = 0;

        while(i >= 0 && i < A.length) {
            System.out.println(i + ", A[i]=" + A[i]);
            if(visitedNodes[i])
                return -1;
            visitedNodes[i] = true;
            i = i + A[i];
            result++;
        }

        return result;
    }

    public static void main(String[] args) throws IOException {
        /*
        File in = new File("F:/code_jam/2010b/in.txt");
        File out = new File("F:/code_jam/2010b/out.txt");
        out.delete();

        App1 a = new App1();
        List<String> lines = FileUtils.readLines(in);

        int testCaseNumber = Integer.valueOf(lines.get(0));
        for (int i = 1; i <= testCaseNumber; i++) {
            String[] values = lines.get(i).split(" ");
            long L = Long.valueOf(values[0]);
            long P = Long.valueOf(values[1]);
            long C = Long.valueOf(values[2]);

            long c = a.findNumberOfTests(L, P, C);

            String testCaseResult = String.format("Case #%d: %d%n", i, c);
            System.out.print(testCaseResult);

            FileUtils.writeStringToFile(out, testCaseResult, true);

        }*/

        //System.out.print(equiList(new int[]{-7, 1, 5, 2, -4, 3, 0}));

        //System.out.println(sqrt(9));


        //System.out.println(q_round_integers (372506));

        //System.out.println(arrayJmp(new int[] {2, 3, 1, 1, 3}));
        //System.out.println(arrayJmp(new int[] {1, 1, -1, 1}));

    }
}
