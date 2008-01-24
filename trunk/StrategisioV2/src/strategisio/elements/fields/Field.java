package strategisio.elements.fields;

import strategisio.elements.Placeable;

/**
 * 
 * represents a Field on the PlayMap can hold a placeAble
 * 
 */
public abstract class Field {

  private Placeable setter = null;

  /**
   * @return the setter
   */
  public Placeable getSetter() {
    return setter;
  }

  /**
   * @param aSetter
   *            the setter to set
   */
  public void setSetter(Placeable aSetter) {
    setter = aSetter;
  }
}
