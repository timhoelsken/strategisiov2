package strategisio.elements;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.ArrayList;

import org.junit.Test;

import strategisio.components.PlayMap;
import strategisio.elements.Team;
import strategisio.elements.figures.Figure;
import strategisio.exceptions.CoordinateOutOfIndexException;
import strategisio.util.StrategisioUtil;
import strategisio.visualization.ConsoleDisplay;

/**
 * @author Tobias
 *
 */
public class PlayMapViewAreaTest {

  // TODO test for if diagonal field is really diagonal and not 1 field x and
  // 2 fields y away or something

  /**
   * tests the view area overbound
   *
   * @throws Exception
   */
  @Test
  public void testViewArea() throws Exception {
    Team tmpTeam = new Team('A', "TestTeam");
    PlayMap tmpPlayMap = new PlayMap(8);

    ArrayList<Figure> tmpFigures = tmpTeam.getFigures();
    // here's a san if needed lateron
    Figure tmpFigure = tmpFigures.get(7);
    assertEquals("Medic.png", tmpFigure.getImage());

    for (int i = 0; i < tmpFigures.size() - 2; i++) {
      tmpPlayMap.position(tmpFigures.get(i), i, 0);
    }
    for (int i = tmpFigures.size() - 2; i < tmpFigures.size(); i++) {
      tmpPlayMap.position(tmpFigures.get(i), i - 8, 1);
    }

    ArrayList<int[]> tmpTeamView = null;
    try {
      tmpTeamView = tmpPlayMap.getTeamViewArea(tmpTeam);
    } catch (CoordinateOutOfIndexException e) {
      fail();
    }

    ConsoleDisplay tmpDisplay = new ConsoleDisplay();
    tmpDisplay.display(tmpPlayMap);

    // first row
    assertTrue(StrategisioUtil.isFieldInTeamView(new int[] { 0, 0 }, tmpTeamView));
    assertTrue(StrategisioUtil.isFieldInTeamView(new int[] { 1, 0 }, tmpTeamView));
    assertTrue(StrategisioUtil.isFieldInTeamView(new int[] { 2, 0 }, tmpTeamView));
    assertTrue(StrategisioUtil.isFieldInTeamView(new int[] { 3, 0 }, tmpTeamView));
    assertTrue(StrategisioUtil.isFieldInTeamView(new int[] { 4, 0 }, tmpTeamView));
    assertTrue(StrategisioUtil.isFieldInTeamView(new int[] { 5, 0 }, tmpTeamView));
    assertTrue(StrategisioUtil.isFieldInTeamView(new int[] { 6, 0 }, tmpTeamView));
    assertTrue(StrategisioUtil.isFieldInTeamView(new int[] { 7, 0 }, tmpTeamView));

    // second row
    assertTrue(StrategisioUtil.isFieldInTeamView(new int[] { 0, 1 }, tmpTeamView));
    assertTrue(StrategisioUtil.isFieldInTeamView(new int[] { 1, 1 }, tmpTeamView));
    assertTrue(StrategisioUtil.isFieldInTeamView(new int[] { 2, 1 }, tmpTeamView));
    assertTrue(StrategisioUtil.isFieldInTeamView(new int[] { 3, 1 }, tmpTeamView));
    assertTrue(StrategisioUtil.isFieldInTeamView(new int[] { 4, 1 }, tmpTeamView));
    assertTrue(StrategisioUtil.isFieldInTeamView(new int[] { 5, 1 }, tmpTeamView));
    assertTrue(StrategisioUtil.isFieldInTeamView(new int[] { 6, 1 }, tmpTeamView));
    assertTrue(StrategisioUtil.isFieldInTeamView(new int[] { 7, 1 }, tmpTeamView));

    // third row
    assertTrue(StrategisioUtil.isFieldInTeamView(new int[] { 0, 2 }, tmpTeamView));
    assertTrue(StrategisioUtil.isFieldInTeamView(new int[] { 1, 2 }, tmpTeamView));
    assertTrue("Fighter on (0/0) should see this field.", StrategisioUtil.isFieldInTeamView(
        new int[] { 2, 2 }, tmpTeamView));
    assertFalse(StrategisioUtil.isFieldInTeamView(new int[] { 3, 2 }, tmpTeamView));
    assertFalse(StrategisioUtil.isFieldInTeamView(new int[] { 4, 2 }, tmpTeamView));
    assertFalse(StrategisioUtil.isFieldInTeamView(new int[] { 5, 2 }, tmpTeamView));
    assertFalse(StrategisioUtil.isFieldInTeamView(new int[] { 6, 2 }, tmpTeamView));
    assertFalse(StrategisioUtil.isFieldInTeamView(new int[] { 7, 2 }, tmpTeamView));

    // fourth row
    assertTrue(StrategisioUtil.isFieldInTeamView(new int[] { 0, 3 }, tmpTeamView));
    assertTrue(StrategisioUtil.isFieldInTeamView(new int[] { 1, 3 }, tmpTeamView));
    assertFalse(StrategisioUtil.isFieldInTeamView(new int[] { 2, 3 }, tmpTeamView));
    assertFalse(StrategisioUtil.isFieldInTeamView(new int[] { 3, 3 }, tmpTeamView));
    assertFalse(StrategisioUtil.isFieldInTeamView(new int[] { 4, 3 }, tmpTeamView));
    assertFalse(StrategisioUtil.isFieldInTeamView(new int[] { 5, 3 }, tmpTeamView));
    assertFalse(StrategisioUtil.isFieldInTeamView(new int[] { 6, 3 }, tmpTeamView));
    assertFalse(StrategisioUtil.isFieldInTeamView(new int[] { 7, 3 }, tmpTeamView));

    // fifth row
    assertFalse(StrategisioUtil.isFieldInTeamView(new int[] { 0, 4 }, tmpTeamView));
    assertFalse(StrategisioUtil.isFieldInTeamView(new int[] { 1, 4 }, tmpTeamView));
    assertFalse(StrategisioUtil.isFieldInTeamView(new int[] { 2, 4 }, tmpTeamView));
    assertFalse(StrategisioUtil.isFieldInTeamView(new int[] { 3, 4 }, tmpTeamView));
    assertFalse(StrategisioUtil.isFieldInTeamView(new int[] { 4, 4 }, tmpTeamView));
    assertFalse(StrategisioUtil.isFieldInTeamView(new int[] { 5, 4 }, tmpTeamView));
    assertFalse(StrategisioUtil.isFieldInTeamView(new int[] { 6, 4 }, tmpTeamView));
    assertFalse(StrategisioUtil.isFieldInTeamView(new int[] { 7, 4 }, tmpTeamView));

    // sixth row
    assertFalse(StrategisioUtil.isFieldInTeamView(new int[] { 0, 5 }, tmpTeamView));
    assertFalse(StrategisioUtil.isFieldInTeamView(new int[] { 1, 5 }, tmpTeamView));
    assertFalse(StrategisioUtil.isFieldInTeamView(new int[] { 2, 5 }, tmpTeamView));
    assertFalse(StrategisioUtil.isFieldInTeamView(new int[] { 3, 5 }, tmpTeamView));
    assertFalse(StrategisioUtil.isFieldInTeamView(new int[] { 4, 5 }, tmpTeamView));
    assertFalse(StrategisioUtil.isFieldInTeamView(new int[] { 5, 5 }, tmpTeamView));
    assertFalse(StrategisioUtil.isFieldInTeamView(new int[] { 6, 5 }, tmpTeamView));
    assertFalse(StrategisioUtil.isFieldInTeamView(new int[] { 7, 5 }, tmpTeamView));

    // seventh row
    assertFalse(StrategisioUtil.isFieldInTeamView(new int[] { 0, 6 }, tmpTeamView));
    assertFalse(StrategisioUtil.isFieldInTeamView(new int[] { 1, 6 }, tmpTeamView));
    assertFalse(StrategisioUtil.isFieldInTeamView(new int[] { 2, 6 }, tmpTeamView));
    assertFalse(StrategisioUtil.isFieldInTeamView(new int[] { 3, 6 }, tmpTeamView));
    assertFalse(StrategisioUtil.isFieldInTeamView(new int[] { 4, 6 }, tmpTeamView));
    assertFalse(StrategisioUtil.isFieldInTeamView(new int[] { 5, 6 }, tmpTeamView));
    assertFalse(StrategisioUtil.isFieldInTeamView(new int[] { 6, 6 }, tmpTeamView));
    assertFalse(StrategisioUtil.isFieldInTeamView(new int[] { 7, 6 }, tmpTeamView));

    // eight row
    assertFalse(StrategisioUtil.isFieldInTeamView(new int[] { 0, 7 }, tmpTeamView));
    assertFalse(StrategisioUtil.isFieldInTeamView(new int[] { 1, 7 }, tmpTeamView));
    assertFalse(StrategisioUtil.isFieldInTeamView(new int[] { 2, 7 }, tmpTeamView));
    assertFalse(StrategisioUtil.isFieldInTeamView(new int[] { 3, 7 }, tmpTeamView));
    assertFalse(StrategisioUtil.isFieldInTeamView(new int[] { 4, 7 }, tmpTeamView));
    assertFalse(StrategisioUtil.isFieldInTeamView(new int[] { 5, 7 }, tmpTeamView));
    assertFalse(StrategisioUtil.isFieldInTeamView(new int[] { 6, 7 }, tmpTeamView));
    assertFalse(StrategisioUtil.isFieldInTeamView(new int[] { 7, 7 }, tmpTeamView));
  }

