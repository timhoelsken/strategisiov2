package strategisio;

import java.io.File;
import java.util.ArrayList;

import strategisio.components.PlayMap;
import strategisio.constants.Ground;
import strategisio.elements.Placeable;
import strategisio.elements.Team;
import strategisio.elements.figures.Figure;
import strategisio.elements.items.Item;
import strategisio.exceptions.CoordinateOutOfIndexException;
import strategisio.exceptions.FlagLimitOverflowException;
import strategisio.exceptions.MapTooLargeException;
import strategisio.exceptions.UnknownFieldGroundException;
import strategisio.visualization.ConsoleDisplay;
import strategisio.visualization.Displayable;

/**
 *
 * contains all game elements
 *
 */
public class Game {

  private PlayMap playMap;

  private Displayable displayer;

  private Team teamA;

  private Team teamB;

  private boolean isFull = false;

  /**
   * standard constructor
   *
   * @param aMapSize
   *            size of the map
   * @param aTeamName
   *            name for team 1
   * @param anotherTeamName
   *            name for team 2
   * @throws FlagLimitOverflowException
   * @throws MapTooLargeException
   */
  public Game(int aMapSize, String aTeamName, String anotherTeamName) throws FlagLimitOverflowException,
      MapTooLargeException {
    initMap(aMapSize);
    displayer = new ConsoleDisplay();
    teamA = new Team('A', aTeamName);
    teamB = new Team('B', anotherTeamName);
  }

  /**
   *
   * Constructor for generating a map via XML-file
   *
   * @param aFile
   * @param aTeamName
   * @param anotherTeamName
   * @throws FlagLimitOverflowException
   * @throws UnknownFieldGroundException
   * @throws MapTooLargeException
   */
  public Game(File aFile, String aTeamName, String anotherTeamName) throws FlagLimitOverflowException,
      UnknownFieldGroundException, MapTooLargeException {
    initMap(aFile);
    displayer = new ConsoleDisplay();
    teamA = new Team('A', aTeamName, aFile);
    teamB = new Team('B', anotherTeamName, aFile);
  }

  private void initMap(int aMapSize) throws MapTooLargeException {
    initMap(aMapSize, aMapSize);
  }

  private void initMap(int anXDimension, int aYDimension) throws MapTooLargeException {
    playMap = new PlayMap(anXDimension, aYDimension);
    for (int i = 0; i < anXDimension; i++) {
      for (int j = 0; j < aYDimension; j++) {
        try {
          playMap.setFieldGround(i, j, Ground.GRASS);
        } catch (UnknownFieldGroundException e) {
          e.printStackTrace();
        }
      }
    }
  }

  private void initMap(File aFile) throws UnknownFieldGroundException, MapTooLargeException {
    playMap = new PlayMap(aFile);
  }

  /**
   * start it here
   *
   * @param args
   * @throws FlagLimitOverflowException
   * @throws UnknownFieldGroundException
   * @throws CoordinateOutOfIndexException
   * @throws MapTooLargeException
   */
  public static void main(String[] args) throws FlagLimitOverflowException, UnknownFieldGroundException,
      CoordinateOutOfIndexException, MapTooLargeException {
    String tmpTeam1 = "Team 1";
    String tmpTeam2 = "Team 2";

    File tmpFile = new File("WebContent/resources/map_config.xml");
    Game tmpGame = new Game(tmpFile, tmpTeam1, tmpTeam2);

    // TODO init fields manually
    tmpGame.generateMapAutomatically();

    tmpGame.setDisplayer(new ConsoleDisplay());

    tmpGame.display("X");
  }

  /**
   *
   */
  public void generateMapAutomatically() {
    ArrayList<Figure> tmpFigures = this.teamA.getFigures();
    ArrayList<Item> tmpItems = this.teamA.getItems();
    PlayMap tmpPlayMap = this.playMap;
    tmpPlayMap.getXDimension();
    int tmpX = 0;
    int tmpY = 0;

    // ////////////////////////////////////////////////////////////
    // INFO: for-loops will run endlessly if the map is to small!
    // ////////////////////////////////////////////////////////////
    for (int i = 0; i < tmpItems.size(); i++) {
      try {
        if (tmpPlayMap.checkPositioningPossibility(tmpItems.get(i), tmpX, tmpY)) {
          tmpPlayMap.positionWithoutCheck(tmpItems.get(i), tmpX++, tmpY);
        } else {
          i--;
          if (tmpX < 1) {
            tmpX = tmpPlayMap.getXDimension() - 1;
            tmpY--;
          } else {
            tmpX--;
          }
        }
      } catch (Exception e) {
        tmpX = 0;
        tmpY++;
        i--;
      }
    }
    for (int i = 0; i < tmpFigures.size(); i++) {
      try {
        if (tmpPlayMap.checkPositioningPossibility(tmpFigures.get(i), tmpX, tmpY)) {
          tmpPlayMap.positionWithoutCheck(tmpFigures.get(i), tmpX++, tmpY);
        } else {
          i--;
          if (tmpX < 1) {
            tmpX = tmpPlayMap.getXDimension() - 1;
            tmpY--;
          } else {
            tmpX--;
          }
        }
      } catch (Exception e) {
        tmpX = 0;
        tmpY++;
        i--;
      }
    }

    tmpFigures = this.teamB.getFigures();
    tmpItems = this.teamB.getItems();
    tmpX = tmpPlayMap.getXDimension() - 1;
    tmpY = tmpPlayMap.getXDimension() - 1;

    for (int i = 0; i < tmpItems.size(); i++) {
      try {
        if (tmpPlayMap.checkPositioningPossibility(tmpItems.get(i), tmpX, tmpY)) {
          tmpPlayMap.positionWithoutCheck(tmpItems.get(i), tmpX--, tmpY);
        } else {
          i--;
          if (tmpX < 1) {
            tmpX = tmpPlayMap.getXDimension() - 1;
            tmpY--;
          } else {
            tmpX--;
          }
        }
      } catch (Exception e) {
        tmpX = tmpPlayMap.getXDimension() - 1;
        tmpY--;
        i--;
      }
    }
    for (int i = 0; i < tmpFigures.size(); i++) {
      try {
        if (tmpPlayMap.checkPositioningPossibility(tmpFigures.get(i), tmpX, tmpY)) {
          tmpPlayMap.positionWithoutCheck(tmpFigures.get(i), tmpX--, tmpY);
          if (tmpX < 0) {
            tmpX = tmpPlayMap.getXDimension() - 1;
            tmpY--;
          }
        } else {
          i--;
          if (tmpX < 1) {
            tmpX = tmpPlayMap.getXDimension() - 1;
            tmpY--;
          } else {
            tmpX--;
          }
        }
      } catch (Exception e) {
        tmpX = tmpPlayMap.getXDimension() - 1;
        tmpY--;
        i--;
      }
    }
  }

