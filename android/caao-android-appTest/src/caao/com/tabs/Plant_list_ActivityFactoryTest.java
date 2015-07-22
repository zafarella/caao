package caao.com.tabs;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;

/**
 * The class <code>Plant_list_ActivityFactoryTest</code> contains tests for the class <code>{@link Plant_list_ActivityFactory}</code>.
 *
 * @author zafar.khaydarov
 * @version $Revision: 1.1 $
 * @generatedBy CodePro at 5/1/11 6:24 PM
 */
public class Plant_list_ActivityFactoryTest {
    /**
     * Run the Plant_list_Activity createPlant_list_Activity() method test.
     *
     * @throws Exception
     * @generatedBy CodePro at 5/1/11 6:24 PM
     */
    @Test
    public void testCreatePlant_list_Activity_1()
            throws Exception {

        Plant_list_Activity result = Plant_list_ActivityFactory.createPlant_list_Activity();

        // add additional test code here
        // An unexpected exception was thrown in user code while executing this test:
        //    java.lang.RuntimeException: Stub!
        //       at android.content.Context.<init>(Context.java:4)
        //       at android.content.ContextWrapper.<init>(ContextWrapper.java:5)
        //       at android.view.ContextThemeWrapper.<init>(ContextThemeWrapper.java:5)
        //       at android.app.Activity.<init>(Activity.java:6)
        //       at caao.com.tabs.Plant_list_Activity.<init>(Plant_list_Activity.java:43)
        //       at caao.com.tabs.Plant_list_ActivityFactory.createPlant_list_Activity(Plant_list_ActivityFactory.java:29)
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
        new org.junit.runner.JUnitCore().run(Plant_list_ActivityFactoryTest.class);
    }
}