  /**
   * @throws Exception
   */
  @Test
  public void testFieldsOutsideTheMap() throws Exception {
    Team tmpTeam = new Team('A', "TestTeam");
    PlayMap tmpPlayMap = new PlayMap(8);

    ArrayList<Figure> tmpFigures = tmpTeam.getFigures();
    // here's a san if needed lateron
    Figure tmpFigure = tmpFigures.get(7);
    assertEquals("Medic.png", tmpFigure.getImage());

    for (int i = 0; i < tmpFigures.size() - 2; i++) {
      tmpPlayMap.position(tmpFigures.get(i), i, 0);
    }
    for (int i = tmpFigures.size() - 2; i < tmpFigures.size(); i++) {
      tmpPlayMap.position(tmpFigures.get(i), i - 8, 1);
    }

    ArrayList<int[]> tmpTeamView = null;
    try {
      tmpTeamView = tmpPlayMap.getTeamViewArea(tmpTeam);
    } catch (CoordinateOutOfIndexException e) {
      fail();
    }

    ConsoleDisplay tmpDisplay = new ConsoleDisplay();
    tmpDisplay.display(tmpPlayMap);

    // upper left
    assertFalse(StrategisioUtil.isFieldInTeamView(new int[] { -1, -1 }, tmpTeamView));
    assertFalse(StrategisioUtil.isFieldInTeamView(new int[] { -1, 0 }, tmpTeamView));
    assertFalse(StrategisioUtil.isFieldInTeamView(new int[] { 0, -1 }, tmpTeamView));
  }
}
