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
   * @return setter
   */
  protected Movable getSetter() {
    return setter;
  }

  /**
   * @param setter
   */
  protected void setSetter(Movable setter) {
    this.setter = setter;
  }
}
