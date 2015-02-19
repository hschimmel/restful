package nl.harryt.restful.persistance;

import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import nl.harryt.restful.exception.RestfulException;
import nl.harryt.restful.exception.RestfulExceptionType;

/**
 * Database connection manager
 *
 * @author Harryt Schimmel
 */
public class Database {

  private static final String LOOKUP_NAME = "java:/comp/env/jdbc/names";

  /**
   * Retrieve a connection from the application's pool
   *
   * @return a connection from the pool
   * @throws RestfulException
   */
  public static Connection getConnection() throws RestfulException {

    DataSource dataSource;
    Connection connection;
    try {
      final InitialContext initialContext = new InitialContext();

      dataSource = (DataSource) initialContext.lookup(LOOKUP_NAME);

      if (dataSource == null) {
        throw new RestfulException(RestfulExceptionType.ERROR_DATASOURCE_NOT_FOUND);
      }

      connection = dataSource.getConnection();

    } catch (final NamingException | SQLException e) {
      throw new RestfulException(RestfulExceptionType.ERROR_DATABASE_CONNECTION_FAILED, e);

    }

    return connection;
  }

}
