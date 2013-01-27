package caao.com.settings_activities;

import android.os.Bundle;
import junit.framework.*;

/**
 * The class <code>Advanced_settingsTest</code> contains tests for the class <code>{@link Advanced_settings}</code>.
 *
 * @generatedBy CodePro at 5/10/11 4:36 PM
 * @author zafar.khaydarov
 * @version $Revision: 1.2 $
 */
public class Advanced_settingsTest extends TestCase {
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
			junit.textui.TestRunner.run(Advanced_settingsTest.class);
		} else {
			// Run only the named tests
			TestSuite suite = new TestSuite("Selected tests");
			for (int i = 0; i < args.length; i++) {
				TestCase test = new Advanced_settingsTest();
				test.setName(args[i]);
				suite.addTest(test);
			}
			junit.textui.TestRunner.run(suite);
		}
	}
}