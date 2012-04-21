package fi.uef.caao;

import org.junit.runner.JUnitCore;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * The class <code>TestAll</code> builds a suite that can be used to run all
 * of the tests within its package as well as within any subpackages of its
 * package.
 *
 * @generatedBy CodePro at 5/10/11 1:03 PM
 * @author zafar.khaydarov
 * @version $Revision: 1.3 $
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({
	CaaoServerCoreTest.class,
	ActivatorTest.class,
	CaaoUserUtilsTest.class,
})
public class TestAll {

    /**
     * Launch the test.
     *
     * @param args the command line arguments
     *
     * @generatedBy CodePro at 5/10/11 1:03 PM
     */
    public static void main(String[] args) {
    	JUnitCore.runClasses(new Class[] { TestAll.class });
    }
}
