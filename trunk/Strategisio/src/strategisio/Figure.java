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
	 * @param kind
	 * 
	 * Constructor of any kind of figure
	 */
	public Figure(String kind) {
		this.kind = kind;

		this.acceptedGround = null;
		this.attack.add("fist");
		this.attack.add("kick");
		this.defence.add("fist");
		this.defence.add("kick");
		this.visible = false;
		this.trapped = false;

		if (kind.equals("fighter")) {
			this.steps = 2;
			this.view = 1;

			this.attack.add("knife");
			this.attack.add("rifle");

			this.defence.add("knife");
			this.defence.add("rifle");
		} else if (kind.equals("spy")) {
			this.steps = 3;
			this.view = 2;
		} else if (kind.equals("diver")) {
			this.steps = 1;
			this.view = 1;
			this.acceptedGround = "water";

			this.attack.add("knife");

			this.defence.add("knife");
			this.defence.add("rifle");
		} else if (kind.equals("medic")) {
			this.steps = 1;
			this.view = 1;

			this.attack.add("knife");

			this.defence.add("knife");
		} else if (kind.equals("climber")) {
			this.steps = 1;
			this.view = 1;

			this.acceptedGround = "mountain";

			this.attack.add("knife");

			this.defence.add("knife");
			this.defence.add("rifle");
		} else if (kind.equals("bomber")) {
			this.steps = 1;
			this.view = 1;

			this.attack.add("knife");

			this.defence.add("knife");
			this.defence.add("rifle");
		}
	}

	public String getKind() {
		return kind;
	}

	public void setKind(String kind) {
		this.kind = kind;
	}

	public String getAcceptedGround() {
		return acceptedGround;
	}

	public void setAcceptedGround(String acceptedGround) {
		this.acceptedGround = acceptedGround;
	}

	public Collection getAttack() {
		return attack;
	}

	public void setAttack(Collection attack) {
		this.attack = attack;
	}

	public Collection getDefence() {
		return defence;
	}

	public void setDefence(Collection defence) {
		this.defence = defence;
	}

	public int getSteps() {
		return steps;
	}

	public void setSteps(int steps) {
		this.steps = steps;
	}

	public boolean isTrapped() {
		return trapped;
	}

	public void setTrapped(boolean trapped) {
		this.trapped = trapped;
	}

	public int getView() {
		return view;
	}

	public void setView(int view) {
		this.view = view;
	}

	public boolean isVisible() {
		return visible;
	}

	public void setVisible(boolean visible) {
		this.visible = visible;
	}
}
