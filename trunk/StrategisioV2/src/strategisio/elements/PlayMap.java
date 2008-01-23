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
    fields = new Field[aYDimension][anXDimension];
  }

  /**
   * sets field type for specified field
   *
   * @param anX
   * @param aY
   * @param aFieldType
   * @throws UnknownFieldTypeException
   */
  public void setFieldType(int anX, int aY, int aFieldType) throws UnknownFieldTypeException {
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
    fields[aY][anX] = tmpField;
  }

  /**
   * Sets the Movable on the specified field. Checking is necessary before!
   *
   * @param aFigure
   * @param anX
   * @param aY
   */
  public void move(Figure aFigure, int anX, int aY) {
    if (!checkIfIsEmpty(anX, aY) && checkIfIsEnemy(aFigure, anX, aY)) {
      Movable tmpEnemy = getSetter(anX, aY);
      //TODO differentiate between items and figures
      tmpEnemy.getId(); // to avoid warning :)
    } else {
      // move without problems
      getField(anX, aY).setSetter(aFigure);
    }
  }

  /**
   * Checks if it is allowed for the Figure to move on the field.
   *
   * @param aFigure
   * @param anX
   * @param aY
   * @return
   */
  public boolean checkMovingPossibility(Figure aFigure, int anX, int aY) {
    return (checkGround(aFigure, anX, aY) && checkIfIsEmptyOrEnemy(aFigure, anX, aY)) ? true : false;
  }

  /**
   * Checks if the specified field is empty or filled by an enemy Movable.
   */
  private boolean checkIfIsEmptyOrEnemy(Figure aFigure, int anX, int aY) {
    return (checkIfIsEmpty(anX, aY) || (checkIfIsEnemy(aFigure, anX, aY))) ? true : false;
  }

  /**
   * Checks if there is an enemy on this field.
   */
  private boolean checkIfIsEnemy(Figure aFigure, int anX, int aY) {
    Movable tmpFieldMovable = getSetter(anX, aY);
    return (aFigure.getId() == tmpFieldMovable.getId()) ? false : true;
  }

  /**
   * Sets the Movable on the specified field. Checking is necessary before!
   *
   * @param aMovable
   * @param anX
   * @param aY
   */
  public void position(Movable aMovable, int anX, int aY) {
    getField(anX, aY).setSetter(aMovable);
  }

  /**
   * Checks if specified field is free and if the ground fits to the given
   * Movable.
   *
   * @param aMovable
   * @param anX
   * @param aY
   * @return
   */
  public boolean checkPositioningPossibility(Movable aMovable, int anX, int aY) {
    return (checkIfIsEmpty(anX, aY) && checkGround(aMovable, anX, aY)) ? true : false;
  }

  /**
   * Checks if the given Movable fits on the ground of the specified field
   */
  private boolean checkGround(Movable aMovable, int anX, int aY) {
    Field tmpField = getField(anX, aY);
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
    return fields[0].length;
  }

  private int getYDimension() {
    return fields.length;
  }

  private Field getField(int anX, int aY) {
    return fields[aY][anX];
  }

  private Movable getSetter(int anX, int aY) {
    Field tmpField = getField(anX, aY);
    Movable tmpMovable = tmpField.getSetter();
    return tmpMovable;
  }

  /**
   * shows map on console
   */
  public void showMap() {
    System.out.println();
    System.out.println();
    String tmpFirstLine = "       ";
    for (int i = 0; i < getXDimension(); i++) {
      tmpFirstLine += i;
      if (i < 10) {
        tmpFirstLine += "         ";
      } else {
        tmpFirstLine += "        ";
      }
    }
    System.out.println(tmpFirstLine);

    for (int y = 0; y < getYDimension(); y++) {
      System.out.println();
      String tmpFieldRow = "  " + y;
      if (y < 10) {
        tmpFieldRow += "   ";
      } else {
        tmpFieldRow += "  ";
      }
      for (int x = 0; x < getXDimension(); x++) {
        if (getSetter(x, y) != null) {
          Movable tmpMovable = getSetter(x, y);
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
