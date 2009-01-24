package strategisio.visualization;

import strategisio.elements.PlayMap;

/**
 * Display interface for painting the game on the screen
 *
 * @author Tim, Tobias
 *
 */
public interface Displayable {

  /**
   * To paint the given map on the screen
   *
   * @param aPlayMap
   * @return
   */
  public String display(PlayMap aPlayMap);

  /**
   * To paint the given map on the screen from the point of view of team aPlayerId
   *
   * @param aPlayMap
   * @param aPlayerId
   * @return
   */
  public String display(PlayMap aPlayMap, char aPlayerId);
}