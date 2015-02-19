package nl.harryt.restful.exception;

/**
 * Enumeration of possible types of exception that may occur in the Restful application.
 *
 * @author smh
 *
 */
public enum RestfulExceptionType {

  ERROR_UNKNOWN("Unknown error"), ERROR_DATASOURCE_NOT_FOUND("Datasource names not found"), ERROR_DATABASE_CONNECTION_FAILED(
          "Could not get a database connection"), NAMES_DUPLICATE("Name already exists in table"), NAMES_IS_NULL_OR_EMPTY(
          "No name given"), NAMES_ERROR_ON_RETRIEVE("Error getting names from database"), NAMES_ERROR_ON_INSERT(
          "Error storing name into database");

  private String message;

  RestfulExceptionType(final String message) {
    this.setMessage(message);
  }

  /**
   * The error message for this type of exception
   *
   * @return the message
   */
  public String getMessage() {
    return message;
  }

  private void setMessage(final String message) {
    this.message = message;
  }

}
