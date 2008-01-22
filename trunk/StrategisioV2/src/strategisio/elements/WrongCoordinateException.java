/**
 *
 */
package strategisio.elements;

/**
 * @author Tobias
 *
 */
public class WrongCoordinateException extends IllegalArgumentException {

  /**
   * serialVersionUID
   */
  private static final long serialVersionUID = -7600160615434658195L;

  WrongCoordinateException(){
    super();
  }

  WrongCoordinateException(String aMessage){
    super(aMessage);
  }
}
