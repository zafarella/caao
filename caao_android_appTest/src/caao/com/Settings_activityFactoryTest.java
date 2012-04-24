package caao.com;

import org.junit.*;
import static org.junit.Assert.*;

/**
 * The class <code>Settings_activityFactoryTest</code> contains tests for the class <code>{@link Settings_activityFactory}</code>.
 *
 * @generatedBy CodePro at 5/1/11 6:24 PM
 * @author zafar.khaydarov
 * @version $Revision: 1.1 $
 */
public class Settings_activityFactoryTest {
	/**
	 * Run the Settings_activity createSettings_activity() method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 5/1/11 6:24 PM
	 */
	@Test
	public void testCreateSettings_activity_1()
		throws Exception {

		Settings_activity result = Settings_activityFactory.createSettings_activity();

		// add additional test code here
		// An unexpected exception was thrown in user code while executing this test:
		//    java.lang.RuntimeException: Stub!
		//       at android.content.Context.<init>(Context.java:4)
		//       at android.content.ContextWrapper.<init>(ContextWrapper.java:5)
		//       at android.view.ContextThemeWrapper.<init>(ContextThemeWrapper.java:5)
		//       at android.app.Activity.<init>(Activity.java:6)
		//       at android.app.ListActivity.<init>(ListActivity.java:5)
		//       at caao.com.Settings_activity.<init>(Settings_activity.java:33)
		//       at caao.com.Settings_activityFactory.createSettings_activity(Settings_activityFactory.java:29)
		assertNotNull(result);
	}

	/**
	 * Perform pre-test initialization.
	 *
	 * @throws Exception
	 *         if the initialization fails for some reason
	 *
	 * @generatedBy CodePro at 5/1/11 6:24 PM
	 */
	@Before
	public void setUp()
		throws Exception {
		// add additional set up code here
	}

	/**
	 * Perform post-test clean-up.
	 *
	 * @throws Exception
	 *         if the clean-up fails for some reason
	 *
	 * @generatedBy CodePro at 5/1/11 6:24 PM
	 */
	@After
	public void tearDown()
		throws Exception {
		// Add additional tear down code here
	}

	/**
	 * Launch the test.
	 *
	 * @param args the command line arguments
	 *
	 * @generatedBy CodePro at 5/1/11 6:24 PM
	 */
	public static void main(String[] args) {
		new org.junit.runner.JUnitCore().run(Settings_activityFactoryTest.class);
	}
}