package mx.dev.blank;

import java.util.Random;
import mx.dev.blank.exception.CheckedException;
import mx.dev.blank.exception.ResourceNotFoundException;
import org.testng.annotations.Test;

public class DemoException {

  @Test
  public void uncheckedError() {
    final Integer test = null;

    System.out.println(test.intValue());
  }

  @Test
  public void uncheckedErrorCatch() {
    final Integer test = null;

    try {
      System.out.println(test.intValue());
    } catch (final NullPointerException ex) {
      System.out.println("Grumpy cat");
    }
  }

  @Test
  public void uncheckedErrorCatchFinally() {

    final int random = new Random().nextInt();

    final Integer test = (random % 2 == 0) ? random : null;

    try {
      System.out.println(test.intValue());
    } catch (final NullPointerException ex) {
      System.out.println("Grumpy cat");
    } finally {
      System.out.println("No longer GrumpyCat");
    }
  }

  @Test
  public void checkedError() throws CheckedException {

    methodFail();
  }

  @Test
  public void rethrowingException() {
    try {
      methodFail();
    } catch (CheckedException e) {
      System.out.print("Something fail");
      throw new ResourceNotFoundException("An error", e);
    }
  }

  private void methodFail() throws CheckedException {
    throw new CheckedException("This is an error");
  }

  private void methodFail(final int value) throws Exception {

    if (value % 2 == 0) {
      throw new CheckedException("Fail Custom");
    } else {
      throw new Exception("Fail Generic");
    }
  }

  @Test
  public void multipleCatch() {
    try {
      methodFail(new Random().nextInt());
    } catch (CheckedException | ResourceNotFoundException e) {
      System.out.print("Custom");
    } catch (Exception e) {
      System.out.print("Generic");
    }
  }
}
