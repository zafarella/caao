package caao.com.xmlrpc;



/**
 * The class <code>XMLRPCExceptionFactory</code> implements static methods that return instances of the class <code>{@link XMLRPCException}</code>.
 *
 * @generatedBy CodePro at 5/1/11 6:15 PM
 * @author zafar.khaydarov
 * @version $Revision: 1.1 $
 */
public class XMLRPCExceptionFactory
 {
	/**
	 * Prevent creation of instances of this class.
	 *
	 * @generatedBy CodePro at 5/1/11 6:15 PM
	 */
	private XMLRPCExceptionFactory() {
	}


	/**
	 * Create an instance of the class <code>{@link XMLRPCException}</code>.
	 *
	 * @generatedBy CodePro at 5/1/11 6:15 PM
	 */
	public static XMLRPCException createXMLRPCException() {
		return new XMLRPCException("");
	}


	/**
	 * Create an instance of the class <code>{@link XMLRPCException}</code>.
	 *
	 * @generatedBy CodePro at 5/1/11 6:15 PM
	 */
	public static XMLRPCException createXMLRPCException2() {
		return new XMLRPCException(new Exception(""));
	}
}