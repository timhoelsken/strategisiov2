package old;

import java.util.Collection;
import java.util.logging.Level;
import java.util.logging.Logger;

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

	private Collection<String> attack;

	private Collection<String> defence;

	private boolean visible;

	private boolean trapped;

	/**
	 * @author Tim
	 * @param aKind
	 * 
	 * Constructor of any kind of figure
	 * @throws NoFigureException
	 */
	public Figure(String aKind) {
		kind = aKind;

		acceptedGround = null;
		attack.add(Constants.FIST);
		attack.add(Constants.KICK);
		defence.add(Constants.FIST);
		defence.add(Constants.KICK);
		visible = false;
		trapped = false;

		if (aKind.equals(Constants.FIGHTER)) {
			steps = 2;
			view = 1;

			attack.add(Constants.KNIFE);
			attack.add(Constants.RIFLE);

			defence.add(Constants.KNIFE);
			defence.add(Constants.RIFLE);
		} else if (aKind.equals(Constants.SPY)) {
			steps = 3;
			view = 2;
		} else if (aKind.equals(Constants.DIVER)) {
			steps = 1;
			view = 1;
			acceptedGround = Constants.WATER;

			attack.add(Constants.KNIFE);

			defence.add(Constants.KNIFE);
			defence.add(Constants.RIFLE);
		} else if (aKind.equals(Constants.MEDIC)) {
			steps = 1;
			view = 1;

			attack.add(Constants.KNIFE);

			defence.add(Constants.KNIFE);
		} else if (aKind.equals(Constants.CLIMBER)) {
			steps = 1;
			view = 1;

			acceptedGround = Constants.MOUNTAIN;

			attack.add(Constants.KNIFE);

			defence.add(Constants.KNIFE);
			defence.add(Constants.RIFLE);
		} else if (aKind.equals(Constants.BOMBER)) {
			steps = 1;
			view = 1;

			attack.add(Constants.KNIFE);

			defence.add(Constants.KNIFE);
			defence.add(Constants.RIFLE);
		} else {
			Exception e = new Exception("Unknown FigureType");
			Logger.getAnonymousLogger().log(Level.SEVERE, e.getMessage());
		}
	}

	public String getAcceptedGround() {
		return acceptedGround;
	}

	public void setAcceptedGround(String anAcceptedGround) {
		acceptedGround = anAcceptedGround;
	}

	/**
	 * @return the attack
	 */
	public Collection<String> getAttack() {
		return attack;
	}

	/**
	 * @param aAttack
	 *            the attack to set
	 */
	public void setAttack(Collection<String> aAttack) {
		attack = aAttack;
	}

	/**
	 * @return the defence
	 */
	public Collection<String> getDefence() {
		return defence;
	}

	/**
	 * @param aDefence
	 *            the defence to set
	 */
	public void setDefence(Collection<String> aDefence) {
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
