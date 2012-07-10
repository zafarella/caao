// $codepro.audit.disable unusedMethod
/*
 * Zafar.Khaydarov @cs.joensuu.fi
 * */
package fi.uef.caao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Vector;

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
	/**
	 * The connection.
	 * 
	 * @see http
	 *      ://download.oracle.com/javase/1.4.2/docs/api/java/sql/Connection.
	 *      html Field conection.
	 */
	private static Connection conection = null;
	/**
	 * Connection string URL. For more details please look at JDBC URL
	 * specification. (http://www.connectionstrings.com)
	 */
	private static String mySQLUrl = "jdbc:mysql://localhost/";
	/**
	 * The database (schema) name in database catalog.
	 */
	private static String database = "caorganizer";
	/**
	 * The class name of the driver. Depends on the driver class implementation.
	 */
	private static final String mysqlDriverName = "com.mysql.jdbc.Driver";
	/**
	 * Database user name.
	 */
	private static String userName = "root";
	/**
	 * Password of the db user
	 */
	private static String pass = "root";
	/**
	 * The statement for SQL queries.
	 */
	protected static Statement statement;
	/**
	 * Field for the parameterized queries.
	 */
	protected static java.sql.PreparedStatement preparedStatement;
	/**
	 * The result set returned by queries
	 * 
	 * @see http 
	 *      ://download.oracle.com/javase/1.4.2/docs/api/java/sql/ResultSet.html
	 *      Field result_set.
	 * @see link 
	 *      http://download.oracle.com/javase/tutorial/java/javaOO/accesscontrol
	 *      .html
	 */
	protected static ResultSet resultSet = null;

	/**
	 * The main intend of the class is the data exchange between client
	 * application and the server. Class uses the {@link DbConnector} class to
	 * connect to the database and execute the SQL queries. The public methods
	 * is executed by the client (in this implementation Android application).
	 * 
	 * 
	 * 
	 * @return Vector<String>
	 * @throws Exception
	 * @see <a>DbConnector<a>
	 */
	// ---------------------------------------------------------------------------------------
	/**
	 * Returns the list of countries from database. Returns Vector as it's
	 * thread safe.
	 * 
	 * @return Vector<String>
	 * @throws Exception
	 */
	public List<String> countryList() throws Exception {
		List<String> returnList = new Vector<String>();
		System.out.println("Gonna connect to db..");
		try {
			conection = getMySqlConnection();
			statement = conection.createStatement();
			System.out
					.println("----------------------------------------------------------");
			System.out.println(preparedStatement.toString());
			System.out
					.println("----------------------------------------------------------");
			if (statement
					.executeQuery("SELECT country_title from core_countries ORDER BY country_title") != null) {
				resultSet = statement.getResultSet();
				while (resultSet.next()) {
					returnList.add(resultSet.getString("country_title"));
					System.out.println("Got result from query -> "
							+ resultSet.getString("country_title"));
				}
			}
		} catch (Exception s) {
			s.printStackTrace();
		} finally {
			statement.close();
			conection.close();
		}
		return returnList;
	}

	/**
	 * For a given country, returns the list of location.
	 * 
	 * @param pCountryName
	 *            the name of the country which for the locations is necessary
	 *            String
	 * 
	 * 
	 * 
	 * @return Vector<String> The list of location * @throws Exception The
	 *         exception could be SQL exception or some another * @see Vector<E>
	 */
	public List<String> locationList(String pCountryName) throws Exception {
		List<String> returnList = new Vector<String>();
		System.out.println("Executing method -> locationList(" + pCountryName
				+ ")");
		System.out.println("Gonna connect to db..");
		try {
			conection = getMySqlConnection();
			preparedStatement = conection
					.prepareStatement("SELECT location_title from core_locations_list where fk_country_id ="
							+ " (SELECT country_id from core_countries where country_title = ?)");
			preparedStatement.setString(1, pCountryName);
			System.out
					.println("----------------------------------------------------------");
			System.out.println(preparedStatement.toString());
			System.out
					.println("----------------------------------------------------------");
			if (null != preparedStatement.executeQuery()) {
				resultSet = statement.getResultSet();
				while (resultSet.next()) {
					returnList.add(resultSet.getString("location_title"));
					System.out.println("Got result from query -> "
							+ resultSet.getString("location_title"));
				}
			}
		} catch (Exception s) {
			s.printStackTrace();
		} finally {
			statement.close();
			conection.close();
		}
		return returnList;
	}

	// ---------------------------------------------------------------------------------------
	/**
	 * Returns the list of events for specified in pUsarName
	 * 
	 * @param pUserName
	 *            String the user name (email in database) - the unique user
	 *            identifier.
	 * 
	 * 
	 * @return Vector<String> * @throws SQLException
	 */
	public List<String> eventList(String pUserName) throws SQLException {
		List<String> returnList = new Vector<String>();
		System.out.println("Executing method -> eventList(" + pUserName + ")");
		System.out.println("Gonna connect to db..");
		try {
			conection = getMySqlConnection();
			preparedStatement = conection
					.prepareStatement("SELECT event_text from pg_events_list  where fk_user_id ="
							+ "(select user_id from core_users where e_mail = ?) order by 1 desc");
			preparedStatement.setString(1, pUserName);
			System.out
					.println("----------------------------------------------------------");
			System.out.println(preparedStatement.toString());
			System.out
					.println("----------------------------------------------------------");

			if (null != preparedStatement.executeQuery()) {
				resultSet = preparedStatement.getResultSet();
				while (resultSet.next()) {
					returnList.add(resultSet.getString("event_text"));
					System.out.println("Got result from query -> "
							+ resultSet.getString("event_text"));
				}
			}
		} catch (Exception s) {
			s.printStackTrace();
		} finally {
			preparedStatement.close();
		}
		return returnList;
	}

	/**
	 * Returns the list of plants (which user is growing) for a specified user.
	 * 
	 * @param pUserName
	 *            The user name for which the procedure returns the list of
	 *            plants.
	 * 
	 * 
	 * @return Vector<String> The list of plants * @throws SQLException
	 */
	public List<String> plantList(String pUserName) throws SQLException {
		List<String> returnList = new Vector<String>();
		System.out.println("Executing method -> plantList(" + pUserName + ")");
		System.out.println("Gonna connect to db..");
		try {
			conection = getMySqlConnection();
			preparedStatement = conection
					.prepareStatement("SELECT pgp_title from pg_plant_growing_plan WHERE user_id = "
							+ "(SELECT user_id from core_users where e_mail = ?)");
			preparedStatement.setString(1, pUserName);
			System.out
					.println("-----------------------------------------------------");
			System.out.println(preparedStatement.toString());
			System.out
					.println("-----------------------------------------------------");
			if (null != preparedStatement.executeQuery()) {
				resultSet = preparedStatement.getResultSet();
				while (resultSet.next()) {
					returnList.add(resultSet.getString("pgp_title"));
					System.out.println("Got result from query -> "
							+ resultSet.getString("pgp_title"));
				}
			}
		} catch (Exception s) {
			s.printStackTrace();
		} finally {
			preparedStatement.close();
		}
		return returnList;
	}

	// ----------------------------------------------------------------------------------------------------
	/**
	 * Returns the PostgreSQL connection. Depend on the driver version, edit the
	 * class name of the driver. Method uses the jdbc wrapper for connecting to
	 * the database.
	 * 
	 * 
	 * 
	 * @return Connection * @throws Exception
	 */
	@SuppressWarnings("unused")
	private static Connection getHSQLConnection() throws Exception {
		Class.forName("org.hsqldb.jdbcDriver");
		System.out.println("ProgreSQL driver Loaded successfully.");
		final String url = "jdbc:hsqldb:data/" + getDatabaseName();
		return DriverManager.getConnection(url, getUserName(), getPass());
	}

	/**
	 * Returns mySQL connection.
	 * 
	 * 
	 * 
	 * @return Connection * @throws Exception
	 */
	public static Connection getMySqlConnection() throws Exception {
		Class.forName(mysqlDriverName); // mysql driver
		Connection conn = DriverManager.getConnection(getMySQLUrl()
				+ getDatabaseName(), getUserName(), getPass());
		System.out.println("mySQL driver Loaded successfully.");
		return conn;
	}

	/**
	 * Returns the connection to the Oracle database. Please edit the driver and
	 * the database connection URL for specific version of the driver.
	 * 
	 * 
	 * 
	 * @return Connection * @throws Exception
	 */
	public static Connection getOracleConnection() throws Exception {
		final String driver = "oracle.jdbc.driver.OracleDriver";
		String url = "jdbc:oracle:thin:@cs.joensuu.fi:1521:"
				+ getDatabaseName();
		Class.forName(driver); // load Oracle driver
		Connection conn = DriverManager.getConnection(url, getUserName(),
				getPass());
		System.out.println("Oracle driver Loaded successfully.");
		return conn;
	}

	// ----------------------------------------------------------------------------------------------------

	// --------------- the getter and setters of the rest class members
	/**
	 * @param userName
	 *            the user_name to set
	 */
	@SuppressWarnings("unused")
	private static void setUserName(String userName) {
		CaaoServerCore.userName = userName;
	}

	/**
	 * 
	 * @return the user_name
	 */
	private static String getUserName() {
		return userName;
	}

	/**
	 * @param pass
	 *            the pass to set
	 */
	@SuppressWarnings("unused")
	private static void setPass(String pass) {
		CaaoServerCore.pass = pass;
	}

	/**
	 * 
	 * @return the pass
	 */
	private static String getPass() {
		return pass;
	}

	/**
	 * @param mySQLUrl
	 *            the mySQL_url to set
	 */
	@SuppressWarnings("unused")
	private static void setMySQLUrl(String mySQLUrl) {
		CaaoServerCore.mySQLUrl = mySQLUrl;
	}

	/**
	 * 
	 * @return the mySQL_url
	 */
	private static String getMySQLUrl() {
		return mySQLUrl;
	}

	/**
	 * @param database
	 *            the database to set
	 */
	@SuppressWarnings("unused")
	private static void setDatabaseName(String database) {
		CaaoServerCore.setDatabase(database);
	}

	/**
	 * 
	 * @return the database
	 */
	private static String getDatabaseName() {
		return getDatabase();
	}

	/**
	 * @return the database
	 */
	protected static String getDatabase() {
		return database;
	}

	/**
	 * @param database
	 *            the database to set
	 */
	protected static void setDatabase(String database) {
		CaaoServerCore.database = database;
	}

}