package strategisio.elements;

import strategisio.elements.constants.Ground;
import strategisio.elements.fields.Field;
import strategisio.elements.fields.Grass;
import strategisio.elements.fields.Mountain;
import strategisio.elements.fields.Water;
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
   * Is called with the current field coordinates! Are used to calculate moving
   * area.
   *
   * @param aFigure
   * @param anX
   * @param aY
   */
  public void selectFieldToMoveTo(Figure aFigure, int anX, int aY) {
    int[][] tmpMovingArea = getMovingArea(aFigure, anX, aY);
    int i = 0;
    // TODO some game logic
    move(aFigure, tmpMovingArea[i][0], tmpMovingArea[i][1]);
  }

  /**
   * Sets the placeAble on the specified field. Checking is necessary before!
   *
   * @param aFigure
   * @param anX
   * @param aY
   */
  private void move(Figure aFigure, int anX, int aY) {
    if (!checkIfIsEmpty(anX, aY) && checkIfIsEnemy(aFigure, anX, aY)) {
      Placeable tmpEnemy = getSetter(anX, aY);
      // TODO differentiate between items and figures
      tmpEnemy.getId(); // to avoid warning :)
    } else {
      // move without problems
      getField(anX, aY).setSetter(aFigure);
    }
  }

  /**
   * Checks if it is allowed for the Figure to move on the field. FOR INITIAL
   * SETTING
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
   * Checks if the specified field is empty or filled by an enemy placeAble.
   */
  private boolean checkIfIsEmptyOrEnemy(Figure aFigure, int anX, int aY) {
    return (checkIfIsEmpty(anX, aY) || (checkIfIsEnemy(aFigure, anX, aY))) ? true : false;
  }

  /**
   * Checks if there is an enemy on this field.
   */
  private boolean checkIfIsEnemy(Figure aFigure, int anX, int aY) {
    Placeable tmpFieldPlaceable = getSetter(anX, aY);
    return (aFigure.getId() == tmpFieldPlaceable.getId()) ? false : true;
  }

  /**
   * Sets the placeAble on the specified field. Checking is necessary before!
   *
   * @param aPlaceable
   * @param anX
   * @param aY
   */
  public void position(Placeable aPlaceable, int anX, int aY) {
    getField(anX, aY).setSetter(aPlaceable);
  }

  /**
   * Checks if the given placeAble fits on the ground of the specified field
   */
  private boolean checkGround(Placeable aPlaceable, int anX, int aY) {
    Field tmpField = getField(anX, aY);
    if (tmpField instanceof Grass){
      return true;
    }
    else{

    if (aPlaceable instanceof Figure) {
      Figure tmpFigure = (Figure) aPlaceable;
        if (tmpField instanceof Mountain && tmpFigure.getGroundAuthority() == 1) {
          return true;
        } else if (tmpField instanceof Water && tmpFigure.getGroundAuthority() == 2) {
          return true;
        } else {
          return false;
        }
      }
    return false;

    }
  }

  /**
   * Checks if the specified field is empty
   */
  private boolean checkIfIsEmpty(int anX, int aY) {
    Placeable tmpPlaceable = getSetter(anX, aY);
    if (tmpPlaceable == null) {
      return true;
    } else {
      return false;
    }
  }

  /**
   * Checks if the given coordinates are inside of the map Dimensions
   *
   * @param anX
   * @param aY
   * @return true when coordinates are inside of the map Dimensions
   */
  private boolean checkCoordinates(int anX, int aY) {
    if (anX < 0 || anX > getXDimension() || aY < 0 || aY > getYDimension()) {
      return false;
    } else {
      return true;
    }
  }

  /**
   *
   * @return the xDimension
   */
  public int getXDimension() {
    return fields[0].length;
  }

  /**
   *
   * @return the yDimension
   */
  public int getYDimension() {
    return fields.length;
  }

  private Field getField(int anX, int aY) {
    return fields[aY][anX];
  }

  /**
   * Gets the setter out of the field (deletes it)
   *
   * @param anX
   * @param aY
   * @return the setter from the specified field
   */
  public Placeable fetchSetter(int anX, int aY) {
    Placeable tmpPlaceable = getSetter(anX, aY);
    delSetter(anX, aY);
    return tmpPlaceable;
  }

  /**
   *
   * @param anX
   * @param aY
   * @return a placeAble
   */
  public Placeable getSetter(int anX, int aY) {
    Field tmpField = getField(anX, aY);
    Placeable tmpPlaceable = tmpField.getSetter();
    return tmpPlaceable;
  }

  private void delSetter(int anX, int aY) {
    Field tmpField = getField(anX, aY);
    tmpField.setSetter(null);
  }

  /**
   *
   * SINGLE FIELD
   *
   * including logic of ground or teamMate in the way
   *
   * @param aFigure
   * @param anX
   * @param aY
   * @return true if it is possible to place a figure within the distance
   */
  public boolean checkFigurePositioningPossibility(Figure aFigure, int anX, int aY) {
    return (checkCoordinates(anX, aY) && checkGround(aFigure, anX, aY) && checkIfIsEmptyOrEnemy(aFigure, anX, aY)) ? true : false;
  }

  /**
   *
   * SINGLE FIELD
   *
   * including logic of ground or teamMate in the way
   * @param aPlaceable
   *
   * @param anX
   * @param aY
   * @return true if it is possible to place a figure within the distance
   */
  public boolean checkItemPositioningPossibility(Placeable aPlaceable, int anX, int aY) {
    return (checkIfIsEmpty(anX, aY) && checkGround(aPlaceable, anX, aY)) ? true : false;
  }

  /**
   *
   * AREA
   *
   * including logic of moving not further then possible (ground)
   *
   * @return an array of fields where a figure could be placed
   * @param aFigure
   * @param anX
   * @param aY
   */
  private int[][] getMovingArea(Figure aFigure, int anX, int aY) {

    int tmpNormalSteps = aFigure.getNormalSteps();
    int tmpDiagonalSteps = aFigure.getDiagonalSteps();
    int[][] tmpMovingArea = new int[tmpNormalSteps * 4 + tmpDiagonalSteps * 4][1];
    int j = 0;

    /*
     * All fields to the left
     */
    for (int i = 1; i <= tmpNormalSteps; i++) {
      if (checkFigurePositioningPossibility(aFigure, anX - i, aY)) {
        tmpMovingArea[j][0] = anX - i;
        tmpMovingArea[j][1] = aY;
        j++;
      } else {
        i = tmpNormalSteps + 1;
      }
    }

    // All fields to the right
    for (int i = 1; i <= tmpNormalSteps; i++) {
      if (checkFigurePositioningPossibility(aFigure, anX + i, aY)) {
        tmpMovingArea[j][0] = anX + i;
        tmpMovingArea[j][1] = aY;
        j++;
      } else {
        i = tmpNormalSteps + 1;
      }
    }

    // All fields to the bottom
    for (int i = 1; i <= tmpNormalSteps; i++) {
      if (checkFigurePositioningPossibility(aFigure, anX, aY + i)) {
        tmpMovingArea[j][0] = anX;
        tmpMovingArea[j][1] = aY + i;
        j++;
      } else {
        i = tmpNormalSteps + 1;
      }
    }

    // All fields to the top
    for (int i = 1; i <= tmpNormalSteps; i++) {
      if (checkFigurePositioningPossibility(aFigure, anX, aY - i)) {
        tmpMovingArea[j][0] = anX;
        tmpMovingArea[j][1] = aY - i;
        j++;
      } else {
        i = tmpNormalSteps + 1;
      }
    }

    if (tmpDiagonalSteps > 0) {
      // All diagonal fields top right
      for (int i = 1; i <= tmpDiagonalSteps; i++) {
        if (checkFigurePositioningPossibility(aFigure, anX + i, aY - i)) {
          tmpMovingArea[j][0] = anX + i;
          tmpMovingArea[j][1] = aY - i;
          j++;
        } else {
          i = tmpNormalSteps + 1;
        }
      }

      // All diagonal fields bottom right
      for (int i = 1; i <= tmpDiagonalSteps; i++) {
        if (checkFigurePositioningPossibility(aFigure, anX + i, aY + i)) {
          tmpMovingArea[j][0] = anX + i;
          tmpMovingArea[j][1] = aY + i;
          j++;
        } else {
          i = tmpNormalSteps + 1;
        }
      }

      // All diagonal fields bottom left
      for (int i = 1; i <= tmpDiagonalSteps; i++) {
        if (checkFigurePositioningPossibility(aFigure, anX - i, aY + i)) {
          tmpMovingArea[j][0] = anX - i;
          tmpMovingArea[j][1] = aY + i;
          j++;
        } else {
          i = tmpNormalSteps + 1;
        }
      }

      // All diagonal fields top left
      for (int i = 1; i <= tmpDiagonalSteps; i++) {
        if (checkFigurePositioningPossibility(aFigure, anX - i, aY - i)) {
          tmpMovingArea[j][0] = anX - i;
          tmpMovingArea[j][1] = aY - i;
          j++;
        } else {
          i = tmpNormalSteps + 1;
        }
      }
    }
    return tmpMovingArea;
  }
}
