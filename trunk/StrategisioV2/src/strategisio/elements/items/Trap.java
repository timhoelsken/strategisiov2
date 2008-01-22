package strategisio.elements.items;

import strategisio.elements.figures.Figure;

/**
 * @author Tobias
 *
 * the trap
 */
public class Trap extends Item {

  private Figure catched;

  /**
   * @return
   */
  protected Figure getCatched() {
    return catched;
  }

  /**
   * @param catched
   */
  protected void setCatched(Figure catched) {
    this.catched = catched;
  }
}
