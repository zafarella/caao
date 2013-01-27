import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * The class <code>Settings_activityTest</code> contains tests for the class <code>{@link Settings_activity}</code>.
 *
 * @generatedBy CodePro at 5/10/11 4:36 PM
 * @author zafar.khaydarov
 * @version $Revision: 1.2 $
 */
public class Settings_activityTest extends TestCase {
	/**
	 * Run the Settings_activity() constructor test.
	 *
	 * @generatedBy CodePro at 5/10/11 4:36 PM
	 * @throws Exception
	 */
	public void testSettings_activity_1()
		throws Exception {
		Settings_activity result = new Settings_activity();
		assertNotNull(result);
		// add additional test code here
	}

	/**
	 * Perform pre-test initialization.
	 *
	
	 *
	
	 *
	 * @generatedBy CodePro at 5/10/11 4:36 PM
	 * @throws Exception
	 *         if the initialization fails for some reason * @see TestCase#setUp() */
	protected void setUp()
		throws Exception {
		super.setUp();
		// add additional set up code here
	}

	/**
	 * Perform post-test clean-up.
	 *
	
	 *
	
	 *
	 * @generatedBy CodePro at 5/10/11 4:36 PM
	 * @throws Exception
	 *         if the clean-up fails for some reason * @see TestCase#tearDown() */
	protected void tearDown()
		throws Exception {
		super.tearDown();
		// Add additional tear down code here
	}

	/**
	 * Launch the test.
	 *
	 * @param args the command line arguments
	 *
	 * @generatedBy CodePro at 5/10/11 4:36 PM
	 */
	public static void main(String[] args) {
		if (args.length == 0) {
			// Run all of the tests
			junit.textui.TestRunner.run(Settings_activityTest.class);
		} else {
			// Run only the named tests
			TestSuite suite = new TestSuite("Selected tests");
			for (int i = 0; i < args.length; i++) {
				TestCase test = new Settings_activityTest();
				test.setName(args[i]);
				suite.addTest(test);
			}
			junit.textui.TestRunner.run(suite);
		}
	}
}