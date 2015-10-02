/**
 * Project: Context-Aware Agriculture Organizer
 * Author: Zafar Khaydarov
 */
package com.caao;

import android.app.ActionBar;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;


import com.caao.service.Service;

import java.util.Locale;

/**
 * The activity which is called when the application is started. The idea behind
 * this activity is simple: first it checks - are the user is registered in the
 * system, if no, it launches the activity which handles the user registration.
 * <p/>
 * The registration information is stored in the private storage of the
 * application (see android docs for details). In case the user is already
 * registered, activity reads from preferences the localization information to
 * switch to the desired user language. The rest procedure is creation of the
 * tabs, their content and menus.
 * <p/>
 * The XML-RPC library included into the application as a source code. You can find the
 * documentation and samples in the references. The library is lightweight and
 * very easy to use.
 * <p/>
 * This implementation is uses the only basics of it's
 * capabilities. As a more advanced features (optimization, compression), please
 * read the references.
 * For the reference please get familiar with the following
 * info -- http://code.google.com/p/android-xmlrpc --
 *
 * @author zafar.khaydarov
 * @version $Revision: 1.22 $
 */

public class MainActivity extends FragmentActivity implements ActionBar.TabListener {

    /**
     * for debug purposes the constant in logs for recognizing the app in logs
     * Field TAG. (value is ""CAAO"")
     */
    @SuppressWarnings("unused")
    private static final String TAG = "CAAO";
    private static final int DATE_PICKER_DIALOG_ID = 0;
    private ListView mDrawerList;
    private DrawerLayout mDrawerLayout;
    private String[] leftDrawerItems;
    private CharSequence mDrawerTitle;
    private CharSequence mTitle;
    private ActionBarDrawerToggle mDrawerToggle;

    ViewPager mViewPager;

    /**
     * Called when the activity is first created. We will create here the tabs
     *
     * @param savedInstanceState Bundle
     * @see Resources
     * @see SharedPreferences
     * @see
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        // for displaying the progress of some operation (wiki page etc.)
        getWindow().requestFeature(Window.FEATURE_PROGRESS);
        getWindow().requestFeature(Window.PROGRESS_VISIBILITY_ON);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        // TODO: the code that should handle new user registration

        final ActionBar actionBar = getActionBar();
        // Specify that tabs should be displayed in the action bar.
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

        mDrawerTitle = mTitle = getTitle();
        leftDrawerItems = getResources().getStringArray(R.array.settings_items_titles);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerList = (ListView) findViewById(R.id.left_drawer);
        mDrawerList.setAdapter(new ArrayAdapter<String>(this,
                R.layout.left_drawer, leftDrawerItems));
        // set a custom shadow that overlays the main content when the drawer opens
        //mDrawerLayout.setDrawerShadow(R.resources.drawable.drawer_shadow, GravityCompat.START);
        // set up the drawer's list view with items and click listener
        mDrawerList.setOnItemClickListener(new DrawerItemClickListener());


        // enable ActionBar app icon to behave as action to toggle nav drawer
        getActionBar().setDisplayHomeAsUpEnabled(true);
        getActionBar().setHomeButtonEnabled(true);

        // ActionBarDrawerToggle ties together the the proper interactions
        // between the sliding drawer and the action bar app icon
        mDrawerToggle = new ActionBarDrawerToggle(
                this,                  // host Activity
                mDrawerLayout,         // DrawerLayout object
                R.drawable.ic_drawer,  // nav drawer image to replace 'Up' caret
                R.string.drawer_open,  // "open drawer" description for accessibility
                R.string.drawer_close  // "close drawer" description for accessibility
        ) {
            public void onDrawerClosed(View view) {
                getActionBar().setTitle(mTitle);
                invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
            }

            public void onDrawerOpened(View drawerView) {
                getActionBar().setTitle(mDrawerTitle);
                invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
            }
        };
        mDrawerLayout.setDrawerListener(mDrawerToggle);

        if (savedInstanceState == null) {
            selectItem(0);
        }

        /*
            locale code
         */
        SharedPreferences advSettings = getSharedPreferences("preferences", 0);
        // reading the server url from preferences
        String lang = advSettings.getString("application_language", "");

        Locale locale = new Locale(lang);

