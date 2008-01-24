package strategisio.elements;

import strategisio.elements.constants.Ground;
import strategisio.elements.fields.Field;
import strategisio.elements.fields.Grass;
import strategisio.elements.fields.Mountain;
import strategisio.elements.fields.Water;
import strategisio.elements.figures.Figure;
import strategisio.elements.items.Item;
import strategisio.exceptions.UnknownFieldTypeException;

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

  private void initFields(int anXDimension, int aYDimension) {
    fields = new Field[aYDimension][anXDimension];
  }

  /**
   * Positions the placeable (initially) on the specified field. Checking with
   * checkPositioningPossibility() is necessary before!
   *
   * @param aPlaceable
   * @param anX
   * @param aY
   */
  public void position(Placeable aPlaceable, int anX, int aY) {
    getField(anX, aY).setSetter(aPlaceable);
  }

  /**
   * Does the check before position().
   *
   * @param aPlaceable
   * @param anX
   * @param aY
   * @return true if positioning is allowed here
   */
  public boolean checkPositioningPossibility(Placeable aPlaceable, int anX, int aY) {
    return (checkIfIsEmpty(anX, aY) && checkGround(aPlaceable, anX, aY)) ? true : false;
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
   * Checks if the given placeAble fits on the ground of the specified field
   */
  private boolean checkGround(Placeable aPlaceable, int anX, int aY) {
    Field tmpField = getField(anX, aY);
    if (tmpField instanceof Grass) {
      if (aPlaceable instanceof Item) {
        return true;
      } else if (aPlaceable instanceof Figure) {
        Figure tmpFigure = (Figure) aPlaceable;
        return checkGround(tmpFigure, Ground.GRASS);
      }
    } else if (tmpField instanceof Mountain) {
      if (aPlaceable instanceof Item) {
        return false;
      } else if (aPlaceable instanceof Figure) {
        Figure tmpFigure = (Figure) aPlaceable;
        return checkGround(tmpFigure, Ground.MOUNTAIN);
      }
    } else if (tmpField instanceof Water) {
      if (aPlaceable instanceof Item) {
        return false;
      } else if (aPlaceable instanceof Figure) {
        Figure tmpFigure = (Figure) aPlaceable;
        return checkGround(tmpFigure, Ground.WATER);
      }
    }
    return false;
  }

  private boolean checkGround(Figure aFigure, int aFieldGround) {
    int[] tmpGroundAuthorities = aFigure.getGroundAuthorities();
    for (int i = 0; i < tmpGroundAuthorities.length; i++) {
      if (tmpGroundAuthorities[i] == aFieldGround) {
        return true;
      }
    }
    return false;
  }

  /**
   * Moves the figure (during the game) onto the specified field. Checking with
   * checkMovingPossibility() is necessary before!
   *
   * @param aFigure
   * @param anX
   * @param aY
   */
  public void move(Figure aFigure, int anX, int aY) {
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
   * Does the check before move().
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
   * Checks if the specified field is empty or filled by an enemy placeable.
   */
  private boolean checkIfIsEmptyOrEnemy(Figure aFigure, int anX, int aY) {
    return (checkIfIsEmpty(anX, aY) || (checkIfIsEnemy(aFigure, anX, aY))) ? true : false;
  }

  /**
   * Checks if there's an enemy on this field. Field has to be set to use this
   * method.
   */
  private boolean checkIfIsEnemy(Figure aFigure, int anX, int aY) {
    Placeable tmpFieldPlaceable = getSetter(anX, aY);
    return (aFigure.getId() == tmpFieldPlaceable.getId()) ? false : true;
  }

  private Field getField(int anX, int aY) {
    return fields[aY][anX];
  }

  /**
   * @param anX
   * @param aY
   * @return the setter of the specified field (might be NULL)
   */
  public Placeable getSetter(int anX, int aY) {
    Field tmpField = getField(anX, aY);
    Placeable tmpPlaceable = tmpField.getSetter();
    return tmpPlaceable;
  }

  /**
   * Gets the setter out of the field (deletes it from the map)
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
   * Deletes the setter from the field
   */
  private void delSetter(int anX, int aY) {
    Field tmpField = getField(anX, aY);
    tmpField.setSetter(null);
  }

  /**
   * Checks if the given coordinates are inside of the map dimensions
   */
  private boolean checkCoordinates(int anX, int aY) {
    return (anX < 0 || anX > getXDimension() || aY < 0 || aY > getYDimension()) ? false : true;
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


  // /**
  // * Is called with the current field coordinates! Use to calculate moving
  // area.
  // *
  // * @param aFigure
  // * @param anX
  // * @param aY
  // */
  // public void selectFieldToMoveTo(Figure aFigure, int anX, int aY) {
  // int[][] tmpMovingArea = getMovingArea(aFigure, anX, aY);
  // int i = 0;
  // // TODO some game logic
  // move(aFigure, tmpMovingArea[i][0], tmpMovingArea[i][1]);
  // }

  // /**
  // *
  // * AREA
  // *
  // * including logic of moving not further then possible (ground)
  // *
  // * @param aFigure
  // * @param anX
  // * @param aY
  // * @return an array of fields where a figure could be placed
  // */
  // private int[][] getMovingArea(Figure aFigure, int anX, int aY) {
  //
  // int tmpNormalSteps = aFigure.getNormalSteps();
  // int tmpDiagonalSteps = aFigure.getDiagonalSteps();
  // int[][] tmpMovingArea = new int[tmpNormalSteps * 4 + tmpDiagonalSteps *
  // 4][1];
  // int j = 0;
  //
  // // All fields to the left
  // for (int i = 1; i <= tmpNormalSteps; i++) {
  // if (checkFigurePositioningPossibility(aFigure, anX - i, aY)) {
  // tmpMovingArea[j][0] = anX - i;
  // tmpMovingArea[j][1] = aY;
  // j++;
  // } else {
  // i = tmpNormalSteps + 1;
  // }
  // }
  //
  // // All fields to the right
  // for (int i = 1; i <= tmpNormalSteps; i++) {
  // if (checkFigurePositioningPossibility(aFigure, anX + i, aY)) {
  // tmpMovingArea[j][0] = anX + i;
  // tmpMovingArea[j][1] = aY;
  // j++;
  // } else {
  // i = tmpNormalSteps + 1;
  // }
  // }
  //
  // // All fields to the bottom
  // for (int i = 1; i <= tmpNormalSteps; i++) {
  // if (checkFigurePositioningPossibility(aFigure, anX, aY + i)) {
  // tmpMovingArea[j][0] = anX;
  // tmpMovingArea[j][1] = aY + i;
  // j++;
  // } else {
  // i = tmpNormalSteps + 1;
  // }
  // }
  //
  // // All fields to the top
  // for (int i = 1; i <= tmpNormalSteps; i++) {
  // if (checkFigurePositioningPossibility(aFigure, anX, aY - i)) {
  // tmpMovingArea[j][0] = anX;
  // tmpMovingArea[j][1] = aY - i;
  // j++;
  // } else {
  // i = tmpNormalSteps + 1;
  // }
  // }
  //
  // if (tmpDiagonalSteps > 0) {
  // // All diagonal fields top right
  // for (int i = 1; i <= tmpDiagonalSteps; i++) {
  // if (checkFigurePositioningPossibility(aFigure, anX + i, aY - i)) {
  // tmpMovingArea[j][0] = anX + i;
  // tmpMovingArea[j][1] = aY - i;
  // j++;
  // } else {
  // i = tmpNormalSteps + 1;
  // }
  // }
  //
  // // All diagonal fields bottom right
  // for (int i = 1; i <= tmpDiagonalSteps; i++) {
  // if (checkFigurePositioningPossibility(aFigure, anX + i, aY + i)) {
  // tmpMovingArea[j][0] = anX + i;
  // tmpMovingArea[j][1] = aY + i;
  // j++;
  // } else {
  // i = tmpNormalSteps + 1;
  // }
  // }
  //
  // // All diagonal fields bottom left
  // for (int i = 1; i <= tmpDiagonalSteps; i++) {
  // if (checkFigurePositioningPossibility(aFigure, anX - i, aY + i)) {
  // tmpMovingArea[j][0] = anX - i;
  // tmpMovingArea[j][1] = aY + i;
  // j++;
  // } else {
  // i = tmpNormalSteps + 1;
  // }
  // }
  //
  // // All diagonal fields top left
  // for (int i = 1; i <= tmpDiagonalSteps; i++) {
  // if (checkFigurePositioningPossibility(aFigure, anX - i, aY - i)) {
  // tmpMovingArea[j][0] = anX - i;
  // tmpMovingArea[j][1] = aY - i;
  // j++;
  // } else {
  // i = tmpNormalSteps + 1;
  // }
  // }
  // }
  // return tmpMovingArea;
  // }
}
