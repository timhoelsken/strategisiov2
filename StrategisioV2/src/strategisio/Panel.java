package strategisio;

/**
 * 
 * @author Tim
 * 
 * This class defines a panel wich has several arrays of figures and items
 */
public class Panel {

	Figure[] fighter = new Figure[2];

	Figure[] spy = new Figure[2];

	Figure[] medic = new Figure[2];

	Figure[] diver = new Figure[2];

	Figure[] bomber = new Figure[2];

	Figure[] climber = new Figure[2];

	Item[] bomb = new Item[2];

	Item[] trap = new Item[2];

	Item[] fakeflag = new Item[2];

	Item[] flag = new Item[1];

	/**
	 * @author Tim
	 * 
	 * Constructor of a panel
	 */
	public Panel() {
		for (int i = 0; i < fighter.length; i++) {
			fighter[i] = new Figure("fighter");
		}
		for (int i = 0; i < spy.length; i++) {
			spy[i] = new Figure("spy");
		}
		for (int i = 0; i < medic.length; i++) {
			medic[i] = new Figure("medic");
		}
		for (int i = 0; i < diver.length; i++) {
			diver[i] = new Figure("diver");
		}
		for (int i = 0; i < bomber.length; i++) {
			bomber[i] = new Figure("bomber");
		}
		for (int i = 0; i < climber.length; i++) {
			climber[i] = new Figure("climber");
		}

		for (int i = 0; i < bomb.length; i++) {
			bomb[i] = new Item("bomb");
		}
		for (int i = 0; i < trap.length; i++) {
			trap[i] = new Item("trap");
		}
		for (int i = 0; i < fakeflag.length; i++) {
			fakeflag[i] = new Item("fakeflag");
		}
		for (int i = 0; i < flag.length; i++) {
			flag[i] = new Item("flag");
		}
	}

	/**
	 * @author Tim
	 * @param aFigureArray
	 * @return int
	 * 
	 * Returns the position of the next available figure, returns -1 if no
	 * figure left
	 */
	private int figureLeft(Figure[] aFigureArray) {

		for (int i = 0; i < aFigureArray.length; i++) {
			if (!aFigureArray[i].equals(null))
				return i;
		}
		return -1;
	}

	/**
	 * @author Tim
	 * @param anItemArray
	 * @return int
	 * 
	 * Returns the position of the next available item, returns -1 if no item
	 * left
	 */
	private int itemLeft(Item[] anItemArray) {

		for (int i = 0; i < anItemArray.length; i++) {
			if (!anItemArray[i].equals(null))
				return i;
		}
		return -1;
	}

	/**
	 * @author Tim
	 * @param anItemArray
	 * @return int
	 * 
	 * Returns the position of the next free space in item[] returns -1 if array
	 * is full
	 */
	private int itemSpace(Item[] anItemArray) {
		for (int i = 0; i < anItemArray.length; i++) {
			if (anItemArray[i].equals(null))
				return i;
		}
		return -1;
	}

	/**
	 * @author Tim
	 * @param anFigureArray
	 * @return int
	 * 
	 * Returns the position of the next free space in figure[] returns -1 if
	 * array is full
	 */
	private int figureSpace(Figure[] anFigureArray) {
		for (int i = 0; i < anFigureArray.length; i++) {
			if (anFigureArray[i].equals(null))
				return i;
		}
		return -1;
	}

	/**
	 * @author Tim
	 * @param figure
	 * @return boolean
	 * 
	 * Removes a figure from the array of the panel Returnes true if it was
	 * possible to remove a figure
	 */
	public Figure removeFigure(String aFigure) {
		Figure[] tmpFigure = convertStringToFigure(aFigure);
		Figure tmpSingleFigure;
		int tmpPosition = figureLeft(tmpFigure);
		if (tmpPosition != -1) {
			tmpSingleFigure = tmpFigure[tmpPosition];
			tmpFigure[tmpPosition] = null;
			return tmpSingleFigure;
		}
		return null;
	}

	/**
	 * @author Tim
	 * @param item
	 * @return boolean
	 * 
	 * Removes an item from the array of the panel Returnes true if it was
	 * possible to remove a figure
	 */
	public Item removeItem(String anItem) {
		Item[] tmpItem = convertStringToItem(anItem);
		Item tmpSingleItem;
		int tmpPosition = itemLeft(tmpItem);
		if (tmpPosition != -1) {
			tmpSingleItem = tmpItem[tmpPosition];
			tmpItem[tmpPosition] = null;
			return tmpSingleItem;
		}
		return null;
	}

	/**
	 * @author Tim
	 * @param anItem
	 * @param aSingleItem
	 * @return boolean
	 * 
	 * Inserts an item to the panel. This should be used when the user did not
	 * want to place the item on the map
	 * 
	 */
	public boolean insertItem(String anItem, Item aSingleItem) {
		Item[] tmpItem = convertStringToItem(anItem);
		int tmpPosition = itemSpace(tmpItem);
		if (tmpPosition != -1) {
			tmpItem[tmpPosition] = aSingleItem;
			return true;
		}
		return false;
	}

	/**
	 * @author Tim
	 * @param aFigure
	 * @param aSingleFigure
	 * @return boolean
	 * 
	 * Inserts a figure to the panel. This should be used when the user did not
	 * want to place the figure on the map
	 */
	public boolean insertFigure(String aFigure, Figure aSingleFigure) {
		Figure[] tmpFigure = convertStringToFigure(aFigure);
		int tmpPosition = figureSpace(tmpFigure);
		if (tmpPosition != -1) {
			tmpFigure[tmpPosition] = aSingleFigure;
			return true;
		}
		return false;
	}

	/**
	 * @author Tim
	 * @param anItem
	 * @return Item[]
	 * 
	 * Converts an itemString to an Item[], so that Item[] remains private
	 */
	private Item[] convertStringToItem(String anItem) {
		Item[] tmpItem;
		if (anItem.equals("trap")) {
			tmpItem = trap;
		} else if (anItem.equals("bomb")) {
			tmpItem = bomb;
		} else if (anItem.equals("fakeflag")) {
			tmpItem = fakeflag;
		} else {
			tmpItem = flag;
		}
		return tmpItem;
	}

	/**
	 * @author Tim
	 * @param aFigure
	 * @return Figure[]
	 * 
	 * Converts an figureString to an Figure[], so that Figure[] remains private
	 */
	private Figure[] convertStringToFigure(String aFigure) {
		Figure[] tmpFigure;
		if (aFigure.equals("fighter")) {
			tmpFigure = fighter;
		} else if (aFigure.equals("spy")) {
			tmpFigure = spy;
		} else if (aFigure.equals("medic")) {
			tmpFigure = medic;
		} else if (aFigure.equals("diver")) {
			tmpFigure = diver;
		} else if (aFigure.equals("bomber")) {
			tmpFigure = bomber;
		} else {
			tmpFigure = climber;
		}
		return tmpFigure;
	}

	public String paint() {
		return "";
	}
}
