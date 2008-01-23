/**
 *
 */
package strategisio.elements;

/**
 * @author Tobias
 *
 */
public class UnknownFieldTypeException extends Exception {

  /**
   *
   */
  private static final long serialVersionUID = 870265889291660961L;

  UnknownFieldTypeException(){
    super();
  }

  UnknownFieldTypeException(String aMessage){
    super(aMessage);
  }
}
