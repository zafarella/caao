/**
 *  Computer science department 
 * Project: Context-Aware Agriculture Organizer 
 * Author: Zafar Khaydarov
 * E-mail: zkhayda@uef.fi 
 * Web: cs.joensuu.fi/~zkhayda 
 * Date: Mar 17, 2011
 */
package caao.com;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TabActivity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.*;
import android.widget.DatePicker;
import android.widget.TabHost;
import android.widget.TabHost.OnTabChangeListener;
import caao.com.service.CaaoService;
import caao.com.tabs.CalendarActivity;
import caao.com.tabs.EventNotificationActivity;
import caao.com.tabs.PlantListActivity;
import caao.com.tabs.WikiActivity;
import com.viewpagerindicator.PageIndicator;
import com.viewpagerindicator.TitlePageIndicator;
import android.support.v4.view.ViewPager;

import java.util.Calendar;
import java.util.Locale;

/**
 * The activity which is called when the application is started. The idea behind
 * this activity is simple: first it checks - are the user is registered in the
 * system, if no, it launches the activity which handles the user registration.
 * The registration information is stored in the private storage of the
 * application (see android docs for details). In case the user is already
 * registered, activity reads from preferences the localization information to
 * switch to the desired user language. The rest procedure is creation of the
 * tabs, their content and menus. The library which is used for XML-RPC is
 * included into the application as a source code. You can find the
 * documentation and samples in the references. The library is lightweight and
 * very easy to use. This implementation is uses the only basics of it's
 * capabilities. As a more advanced features (optimization, compression), please
 * read the references. For the reference please get familiar with the following
 * info -- http://code.google.com/p/android-xmlrpc --
 *
 * @author zafar.khaydarov
 * @version $Revision: 1.22 $
 */

public class MainActivity extends TabActivity {

    /**
     * for debug purposes the constant in logs for recognizing the app in logs
     * Field TAG. (value is ""CAAO"")
     */
    @SuppressWarnings("unused")
    private static final String TAG = "CAAO";
    /** Field DATE_PICKER_DIALOG_ID. (value is 0) */
    private static final int DATE_PICKER_DIALOG_ID = 0;
    /**
     * Field TheTabs.
     *
     * @see TabHost
     */
    static TabHost TheTabs;
    /** Field activeTabIndex. */
    private short activeTabIndex = 0;
    /** Variables for the date picker dialog. */
    private int lCalendarYear, lCalendarMonth, lCalendarDay;

    /**
     * The user name in the system. Reads and stored in preferences. In server
     * side (database) it's an unique name of the user (e-mail field)
     * Initializes on create of activity.
     */
    // ------------------------------------------------------------------------------------
    /** Listener for the dialog button selector */
    private DatePickerDialog.OnDateSetListener memuDateSetListener = new DatePickerDialog.OnDateSetListener() {
        public void onDateSet(DatePicker view, int year, int monthOfYear,
                              int dayOfMonth) {
            lCalendarYear = year;
            lCalendarMonth = monthOfYear;
            lCalendarDay = dayOfMonth;
            // TODO: add the scroll method
        }
    };

    // ------------------------------------------------------------------------------------

    /**
     * Called when the activity is first created. We will create here the tabs *
     *
     * @param savedInstanceState Bundle
     *
     * @see Resources
     * @see SharedPreferences
     * @see
     */
    @SuppressWarnings("deprecation")
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // TODO: the code that should handle new user registration

           /*
           * the locate of the application
           * ----------------------------------------
           */
        SharedPreferences adv_settings = getSharedPreferences("preferences", 0);
        // reading the server url from preferences
        String which_lang = adv_settings.getString("application_language", "");

        Locale locale = new Locale(which_lang);

        Locale.setDefault(locale);
        Configuration config = new Configuration();
        config.locale = locale;
        getBaseContext().getResources().updateConfiguration(config,
                getBaseContext().getResources().getDisplayMetrics());

