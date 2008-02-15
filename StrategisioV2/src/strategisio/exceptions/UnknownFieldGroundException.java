/**
 *
 */
package strategisio.exceptions;

/**
 * @author Tobias
 * 
 */
public class UnknownFieldGroundException extends Exception {

  /**
   * 
   */
  private static final long serialVersionUID = 870265889291660961L;

  /**
   * 
   */
  public UnknownFieldGroundException() {
    super();
  }

  /**
   * @param aMessage
   */
  public UnknownFieldGroundException(String aMessage) {
    super(aMessage);
  }
}
