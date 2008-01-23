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

    setGroundAuthority(Ground.GRASS);

    int[] tmpAttacks = new int[] { FightMoves.BEAT, FightMoves.CUT };
    setAttacks(tmpAttacks);
    int[] tmpDefences = new int[] { FightMoves.BEAT, FightMoves.CUT, FightMoves.KICK };
    setDefences(tmpDefences);
  }
}
