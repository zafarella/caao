package caao.com.service;


/**
 * The class <code>caao_serviceFactory</code> implements static methods that return instances of the class <code>{@link caao_service}</code>.
 *
 * @author zafar.khaydarov
 * @version $Revision: 1.3 $
 * @generatedBy CodePro at 5/10/11 3:42 PM
 */
public class caao_serviceFactory {
    /**
     * Prevent creation of instances of this class.
     *
     * @generatedBy CodePro at 5/10/11 3:42 PM
     */
    private caao_serviceFactory() {
    }


    /**
     * Create an instance of the class <code>{@link caao_service}</code>.
     *
     * @return caao_service
     * @generatedBy CodePro at 5/10/11 3:42 PM
     */
    public static caao_service createcaao_service() {
        return new caao_service();
    }
}