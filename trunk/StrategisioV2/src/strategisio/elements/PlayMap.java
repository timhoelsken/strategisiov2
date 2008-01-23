package strategisio.elements;

import strategisio.elements.constants.Ground;
import strategisio.elements.fields.Field;
import strategisio.elements.fields.Grass;
import strategisio.elements.fields.Mountain;
import strategisio.elements.fields.Water;
import strategisio.elements.figures.Climber;
import strategisio.elements.figures.Diver;
import strategisio.elements.figures.Figure;

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
    checkCoordinates(anX, aY);
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

  /**
   * positioning method for items and figures (at the beginning of the game)
   *
   * @param aMovable
   * @param anX
   * @param aY
   * @return true if Movable was set
   * @throws WrongCoordinateException
   * @throws IllegalArgumentException
   *             if coordinates are not applicable
   */
  public boolean positionInitially(Movable aMovable, int anX, int aY) throws WrongCoordinateException {
    checkCoordinates(anX, aY);
    if (checkPositionPossibilityFor(aMovable, anX, aY)) {
      setSetter(aMovable, anX, aY);
      return true;
    }
    return false;
  }

  public void position(Movable aMovable, int anX, int aY) throws WrongCoordinateException {
    checkCoordinates(anX, aY);
    if (checkPositionPossibilityFor(aMovable, anX, aY)) {

    }
  }

  public boolean checkPositionPossibilityFor(Movable aMovable, int anX, int aY) {
    return (checkGroundFor(aMovable, anX, aY) && checkIfSet(anX, aY)) ? true : false;
  }

  public Movable getMovable(int anX, int aY) throws WrongCoordinateException {
    checkCoordinates(anX, aY);
    return getSetter(anX, aY);
  }

  private Movable getSetter(int anX, int aY) {
    Field tmpField = fields[aY][anX];
    Movable tmpMovable = tmpField.getSetter();
    return tmpMovable;
  }

  private boolean checkGroundFor(Movable aMovable, int anX, int aY) {
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

  private boolean checkIfSet(int anX, int aY) {
    Movable tmpMovable = getSetter(anX, aY);
    if (tmpMovable == null) {
      return true;
    } else {
      return false;
    }
  }

  private void setSetter(Movable aMovable, int anX, int aY) {
    fields[aY][anX].setSetter(aMovable);
  }

  private void checkCoordinates(int anX, int aY) throws WrongCoordinateException {
    if (anX < 0) {
      throw new WrongCoordinateException("x value '" + anX + "' is less than 0");
    } else if (anX >= getXDimension()) {
      throw new WrongCoordinateException("x value '" + anX + "' equals or is greater than maximum value '" + getXDimension() + "'");
    } else if (aY < 0) {
      throw new WrongCoordinateException("y value '" + aY + "'  less than 0");
    } else if (aY >= getYDimension()) {
      throw new WrongCoordinateException("y value '" + aY + "'  equals or is greater than maximum value '" + getYDimension() + "'");
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
