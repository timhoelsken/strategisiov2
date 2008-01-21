package strategisio.objects.figures;

import strategisio.objects.FightMoves;
import strategisio.objects.Ground;

public class Miner extends Figure {

	public Miner() {
		setHorizontalSteps(1);
		setVerticalSteps(1);
		setDiagonalSteps(0);

		setHorizontalView(1);
		setVerticalView(1);
		setDiagonalView(0);

		setGroundAuthority(Ground.GRASS);

		int[] tmpFightMoves = new int[]{FightMoves.BEAT, FightMoves.CUT, FightMoves.KICK};
		setAttacks(tmpFightMoves);
		setDefences(tmpFightMoves);

		setVisible(true);
	}
}
