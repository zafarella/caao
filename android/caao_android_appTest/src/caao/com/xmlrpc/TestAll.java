package caao.com.xmlrpc;

import org.junit.runner.JUnitCore;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * The class <code>TestAll</code> builds a suite that can be used to run all
 * of the tests within its package as well as within any subpackages of its
 * package.
 *
 * @author zafar.khaydarov
 * @version $Revision: 1.1 $
 * @generatedBy CodePro at 5/1/11 6:24 PM
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({
        XMLRPCSerializableFactoryTest.class,
        XMLRPCCommonFactoryTest.class,
        XMLRPCFaultFactoryTest.class,
        XMLRPCClientFactoryTest.class,
        XMLRPCServerFactoryTest.class,
        Base64CoderFactoryTest.class,
        IXMLRPCSerializerFactoryTest.class,
        XMLRPCSerializerFactoryTest.class,
        MethodCallFactoryTest.class,
        XMLRPCExceptionFactoryTest.class,
        TagFactoryTest.class,
})
public class TestAll {

    /**
     * Launch the test.
     *
     * @param args the command line arguments
     * @generatedBy CodePro at 5/1/11 6:24 PM
     */
    public static void main(String[] args) {
        JUnitCore.runClasses(new Class[]{TestAll.class});
    }
}
