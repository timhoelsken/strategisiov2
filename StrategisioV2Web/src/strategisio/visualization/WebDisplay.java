package strategisio.visualization;

import strategisio.elements.PlayMap;
import strategisio.elements.constants.Ground;
import strategisio.elements.fields.Field;
import strategisio.exceptions.UnknownFieldGroundException;

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
    tmpOutput = "<div class=\"map\" style=\"width:" + aPlayMap.getXDimension() * 34.75 + "px; ";
    tmpOutput += "height:" + aPlayMap.getYDimension() * 34.75 + "px;\">\n";
    // loop for row
    for (int i = 0; i < aPlayMap.getYDimension(); i++) {
      // loop for column
      for (int j = 0; j < aPlayMap.getXDimension(); j++) {
        tmpField = aPlayMap.getField(j, i);
        tmpOutput += "<div id=\"" + j + "/" + i + "\" ";
        try {
          tmpOutput += "class=\"field " + Ground.getGroundLabeling(tmpField.getGround()) + "\" ";
        } catch (UnknownFieldGroundException e) {
          e.printStackTrace();
        }
        tmpOutput += "onMouseOver=\"hoverOn(this);\" onMouseOut=\"hoverOff(this);\">";
        tmpOutput += "</div>\n";
      }
    }
    tmpOutput += "</div>\n";

    return tmpOutput;
  }

}
