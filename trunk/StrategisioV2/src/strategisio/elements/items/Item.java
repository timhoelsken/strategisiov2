package strategisio.elements.items;

import strategisio.elements.Placeable;

/**
 * abstract class for all items
 */
public abstract class Item implements Placeable {

  private char id;

  /**
   * @return the id
   */
  public char getId() {
    return id;
  }

  /**
   * @param aId
   *            the id to set
   */
  public void setId(char aId) {
    id = aId;
  }
}
