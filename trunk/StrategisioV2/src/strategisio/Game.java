package strategisio;

import java.util.ArrayList;

import strategisio.elements.PlayMap;
import strategisio.elements.Team;
import strategisio.elements.constants.Ground;
import strategisio.elements.figures.Figure;
import strategisio.elements.items.Item;
import strategisio.exceptions.UnknownFieldTypeException;
import strategisio.visualization.*;

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
   */
  public Game(int aMapSize, String aTeamName, String anotherTeamName) {
    // TODO make ready for xml input instead of mapsize only
    initMap(aMapSize);
    displayer = new ConsoleDisplay();
    teamA = new Team('A', aTeamName);
    teamB = new Team('B', anotherTeamName);
  }

  private void initMap(int aMapSize) {
    initMap(aMapSize, aMapSize);
  }

  private void initMap(int anXDimension, int aYDimension) {
    playMap = new PlayMap(anXDimension, aYDimension);
    for (int i = 0; i < anXDimension; i++) {
      for (int j = 0; j < aYDimension; j++) {
        try {
          playMap.setFieldType(i, j, Ground.GRASS);
        } catch (UnknownFieldTypeException e) {
          e.printStackTrace();
        }
      }
    }
  }

  /**
   * start it here
   *
   * @param args
   */
  public static void main(String[] args) {
    int tmpMapSize = 9;
    String tmpTeam1 = "Team 1";
    String tmpTeam2 = "Team 2";
    Game tmpGame = new Game(tmpMapSize, tmpTeam1, tmpTeam2);

    // TODO init fields by request
    ArrayList<Figure> tmpFigures = tmpGame.teamA.getFigures();
    ArrayList<Item> tmpItems = tmpGame.teamA.getItems();
    PlayMap tmpPlayMap = tmpGame.playMap;
    int tmpX = 0;
    int tmpY = 0;
    //////////////////////////////////////////////////////////////
    // INFO: for-loops will run endlessly if the map is to small!
    //////////////////////////////////////////////////////////////
    for (int i = 0; i < tmpItems.size(); i++) {
      try {
        if (tmpPlayMap.checkPositioningPossibility(tmpItems.get(i), tmpX, tmpY)) {
          tmpPlayMap.position(tmpItems.get(i), tmpX++, tmpY);
        } else {
          i--;
          if (tmpX < 1){
            tmpX = tmpMapSize - 1;
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
          tmpPlayMap.position(tmpFigures.get(i), tmpX++, tmpY);
        } else {
          i--;
          if (tmpX < 1){
            tmpX = tmpMapSize - 1;
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


    tmpFigures = tmpGame.teamB.getFigures();
    tmpItems = tmpGame.teamB.getItems();
    tmpX = tmpMapSize - 1;
    tmpY = tmpMapSize - 1;

    for (int i = 0; i < tmpItems.size(); i++) {
      try {
        if (tmpPlayMap.checkPositioningPossibility(tmpItems.get(i), tmpX, tmpY)) {
          tmpPlayMap.position(tmpItems.get(i), tmpX--, tmpY);
        } else {
          i--;
          if (tmpX < 1){
            tmpX = tmpMapSize - 1;
            tmpY--;
          } else {
            tmpX--;
          }
        }
      } catch (Exception e) {
        tmpX = tmpMapSize - 1;
        tmpY--;
        i--;
      }
    }
    for (int i = 0; i < tmpFigures.size(); i++) {
      try {
        if (tmpPlayMap.checkPositioningPossibility(tmpFigures.get(i), tmpX, tmpY)) {
          tmpPlayMap.position(tmpFigures.get(i), tmpX--, tmpY);
          if (tmpX < 0){
            tmpX = tmpMapSize - 1;
            tmpY--;
          }
        } else {
          i--;
          if (tmpX < 1){
            tmpX = tmpMapSize - 1;
            tmpY--;
          } else {
            tmpX--;
          }
        }
      } catch (Exception e) {
        tmpX = tmpMapSize - 1;
        tmpY--;
        i--;
      }
    }
   tmpGame.displayer.showMap(tmpPlayMap);
  }

  /**
   * @return the displayer
   */
  public Displayable getDisplayer() {
    return displayer;
  }

  /**
   * @param aDisplayer the displayer to set
   */
  public void setDisplayer(Displayable aDisplayer) {
    displayer = aDisplayer;
  }
}