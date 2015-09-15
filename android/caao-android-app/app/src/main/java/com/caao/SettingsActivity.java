/**
 * Computer science department
 * Project: Context-Aware Agriculture Organizer
 * Author: Zafar Khaydarov
 * E-mail: zkhayda@uef.fi
 * Web: cs.joensuu.fi/~zkhayda
 * Date: Mar 17, 2011
 */
package com.caao;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.caao.settings.AccountSettings;
import com.caao.settings.AdvancedSettings;
import com.caao.settings.LangLocSettings;
import com.caao.settings.NotificationSettings;

/**
 * The activity implements main settings view of the application.
 *
 * @author zafar.khaydarov
 * @version $Revision: 1.15 $
 */
public class SettingsActivity extends ListActivity {
    private static final int ABOUT_DIALOG = 0;
    private static final int LICENSE_DIALOG = 1;
    private static final int FEEDBACK_DIALOG = 2;
    private static final int THIRD_PARTY_LICENCES_DIALOG = 3;
    private ListView lv;
    /**
     * Listener for the on list item click. Handles the clicked item in the list
     * view.
     *
     * @see android.widget.AdapterView.OnItemClickListener
     */
    private AdapterView.OnItemClickListener onSettings_List_Click = new AdapterView.OnItemClickListener() {
        public void onItemClick(AdapterView<?> parent, View view, int position,
                                long id) {
            // 0 Account Settings
            // 1 Application language and location settings
            // 2 Notification
            // 3 Advanced settings
            // 4 Version information
            // 5 Share feedback
            // 6 License
            // 7 Third party licenses
            switch (position) {

                case 0:
                    startActivity(new Intent().setClass(getApplicationContext(),
                            AccountSettings.class));
                    break;

                case 1:
                    startActivity(new Intent().setClass(getApplicationContext(),
                            LangLocSettings.class));
                    break;

                case 2:
                    startActivity(new Intent().setClass(getApplicationContext(),
                            NotificationSettings.class));
                    break;

                case 3:
                    startActivity(new Intent().setClass(getApplicationContext(),
                            AdvancedSettings.class));
                    break;

                case 4:
                    showDialog(ABOUT_DIALOG);
                    break;

                case 5:
                    showDialog(FEEDBACK_DIALOG);
                    break;

                case 6:
                    showDialog(LICENSE_DIALOG);
                    break;
                case 7:
                    showDialog(THIRD_PARTY_LICENCES_DIALOG);
                    break;

                default:
                    break;
            }
        }
    };

    /**
     * On create - the initialization of variables and members.
     *
     * @param savedInstanceState Bundle
     */

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setListAdapter(new ArrayAdapter<String>(this, R.layout.settings,
                getResources().getStringArray(R.array.settings_items_titles)));

        this.lv = getListView();
        this.lv.setTextFilterEnabled(true);
        this.lv.setPadding(1, 8, 0, 8);
        this.lv.setOnItemClickListener(this.onSettings_List_Click);
    }

    /**
     * Displays the dialogs for the settings activity
     *
     * @param id int
     * @return Dialog
     */
    protected Dialog onCreateDialog(int id) {
        Dialog dialog = null;
        switch (id) {
            case ABOUT_DIALOG:
                // the about dialog
                // ------------------------------------------------------------------------------
                dialog = new AlertDialog.Builder(SettingsActivity.this)
                        .setTitle(getString(R.string.about_dialog_title))
                        .setMessage(getString(R.string.about_dialog_content_text))
                        .create();
                // ------------------------------------------------------------------------------
                break;
            case LICENSE_DIALOG:
                // the license dialog
                // ------------------------------------------------------------------------------
                dialog = new AlertDialog.Builder(SettingsActivity.this)
                        .setTitle(getString(R.string.licence_dialog_title))
                        .setMessage(getString(R.string.license_dialog_licence_text))
                        .create();
                break;

            case FEEDBACK_DIALOG:
                // the feedback dialog
                dialog = new Dialog(this);
                dialog.setContentView(R.layout.feedback_dialog);
                dialog.setTitle("Your opinion is very important for us.");
                // Button b =
                // (Button)dialog.findViewById(R.id.submit_feedback_button_id);
                // b.setOnClickListener( new OnClickListener()
                // {
                // public void onClick(View v) {
                // // TODO add the feedback submitter handler
                // Toast.makeText(getApplicationContext(), "Thank you so much!",
                // Toast.LENGTH_LONG).show();
                // }
                // });
                break;
            case THIRD_PARTY_LICENCES_DIALOG:
                // the feedback dialog
                dialog = new Dialog(this);
                dialog.setContentView(R.layout.third_party_licenses);
                dialog.setTitle("Third party licenses.");
                break;
            default:
                dialog = null;
        }
        return dialog;
    }
}

