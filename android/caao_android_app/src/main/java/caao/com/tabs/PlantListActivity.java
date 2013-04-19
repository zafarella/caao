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
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import caao.com.CaaoConstants;
import caao.com.MyToast;
import caao.com.R;
import caao.com.settings_activities.AdvancedSettings;
import caao.com.xmlrpc.XMLRPCClient;

import java.net.URI;
import java.util.ArrayList;
import java.util.Vector;

/**
 * Handles the contents of the plant list activity. Simply displays the list of
 * the plants for the user.
 * <p/>
 * On order to understand some tricks here (at least they were :)) read the
 * following link content:
 * http://stackoverflow.com/questions/4502810/android-listview
 * -within-tabwidget-activity-gives-exception
 *
 * @author zafar.khaydarov
 * @version $Revision: 1.12 $
 */
public class PlantListActivity extends Activity {
    // ----------------------------------------------------------------------
    /** Field mPlantsListAdapter. */
    private PlantsListAdapter mPlantsListAdapter = null;
    /** the list of plans as List */
    private ArrayList<String> mListOfPlants = null;
    /** Field mProgressDialog. */
    private ProgressDialog mProgressDialog = null;
    /** Field mGetPlantList. */
    private Runnable mGetPlantList;

    // -------------------------------------------------------------------------

    /**
     * Called when the activity created. The initialization of the variables
     * should be placed here
     *
     * @param savedInstanceState Bundle
     */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.plant_list_tab);

        // ------------------------------------------------------------------------------
        ListView listOfPlanst_list_view;
        // the listview of the plants, linking with the layout
        listOfPlanst_list_view = (ListView) findViewById(R.id.list_of_plants__listView);

        mListOfPlants = new ArrayList<String>();
        // the adapter for the list of plants
        mPlantsListAdapter = new PlantsListAdapter();
        // assigning the adapter to render the data to the listview
        listOfPlanst_list_view.setAdapter(this.mPlantsListAdapter);

        // retrieving the plants in separate thread
        mGetPlantList = new Runnable() {
            @Override
            public void run() {
                try {
                    retreivePlants();
                } catch (Exception e) {
                    new MyToast(getApplicationContext(), "[Exception->] "
                            + e.getMessage(), true);
                }
            }
        };

        Thread thread = new Thread(mGetPlantList, "MagentoBackground");
        thread.start();
        mProgressDialog = ProgressDialog.show(this, "Please wait...",
                "Retrieving data from server ...", true);
    }

    /** Field returnRes. */
    private Runnable returnRes = new Runnable() {
        @Override
        public void run() {
            if (mListOfPlants != null && mListOfPlants.size() > 0) {
                mPlantsListAdapter.notifyDataSetChanged();
                for (int i = 0; i < mListOfPlants.size(); i++)
                    mPlantsListAdapter.add(mListOfPlants.get(i));
            }
            mProgressDialog.dismiss();
            mPlantsListAdapter.notifyDataSetChanged();
            new MyToast(getApplicationContext(), +mListOfPlants.size()
                    + " plants have been received", false);
            if (null == mListOfPlants)
                new MyToast(getApplicationContext(), "Some error has ocured, no data received" +
                        ". Please contact administrator.", false);
        }
    };

    // -------------------------------------------------------------------------

    /**
     * Retrieves the list of plants from the server. Should be executed in
     * separate (from UI) thread.
     *
     * @throws Exception
     */
    private void retreivePlants() throws Exception {
        mListOfPlants = new ArrayList<String>();
        XMLRPCClient rpc_client;
        URI uri;
        String user_name;

        SharedPreferences adv_settings = getSharedPreferences(
                CaaoConstants.ADVANCED_PREFERENCES_FILE, 0);
        // reading the server url from preferences
        String l_server_url = adv_settings.getString("server_url", "");
        if (!l_server_url.trim().equals("")) {
            // if (areThePhoneOnline()) {
            uri = URI.create(l_server_url);
            // creating the rpc client
            rpc_client = new XMLRPCClient(uri);
            user_name = "zkhayda@uef.fi";
            try {
                Object[] result = (Object[]) rpc_client.call(
                        "CaaoServerCore.plantList", user_name);
                Vector<Object> plant_list = new Vector<Object>();
                for (Object o : result) {
                    plant_list.add(o.toString());
                    mListOfPlants.add(o.toString());
                }
            } catch (Exception e) {
                Log.e(CaaoConstants.TAG, "[Exception]->" + e.getMessage());
            }
            // } else {
            // Toast
            // .makeText(
            // getApplicationContext(),
            // "You are offline, cannot retreive the data. Please try later.",
            // Toast.LENGTH_LONG);
            // }
            // PlantListActivity.populate_plant_list();
            // ListAdapter(new ArrayAdapter<String>(this, R., COUNTRIES));
        } else {
            // the server url is empty, launching the preferences to ask
            // user enter the server address

            AlertDialog alertDialog = new AlertDialog.Builder(
                    getApplicationContext()).create();
            alertDialog.setTitle("Missing server URL");
            alertDialog
                    .setMessage("It seams that your settings need to be updates. Please provide server URL in advanced settings. Gonna launch the preferences");
            alertDialog.setButton("OK", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    startActivity(new Intent().setClass(
                            getApplicationContext(), AdvancedSettings.class));
                }
            });
            alertDialog.show();
        }
        runOnUiThread(returnRes);
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
     * fields) override the ancestors's class few methods For more details,
     * please read the documentation.
     *
     * @author zafar.khaydarov
     * @see ArrayAdapter
     */
    class PlantsListAdapter extends ArrayAdapter<String> {
        /** Constructor for PlantsListAdapter. */
        PlantsListAdapter() {
            super(PlantListActivity.this, R.layout.plant_list_row,
                    mListOfPlants);
        }
    }
}
