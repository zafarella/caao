/**
 * The beautiful and smart calendar
 */
package com.caao.tabs;

import android.app.Activity;
import android.os.Bundle;

import com.caao.R;

/**
 * The activity implements the calendar (month view) in a tab.
 *
 * @author zafar.khaydarov
 * @version $Revision: 1.16 $
 */
public class CalendarActivity extends Activity {
    /**
     * @param savedInstanceState Bundle
     */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // setting the resources.layout from resources.xml
        setContentView(R.layout.calendar_tab);
    }
}
