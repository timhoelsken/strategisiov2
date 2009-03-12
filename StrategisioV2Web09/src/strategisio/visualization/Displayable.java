package strategisio.visualization;

import strategisio.components.PlayMap;
import strategisio.elements.Team;

/**
 * Display interface for painting the game on the screen
 *
 * @author Tobias, Tim
 *
 */
public interface Displayable {

  /**
   * @param aPlayMap
   * @return the current status of the given map in God's view
   */
  public String display(PlayMap aPlayMap);

  /**
   * To paint
   *
   * @param aPlayMap
   * @param aPlayerId
   * @param aTeam
   * @return the current status of the given map from the point of view of team
   *         aTeam with the given playerId
   */
  public String display(PlayMap aPlayMap, char aPlayerId, Team aTeam);
}