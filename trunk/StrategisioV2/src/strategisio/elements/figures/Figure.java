package strategisio.elements.figures;

import strategisio.elements.PlayElement;


/**
 * Abstract class for all figures
 */
public abstract class Figure implements PlayElement {

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
}
