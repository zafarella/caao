/**
 * University of Eastern Finland
 * Computer science department
 * Project: Context Aware Orginizer
 * Author: Zafar Khaydarov
 * E-mail: zkhayda@uef.fi
 * Web: cs.joensuu.fi/~zkhayda
 * Date: Apr 26, 2012
 */
package main.java.caao.com;

import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * @author z1
 */
public class CaaoUtils extends Activity {

    public boolean isOnline() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        if (netInfo != null && netInfo.isConnectedOrConnecting()) {
            return true;
        }
        return false;
    }
}