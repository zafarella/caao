/**
 *
 * Computer science department
 * Project: Context Aware Orginizer
 * Author: Zafar Khaydarov
 * E-mail: zkhayda@uef.fi
 * Web: cs.joensuu.fi/~zkhayda
 * Date: Apr 28, 2011
 */
package main.java.caao.com;

/**
 * class contains common constants used in application the purpose - simplify
 * the maintenance and increase the readability.
 *
 * @author zafar.khaydarov
 */
public class CaaoConstants {
    /**
     * xml file with the advanced preferences
     */
    public static final String ADVANCED_PREFERENCES_FILE = "advanced_preferences";
    /**
     * android service location
     * <p/>
     * Field ACTION_FOREGROUND. (value is ""caao.com.service.caao_service"")
     */
    static final String ACTION_FOREGROUND = "caao.com.service.caao_service";
    /**
     * the application tag for recognizing the app in the log
     *
     * @see android.util.Log
     */
    public static final String TAG = "caao_app";
}
