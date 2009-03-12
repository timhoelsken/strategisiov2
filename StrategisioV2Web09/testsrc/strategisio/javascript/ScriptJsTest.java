package strategisio.javascript;

import junit.framework.TestCase;
import net.sf.jstester.JsTester;

/**
 * @author Tobias
 *
 */
public class ScriptJsTest extends TestCase {

  private JsTester jsTester;

  /**
   * sets up the jsTester
   */
  @Override
  public void setUp() {
    jsTester = new JsTester();
    jsTester.onSetUp();
    jsTester.eval(JsTester.loadScript("script.js"));
  }

  /**
   * tears down the jsTester
   */
  @Override
  public void tearDown() {
    jsTester.onTearDown();
  }

  /**
   * tests if all functions are there
   */
  public void testAssertFunctions() {
    jsTester.assertIsFunction("hoverOn");
    jsTester.assertIsFunction("hoverOff");
    jsTester.assertIsFunction("globalHoverOn");
    jsTester.assertIsFunction("globalHoverOff");
    jsTester.assertIsFunction("moveHoverOn");
    jsTester.assertIsFunction("moveHoverOff");
    jsTester.assertIsFunction("resolveViewField");
    jsTester.assertIsFunction("unmarkField");
    jsTester.assertIsFunction("openMessageBox");
    jsTester.assertIsFunction("openUncloseableMessageBox");
    jsTester.assertIsFunction("closeMessageBox");
    jsTester.assertIsFunction("checkUserAction");
    jsTester.assertIsFunction("buildAnswer");

    // TODO put ajax functions in another file
    jsTester.assertIsFunction("sendRequest");
    jsTester.assertIsFunction("getAnswer");
    jsTester.assertIsFunction("setRefreshedMap");
    jsTester.assertIsFunction("doRefreshRequest");
    jsTester.assertIsFunction("refresh");
  }

  /**
   * tests if all global variables are there
   */
  public void testAssertGlobalVars() {
    jsTester.assertIsEmpty("req");
    jsTester.assertIsEmpty("movingFigureId");
    jsTester.assertIsArray("viewField");
  }


}
