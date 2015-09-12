/**
 *
 * Computer science department 
 * Project: Context Aware Orginizer 
 * Author: Zafar Khaydarov
 * E-mail: zkhayda@uef.fi 
 * Web: cs.joensuu.fi/~zkhayda 
 * Date: Mar 23, 2011
 */
package caao.com.settings;

import android.app.Activity;
import android.os.Bundle;
import caao.com.R;

/**
 * Activity handles the account settings view.
 *
 * @author zafar.khaydarov
 * @version $Revision: 1.5 $
 */
public class AccountSettings extends Activity {
    /**
     * @param Saved_Instance_State Bundle
     */
    @Override
    public void onCreate(Bundle Saved_Instance_State) {
        super.onCreate(Saved_Instance_State);
        // loading the screen from the xml
        setContentView(R.layout.account_settings);
    }
}
