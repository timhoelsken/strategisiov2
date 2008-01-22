package strategisio;

import java.util.HashMap;
import java.util.Map;

import strategisio.elements.PlayMap;
import strategisio.elements.Team;

/**
 * 
 * contains all Game elements
 * 
 */
public class Game {

  private PlayMap playMap;

  private Map<Character, Team> teams;

  /**
   * standard constructor
   */
  public Game() {
    playMap = new PlayMap();

    teams = new HashMap<Character, Team>();
    Team tmpTeam1 = new Team("Team 1");
    Team tmpTeam2 = new Team("Team 2");
    teams.put(Character.valueOf('A'), tmpTeam1);
    teams.put(Character.valueOf('B'), tmpTeam2);
  }

  private PlayMap getPlayMap() {
    return playMap;
  }

  private Map<Character, Team> getTeams() {
    return teams;
  }

  /**
   * just a test main method
   * 
   * @param args
   */
  public static void main(String[] args) {
    Game tmpGame = new Game();
    System.out.println("Das Spiel: " + tmpGame.toString());
    System.out.println("Die Karte: " + tmpGame.getPlayMap().toString());
    System.out.println("Die Teams: " + tmpGame.getTeams().toString());
  }
}
