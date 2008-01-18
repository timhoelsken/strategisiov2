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
	 * @param figure
	 * @return int
	 * 
	 * Returns the position of the next available figure, returns -1 if no
	 * figure left
	 */
	private int figureLeft(Figure[] figure) {

		for (int i = 0; i < figure.length; i++) {
			if (!figure[i].equals(null))
				return i;
		}
		return -1;
	}

	/**
	 * @author Tim
	 * @param item
	 * @return int
	 * 
	 * Returns the position of the next available item, returns -1 if no item
	 * left
	 */
	private int itemLeft(Item[] item) {

		for (int i = 0; i < item.length; i++) {
			if (!item[i].equals(null))
				return i;
		}
		return -1;
	}

	/**
	 * @author Tim
	 * @param item
	 * @return int
	 * 
	 * Returns the position of the next free space in item[] returns -1 if array
	 * is full
	 */
	private int itemSpace(Item[] item) {
		for (int i = 0; i < item.length; i++) {
			if (item[i].equals(null))
				return i;
		}
		return -1;
	}

	/**
	 * @author Tim
	 * @param figure
	 * @return int
	 * 
	 * Returns the position of the next free space in figure[] returns -1 if
	 * array is full
	 */
	private int figureSpace(Figure[] figure) {
		for (int i = 0; i < figure.length; i++) {
			if (figure[i].equals(null))
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
	public Figure removeFigure(String figureString) {
		Figure[] figure = convertStringToFigure(figureString);
		Figure singleFigure;
		int position = figureLeft(figure);
		if (position != -1) {
			singleFigure = figure[position];
			figure[position] = null;
			return singleFigure;
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
	public Item removeItem(String itemString) {
		Item[] item = convertStringToItem(itemString);
		Item singleItem;
		int position = itemLeft(item);
		if (position != -1) {
			singleItem = item[position];
			item[position] = null;
			return singleItem;
		}
		return null;
	}

	/**
	 * @author Tim
	 * @param itemString
	 * @param singleItem
	 * @return boolean
	 * 
	 * Inserts an item to the panel. This should be used when the user did not
	 * want to place the item on the map
	 * 
	 */
	public boolean insertItem(String itemString, Item singleItem) {
		Item[] item = convertStringToItem(itemString);
		int position = itemSpace(item);
		if (position != -1) {
			item[position] = singleItem;
			return true;
		}
		return false;
	}

	/**
	 * @author Tim
	 * @param figureString
	 * @param singleFigure
	 * @return boolean
	 * 
	 * Inserts a figure to the panel. This should be used when the user did not
	 * want to place the figure on the map
	 */
	public boolean insertFigure(String figureString, Figure singleFigure) {
		Figure[] figure = convertStringToFigure(figureString);
		int position = figureSpace(figure);
		if (position != -1) {
			figure[position] = singleFigure;
			return true;
		}
		return false;
	}

	/**
	 * @author Tim
	 * @param itemString
	 * @return Item[]
	 * 
	 * Converts an itemString to an Item[], so that Item[] remains private
	 */
	private Item[] convertStringToItem(String itemString) {
		Item[] item;
		if (itemString.equals("trap")) {
			item = trap;
		} else if (itemString.equals("bomb")) {
			item = bomb;
		} else if (itemString.equals("fakeflag")) {
			item = fakeflag;
		} else {
			item = flag;
		}
		return item;
	}

	/**
	 * @author Tim
	 * @param figureString
	 * @return Figure[]
	 * 
	 * Converts an figureString to an Figure[], so that Figure[] remains private
	 */
	private Figure[] convertStringToFigure(String figureString) {
		Figure[] figure;
		if (figureString.equals("fighter")) {
			figure = fighter;
		} else if (figureString.equals("spy")) {
			figure = spy;
		} else if (figureString.equals("medic")) {
			figure = medic;
		} else if (figureString.equals("diver")) {
			figure = diver;
		} else if (figureString.equals("bomber")) {
			figure = bomber;
		} else {
			figure = climber;
		}
		return figure;
	}

	public String paint() {
		return "";
	}
}
