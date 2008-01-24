package strategisio.visualization;

import strategisio.elements.Placeable;
import strategisio.elements.*;

/**
 * for displaying the game in the console
 *
 * @author Tim
 *
 */
public class ConsoleDisplay implements Displayable {

  /**
   * displays the map in the console
   *
   * @param aPlayMap
   */
  public void showMap(PlayMap aPlayMap) {
    System.out.println();
    System.out.println();
    String tmpFirstLine = "       ";
    for (int i = 0; i < aPlayMap.getXDimension(); i++) {
      tmpFirstLine += i;
      if (i < 10) {
        tmpFirstLine += "         ";
      } else {
        tmpFirstLine += "        ";
      }
    }
    System.out.println(tmpFirstLine);

    for (int y = 0; y < aPlayMap.getYDimension(); y++) {
      System.out.println();
      String tmpFieldRow = "  " + y;
      if (y < 10) {
        tmpFieldRow += "   ";
      } else {
        tmpFieldRow += "  ";
      }
      for (int x = 0; x < aPlayMap.getXDimension(); x++) {
        if (aPlayMap.getSetter(x, y) != null) {
          Placeable tmpPlaceable = aPlayMap.getSetter(x, y);
          String tmpPlaceableName = tmpPlaceable.getClass().toString();
          tmpPlaceableName = tmpPlaceableName.substring(tmpPlaceableName.lastIndexOf('.') + 1);
          if (tmpPlaceable.getId() == 'A') {
            tmpPlaceableName = tmpPlaceableName.toUpperCase();
          } else if (tmpPlaceable.getId() == 'B') {
            tmpPlaceableName = tmpPlaceableName.toLowerCase();
          }
          while (tmpPlaceableName.length() != 10) {
            tmpPlaceableName += " ";
          }
          tmpFieldRow += tmpPlaceableName;
        } else {
          tmpFieldRow += "          ";
        }
      }
      System.out.println(tmpFieldRow);
    }
  }
}