/**
 * Project: Context-Aware Agriculture Organizer
 * Author: Zafar Khaydarov
 * E-mail: zkhayda@uef.fi
 * Web: cs.joensuu.fi/~zkhayda
 * Date: Mar 17, 2011
 */
package com.caao.tabs;

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

import com.caao.MyToast;
import com.caao.R;
import com.caao.Utils;
import com.caao.Constants;
import com.caao.settings.AdvancedSettings;

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
    private PlantsListAdapter mPlantsListAdapter = null;
    private ArrayList<String> mListOfPlants = null;
    private ProgressDialog mProgressDialog = null;
    private Runnable mGetPlantList;
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

    /**
     * Called when the activity created. The initialization of the variables
     * should be placed here
     *
     * @param savedInstanceState Bundle
     */
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.plant_list_tab);

        ListView listOfPlanst_list_view;
        // the listview of the plants, linking with the resources.layout
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
                    retrievePlants();
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

    /**
     * Retrieves the list of plants from the server. Should be executed in
     * separate (from UI) thread.
     *
     * @throws Exception
     */
    private void retrievePlants() throws Exception {

        mListOfPlants = new ArrayList<String>();
        com.caao.xmlrpc.XMLRPCClient rpcClient;
        URI uri;
        String userName;

        SharedPreferences adv_settings = getSharedPreferences(
                Constants.ADVANCED_PREFERENCES_FILE, 0);

        // reading the server url from preferences
        String serverUrl = adv_settings.getString("server_url", "");

        if (!serverUrl.trim().isEmpty() && new Utils().areWeOnline()) {
            uri = URI.create(serverUrl);
            // creating the rpc client
            rpcClient = new com.caao.xmlrpc.XMLRPCClient(uri);
            userName = "zkhayda@uef.fi";
            try {
                Object[] result = (Object[]) rpcClient.call(
                        "CaaoServerCore.plantList", userName);
                Vector<Object> plantList = new Vector<Object>();
                // plantList = new Collections.synchronizedList(plantList);
                for (Object o : result) {
                    plantList.add(o.toString());
                    mListOfPlants.add(o.toString());
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
     * @see ArrayAdapter
     */
    class PlantsListAdapter extends ArrayAdapter<String> {
        PlantsListAdapter() {
            super(PlantListActivity.this, R.layout.plant_list_row,
                    mListOfPlants);
        }
    }
}
