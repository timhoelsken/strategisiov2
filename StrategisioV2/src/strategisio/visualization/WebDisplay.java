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
    tmpOutput = "\n\n<div class=\"map\" style=\"width:" + aPlayMap.getXDimension() * 32 + "px;";
    tmpOutput += "height:" + aPlayMap.getYDimension() * 32 + "px;\">\n";
    for (int i = 0; i < aPlayMap.getYDimension(); i++) {

      for (int j = 0, k = 0; j < aPlayMap.getXDimension(); j++) {
        tmpField = aPlayMap.getField(j, i);
        if (tmpField.getGround() == Ground.WATER) {
          tmpOutput += "<div class=\"field water\" ";
        } else if (tmpField.getGround() == Ground.MOUNTAIN) {
          tmpOutput += "<div class=\"field mountain\" ";
        } else {
          tmpOutput += "<div class=\"field grass\" ";
        }
        if (j != 0){
          k =1;
        }
        tmpOutput += "onMouseOver=\"hoverOn(this);\" onMouseOut=\"hoverOff(this);\" ";
        tmpOutput += "style=\"margin-left:" + j * 32 + "px;margin-top:-" + k * 32 + "px;\">";
        tmpOutput += "</div>\n";
      }
    }
    tmpOutput += "</div>\n\n";

    return tmpOutput;
  }

}
