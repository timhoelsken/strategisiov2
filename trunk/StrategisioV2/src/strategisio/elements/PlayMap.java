package strategisio.elements;

import strategisio.elements.constants.Ground;
import strategisio.elements.fields.Field;
import strategisio.elements.fields.Grass;
import strategisio.elements.fields.Mountain;
import strategisio.elements.fields.Water;
import strategisio.elements.figures.Climber;
import strategisio.elements.figures.Diver;

/**
 *
 * the PlayMap
 *
 */
public class PlayMap {

  private Field[][] fields;

  /**
   * creates a quadratic map
   *
   * @param aDimension
   *            for size of the map (aDimension^2)
   */
  public PlayMap(int aDimension) {
    initFields(aDimension, aDimension);
  }

  /**
   * creates a map
   *
   * @param anXDimension
   * @param aYDimension
   *            for size of the map (anXDimension x aYDimension)
   */
  public PlayMap(int anXDimension, int aYDimension) {
    initFields(anXDimension, aYDimension);
  }

  private void initFields(int anXDimension, int aYDimension) {
    fields = new Field[anXDimension][aYDimension];
  }

  /**
   * sets field type for specified field
   *
   * @param anX
   * @param aY
   * @param aFieldType
   * @throws UnknownFieldTypeException
   * @throws WrongCoordinateException
   */
  public void setFieldType(int anX, int aY, int aFieldType) throws UnknownFieldTypeException, WrongCoordinateException {
    Field tmpField;
    switch (aFieldType) {
      case Ground.GRASS:
        tmpField = new Grass();
        break;
      case Ground.MOUNTAIN:
        tmpField = new Mountain();
        break;
      case Ground.WATER:
        tmpField = new Water();
        break;
      default:
        throw new UnknownFieldTypeException(aFieldType + " is not a valid field type");
    }
    fields[anX][aY] = tmpField;
  }

  public Movable getMovable(int anX, int aY) throws WrongCoordinateException {
    return getSetter(anX, aY);
  }

  private Movable getSetter(int anX, int aY) {
    Field tmpField = fields[aY][anX];
    Movable tmpMovable = tmpField.getSetter();
    return tmpMovable;
  }

  /**
   * Sets the Movable on the specified field. Checking is necessary before!
   *
   * @param aMovable
   * @param anX
   * @param aY
   */
  public void position(Movable aMovable, int anX, int aY) {
    fields[aY][anX].setSetter(aMovable);
  }

  /**
   * Checks if specified field is free and if the ground fits to the given Movable.
   *
   * @param aMovable
   * @param anX
   * @param aY
   * @return
   */
  public boolean checkInitialPositionPossibility(Movable aMovable, int anX, int aY) {
    return (checkIfIsEmpty(anX, aY) && checkGround(aMovable, anX, aY)) ? true : false;
  }

  /**
   * Checks if the given Movable fits on the ground of the specified field
   */
  private boolean checkGround(Movable aMovable, int anX, int aY) {
    Field tmpField = fields[aY][anX];
    if (tmpField instanceof Mountain) {
      if (aMovable instanceof Climber) {
        return true;
      } else {
        return false;
      }
    } else if (tmpField instanceof Water) {
      if (aMovable instanceof Diver) {
        return true;
      } else {
        return false;
      }
    } else {
      return true;
    }
  }

  /**
   * Checks if the specified field is empty
   */
  private boolean checkIfIsEmpty(int anX, int aY) {
    Movable tmpMovable = getSetter(anX, aY);
    if (tmpMovable == null) {
      return true;
    } else {
      return false;
    }
  }

  private int getXDimension() {
    return fields.length;
  }

  private int getYDimension() {
    return fields[0].length;
  }

  /**
   * shows map on console
   *
   * @deprecated
   */
  public void showMap() {
    System.out.println();
    System.out.println();
    String tmpFirstLine = "       ";
    for (int i = 0; i < getYDimension(); i++) {
      tmpFirstLine += (i + 1);
      if ((i + 1) < 10) {
        tmpFirstLine += "         ";
      } else {
        tmpFirstLine += "        ";
      }
    }
    System.out.println(tmpFirstLine);

    for (int i = 0; i < getYDimension(); i++) {
      System.out.println();
      String tmpFieldRow = "  " + (i + 1);
      if ((i + 1) < 10) {
        tmpFieldRow += "   ";
      } else {
        tmpFieldRow += "  ";
      }
      for (int j = 0; j < getXDimension(); j++) {
        if (fields[i][j].getSetter() != null) {
          Movable tmpMovable = fields[i][j].getSetter();
          String tmpMovableName = tmpMovable.getClass().toString();
          tmpMovableName = tmpMovableName.substring(tmpMovableName.lastIndexOf('.') + 1);
          if (tmpMovable.getId() == 'A') {
            tmpMovableName = tmpMovableName.toUpperCase();
          } else if (tmpMovable.getId() == 'B') {
            tmpMovableName = tmpMovableName.toLowerCase();
          }
          while (tmpMovableName.length() != 10) {
            tmpMovableName += " ";
          }
          tmpFieldRow += tmpMovableName;
        } else {
          tmpFieldRow += "          ";
        }
      }
      System.out.println(tmpFieldRow);
    }
  }
}
