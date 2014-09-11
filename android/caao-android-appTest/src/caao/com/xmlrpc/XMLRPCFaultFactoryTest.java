package caao.com.xmlrpc;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;

/**
 * The class <code>XMLRPCFaultFactoryTest</code> contains tests for the class <code>{@link XMLRPCFaultFactory}</code>.
 *
 * @author zafar.khaydarov
 * @version $Revision: 1.1 $
 * @generatedBy CodePro at 5/1/11 6:24 PM
 */
public class XMLRPCFaultFactoryTest {
    /**
     * Run the XMLRPCFault createXMLRPCFault() method test.
     *
     * @throws Exception
     * @generatedBy CodePro at 5/1/11 6:24 PM
     */
    @Test
    public void testCreateXMLRPCFault_1()
            throws Exception {

        XMLRPCFault result = XMLRPCFaultFactory.createXMLRPCFault();

        // add additional test code here
        assertNotNull(result);
        assertEquals("", result.getFaultString());
        assertEquals(0, result.getFaultCode());
        assertEquals(null, result.getCause());
        assertEquals("caao.com.xmlrpc.XMLRPCFault: XMLRPC Fault:  [code 0]", result.toString());
        assertEquals("XMLRPC Fault:  [code 0]", result.getMessage());
        assertEquals("XMLRPC Fault:  [code 0]", result.getLocalizedMessage());
    }

    /**
     * Run the XMLRPCFault createXMLRPCFault2() method test.
     *
     * @throws Exception
     * @generatedBy CodePro at 5/1/11 6:24 PM
     */
    @Test
    public void testCreateXMLRPCFault2_1()
            throws Exception {

        XMLRPCFault result = XMLRPCFaultFactory.createXMLRPCFault2();

        // add additional test code here
        assertNotNull(result);
        assertEquals("0123456789", result.getFaultString());
        assertEquals(1, result.getFaultCode());
        assertEquals(null, result.getCause());
        assertEquals("caao.com.xmlrpc.XMLRPCFault: XMLRPC Fault: 0123456789 [code 1]", result.toString());
        assertEquals("XMLRPC Fault: 0123456789 [code 1]", result.getMessage());
        assertEquals("XMLRPC Fault: 0123456789 [code 1]", result.getLocalizedMessage());
    }

    /**
     * Perform pre-test initialization.
     *
     * @throws Exception if the initialization fails for some reason
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
     * @throws Exception if the clean-up fails for some reason
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
     * @generatedBy CodePro at 5/1/11 6:24 PM
     */
    public static void main(String[] args) {
        new org.junit.runner.JUnitCore().run(XMLRPCFaultFactoryTest.class);
    }
}