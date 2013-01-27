package tabs.calendar;

import org.junit.runner.JUnitCore;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * The class <code>TestAll</code> builds a suite that can be used to run all
 * of the tests within its package as well as within any subpackages of its
 * package.
 *
 * @generatedBy CodePro at 5/1/11 6:24 PM
 * @author zafar.khaydarov
 * @version $Revision: 1.1 $
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({
	CalendarActivityFactoryTest.class,
	CellFactoryTest.class,
	CalendarViewFactoryTest.class,
})
public class TestAll {

    /**
     * Launch the test.
     *
     * @param args the command line arguments
     *
     * @generatedBy CodePro at 5/1/11 6:24 PM
     */
    public static void main(String[] args) {
    	JUnitCore.runClasses(new Class[] { TestAll.class });
    }
}
