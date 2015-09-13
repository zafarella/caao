/**
 * Project: Context-Aware Agriculture Organizer
 * Author: Zafar Khaydarov
 */
package caao.com;

import android.app.ActionBar;
import android.app.DatePickerDialog;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.Window;
import android.widget.DatePicker;

import java.util.Locale;

import caao.com.service.Service;

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

public class MainActivity extends FragmentActivity {

    /**
     * for debug purposes the constant in logs for recognizing the app in logs
     * Field TAG. (value is ""CAAO"")
     */
    @SuppressWarnings("unused")
    private static final String TAG = "CAAO";
    private static final int DATE_PICKER_DIALOG_ID = 0;
	ViewPager mViewPager;

    /**
     * Called when the activity is first created. We will create here the tabs *
     *
     * @param savedInstanceState Bundle
     * @see Resources
     * @see SharedPreferences
     * @see
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: the code that should handle new user registration
	    setContentView(R.layout.main);

	    final ActionBar actionBar = getActionBar();
	    // Specify that tabs should be displayed in the action bar.
	    actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);


	    SharedPreferences advSettings = getSharedPreferences("preferences", 0);
	    // reading the server url from preferences
	    String lang = advSettings.getString("application_language", "");

	    Locale locale = new Locale(lang);

        Locale.setDefault(locale);
        Configuration config = new Configuration();
        config.locale = locale;
        getBaseContext().getResources().updateConfiguration(config,
                getBaseContext().getResources().getDisplayMetrics());

        // --------------------------------------------------------------------------------
        // the service which we gonna run on background
	    Intent serviceIntent = new Intent(Constants.ACTION_FOREGROUND);
	    serviceIntent.setClass(MainActivity.this, Service.class);
	    startService(serviceIntent);
	    // --------------------------------------------------------------------------------
        // for displaying the progress of some operation (wiki page etc.)
        getWindow().requestFeature(Window.FEATURE_PROGRESS);
        getWindow().requestFeature(Window.PROGRESS_VISIBILITY_ON);


//        // Set up the ViewPager, attaching the adapter.
//        mViewPager = (ViewPager) findViewById(R.id.pager);
////        mViewPager.setAdapter(mDemoCollectionPagerAdapter);
//
//        mViewPager = (ViewPager) findViewById(R.id.pager);
//        mViewPager.setOnPageChangeListener(
//                new ViewPager.SimpleOnPageChangeListener() {
//                    @Override
//                    public void onPageSelected(int position) {
//                        // When swiping between pages, select the
//                        // corresponding tab.
//                        getActionBar().setSelectedNavigationItem(position);
//                    }
//                });
//        // Create a tab listener that is called when the user changes tabs.
//        ActionBar.TabListener tabListener = new ActionBar.TabListener() {
//            public void onTabSelected(ActionBar.Tab tab, FragmentTransaction ft) {
//                // show the given tab
//            }
//
//            public void onTabUnselected(ActionBar.Tab tab, FragmentTransaction ft) {
//                // hide the given tab
//            }
//
//            public void onTabReselected(ActionBar.Tab tab, FragmentTransaction ft) {
//                // When the tab is selected, switch to the
//                // corresponding page in the ViewPager.
//                mViewPager.setCurrentItem(tab.getPosition());
//            }
//        };
//
//        // actionBar.addTab(actionBar.newTab());
//        for (int i = 0; i < 4; i++) {
//            actionBar.addTab(
//                    actionBar.newTab()
//                            .setText("Tab " + (i + 1))
//                            .setTabListener(tabListener));
//        }
    }

    /**
     * Adds menu into the main activity from xml.
     *
     * @param menu Menu
     * @return boolean
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_window_menu, menu);
        return true;
    }

    /**
     * Overriding the method of super class as we will display the menu in
     * accordance of selected tab
     *
     * @param menu Menu
     * @return boolean
     * @see <code> http://developer.android.com/reference/android/app/Activity.html</code>
     * onPrepareOptionsMenu(android .view.Menu) Method
     * onPrepareOptionsMenu.
     */
    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        /*
           * 0 Calendar 1 Event notification 2 Plant list 3 Wiki
           */
        /*
      Field activeTabIndex.
     */
        short activeTabIndex = 0;
        switch (activeTabIndex) {
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
     * @return boolean
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle menu item selections
        switch (item.getItemId()) {
            case R.id.menu_item_exit_app:
                // stopping the service (the memory)
                Intent service_intent = new Intent().setClass(MainActivity.this,
                        Service.class);
                stopService(service_intent);// and finish the activity (hide, the
                // system itself will clean the
                // memory)
                Service.removeNotifications();
                this.moveTaskToBack(true); // see
                // http://developer.android.com/reference/android/app/Activity.html#moveTaskToBack%28boolean%29
                // finish();
                return true;
            case R.id.menu_item_settings:
                // Intent intent = new
                // Intent(Constants.INTENT_ACTION_VIEW_LIST);
                // startActivity(intent);
                startActivity(new Intent(this, SettingsActivity.class));
                return true;
            case R.id.menu_item_refresh_events:
                return true;
            case R.id.menu_item_refresh_plant_list: // user selected the refresh
                // plant list item in menu
	            refreshPlantList();
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
    public void refreshPlantList() {
        // TODO: add the intend to call the method from the plant_list activity
    }

}
/* Just for fun (C) Torvalds Linus */
