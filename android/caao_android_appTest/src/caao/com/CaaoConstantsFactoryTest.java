package caao.com;

import org.junit.*;
import static org.junit.Assert.*;

/**
 * The class <code>CaaoConstantsFactoryTest</code> contains tests for the class <code>{@link CaaoConstantsFactory}</code>.
 *
 * @generatedBy CodePro at 5/1/11 6:24 PM
 * @author zafar.khaydarov
 * @version $Revision: 1.1 $
 */
public class CaaoConstantsFactoryTest {
	/**
	 * Run the CaaoConstants createCaaoConstants() method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 5/1/11 6:24 PM
	 */
	@Test
	public void testCreateCaaoConstants_1()
		throws Exception {

		CaaoConstants result = CaaoConstantsFactory.createCaaoConstants();

		// add additional test code here
		assertNotNull(result);
	}

	/**
	 * Perform pre-test initialization.
	 *
	 * @throws Exception
	 *         if the initialization fails for some reason
	 *
	 * @generatedBy CodePro at 5/1/11 6:24 PM
	 */
	@Before
	public void setUp()
		throws Exception {
		// add additional set up code here
	}

	/**
	 * Perform post-test clean-up.
	 *
	 * @throws Exception
	 *         if the clean-up fails for some reason
	 *
	 * @generatedBy CodePro at 5/1/11 6:24 PM
	 */
	@After
	public void tearDown()
		throws Exception {
		// Add additional tear down code here
	}

	/**
	 * Launch the test.
	 *
	 * @param args the command line arguments
	 *
	 * @generatedBy CodePro at 5/1/11 6:24 PM
	 */
	public static void main(String[] args) {
		new org.junit.runner.JUnitCore().run(CaaoConstantsFactoryTest.class);
	}
}