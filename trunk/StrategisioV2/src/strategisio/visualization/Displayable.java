package strategisio.visualization;

import strategisio.elements.PlayMap;

/**
 * Display interface for painting the game on the screen
 *
 * @author Tim
 *
 */
public interface Displayable {

  /**
   * To paint the given map on the screen
   *
   * @param aPlayMap
   */
  public void display(PlayMap aPlayMap);
}