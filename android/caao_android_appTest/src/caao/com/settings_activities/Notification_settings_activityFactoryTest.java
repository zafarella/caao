package caao.com.settings_activities;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;

/**
 * The class <code>Notification_settings_activityFactoryTest</code> contains tests for the class <code>{@link Notification_settings_activityFactory}</code>.
 *
 * @author zafar.khaydarov
 * @version $Revision: 1.1 $
 * @generatedBy CodePro at 5/1/11 6:24 PM
 */
public class Notification_settings_activityFactoryTest {
    /**
     * Run the Notification_settings_activity createNotification_settings_activity() method test.
     *
     * @throws Exception
     * @generatedBy CodePro at 5/1/11 6:24 PM
     */
    @Test
    public void testCreateNotification_settings_activity_1()
            throws Exception {

        Notification_settings_activity result = Notification_settings_activityFactory.createNotification_settings_activity();

        // add additional test code here
        // An unexpected exception was thrown in user code while executing this test:
        //    java.lang.RuntimeException: Stub!
        //       at android.content.Context.<init>(Context.java:4)
        //       at android.content.ContextWrapper.<init>(ContextWrapper.java:5)
        //       at android.view.ContextThemeWrapper.<init>(ContextThemeWrapper.java:5)
        //       at android.app.Activity.<init>(Activity.java:6)
        //       at android.app.ListActivity.<init>(ListActivity.java:5)
        //       at android.preference.PreferenceActivity.<init>(PreferenceActivity.java:5)
        //       at caao.com.settings_activities.Notification_settings_activity.<init>(Notification_settings_activity.java:22)
        //       at caao.com.settings_activities.Notification_settings_activityFactory.createNotification_settings_activity(Notification_settings_activityFactory.java:29)
        assertNotNull(result);
    }

    /**
     * Perform pre-test initialization.
     *
     * @throws Exception if the initialization fails for some reason
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
     * @throws Exception if the clean-up fails for some reason
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
     * @generatedBy CodePro at 5/1/11 6:24 PM
     */
    public static void main(String[] args) {
        new org.junit.runner.JUnitCore().run(Notification_settings_activityFactoryTest.class);
    }
}