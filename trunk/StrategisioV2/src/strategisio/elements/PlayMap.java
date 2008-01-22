package strategisio.elements;

import strategisio.elements.fields.Field;
import strategisio.elements.fields.Grass;

/**
 *
 * the PlayMap
 *
 */
public class PlayMap {

  private int xDimension;
  private int yDimension;

  private Field[][] fields;

  /**
   * creates a quadratic map
   *
   * @param aDimension
   *            for size of the map (aDimension^2)
   */
  public PlayMap(int aDimension) {
    xDimension = aDimension;
    yDimension = aDimension;
    initMap();
  }

  /**
   * creates a map
   *
   * @param anXDimension
   * @param aYDimension
   *            for size of the map (anXDimension x aYDimension)
   */
  public PlayMap(int anXDimension, int aYDimension) {
    xDimension = anXDimension;
    yDimension = aYDimension;
    initMap();
  }

  private void initMap() {
    // TODO dynamic fill has to be implemented
    fields = new Field[xDimension][yDimension];
    for (int i = 0; i < fields.length; i++) {
      for (int j = 0; j < fields[i].length; j++) {
        fields[i][j] = new Grass();
      }
    }
    checkMap();
  }

  /**
   * positioning method for items and figures (at the beginning of the game)
   *
   * @param aMovable
   * @param anX
   * @param aY
   * @throws IllegalArgumentException
   *             if coordinates are not applicable
   */
  public void position(Movable aMovable, int anX, int aY) throws IllegalArgumentException {
    checkCoordinates(anX, aY);
    Field tmpField = fields[anX][aY];
    Movable tmpElement = tmpField.getSetter();
    if (tmpElement == null) {
      fields[anX][aY].setSetter(aMovable);
    } else {
      // TODO already set => go anywhere!
      System.out.println("Go anywhere!");
    }
  }

  private void checkCoordinates(int anX, int aY) throws IllegalArgumentException {
    if (anX < 0){
      throw new IllegalArgumentException("x value '" + anX + "' is less than 0");
    } else if (anX >= xDimension) {
      throw new IllegalArgumentException("x value '" + anX + "' equals or is greater than maximum value '" + xDimension + "'");
    } else if (aY < 0) {
      throw new IllegalArgumentException("y value '" + aY + "'  less than 0");
    } else if (aY >= yDimension) {
      throw new IllegalArgumentException("y value '" + aY + "'  equals or is greater than maximum value '" + yDimension + "'");
    }
  }

  private void checkMap() {
    // TODO MapCheck
  }
}
