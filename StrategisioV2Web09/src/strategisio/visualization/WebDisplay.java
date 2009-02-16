package strategisio.visualization;

import java.util.ArrayList;

import strategisio.elements.Placeable;
import strategisio.elements.PlayMap;
import strategisio.elements.Team;
import strategisio.elements.constants.Ground;
import strategisio.elements.fields.Field;
import strategisio.elements.figures.Figure;
import strategisio.elements.items.FakeFlag;
import strategisio.elements.items.Flag;
import strategisio.exceptions.CoordinateOutOfIndexException;
import strategisio.exceptions.UnknownFieldGroundException;
import strategisio.util.StrategisioUtil;

/**
 * @author Tobias, Tim
 *
 */
public class WebDisplay implements Displayable {

  /**
   * @see strategisio.visualization.Displayable#display(strategisio.elements.PlayMap)
   */
  public String display(PlayMap aPlayMap) {
    return display(aPlayMap, 'X', null);
  }

  /**
   * @see strategisio.visualization.Displayable#display(strategisio.elements.PlayMap,
   *      char, strategisio.elements.Team)
   */
  public String display(PlayMap aPlayMap, char aPlayerId, Team aTeam) {
    String tmpOutput = "";
    Field tmpField;

    ArrayList<int[]> tmpTeamView = null;
    try {
      tmpTeamView = aPlayMap.getTeamViewArea(aTeam);
    } catch (CoordinateOutOfIndexException e) {
      // TODO display an elegant error message for both users :)
      e.printStackTrace();
    }

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
        String tmpImageColorPrefix = "";
        String tmpPlacable = "item";
        if (tmpSetter != null) {
          if (aPlayerId == 'X'
              || StrategisioUtil.isFieldInTeamView(tmpSetter.getCurrentCoordinates(), tmpTeamView)) {
            char tmpSetterId = tmpSetter.getId();
            if (tmpSetter instanceof Figure) {
              switch (tmpSetterId) {
                case 'A':
                  tmpColor = "#ff0000";
                  tmpImageColorPrefix = "coloredSetter/" + "red";
                  break;
                case 'B':
                  tmpColor = "#0000ff";
                  tmpImageColorPrefix = "coloredSetter/" + "blu";
                  break;
              }
              tmpPlacable = "figure";
            }
            if (tmpSetterId == aPlayerId) {
              // if own setter

              tmpOutput += "onClick=\"checkUserAction(this);\" ";
              tmpOutput += "onMouseOver=\"hoverOn(this);\" onMouseOut=\"hoverOff(this);\" status=\"placed\" filled=\""
                  + tmpPlacable + "\" placablecolor=\"" + tmpColor + "\" >";
              String tmpImage = tmpImageColorPrefix + tmpSetter.getImage();
              tmpOutput += "<img src=\"resources/pictures/" + tmpImage + "\">";
            } else {
              // if opponent's setter

              tmpOutput += "onClick=\"checkUserAction(this);\" ";
              tmpOutput += "status=\"placedInView\" filled=\"" + tmpPlacable + "\" placablecolor=\""
                  + tmpColor + "\">";
              String tmpImage;
              if (tmpSetter instanceof FakeFlag) {
                tmpImage = tmpImageColorPrefix + new Flag().getImage();
              } else {
                tmpImage = tmpImageColorPrefix + tmpSetter.getImage();
              }
              tmpOutput += "<img src=\"resources/pictures/" + tmpImage + "\">";
            }
          } else {
            // invisible enemy's setter on the field
            tmpOutput += "onClick=\"checkUserAction(this);\" ";
            tmpOutput += "onMouseOver=\"hoverOn(this);\" onMouseOut=\"hoverOff(this);\" status=\"placed\" filled=\""
                + tmpPlacable + "\" placablecolor=\"#000000\" >";
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
