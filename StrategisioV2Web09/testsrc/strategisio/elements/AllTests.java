/**
 *
 */
package strategisio.elements;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import strategisio.elements.CombatTest;
import strategisio.elements.PlayMapTest;
import strategisio.elements.PlayMapViewAreaTest;

/**
 * @author Tobias
 * 
 */
@RunWith(Suite.class)
@SuiteClasses( { CombatTest.class, PlayMapTest.class, PlayMapViewAreaTest.class })
public class AllTests {
}
