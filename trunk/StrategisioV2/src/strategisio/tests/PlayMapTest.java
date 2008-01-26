/**
 *
 */
package strategisio.tests;

import junit.framework.JUnit4TestAdapter;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.textui.TestRunner;
import strategisio.elements.PlayMap;
import strategisio.elements.constants.Ground;
import strategisio.elements.figures.Climber;
import strategisio.elements.figures.Diver;
import strategisio.elements.figures.Fighter;
import strategisio.elements.figures.Medic;
import strategisio.elements.figures.Spy;
import strategisio.elements.items.Flag;
import strategisio.elements.items.Item;
import strategisio.exceptions.UnknownFieldTypeException;
import strategisio.visualization.ConsoleDisplay;

/**
 * @author Tobias
 *
 */
public class PlayMapTest extends TestCase {

  private PlayMap playMap;

  @SuppressWarnings("unused")
  private ConsoleDisplay console = new ConsoleDisplay();

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
   *
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
   * Tests positioning every kind of figures on grass.
   *
   * @throws UnknownFieldTypeException
   */
  public void testPositioningFiguresOnGrass() throws UnknownFieldTypeException {
    playMap = new PlayMap(2, 3);

    playMap.setFieldType(0, 0, Ground.GRASS);
    playMap.setFieldType(0, 1, Ground.GRASS);
    playMap.setFieldType(0, 2, Ground.GRASS);
    

    Fighter tmpFighter = new Fighter();
    Climber tmpClimber = new Climber();
    Diver tmpDiver = new Diver();
    assertTrue("Should be possible to set a fighter on a grass field.", playMap.checkPositioningPossibility(tmpFighter, 0, 0));
    assertTrue("Should be possible to set a climber on a grass field.", playMap.checkPositioningPossibility(tmpClimber, 0, 1));
    assertTrue("Should be possible to set a diver on a grass field.", playMap.checkPositioningPossibility(tmpDiver, 0, 2));
    playMap.position(tmpFighter, 0, 0);
    playMap.position(tmpClimber, 0, 1);
    playMap.position(tmpDiver, 0, 2);
    tmpFighter = (Fighter)playMap.fetchSetter(0, 0);
    //TODO tim writes an extra test for that (grrr...)
    //assertTrue("Should be possible to move a fighter on a grass field.", playMap.move(tmpFighter, 1, 1));
  }

  
  /**
   * Tests positioning figures on all three grounds.
   *
   * @throws UnknownFieldTypeException
   */
  public void testPositioningFigures() throws UnknownFieldTypeException {
    playMap = new PlayMap(1, 3);

    playMap.setFieldType(0, 0, Ground.GRASS);
    playMap.setFieldType(0, 1, Ground.MOUNTAIN);
    playMap.setFieldType(0, 2, Ground.WATER);

    Fighter tmpFighter = new Fighter();
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
   * Tests moving figures of the same team (NULL) vertically on grass.
   *
   * @throws UnknownFieldTypeException
   */
  public void testMovingFiguresVerticallyOnGrass() throws UnknownFieldTypeException {
    playMap = new PlayMap(1, 2);

    playMap.setFieldType(0, 0, Ground.GRASS);
    playMap.setFieldType(0, 1, Ground.GRASS);

    Fighter tmpFighter = new Fighter();
    playMap.position(tmpFighter, 0, 0);
    tmpFighter = (Fighter) playMap.fetchSetter(0, 0);

    assertTrue("Should be possible to move the fighter on an empty field.", playMap.checkMovingPossibility(tmpFighter, 0, 0, 0, 1));
    playMap.move(tmpFighter, 0, 1);

    Spy tmpSpy = new Spy();
    assertTrue("The field should be free again.", playMap.checkPositioningPossibility(tmpSpy, 0, 0));
    playMap.position(tmpSpy, 0, 0);

    tmpSpy = (Spy) playMap.fetchSetter(0, 0);

    assertFalse("Field should be filled by the fighter.", playMap.checkMovingPossibility(tmpSpy, 0, 0, 0, 1));
    assertTrue("Should be allowed to go back to the same field.", playMap.checkMovingPossibility(tmpSpy, 0, 0, 0, 0));

    tmpFighter = (Fighter) playMap.fetchSetter(0, 1);
    assertTrue("Should be able to back again because spy is gone.", playMap.checkMovingPossibility(tmpFighter, 0, 1, 0, 0));
  }

  /**
   * Tests moving figures of the same team (NULL) horizontally on grass.
   *
   * @throws UnknownFieldTypeException
   */
  public void testMovingFiguresHorizontallyOnGrass() throws UnknownFieldTypeException {
    playMap = new PlayMap(2, 1);

    playMap.setFieldType(0, 0, Ground.GRASS);
    playMap.setFieldType(1, 0, Ground.GRASS);

    Fighter tmpFighter = new Fighter();
    playMap.position(tmpFighter, 0, 0);
    tmpFighter = (Fighter) playMap.fetchSetter(0, 0);

    assertTrue("Should be possible to move the fighter on an empty field.", playMap.checkMovingPossibility(tmpFighter, 0, 0, 1, 0));
    playMap.move(tmpFighter, 1, 0);

    Spy tmpSpy = new Spy();
    assertTrue("The field should be free again.", playMap.checkPositioningPossibility(tmpSpy, 0, 0));
    playMap.position(tmpSpy, 0, 0);

    tmpSpy = (Spy) playMap.fetchSetter(0, 0);

    assertFalse("Field should be filled by the fighter.", playMap.checkMovingPossibility(tmpSpy, 0, 0, 1, 0));
    assertTrue("Should be allowed to go back to the same field.", playMap.checkMovingPossibility(tmpSpy, 0, 0, 0, 0));

    tmpFighter = (Fighter) playMap.fetchSetter(1, 0);
    assertTrue("Should be able to back again because spy is gone.", playMap.checkMovingPossibility(tmpFighter, 1, 0, 0, 0));
  }

  /**
   * Tests moving medics of the same team (NULL) diagonally on grass.
   *
   * @throws UnknownFieldTypeException
   */
  public void testMovingMedicsDiagonallyOnGrass() throws UnknownFieldTypeException {
    playMap = new PlayMap(2, 2);

    playMap.setFieldType(0, 0, Ground.GRASS);
    playMap.setFieldType(0, 1, Ground.GRASS);
    playMap.setFieldType(1, 0, Ground.GRASS);
    playMap.setFieldType(1, 1, Ground.GRASS);

    Medic tmpMedic1 = new Medic();
    playMap.position(tmpMedic1, 0, 0);
    tmpMedic1 = (Medic) playMap.fetchSetter(0, 0);

    assertTrue("Should be possible to move the medic on an empty field.", playMap.checkMovingPossibility(tmpMedic1, 0, 0, 1, 1));
    playMap.move(tmpMedic1, 1, 1);

    Medic tmpMedic2 = new Medic();
    assertTrue("The field should be free again.", playMap.checkPositioningPossibility(tmpMedic2, 0, 0));
    playMap.position(tmpMedic2, 0, 0);

    tmpMedic2 = (Medic) playMap.fetchSetter(0, 0);

    assertFalse("Field should be filled by the other medic.", playMap.checkMovingPossibility(tmpMedic2, 0, 0, 1, 1));
    assertTrue("Should be allowed to move on an empty field.", playMap.checkMovingPossibility(tmpMedic2, 0, 0, 0, 1));
    playMap.position(tmpMedic2, 0, 1);

    tmpMedic1 = (Medic) playMap.fetchSetter(1, 1);
    assertTrue("Should be able to back again because other medic is on other field.", playMap.checkMovingPossibility(tmpMedic1, 1, 1, 0, 0));

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
