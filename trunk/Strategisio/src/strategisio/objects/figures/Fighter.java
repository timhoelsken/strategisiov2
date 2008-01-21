package strategisio.objects.figures;

import strategisio.objects.FightMoves;
import strategisio.objects.Ground;

public class Fighter extends Figure {

	public Fighter() {
		setHorizontalSteps(2);
		setVerticalSteps(2);
		setDiagonalSteps(0);

		setHorizontalView(1);
		setVerticalView(1);
		setDiagonalView(1);

		setGroundAuthority(Ground.GRASS);

		int[] tmpFightMoves = new int[]{FightMoves.BEAT, FightMoves.CUT, FightMoves.KICK, FightMoves.SHOT};
		setAttacks(tmpFightMoves);
		setDefences(tmpFightMoves);

		setVisible(true);
	}
}
