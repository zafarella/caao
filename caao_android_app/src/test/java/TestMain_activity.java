/**
 * Author: zafar.khaydarov
 * Date: 2011 May 10, 2011 3:46:05 PM
 * Project: CAAOTest
 *
 */

import java.math.BigInteger;
import java.util.Random;

import com.jayway.android.robotium.solo.Solo;
import android.app.Dialog;
import android.os.Bundle;
import android.test.*;
import android.view.Menu;
import android.view.MenuItem;
import junit.framework.*;

/**
 * @author zafar.khaydarov
 * 
 * @version $Revision: 1.4 $
 */
public class TestMain_activity extends
	ActivityInstrumentationTestCase2<Main_activity> {
    /**
     * Field solo.
     */
    private Solo solo;

    /**
     * Constructor for TestMain_activity.
     */
    public TestMain_activity() {
	super("caao.com", Main_activity.class);
    }

    /**
     * Run the Main_activity() constructor test.
     * 
     * @generatedBy CodePro at 5/10/11 4:34 PM
     * @throws Exception
     */
    public void testMain_activity_1() throws Exception {
	Main_activity result = new Main_activity();
	assertNotNull(result);
	// add additional test code here
    }

    /**
     * Method setUp.
     * 
     * @throws Exception
     */
    @Override
    public void setUp() throws Exception {
	super.setUp();
	solo = new Solo(getInstrumentation(), getActivity());
    }

    /**
     * Method testMainMenu.
     * 
     * @throws Exception
     */
    @SuppressWarnings("static-access")
    public void testMainMenu() throws Exception {
	solo.sendKey(solo.MENU);
	solo.clickOnText("Settings");
	solo.clickOnText("Account Settings");

	solo.goBack();
	solo.clickOnText("Location and language");
	solo.goBack();
	solo.clickOnText("Notifications");
	solo.goBack();
	solo.clickOnText("Advanced settings");
	solo.goBack();
	solo.clickOnText("Share feedback");
	solo.enterText(0, this.randomText());
	solo.goBack();
	solo.clickOnText("Version information");
	solo.goBack();
	solo.clickOnText("License");
	solo.goBack();
	solo.clickOnText("Third party licenses");
	solo.goBack();
	solo.goBack();
	// Account Settings
	// Location and language
	// Notifications
	// Advanced settings
	// Version information
	// Share feedback
	// License
	// Third party licenses

	// Calendar
	// Event notification
	// Plant list
	// Wiki
	solo.clickOnText("Calendar");

	testCalendarControls();
	solo.clickOnText("Event notification");
	solo.clickOnText("Plant list");
	solo.clickOnText("Wiki");
	solo.sendKey(solo.MENU);
	solo.clickOnText("Go to URL");
	solo.sendKey(solo.MENU);
	solo.clickOnText("Exit");
    }

    /**
     * 
     */
    private void testCalendarControls() {
	int i = 11;
	while (i-- != 0) {
	    solo.clickOnButton("Prev.");
	}
	while (i++ != 11) {
	    solo.clickOnButton("Next");
	}
	solo.clickOnButton("Today");
    }

    /**
     * Method tearDown.
     * 
     * @throws Exception
     */
    @Override
    public void tearDown() throws Exception {

	try {
	    solo.finalize();
	} catch (Throwable e) {

	    e.printStackTrace();
	}
	getActivity().finish();
	super.tearDown();
    }

    /**
     * Method randomText.
     * 
     * @return String
     */
    public String randomText() {
	return new BigInteger(130, new Random()).toString(32);
    }

}
