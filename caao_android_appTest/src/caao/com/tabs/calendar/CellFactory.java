package caao.com.tabs.calendar;

import android.graphics.Rect;


/**
 * The class <code>CellFactory</code> implements static methods that return instances of the class <code>{@link Cell}</code>.
 *
 * @generatedBy CodePro at 5/1/11 6:15 PM
 * @author zafar.khaydarov
 * @version $Revision: 1.1 $
 */
public class CellFactory
 {
	/**
	 * Prevent creation of instances of this class.
	 *
	 * @generatedBy CodePro at 5/1/11 6:15 PM
	 */
	private CellFactory() {
	}


	/**
	 * Create an instance of the class <code>{@link Cell}</code>.
	 *
	 * @generatedBy CodePro at 5/1/11 6:15 PM
	 */
	public static Cell createCell() {
		return new Cell(-1, new Rect(0, 0, 0, 0), -0.5f, true);
	}


	/**
	 * Create an instance of the class <code>{@link Cell}</code>.
	 *
	 * @generatedBy CodePro at 5/1/11 6:15 PM
	 */
	public static Cell createCell2() {
		return new Cell(0, Rect.unflattenFromString(""), -1.0f);
	}


	/**
	 * Create an instance of the class <code>{@link Cell}</code>.
	 *
	 * @generatedBy CodePro at 5/1/11 6:15 PM
	 */
	public static Cell createCell3() {
		return new Cell(0, Rect.unflattenFromString(""), -1.0f, false);
	}


	/**
	 * Create an instance of the class <code>{@link Cell}</code>.
	 *
	 * @generatedBy CodePro at 5/1/11 6:15 PM
	 */
	public static Cell createCell4() {
		return new Cell(1, new Rect(), 0.0f);
	}


	/**
	 * Create an instance of the class <code>{@link Cell}</code>.
	 *
	 * @generatedBy CodePro at 5/1/11 6:15 PM
	 */
	public static Cell createCell5() {
		return new Cell(1, new Rect(), 0.0f, true);
	}


	/**
	 * Create an instance of the class <code>{@link Cell}</code>.
	 *
	 * @generatedBy CodePro at 5/1/11 6:15 PM
	 */
	public static Cell createCell6() {
		return new Cell(7, new Rect(-1, -1, -1, -1), 1.0f);
	}


	/**
	 * Create an instance of the class <code>{@link Cell}</code>.
	 *
	 * @generatedBy CodePro at 5/1/11 6:15 PM
	 */
	public static Cell createCell7() {
		return new Cell(7, new Rect(-1, -1, -1, -1), 1.0f, true);
	}
}