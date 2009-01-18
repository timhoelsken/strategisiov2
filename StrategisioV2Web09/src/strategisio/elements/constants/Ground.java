package strategisio.elements.constants;

import strategisio.elements.fields.Field;
import strategisio.elements.fields.Grass;
import strategisio.elements.fields.Mountain;
import strategisio.elements.fields.Water;
import strategisio.exceptions.UnknownFieldGroundException;

/**
 * @author Tobias
 *
 * has the constants for the different field grounds
 *
 */
public class Ground {

  /**
   * GRASS
   */
  public static final int GRASS = 0;

  /**
   * MOUNTAIN
   */
  public static final int MOUNTAIN = 1;

  /**
   * WATER
   */
  public static final int WATER = 2;

  /**
   *
   * @param aFieldGround
   * @return
   * @throws UnknownFieldGroundException
   */
  public static final Field getFieldGround(int aFieldGround) throws UnknownFieldGroundException {
    Field tmpField;
    switch (aFieldGround) {
      case GRASS:
        tmpField = new Grass();
        break;
      case MOUNTAIN:
        tmpField = new Mountain();
        break;
      case WATER:
        tmpField = new Water();
        break;
      default:
        throw new UnknownFieldGroundException(aFieldGround + " is not a valid field type");
    }
    return tmpField;
  }

  /**
   * @param aFieldGround
   * @return the label of the specified fieldGround
   * @throws UnknownFieldGroundException
   */
  public static final String getGroundLabeling(int aFieldGround) throws UnknownFieldGroundException {
    switch (aFieldGround) {
      case GRASS:
        return new String("grass");
      case MOUNTAIN:
        return new String("mountain");
      case WATER:
        return new String("water");
      default:
        throw new UnknownFieldGroundException(aFieldGround + " is not a valid field type");
    }
  }
}
