package settings_activities;

import org.junit.*;
import static org.junit.Assert.*;

/**
 * The class <code>Lang_and_loc_SettingsFactoryTest</code> contains tests for the class <code>{@link Lang_and_loc_SettingsFactory}</code>.
 *
 * @generatedBy CodePro at 5/1/11 6:24 PM
 * @author zafar.khaydarov
 * @version $Revision: 1.1 $
 */
public class Lang_and_loc_SettingsFactoryTest {
	/**
	 * Run the Lang_and_loc_Settings createLang_and_loc_Settings() method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 5/1/11 6:24 PM
	 */
	@Test
	public void testCreateLang_and_loc_Settings_1()
		throws Exception {

		Lang_and_loc_Settings result = Lang_and_loc_SettingsFactory.createLang_and_loc_Settings();

		// add additional test code here
		// An unexpected exception was thrown in user code while executing this test:
		//    java.lang.RuntimeException: Stub!
		//       at android.content.Context.<init>(Context.java:4)
		//       at android.content.ContextWrapper.<init>(ContextWrapper.java:5)
		//       at android.view.ContextThemeWrapper.<init>(ContextThemeWrapper.java:5)
		//       at android.app.Activity.<init>(Activity.java:6)
		//       at android.app.ListActivity.<init>(ListActivity.java:5)
		//       at android.preference.PreferenceActivity.<init>(PreferenceActivity.java:5)
		//       at caao.com.settings_activities.Lang_and_loc_Settings.<init>(Lang_and_loc_Settings.java:22)
		//       at caao.com.settings_activities.Lang_and_loc_SettingsFactory.createLang_and_loc_Settings(Lang_and_loc_SettingsFactory.java:29)
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
		new org.junit.runner.JUnitCore().run(Lang_and_loc_SettingsFactoryTest.class);
	}
}