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

    setHorizontalView(1);
    setVerticalView(1);
    setDiagonalView(0);

    setGroundAuthority(Ground.MOUNTAIN);

    int[] tmpAttacks = new int[] { FightMoves.BEAT, FightMoves.CUT, FightMoves.KICK };
    setAttacks(tmpAttacks);
    int[] tmpDefends = new int[] { FightMoves.BEAT, FightMoves.CUT, FightMoves.KICK, FightMoves.SHOT };
    setDefences(tmpDefends);
  }
}
