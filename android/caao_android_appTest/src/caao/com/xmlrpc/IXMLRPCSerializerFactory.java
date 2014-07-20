package caao.com.xmlrpc;


/**
 * The class <code>IXMLRPCSerializerFactory</code> implements static methods that return instances of the class <code>{@link IXMLRPCSerializer}</code>.
 *
 * @author zafar.khaydarov
 * @version $Revision: 1.1 $
 * @generatedBy CodePro at 5/1/11 6:15 PM
 */
public class IXMLRPCSerializerFactory {
    /**
     * Prevent creation of instances of this class.
     *
     * @generatedBy CodePro at 5/1/11 6:15 PM
     */
    private IXMLRPCSerializerFactory() {
    }


    /**
     * Create an instance of the class <code>{@link IXMLRPCSerializer}</code>.
     *
     * @generatedBy CodePro at 5/1/11 6:15 PM
     */
    public static IXMLRPCSerializer createIXMLRPCSerializer() {
        return new XMLRPCSerializer();
    }
}