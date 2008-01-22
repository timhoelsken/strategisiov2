package strategisio.elements;

import strategisio.elements.fields.Field;
import strategisio.elements.fields.Grass;

/**
 * 
 * the PlayMap
 * 
 */
public class PlayMap {

  private Field[][] fields;

  /**
   * standard constructor
   */
  public PlayMap() {
    // TODO dynamic fill has to be implemented
    int tmpDimension = 6;
    fields = new Field[tmpDimension][tmpDimension];
    for (int i = 0; i < fields.length; i++) {
      for (int j = 0; j < fields[i].length; j++) {
        fields[i][j] = new Grass();
      }
    }
    checkMap();
  }

  private void checkMap() {
    // TODO MapCheck
  }
}
