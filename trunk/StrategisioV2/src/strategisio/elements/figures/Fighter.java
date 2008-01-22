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
    setHorizontalSteps(2);
    setVerticalSteps(2);
    setDiagonalSteps(0);

    setHorizontalView(1);
    setVerticalView(1);
    setDiagonalView(1);

    setGroundAuthority(Ground.GRASS);

    int[] tmpFightMoves = new int[] { FightMoves.BEAT, FightMoves.CUT, FightMoves.KICK, FightMoves.SHOT };
    setAttacks(tmpFightMoves);
    setDefences(tmpFightMoves);
  }
}
