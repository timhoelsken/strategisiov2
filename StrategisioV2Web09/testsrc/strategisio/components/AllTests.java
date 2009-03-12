/**
 *
 */
package strategisio.components;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import strategisio.components.CombatTest;
import strategisio.components.PlayMapTest;
import strategisio.components.PlayMapViewAreaTest;

/**
 * @author Tobias
 * 
 */
@RunWith(Suite.class)
@SuiteClasses( { CombatTest.class, PlayMapTest.class, PlayMapViewAreaTest.class })
public class AllTests {
}
