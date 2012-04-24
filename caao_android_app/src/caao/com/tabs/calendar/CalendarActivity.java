/*
 * Copyright (C) 2011 Chris Gao <chris@exina.net>
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package caao.com.tabs.calendar;

import caao.com.R;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.format.DateUtils;
import android.widget.TextView;
import android.widget.Toast;

/**
 * @author zafar.khaydarov
 * @version $Revision: 1.6 $
 */
public class CalendarActivity extends Activity implements
	CalendarView.OnCellTouchListener
    {
	/**
	 * Field MIME_TYPE.
	 * (value is ""vnd.android.cursor.dir/vnd.caao.com.tabs.calendar.date"")
	 */
	public static final String MIME_TYPE = "vnd.android.cursor.dir/vnd.caao.com.tabs.calendar.date";
	/**
	 * Field mView.
	 */
	CalendarView	       mView     = null;
	/**
	 * Field mHit.
	 */
	TextView		   mHit;
	/**
	 * Field mHandler.
	 */
	Handler		    mHandler  = new Handler();

	/** Called when the activity is first created. * @param savedInstanceState Bundle
	 * @param savedInstanceState Bundle
	 * @param savedInstanceState Bundle
	 * @param savedInstanceState Bundle
	 * @param savedInstanceState Bundle
	 * @param savedInstanceState Bundle
	 */
	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    // setContentView(R.layout.main);
	    this.mView = (CalendarView) findViewById(R.id.calendar);
	    this.mView.setOnCellTouchListener(this);

	    // if(getIntent().getAction().equals(Intent.ACTION_PICK))
	    // findViewById(R.id.hit).setVisibility(View.INVISIBLE);
	}

	/**
	 * Method onTouch.
	 * @param cell Cell
	
	 * @see caao.com.tabs.calendar.CalendarView$OnCellTouchListener#onTouch(Cell) */
	public void onTouch(Cell cell) {
	    Intent intent = getIntent();
	    String action = intent.getAction();
	    if (action.equals(Intent.ACTION_PICK)
		    || action.equals(Intent.ACTION_GET_CONTENT)) {
		Intent ret = new Intent();
		ret.putExtra("year", this.mView.getYear());
		ret.putExtra("month", this.mView.getMonth());
		ret.putExtra("day", cell.getDayOfMonth());
		this.setResult(RESULT_OK, ret);
		finish();
		return;
	    }
	    int day = cell.getDayOfMonth();
	    if (this.mView.firstDay(day)) this.mView.previousMonth();
	    else if (this.mView.lastDay(day)) this.mView.nextMonth();
	    else
		return;

	    this.mHandler.post(new Runnable()
		{
		    public void run() {
			Toast.makeText(
				CalendarActivity.this,
				DateUtils.getMonthString(CalendarActivity.this.mView.getMonth(),
					DateUtils.LENGTH_LONG)
					+ " " + CalendarActivity.this.mView.getYear(),
				Toast.LENGTH_SHORT).show();
		    }
		});
	}

    }