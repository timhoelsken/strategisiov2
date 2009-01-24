package strategisio;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.ArrayList;

import org.junit.Test;

import strategisio.elements.PlayMap;
import strategisio.elements.Team;
import strategisio.elements.figures.Figure;
import strategisio.exceptions.CoordinateOutOfIndexException;
import strategisio.exceptions.FlagLimitOverflowException;
import strategisio.exceptions.UnknownFieldGroundException;

/**
 * @author Tobias
 *
 */
public class ViewAreaTest {
  /**
   * tests the view area overbound
   *
   * @throws UnknownFieldGroundException
   * @throws FlagLimitOverflowException
   */
  @Test
  public void testViewArea() throws FlagLimitOverflowException, UnknownFieldGroundException {
    Team tmpTeam = new Team('A', "TestTeam");
    PlayMap tmpPlayMap = new PlayMap(8);

    ArrayList<Figure> tmpFigures = tmpTeam.getFigures();
    // here's a san if needed lateron
    Figure tmpFigure = tmpFigures.get(7);
    assertEquals("san_set.png", tmpFigure.getImage());

    for (int i = 0; i < tmpFigures.size() - 2; i++) {
      tmpPlayMap.position(tmpFigure, 0, i);
    }
    for (int i = tmpFigures.size() - 2; i < tmpFigures.size(); i++) {
      tmpPlayMap.position(tmpFigure, 1, i - 8);
    }

    try {
      ArrayList<int[]> tmpTeamView = tmpPlayMap.getTeamViewArea(tmpTeam);
    } catch (CoordinateOutOfIndexException e) {
      fail();
    }
  }
}
