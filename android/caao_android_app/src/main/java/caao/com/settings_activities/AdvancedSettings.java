/**
 *  Computer science department 
 * Project: Context Aware Orginizer 
 * Author: Zafar Khaydarov
 * E-mail: zkhayda@uef.fi 
 * Web: cs.joensuu.fi/~zkhayda 
 * Date: Mar 24, 2011
 */
package caao.com.settings_activities;

import android.os.Bundle;
import android.preference.PreferenceActivity;
import caao.com.R;

/**
 * Advanced preferences - the submenu in settings. Displays bunch of advanced
 * preferences.
 *
 * @author zafar.khaydarov
 * @version $Revision: 1.8 $
 */
public class AdvancedSettings extends PreferenceActivity {
    /**
     * @param Saved_Instance_State Bundle
     */
    @Override
    public void onCreate(Bundle Saved_Instance_State) {
        super.onCreate(Saved_Instance_State);
        // natifying the Preference manager to save the settings after user have
        // changed them
        getPreferenceManager().setSharedPreferencesName("advanced_preferences");
        // displaying the preferences
        addPreferencesFromResource(R.xml.advanced_preferences);
    }
}
