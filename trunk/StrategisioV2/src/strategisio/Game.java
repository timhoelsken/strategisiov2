package strategisio;

import java.io.File;
import java.util.ArrayList;

import strategisio.elements.Placeable;
import strategisio.elements.PlayMap;
import strategisio.elements.Team;
import strategisio.elements.constants.Ground;
import strategisio.elements.figures.Figure;
import strategisio.elements.items.Item;
import strategisio.exceptions.CoordinateOutOfIndexException;
import strategisio.exceptions.FlagLimitOverflowException;
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
   */
  public Game(int aMapSize, String aTeamName, String anotherTeamName) throws FlagLimitOverflowException {
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
   */
  public Game(File aFile, String aTeamName, String anotherTeamName) throws FlagLimitOverflowException,
      UnknownFieldGroundException {
    initMap(aFile);
    displayer = new ConsoleDisplay();
    teamA = new Team('A', aTeamName, aFile);
    teamB = new Team('B', anotherTeamName, aFile);
  }

  private void initMap(int aMapSize) {
    initMap(aMapSize, aMapSize);
  }

  private void initMap(int anXDimension, int aYDimension) {
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

  private void initMap(File aFile) throws UnknownFieldGroundException {
    playMap = new PlayMap(aFile);
  }

  /**
   * start it here
   * 
   * @param args
   * @throws FlagLimitOverflowException
   * @throws UnknownFieldGroundException 
   */
  public static void main(String[] args) throws FlagLimitOverflowException, UnknownFieldGroundException {
    String tmpTeam1 = "Team 1";
    String tmpTeam2 = "Team 2";
    
    File tmpFile = new File("resources/map_config.xml");
    Game tmpGame = new Game (tmpFile, tmpTeam1, tmpTeam2);

    // TODO init fields manually
    tmpGame.generateMapAutomatically();
    
    tmpGame.display("map");
  }

  /**
   * 
   */
  public void generateMapAutomatically(){
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
   * @param anElement 
   * 
   */
  public void display(String anElement) {
    if (anElement.equals("map")){
      this.displayer.display(this.playMap);
    }
  }
  
  /**
   * 
   * @param anOldXCoordinate
   * @param anOldYCoordinate
   * @param aNewXCoordinate
   * @param aNewYCoordinate
   * @throws CoordinateOutOfIndexException
   */
  public void move(int anOldXCoordinate, int anOldYCoordinate, int aNewXCoordinate, int aNewYCoordinate) throws CoordinateOutOfIndexException{
    Placeable tmpPlaceable = this.playMap.fetchSetter(anOldXCoordinate, anOldYCoordinate);
    playMap.move((Figure)tmpPlaceable, aNewXCoordinate, aNewYCoordinate);
    
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
}