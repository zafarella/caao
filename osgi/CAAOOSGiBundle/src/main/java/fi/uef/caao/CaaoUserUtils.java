/* Author: zafar.khaydarov 
 * Date: 2011 Apr 17, 2011 6:41:24 PM
 * Zafar.Khaydarov @cs.joensuu.fi
 * */
package fi.uef.caao;

/**
 * Purpose: provide util to validate/update user and provide server information.
 *
 * @author zafar.khaydarov
 * @version $Revision: 1.5 $
 */
public class CaaoUserUtils {

    protected static String SERVER_VERSION = "1.0";

    /**
     * Checks the uniqueness of the user name (e-mail in database)
     *
     * @param pUserName String
     * @return boolean true says that everything went well and false otherwise
     */
    public boolean isUserNameIsUnique(String pUserName) {

        //TODO:check username uniqueness
        return true;
    }

    /**
     * updates the password of the specified user.
     *
     * @param pUsername    String - the user name for which the password will be updated.
     * @param pNewPassword String
     * @return boolean true says that everything went well and false otherwise
     */
    public boolean updateUserPassword(String pUsername, String pNewPassword) {
        return true;
    }

    // TODO: the rest of the methods

    /**
     * Returns short capabilities of the server side
     *
     * @return String
     */
    public String getServerInfo() {
        return SERVER_VERSION;
    }

    @Override
    public String toString() {
        return this.getClass().getName();
    }
}
