/*
 * Copyright (C) 2011 Chris Gao <chris@exina.net> Licensed under the Apache License, Version 2.0 (the "License"); you
 * may not use this file except in compliance with the License. You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0 Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND,
 * either express or implied. See the License for the specific language governing permissions and limitations under the
 * License.
 */

package caao.com.tabs.calendar;

import java.util.Calendar;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.MonthDisplayHelper;
import android.view.MotionEvent;
import android.widget.ImageView;
import caao.com.R;

/**
 * @author zafar.khaydarov
 * @version $Revision: 1.7 $
 */
public class CalendarView extends ImageView
    {
        /**
         * Field WEEK_TOP_MARGIN.
         */
        private static int          WEEK_TOP_MARGIN      = 74;
        /**
         * Field WEEK_LEFT_MARGIN.
         */
        private static int          WEEK_LEFT_MARGIN     = 40;
        /**
         * Field CELL_WIDTH.
         */
        private static int          CELL_WIDTH           = 58;
        /**
         * Field CELL_HEIGH.
         */
        private static int          CELL_HEIGH           = 53;
        /**
         * Field CELL_MARGIN_TOP.
         */
        private static int          CELL_MARGIN_TOP      = 92;
        /**
         * Field CELL_MARGIN_LEFT.
         */
        private static int          CELL_MARGIN_LEFT     = 39;
        /**
         * Field CELL_TEXT_SIZE.
         */
        private static float        CELL_TEXT_SIZE;

        /**
         * Field TAG. (value is ""CalendarView"")
         */
        @SuppressWarnings("unused")
        private static final String TAG                  = "CalendarView";
        /**
         * Field mRightNow.
         */
        private Calendar            mRightNow            = null;
        /**
         * Field mWeekTitle.
         */
        private Drawable            mWeekTitle           = null;
        /**
         * Field mToday.
         */
        private Cell                mToday               = null;
        /**
         * Field mCells.
         */
        private Cell[][]            mCells               = new Cell[6][7];
        /**
         * Field mOnCellTouchListener.
         */
        private OnCellTouchListener mOnCellTouchListener = null;
        /**
         * Field mHelper.
         */
        MonthDisplayHelper          mHelper;
        /**
         * Field mDecoration.
         */
        Drawable                    mDecoration          = null;

        /**
         * @author zafar.khaydarov
         */
        public interface OnCellTouchListener
            {
                /**
                 * Method onTouch.
                 * 
                 * @param cell
                 *            Cell
                 */
                public void onTouch(Cell cell);
            }

        /**
         * Constructor for CalendarView.
         * 
         * @param context
         *            Context
         */
        public CalendarView(Context context)
        {
            this(context, null);
        }

        /**
         * Constructor for CalendarView.
         * 
         * @param context
         *            Context
         * @param attrs
         *            AttributeSet
         */
        public CalendarView(Context context, AttributeSet attrs)
        {
            this(context, attrs, 0);
        }

        /**
         * Constructor for CalendarView.
         * 
         * @param context
         *            Context
         * @param attrs
         *            AttributeSet
         * @param defStyle
         *            int
         */
        public CalendarView(Context context, AttributeSet attrs, int defStyle)
        {
            super(context, attrs, defStyle);
            mDecoration = context.getResources().getDrawable(R.drawable.typeb_calendar_today);
            initCalendarView();
        }

        /**
         * Method initCalendarView.
         */
        private void initCalendarView()
        {
            mRightNow = Calendar.getInstance();
            // prepare static vars
            Resources res = getResources();
            WEEK_TOP_MARGIN = (int) res.getDimension(R.dimen.week_top_margin);
            WEEK_LEFT_MARGIN = (int) res.getDimension(R.dimen.week_left_margin);

            CELL_WIDTH = (int) res.getDimension(R.dimen.cell_width);
            CELL_HEIGH = (int) res.getDimension(R.dimen.cell_heigh);
            CELL_MARGIN_TOP = (int) res.getDimension(R.dimen.cell_margin_top);
            CELL_MARGIN_LEFT = (int) res.getDimension(R.dimen.cell_margin_left);
            CELL_TEXT_SIZE = res.getDimension(R.dimen.cell_text_size);
            // set background
            setImageResource(R.drawable.background);
            mWeekTitle = res.getDrawable(R.drawable.calendar_week);
            mWeekTitle.setBounds(WEEK_LEFT_MARGIN, WEEK_TOP_MARGIN, WEEK_LEFT_MARGIN + mWeekTitle.getMinimumWidth(),
                    WEEK_TOP_MARGIN + mWeekTitle.getMinimumHeight());

            mHelper = new MonthDisplayHelper(mRightNow.get(Calendar.YEAR), mRightNow.get(Calendar.MONTH));
            initCells();
        }

        /**
         * Method initCells.
         */
        private void initCells()
        {
            /**
             * @author zafar.khaydarov
             */
            class _calendar
                {
                    /**
                     * Field day.
                     */
                    public int     day;
                    /**
                     * Field thisMonth.
                     */
                    public boolean thisMonth;

                    /**
                     * Constructor for _calendar.
                     * 
                     * @param d
                     *            int
                     * @param b
                     *            boolean
                     */
                    public _calendar(int d, boolean b)
                    {
                        day = d;
                        thisMonth = b;
                    }

                    /**
                     * Constructor for _calendar.
                     * 
                     * @param d
                     *            int
                     */
                    public _calendar(int d)
                    {
                        this(d, false);
                    }
                }

            _calendar tmp[][] = new _calendar[6][7];

            for (int i = 0; i < tmp.length; i++) {
                int n[] = mHelper.getDigitsForRow(i);
                for (int d = 0; d < n.length; d++) {
                    if (mHelper.isWithinCurrentMonth(i, d)) tmp[i][d] = new _calendar(n[d], true);
                    else
                        tmp[i][d] = new _calendar(n[d]);

                }
            }

            Calendar today = Calendar.getInstance();
            int thisDay = 0;
            mToday = null;
            if (mHelper.getYear() == today.get(Calendar.YEAR) && mHelper.getMonth() == today.get(Calendar.MONTH)) {
                thisDay = today.get(Calendar.DAY_OF_MONTH);
            }
            // build cells
            Rect Bound = new Rect(CELL_MARGIN_LEFT, CELL_MARGIN_TOP, CELL_WIDTH + CELL_MARGIN_LEFT, CELL_HEIGH
                    + CELL_MARGIN_TOP);
            for (int week = 0; week < mCells.length; week++) {
                for (int day = 0; day < mCells[week].length; day++) {
                    if (tmp[week][day].thisMonth) {
                        if (day == 0 || day == 6) mCells[week][day] = new RedCell(tmp[week][day].day, new Rect(Bound),
                                CELL_TEXT_SIZE);
                        else
                            mCells[week][day] = new Cell(tmp[week][day].day, new Rect(Bound), CELL_TEXT_SIZE);
                    }
                    else
                        mCells[week][day] = new GrayCell(tmp[week][day].day, new Rect(Bound), CELL_TEXT_SIZE);

                    Bound.offset(CELL_WIDTH, 0); // move to next column

                    // get today
                    if (tmp[week][day].day == thisDay) {
                        mToday = mCells[week][day];
                        mDecoration.setBounds(mToday.getBound());
                    }
                }
                Bound.offset(0, CELL_HEIGH); // move to next row and first
                // column
                Bound.left = CELL_MARGIN_LEFT;
                Bound.right = CELL_MARGIN_LEFT + CELL_WIDTH;
            }
        }

        /**
         * Method setTimeInMillis.
         * 
         * @param milliseconds
         *            long
         */
        public void setTimeInMillis(long milliseconds)
        {
            mRightNow.setTimeInMillis(milliseconds);
            initCells();
            this.invalidate();
        }

        /**
         * Method getYear.
         * 
        
         * @return int */
        public int getYear()
        {
            return mHelper.getYear();
        }

        /**
         * Method getMonth.
         * 
        
         * @return int */
        public int getMonth()
        {
            return mHelper.getMonth();
        }

        /**
         * Method getMonthName.
         * @return String
         */
        public String getMonthName()
        {

            switch (mHelper.getMonth())
            {
                case 0:
                    return "Jan";
                case 1:
                    return "Feb";
                case 2:
                    return "Mar";
                case 3:
                    return "Apr";
                case 4:
                    return "May";
                case 5:
                    return "Jun";
                case 6:
                    return "Jul";
                case 7:
                    return "Aug";
                case 8:
                    return "Sep";
                case 9:
                    return "Oct";
                case 10:
                    return "Nov";
                case 11:
                    return "Dec";
                default:
                    return null;
            }

        }

        /**
         * Method nextMonth.
         */
        public void nextMonth()
        {
            mHelper.nextMonth();
            initCells();
            invalidate();
        }

        /**
         * Method previousMonth.
         */
        public void previousMonth()
        {
            mHelper.previousMonth();
            initCells();
            invalidate();
        }

        /**
         * Method firstDay.
         * 
         * @param day
         *            int
        
         * @return boolean */
        public boolean firstDay(int day)
        {
            return day == 1;
        }

        /**
         * Method lastDay.
         * 
         * @param day
         *            int
        
         * @return boolean */
        public boolean lastDay(int day)
        {
            return mHelper.getNumberOfDaysInMonth() == day;
        }

        /**
         * Method goToday.
         */
        public void goToday()
        {
            Calendar cal = Calendar.getInstance();
            mHelper = new MonthDisplayHelper(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH));
            initCells();
            invalidate();
        }

        /**
         * Method getDate.
         * 
        
         * @return Calendar */
        public Calendar getDate()
        {
            return mRightNow;
        }

        /**
         * Method onTouchEvent.
         * 
         * @param event
         *            MotionEvent
        
         * @return boolean */
        @Override
        public boolean onTouchEvent(MotionEvent event)
        {
            if (mOnCellTouchListener != null) {
                for (Cell[] week : mCells) {
                    for (Cell day : week) {
                        if (day.hitTest((int) event.getX(), (int) event.getY())) {
                            mOnCellTouchListener.onTouch(day);
                        }
                    }
                }
            }
            return super.onTouchEvent(event);
        }

        /**
         * Method setOnCellTouchListener.
         * 
         * @param p
         *            OnCellTouchListener
         */
        public void setOnCellTouchListener(OnCellTouchListener p)
        {
            mOnCellTouchListener = p;
        }

        /**
         * Method onDraw.
         * 
         * @param canvas
         *            Canvas
         */
        @Override
        protected void onDraw(Canvas canvas)
        {
            // draw background
            super.onDraw(canvas);
            mWeekTitle.draw(canvas);

            // draw cells
            for (Cell[] week : mCells) {
                for (Cell day : week) {
                    day.draw(canvas);
                }
            }

            // draw today
            if (mDecoration != null && mToday != null) {
                mDecoration.draw(canvas);
            }
        }

        /**
         * @author zafar.khaydarov
         */
        private class GrayCell extends Cell
            {
                /**
                 * Constructor for GrayCell.
                 * 
                 * @param dayOfMon
                 *            int
                 * @param rect
                 *            Rect
                 * @param s
                 *            float
                 */
                public GrayCell(int dayOfMon, Rect rect, float s)
                {
                    super(dayOfMon, rect, s);
                    mPaint.setColor(Color.LTGRAY);
                }
            }

        /**
         * @author zafar.khaydarov
         */
        private class RedCell extends Cell
            {
                /**
                 * Constructor for RedCell.
                 * 
                 * @param dayOfMon
                 *            int
                 * @param rect
                 *            Rect
                 * @param s
                 *            float
                 */
                public RedCell(int dayOfMon, Rect rect, float s)
                {
                    super(dayOfMon, rect, s);
                    mPaint.setColor(0xdddd0000);
                }

            }

    }
