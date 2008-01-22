package strategisio.elements.fields;

import strategisio.elements.Movable;

/**
 *
 * represents a Field on the PlayMap can hold a PlayElement
 *
 */
public abstract class Field {

  private Movable setter = null;

  /**
   * @return the setter
   */
  public Movable getSetter() {
    return setter;
  }

  /**
   * @param aSetter the setter to set
   */
  public void setSetter(Movable aSetter) {
    setter = aSetter;
  }
}
