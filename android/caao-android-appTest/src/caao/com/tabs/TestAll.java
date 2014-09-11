package caao.com.tabs;

import junit.framework.Test;
import junit.framework.TestSuite;

/**
 * The class <code>TestAll</code> builds a suite that can be used to run all of
 * the tests within its package as well as within any subpackages of its
 * package.
 *
 * @author zafar.khaydarov
 * @version $Revision: 1.3 $
 * @generatedBy CodePro at 5/10/11 4:38 PM
 */
public class TestAll {

    /**
     * Launch the test.
     *
     * @param args the command line arguments
     * @generatedBy CodePro at 5/10/11 4:38 PM
     */
    public static void main(String[] args) {
        junit.textui.TestRunner.run(suite());
    }

    /**
     * Create a test suite that can run all of the test cases in this package
     * and all subpackages.
     *
     * @return the test suite that was created
     * @generatedBy CodePro at 5/10/11 4:38 PM
     */
    public static Test suite() {
        TestSuite suite;

        suite = new TestSuite("Tests in package caao.com.tabs");
        suite.addTestSuite(Event_Notification_ActivityTest.class);
        suite.addTestSuite(Plant_list_ActivityTest.class);
        suite.addTestSuite(Wiki_ActivityTest.class);
        suite.addTestSuite(Calendar_ActivityTest.class);
        return suite;
    }
}
