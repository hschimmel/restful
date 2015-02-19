package nl.harryt.restful.presentation;

import java.util.List;

/**
 * Present a list of values as a HTML-table
 *
 * @author Harryt Schimmel
 *
 */
public class NameHtmlTablePresenter {

  private static final String NOTIFICATION_LIST_EMPTY = "Nothing to show";

  /**
   * No public constructor in util class
   */
  NameHtmlTablePresenter() {
  }

  /**
   * Create the html
   *
   * @param values
   * @return
   */
  public static String present(final List<String> values) {
    final StringBuilder htmlBuilder = new StringBuilder();

    // Create html, body and table elements
    htmlBuilder.append("<html><body><table>");

    if (values.size() > 0) {
      // Create row with cell with value for each value
      for (final String value : values) {
        htmlBuilder.append("<tr><td>");
        htmlBuilder.append(value);
        htmlBuilder.append("</td></tr>");
      }

    } else {
      // Show notification that list is empty
      htmlBuilder.append("<tr><td>");
      htmlBuilder.append(NOTIFICATION_LIST_EMPTY);
      htmlBuilder.append("</td></tr>");

    }

    // Close html header and table
    htmlBuilder.append("</table></body></html>");

    final String html = htmlBuilder.toString();
    return html;
  }

}
