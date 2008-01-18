package strategisio;

/**
 * 
 * @author Tim
 * 
 * This class defines a single Field on the Map
 * 
 */
public class Field {

	private String ground;

	private Figure figure;

	private Item item;

	/**
	 * @author Tim
	 * @param xCoordinate
	 * @param yCoordinate
	 * @param ground
	 * @param figure
	 * @param item
	 * 
	 * Constructor for a Field on the map
	 */

	public Field(String ground, Figure figure, Item item) {
		setGround(ground);
		setFigure(figure);
		setItem(item);
	}

	public Figure getFigure() {
		return figure;
	}

	public void setFigure(Figure figure) {
		this.figure = figure;
	}

	public String getGround() {
		return ground;
	}

	public void setGround(String ground) {
		this.ground = ground;
	}

	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
	}

	/**
	 * @author Tim
	 * @return boolean
	 * 
	 * returns true if a figure is set on the field
	 */
	public boolean hasFigure() {
		if (this.figure != null) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * @author Tim
	 * @return boolean
	 * 
	 * returns true if a item is set on the field
	 */
	public boolean hasItem() {
		if (this.item != null) {
			return true;
		} else {
			return false;
		}
	}
}
