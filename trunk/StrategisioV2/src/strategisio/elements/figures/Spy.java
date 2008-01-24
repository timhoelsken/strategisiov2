package strategisio.elements.figures;

import strategisio.elements.constants.FightMoves;
import strategisio.elements.constants.Ground;

/**
 * @author Tobias
 * 
 * the spy
 */
public class Spy extends Figure {

  /**
   * standard constructor
   */
  public Spy() {
    setNormalSteps(3);
    setDiagonalSteps(0);

    setNormalView(2);
    setDiagonalView(0);

    setGroundAuthorities(new int[] { Ground.GRASS });

    int[] tmpAttacks = new int[] { FightMoves.HIT, FightMoves.CUT };
    setAttacks(tmpAttacks);
    int[] tmpDefences = new int[] { FightMoves.HIT, FightMoves.CUT, FightMoves.KICK };
    setDefences(tmpDefences);
  }
}
