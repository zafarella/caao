/**
 * $codepro.audit.disable unusedMethod
 * <p/>
 * Zafar.Khaydarov @cs.joensuu.fi
 */

package fi.uef.caao;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


/**
 * The class implements basic methods for the client application. Used as
 * handler class for the xml-rpc service. See the comments on each method for
 * details. Design notes: -- the thread safety was taken into consideration --
 * wrapper classes of the jdk was taken into account, so with small modification
 * you can use different databases. This implementation uses mySQL
 *
 * @author zafar.khaydarov
 * @version $Revision: 1.8 $
 */

public class CaaoServerCore {
  private static final String MY_SQL_DRIVER_NAME = "com.mysql.jdbc.Driver";
  private final static Log log = LogFactory.getLog(CaaoServerCore.class);
  protected static Statement statement;
  protected static java.sql.PreparedStatement preparedStatement;
  protected static ResultSet resultSet = null;
  private static Connection connection = null;
  private static String mySQLUrl = "jdbc:mysql://localhost/";
  private static String database = "caao";
  private static String userName = "caao";
  private static String pass = "root";

  /**
   * Returns the PostgreSQL connection. Depend on the driver version, edit the
   * class name of the driver. Method uses the jdbc wrapper for connecting to
   * the database.
   *
   * @return Connection
   *
   * @throws Exception
   */
  @SuppressWarnings("unused")
  private static Connection getHSQLConnection() throws Exception {
    Class.forName("org.hsqldb.jdbcDriver");
    log.info("ProgreSQL driver Loaded successfully.");
    final String url = "jdbc:hsqldb:data/" + getDatabaseName();
    return DriverManager.getConnection(url, getUserName(), getPass());
  }

  /**
   * Returns mySQL connection.
   *
   * @return Connection
   */
  public static Connection getMySqlConnection() {
    Connection conn = null;
    try {
      Class.forName(MY_SQL_DRIVER_NAME);
      conn = DriverManager.getConnection(getMysqlUrl()
              + getDatabaseName(), getUserName(), getPass());
    } catch (Exception e) {
      log.error(e.getMessage());
    }
    return conn;
  }

  /**
   * Returns the connection to the Oracle database. Please edit the driver and
   * the database connection URL for specific version of the driver.
   *
   * @return Connection * @throws Exception
   */
  public static Connection getOracleConnection() {
    final String driver = "oracle.jdbc.driver.OracleDriver";
    String url = "jdbc:oracle:thin:@cs.joensuu.fi:1521:"
            + getDatabaseName();
    Connection con = null;
    try {
      Class.forName(driver);
      con = DriverManager.getConnection(url, getUserName(), getPass());
    } catch (Exception e) {
      log.error(e.getMessage());
    }
    return con;
  }

  /**
   * Checks db health status.
   *
   * @return true when all is good and false otherwise.
   */
  public static boolean canUsedb() {
    try {
      connection = getMySqlConnection();
      preparedStatement = connection
              .prepareStatement("SELECT 1 + 1 FROM DUAL");
      if (null != preparedStatement.executeQuery()) {
        resultSet = preparedStatement.getResultSet();
      }
      preparedStatement.close();
    } catch (Exception e) {
      return false;
    }
    return true;
  }

  private static String getUserName() {
    return userName;
  }

  @SuppressWarnings("unused")
  private static void setUserName(String userName) {
    CaaoServerCore.userName = userName;
  }

  private static String getPass() {
    return pass;
  }

  @SuppressWarnings("unused")
  private static void setPass(String pass) {
    CaaoServerCore.pass = pass;
  }

  // --------------- the getter and setters of the rest class members

  private static String getMysqlUrl() {
    return mySQLUrl;
  }

  @SuppressWarnings("unused")
  private static void setMySQLUrl(String mySQLUrl) {
    CaaoServerCore.mySQLUrl = mySQLUrl;
  }

  private static String getDatabaseName() {
    return getDatabase();
  }

  @SuppressWarnings("unused")
  private static void setDatabaseName(String database) {
    CaaoServerCore.setDatabase(database);
  }

  protected static String getDatabase() {
    return database;
  }

  protected static void setDatabase(String database) {
    CaaoServerCore.database = database;
  }

