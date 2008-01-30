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

  // TODO show enemies in the view of a figure
  // is this the right class for this logic??
  // this is no class. this is an interface. :P
  // i would put the logic into playmap. where the other logic takes place as
  // well
}