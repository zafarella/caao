package xmlrpc;



/**
 * The class <code>MethodCallFactory</code> implements static methods that return instances of the class <code>{@link MethodCall}</code>.
 *
 * @generatedBy CodePro at 5/1/11 6:15 PM
 * @author zafar.khaydarov
 * @version $Revision: 1.1 $
 */
public class MethodCallFactory
 {
	/**
	 * Prevent creation of instances of this class.
	 *
	 * @generatedBy CodePro at 5/1/11 6:15 PM
	 */
	private MethodCallFactory() {
	}


	/**
	 * Create an instance of the class <code>{@link MethodCall}</code>.
	 *
	 * @generatedBy CodePro at 5/1/11 6:15 PM
	 */
	public static MethodCall createMethodCall() {
		return new MethodCall();
	}
}