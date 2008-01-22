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
  protected Movable getSetter() {
    return setter;
  }

  /**
   * @param aSetter the setter to set
   */
  protected void setSetter(Movable aSetter) {
    setter = aSetter;
  }
}
