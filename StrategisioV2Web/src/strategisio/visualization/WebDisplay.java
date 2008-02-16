package strategisio.visualization;

import strategisio.elements.Placeable;
import strategisio.elements.PlayMap;
import strategisio.elements.constants.Ground;
import strategisio.elements.fields.Field;
import strategisio.elements.figures.Figure;
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
    
    tmpOutput = "<div class=\"map\" id=\"map\" style=\"width:" + aPlayMap.getXDimension() * 34.75 + "px; ";
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
        Placeable tmpSetter = tmpField.getSetter();
        String tmpColor = new String ("'#000000'");
        if (tmpSetter != null) {
          if (tmpSetter instanceof Figure) {
            tmpColor = new String();
            switch (tmpField.getSetter().getId()) {
              case 'A':
                tmpColor = "'#ff0000'";
                break;
              case 'B':
                tmpColor = "'#0000ff'";
                break;
            }
          }
            tmpOutput += "onClick=\"checkUserAction(this);\" ";
            tmpOutput += "onMouseOver=\"hoverOn(this, " + tmpColor + ");\" onMouseOut=\"hoverOff(this, " + tmpColor + ");\">";
          /*} else {
            tmpOutput += ">";
          }*/
          String tmpImage = tmpSetter.getImage();
          tmpOutput += "<img src=\"resources/pictures/" + tmpImage + "\">";
        } else {
          tmpOutput += "onClick=\"checkUserAction(this);\" ";
          tmpOutput += "onMouseOver=\"hoverOn(this, " + tmpColor + ");\" onMouseOut=\"hoverOff(this, " + tmpColor + ");\">";
        }
        tmpOutput += "</div>\n";
      }
    }
    tmpOutput += "</div>\n";

    return tmpOutput;
  }

}
