package caao.com.settings_activities;

import junit.framework.Test;
import junit.framework.TestSuite;

/**
 * The class <code>TestAll</code> builds a suite that can be used to run all
 * of the tests within its package as well as within any subpackages of its
 * package.
 *
 * @generatedBy CodePro at 5/10/11 4:38 PM
 * @author zafar.khaydarov
 * @version $Revision: 1.3 $
 */
public class TestAll {

    /**
     * Launch the test.
     *
     * @param args the command line arguments
     *
     * @generatedBy CodePro at 5/10/11 4:38 PM
     */
    public static void main(String[] args) {
    	junit.textui.TestRunner.run(suite());
    }

    /**
     * Create a test suite that can run all of the test cases in this package
     * and all subpackages.
     *
    
     *
     * @generatedBy CodePro at 5/10/11 4:38 PM
     * @return the test suite that was created */
    public static Test suite() {
    	TestSuite suite;
    
    	suite = new TestSuite("Tests in package caao.com.settings_activities");
    	suite.addTestSuite(Lang_and_loc_SettingsTest.class);
    	suite.addTestSuite(Account_settings_ActivityTest.class);
    	suite.addTestSuite(Advanced_settingsTest.class);
    	suite.addTestSuite(Notification_settings_activityTest.class);
    	return suite;
    }
}
