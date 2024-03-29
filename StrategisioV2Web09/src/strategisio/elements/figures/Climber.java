package strategisio.elements.figures;

import strategisio.constants.FightMoves;
import strategisio.constants.Ground;

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
    setDiagonalView(1);

    setFigureType("Climber");
    setGroundAuthorities(new int[] { Ground.GRASS, Ground.MOUNTAIN });

    int[] tmpAttacks = new int[] { FightMoves.HIT, FightMoves.CUT, FightMoves.KICK };
    setAttacks(tmpAttacks);
    int[] tmpDefends = new int[] { FightMoves.HIT, FightMoves.CUT, FightMoves.KICK, FightMoves.SHOT };
    setDefences(tmpDefends);
  }

  /**
   * @return the image for this figure
   */
  public String getImage() {
    return new String("Climber.png");
  }
}
