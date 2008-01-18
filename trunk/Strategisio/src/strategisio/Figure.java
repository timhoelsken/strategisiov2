package strategisio;

import java.util.Collection;

/**
 * 
 * @author Tim
 * 
 * This class defines a figure
 */
public class Figure {

	private String kind;

	private int steps;

	private int view;

	private String acceptedGround;

	private Collection attack;

	private Collection defence;

	private boolean visible;

	private boolean trapped;

	/**
	 * @author Tim
	 * @param aKind
	 * 
	 * Constructor of any kind of figure
	 */
	public Figure(String aKind) {
		this.kind = aKind;

		this.acceptedGround = null;
		this.attack.add("fist");
		this.attack.add("kick");
		this.defence.add("fist");
		this.defence.add("kick");
		this.visible = false;
		this.trapped = false;

		if (aKind.equals("fighter")) {
			this.steps = 2;
			this.view = 1;

			this.attack.add("knife");
			this.attack.add("rifle");

			this.defence.add("knife");
			this.defence.add("rifle");
		} else if (aKind.equals("spy")) {
			this.steps = 3;
			this.view = 2;
		} else if (aKind.equals("diver")) {
			this.steps = 1;
			this.view = 1;
			this.acceptedGround = "water";

			this.attack.add("knife");

			this.defence.add("knife");
			this.defence.add("rifle");
		} else if (aKind.equals("medic")) {
			this.steps = 1;
			this.view = 1;

			this.attack.add("knife");

			this.defence.add("knife");
		} else if (aKind.equals("climber")) {
			this.steps = 1;
			this.view = 1;

			this.acceptedGround = "mountain";

			this.attack.add("knife");

			this.defence.add("knife");
			this.defence.add("rifle");
		} else if (aKind.equals("bomber")) {
			this.steps = 1;
			this.view = 1;

			this.attack.add("knife");

			this.defence.add("knife");
			this.defence.add("rifle");
		}
	}

	public String getAcceptedGround() {
		return acceptedGround;
	}

	public void setAcceptedGround(String anAcceptedGround) {
		acceptedGround = anAcceptedGround;
	}

	public Collection getAttack() {
		return attack;
	}

	public void setAttack(Collection anAttack) {
		attack = anAttack;
	}

	public Collection getDefence() {
		return defence;
	}

	public void setDefence(Collection aDefence) {
		defence = aDefence;
	}

	public String getKind() {
		return kind;
	}

	public void setKind(String aKind) {
		kind = aKind;
	}

	public int getSteps() {
		return steps;
	}

	public void setSteps(int aStepNumber) {
		steps = aStepNumber;
	}

	public boolean isTrapped() {
		return trapped;
	}

	public void setTrapped(boolean isTrapped) {
		trapped = isTrapped;
	}

	public int getView() {
		return view;
	}

	public void setView(int aView) {
		view = aView;
	}

	public boolean isVisible() {
		return visible;
	}

	public void setVisible(boolean isVisible) {
		visible = isVisible;
	}
}
