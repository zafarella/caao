package caao.com.xmlrpc;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;


/**
 * The class <code>XMLRPCClientFactory</code> implements static methods that return instances of the class <code>{@link XMLRPCClient}</code>.
 *
 * @generatedBy CodePro at 5/1/11 6:15 PM
 * @author zafar.khaydarov
 * @version $Revision: 1.1 $
 */
public class XMLRPCClientFactory
 {
	/**
	 * Prevent creation of instances of this class.
	 *
	 * @generatedBy CodePro at 5/1/11 6:15 PM
	 */
	private XMLRPCClientFactory() {
	}


	/**
	 * Create an instance of the class <code>{@link XMLRPCClient}</code>.
	 *
	 * @generatedBy CodePro at 5/1/11 6:15 PM
	 */
	public static XMLRPCClient createXMLRPCClient() {
		return new XMLRPCClient("");
	}


	/**
	 * Create an instance of the class <code>{@link XMLRPCClient}</code>.
	 *
	 * @generatedBy CodePro at 5/1/11 6:15 PM
	 */
	public static XMLRPCClient createXMLRPCClient2() {
		return new XMLRPCClient("", "", "");
	}


	/**
	 * Create an instance of the class <code>{@link XMLRPCClient}</code>.
	 *
	 * @generatedBy CodePro at 5/1/11 6:15 PM
	 */
	public static XMLRPCClient createXMLRPCClient3() {
		return new XMLRPCClient("0123456789", "0123456789", "0123456789");
	}


	/**
	 * Create an instance of the class <code>{@link XMLRPCClient}</code>.
	 *
	 * @generatedBy CodePro at 5/1/11 6:15 PM
	 */
	public static XMLRPCClient createXMLRPCClient4() {
		return new XMLRPCClient("An??t-1.0.txt", "An??t-1.0.txt", "An??t-1.0.txt");
	}


	/**
	 * Create an instance of the class <code>{@link XMLRPCClient}</code>.
	 *
	 * @generatedBy CodePro at 5/1/11 6:15 PM
	 */
	public static XMLRPCClient createXMLRPCClient5() {
		return new XMLRPCClient(URI.create(""));
	}


	/**
	 * Create an instance of the class <code>{@link XMLRPCClient}</code>.
	 *
	 * @generatedBy CodePro at 5/1/11 6:15 PM
	 */
	public static XMLRPCClient createXMLRPCClient6() {
		return new XMLRPCClient(URI.create(""), "", "");
	}


	/**
	 * Create an instance of the class <code>{@link XMLRPCClient}</code>.
	 *
	 * @generatedBy CodePro at 5/1/11 6:15 PM
	 */
	public static XMLRPCClient createXMLRPCClient7()
		throws java.net.URISyntaxException {
		return new XMLRPCClient(new URI(""), "0123456789", "0123456789");
	}


	/**
	 * Create an instance of the class <code>{@link XMLRPCClient}</code>.
	 *
	 * @generatedBy CodePro at 5/1/11 6:15 PM
	 */
	public static XMLRPCClient createXMLRPCClient8()
		throws java.net.URISyntaxException {
		return new XMLRPCClient(new URI("", "", ""), "An??t-1.0.txt", "An??t-1.0.txt");
	}


	/**
	 * Create an instance of the class <code>{@link XMLRPCClient}</code>.
	 * @throws Exception 
	 *
	 * @generatedBy CodePro at 5/1/11 6:15 PM
	 */
	public static XMLRPCClient createXMLRPCClient9() throws Exception {
		return new XMLRPCClient(new URL("http://www.eclipse.org"));
	}


	/**
	 * Create an instance of the class <code>{@link XMLRPCClient}</code>.
	 * @throws Exception 
	 *
	 * @generatedBy CodePro at 5/1/11 6:15 PM
	 */
	public static XMLRPCClient createXMLRPCClient10() throws Exception {
		return new XMLRPCClient(new URL("http://www.eclipse.org"), "", "");
	}


	/**
	 * Create an instance of the class <code>{@link XMLRPCClient}</code>.
	 * @throws Exception 
	 *
	 * @generatedBy CodePro at 5/1/11 6:15 PM
	 */
	public static XMLRPCClient createXMLRPCClient11() throws Exception {
		return new XMLRPCClient(new URL("http://www.eclipse.org"), "An??t-1.0.txt", "An??t-1.0.txt");
	}


	/**
	 * Create an instance of the class <code>{@link XMLRPCClient}</code>.
	 *
	 * @generatedBy CodePro at 5/1/11 6:15 PM
	 */
	public static XMLRPCClient createXMLRPCClient12() {
		return new XMLRPCClient((URL) null, "0123456789", "0123456789");
	}
}