package caao.com.xmlrpc;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * The class <code>Base64CoderFactoryTest</code> contains tests for the class <code>{@link Base64CoderFactory}</code>.
 *
 * @author zafar.khaydarov
 * @version $Revision: 1.1 $
 * @generatedBy CodePro at 5/1/11 6:24 PM
 */
public class Base64CoderFactoryTest {
    /**
     * Run the Base64Coder createBase64Coder() method test.
     *
     * @throws Exception
     * @generatedBy CodePro at 5/1/11 6:24 PM
     */
    @Test
    public void testCreateBase64Coder_1()
            throws Exception {

        Base64Coder result = Base64CoderFactory.createBase64Coder();

        // add additional test code here
        assertEquals(null, result);
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
        new org.junit.runner.JUnitCore().run(Base64CoderFactoryTest.class);
    }
}