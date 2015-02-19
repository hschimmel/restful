package nl.harryt.restful.persistance;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import com.google.common.base.Strings;

import nl.harryt.restful.exception.RestfulException;
import nl.harryt.restful.exception.RestfulExceptionType;

@RunWith(PowerMockRunner.class)
@PrepareForTest({Database.class, NameDAO.class})
public class NameDAOTest {

  @Test
  public void testGetNamesEmpty() throws RestfulException, SQLException {
    // Given
    PowerMockito.mockStatic(Database.class);

    final Connection connection = Mockito.mock(Connection.class);
    final PreparedStatement statement = Mockito.mock(PreparedStatement.class);
    final ResultSet resultSet = Mockito.mock(ResultSet.class);

    PowerMockito.when(Database.getConnection()).thenReturn(connection);
    Mockito.when(connection.prepareStatement(NameDAO.SQL_GET_NAMES)).thenReturn(statement);

    Mockito.when(statement.executeQuery()).thenReturn(resultSet);

    Mockito.when(resultSet.next()).thenReturn(false);

    // When
    final List<String> result = NameDAO.getNames();

    // Then
    Assert.assertTrue("Expected an empty list", result.isEmpty());
  }

  @Test
  public void testGetNamesSmall() throws RestfulException, SQLException {
    // Given
    PowerMockito.mockStatic(Database.class);

    final Connection connection = Mockito.mock(Connection.class);
    final PreparedStatement statement = Mockito.mock(PreparedStatement.class);
    final ResultSet resultSet = Mockito.mock(ResultSet.class);

    PowerMockito.when(Database.getConnection()).thenReturn(connection);
    Mockito.when(connection.prepareStatement(NameDAO.SQL_GET_NAMES)).thenReturn(statement);

    Mockito.when(statement.executeQuery()).thenReturn(resultSet);

    Mockito.when(resultSet.next()).thenReturn(true, true, true, false);
    Mockito.when(resultSet.getString(NameDAO.COLUMNS_NAME)).thenReturn("a", "b", "c");

    // When
    final List<String> result = NameDAO.getNames();

    // Then
    Assert.assertFalse("Expected a non empty list", result.isEmpty());
    Assert.assertEquals("a", result.get(0));
    Assert.assertEquals("b", result.get(1));
    Assert.assertEquals("c", result.get(2));
  }

  @Test
  public void testGetName() throws RestfulException, SQLException {
    // Given
    final String name = "someName";

    PowerMockito.mockStatic(Database.class);

    final Connection connection = Mockito.mock(Connection.class);
    final PreparedStatement statement = Mockito.mock(PreparedStatement.class);
    final ResultSet resultSet = Mockito.mock(ResultSet.class);

    PowerMockito.when(Database.getConnection()).thenReturn(connection);
    Mockito.when(connection.prepareStatement(NameDAO.SQL_GET_NAME)).thenReturn(statement);

    Mockito.doNothing().when(statement).setString(1, name);
    Mockito.when(statement.executeQuery()).thenReturn(resultSet);

    Mockito.when(resultSet.next()).thenReturn(true, false);
    Mockito.when(resultSet.getString(NameDAO.COLUMNS_NAME)).thenReturn(name);

    // When
    final String result = NameDAO.getName(name);

    // Then
    Assert.assertFalse("Expected a non empty result", Strings.isNullOrEmpty(result));
    Assert.assertEquals("Expected result to be equal to the given name", name, result);
  }

  @Test
  public void testPersistEmpty() throws RestfulException {
    // Given
    final String name = null;

    // When
    try {
      NameDAO.persist(name);
      Assert.fail("Exception expected");
    } catch (final RestfulException e) {
      Assert.assertEquals(RestfulExceptionType.NAMES_IS_NULL_OR_EMPTY, e.getType());
    }
  }

}
