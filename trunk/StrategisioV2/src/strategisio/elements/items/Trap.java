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
   * @return the catched
   */
  protected Figure getCatched() {
    return catched;
  }

  /**
   * @param aCatched
   *            the catched to set
   */
  protected void setCatched(Figure aCatched) {
    catched = aCatched;
  }
}
