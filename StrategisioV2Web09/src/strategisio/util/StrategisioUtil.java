package strategisio.util;

import java.util.ArrayList;

/**
 * @author Tobias
 *
 */
public class StrategisioUtil {

	//TODO why is this a util class and not part of the Team class? :D
  /**
   * @param aField
   * @param aTeamView
   * @return true if aField-Coordinates are in aTeamView-Coordinate-List, false
   *         if not
   */
  public static boolean isFieldInTeamView(int[] aField, ArrayList<int[]> aTeamView) {
    for (int[] tmpFieldCoordinates : aTeamView) {
      if (aField[0] == tmpFieldCoordinates[0] && aField[1] == tmpFieldCoordinates[1]) {
        return true;
      }
    }

    return false;
  }
}
