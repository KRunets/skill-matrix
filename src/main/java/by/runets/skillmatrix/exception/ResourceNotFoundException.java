package by.runets.skillmatrix.exception;

/**
 * This exception will thrown when required resource will not found.
 */
public class ResourceNotFoundException extends Exception {
  
  public ResourceNotFoundException() {
    super();
  }
  
  public ResourceNotFoundException(String message) {
    super(message);
  }
  
  public ResourceNotFoundException(String message, Throwable cause) {
    super(message, cause);
  }
  
  public ResourceNotFoundException(Throwable cause) {
    super(cause);
  }
}
