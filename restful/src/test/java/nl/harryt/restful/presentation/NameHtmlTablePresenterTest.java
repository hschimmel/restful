package nl.harryt.restful.presentation;

import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

public class NameHtmlTablePresenterTest {

  /**
   * Will validate the existance of the input values in the result. Does not validate if result is
   * valid HTML/ table.
   */
  @Test
  public void testPresent() {
    // Given
    final NameHtmlTablePresenter presenter = new NameHtmlTablePresenter();
    final String value1 = "SomeRandomStringValue";
    final String value2 = "Another random string value";
    final String value3 = "Third random string";
    final List<String> list = Arrays.asList(new String[] {value1, value2, value3});

    // When
    final String result = presenter.present(list);

    // Then
    Assert.assertTrue("Expected result to contain value1", result.contains(value1));
    Assert.assertTrue("Expected result to contain value2", result.contains(value2));
    Assert.assertTrue("Expected result to contain value3", result.contains(value3));
  }

}
