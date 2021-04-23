package mx.dev.blank.exception;

public class CheckedException extends Exception {

  private static final long serialVersionUID = 1L;

  public CheckedException(final String message) {
    super(message);
  }

  public CheckedException(final String message, final Throwable t) {
    super(message, t);
  }
}
