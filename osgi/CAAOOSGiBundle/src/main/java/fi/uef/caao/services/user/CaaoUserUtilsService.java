package fi.uef.caao.services.user;

/**
 * Created by z1 on 10/10/15.
 */
public interface CaaoUserUtilsService {
  boolean isUserNameIsUniq(String username);

  boolean updateUserPassword(String userName, String newPassword);

  String getServerInfo();
}
