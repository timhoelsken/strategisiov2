package strategisio;

import java.util.ArrayList;

import strategisio.elements.PlayMap;
import strategisio.elements.Team;
import strategisio.elements.figures.Figure;
import strategisio.elements.items.Item;

/**
 *
 * contains all Game elements
 *
 */
public class Game {

  private PlayMap playMap;

  private Team team1;

  private Team team2;

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

    team1 = new Team('A', aTeamName);
    team2 = new Team('B', anotherTeamName);
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
    int tmpMapSize = 8;
    String tmpTeam1 = "Team 1";
    String tmpTeam2 = "Team 2";
    Game tmpGame = new Game(tmpMapSize, tmpTeam1, tmpTeam2);

    // TODO init fields by request
    ArrayList<Figure> tmpFigures = tmpGame.team1.getFigures();
    ArrayList<Item> tmpItems = tmpGame.team1.getItems();
    PlayMap tmpPlayMap = tmpGame.playMap;
    int tmpX = 0;
    int tmpY = 0;
    for (int i = 0; i < tmpItems.size(); i++) {
      try {
        tmpPlayMap.position(tmpItems.get(i), tmpX++, tmpY);
      } catch (IllegalArgumentException e) {
        tmpX = 0;
        tmpY++;
        i--;
      }
    }
    for (int i = 0; i < tmpFigures.size(); i++) {
      try {
        tmpPlayMap.position(tmpFigures.get(i), tmpX++, tmpY);
      } catch (IllegalArgumentException e) {
        tmpX = 0;
        tmpY++;
        i--;
      }
    }

    tmpFigures = tmpGame.team2.getFigures();
    tmpItems = tmpGame.team2.getItems();
    tmpX = tmpMapSize - 1;
    tmpY = tmpMapSize - 1;
    for (int i = 0; i < tmpItems.size(); i++) {
      try {
        tmpPlayMap.position(tmpItems.get(i), tmpX--, tmpY);
      } catch (IllegalArgumentException e) {
        System.err.println(e.getMessage());
        tmpX = tmpMapSize - 1;
        tmpY--;
        i--;
      }
    }
    for (int i = 0; i < tmpFigures.size(); i++) {
      try {
        tmpPlayMap.position(tmpFigures.get(i), tmpX--, tmpY);
      } catch (IllegalArgumentException e) {
        tmpX = tmpMapSize - 1;
        tmpY--;
        i--;
      }
    }

    tmpPlayMap.showMap();
  }
}
