/**
 *  Computer science department 
 * Project: Context-Aware Agriculture Organizer 
 * Author: Zafar Khaydarov
 * E-mail: zkhayda@uef.fi 
 * Web: cs.joensuu.fi/~zkhayda 
 * Date: Mar 17, 2011
 */
package caao.com.tabs;

import java.util.Calendar;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import caao.com.R;
import caao.com.tabs.calendar.CalendarView;
import caao.com.tabs.calendar.Cell;

/**
 * The activity implements the calendar (month view) in a tab.
 * 
 * @author zafar.khaydarov
 * @version $Revision: 1.16 $
 */
public class Calendar_Activity extends Activity implements
	CalendarView.OnCellTouchListener {
    /**
     * Field MIME_TYPE. (value is
     * ""vnd.android.cursor.dir/vnd.exina.android.calendar.date"")
     */
    public static final String MIME_TYPE = "vnd.android.cursor.dir/vnd.exina.android.calendar.date";
    /**
     * Field mView.
     */
    CalendarView mView = null;
    /**
     * Field mHit.
     */
    TextView mHit;

    /**
     * Field button_next.
     */
    /**
     * Field button_today.
     */
    /**
     * Field button_prev.
     */
    private Button button_prev, button_today, button_next;
    /**
     * Field mHandler.
     */
    Handler mHandler = new Handler();
    /**
     * Field tv.
     */
    TextView tv = null;

    // -------------------------------------------------------------------------------------------------
    /**
     * @param savedInstanceState
     *            Bundle
     */
    public void onCreate(Bundle savedInstanceState) {
	super.onCreate(savedInstanceState);
	// setting the layout from xml
	setContentView(R.layout.calendar_tab);
	this.mView = (CalendarView) findViewById(R.id.calendar);
	// mView.setOnCellTouchListener(this.onTouch(cell));
	tv = (TextView) findViewById(R.id.calendar_lable);
	tv.setText(mView.getMonthName() + " " + mView.getYear());
	// ------------------------------------------------------------------
	// adding the listeners for the buttons which will scroll to the next
	// and previous months
	this.button_next = (Button) findViewById(R.id.calendar_button_next);
	this.button_next.setOnClickListener(new OnClickListener() {
	    public void onClick(View v) {
		Calendar_Activity.this.mView.nextMonth();
		tv.setText(mView.getMonthName() + " " + mView.getYear());
	    }
	});
	/* ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
	this.button_today = (Button) findViewById(R.id.calendar_button_today);
	this.button_today.setOnClickListener(new OnClickListener() {
	    public void onClick(View v) {
		mView.goToday();
		tv.setText(mView.getMonthName() + " " + mView.getYear());
	    }
	});
	/* ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
	this.button_prev = (Button) findViewById(R.id.calendar_button_prev);
	this.button_prev.setOnClickListener(new OnClickListener() {
	    public void onClick(View v) {
		mView.previousMonth();
		tv.setText(mView.getMonthName() + " " + mView.getYear());
	    }
	});
	// ------------------------------------------------------------------
    }

    // -------------------------------------------------------------------------------------------------
    /**
     * Method onTouch.
     * 
     * @param cell
     *            Cell
     * @see caao.com.tabs.calendar.CalendarView$OnCellTouchListener#onTouch(Cell)
     */
    public void onTouch(Cell cell) {
	Intent intent = getIntent();
	String action = intent.getAction();
	if (action.equals(Intent.ACTION_PICK)
		|| action.equals(Intent.ACTION_GET_CONTENT)) {
	    Intent ret = new Intent();
	    ret.putExtra("year", mView.getYear());
	    ret.putExtra("month", mView.getMonth());
	    ret.putExtra("day", cell.getDayOfMonth());
	    this.setResult(RESULT_OK, ret);
	    finish();
	    return;
	}
	int day = cell.getDayOfMonth();
	if (mView.firstDay(day))
	    mView.previousMonth();
	else if (mView.lastDay(day))
	    mView.nextMonth();
	else
	    return;
    }

    /**
     * Scrolls the calendar to specified month and year
     */
    public void scrollToDate(int pMonth, int pYear) {
	// TODO: add code for scrolling the calendar
	mView.nextMonth();
    }

    /**
     * returns current date in calendar view
     */
    @SuppressWarnings("unused")
    private void getCurrentDate() {
	Calendar CurCalDate = mView.getDate();
    }

}
