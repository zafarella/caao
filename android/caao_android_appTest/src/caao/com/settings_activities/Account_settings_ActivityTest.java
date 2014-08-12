package caao.com.settingsactivities;

import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * The class <code>Account_settings_ActivityTest</code> contains tests for the class <code>{@link Account_settings_Activity}</code>.
 *
 * @author zafar.khaydarov
 * @version $Revision: 1.2 $
 * @generatedBy CodePro at 5/10/11 4:38 PM
 */
public class Account_settings_ActivityTest extends TestCase {
    /**
     * Perform pre-test initialization.
     *
     * @throws Exception if the initialization fails for some reason * @see TestCase#setUp()
     * @generatedBy CodePro at 5/10/11 4:38 PM
     */
    protected void setUp()
            throws Exception {
        super.setUp();
        // add additional set up code here
    }

    /**
     * Perform post-test clean-up.
     *
     * @throws Exception if the clean-up fails for some reason * @see TestCase#tearDown()
     * @generatedBy CodePro at 5/10/11 4:38 PM
     */
    protected void tearDown()
            throws Exception {
        super.tearDown();
        // Add additional tear down code here
    }

    /**
     * Launch the test.
     *
     * @param args the command line arguments
     * @generatedBy CodePro at 5/10/11 4:38 PM
     */
    public static void main(String[] args) {
        if (args.length == 0) {
            // Run all of the tests
            junit.textui.TestRunner.run(Account_settings_ActivityTest.class);
        } else {
            // Run only the named tests
            TestSuite suite = new TestSuite("Selected tests");
            for (int i = 0; i < args.length; i++) {
                TestCase test = new Account_settings_ActivityTest();
                test.setName(args[i]);
                suite.addTest(test);
            }
            junit.textui.TestRunner.run(suite);
        }
    }
}