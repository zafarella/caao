package fi.uef.caao.services.core;

import java.sql.SQLException;
import java.util.List;

/**
 *
 */
public interface CaaoCoreServices {
  List<String> countryList() throws Exception;

  List<String> locationList(String countryName) throws SQLException;

  List<String> eventList(String userName) throws SQLException;

  List<String> plantList(String userName) throws SQLException;
}
