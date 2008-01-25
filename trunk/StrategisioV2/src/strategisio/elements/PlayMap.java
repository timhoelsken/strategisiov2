package strategisio.elements;

import java.util.ArrayList;

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
   * @return true
   */
  public boolean move(Figure aFigure, int anX, int aY) {
    if (!checkIfIsEmpty(anX, aY) && checkIfIsEnemy(aFigure, anX, aY)) {
      Placeable tmpEnemy = getSetter(anX, aY);
      // TODO differentiate between items and figures
      tmpEnemy.getId(); // to avoid warning :)
      return false;
    } else {
      // move without problems
      position(aFigure, anX, aY);
      return true;
    }
  }

  /**
   * Does the check before move().
   * 
   * @param aFigure
   * @param anOldX
   * @param anOldY
   *            where the figure comes from (anOldX/anOldY)
   * @param aNewX
   * @param aNewY
   *            where it wants to go to (aNewX/aNewY)
   * @return true if moving to the specified field is possible
   */
  public boolean checkMovingPossibility(Figure aFigure, int anOldX, int anOldY, int aNewX, int aNewY) {
    return (checkGround(aFigure, anOldX, anOldY) && checkIfIsReachable(aFigure, anOldX, anOldY, aNewX, aNewY) && checkIfIsEmptyOrEnemy(aFigure, aNewX, aNewY)) ? true : false;
  }

  /**
   * Returns all possibilities to move to
   * 
   * @param aFigure
   * @param anX
   * @param aY
   *            where the figure remains at the moment
   * @return an array of coordinates where a figure could be placed
   */
  public int[][] getMovingArea(Figure aFigure, int anX, int aY) {
    ArrayList<int[]> tmpMovingArea = new ArrayList<int[]>();

    // TODO not nice but works without further effort :)
    for (int y = 0; y < getYDimension(); y++) {
      for (int x = 0; x < getXDimension(); x++) {
        if (checkMovingPossibility(aFigure, anX, aY, x, y)) {
          int[] tmpCoordinates = new int[2];
          tmpCoordinates[0] = anX;
          tmpCoordinates[1] = aY;
          tmpMovingArea.add(tmpCoordinates);
        }
      }
    }
    return (int[][]) tmpMovingArea.toArray();
  }

  /**
   * Checks if the figure could reach the field
   */
  private boolean checkIfIsReachable(Figure aFigure, int anOldX, int anOldY, int aNewX, int aNewY) {
    int tmpNormalSteps = aFigure.getNormalSteps();
    // TODO Have fun to understand THIS logic! Really weird....
    if (anOldX == aNewX && anOldY == aNewY) {
      // same field
      return true;
    } else if (anOldX == aNewX) {
      // vertical move
      Field tmpField;
      if (checkIfIsReachable(anOldY, aNewY, tmpNormalSteps)) {
        if (aNewY - anOldY > 0) {
          for (int i = 1; i <= Math.abs(aNewY - anOldY); i++) {
            tmpField = getField(anOldX, anOldY + i);
            if (!checkGround(aFigure, tmpField.getGround())) {
              return false;
            }
          }
          return true;
        } else {
          for (int i = 1; i <= Math.abs(aNewY - anOldY); i++) {
            tmpField = getField(anOldX, anOldY - i);
            if (!checkGround(aFigure, tmpField.getGround())) {
              return false;
            }
          }
          return true;
        }
      }
      return false;
    } else if (anOldY == aNewY) {
      // horizontal move
      Field tmpField;
      if (checkIfIsReachable(anOldX, aNewX, tmpNormalSteps)) {
        if (aNewX - anOldX > 0) {
          for (int i = 1; i <= Math.abs(aNewX - anOldX); i++) {
            tmpField = getField(anOldX + i, anOldY);
            if (!checkGround(aFigure, tmpField.getGround())) {
              return false;
            }
          }
          return true;
        } else {
          for (int i = 1; i <= Math.abs(aNewX - anOldX); i++) {
            tmpField = getField(anOldX - i, anOldY);
            if (!checkGround(aFigure, tmpField.getGround())) {
              return false;
            }
          }
          return true;
        }
      }
      return false;
    } else {
      // diagonal move
      Field tmpField;
      int tmpDiagonalSteps = aFigure.getDiagonalSteps();
      if (checkIfIsReachable(anOldX, aNewX, tmpDiagonalSteps) && checkIfIsReachable(anOldY, aNewY, tmpDiagonalSteps)) {
        if (aNewX - anOldX > 0 && aNewY - anOldY > 0) {
          for (int i = 1; i <= Math.abs(aNewX - anOldX); i++) {
            tmpField = getField(anOldX + i, anOldY + i);
            if (!checkGround(aFigure, tmpField.getGround())) {
              return false;
            }
          }
          return true;
        } else if (aNewX - anOldX > 0 && aNewY - anOldY < 0) {
          for (int i = 1; i <= Math.abs(aNewX - anOldX); i++) {
            tmpField = getField(anOldX + i, anOldY - i);
            if (!checkGround(aFigure, tmpField.getGround())) {
              return false;
            }
          }
          return true;
        } else if (aNewX - anOldX < 0 && aNewY - anOldY > 0) {
          for (int i = 1; i <= Math.abs(aNewX - anOldX); i++) {
            tmpField = getField(anOldX - i, anOldY + i);
            if (!checkGround(aFigure, tmpField.getGround())) {
              return false;
            }
          }
          return true;
        } else {
          for (int i = 1; i <= Math.abs(aNewX - anOldX); i++) {
            tmpField = getField(anOldX - i, anOldY - i);
            if (!checkGround(aFigure, tmpField.getGround())) {
              return false;
            }
          }
          return true;
        }
      }
      return false;
    }
  }

  private boolean checkIfIsReachable(int anOldCoordinate, int aNewCoordinate, int aStepNumber) {
    return Math.abs(anOldCoordinate - aNewCoordinate) <= aStepNumber;
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
  // * Checks if the given coordinates are inside of the map dimensions
  // */
  // private boolean checkCoordinates(int anX, int aY) {
  // return (anX < 0 || anX > getXDimension() || aY < 0 || aY > getYDimension())
  // ? false : true;
  // }
}
