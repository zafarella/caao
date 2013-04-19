/**
 *
 * Computer science department 
 * Project: Context Aware Orginizer 
 * Author: Zafar Khaydarov
 * E-mail: zkhayda@uef.fi 
 * Web: cs.joensuu.fi/~zkhayda 
 * Date: Mar 23, 2011
 */
package caao.com.settings_activities;

import android.os.Bundle;
import android.preference.PreferenceActivity;
import caao.com.R;

/**
 * Displays and handles the notification settings of the application
 *
 * @author zafar.khaydarov
 * @version $Revision: 1.5 $
 */
public class NotificationSettingsActivity extends PreferenceActivity {
    /** @param Saved_Instance_State Bundle */
    @Override
    public void onCreate(Bundle Saved_Instance_State) {
        super.onCreate(Saved_Instance_State);
        getPreferenceManager().setSharedPreferencesName(
                "notification_preferences");
        addPreferencesFromResource(R.xml.notification_preferences);
    }
}