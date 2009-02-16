package strategisio.elements.figures;

import strategisio.elements.constants.FightMoves;
import strategisio.elements.constants.Ground;

/**
 * @author Tobias
 * 
 * the medic
 */
public class Medic extends Figure {

  /**
   * standard constructor
   */
  public Medic() {
    setNormalSteps(1);
    setDiagonalSteps(1);

    setNormalView(1);
    setDiagonalView(1);

    setFigureType("Medic");
    setGroundAuthorities(new int[] { Ground.GRASS });

    // Removed defend Shot, according to anleitung.html
    int[] tmpFightMoves = new int[] { FightMoves.HIT, FightMoves.CUT, FightMoves.KICK };
    setAttacks(tmpFightMoves);
    setDefences(tmpFightMoves);
  }

  /**
   * @return the image for this figure
   */
  public String getImage() {
    return new String("Medic.png");
  }
}
