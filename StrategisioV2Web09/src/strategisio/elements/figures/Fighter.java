package strategisio.elements.figures;

import strategisio.constants.FightMoves;
import strategisio.constants.Ground;

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
    setNormalSteps(3);
    setDiagonalSteps(0);

    setNormalView(2);
    setDiagonalView(1);

    setFigureType("Fighter");
    setGroundAuthorities(new int[] { Ground.GRASS });

    int[] tmpFightMoves = new int[] { FightMoves.HIT, FightMoves.CUT, FightMoves.KICK, FightMoves.SHOT };
    setAttacks(tmpFightMoves);
    setDefences(tmpFightMoves);
  }

  /**
   * @return the image for this figure
   */
  public String getImage() {
    return new String("Fighter.png");
  }
}
