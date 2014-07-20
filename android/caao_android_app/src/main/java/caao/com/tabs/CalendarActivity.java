/**
 *  Computer science department 
 * Project: Context-Aware Agriculture Organizer 
 * Author: Zafar Khaydarov
 * E-mail: zkhayda@uef.fi 
 * Web: cs.joensuu.fi/~zkhayda 
 * Date: Mar 17, 2011
 */
package caao.com.tabs;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Button;
import android.widget.TextView;
import caao.com.R;


/**
 * The activity implements the calendar (month view) in a tab.
 *
 * @author zafar.khaydarov
 * @version $Revision: 1.16 $
 */
public class CalendarActivity extends Activity {
    /**
     * Field MIME_TYPE. (value is
     * ""vnd.android.cursor.dir/vnd.exina.android.calendar.date"")
     */
    public static final String MIME_TYPE = "vnd.android.cursor.dir/vnd.exina.android.calendar.date";
    /**
     * Field mHit.
     */
    TextView mHit;
    /**
     * Field mHandler.
     */
    Handler mHandler = new Handler();
    /**
     * Field tv.
     */
    TextView tv = null;
    /**
     * Field button_prev.
     */
    private Button button_prev, button_today, button_next;

    // -------------------------------------------------------------------------------------------------

    /**
     * @param savedInstanceState Bundle
     */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // setting the layout from xml
        setContentView(R.layout.calendar_tab);
    }

    // -------------------------------------------------------------------------------------------------
}
