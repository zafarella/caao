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

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;

/**
 * @author zafar.khaydarov
 * @version $Revision: 1.5 $
 */
public class Cell {
	/**
	 * Field TAG. (value is ""Cell"")
	 */
	@SuppressWarnings("unused")
	private static final String TAG = "Cell";
	/**
	 * Field mBound.
	 */
	protected Rect mBound = null;
	/**
	 * Field mDayOfMonth.
	 */
	protected int mDayOfMonth = 1; // from 1 to 31
	/**
	 * Field mPaint.
	 */
	protected Paint mPaint = new Paint(Paint.SUBPIXEL_TEXT_FLAG
			| Paint.ANTI_ALIAS_FLAG);
	/**
	 * Field dy.
	 */
	/**
	 * Field dx.
	 */
	int dx, dy;

	/**
	 * Constructor for Cell.
	 * 
	 * @param dayOfMon
	 *            int
	 * @param rect
	 *            Rect
	 * @param textSize
	 *            float
	 * @param bold
	 *            boolean
	 */
	public Cell(int dayOfMon, Rect rect, float textSize, boolean bold) {
		this.mDayOfMonth = dayOfMon;
		this.mBound = rect;
		this.mPaint.setTextSize(textSize/* 26f */);
		this.mPaint.setColor(Color.BLACK);
		if (bold)
			this.mPaint.setFakeBoldText(true);

		this.dx = (int) this.mPaint.measureText(String
				.valueOf(this.mDayOfMonth)) / 2;
		this.dy = (int) (-this.mPaint.ascent() + this.mPaint.descent()) / 2;
	}

	/**
	 * Constructor for Cell.
	 * 
	 * @param dayOfMon
	 *            int
	 * @param rect
	 *            Rect
	 * @param textSize
	 *            float
	 */
	public Cell(int dayOfMon, Rect rect, float textSize) {
		this(dayOfMon, rect, textSize, false);
	}

	/**
	 * Method draw.
	 * 
	 * @param canvas
	 *            Canvas
	 */
	protected void draw(Canvas canvas) {
		canvas.drawText(String.valueOf(this.mDayOfMonth), this.mBound.centerX()
				- this.dx, this.mBound.centerY() + this.dy, this.mPaint);
	}

	/**
	 * Method getDayOfMonth.
	 * 
	 * @return int
	 */
	public int getDayOfMonth() {
		return this.mDayOfMonth;
	}

	/**
	 * Method hitTest.
	 * 
	 * @param x
	 *            int
	 * @param y
	 *            int
	 * 
	 * @return boolean
	 */
	public boolean hitTest(int x, int y) {
		return this.mBound.contains(x, y);
	}

	/**
	 * Method getBound.
	 * 
	 * @return Rect
	 */
	public Rect getBound() {
		return this.mBound;
	}

	/**
	 * Method toString.
	 * 
	 * @return String
	 */
	public String toString() {
		return String.valueOf(this.mDayOfMonth) + "(" + this.mBound.toString()
				+ ")";
	}

}
