package codility;

import org.junit.Test;


import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertTrue;

/**
 * Created with IntelliJ IDEA.
 * User: Admin
 * Date: 16.01.13
 * Time: 23:48
 * To change this template use File | Settings | File Templates.
 */
public class App1Test {

    @Test
    public void testNumberOfTests() throws Exception {
        App1 a = new App1();
        System.out.println(a.maxPeopleCount(1, 3, 2));
        System.out.println(a.maxPeopleCount(1, 4, 2));
        System.out.println(a.maxPeopleCount(50, 1, 2));
        System.out.println(a.maxPeopleCount(50, 2, 2));
    }
}
