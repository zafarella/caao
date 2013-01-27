package caao.com.tabs.calendar;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.CalendarView;
import caao.com.Main_activityFactory;
import caao.com.Settings_activityFactory;
import caao.com.service.caao_serviceFactory;
import caao.com.settings_activities.Advanced_settingsFactory;

import static caao.com.Main_activityFactory.*;


/**
 * The class <code>CalendarViewFactory</code> implements static methods that return instances of the class <code>{@link CalendarView}</code>.
 *
 * @generatedBy CodePro at 5/1/11 6:15 PM
 * @author zafar.khaydarov
 * @version $Revision: 1.1 $
 */
public class CalendarViewFactory
 {
	/**
	 * Prevent creation of instances of this class.
	 *
	 * @generatedBy CodePro at 5/1/11 6:15 PM
	 */
	private CalendarViewFactory() {
	}


	/**
	 * Create an instance of the class <code>{@link CalendarView}</code>.
	 *
	 * @generatedBy CodePro at 5/1/11 6:15 PM
	 */
	public static CalendarView createCalendarView() {
		return new CalendarView(createMain_activity());
	}


	/**
	 * Create an instance of the class <code>{@link CalendarView}</code>.
	 *
	 * @generatedBy CodePro at 5/1/11 6:15 PM
	 */
	public static CalendarView createCalendarView2() {
		return new CalendarView(createMain_activity(), (AttributeSet) null);
	}


	/**
	 * Create an instance of the class <code>{@link CalendarView}</code>.
	 *
	 * @generatedBy CodePro at 5/1/11 6:15 PM
	 */
	public static CalendarView createCalendarView3() {
		return new CalendarView(createMain_activity(), (AttributeSet) null, 0);
	}


	/**
	 * Create an instance of the class <code>{@link CalendarView}</code>.
	 *
	 * @generatedBy CodePro at 5/1/11 6:15 PM
	 */
	public static CalendarView createCalendarView4() {
		return new CalendarView(Settings_activityFactory.createSettings_activity(), (AttributeSet) null);
	}


	/**
	 * Create an instance of the class <code>{@link CalendarView}</code>.
	 *
	 * @generatedBy CodePro at 5/1/11 6:15 PM
	 */
	public static CalendarView createCalendarView5() {
		return new CalendarView(caao_serviceFactory.createcaao_service(), (AttributeSet) null, 1);
	}


	/**
	 * Create an instance of the class <code>{@link CalendarView}</code>.
	 *
	 * @generatedBy CodePro at 5/1/11 6:15 PM
	 */
	public static CalendarView createCalendarView6() {
		return new CalendarView(Advanced_settingsFactory.createAdvanced_settings(), (AttributeSet) null, 7);
	}
}