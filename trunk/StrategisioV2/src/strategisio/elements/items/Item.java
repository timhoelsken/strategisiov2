package strategisio.elements.items;

import strategisio.elements.Movable;

/**
 * abstract class for all items
 */
public abstract class Item implements Movable {

  private String id;

  /**
   * @return the id
   */
  public String getId() {
    return id;
  }

  /**
   * @param aId the id to set
   */
  public void setId(String aId) {
    id = aId;
  }
}
