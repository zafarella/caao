package caao.com.service;

import android.app.NotificationManager;
import android.content.Intent;
import android.os.IBinder;
import junit.framework.*;

/**
 * The class <code>caao_serviceTest</code> contains tests for the class <code>{@link caao_service}</code>.
 *
 * @generatedBy CodePro at 5/10/11 4:34 PM
 * @author zafar.khaydarov
 * @version $Revision: 1.2 $
 */
public class caao_serviceTest extends TestCase {
	/**
	 * Run the caao_service() constructor test.
	 *
	 * @generatedBy CodePro at 5/10/11 4:34 PM
	 * @throws Exception
	 */
	public void testCaao_service_1()
		throws Exception {
		caao_service result = new caao_service();
		assertNotNull(result);
		// add additional test code here
	}

	/**
	 * Run the void removeNotifications() method test.
	 *
	
	 *
	 * @generatedBy CodePro at 5/10/11 4:34 PM
	 * @throws Exception */
	public void testRemoveNotifications_1()
		throws Exception {
		caao_service.mNM = null;

		caao_service.removeNotifications();

		// add additional test code here
	}

	/**
	 * Perform pre-test initialization.
	 *
	
	 *
	
	 *
	 * @generatedBy CodePro at 5/10/11 4:34 PM
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
	 * @generatedBy CodePro at 5/10/11 4:34 PM
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
	 * @generatedBy CodePro at 5/10/11 4:34 PM
	 */
	public static void main(String[] args) {
		if (args.length == 0) {
			// Run all of the tests
			junit.textui.TestRunner.run(caao_serviceTest.class);
		} else {
			// Run only the named tests
			TestSuite suite = new TestSuite("Selected tests");
			for (int i = 0; i < args.length; i++) {
				TestCase test = new caao_serviceTest();
				test.setName(args[i]);
				suite.addTest(test);
			}
			junit.textui.TestRunner.run(suite);
		}
	}
}