package nl.harryt.restful.exception;

/**
 * @author Harryt Schimmel
 *
 */
public class RestfulException extends Exception {

  RestfulExceptionType type;

  public RestfulException(final RestfulExceptionType type) {
    super(type.getMessage());
    this.type = type;
  }

  public RestfulException(final RestfulExceptionType type, final Throwable cause) {
    super(type.getMessage(), cause);
    this.type = type;
  }

  /**
   * @return The type of exception
   */
  public RestfulExceptionType getType() {
    return type;
  }

}
