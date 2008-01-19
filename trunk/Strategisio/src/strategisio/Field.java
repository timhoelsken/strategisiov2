package strategisio;

/**
 * 
 * @author Tim
 * 
 * This class defines a single field on the map
 * 
 */
public class Field {

	private String ground;

	private Figure figure;

	private Item item;

	/**
	 * @author Tim
	 * @param aGround
	 * @param aFigure
	 * @param anItem
	 * 
	 * Constructor for a field on the map
	 */
	public Field(String aGround, Figure aFigure, Item anItem) {
		setGround(aGround);
		setFigure(aFigure);
		setItem(anItem);
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
	 * returns true if an item is set on the field
	 */
	public boolean hasItem() {
		if (this.item != null) {
			return true;
		} else {
			return false;
		}
	}

	public Figure getFigure() {
		return figure;
	}

	public void setFigure(Figure aFigure) {
		figure = aFigure;
	}

	public String getGround() {
		return ground;
	}

	public void setGround(String aGround) {
		ground = aGround;
	}

	public Item getItem() {
		return item;
	}

	public void setItem(Item aItem) {
		item = aItem;
	}
}
