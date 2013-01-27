package caao.com;

import android.accounts.AccountAuthenticatorActivity;
import android.app.Activity;
import android.app.ActivityGroup;
import android.content.Context;


/**
 * The class <code>MyToastFactory</code> implements static methods that return instances of the class <code>{@link MyToast}</code>.
 *
 * @generatedBy CodePro at 5/10/11 3:41 PM
 * @author zafar.khaydarov
 * @version $Revision: 1.3 $
 */
public class MyToastFactory
 {
	/**
	 * Prevent creation of instances of this class.
	 *
	 * @generatedBy CodePro at 5/10/11 3:41 PM
	 */
	private MyToastFactory() {
	}


	/**
	 * Create an instance of the class <code>{@link MyToast}</code>.
	 *
	 * @generatedBy CodePro at 5/10/11 3:41 PM
	 * @return MyToast
	 */
	public static MyToast createMyToast() {
		return new MyToast(new AccountAuthenticatorActivity(), "");
	}


	/**
	 * Create an instance of the class <code>{@link MyToast}</code>.
	 *
	 * @generatedBy CodePro at 5/10/11 3:41 PM
	 * @return MyToast
	 */
	public static MyToast createMyToast2() {
		return new MyToast(new AccountAuthenticatorActivity(), "", false);
	}


	/**
	 * Create an instance of the class <code>{@link MyToast}</code>.
	 *
	 * @generatedBy CodePro at 5/10/11 3:41 PM
	 * @return MyToast
	 */
	public static MyToast createMyToast3() {
		return new MyToast(new Activity(), "0123456789");
	}


	/**
	 * Create an instance of the class <code>{@link MyToast}</code>.
	 *
	 * @generatedBy CodePro at 5/10/11 3:41 PM
	 * @return MyToast
	 */
	public static MyToast createMyToast4() {
		return new MyToast(new Activity(), "0123456789", true);
	}


	/**
	 * Create an instance of the class <code>{@link MyToast}</code>.
	 *
	 * @generatedBy CodePro at 5/10/11 3:41 PM
	 * @return MyToast
	 */
	public static MyToast createMyToast5() {
		return new MyToast(new ActivityGroup(), "An??t-1.0.txt", true);
	}
}