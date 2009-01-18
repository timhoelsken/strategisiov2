/**
 *
 */
package strategisio;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import junit.textui.TestRunner;

/**
 * @author Tobias
 * 
 */
public class AllTests extends TestCase {

  /**
   * @return all test suites of package strategisio.tests
   */
  public static Test suite() {
    TestSuite suite = new TestSuite();
    suite.addTestSuite(PlayMapTest.class);
    suite.addTestSuite(CombatTest.class);
    return suite;
  }

  /**
   * Start the PlayMapTests here.
   * 
   * @param args
   */
  public static void main(String[] args) {
    TestRunner.run(AllTests.class);
  }
}
