package codility;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static java.lang.Math.abs;

/**
 * Hello world!
 */
public class App1 {

    public int numberOfTests(int l, int n, int c) {
        if (n==1)
            return l*c*c;

        int result = numberOfTests(l, n-1, c);
        System.out.println(String.format("%d, %d, %d", l, n, result));
        return result;
    }

    public int findNumberOfTests(int L, int P, int C) {
        int result = 0;
        int product = L;
        while (product < P) {
            product = product*C*C;
            result++;
        }
        return result;
    }

    public static void main(String[] args) throws IOException {

        File in = new File("F:/code_jam/2010b/in.txt");
        File out = new File("F:/code_jam/2010b/out.txt");
        out.delete();

        App1 a = new App1();
        List<String> lines = FileUtils.readLines(in);

        int testCaseNumber = Integer.valueOf(lines.get(0));
        for (int i = 1; i <= testCaseNumber; i++) {
            String[] values = lines.get(i).split(" ");
            int L = Integer.valueOf(values[0]);
            int P = Integer.valueOf(values[1]);
            int C = Integer.valueOf(values[2]);

            int c = a.findNumberOfTests(L, P, C);

            String testCaseResult = String.format("Case #%d: %d%n", i, c);
            System.out.print(testCaseResult);

            FileUtils.writeStringToFile(out, testCaseResult, true);

        }
    }
}
