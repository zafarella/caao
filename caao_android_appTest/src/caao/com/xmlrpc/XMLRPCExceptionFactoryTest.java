package caao.com.xmlrpc;

import org.junit.*;
import static org.junit.Assert.*;

/**
 * The class <code>XMLRPCExceptionFactoryTest</code> contains tests for the class <code>{@link XMLRPCExceptionFactory}</code>.
 *
 * @generatedBy CodePro at 5/1/11 6:24 PM
 * @author zafar.khaydarov
 * @version $Revision: 1.1 $
 */
public class XMLRPCExceptionFactoryTest {
	/**
	 * Run the XMLRPCException createXMLRPCException() method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 5/1/11 6:24 PM
	 */
	@Test
	public void testCreateXMLRPCException_1()
		throws Exception {

		XMLRPCException result = XMLRPCExceptionFactory.createXMLRPCException();

		// add additional test code here
		assertNotNull(result);
		assertEquals(null, result.getCause());
		assertEquals("caao.com.xmlrpc.XMLRPCException: ", result.toString());
		assertEquals("", result.getMessage());
		assertEquals("", result.getLocalizedMessage());
	}

	/**
	 * Run the XMLRPCException createXMLRPCException2() method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 5/1/11 6:24 PM
	 */
	@Test
	public void testCreateXMLRPCException2_1()
		throws Exception {

		XMLRPCException result = XMLRPCExceptionFactory.createXMLRPCException2();

		// add additional test code here
		assertNotNull(result);
		assertEquals("caao.com.xmlrpc.XMLRPCException: java.lang.Exception: ", result.toString());
		assertEquals("java.lang.Exception: ", result.getMessage());
		assertEquals("java.lang.Exception: ", result.getLocalizedMessage());
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
		new org.junit.runner.JUnitCore().run(XMLRPCExceptionFactoryTest.class);
	}
}