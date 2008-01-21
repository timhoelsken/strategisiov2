package strategisio.elements.figures;

import strategisio.elements.constants.FightMoves;
import strategisio.elements.constants.Ground;


public class Spy extends Figure {

	public Spy() {
		setHorizontalSteps(3);
		setVerticalSteps(3);
		setDiagonalSteps(0);

		setHorizontalView(2);
		setVerticalView(2);
		setDiagonalView(0);

		setGroundAuthority(Ground.GRASS);

		int[] tmpAttacks = new int[]{FightMoves.BEAT, FightMoves.CUT};
		setAttacks(tmpAttacks);
		int[] tmpDefences = new int[]{FightMoves.BEAT, FightMoves.CUT, FightMoves.KICK};
		setDefences(tmpDefences);
	}
}
