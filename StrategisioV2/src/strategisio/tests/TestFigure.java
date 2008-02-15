package strategisio.tests;

import strategisio.elements.constants.FightMoves;
import strategisio.elements.constants.Ground;
import strategisio.elements.figures.Figure;

/**
 * @author Tobias
 * 
 * the medic
 */
public class TestFigure extends Figure {

  /**
   * standard constructor
   */
  public TestFigure() {
    setNormalSteps(1);
    setDiagonalSteps(2);

    setNormalView(1);
    setDiagonalView(0);

    setGroundAuthorities(new int[] { Ground.GRASS });

    // Removed defend Shot, according to anleitung.html
    int[] tmpFightMoves = new int[] { FightMoves.HIT, FightMoves.CUT, FightMoves.KICK };
    setAttacks(tmpFightMoves);
    setDefences(tmpFightMoves);
  }
}
