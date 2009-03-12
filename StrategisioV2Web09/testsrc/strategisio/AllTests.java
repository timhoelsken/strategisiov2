package strategisio;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

/**
 * all strategisio JUnit tests
 *
 * @author Tobias
 */
@RunWith(Suite.class)
@SuiteClasses( { strategisio.components.AllTests.class, strategisio.javascript.AllTests.class })
public class AllTests {
}
