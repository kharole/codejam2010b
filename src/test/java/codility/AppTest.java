package codility;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import java.util.List;

/**
 * Unit test for simple App.
 */
public class AppTest 
    extends TestCase
{
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public AppTest( String testName )
    {
        super( testName );
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite( AppTest.class );
    }

    /**
     * Rigourous Test :-)
     */
    public void testApp()
    {
        App a = new App();

        //a.countTriplets(29);
        //assertEquals(1, a.countTriplets(1));

        a.countTriplets(1);
        System.out.println("--------");
        a.countTriplets(2);
        System.out.println("--------");
        a.countTriplets(3);

        System.out.println("--------");

        a.countTriplets(27);
        System.out.println("--------");
        a.countTriplets(28);
        System.out.println("--------");
        a.countTriplets(29);


        a.countTriplets(21);
    }

    public void test20()
    {
        App a = new App();

        List<App.Triplet> triplets = a.countTriplets(20);
        assertEquals(2, triplets.size());
        assertEquals("(6,6,8)", triplets.get(0).toString());
        assertEquals("(6,7,7)", triplets.get(1).toString());
    }


}
