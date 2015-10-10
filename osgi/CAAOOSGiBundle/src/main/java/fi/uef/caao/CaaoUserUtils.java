/**
 * Author: zafar.khaydarov
 * Date: 2011 Apr 17, 2011 6:41:24 PM
 * Project: rpc_server_test
 * Zafar.Khaydarov @cs.joensuu.fi
 */

package fi.uef.caao;

import fi.uef.caao.services.user.CaaoUserUtilsService;

/**
 * The main purpose of the class is provide methods for registering new users
 * and updating existing user's information.
 *
 * @author zafar.khaydarov
 * @version $Revision: 1.5 $
 */
public class CaaoUserUtils implements CaaoUserUtilsService {
  /**
   * Checks the uniqueness of the user name (e-mail in database).
   *
   * @param username String
   * @return boolean true says that everything went well and false otherwise
   */
  @Override
  public boolean isUserNameIsUniq(String username) {

    return true;
  }

  /**
   * updates the password of the specified user.
   *
   * @param userName    String - the user name for which the password will be updated.
   * @param newPassword String
   * @return boolean true says that everything went well and false otherwise
   */
  @Override
  public boolean updateUserPassword(String userName, String newPassword) {
    return true;
  }

  // TODO: the rest of the methods

  /**
   * Returns short capabilities of the server side.
   *
   * @return String
   */
  @Override
  public String getServerInfo() {
    return "1.0";
  }

  public String toString() {
    return this.getClass().getName();
  }
}
