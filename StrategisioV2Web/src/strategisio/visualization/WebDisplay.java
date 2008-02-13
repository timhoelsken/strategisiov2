package strategisio.visualization;

import strategisio.elements.PlayMap;
import strategisio.elements.constants.Ground;
import strategisio.elements.fields.Field;

/**
 * @author Tim
 *
 */
public class WebDisplay implements Displayable {

  /**
   * displays the map in the web
   *
   * @param aPlayMap
   * @return
   */
  public String display(PlayMap aPlayMap) {
    String tmpOutput = "";
    Field tmpField;
    tmpOutput = "<div class=\"map\" style=\"width:" + aPlayMap.getXDimension() * 32 + "px;";
    tmpOutput += "height:" + aPlayMap.getYDimension() * 32 + "px;\">\n";
    // loop for row
    for (int i = 0; i < aPlayMap.getYDimension(); i++) {
//      tmpOutput = "<div style=\"width:" + aPlayMap.getXDimension() * 32 + "px; height: 32px;";
      // loop for column
      for (int j = 0, k = 0; j < aPlayMap.getXDimension(); j++) {
        tmpField = aPlayMap.getField(j, i);
        if (tmpField.getGround() == Ground.WATER) {
          tmpOutput += "<div class=\"field water\" ";
        } else if (tmpField.getGround() == Ground.MOUNTAIN) {
          tmpOutput += "<div class=\"field mountain\" ";
        } else {
          tmpOutput += "<div class=\"field grass\" ";
        }
        if (j != 0) {
          k = 1;
        }
        tmpOutput += "onMouseOver=\"hoverOn(this);\" onMouseOut=\"hoverOff(this);\" ";
        tmpOutput += "style=\"margin-left:" + ((j * 32) + (k * 3)) + "px; margin-top:-" + ((k * 32) + (k * 2)) + "px;\">";
        tmpOutput += "</div>\n";
      }
//      tmpOutput += "</div>\n";
    }
    tmpOutput += "</div>\n\n";

    return tmpOutput;
  }

}
