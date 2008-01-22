package strategisio.elements.fields;

import strategisio.elements.PlayElement;

/**
 * 
 * represents a Field on the PlayMap can hold a PlayElement
 * 
 */
public abstract class Field {

  private PlayElement setter = null;

  /**
   * @return setter
   */
  protected PlayElement getSetter() {
    return setter;
  }

  /**
   * @param setter
   */
  protected void setSetter(PlayElement setter) {
    this.setter = setter;
  }
}
