package caao.com.settings_activities;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;

/**
 * The class <code>Account_settings_ActivityFactoryTest</code> contains tests for the class <code>{@link Account_settings_ActivityFactory}</code>.
 *
 * @author zafar.khaydarov
 * @version $Revision: 1.1 $
 * @generatedBy CodePro at 5/1/11 6:24 PM
 */
public class Account_settings_ActivityFactoryTest {
    /**
     * Run the Account_settings_Activity createAccount_settings_Activity() method test.
     *
     * @throws Exception
     * @generatedBy CodePro at 5/1/11 6:24 PM
     */
    @Test
    public void testCreateAccount_settings_Activity_1()
            throws Exception {

        Account_settings_Activity result = Account_settings_ActivityFactory.createAccount_settings_Activity();

        // add additional test code here
        // An unexpected exception was thrown in user code while executing this test:
        //    java.lang.RuntimeException: Stub!
        //       at android.content.Context.<init>(Context.java:4)
        //       at android.content.ContextWrapper.<init>(ContextWrapper.java:5)
        //       at android.view.ContextThemeWrapper.<init>(ContextThemeWrapper.java:5)
        //       at android.app.Activity.<init>(Activity.java:6)
        //       at caao.com.settings_activities.Account_settings_Activity.<init>(Account_settings_Activity.java:22)
        //       at caao.com.settings_activities.Account_settings_ActivityFactory.createAccount_settings_Activity(Account_settings_ActivityFactory.java:29)
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
        new org.junit.runner.JUnitCore().run(Account_settings_ActivityFactoryTest.class);
    }
}