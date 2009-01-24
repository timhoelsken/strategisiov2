package strategisio.visualization;

import strategisio.elements.Placeable;
import strategisio.elements.PlayMap;
import strategisio.elements.constants.Ground;
import strategisio.elements.fields.Field;
import strategisio.elements.figures.Figure;
import strategisio.exceptions.UnknownFieldGroundException;

/**
 * @author Tobias, Tim
 *
 */
public class WebDisplay implements Displayable {

  /**
   * @see strategisio.visualization.Displayable#display(strategisio.elements.PlayMap)
   */
  public String display(PlayMap aPlayMap) {
    return display(aPlayMap, 'x');
  }

  /**
   * @see strategisio.visualization.Displayable#display(strategisio.elements.PlayMap,
   *      char)
   */
  public String display(PlayMap aPlayMap, char aPlayerId) {
    String tmpOutput = "";
    Field tmpField;

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
        String tmpColor = "#000000";
        String tmpPlacable = "item";
        if (tmpSetter != null) {
          char tmpSetterId = tmpSetter.getId();
          if (aPlayerId != 'X' && tmpSetterId == aPlayerId) {
            if (tmpSetter instanceof Figure) {
              switch (tmpSetterId) {
                case 'A':
                  tmpColor = "#ff0000";
                  break;
                case 'B':
                  tmpColor = "#0000ff";
                  break;
              }
              tmpPlacable = "figure";
            }
            tmpOutput += "onClick=\"checkUserAction(this);\" ";
            tmpOutput += "onMouseOver=\"hoverOn(this);\" onMouseOut=\"hoverOff(this);\" status=\"placed\" filled=\""
                + tmpPlacable + "\" placablecolor=\"" + tmpColor + "\" >";
            String tmpImage = tmpSetter.getImage();
            tmpOutput += "<img src=\"resources/pictures/" + tmpImage + "\">";
          } else {
            // enemy's setter on the field
            tmpOutput += "onClick=\"checkUserAction(this);\" ";
            tmpOutput += "onMouseOver=\"hoverOn(this);\" onMouseOut=\"hoverOff(this);\" status=\"empty\" filled=\"no\" placablecolor=\"#000000\" >";
          }
        } else {
          // no setter on the field
          tmpOutput += "onClick=\"checkUserAction(this);\" ";
          tmpOutput += "onMouseOver=\"hoverOn(this);\" onMouseOut=\"hoverOff(this);\" status=\"empty\" filled=\"no\" placablecolor=\"#000000\" >";
        }
        tmpOutput += "</div>";
      }
    }

    return tmpOutput;
  }

}
