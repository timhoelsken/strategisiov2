package strategisio.elements.figures;

import strategisio.elements.constants.FightMoves;
import strategisio.elements.constants.Ground;

/**
 * @author Tobias
 *
 * the miner
 */
public class Miner extends Figure {

  /**
   * standard constructor
   */
  public Miner() {
    setNormalSteps(1);
    setDiagonalSteps(0);

    setHorizontalView(1);
    setVerticalView(1);
    setDiagonalView(0);

    setGroundAuthority(Ground.GRASS);

    int[] tmpFightMoves = new int[] { FightMoves.BEAT, FightMoves.CUT, FightMoves.KICK };
    setAttacks(tmpFightMoves);
    setDefences(tmpFightMoves);
  }
}