  /**
   * The main intend of the class is the data exchange between client
   * application and the server. Class uses the DbConnector class to
   * connect to the database and execute the SQL queries. The public methods
   * is executed by the client (in this implementation Android application).
   *
   * @return the list of countries from database.
   */
  public List<String> countryList() throws Exception {
    List<String> listOfCountries = Collections.synchronizedList(new ArrayList<String>());
    log.debug("Gonna connect to db..");
    try {
      connection = getMySqlConnection();
      statement = connection.createStatement();
      log.debug("----------------------------------------------------------");
      log.debug(preparedStatement.toString());
      log.debug("----------------------------------------------------------");
      if (statement.executeQuery("SELECT country_title from core_countries ORDER BY country_title")
              != null) {
        resultSet = statement.getResultSet();
        while (resultSet.next()) {
          listOfCountries.add(resultSet.getString("country_title"));
          log.debug("Got result from query -> "
                  + resultSet.getString("country_title"));
        }
      }
    } catch (SQLException e) {
      log.error(e.getMessage());
    } finally {
      statement.close();
      connection.close();
    }
    return listOfCountries;
  }

  /**
   * For a given country, returns the list of location.
   *
   * @param countryName the name of the country which for the locations is necessary
   *                    String
   * @return List List of location
   *
   * @see List
   */
  public List<String> locationList(String countryName) throws SQLException {
    List<String> returnList = Collections.synchronizedList(new ArrayList<String>());
    log.info("Executing method -> locationList(" + countryName + ")");
    log.info("Gonna connect to db..");
    try {
      connection = getMySqlConnection();
      preparedStatement = connection
              .prepareStatement("SELECT location_title"
                      + " from core_locations_list "
                      + "where fk_country_id ="
                      + " (SELECT country_id from core_countries "
                      + "where country_title = ?)");
      preparedStatement.setString(1, countryName);
      log.debug("----------------------------------------------------------");
      log.debug(preparedStatement.toString());
      log.debug("----------------------------------------------------------");
      if (null != preparedStatement.executeQuery()) {
        resultSet = statement.getResultSet();
        while (resultSet.next()) {
          returnList.add(resultSet.getString("location_title"));
          log.debug("Got result from query -> "
                  + resultSet.getString("location_title"));
        }
      }
    } catch (SQLException e) {
      log.error(e.getMessage());
    } finally {
      statement.close();
      connection.close();
    }
    return returnList;
  }

  /**
   * Returns the list of events for specified in pUsarName
   *
   * @param userName String the user name (email in database) - the unique user
   *                 identifier.
   * @return List
   *
   * @throws SQLException SQL Exception
   */
  public List<String> eventList(String userName) throws SQLException {

    List<String> returnList = Collections.synchronizedList(new ArrayList<String>());
    log.debug("Executing method -> eventList(" + userName + ")");
    log.debug("Gonna connect to db..");
    try {
      connection = getMySqlConnection();
      preparedStatement = connection
              .prepareStatement("SELECT event_text from pg_events_list  where fk_user_id ="
                      + "(select user_id from core_users where e_mail = ?) order by 1 desc");
      preparedStatement.setString(1, userName);
      if (log.isDebugEnabled()) {
        log.debug("----------------------------------------------------------");
        log.debug(preparedStatement.toString());
        log.debug("----------------------------------------------------------");
      }
      if (null != preparedStatement.executeQuery()) {
        resultSet = preparedStatement.getResultSet();
        while (resultSet.next()) {
          returnList.add(resultSet.getString("event_text"));
          log.debug("Got result from query -> "
                  + resultSet.getString("event_text"));
        }
      }
    } catch (SQLException e) {
      log.error(e.getMessage());
    } finally {
      preparedStatement.close();
    }
    return returnList;
  }

  /**
   * Returns the list of plants (which user is growing) for a specified user.
   *
   * @param userName The user name for which the procedure returns the list of
   *                 plants.
   * @return List The list of plants
   *
   * @throws SQLException
   */
  public List<String> plantList(String userName) throws SQLException {

    List<String> returnList = Collections.synchronizedList(new ArrayList<String>());
    log.info("Executing method -> plantList(" + userName + ")");
    log.info("Gonna connect to db..");
    try {
      connection = getMySqlConnection();
      preparedStatement = connection
              .prepareStatement("SELECT pgp_title from pg_plant_growing_plan WHERE user_id = "
                      + "(SELECT user_id from core_users where e_mail = ?)");
      preparedStatement.setString(1, userName);
      log.debug("-----------------------------------------------------");
      log.debug(preparedStatement.toString());
      log.debug("-----------------------------------------------------");
      if (null != preparedStatement.executeQuery()) {
        resultSet = preparedStatement.getResultSet();
        while (resultSet.next()) {
          returnList.add(resultSet.getString("pgp_title"));
          log.debug("Got result from query -> "
                  + resultSet.getString("pgp_title"));
        }
      }
    } catch (SQLException e) {
      log.error(e.getMessage());
    } finally {
      preparedStatement.close();
    }
    return returnList;
  }

  public String toString() {
    return this.getClass().getName();
  }
}
