package xmlrpc;



/**
 * The class <code>XMLRPCSerializerFactory</code> implements static methods that return instances of the class <code>{@link XMLRPCSerializer}</code>.
 *
 * @generatedBy CodePro at 5/1/11 6:15 PM
 * @author zafar.khaydarov
 * @version $Revision: 1.1 $
 */
public class XMLRPCSerializerFactory
 {
	/**
	 * Prevent creation of instances of this class.
	 *
	 * @generatedBy CodePro at 5/1/11 6:15 PM
	 */
	private XMLRPCSerializerFactory() {
	}


	/**
	 * Create an instance of the class <code>{@link XMLRPCSerializer}</code>.
	 *
	 * @generatedBy CodePro at 5/1/11 6:15 PM
	 */
	public static XMLRPCSerializer createXMLRPCSerializer() {
		return new XMLRPCSerializer();
	}
}