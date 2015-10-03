package com.caao; /**
 * Computer science department
 * Project: Context Aware Organizer
 * Author: Zafar Khaydarov
 * E-mail: zkhayda@uef.fi
 * Web: cs.joensuu.fi/~zkhayda
 * Date: Apr 28, 2011
 */

/**
 * class contains common constants used in application the purpose - simplify
 * the maintenance and increase the readability.
 *
 * @author zafar.khaydarov
 */
public final class Constants {
    /**
     * resources.xml file with the advanced preferences
     */
    public static final String ADVANCED_PREFERENCES_FILE = "advanced_preferences";
    /**
     * android service location
     * <p/>
     * Field ACTION_FOREGROUND. (value is ""caao.com.service.Service"")
     */
    public static final String ACTION_FOREGROUND = "com.caao.service.Service";
    /**
     * the application tag for recognizing the app in the log
     *
     * @see android.util.Log
     */
    public static final String TAG = "caao_app";
}
