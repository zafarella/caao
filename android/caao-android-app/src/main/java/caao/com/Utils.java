/**
 * University of Eastern Finland
 * Computer science department
 * Project: Context Aware Orginizer
 * Author: Zafar Khaydarov
 * E-mail: zkhayda@uef.fi
 * Web: cs.joensuu.fi/~zkhayda
 * Date: Apr 26, 2012
 */
package caao.com;

import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Wrappers on top of device API to get device settings.
 *
 * @author z1
 */
public class Utils extends Activity {
    /**
     * Do we have network connectivity?
     *
     * @return true, when device is able to connect to the internet.
     */
    public boolean areWeOnline() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        return (netInfo != null && netInfo.isConnectedOrConnecting());
    }
}