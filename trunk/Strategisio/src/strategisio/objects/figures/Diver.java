package strategisio.objects.figures;

import strategisio.objects.FightMoves;
import strategisio.objects.Ground;

public class Diver extends Figure {

	public Diver() {
		setHorizontalSteps(1);
		setVerticalSteps(1);
		setDiagonalSteps(0);

		setHorizontalView(1);
		setVerticalView(1);
		setDiagonalView(0);

		setGroundAuthority(Ground.WATER);

		int[] tmpAttacks = new int[]{FightMoves.BEAT, FightMoves.CUT, FightMoves.KICK};
		setAttacks(tmpAttacks);
		int[] tmpDefends = new int[]{FightMoves.BEAT, FightMoves.CUT, FightMoves.KICK, FightMoves.SHOT};
		setDefences(tmpDefends);

		setVisible(true);
	}
}
