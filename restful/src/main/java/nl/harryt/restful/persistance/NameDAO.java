package nl.harryt.restful.persistance;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.google.common.base.Strings;

import nl.harryt.restful.exception.RestfulException;
import nl.harryt.restful.exception.RestfulExceptionType;

public class NameDAO {

  static final String SQL_GET_NAMES = "SELECT name FROM names";
  static final String SQL_GET_NAME = SQL_GET_NAMES + " WHERE name = ?";
  static final String SQL_INSERT_NAME = "INSERT INTO names (name) VALUES (?)";

  static final String COLUMNS_NAME = "name";

  /**
   * Get a list of all stored names
   *
   * @return
   * @throws RestfulException
   */
  public static List<String> getNames() throws RestfulException {
    final List<String> names;

    try (final Connection connection = Database.getConnection();
            PreparedStatement statement = connection.prepareStatement(SQL_GET_NAMES);
            ResultSet resultSet = statement.executeQuery()) {

      names = new ArrayList<String>();
      while (resultSet.next()) {
        names.add(resultSet.getString(COLUMNS_NAME));
      }

    } catch (final SQLException e) {
      throw new RestfulException(RestfulExceptionType.NAMES_ERROR_ON_RETRIEVE, e);
    }

    return names;
  }

  /**
   * Get a name from the database
   *
   * @param name the name to look for
   * @return the name if found or null if not found
   * @throws RestfulException
   */
  public static String getName(final String name) throws RestfulException {
    String result = null;

    try (final Connection connection = Database.getConnection();
            PreparedStatement statement = connection.prepareStatement(SQL_GET_NAME);) {

      statement.setString(1, name);
      try (ResultSet resultSet = statement.executeQuery()) {

        if (resultSet.next()) {
          result = resultSet.getString(COLUMNS_NAME);
        }

      }
    } catch (final SQLException e) {
      throw new RestfulException(RestfulExceptionType.NAMES_ERROR_ON_RETRIEVE, e);
    }

    return result;
  }

  /**
   * Persists the given name and returns the new resulting table
   *
   * @param name
   * @return
   * @throws RestfulException
   */
  public static List<String> persist(final String name) throws RestfulException {
    if (Strings.isNullOrEmpty(name)) {
      throw new RestfulException(RestfulExceptionType.NAMES_IS_NULL_OR_EMPTY);
    }

    // Check for duplicates
    if (name.equals(getName(name))) {
      throw new RestfulException(RestfulExceptionType.NAMES_DUPLICATE);
    }

    //Persist name
    try (final Connection connection = Database.getConnection();
            PreparedStatement statement = connection.prepareStatement(SQL_INSERT_NAME)) {

      statement.setString(1, name);
      final int result = statement.executeUpdate();
      if (result != 1) {
        // Expected 1 as row count
        throw new RestfulException(RestfulExceptionType.NAMES_ERROR_ON_INSERT);
      }

    } catch (final SQLException e) {
      throw new RestfulException(RestfulExceptionType.NAMES_ERROR_ON_INSERT, e);
    }

    //Retrieve all stored names
    final List<String> returnValue = getNames();
    return returnValue;
  }

}
