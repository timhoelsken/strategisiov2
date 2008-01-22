package strategisio;

import java.util.ArrayList;

import strategisio.elements.PlayMap;
import strategisio.elements.Team;
import strategisio.elements.WrongCoordinateException;
import strategisio.elements.figures.Figure;
import strategisio.elements.items.Item;

/**
 *
 * contains all Game elements
 *
 */
public class Game {

  private PlayMap playMap;

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
    playMap = new PlayMap(aMapSize);

    teamA = new Team('A', aTeamName);
    teamB = new Team('B', anotherTeamName);
  }

  // private void move(Figure aFigure, int anX, int aY) {
  // Field tmpField = playMap.getFields()[anX][aY];
  // Movable tmpElement = tmpField.getSetter();
  // if (tmpElement == null) {
  // playMap.getFields()[anX][aY].setSetter(aFigure);
  // } else {
  // // TODO already set => fight!
  // }
  // }

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
    for (int i = 0; i < tmpItems.size(); i++) {
      try {
        tmpPlayMap.position(tmpItems.get(i), tmpX++, tmpY);
      } catch (WrongCoordinateException e) {
        tmpX = 0;
        tmpY++;
        i--;
      }
    }
    for (int i = 0; i < tmpFigures.size(); i++) {
      try {
        tmpPlayMap.position(tmpFigures.get(i), tmpX++, tmpY);
      } catch (WrongCoordinateException e) {
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
        tmpPlayMap.position(tmpItems.get(i), tmpX--, tmpY);
      } catch (WrongCoordinateException e) {
        tmpX = tmpMapSize - 1;
        tmpY--;
        i--;
      }
    }
    for (int i = 0; i < tmpFigures.size(); i++) {
      try {
        tmpPlayMap.position(tmpFigures.get(i), tmpX--, tmpY);
      } catch (WrongCoordinateException e) {
        tmpX = tmpMapSize - 1;
        tmpY--;
        i--;
      }
    }

    tmpPlayMap.showMap();

//    // activate to see that the objects in the ArrayList are the same as on the map
//    // the figures of teamB will be shown with only one capital letter because id 'C' is unknown
//    for (int i = 0; i < tmpFigures.size(); i++) {
//      tmpFigures.get(i).setId('C');
//    }
//    tmpPlayMap.showMap();
  }
}