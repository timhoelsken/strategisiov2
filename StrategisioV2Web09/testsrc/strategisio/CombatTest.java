/**
 *
 */
package strategisio;

import junit.framework.JUnit4TestAdapter;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.textui.TestRunner;
import strategisio.elements.Combat;
import strategisio.elements.figures.Fighter;
import strategisio.elements.figures.Medic;
import strategisio.elements.figures.Spy;
import strategisio.exceptions.UnknownFieldGroundException;
import strategisio.visualization.ConsoleDisplay;

/**
 *
 * @author Tim
 *
 */
public class CombatTest extends TestCase {
  private Combat combat;

  @SuppressWarnings("unused")
  private ConsoleDisplay console = new ConsoleDisplay();

  /**
   * All things that should happen before each test in this class.
   */
  @Override
  protected void setUp() {
    // nothing yet
  }

  /**
   * All things that should happen after each test in this class.
   */
  @Override
  protected void tearDown() {
    // nothing yet
  }

  /**
   * Tests positioning an item on all three grounds.
   *
   * @throws UnknownFieldGroundException
   */
  public void testInitFight() throws UnknownFieldGroundException {
    combat = new Combat();
    Fighter tmpFighter = new Fighter();
    Medic tmpMedic = new Medic();
    Spy tmpSpy = new Spy();

    assertTrue("Fighter should fast win.", combat.init(tmpFighter, tmpMedic) instanceof Fighter);
    assertTrue("Fighter should fast win.", combat.init(tmpMedic, tmpFighter) instanceof Fighter);
    assertTrue("Fighter should fast win.", combat.init(tmpFighter, tmpSpy) instanceof Fighter);
    assertTrue("Fighter should fast win.", combat.init(tmpSpy, tmpFighter) instanceof Fighter);
  }

  /**
   * @return the test suite
   */
  public static Test suite() {
    return new JUnit4TestAdapter(CombatTest.class);
  }

  /**
   * Start the PlayMapTests here.
   *
   * @param args
   */
  public static void main(String[] args) {
    TestRunner.run(CombatTest.class);
  }
}
