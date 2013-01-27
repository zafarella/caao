package xmlrpc;

import org.junit.*;
import static org.junit.Assert.*;

/**
 * The class <code>XMLRPCCommonFactoryTest</code> contains tests for the class <code>{@link XMLRPCCommonFactory}</code>.
 *
 * @generatedBy CodePro at 5/1/11 6:24 PM
 * @author zafar.khaydarov
 * @version $Revision: 1.1 $
 */
public class XMLRPCCommonFactoryTest {
	/**
	 * Run the XMLRPCCommon createXMLRPCCommon() method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 5/1/11 6:24 PM
	 */
	@Test
	public void testCreateXMLRPCCommon_1()
		throws Exception {

		XMLRPCCommon result = XMLRPCCommonFactory.createXMLRPCCommon();

		// add additional test code here
		// An unexpected exception was thrown in user code while executing this test:
		//    java.lang.RuntimeException: Stub!
		//       at android.util.Xml.newSerializer(Xml.java:16)
		//       at caao.com.xmlrpc.XMLRPCCommon.<init>(XMLRPCCommon.java:28)
		//       at caao.com.xmlrpc.XMLRPCCommonFactory.createXMLRPCCommon(XMLRPCCommonFactory.java:29)
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
		new org.junit.runner.JUnitCore().run(XMLRPCCommonFactoryTest.class);
	}
}