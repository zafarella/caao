package caao.com.xmlrpc;

import org.junit.*;
import static org.junit.Assert.*;

/**
 * The class <code>XMLRPCServerFactoryTest</code> contains tests for the class <code>{@link XMLRPCServerFactory}</code>.
 *
 * @generatedBy CodePro at 5/1/11 6:24 PM
 * @author zafar.khaydarov
 * @version $Revision: 1.1 $
 */
public class XMLRPCServerFactoryTest {
	/**
	 * Run the XMLRPCServer createXMLRPCServer() method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 5/1/11 6:24 PM
	 */
	@Test
	public void testCreateXMLRPCServer_1()
		throws Exception {

		XMLRPCServer result = XMLRPCServerFactory.createXMLRPCServer();

		// add additional test code here
		// An unexpected exception was thrown in user code while executing this test:
		//    java.lang.RuntimeException: Stub!
		//       at android.util.Xml.newSerializer(Xml.java:16)
		//       at caao.com.xmlrpc.XMLRPCCommon.<init>(XMLRPCCommon.java:28)
		//       at caao.com.xmlrpc.XMLRPCServer.<init>(XMLRPCServer.java:44)
		//       at caao.com.xmlrpc.XMLRPCServerFactory.createXMLRPCServer(XMLRPCServerFactory.java:29)
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
		new org.junit.runner.JUnitCore().run(XMLRPCServerFactoryTest.class);
	}
}