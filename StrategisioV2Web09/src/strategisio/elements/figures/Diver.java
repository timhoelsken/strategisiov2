package strategisio.elements.figures;

import strategisio.constants.FightMoves;
import strategisio.constants.Ground;

/**
 * @author Tobias
 * 
 * the diver
 */
public class Diver extends Figure {

  /**
   * standard constructor
   */
  public Diver() {
    setNormalSteps(1);
    setDiagonalSteps(0);

    setNormalView(1);
    setDiagonalView(1);

    setFigureType("Diver");
    setGroundAuthorities(new int[] { Ground.GRASS, Ground.WATER });

    int[] tmpAttacks = new int[] { FightMoves.HIT, FightMoves.CUT, FightMoves.KICK };
    setAttacks(tmpAttacks);
    int[] tmpDefends = new int[] { FightMoves.HIT, FightMoves.CUT, FightMoves.KICK, FightMoves.SHOT };
    setDefences(tmpDefends);
  }

  /**
   * @return the image for this figure
   */
  public String getImage() {
    return new String("Diver.png");
  }
}