        // --------------------------------------------------------------------------------
        // the service which we gonna run on background
        Intent service_intent = new Intent(CaaoConstants.ACTION_FOREGROUND);
        service_intent.setClass(MainActivity.this, CaaoService.class);
        startService(service_intent);
        // --------------------------------------------------------------------------------
        // for displaying the progress of some operation (wiki page etc.)
        getWindow().requestFeature(Window.FEATURE_PROGRESS);
        getWindow().requestFeature(Window.PROGRESS_VISIBILITY_ON);



        setContentView(R.layout.main);

        ViewPager pager = (ViewPager)findViewById(R.id.pager);
        pager.setAdapter(new TestAdapter(getSupportFragmentManager()));

        //Bind the title indicator to the adapter
        TitlePageIndicator titleIndicator = (TitlePageIndicator)findViewById(R.id.pager);
        titleIndicator.setViewPager(pager);
//
//        // creating the tabs
//        Resources res_tab_titles = getResources();
//        String[] tab_titles = res_tab_titles
//                .getStringArray(R.array.tabs_titles);
//
//        TheTabs = getTabHost(); // The activity TabHost
//        TabHost.TabSpec spec; // Reusable TabSpec for each tab
//        Intent intent; // Reusable Intent for each tab
//
//        // Create an Intent to launch an Activity for the tab
//        intent = new Intent().setClass(this, CalendarActivity.class);
//        // Calendar --------------------------------------------
//        // Initialize a TabSpec for each tab and add it to the TabHost
//        spec = TheTabs.newTabSpec("CalendarActivity")
//                .setIndicator(tab_titles[0]).setContent(intent);
//        TheTabs.addTab(spec);
//
//        // Event notifications --------------------------------------------
//        intent = new Intent().setClass(this, EventNotificationActivity.class);
//        spec = TheTabs.newTabSpec("EventNotificationActivity")
//                .setIndicator(tab_titles[1]).setContent(intent);
//        TheTabs.addTab(spec);
//
//        // List of plants --------------------------------------------
//        // Create an Intent to launch an Activity for the tab
//        intent = new Intent().setClass(this, PlantListActivity.class);
//        spec = TheTabs.newTabSpec("PlantListActivity")
//                .setIndicator(tab_titles[2]).setContent(intent);
//        TheTabs.addTab(spec);
//
//        // Wiki web view --------------------------------------------
//        intent = new Intent().setClass(this, WikiActivity.class);
//        spec = TheTabs.newTabSpec("WikiActivity").setIndicator(tab_titles[3])
//                .setContent(intent);
//        TheTabs.addTab(spec);
//        // switching to the first default calendar tab
//        TheTabs.setCurrentTab(0);
//        // ---------------------------------------------------------------
//        // In order to find out the current selected tab we will listen on the
//        // tabchange event and
//        // assign the active tab index to private variable
//        TheTabs.setOnTabChangedListener(new OnTabChangeListener() {
//            @Override
//            public void onTabChanged(String arg0) {
//                MainActivity.this.activeTabIndex = (short) TheTabs
//                        .getCurrentTab();
//            }
//        });
    }

    // ------------------------------------------------------------------------------------

    /**
     * Adds menu into the main activity from xml.
     *
     * @param menu Menu
     *
     * @return boolean
     * @see onCreateOptionsMenu
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_window_menu, menu);
        return true;
    }

    // -----------------------------------------------------------------------------------

    /**
     * Overriding the method of super class as we will display the menu in
     * accordance of selected tab
     *
     * @param menu Menu
     *
     * @return boolean
     * @see <code> http://developer.android.com/reference/android/app/Activity.html</code>
     *      onPrepareOptionsMenu(android .view.Menu) Method
     *      onPrepareOptionsMenu.
     */
    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        /*
           * 0 Calendar 1 Event notification 2 Plant list 3 Wiki
           */
        switch (this.activeTabIndex) {
            case 0:
                menu.setGroupVisible(R.id.group_calendar_items, true);
                menu.setGroupVisible(R.id.group_webView, false);
                menu.setGroupVisible(R.id.group__menu_plans_events, false);
                break;
            case 3:
                // hiding the group of refresh items menu
                menu.setGroupVisible(R.id.group__menu_plans_events, false);
                menu.setGroupVisible(R.id.group_calendar_items, false);
                menu.setGroupVisible(R.id.group_webView, true);
                break;
            case 2: // TODO the item should be hidden and shown more accurate for
                // each tab
            case 1: // and setting visible in case user is on event or plant list
                // tabs
                menu.setGroupVisible(R.id.group__menu_plans_events, true);
                menu.setGroupVisible(R.id.group_webView, false);
                menu.setGroupVisible(R.id.group_calendar_items, false);
                break;
            default:
                break;
        }
        return true;
    }

    /**
     * The menu handler method. The menu items id's can reveal the meaning of
     * the items
     *
     * @param item MenuItem
     *
     * @return boolean
     * @see onOptionsItemSelected
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle menu item selections
        switch (item.getItemId()) {
            case R.id.menu_item_exit_app:
                // stopping the service (the memory)
                Intent service_intent = new Intent().setClass(MainActivity.this,
                        CaaoService.class);
                stopService(service_intent);// and finish the activity (hide, the
                // system itself will clean the
                // memory)
                CaaoService.removeNotifications();
                this.moveTaskToBack(true); // see
                // http://developer.android.com/reference/android/app/Activity.html#moveTaskToBack%28boolean%29
                // finish();
                return true;
            // --------------------------------------------------------------------------------------------------------
            case R.id.menu_item_settings:
                // Intent intent = new
                // Intent(Constants.INTENT_ACTION_VIEW_LIST);
                // startActivity(intent);
                startActivity(new Intent(this, Settings_activity.class));
                return true;
            // ---------------------------------------------------------------------------------------------------------
            case R.id.menu_item_refresh_events:
                return true;
            // ----------------------------------------------------------------------------------------------------------
            case R.id.menu_item_refresh_plant_list: // user selected the refresh
                // plant list item in menu
                refresh_plant_list();
                return true;
            // displaying the date picker dialog for picking the date
            case R.id.menu_item_goto_date:
                showDialog(DATE_PICKER_DIALOG_ID);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    /**
     * Fetches the plant list of the user from the server and publishes into the
     * plant list tab.
     *
     * @see caao.com.tabs.PlantListActivity
     */
    public void refresh_plant_list() {
        // TODO: add the intend to call the method from the plant_list activity
    }

    /**
     * Overridden method called for showing the dialogs
     *
     * @param id int the id of the dialog
     *
     * @return Dialog the dialog that will be shown.
     */
    @Override
    protected Dialog onCreateDialog(int id) {
        switch (id) { // the date picker dialog
            case DATE_PICKER_DIALOG_ID:
                // get the current date
                final Calendar c = Calendar.getInstance();
                lCalendarYear = c.get(Calendar.YEAR);
                lCalendarMonth = c.get(Calendar.MONTH);
                lCalendarDay = c.get(Calendar.DAY_OF_MONTH);
                return new DatePickerDialog(this, memuDateSetListener,
                        lCalendarYear, lCalendarMonth, lCalendarDay);
        }
        return null;
    }

    // ------------------------------------------------------------------------------------

    /**
     * Checks the connection state. Returns true if the phone is online and
     * false otherwise
     *
     * @return boolean
     * @see ConnectivityManager
     * @see getSystemService
     */
    @SuppressWarnings("unused")
    public boolean areThePhoneOnline() {
        ConnectivityManager cm =
                (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);

        return cm.getActiveNetworkInfo().isConnectedOrConnecting();
    }

    public Object getSupportFragmentManager() {
        return supportFragmentManager;
    }

    private class TestAdapter extends PagerAdapter {
        public TestAdapter(Object supportFragmentManager) {
        }

        @Override
        public int getCount() {
            return 0;  //To change body of implemented methods use File | Settings | File Templates.
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return false;  //To change body of implemented methods use File | Settings | File Templates.
        }
    }
}
/* Just for fun (C) Torvalds Linus */