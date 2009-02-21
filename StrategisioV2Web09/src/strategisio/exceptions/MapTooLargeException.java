package strategisio.exceptions;

/**
 *
 * @author Tobias
 *
 */
public class MapTooLargeException extends Exception {

  private static final long serialVersionUID = 8529865908559391830L;

  /**
   * std. constructor
   */
  public MapTooLargeException() {
    super();
  }

  /**
   * dim. constructor
   * @param anXDimension x
   * @param aYDimension y
   *
   */
  public MapTooLargeException(int anXDimension, int aYDimension) {
    super("Invalid dimensions " + anXDimension + "(x)/" + aYDimension + "(y)");
  }
}
