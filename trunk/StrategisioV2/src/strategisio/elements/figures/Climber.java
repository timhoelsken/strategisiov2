package strategisio.elements.figures;

import strategisio.elements.constants.FightMoves;
import strategisio.elements.constants.Ground;

/**
 * @author Tobias
 *
 * the climber
 */
public class Climber extends Figure {

  /**
   * standard constructor
   */
  public Climber() {
    setNormalSteps(1);
    setDiagonalSteps(0);

    setNormalView(1);
    setDiagonalView(0);

    setGroundAuthority(Ground.MOUNTAIN);

    int[] tmpAttacks = new int[] { FightMoves.HIT, FightMoves.CUT, FightMoves.KICK };
    setAttacks(tmpAttacks);
    int[] tmpDefends = new int[] { FightMoves.HIT, FightMoves.CUT, FightMoves.KICK, FightMoves.SHOT };
    setDefences(tmpDefends);
  }
}
