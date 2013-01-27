package settings_activities;

import org.junit.*;
import static org.junit.Assert.*;

/**
 * The class <code>Advanced_settingsFactoryTest</code> contains tests for the class <code>{@link Advanced_settingsFactory}</code>.
 *
 * @generatedBy CodePro at 5/1/11 6:24 PM
 * @author zafar.khaydarov
 * @version $Revision: 1.1 $
 */
public class Advanced_settingsFactoryTest {
	/**
	 * Run the Advanced_settings createAdvanced_settings() method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 5/1/11 6:24 PM
	 */
	@Test
	public void testCreateAdvanced_settings_1()
		throws Exception {

		Advanced_settings result = Advanced_settingsFactory.createAdvanced_settings();

		// add additional test code here
		// An unexpected exception was thrown in user code while executing this test:
		//    java.lang.RuntimeException: Stub!
		//       at android.content.Context.<init>(Context.java:4)
		//       at android.content.ContextWrapper.<init>(ContextWrapper.java:5)
		//       at android.view.ContextThemeWrapper.<init>(ContextThemeWrapper.java:5)
		//       at android.app.Activity.<init>(Activity.java:6)
		//       at android.app.ListActivity.<init>(ListActivity.java:5)
		//       at android.preference.PreferenceActivity.<init>(PreferenceActivity.java:5)
		//       at caao.com.settings_activities.Advanced_settings.<init>(Advanced_settings.java:22)
		//       at caao.com.settings_activities.Advanced_settingsFactory.createAdvanced_settings(Advanced_settingsFactory.java:29)
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
		new org.junit.runner.JUnitCore().run(Advanced_settingsFactoryTest.class);
	}
}