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
import strategisio.elements.figures.Miner;
import strategisio.elements.figures.Spy;
import strategisio.elements.items.Bomb;
import strategisio.elements.items.FakeFlag;
import strategisio.elements.items.Flag;
import strategisio.elements.items.Item;
import strategisio.elements.items.Trap;
import strategisio.exceptions.UnknownFieldGroundException;
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
   * @throws UnknownFieldGroundException
   */
  public void testPositioningItems() throws UnknownFieldGroundException {
    playMap = new PlayMap(1);
    Item tmpItem = new Flag();

    playMap.setFieldGround(0, 0, Ground.GRASS);
    assertTrue("Should be possible to set a flag on a grass field.", playMap.checkPositioningPossibility(
        tmpItem, 0, 0));
    playMap.setFieldGround(0, 0, Ground.MOUNTAIN);
    assertFalse("Should not be possible to set a flag on a mountain field.", playMap
        .checkPositioningPossibility(tmpItem, 0, 0));
    playMap.setFieldGround(0, 0, Ground.WATER);
    assertFalse("Should not be possible to set a flag on a water field.", playMap.checkPositioningPossibility(
        tmpItem, 0, 0));
  }

  /**
   * Tests positioning every kind of figures on grass.
   * 
   * @throws UnknownFieldGroundException
   */
  public void testPositioningFiguresOnGrass() throws UnknownFieldGroundException {
    playMap = new PlayMap(1, 3);

    playMap.setFieldGround(0, 0, Ground.GRASS);
    playMap.setFieldGround(0, 1, Ground.GRASS);
    playMap.setFieldGround(0, 2, Ground.GRASS);

    Fighter tmpFighter = new Fighter();
    Climber tmpClimber = new Climber();
    Diver tmpDiver = new Diver();
    assertTrue("Should be possible to set a fighter on a grass field.", playMap.checkPositioningPossibility(
        tmpFighter, 0, 0));
    assertTrue("Should be possible to set a climber on a grass field.", playMap.checkPositioningPossibility(
        tmpClimber, 0, 1));
    assertTrue("Should be possible to set a diver on a grass field.", playMap.checkPositioningPossibility(
        tmpDiver, 0, 2));
    playMap.positionWithoutCheck(tmpFighter, 0, 0);
    playMap.positionWithoutCheck(tmpClimber, 0, 1);
    playMap.positionWithoutCheck(tmpDiver, 0, 2);
    tmpFighter = (Fighter) playMap.fetchSetter(0, 0);
  }

  /**
   * Tests positioning figures on all three grounds.
   * 
   * @throws UnknownFieldGroundException
   */
  public void testPositioningFigures() throws UnknownFieldGroundException {
    playMap = new PlayMap(1, 3);

    playMap.setFieldGround(0, 0, Ground.GRASS);
    playMap.setFieldGround(0, 1, Ground.MOUNTAIN);
    playMap.setFieldGround(0, 2, Ground.WATER);

    Fighter tmpFighter = new Fighter();
    assertTrue("Should be possible to set a fighter on a grass field.", playMap.checkPositioningPossibility(
        tmpFighter, 0, 0));
    assertFalse("Should not be possible to set a fighter on a mountain field.", playMap
        .checkPositioningPossibility(tmpFighter, 0, 1));
    assertFalse("Should not be possible to set a fighter on a water field.", playMap
        .checkPositioningPossibility(tmpFighter, 0, 2));
    playMap.positionWithoutCheck(tmpFighter, 0, 0);

    Climber tmpClimber = new Climber();
    assertFalse("Should be a fighter there.", playMap.checkPositioningPossibility(tmpClimber, 0, 0));
    assertTrue("Should be possible to set a climber on a mountain field.", playMap
        .checkPositioningPossibility(tmpClimber, 0, 1));
    assertFalse("Should not be possible to set a climber on a water field.", playMap
        .checkPositioningPossibility(tmpClimber, 0, 2));
    playMap.positionWithoutCheck(tmpClimber, 0, 1);

    Diver tmpDiver = new Diver();
    assertFalse("Should be a fighter there.", playMap.checkPositioningPossibility(tmpDiver, 0, 0));
    assertFalse("Should not be possible to set a diver on a mountain field.", playMap
        .checkPositioningPossibility(tmpDiver, 0, 1));
    assertTrue("Should be possible to set a climber on a water field.", playMap.checkPositioningPossibility(
        tmpDiver, 0, 2));
    playMap.positionWithoutCheck(tmpDiver, 0, 2);
  }

  /**
   * Tests moving figures of the same team (NULL) vertically on grass.
   * 
   * @throws UnknownFieldGroundException
   */
  public void testMovingFiguresVerticallyOnGrass() throws UnknownFieldGroundException {
    playMap = new PlayMap(1, 2);

    playMap.setFieldGround(0, 0, Ground.GRASS);
    playMap.setFieldGround(0, 1, Ground.GRASS);

    Fighter tmpFighter = new Fighter();
    playMap.positionWithoutCheck(tmpFighter, 0, 0);
    tmpFighter = (Fighter) playMap.fetchSetter(0, 0);

    assertTrue("Should be possible to move the fighter on an empty field.", playMap.checkMovingPossibility(
        tmpFighter, 0, 1));
    playMap.moveWithoutCheck(tmpFighter, 0, 1);

    Spy tmpSpy = new Spy();
    assertTrue("The field should be free again.", playMap.checkPositioningPossibility(tmpSpy, 0, 0));
    playMap.positionWithoutCheck(tmpSpy, 0, 0);

    tmpSpy = (Spy) playMap.fetchSetter(0, 0);

    assertFalse("Field should be filled by the fighter.", playMap.checkMovingPossibility(tmpSpy, 0, 1));
    assertTrue("Should be allowed to go back to the same field.", playMap.checkMovingPossibility(tmpSpy, 0, 0));

    tmpFighter = (Fighter) playMap.fetchSetter(0, 1);
    assertTrue("Should be able to back again because spy is gone.", playMap.checkMovingPossibility(tmpFighter,
        0, 0));
  }

  /**
   * Tests moving figures of the same team (NULL) horizontally on grass.
   * 
   * @throws UnknownFieldGroundException
   */
  public void testMovingFiguresHorizontallyOnGrass() throws UnknownFieldGroundException {
    playMap = new PlayMap(2, 1);

    playMap.setFieldGround(0, 0, Ground.GRASS);
    playMap.setFieldGround(1, 0, Ground.GRASS);

    Fighter tmpFighter = new Fighter();
    playMap.positionWithoutCheck(tmpFighter, 0, 0);
    tmpFighter = (Fighter) playMap.fetchSetter(0, 0);

    assertTrue("Should be possible to move the fighter on an empty field.", playMap.checkMovingPossibility(
        tmpFighter, 1, 0));
    playMap.moveWithoutCheck(tmpFighter, 1, 0);

    Spy tmpSpy = new Spy();
    assertTrue("The field should be free again.", playMap.checkPositioningPossibility(tmpSpy, 0, 0));
    playMap.positionWithoutCheck(tmpSpy, 0, 0);

    tmpSpy = (Spy) playMap.fetchSetter(0, 0);

    assertFalse("Field should be filled by the fighter.", playMap.checkMovingPossibility(tmpSpy, 1, 0));
    assertTrue("Should be allowed to go back to the same field.", playMap.checkMovingPossibility(tmpSpy, 0, 0));

    tmpFighter = (Fighter) playMap.fetchSetter(1, 0);
    assertTrue("Should be able to back again because spy is gone.", playMap.checkMovingPossibility(tmpFighter,
        0, 0));
  }

  /**
   * Tests moving medics of the same team (NULL) diagonally on grass.
   * 
   * @throws UnknownFieldGroundException
   */
  public void testMovingMedicsDiagonallyOnGrass() throws UnknownFieldGroundException {
    playMap = new PlayMap(2, 2);

    playMap.setFieldGround(0, 0, Ground.GRASS);
    playMap.setFieldGround(0, 1, Ground.GRASS);
    playMap.setFieldGround(1, 0, Ground.GRASS);
    playMap.setFieldGround(1, 1, Ground.GRASS);

    Medic tmpMedic1 = new Medic();
    playMap.positionWithoutCheck(tmpMedic1, 0, 0);
    tmpMedic1 = (Medic) playMap.fetchSetter(0, 0);

    assertTrue("Should be possible to move the medic on an empty field.", playMap.checkMovingPossibility(
        tmpMedic1, 1, 1));
    playMap.moveWithoutCheck(tmpMedic1, 1, 1);

    Medic tmpMedic2 = new Medic();
    assertTrue("The field should be free again.", playMap.checkPositioningPossibility(tmpMedic2, 0, 0));
    playMap.positionWithoutCheck(tmpMedic2, 0, 0);

    tmpMedic2 = (Medic) playMap.fetchSetter(0, 0);

    assertFalse("Field should be filled by the other medic.", playMap.checkMovingPossibility(tmpMedic2, 1, 1));
    assertTrue("Should be allowed to move on an empty field.", playMap.checkMovingPossibility(tmpMedic2, 0, 1));
    playMap.positionWithoutCheck(tmpMedic2, 0, 1);

    tmpMedic1 = (Medic) playMap.fetchSetter(1, 1);
    assertTrue("Should be able to back again because other medic is on other field.", playMap
        .checkMovingPossibility(tmpMedic1, 0, 0));

  }

  /**
   * @throws UnknownFieldGroundException
   * 
   */
  public void testGroundMovingTest() throws UnknownFieldGroundException {
    playMap = new PlayMap(1, 3);

    playMap.setFieldGround(0, 0, Ground.GRASS);
    playMap.setFieldGround(0, 1, Ground.MOUNTAIN);
    playMap.setFieldGround(0, 2, Ground.GRASS);

    Fighter tmpFighter = new Fighter();
    playMap.positionWithoutCheck(tmpFighter, 0, 0);

    // Spy tmpSpy = new Spy();
    // playMap.position(tmpSpy, 0, 2);

    tmpFighter = (Fighter) playMap.fetchSetter(0, 0);

    assertFalse("Should not be possible to move the Fighter behind the mountain field.", playMap
        .checkMovingPossibility(tmpFighter, 0, 2));
  }

  /**
   * @throws UnknownFieldGroundException
   * 
   */
  public void testGroundDiagonalMovingTest() throws UnknownFieldGroundException {
    playMap = new PlayMap(3, 3);

    playMap.setFieldGround(0, 0, Ground.GRASS);
    playMap.setFieldGround(0, 1, Ground.GRASS);
    playMap.setFieldGround(0, 2, Ground.GRASS);
    playMap.setFieldGround(1, 0, Ground.GRASS);
    playMap.setFieldGround(1, 1, Ground.MOUNTAIN);
    playMap.setFieldGround(1, 2, Ground.GRASS);
    playMap.setFieldGround(2, 0, Ground.GRASS);
    playMap.setFieldGround(2, 1, Ground.GRASS);
    playMap.setFieldGround(2, 2, Ground.GRASS);

    TestFigure tmpTestFigure = new TestFigure();
    playMap.positionWithoutCheck(tmpTestFigure, 0, 0);

    // Spy tmpSpy = new Spy();
    // playMap.position(tmpSpy, 0, 2);

    tmpTestFigure = (TestFigure) playMap.fetchSetter(0, 0);

    assertFalse("Should not be possible to move the Fighter behind the mountain field.", playMap
        .checkMovingPossibility(tmpTestFigure, 2, 2));
  }

  /**
   * @throws UnknownFieldGroundException
   * 
   */
  public void testMoveFigureOnTrap() throws UnknownFieldGroundException {
    playMap = new PlayMap(2, 2);

    playMap.setFieldGround(0, 0, Ground.GRASS);
    playMap.setFieldGround(0, 1, Ground.GRASS);
    playMap.setFieldGround(1, 0, Ground.GRASS);
    playMap.setFieldGround(1, 1, Ground.GRASS);

    Trap tmpTrap = new Trap();
    Medic tmpMedic = new Medic();
    Spy tmpSpy = new Spy();

    tmpTrap.setId('b');
    tmpMedic.setId('a');
    tmpSpy.setId('a');

    playMap.positionWithoutCheck(tmpTrap, 0, 1);
    playMap.positionWithoutCheck(tmpSpy, 0, 0);
    playMap.positionWithoutCheck(tmpMedic, 1, 0);
    playMap.moveWithoutCheck(tmpSpy, 0, 1);

    tmpTrap = (Trap) playMap.getSetter(0, 1);
    assertTrue("Trap should be filled.", tmpTrap.getCatched() != null);
    playMap.moveWithoutCheck(tmpMedic, 0, 1);
    assertTrue("Medic freed Spy.", playMap.getSetter(0, 1) instanceof Spy);
    assertTrue("Medic stayed on his field", playMap.getSetter(1, 0) instanceof Medic);

  }

  /**
   * 
   * @throws UnknownFieldGroundException
   */
  public void testMoveMedicOnEnemyTrap() throws UnknownFieldGroundException {
    playMap = new PlayMap(2, 2);

    playMap.setFieldGround(0, 0, Ground.GRASS);
    playMap.setFieldGround(0, 1, Ground.GRASS);
    playMap.setFieldGround(1, 0, Ground.GRASS);
    playMap.setFieldGround(1, 1, Ground.GRASS);

    Trap tmpTrap = new Trap();
    Medic tmpMedic = new Medic();

    tmpTrap.setId('b');
    tmpMedic.setId('a');

    playMap.positionWithoutCheck(tmpMedic, 0, 0);
    playMap.positionWithoutCheck(tmpTrap, 1, 0);

    assertTrue("Medic can destroy enemy trap", playMap.checkMovingPossibility(tmpMedic, 0, 1));
    playMap.moveWithoutCheck(tmpMedic, 0, 1);
    assertTrue("Medic destroyed trap", playMap.getSetter(0, 1) instanceof Medic);

  }

  /**
   * 
   * @throws UnknownFieldGroundException
   */
  public void testMoveMedicOnTeamTrap() throws UnknownFieldGroundException {
    playMap = new PlayMap(2, 2);

    playMap.setFieldGround(0, 0, Ground.GRASS);
    playMap.setFieldGround(0, 1, Ground.GRASS);
    playMap.setFieldGround(1, 0, Ground.GRASS);
    playMap.setFieldGround(1, 1, Ground.GRASS);

    Trap tmpTrap = new Trap();
    Medic tmpMedic = new Medic();
    Spy tmpSpy = new Spy();

    tmpTrap.setId('a');
    tmpMedic.setId('a');
    tmpSpy.setId('b');

    playMap.positionWithoutCheck(tmpMedic, 0, 0);
    playMap.positionWithoutCheck(tmpTrap, 1, 0);
    playMap.positionWithoutCheck(tmpSpy, 1, 1);

    playMap.moveWithoutCheck(tmpSpy, 1, 0);
    tmpTrap = (Trap) playMap.getSetter(1, 0);
    assertTrue("Enemy Spy caught in trap", tmpTrap.getCatched() instanceof Spy);
    playMap.move(tmpMedic, 1, 0);
    assertTrue("Medic can defeat enemy in trap", playMap.getSetter(1, 0) instanceof Trap);
    assertTrue("Medic stayed on his field", playMap.getSetter(0, 0) instanceof Medic);
    assertFalse("Medic cannot move on empty team trap", playMap.checkMovingPossibility(tmpMedic, 1, 0));

  }

  /**
   * @throws UnknownFieldGroundException
   * 
   */
  public void testMoveFigureOnBomb() throws UnknownFieldGroundException {
    playMap = new PlayMap(1, 4);

    playMap.setFieldGround(0, 0, Ground.GRASS);
    playMap.setFieldGround(0, 1, Ground.GRASS);
    playMap.setFieldGround(0, 2, Ground.GRASS);
    playMap.setFieldGround(0, 3, Ground.GRASS);

    Bomb tmpBomb = new Bomb();
    Bomb tmpAnotherBomb = new Bomb();
    Medic tmpMedic = new Medic();
    Miner tmpMiner = new Miner();
    tmpBomb.setId('b');
    tmpAnotherBomb.setId('b');
    tmpMedic.setId('a');
    tmpMiner.setId('a');
    playMap.positionWithoutCheck(tmpBomb, 0, 0);
    playMap.positionWithoutCheck(tmpMiner, 0, 1);
    playMap.positionWithoutCheck(tmpMedic, 0, 2);
    playMap.positionWithoutCheck(tmpAnotherBomb, 0, 3);
    playMap.moveWithoutCheck(tmpMiner, 0, 0);

    assertTrue("There should be a Miner.", playMap.getSetter(0, 0) instanceof Miner);
    playMap.moveWithoutCheck(tmpMedic, 0, 3);
    assertTrue("Medic should be bombed away, field empty.", playMap.getSetter(0, 3) == null);
  }

  /**
   * @throws UnknownFieldGroundException
   * 
   */
  public void testMoveFigureOnFakeFlag() throws UnknownFieldGroundException {
    playMap = new PlayMap(1, 4);

    playMap.setFieldGround(0, 0, Ground.GRASS);
    playMap.setFieldGround(0, 1, Ground.GRASS);

    FakeFlag tmpFakeFlag = new FakeFlag();

    Miner tmpMiner = new Miner();
    tmpFakeFlag.setId('b');

    tmpMiner.setId('a');
    playMap.positionWithoutCheck(tmpFakeFlag, 0, 0);
    playMap.positionWithoutCheck(tmpMiner, 0, 1);

    playMap.moveWithoutCheck(tmpMiner, 0, 0);

    assertTrue("There should be a Miner.", playMap.getSetter(0, 0) instanceof Miner);
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
