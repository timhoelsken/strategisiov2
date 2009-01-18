package strategisio.elements;

/**
 *
 * groups figures & items
 *
 */
public interface Placeable {

  /**
   * @return the team id
   */
  char getId();

  /**
   * @return the currentCoordinates
   */
  int[] getCurrentCoordinates();

  /**
   * @param anX
   * @param aY
   *            sets the currentCoordninates
   */
  void setCurrentCoordinates(int anX, int aY);

  /**
   * @return the filename of the picture
   */
  String getImage();
  //TODO how to image trapped figures?!
}
