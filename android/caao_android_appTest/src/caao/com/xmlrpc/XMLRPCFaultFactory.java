package caao.com.xmlrpc;



/**
 * The class <code>XMLRPCFaultFactory</code> implements static methods that return instances of the class <code>{@link XMLRPCFault}</code>.
 *
 * @generatedBy CodePro at 5/1/11 6:15 PM
 * @author zafar.khaydarov
 * @version $Revision: 1.1 $
 */
public class XMLRPCFaultFactory
 {
	/**
	 * Prevent creation of instances of this class.
	 *
	 * @generatedBy CodePro at 5/1/11 6:15 PM
	 */
	private XMLRPCFaultFactory() {
	}


	/**
	 * Create an instance of the class <code>{@link XMLRPCFault}</code>.
	 *
	 * @generatedBy CodePro at 5/1/11 6:15 PM
	 */
	public static XMLRPCFault createXMLRPCFault() {
		return new XMLRPCFault("", 0);
	}


	/**
	 * Create an instance of the class <code>{@link XMLRPCFault}</code>.
	 *
	 * @generatedBy CodePro at 5/1/11 6:15 PM
	 */
	public static XMLRPCFault createXMLRPCFault2() {
		return new XMLRPCFault("0123456789", 1);
	}
}