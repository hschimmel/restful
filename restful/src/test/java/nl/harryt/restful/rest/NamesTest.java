package nl.harryt.restful.rest;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import nl.harryt.restful.exception.RestfulException;
import nl.harryt.restful.persistance.NameDAO;
import nl.harryt.restful.presentation.NameHtmlTablePresenter;

/**
 * @author Harryt Schimmel
 *
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest({NameDAO.class, NameHtmlTablePresenter.class})
public class NamesTest {

  /**
   * Test method for {@link nl.harryt.restful.rest.Names#get()}. Validates that the result of the
   * DAO will be presented.
   *
   * @throws RestfulException
   */
  @Test
  public void testGet() throws RestfulException {
    // Given
    final Names names = new Names();

    final List<String> list = new ArrayList<String>();

    PowerMockito.mockStatic(NameDAO.class);
    PowerMockito.when(NameDAO.getNames()).thenReturn(list);

    PowerMockito.mockStatic(NameHtmlTablePresenter.class);
    PowerMockito.when(NameHtmlTablePresenter.present(list)).thenReturn("someString");

    // When
    final String result = names.get();

    // Then
    Assert.assertEquals("someString", result);
  }

  /**
   * Test method for {@link nl.harryt.restful.rest.Names#get()}. Validates that the Exception thrown
   * by the DAO is presented
   *
   * @throws RestfulException
   */
  @Test
  public void testGetExceptionOnNameDAO() throws RestfulException {
    // Given
    final Names names = new Names();

    final RestfulException exception = Mockito.mock(RestfulException.class);
    Mockito.when(exception.getMessage()).thenReturn("someMessage");

    PowerMockito.mockStatic(NameDAO.class);
    PowerMockito.when(NameDAO.getNames()).thenThrow(exception);

    // When
    final String result = names.get();

    // Then
    Assert.assertEquals("The message of the exception was expected.", "someMessage", result);
  }

  /**
   * Test method for {@link nl.harryt.restful.rest.Names#post(java.lang.String)}. Validates that the
   * result of the DAO will be presented.
   *
   * @throws RestfulException
   */
  @Test
  public void testPost() throws RestfulException {
    // Given
    final Names names = new Names();

    final String nameToPersist = "someName";

    final List<String> list = new ArrayList<String>();

    PowerMockito.mockStatic(NameDAO.class);
    PowerMockito.when(NameDAO.persist(nameToPersist)).thenReturn(list);

    PowerMockito.mockStatic(NameHtmlTablePresenter.class);
    PowerMockito.when(NameHtmlTablePresenter.present(list)).thenReturn("someString");

    // When
    final String result = names.post("someName");

    // Then
    Assert.assertEquals("someString", result);
  }

  /**
   * Test method for {@link nl.harryt.restful.rest.Names#post(java.lang.String)}. Validates that the
   * result of the DAO will be presented.
   *
   * @throws RestfulException
   */
  @Test
  public void testPostExceptionOnDAO() throws RestfulException {
    // Given
    final Names names = new Names();

    final String nameToPersist = "someName";

    final RestfulException exception = Mockito.mock(RestfulException.class);
    Mockito.when(exception.getMessage()).thenReturn("someMessage");

    PowerMockito.mockStatic(NameDAO.class);
    PowerMockito.when(NameDAO.persist(nameToPersist)).thenThrow(exception);


    // When
    final String result = names.post(nameToPersist);

    // Then
    Assert.assertEquals("The message of the exception was expected.", "someMessage", result);
  }

}
