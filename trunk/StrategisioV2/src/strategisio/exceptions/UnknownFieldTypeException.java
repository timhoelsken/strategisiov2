/**
 *
 */
package strategisio.exceptions;

/**
 * @author Tobias
 *
 */
public class UnknownFieldTypeException extends Exception {

  /**
   *
   */
  private static final long serialVersionUID = 870265889291660961L;

  /**
   *
   */
  public UnknownFieldTypeException(){
    super();
  }

  /**
   * @param aMessage
   */
  public UnknownFieldTypeException(String aMessage){
    super(aMessage);
  }
}