  /**
   * @param aPlayerId
   *            may be playerId 'A' or 'B' or God's view 'X'
   * @return the display()-result of this.displayer
   */
  public String display(String aPlayerId) {
    if ("".equals(aPlayerId) || aPlayerId == null || aPlayerId.length() != 1) {
      return "";
    }
    char tmpPlayerId = aPlayerId.charAt(0);
    if (tmpPlayerId == 'X') {
      return this.displayer.display(this.playMap);
    }
    Team tmpTeam = getTeam(tmpPlayerId);
    return this.displayer.display(this.playMap, tmpPlayerId, tmpTeam);
  }

  /**
   *
   * @param anOldXCoordinate
   * @param anOldYCoordinate
   * @param aNewXCoordinate
   * @param aNewYCoordinate
   * @throws CoordinateOutOfIndexException
   */
  public void move(int anOldXCoordinate, int anOldYCoordinate, int aNewXCoordinate, int aNewYCoordinate)
      throws CoordinateOutOfIndexException {
    Placeable tmpPlaceable = this.playMap.fetchSetter(anOldXCoordinate, anOldYCoordinate);
    if (!playMap.move((Figure) tmpPlaceable, aNewXCoordinate, aNewYCoordinate)) {
      playMap.getField(anOldXCoordinate, anOldYCoordinate).setSetter(tmpPlaceable);
    }

  }

  /**
   *
   * @param anX
   * @param aY
   * @return the movingArrea
   * @throws CoordinateOutOfIndexException
   */
  public ArrayList<int[]> getMovingArea(int anX, int aY) throws CoordinateOutOfIndexException {
    Figure tmpFigure = (Figure) this.playMap.getSetter(anX, aY);
    return this.playMap.getMovingArea(tmpFigure);
  }

  /**
   *
   * @param anX
   * @param aY
   * @return true if setter on field, false if not
   */
  public boolean fieldIsSetByPlaceable(int anX, int aY) {
    return (this.playMap.getSetter(anX, aY) == null) ? false : true;
  }

  /**
   * @return the displayer
   */
  public Displayable getDisplayer() {
    return displayer;
  }

  /**
   * @param aDisplayer
   *            the displayer to set
   */
  public void setDisplayer(Displayable aDisplayer) {
    displayer = aDisplayer;
  }

  /**
   *
   * @param anX
   * @param aY
   * @return the ViewArea of a single Figure or -1/-1 if there is no Figure on
   *         the given field coordinates.
   * @throws CoordinateOutOfIndexException
   *             if coordinates are not on the map
   */
  public ArrayList<int[]> getViewArea(int anX, int aY) throws CoordinateOutOfIndexException {

    Placeable tmpPlaceable = this.playMap.getSetter(anX, aY);
    if (tmpPlaceable instanceof Figure) {
      Figure tmpFigure = (Figure) tmpPlaceable;
      return this.playMap.fakeGetSingleFigureViewArea(tmpFigure);
    }
    ArrayList<int[]> tmpViewArea = new ArrayList<int[]>();
    int[] tmpCoordinates = new int[2];
    tmpCoordinates[0] = -1;
    tmpCoordinates[1] = -1;
    tmpViewArea.add(tmpCoordinates);
    return tmpViewArea;
  }

  private Team getTeam(char aPlayerId) {
    switch (aPlayerId) {
      case 'A':
        return teamA;
      case 'B':
        return teamB;
      default:
        return null;
    }
  }

  /**
   * @return the playMap of the game
   */
  public PlayMap getPlayMap() {
    return playMap;
  }

  /**
   * Deletes an item or a figure from the team
   *
   * @param aPlaceable
   * @param anId
   */
  public void removePlaceableFromTeam(Placeable aPlaceable, char anId) {
    Team tmpTeam = getTeam(anId);
    if (aPlaceable instanceof Figure) {
      tmpTeam.removeFigureFromTeam((Figure) aPlaceable);
    } else if (aPlaceable instanceof Item) {
      // TODO Maybe the Game ends here?!
      endGame(tmpTeam.removeItemFromTeam((Item) aPlaceable));
    }
  }

  /**
   * @return the isFull
   */
  public boolean isFull() {
    return isFull;
  }

  /**
   * @param aIsFull
   *            the isFull to set
   */
  public void setFull(boolean aIsFull) {
    isFull = aIsFull;
  }

  // TODO write a method to end the game
  private void endGame(boolean gameEnded) {

  }
}