package strategisio.elements.fields;

import strategisio.elements.Placeable;

/**
 * 
 * represents a Field on the PlayMap can hold a placeAble
 * 
 */
public abstract class Field {

  private Placeable setter = null;

  private int ground;

  /**
   * @return the ground
   */
  public int getGround() {
    return ground;
  }

  /**
   * @param aGround
   *            the ground to set
   */
  protected void setGround(int aGround) {
    ground = aGround;
  }

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
