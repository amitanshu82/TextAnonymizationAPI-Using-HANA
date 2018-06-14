package io.poc.text.anym.dbservices;


import javax.sql.DataSource;

/**
 * Helper class to create DataSources for HANA DB.
 * 
 * @author d047244
 */
public class HANADataSourceCreator {

	/**
	 * Log4j logger
	 */
	//private static Logger logger = Logger.getLogger(HANADataSourceCreator.class);

	/**
	 * Name of class implementing HANA JDBC driver
	 */
	public static final String hanaJDBCDriverClassName = "com.sap.db.jdbc.Driver";

	/**
	 * Create a DataSource for HANA from the provided credentials.
	 *
	 * The function will check for the best available classes in current runtime to
	 * create a DataSource, e.g. Apache Tomcat's pooled DataSource might be available
	 * if we are run in a Tomcat container.
	 *
	 * After DataSource has been created, simply call its getConnection() whenever
	 * you need a connection to HANA DB.
	 *
	 * @param host HANA host
	 * @param port HANA port
	 * @param user HANA user
	 * @param password HANA password
	 * @param schema HANA schema
	 * @return DataSource for HANA DB
	 */
	@SuppressWarnings("resource")
	public static DataSource createHanaDataSource(String host, int port, String user, String password, String schema) {

		DataSource ds = null;

		//String hanaConnUrl = "jdbc:sap://"+host+":"+port+"/?autocommit=true";
		String hanaConnUrl = "jdbc:sap://"+host+":"+port+"/?currentschema=DLP"; 

		//logger.debug("Checking for best available class to create DataSource.");

		if ((ds==null) && (isClassAvailable("org.apache.tomcat.jdbc.pool.DataSource"))) {
			//logger.info(" - Available: org.apache.tomcat.jdbc.pool.DataSource");
			System.out.println(" - Available: org.apache.tomcat.jdbc.pool.DataSource");
			org.apache.tomcat.jdbc.pool.DataSource ds2 = new org.apache.tomcat.jdbc.pool.DataSource();
			ds2.setDriverClassName(hanaJDBCDriverClassName);
			ds2.setUrl(hanaConnUrl);
			ds2.setUsername(user);
			ds2.setPassword(password);
			ds = ds2;
		}

		if ((ds==null) && (isClassAvailable("org.apache.tomcat.dbcp.dbcp2.BasicDataSource"))) {
			//logger.info(" - Available: org.apache.tomcat.dbcp.dbcp2.BasicDataSource");
			System.out.println("- Available: org.apache.tomcat.dbcp.dbcp2.BasicDataSource");
			org.apache.tomcat.dbcp.dbcp2.BasicDataSource ds2 = new org.apache.tomcat.dbcp.dbcp2.BasicDataSource();
			ds2.setDriverClassName(hanaJDBCDriverClassName);
			ds2.setUrl(hanaConnUrl);
			ds2.setUsername(user);
			ds2.setPassword(password);
			ds = ds2;
		}

		// There are also managed DS, what's with them? org.apache.commons.dbcp2.managed.BasicManagedDataSource
		if ((ds==null) && (isClassAvailable("org.apache.commons.dbcp2.BasicDataSource"))) {
			//logger.info(" - Available: org.apache.commons.dbcp2.BasicDataSource");
			System.out.println(" - Available: org.apache.commons.dbcp2.BasicDataSource");
			org.apache.commons.dbcp2.BasicDataSource ds2 = new org.apache.commons.dbcp2.BasicDataSource();
			ds2.setDriverClassName(hanaJDBCDriverClassName);
			ds2.setUrl(hanaConnUrl);
			ds2.setUsername(user);
			ds2.setPassword(password);
			ds = ds2;
		}

		if ((ds==null) && (isClassAvailable("org.apache.commons.dbcp.BasicDataSource"))) {
			//logger.info(" - Available: org.apache.commons.dbcp.BasicDataSource");
			System.out.println(" - Available: org.apache.commons.dbcp.BasicDataSource");
			org.apache.commons.dbcp.BasicDataSource ds2 = new org.apache.commons.dbcp.BasicDataSource();
			ds2.setDriverClassName(hanaJDBCDriverClassName);
			ds2.setUrl(hanaConnUrl);
			ds2.setUsername(user);
			ds2.setPassword(password);
			ds = ds2;			
		}

		if (ds==null)
			//logger.debug(" - No suitable class for creating DataSource found. Suggestion: Package something like org.apache.commons.dbcp2 with your web app.");
			System.out.println(" - No suitable class for creating DataSource found. Suggestion: Package something like org.apache.commons.dbcp2 with your web app.");

		return ds;

	}

	/**
	 * Little helper to check if a class is available on the classpath.
	 */
	public static boolean isClassAvailable(String className) {
		try {
			Class.forName(className);
			return true;
		} catch (Throwable ex) {
			return false;
		}
	}

}
