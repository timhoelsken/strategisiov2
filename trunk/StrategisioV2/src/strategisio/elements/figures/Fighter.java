package strategisio.elements.figures;

import strategisio.elements.constants.FightMoves;
import strategisio.elements.constants.Ground;

/**
 * @author Tobias
 *
 * the fighter
 */
public class Fighter extends Figure {

  /**
   * standard constructor
   */
  public Fighter() {
    setNormalSteps(2);
    setDiagonalSteps(0);

    setNormalView(1);
    setDiagonalView(1);

    setGroundAuthority(Ground.GRASS);

    int[] tmpFightMoves = new int[] { FightMoves.BEAT, FightMoves.CUT, FightMoves.KICK, FightMoves.SHOT };
    setAttacks(tmpFightMoves);
    setDefences(tmpFightMoves);
  }
}