        Locale.setDefault(locale);
        Configuration config = new Configuration();
        config.locale = locale;
        getBaseContext().getResources().updateConfiguration(config,
                getBaseContext().getResources().getDisplayMetrics());

        // the service which we gonna run on background
        Intent serviceIntent = new Intent(Constants.ACTION_FOREGROUND);
        serviceIntent.setClass(MainActivity.this, Service.class);
        startService(serviceIntent);

        /*
                pager - tabs
         */
        // Set up the ViewPager, attaching the adapter.
        mViewPager = (ViewPager) findViewById(R.id.pager);
        // mViewPager.setAdapter(mDemoCollectionPagerAdapter);

        mViewPager = (ViewPager) findViewById(R.id.pager);
        mViewPager.setOnPageChangeListener(
                new ViewPager.SimpleOnPageChangeListener() {
                    @Override
                    public void onPageSelected(int position) {
                        // When swiping between pages, select the
                        // corresponding tab.
                        getActionBar().setSelectedNavigationItem(position);
                    }
                });
        // Create a tab listener that is called when the user changes tabs.
        String[] tabs = getResources().getStringArray(R.array.tabTitles);
        for (String tab : tabs) {
            actionBar.addTab(
                    actionBar.newTab()
                            .setText(tab)
                            .setTabListener(this));
        }
    }


    @Override
    public void setTitle(CharSequence title) {
        mTitle = title;
        getActionBar().setTitle(mTitle);
    }

    /**
     * Adds resources.menu into the main activity from resources.xml.
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
     * Overriding the method of super class as we will display the resources.menu in
     * accordance of selected tab.
     *
     * @param menu Menu
     * @return boolean
     * @see <code> http://developer.android.com/reference/android/app/Activity.html</code>
     * onPrepareOptionsMenu(android .view.Menu) Method
     * onPrepareOptionsMenu.
     */
    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        short activeTabIndex = 0;
        switch (activeTabIndex) {
            case 0:
                menu.setGroupVisible(R.id.group_calendar_items, true);
                menu.setGroupVisible(R.id.group_webView, false);
                menu.setGroupVisible(R.id.group__menu_plans_events, false);
                break;
            case 3:
                // hiding the group of refresh items resources.menu
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
     * todo to be moved to nav panel on the left
     * <p/>
     * The resources.menu handler method. The resources.menu items id's can reveal the meaning of
     * the items
     *
     * @param item MenuItem
     * @return boolean
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (mDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }

        // Handle resources.menu item selections
        switch (item.getItemId()) {
            case R.id.menu_item_exit_app:
                // stopping the service (the memory)
                Intent service_intent = new Intent().setClass(MainActivity.this,
                        Service.class);
                // and finish the activity (hide, the system itself will clean the memory)
                stopService(service_intent);
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
            case R.id.menu_item_sync:
                return true;
            case R.id.menu_item_sync_data: // user selected the refresh
                // plant list item in resources.menu
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
     */
    protected void refreshPlantList() {
        // TODO: add the intend to call the method from the plant_list activity
    }

    @Override
    public void onTabSelected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {
        mViewPager.setCurrentItem(tab.getPosition());
    }

    @Override
    public void onTabUnselected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {

    }

    @Override
    public void onTabReselected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {
    }

    private class DrawerItemClickListener implements ListView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            selectItem(position);
        }
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        // Sync the toggle state after onRestoreInstanceState has occurred.
        mDrawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        // Pass any configuration change to the drawer toggls
        mDrawerToggle.onConfigurationChanged(newConfig);
    }

    private void selectItem(int position) {
        // update the main content by replacing fragments
        //Fragment fragment = new PlanetFragment();
        Bundle args = new Bundle();
        // args.putInt(PlanetFragment.ARG_PLANET_NUMBER, position);
        //fragment.setArguments(args);

        //   FragmentManager fragmentManager = getFragmentManager();
        // fragmentManager.beginTransaction().replace(R.id.content_frame, fragment).commit();

        // update selected item and title, then close the drawer
        mDrawerList.setItemChecked(position, true);
        // setTitle(mPlanetTitles[position]);
        mDrawerLayout.closeDrawer(mDrawerList);
    }
}
/* Just for fun (C) Torvalds Linus */
