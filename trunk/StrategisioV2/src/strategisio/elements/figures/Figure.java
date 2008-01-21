package strategisio.elements.figures;


/**
 * Abstract class for all figures
 */
abstract class Figure {

	private int horizontalSteps;

	private int verticalSteps;

	private int diagonalSteps;

	private int horizontalView;

	private int verticalView;

	private int diagonalView;

	private int groundAuthority;

	private int[] attacks;

	private int[] defences;

	protected int getHorizontalSteps() {
		return horizontalSteps;
	}

	protected void setHorizontalSteps(int horizontalSteps) {
		this.horizontalSteps = horizontalSteps;
	}

	protected int getVerticalSteps() {
		return verticalSteps;
	}

	protected void setVerticalSteps(int verticalSteps) {
		this.verticalSteps = verticalSteps;
	}

	protected int getDiagonalSteps() {
		return diagonalSteps;
	}

	protected void setDiagonalSteps(int diagonalSteps) {
		this.diagonalSteps = diagonalSteps;
	}

	protected int getHorizontalView() {
		return horizontalView;
	}

	protected void setHorizontalView(int horizontalView) {
		this.horizontalView = horizontalView;
	}

	protected int getVerticalView() {
		return verticalView;
	}

	protected void setVerticalView(int verticalView) {
		this.verticalView = verticalView;
	}

	protected int getDiagonalView() {
		return diagonalView;
	}

	protected void setDiagonalView(int diagonalView) {
		this.diagonalView = diagonalView;
	}

	protected int getGroundAuthority() {
		return groundAuthority;
	}

	protected void setGroundAuthority(int groundAuthority) {
		this.groundAuthority = groundAuthority;
	}

	protected int[] getAttacks() {
		return attacks;
	}

	protected void setAttacks(int[] attacks) {
		this.attacks = attacks;
	}

	protected int[] getDefences() {
		return defences;
	}

	protected void setDefences(int[] defences) {
		this.defences = defences;
	}

//	/**
//	 * @param aKind
//	 *
//	 * Constructor of any kind of figure
//	 * @throws NoFigureException
//	 */
//	public Figure(String aKind) {
//		kind = aKind;
//
//		acceptedGround = null;
//		attack.add(Constants.FIST);
//		attack.add(Constants.KICK);
//		defence.add(Constants.FIST);
//		defence.add(Constants.KICK);
//		visible = false;
//		trapped = false;
//
//		if (aKind.equals(Constants.FIGHTER)) {
//			steps = 2;
//			view = 1;
//
//			attack.add(Constants.KNIFE);
//			attack.add(Constants.RIFLE);
//
//			defence.add(Constants.KNIFE);
//			defence.add(Constants.RIFLE);
//		} else if (aKind.equals(Constants.SPY)) {
//			steps = 3;
//			view = 2;
//		} else if (aKind.equals(Constants.DIVER)) {
//			steps = 1;
//			view = 1;
//			acceptedGround = Constants.WATER;
//
//			attack.add(Constants.KNIFE);
//
//			defence.add(Constants.KNIFE);
//			defence.add(Constants.RIFLE);
//		} else if (aKind.equals(Constants.MEDIC)) {
//			steps = 1;
//			view = 1;
//
//			attack.add(Constants.KNIFE);
//
//			defence.add(Constants.KNIFE);
//		} else if (aKind.equals(Constants.CLIMBER)) {
//			steps = 1;
//			view = 1;
//
//			acceptedGround = Constants.MOUNTAIN;
//
//			attack.add(Constants.KNIFE);
//
//			defence.add(Constants.KNIFE);
//			defence.add(Constants.RIFLE);
//		} else if (aKind.equals(Constants.BOMBER)) {
//			steps = 1;
//			view = 1;
//
//			attack.add(Constants.KNIFE);
//
//			defence.add(Constants.KNIFE);
//			defence.add(Constants.RIFLE);
//		} else {
//			Exception e = new Exception("Unknown FigureType");
//			Logger.getAnonymousLogger().log(Level.SEVERE, e.getMessage());
//		}
//	}
}
