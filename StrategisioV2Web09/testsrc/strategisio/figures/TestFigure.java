package strategisio.figures;

import strategisio.constants.FightMoves;
import strategisio.constants.Ground;
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

  /**
   * @return the image for this figure
   */
  public String getImage() {
    return null;
  }
}
