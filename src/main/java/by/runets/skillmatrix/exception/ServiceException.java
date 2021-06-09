package by.runets.skillmatrix.exception;

/**
 * This exception will thrown in service layer.
 */
public class ServiceException extends Exception {
  
  public ServiceException() {
  }
  
  public ServiceException(String message) {
    super(message);
  }
  
  public ServiceException(String message, Throwable cause) {
    super(message, cause);
  }
  
  public ServiceException(Throwable cause) {
    super(cause);
  }
}
