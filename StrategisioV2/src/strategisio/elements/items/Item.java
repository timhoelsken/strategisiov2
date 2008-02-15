package strategisio.elements.items;

import strategisio.elements.Placeable;

/**
 * abstract class for all items
 */
public abstract class Item implements Placeable {

  private char id;
  
  private int[] currentCoordinates = new int[2];

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
  
  /**
   * @return the currentCoordinates
   */
  public int[] getCurrentCoordinates() {
    return currentCoordinates;
  }

  /**
   * 
   * @param anX
   * @param aY
   */
  public void setCurrentCoordinates(int anX, int aY) {
    
    currentCoordinates[0] = anX;
    currentCoordinates[1] = aY;
  }
}
