package strategisio.objects.figures;

import strategisio.objects.FightMoves;
import strategisio.objects.Ground;

public class Climber extends Figure {

	public Climber() {
		setHorizontalSteps(1);
		setVerticalSteps(1);
		setDiagonalSteps(0);

		setHorizontalView(1);
		setVerticalView(1);
		setDiagonalView(0);

		setGroundAuthority(Ground.MOUNTAIN);

		int[] tmpAttacks = new int[]{FightMoves.BEAT, FightMoves.CUT, FightMoves.KICK};
		setAttacks(tmpAttacks);
		int[] tmpDefends = new int[]{FightMoves.BEAT, FightMoves.CUT, FightMoves.KICK, FightMoves.SHOT};
		setDefences(tmpDefends);

		setVisible(true);
	}
}
