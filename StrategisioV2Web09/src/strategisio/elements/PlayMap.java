package strategisio.elements;

import java.io.File;
import java.util.ArrayList;

import strategisio.XmlReader;
import strategisio.elements.constants.Direction;
import strategisio.elements.constants.Ground;
import strategisio.elements.fields.Field;
import strategisio.elements.fields.Grass;
import strategisio.elements.figures.Figure;
import strategisio.elements.figures.Medic;
import strategisio.elements.figures.Miner;
import strategisio.elements.figures.Spy;
import strategisio.elements.items.Bomb;
import strategisio.elements.items.FakeFlag;
import strategisio.elements.items.Flag;
import strategisio.elements.items.Item;
import strategisio.elements.items.Trap;
import strategisio.exceptions.CoordinateOutOfIndexException;
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
   *
   */
  public PlayMap(int anXDimension, int aYDimension) {
    fields = new Field[aYDimension][anXDimension];
    for (int y = 0; y < aYDimension; y++) {
      for (int x = 0; x < anXDimension; x++) {
        try {
          fields[y][x] = Ground.getFieldGround(Ground.GRASS);
        } catch (UnknownFieldGroundException e) {
          // do nothing, will not happen
        }
      }
    }
  }

  /**
   * creates a map via XML File
   *
   * @param aFile
   * @throws UnknownFieldGroundException
   */
  public PlayMap(File aFile) throws UnknownFieldGroundException {

    XmlReader tmpReader = new XmlReader();
    int[][] tmpMapData = tmpReader.getMapdata(aFile);

    fields = new Field[tmpMapData.length][tmpMapData.length];
    for (int y = 0; y < tmpMapData.length; y++) {
      for (int x = 0; x < tmpMapData[y].length; x++) {
        fields[y][x] = Ground.getFieldGround(tmpMapData[y][x]);
      }
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
   *
   * A positioning action with checking the possibility of positioning before
   *
   * @param aPlaceable
   *
   * @param aFigure
   * @param anX
   * @param aY
   * @return true if positioning was successful
   */
  public boolean position(Placeable aPlaceable, int anX, int aY) {
    if (checkPositioningPossibility(aPlaceable, anX, aY)) {
      positionWithoutCheck(aPlaceable, anX, aY);
      return true;
    }
    return false;
  }

  /**
   * Positions the placeable (initially) on the specified field. Checking with
   * checkPositioningPossibility() is necessary before!
   *
   * @param aPlaceable
   * @param anX
   * @param aY
   */
  public void positionWithoutCheck(Placeable aPlaceable, int anX, int aY) {
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
   *
   * A move with checking the possibility of moving before
   *
   * @param aFigure
   * @param anX
   * @param aY
   * @return true if move was successful
   * @throws CoordinateOutOfIndexException
   */
  public boolean move(Figure aFigure, int anX, int aY) throws CoordinateOutOfIndexException {
    if (checkMovingPossibility(aFigure, anX, aY)) {
      moveWithoutCheck(aFigure, anX, aY);
      return true;
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
  public void moveWithoutCheck(Figure aFigure, int anX, int aY) {
    if (!checkIfIsEmpty(anX, aY) && checkIfIsEnemy(aFigure, anX, aY)) {
      Placeable tmpEnemy = getSetter(anX, aY);
      // if there is an enemy Figure, a fight has to start
      if (tmpEnemy instanceof Figure) {
        Combat tmpCombat = new Combat();
        tmpEnemy = fetchSetter(anX, aY);
        // the winner of the fight is placed on the field
        Figure tmpWinner = tmpCombat.init(aFigure, (Figure) tmpEnemy);
        positionWithoutCheck(tmpWinner, anX, aY);
      }
      // if there is an enemy Item, we have to do some more logic
      else if (tmpEnemy instanceof Item) {
        Item tmpItem = (Item) tmpEnemy;
        if (tmpItem instanceof FakeFlag) {
          // in case of a FakeFlag, the flag is fetched from the field
          // an the figure is placed on the field
          fetchSetter(anX, aY);
          positionWithoutCheck(aFigure, anX, aY);
        } else if (tmpItem instanceof Trap) {
          // in case of a Trap, we first look if there is a catched
          // figure in it
          Trap tmpTrap = (Trap) tmpItem;
          Figure tmpCatched = tmpTrap.getCatched();
          if (tmpCatched == null) {
            // if the moving figure is a medic, we disable the trap
            if (aFigure instanceof Medic) {
              fetchSetter(anX, aY);
              positionWithoutCheck(aFigure, anX, aY);
            } else {
              tmpTrap.setCatched(aFigure);
            }
          } else {
            // if the trap has a catched figure, and the moving
            // figure is a medic,
            if (aFigure instanceof Medic) {
              if (!checkFigureAndPlaceableId(aFigure, tmpTrap)) {
                // the trap is disabled and the
                // catched figure is placed on the selected
                // field. The
                // medic stays on his initial field, in case of
                // an enemy trap!
                fetchSetter(anX, aY);
                positionWithoutCheck(tmpTrap.getCatched(), anX, aY);
                int[] tmpCurrentCoordinates = aFigure.getCurrentCoordinates();
                positionWithoutCheck(aFigure, tmpCurrentCoordinates[0], tmpCurrentCoordinates[1]);
                // stays on the same mapfield when freeing a
                // catched
                // figure
              } else {
                // the trap stays enabled and the
                // catched figure is defeated. The
                // medic stays on his initial field, in case of
                // an team trap!
                tmpTrap.setCatched(null);
                int[] tmpCurrentCoordinates = aFigure.getCurrentCoordinates();
                positionWithoutCheck(aFigure, tmpCurrentCoordinates[0], tmpCurrentCoordinates[1]);
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
            positionWithoutCheck(aFigure, anX, aY);
          } else {
            fetchSetter(anX, aY);
          }

        } else if (tmpItem instanceof Flag) {
          fetchSetter(anX, aY);
          positionWithoutCheck(aFigure, anX, aY);
          // TODO Logic (Method?) what if Figure finds Flag
          /*
           * The game ends here... maybe use a return param and then call a
           * method in game? would be nasty... another way to end the game is
           * defeat all enemies...
           *
           * another Possibility would be, if there is an endless loop in Game
           * for while game is active (public int) that is set to 1 when game is
           * going on, 0 when ends by flag, -1 when ends by defeat all
           * enemies...
           */
        }
      }

    } else {
      // move without problems
      positionWithoutCheck(aFigure, anX, aY);

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
   * @throws CoordinateOutOfIndexException
   */
  // checkGround is also done by checkIfIsReachable but it is done first time
  public boolean checkMovingPossibility(Figure aFigure, int aNewX, int aNewY)
      throws CoordinateOutOfIndexException {
    return (checkGround(aFigure, aNewX, aNewY) && checkIfIsReachable(aFigure, aNewX, aNewY) && checkIfIsEmptyOrEnemy(
        aFigure, aNewX, aNewY)) ? true : false;
  }

  /**
   * Returns all possibilities to move to
   *
   * @param aFigure
   *            where the figure remains at the moment
   * @return an array of coordinates where a figure could be placed
   * @throws CoordinateOutOfIndexException
   */
  public ArrayList<int[]> getMovingArea(Figure aFigure) throws CoordinateOutOfIndexException {
    ArrayList<int[]> tmpMovingArea = new ArrayList<int[]>();

    // tmpCoordinates = aFigure.getCurrentCoordinates();
    // tmpMovingArea.add(tmpCoordinates);
    tmpMovingArea.add(aFigure.getCurrentCoordinates());

    for (int y = 0; y < getYDimension(); y++) {
      for (int x = 0; x < getXDimension(); x++) {
        if (checkMovingPossibility(aFigure, x, y)) {
          int[] tmpCoordinates = new int[2];
          tmpCoordinates[0] = x;
          tmpCoordinates[1] = y;
          tmpMovingArea.add(tmpCoordinates);
        }
      }
    }
    return tmpMovingArea;
  }

  /**
   * Checks if the figure could reach the field (checks the direction)
   */
  private boolean checkIfIsReachable(Figure aFigure, int aNewX, int aNewY)
      throws CoordinateOutOfIndexException {
    int[] tmpCurrentCoordinates = aFigure.getCurrentCoordinates();
    if (tmpCurrentCoordinates[0] == aNewX && tmpCurrentCoordinates[1] == aNewY) {
      // same field
      return true;
    } else if (tmpCurrentCoordinates[0] == aNewX) {
      // vertical move
      if (checkIfDistanceIsSolvable(tmpCurrentCoordinates[1], aNewY, aFigure.getNormalSteps())) {
        return checkIfIsReachableNonDiagonally(aFigure, aNewY, "y");
      }
      return false;
    } else if (tmpCurrentCoordinates[1] == aNewY) {
      // horizontal move
      if (checkIfDistanceIsSolvable(tmpCurrentCoordinates[0], aNewX, aFigure.getNormalSteps())) {
        return checkIfIsReachableNonDiagonally(aFigure, aNewX, "x");
      }
      return false;
    } else {
      // diagonal move
      // one check lasts (decided to check the change of x-coordinate)
      if (checkIfDistanceIsSolvable(tmpCurrentCoordinates[0], aNewX, aFigure.getDiagonalSteps())
          && checkIfDistanceIsSolvable(tmpCurrentCoordinates[1], aNewY, aFigure.getDiagonalSteps())) {
        return checkIfIsReachableDiagonally(aFigure, aNewX, aNewY);
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
  private boolean checkIfIsReachableNonDiagonally(Figure aFigure, int aNewCoordinate, String anAxis)
      throws CoordinateOutOfIndexException {
    int[] tmpCoordinates = aFigure.getCurrentCoordinates();
    int tmpDirection, tmpOldCoordinate;
    Field tmpField;
    if (anAxis.equals("y")) {
      tmpDirection = detectDirection(tmpCoordinates[1], aNewCoordinate);
      tmpOldCoordinate = tmpCoordinates[1];
    } else if (anAxis.equals("x")) {
      tmpDirection = detectDirection(tmpCoordinates[0], aNewCoordinate);
      tmpOldCoordinate = tmpCoordinates[0];
    } else {
      throw new CoordinateOutOfIndexException("The Coordinate is not in the map index.");
    }
    for (int i = 1; i <= Math.abs(aNewCoordinate - tmpOldCoordinate); i++) {
      if (anAxis.equals("y")) {
        tmpField = getField(tmpCoordinates[0], tmpCoordinates[1] + (i * tmpDirection));
      } else {
        tmpField = getField(tmpCoordinates[0] + (i * tmpDirection), tmpCoordinates[1]);
      }
      if (!checkGround(aFigure, tmpField.getGround())) {
        return false;
      }
    }
    return true;
  }

  /**
   * Checks the view of a figure Enemy figures placed on a special field can be
   * seen, but figures behind a special field can not be seen.
   *
   * @param aFigure
   * @param aNewCoordinate
   * @param anAxis
   * @return
   * @throws CoordinateOutOfIndexException
   */
  private boolean checkIfIsViewableNonDiagonally(Figure aFigure, int aNewCoordinate, String anAxis)
      throws CoordinateOutOfIndexException {
    int[] tmpCoordinates = aFigure.getCurrentCoordinates();
    int tmpDirection, tmpOldCoordinate;
    Field tmpField, tmpPreviousField = null;

    if (anAxis.equals("y")) {
      tmpDirection = detectDirection(tmpCoordinates[1], aNewCoordinate);
      tmpOldCoordinate = tmpCoordinates[1];
    } else if (anAxis.equals("x")) {
      tmpDirection = detectDirection(tmpCoordinates[0], aNewCoordinate);
      tmpOldCoordinate = tmpCoordinates[0];
    } else {
      throw new CoordinateOutOfIndexException("The Coordinate is not in the map index.");
    }

    for (int i = 1; i <= Math.abs(aNewCoordinate - tmpOldCoordinate); i++) {
      if (anAxis.equals("y")) {
        tmpField = getField(tmpCoordinates[0], tmpCoordinates[1] + (i * tmpDirection));
      } else {
        tmpField = getField(tmpCoordinates[0] + (i * tmpDirection), tmpCoordinates[1]);
      }
      if (!checkGround(aFigure, tmpField.getGround())) {
        if ((tmpPreviousField == null && Math.abs(aNewCoordinate - tmpOldCoordinate) == 1)
            || (tmpPreviousField != null && checkGround(aFigure, tmpPreviousField.getGround()))) {
          return true;
        }
        return false;
      }
      tmpPreviousField = tmpField;
    }
    return true;
  }

  /**
   * Checks if the figure could diagonally reach the field (checks the fields
   * between the old and the new one)
   */
  private boolean checkIfIsReachableDiagonally(Figure aFigure, int aNewX, int aNewY) {
    int[] tmpCurrentCoordinates = aFigure.getCurrentCoordinates();
    int tmpHorizontalDirection = detectDirection(tmpCurrentCoordinates[0], aNewX);
    int tmpVerticalDirection = detectDirection(tmpCurrentCoordinates[1], aNewY);
    Field tmpField;
    for (int i = 1; i <= Math.abs(aNewX - tmpCurrentCoordinates[0]); i++) {
      tmpField = getField(tmpCurrentCoordinates[0] + (i * tmpHorizontalDirection), tmpCurrentCoordinates[1]
          + (i * tmpVerticalDirection));
      if (!checkGround(aFigure, tmpField.getGround())) {

        return false;
      }
    }
    return true;
  }

  /**
   * Checks the view of a figure Enemy figures placed on a special field can be
   * seen, but figures behind a special field can not be seen.
   *
   * @param aFigure
   * @param aNewX
   * @param aNewY
   * @return
   */
  private boolean checkIfIsViewableDiagonally(Figure aFigure, int aNewX, int aNewY) {
    int[] tmpCurrentCoordinates = aFigure.getCurrentCoordinates();
    int tmpHorizontalDirection = detectDirection(tmpCurrentCoordinates[0], aNewX);
    int tmpVerticalDirection = detectDirection(tmpCurrentCoordinates[1], aNewY);
    Field tmpField, tmpPreviousField = null;
    for (int i = 1; i <= Math.abs(aNewX - tmpCurrentCoordinates[0]); i++) {
      tmpField = getField(tmpCurrentCoordinates[0] + (i * tmpHorizontalDirection), tmpCurrentCoordinates[1]
          + (i * tmpVerticalDirection));
      if (!checkGround(aFigure, tmpField.getGround())) {
        if ((tmpPreviousField == null && Math.abs(aNewX - tmpCurrentCoordinates[0]) == 1)
            || (tmpPreviousField != null && checkGround(aFigure, tmpPreviousField.getGround()))) {
          return true;
        }
        return false;
      }
      tmpPreviousField = tmpField;
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
    Trap tmpTrap;
    if (!checkFigureAndPlaceableId(aFigure, tmpFieldPlaceable)) {
      if (tmpFieldPlaceable instanceof Trap) {
        tmpTrap = (Trap) tmpFieldPlaceable;
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
    // Logic when there is a Teamtrap and a Team Medic wants to step on it
    else if (checkFigureAndPlaceableId(aFigure, tmpFieldPlaceable) && tmpFieldPlaceable instanceof Trap
        && aFigure instanceof Medic) {
      tmpTrap = (Trap) tmpFieldPlaceable;
      if (tmpTrap.getCatched() == null) {
        return false;
      } else {
        return true;
      }
    }
    return false;
  }

  /**
   *
   * Checks if a figure and a placeable on the field is in the same team
   *
   * @param aFigure
   * @param aPlaceable
   * @return
   */
  private boolean checkFigureAndPlaceableId(Figure aFigure, Placeable aPlaceable) {
    return (aFigure.getId() == aPlaceable.getId()) ? true : false;
  }

  /**
   * Returns all fields a figure can see the setter of
   *
   * @param aFigure
   *            where the figure remains at the moment
   * @return an array of coordinates of fields a figure can see
   * @throws CoordinateOutOfIndexException
   */
  public ArrayList<int[]> getSingleFigureViewArea(Figure aFigure) throws CoordinateOutOfIndexException {
    ArrayList<int[]> tmpViewArea = new ArrayList<int[]>();

    for (int y = 0; y < getYDimension(); y++) {
      for (int x = 0; x < getXDimension(); x++) {
        if (checkIfIsApparitionial(aFigure, x, y)) {
          int[] tmpCoordinates = new int[2];
          tmpCoordinates[0] = x;
          tmpCoordinates[1] = y;
          tmpViewArea.add(tmpCoordinates);
        }
      }
    }
    return tmpViewArea;
  }

  /**
   * Returns all fields a team can see on the map
   *
   * @param aTeam
   *            which team's view Area is needed
   * @return
   * @throws CoordinateOutOfIndexException
   */
  public ArrayList<int[]> getTeamViewArea(Team aTeam) throws CoordinateOutOfIndexException {
    ArrayList<int[]> tmpTeamViewArea = new ArrayList<int[]>();
    ArrayList<int[]> tmpFigureViewArea = new ArrayList<int[]>();
    Figure tmpFigure;
    int[] tmpTeamCoordinates, tmpFigureCoordinates;
    int tmpOldTeamViewAreaSize;
    boolean tmpAddToView = true;

    ArrayList<Figure> tmpFigureList = aTeam.getFigures();
    for (int i = 0; i < tmpFigureList.size(); i++) {
      tmpFigure = tmpFigureList.get(i);

      tmpFigureViewArea = getSingleFigureViewArea(tmpFigure);

      tmpOldTeamViewAreaSize = tmpTeamViewArea.size();
      if (tmpOldTeamViewAreaSize == 0) {
        tmpTeamViewArea = tmpFigureViewArea;
      } else {
        for (int j = 0; j < tmpFigureViewArea.size(); j++) {
          tmpFigureCoordinates = tmpFigureViewArea.get(j);
          for (int k = 0; k < tmpOldTeamViewAreaSize; k++) {
            tmpTeamCoordinates = tmpTeamViewArea.get(k);
            if (tmpTeamCoordinates[0] == tmpFigureCoordinates[0]
                && tmpTeamCoordinates[1] == tmpFigureCoordinates[1]) {
              tmpAddToView = false;
            }
          }
          if (tmpAddToView) {
            tmpTeamViewArea.add(tmpFigureViewArea.get(j));
          }
          tmpAddToView = true;
        }
      }

    }
    return tmpTeamViewArea;
  }

  /**
   * For test only
   *
   * @param aFigure
   * @param aNewX
   * @param aNewY
   * @return
   * @throws CoordinateOutOfIndexException
   */
  public boolean checkViewForTest(Figure aFigure, int aNewX, int aNewY) throws CoordinateOutOfIndexException {
    return checkIfIsApparitionial(aFigure, aNewX, aNewY) ? true : false;
  }

  /**
   * checks if a figure can see what's on a field
   *
   * @param aFigure
   * @param aNewX
   * @param aNewY
   * @return
   * @throws CoordinateOutOfIndexException
   */
  private boolean checkIfIsApparitionial(Figure aFigure, int aNewX, int aNewY)
      throws CoordinateOutOfIndexException {
    int[] tmpCurrentCoordinates = aFigure.getCurrentCoordinates();
    if (tmpCurrentCoordinates[0] == aNewX && tmpCurrentCoordinates[1] == aNewY) {
      // same field
      return true;
    } else if (tmpCurrentCoordinates[0] == aNewX) {
      // vertical move
      if (checkIfDistanceIsSolvable(tmpCurrentCoordinates[1], aNewY, aFigure.getNormalView())) {
        return checkIfIsViewableNonDiagonally(aFigure, aNewY, "y")
            && checkIfFieldIsEmptyOrHasVisibleEnemySetter(aFigure, aNewX, aNewY);
      }
      return false;
    } else if (tmpCurrentCoordinates[1] == aNewY) {
      // horizontal move
      if (checkIfDistanceIsSolvable(tmpCurrentCoordinates[0], aNewX, aFigure.getNormalView())) {
        return checkIfIsViewableNonDiagonally(aFigure, aNewX, "x")
            && checkIfFieldIsEmptyOrHasVisibleEnemySetter(aFigure, aNewX, aNewY);
      }
      return false;
    } else if (Math.abs(tmpCurrentCoordinates[0] - aNewX) == Math.abs(tmpCurrentCoordinates[1] - aNewY)) {
      // diagonal move
      if (checkIfDistanceIsSolvable(tmpCurrentCoordinates[0], aNewX, aFigure.getDiagonalView())
          && checkIfDistanceIsSolvable(tmpCurrentCoordinates[1], aNewY, aFigure.getDiagonalView())) {
        return checkIfIsViewableDiagonally(aFigure, aNewX, aNewY)
            && checkIfFieldIsEmptyOrHasVisibleEnemySetter(aFigure, aNewX, aNewY);
      }
      return false;
    }
    return false;
  }

  /**
   * @return true if the field is empty or if there is an enemy placeable the
   *         given figure can see
   */
  private boolean checkIfFieldIsEmptyOrHasVisibleEnemySetter(Figure aFigure, int aNewX, int aNewY) {
    if (!checkIfIsEmpty(aNewX, aNewY)) {
      if (checkIfIsEnemy(aFigure, aNewX, aNewY)) {
        Placeable tmpSetter = getSetter(aNewX, aNewY);
        if (tmpSetter instanceof Spy) {
          return false;
        } else if (tmpSetter instanceof Trap && !(aFigure instanceof Medic)) {
          return false;
        } else if (tmpSetter instanceof Bomb && !(aFigure instanceof Miner)) {
          return false;
        }
        // no special can't-see-rule
        return true;
      }
      // there's a figure of the same team on the field
      return true;
    }
    // field is empty
    return true;
  }

  // needed in Displayers ==> public
  /**
   * @param anX
   * @param aY
   * @return a Field
   *
   */
  public Field getField(int anX, int aY) {
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
