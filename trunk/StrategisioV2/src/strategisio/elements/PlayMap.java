package strategisio.elements;

import java.io.File;
import java.util.ArrayList;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import strategisio.XmlReader;
import strategisio.elements.constants.Direction;
import strategisio.elements.constants.Ground;
import strategisio.elements.fields.Field;
import strategisio.elements.fields.Grass;
import strategisio.elements.figures.Figure;
import strategisio.elements.figures.Medic;
import strategisio.elements.figures.Miner;
import strategisio.elements.items.Bomb;
import strategisio.elements.items.FakeFlag;
import strategisio.elements.items.Flag;
import strategisio.elements.items.Item;
import strategisio.elements.items.Trap;
import strategisio.exceptions.UnknownFieldGroundException;

/**
 * 
 * the playmap
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
    this(aDimension, aDimension);
  }

  /**
   * creates a map
   * 
   * @param anXDimension
   * @param aYDimension
   *            for size of the map (anXDimension x aYDimension)
   */
  public PlayMap(int anXDimension, int aYDimension) {
    fields = new Field[aYDimension][anXDimension];
  }

  /**
   * creates a map via XML File
   * 
   * @param aFile
   */
  public PlayMap(File aFile) {
    // TODO Reading an XML file
    XmlReader tmpReader = new XmlReader();
    Document tmpDocument = tmpReader.getDocumentFrom(aFile);
    /*
     * Read XML file from path, save data to field collection
     * 
     */

    NodeList tmpFieldList = tmpDocument.getElementsByTagName("field");
    Node tmpNode;
    NodeList tmpChilds;

    // int x = 0;

    // GANZ DOLL BÄH !!!
    // int z = (int) Math.sqrt((double) tmpFieldList.getLength());

    /*
     * Structure in XML has to be the same as it is here. see mapdummy.xml
     * x1.y1, x1.y2, x1.y3, x2.y1, x2.y2, etc.
     */

    for (int y = 0; y < tmpFieldList.getLength(); y++) {
      tmpNode = tmpFieldList.item(y);
      tmpChilds = tmpNode.getChildNodes();

      tmpChilds.notify(); // just to avoid WARNINGS :)
      /*
       * if ((y % z == 0) && y > 0) { x += 1; fields[x][(y - (x * z))] = new
       * Field(tmpChilds.item(0).getTextContent(), new Figure(
       * tmpChilds.item(1).getTextContent()), new
       * Item(tmpChilds.item(2).getTextContent())); } else { fields[x][(y - (x *
       * z))] = new Field(tmpChilds.item(0).getTextContent(), new Figure(
       * tmpChilds.item(1).getTextContent()), new
       * Item(tmpChilds.item(2).getTextContent())); }
       */
    }
  }

  /**
   * sets field type for specified field
   * 
   * @param anX
   * @param aY
   * @param aFieldGround
   * @throws UnknownFieldGroundException
   */
  public void setFieldGround(int anX, int aY, int aFieldGround) throws UnknownFieldGroundException {
    fields[aY][anX] = Ground.getFieldGround(aFieldGround);
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
    aPlaceable.setCurrentCoordinates(anX, aY);
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
    if (aPlaceable instanceof Item) {
      if (tmpField instanceof Grass) {
        return true;
      } else {
        return false;
      }
    } else if (aPlaceable instanceof Figure) {
      int tmpFieldGround = tmpField.getGround();
      return checkGround((Figure) aPlaceable, tmpFieldGround);
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
      // if there is an enemy Figure, a fight has to start
      if (tmpEnemy instanceof Figure) {
        Combat tmpCombat = new Combat();
        tmpEnemy = fetchSetter(anX, aY);
        // the winner of the fight is placed on the field
        Figure tmpWinner = tmpCombat.init(aFigure, (Figure) tmpEnemy);
        position(tmpWinner, anX, aY);
      }
      // if there is an enemy Item, we have to do some more logic
      else if (tmpEnemy instanceof Item) {
        Item tmpItem = (Item) tmpEnemy;
        if (tmpItem instanceof FakeFlag) {
          // in case of a FakeFlag, the flag is fetched from the field
          // an the figure is placed on the field
          fetchSetter(anX, aY);
          position(aFigure, anX, aY);
        } else if (tmpItem instanceof Trap) {
          // in case of a Trap, we first look if there is a catched
          // figure in it
          Trap tmpTrap = (Trap) tmpItem;
          Figure tmpCatched = tmpTrap.getCatched();
          if (tmpCatched == null) {
            // if the moving figure is a medic, we disable the trap
            if (aFigure instanceof Medic) {
                fetchSetter(anX, aY);
                position(aFigure, anX, aY);          
            } else {
              tmpTrap.setCatched(aFigure);
            }
          } else {
            // if the trap has a catched figure, and the moving
            // figure is a medic, 
            if (aFigure instanceof Medic) {
              if(aFigure.getId() != tmpTrap.getId()){
                // the trap is disabled and the
                // catched figure is placed on the selected field. The
                // medic stays on his initial field, in case of an enemy trap!
                fetchSetter(anX, aY);
                position(tmpTrap.getCatched(), anX, aY);
                int[] tmpCurrentCoordinates = aFigure.getCurrentCoordinates();
                position(aFigure, tmpCurrentCoordinates[0], tmpCurrentCoordinates[1]);
                // stays on the same mapfield when freeing a catched
                // figure
              }
              else{
                // the trap stays enabled and the
                // catched figure is defeated. The
                // medic stays on his initial field, in case of an team trap!
                tmpTrap.setCatched(null);
                int[] tmpCurrentCoordinates = aFigure.getCurrentCoordinates();
                position(aFigure, tmpCurrentCoordinates[0], tmpCurrentCoordinates[1]);
              }
            }
          }
        } else if (tmpItem instanceof Bomb) {
          // in case of a Bomb, a Figure is bombed away, except the
          // Figure is a Miner...
          if (aFigure instanceof Miner) {
            // ... then the bomb is taken away and the miner moves
            // to the field
            fetchSetter(anX, aY);
            position(aFigure, anX, aY);
          } else {
            fetchSetter(anX, aY);
          }

        } else if (tmpItem instanceof Flag) {
          fetchSetter(anX, aY);
          position(aFigure, anX, aY);
          // TODO Logic (Method?) what if Figure finds Flag
          /* The game ends here... maybe use a return param and then call
           * a method in game? would be nasty...
           * another way to end the game is defeat all enemies...
           */ 
        }
      }

    } else {
      // move without problems
      position(aFigure, anX, aY);

    }
  }

  /**
   * Does the check before move().
   * 
   * @param aFigure
   * @param tmpOldX
   * @param tmpOldY
   *            where the figure comes from (anOldX/anOldY)
   * @param aNewX
   * @param aNewY
   *            where it wants to go to (aNewX/aNewY)
   * @return true if moving to the specified field is possible
   */
  public boolean checkMovingPossibility(Figure aFigure, int aNewX, int aNewY) {
    return (checkGround(aFigure, aNewX, aNewY) && checkIfIsReachable(aFigure, aNewX, aNewY) && checkIfIsEmptyOrEnemy(
        aFigure, aNewX, aNewY)) ? true : false;
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

    for (int y = 0; y < getYDimension(); y++) {
      for (int x = 0; x < getXDimension(); x++) {
        if (checkMovingPossibility(aFigure, x, y)) {
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
   * Checks if the figure could reach the field (checks the direction)
   */
  private boolean checkIfIsReachable(Figure aFigure, int aNewX, int aNewY) {
    int[] tmpCoordinates = aFigure.getCurrentCoordinates();
    if (tmpCoordinates[0] == aNewX && tmpCoordinates[1] == aNewY) {
      // same field
      return true;
    } else if (tmpCoordinates[0] == aNewX) {
      // vertical move
      if (checkIfDistanceIsSolvable(tmpCoordinates[1], aNewY, aFigure.getNormalSteps())) {
        return checkIfIsReachableForNonDiagonalMove(aFigure, tmpCoordinates[1], aNewY);
      }
      return false;
    } else if (tmpCoordinates[1] == aNewY) {
      // horizontal move
      if (checkIfDistanceIsSolvable(tmpCoordinates[0], aNewX, aFigure.getNormalSteps())) {
        return checkIfIsReachableForNonDiagonalMove(aFigure, tmpCoordinates[0], aNewX);
      }
      return false;
    } else {
      // diagonal move
      // one check lasts (decided to check the change of x-coordinate)
      if (checkIfDistanceIsSolvable(tmpCoordinates[0], aNewX, aFigure.getDiagonalSteps())) {
        return checkIfIsReachableForDiagonalMove(aFigure, aNewX, aNewY);
      }
      return false;
    }
  }

  /**
   * Checks if the figure could reach the field (checks the distance between the
   * old and the new one and if the figures has enough steps to move there)
   */
  private boolean checkIfDistanceIsSolvable(int anOldCoordinate, int aNewCoordinate, int aStepNumber) {
    return Math.abs(anOldCoordinate - aNewCoordinate) <= aStepNumber;
  }

  /**
   * Checks if the figure could reach the field (checks the fields between the
   * old and the new one)
   */
  private boolean checkIfIsReachableForNonDiagonalMove(Figure aFigure, int anOldCoordinate, int aNewCoordinate) {
    int tmpDirection = detectDirection(anOldCoordinate, aNewCoordinate);
    Field tmpField;
    for (int i = 1; i < Math.abs(aNewCoordinate - anOldCoordinate); i++) {
      tmpField = getField(anOldCoordinate, anOldCoordinate + (i * tmpDirection));
      if (!checkGround(aFigure, tmpField.getGround())) {
        return false;
      }
    }
    return true;
  }

  /**
   * Checks if the figure could diagonally reach the field (checks the fields
   * between the old and the new one)
   */
  private boolean checkIfIsReachableForDiagonalMove(Figure aFigure, int aNewX, int aNewY) {
    int[] tmpCoordinates = aFigure.getCurrentCoordinates();
    int tmpHorizontalDirection = detectDirection(tmpCoordinates[0], aNewX);
    int tmpVerticalDirection = detectDirection(tmpCoordinates[1], aNewY);
    Field tmpField;
    for (int i = 1; i < Math.abs(aNewX - tmpCoordinates[0]); i++) {
      tmpField = getField(tmpCoordinates[0] + (i * tmpHorizontalDirection), tmpCoordinates[1]
          + (i * tmpVerticalDirection));
      if (!checkGround(aFigure, tmpField.getGround())) {
        return false;
      }
    }
    return true;
  }

  /**
   * Checks if the direction from old coordinate to new coordinate
   * 
   * @throws IllegalArgumentException
   *             if the coordinates are equal
   */
  private int detectDirection(int anOldCoordinate, int aNewCoordinate) throws IllegalArgumentException {
    if (aNewCoordinate - anOldCoordinate > 0) {
      return Direction.DOWN_OR_RIGHT;
    } else if (aNewCoordinate - anOldCoordinate < 0) {
      return Direction.UP_OR_LEFT;
    } else {
      throw new IllegalArgumentException("Method does not support the direction detect for equal coordinates.");
    }
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

    if (aFigure.getId() != tmpFieldPlaceable.getId()) {
      if (tmpFieldPlaceable instanceof Trap) {
        Trap tmpTrap = (Trap) tmpFieldPlaceable;
        if (tmpTrap.getCatched() == null) {
          return true;
        } else {
          if (aFigure instanceof Medic) {
            return true;
          }
          return false;
        }
      }
      return true;
    }
    return false;

    // return (aFigure.getId() == tmpFieldPlaceable.getId()) ? false : true;
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

  
}
