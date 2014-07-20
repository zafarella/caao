/**
 *  Computer science department 
 * Project: Context-Aware Agriculture Organizer 
 * Author: Zafar Khaydarov
 * E-mail: zkhayda@uef.fi 
 * Web: cs.joensuu.fi/~zkhayda 
 * Date: Mar 17, 2011
 */
package caao.com.tabs;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import caao.com.Constants;
import caao.com.MyToast;
import caao.com.R;
import caao.com.settings_activities.AdvancedSettings;
import caao.com.xmlrpc.XMLRPCClient;

import java.net.URI;
import java.util.ArrayList;
import java.util.Vector;

/**
 * @author zafar.khaydarov
 * @version $Revision: 1.9 $
 */
public class EventNotificationActivity extends Activity {
    /**
     * Field mEventsListAdapter.
     */
    private EventListAdapter mEventsListAdapter = null;
    /**
     * the list of plans as List
     */
    private ArrayList<String> mListOfEvents = null;
    /**
     * Field mProgressDialog.
     */
    private ProgressDialog mProgressDialog = null;
    /**
     * Field mGetEventList.
     */
    private Runnable mGetEventList;
    /**
     * Field returnRes.
     */
    private Runnable returnRes = new Runnable() {
        @Override
        public void run() {
            if (mListOfEvents != null && mListOfEvents.size() > 0) {
                mEventsListAdapter.notifyDataSetChanged();
                for (int i = 0; i < mListOfEvents.size(); i++)
                    mEventsListAdapter.add(mListOfEvents.get(i));
            }
            mProgressDialog.dismiss();
            mEventsListAdapter.notifyDataSetChanged();
            new MyToast(getApplicationContext(), +mListOfEvents.size()
                    + " plants have been received", false);
            if (null == mListOfEvents)
                new MyToast(getApplicationContext(),
                        "Some error has ocured, no data received"
                                + ". Please contact administrator.", false);
        }
    };

    // -------------------------------------------------------------------------------------

    /**
     * @param savedInstanceState Bundle
     */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.event_list_tab);

        if (areThePhoneOnline()) {
            // ------------------------------------------------------------------------------
            ListView listOfEvents_list_view;
            // the listview of the plants, linking with the layout
            listOfEvents_list_view = (ListView) findViewById(R.id.list_of_events__listView);
            mListOfEvents = new ArrayList<String>();
            // the adapter for the list of plants
            mEventsListAdapter = new EventListAdapter();
            // assigning the adapter to render the data to the listview
            listOfEvents_list_view.setAdapter(this.mEventsListAdapter);
            // retrieving the plants in separate thread
            mGetEventList = new Runnable() {
                @Override
                public void run() {
                    try {
                        retreiveEvents();
                    } catch (Exception e) {
                        new MyToast(getApplicationContext(), "[Exception->] "
                                + e.getMessage(), true);
                    }
                }
            };
            Thread thread = new Thread(mGetEventList, "Event_List_Background");
            thread.start();
            mProgressDialog = ProgressDialog.show(this, "Please wait...",
                    "Retrieving events ...", true);
        } else {
            new MyToast(getApplicationContext(),
                    "Unable to perform action - no Internet connection");
        }
    }

    // -------------------------------------------------------------------------

    /**
     * Retrieves the list of plants from the server. Should be executed in
     * separate (from UI) thread.
     *
     * @throws Exception
     */
    private void retreiveEvents() throws Exception {
        mListOfEvents = new ArrayList<String>();
        XMLRPCClient rpc_client;
        URI uri;
        String user_name;

        SharedPreferences adv_settings = getSharedPreferences(
                Constants.ADVANCED_PREFERENCES_FILE, 0);
        // reading the server url from preferences
        String l_server_url = adv_settings.getString("server_url", "");
        if (!l_server_url.trim().equals("")) {
            if (areThePhoneOnline()) {
                uri = URI.create(l_server_url);
                // creating the rpc client
                rpc_client = new XMLRPCClient(uri);
                // TODO: add the getting the user name from the preferences
                user_name = "zkhayda@uef.fi";
                try {
                    Object[] result = (Object[]) rpc_client.call(
                            "CaaoServerCore.eventList", user_name);
                    Vector<Object> events_list = new Vector<Object>();
                    for (Object o : result) {
                        events_list.add(o.toString());
                        mListOfEvents.add(o.toString());
                    }
                } catch (Exception e) {
                    Log.e(Constants.TAG, "[Exception]->" + e.getMessage());
                }

            } else {
                // the server url is empty, launching the preferences to ask
                // user enter the server address

                AlertDialog alertDialog = new AlertDialog.Builder(
                        getApplicationContext()).create();
                alertDialog.setTitle("Missing server URL");
                alertDialog
                        .setMessage("It seams that your settings need to be updates. Please provide server URL in advanced settings. Gonna launch the preferences");
                alertDialog.setButton("OK",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,
                                                int which) {
                                startActivity(new Intent().setClass(
                                        getApplicationContext(),
                                        AdvancedSettings.class));
                            }
                        });
                alertDialog.show();
            }
            runOnUiThread(returnRes);
        }
    }

    public boolean areThePhoneOnline() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        return cm.getActiveNetworkInfo().isConnectedOrConnecting();
    }

    // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

    /**
     * The adaptor class which binds the components and provides the list
     * management. Not overriding the getView method as we only need to display
     * simple text. In case you want to have some other data, override the
     * method getView. For more details see the corresponding Javadoc for
     * android platform.
     * <p/>
     * In order to extend the list (for example you want to add some other
     * fields) override the ancestors's class few methods. For more details,
     * please read the documentation.
     *
     * @author zafar.khaydarov
     * @see ArrayAdapter
     */
    class EventListAdapter extends ArrayAdapter<String> {
        /**
         * Constructor for EventListAdapter.
         */
        EventListAdapter() {
            super(EventNotificationActivity.this, R.layout.event_list_row,
                    mListOfEvents);
        }
    }
}