/**
 *
 */
package strategisio.tests;

import junit.framework.JUnit4TestAdapter;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.textui.TestRunner;
import strategisio.elements.PlayMap;
import strategisio.elements.UnknownFieldTypeException;
import strategisio.elements.constants.Ground;
import strategisio.elements.figures.Climber;
import strategisio.elements.figures.Diver;
import strategisio.elements.figures.Fighter;
import strategisio.elements.items.Flag;
import strategisio.elements.items.Item;

/**
 * @author Tobias
 *
 */
public class PlayMapTest extends TestCase {

  private PlayMap playMap;

  /**
   * All things that should happen before each test in this class.
   */
  protected void setUp() {
    // nothing yet
  }

  /**
   * All things that should happen after each test in this class.
   */
  protected void tearDown() {
    // nothing yet
  }

  /**
   * Tests positioning an item on all three grounds.
   * @throws UnknownFieldTypeException
   */
  public void testPositioningItems() throws UnknownFieldTypeException {
    playMap = new PlayMap(1);
    Item tmpItem = new Flag();

    playMap.setFieldType(0, 0, Ground.GRASS);
    assertTrue("Should be possible to set a flag on a grass field.", playMap.checkPositioningPossibility(tmpItem, 0, 0));
    playMap.setFieldType(0, 0, Ground.MOUNTAIN);
    assertFalse("Should not be possible to set a flag on a mountain field.", playMap.checkPositioningPossibility(tmpItem, 0, 0));
    playMap.setFieldType(0, 0, Ground.WATER);
    assertFalse("Should not be possible to set a flag on a water field.", playMap.checkPositioningPossibility(tmpItem, 0, 0));
  }

  /**
   * Tests positioning figures on all three grounds.
   * @throws UnknownFieldTypeException
   */
  public void testPositioningFigures() throws UnknownFieldTypeException {
    playMap = new PlayMap(1, 3);
    Fighter tmpFighter = new Fighter();

    playMap.setFieldType(0, 0, Ground.GRASS);
    playMap.setFieldType(0, 1, Ground.MOUNTAIN);
    playMap.setFieldType(0, 2, Ground.WATER);

    assertTrue("Should be possible to set a fighter on a grass field.", playMap.checkPositioningPossibility(tmpFighter, 0, 0));
    assertFalse("Should not be possible to set a fighter on a mountain field.", playMap.checkPositioningPossibility(tmpFighter, 0, 1));
    assertFalse("Should not be possible to set a fighter on a water field.", playMap.checkPositioningPossibility(tmpFighter, 0, 2));
    playMap.position(tmpFighter, 0, 0);

    Climber tmpClimber = new Climber();
    assertFalse("Should be a fighter there.", playMap.checkPositioningPossibility(tmpClimber, 0, 0));
    assertTrue("Should be possible to set a climber on a mountain field.", playMap.checkPositioningPossibility(tmpClimber, 0, 1));
    assertFalse("Should not be possible to set a climber on a water field.", playMap.checkPositioningPossibility(tmpClimber, 0, 2));
    playMap.position(tmpClimber, 0, 1);

    Diver tmpDiver = new Diver();
    assertFalse("Should be a fighter there.", playMap.checkPositioningPossibility(tmpDiver, 0, 0));
    assertFalse("Should not be possible to set a diver on a mountain field.", playMap.checkPositioningPossibility(tmpDiver, 0, 1));
    assertTrue("Should be possible to set a climber on a water field.", playMap.checkPositioningPossibility(tmpDiver, 0, 2));
    playMap.position(tmpDiver, 0, 2);
  }

  /**
   * @return the test suite
   */
  public static Test suite() {
    return new JUnit4TestAdapter(PlayMapTest.class);
  }

  /**
   * Start the PlayMapTests here.
   *
   * @param args
   */
  public static void main(String[] args) {
    TestRunner.run(PlayMapTest.class);
  }
}
