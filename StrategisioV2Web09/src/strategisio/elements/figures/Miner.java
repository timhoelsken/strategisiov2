package strategisio.elements.figures;

import strategisio.constants.FightMoves;
import strategisio.constants.Ground;

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

    setNormalView(1);
    setDiagonalView(1);

    setFigureType("Miner");
    setGroundAuthorities(new int[] { Ground.GRASS });

    int[] tmpFightMoves = new int[] { FightMoves.HIT, FightMoves.CUT, FightMoves.KICK };
    setAttacks(tmpFightMoves);
    setDefences(tmpFightMoves);
  }

  /**
   * @return the image for this figure
   */
  public String getImage() {
    return new String("Miner.png");
  }
}
