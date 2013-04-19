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
 * Location and the language of the application settings. Displayed from the xml
 * preferences file.
 *
 * @author zafar.khaydarov
 * @version $Revision: 1.7 $
 */
public class LangLocSettings extends PreferenceActivity {

    /** @param Saved_Instance_State Bundle */
    @Override
    public void onCreate(Bundle Saved_Instance_State) {

        super.onCreate(Saved_Instance_State);
        // setContentView(R.layout.lang_and_loc_settings);
        getPreferenceManager().setSharedPreferencesName("preferences");
        addPreferencesFromResource(R.xml.preferences);
        // TODO: add the handlers for the country and location list
    }
}
