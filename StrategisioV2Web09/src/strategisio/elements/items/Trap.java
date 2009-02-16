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
  public Figure getCatched() {
    return catched;
  }

  /**
   * @param aCatched
   *            the catched to set
   */
  public void setCatched(Figure aCatched) {
    catched = aCatched;
  }

  /**
   * @return image of this item
   */
  public String getImage() {
    return new String("trp_set.png");
  }
}
