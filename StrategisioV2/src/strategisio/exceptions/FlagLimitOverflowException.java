package strategisio.exceptions;

/**
 * 
 * @author Tim
 * 
 */
public class FlagLimitOverflowException extends Exception {

  /**
   * 
   */
  private static final long serialVersionUID = -6455986187674933032L;

  /**
   * 
   */
  public FlagLimitOverflowException() {
    super();
  }

  /**
   * 
   * @param aMessage
   */
  public FlagLimitOverflowException(String aMessage) {
    super(aMessage);
  }
}
