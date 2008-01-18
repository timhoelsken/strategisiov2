package strategisio;

/**
 * 
 * @author Tim
 * 
 * This class is the game and it consists of a map,a panel and the possibility
 * of a combat
 */
public class Game {

	private Map map;

	private Panel panel;

	private Combat combat;

	/**
	 * @author Tim
	 * @return boolean
	 * 
	 * Initprocess of a game.
	 */
	public boolean init() {
		try {
			if (map.importMap("")) {
				map.paint();
			}
			panel.paint();

		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	public static void main(String[] args) {
		Game game = new Game();
		game.init();
	}

	/**
	 * @author Tim
	 * @param figureString
	 * @return figure
	 * 
	 * Removes a figure from the collection in the Panel. Supposed to be used in
	 * the beginnig of the game.
	 */
	public Figure getFigureFromPanel(String figureString) {
		Figure figure = panel.removeFigure(figureString);
		return figure;
	}

	/**
	 * @author Tim
	 * @param figureString
	 * @param singleFigure
	 * @return boolean
	 * 
	 * Inserts a figure to the panel. This should be used when the user did not
	 * want to place the figure on the map he had selected with
	 * getFigureFromPanel.
	 */
	public boolean setFigureToPanel(String figureString, Figure singleFigure) {
		return panel.insertFigure(figureString, singleFigure);
	}

	/**
	 * @author Tim
	 * @param x
	 * @param y
	 * @return figure
	 * 
	 * Removes a figure from a field on the map
	 */
	public Figure getFigureFromMap(int x, int y) {
		return map.removeFigure(x, y);
	}

	/**
	 * @author Tim
	 * @param x
	 * @param y
	 * @param figure
	 * @return boolean
	 * 
	 * Places a figure on a field on the map
	 */
	public boolean setFigureToMap(int x, int y, Figure figure) {
		return map.insertFigure(x, y, figure);
	}

	/**
	 * @author Tim
	 * @param itemString
	 * @return Item
	 * 
	 * Removes an item from the collection in the Panel. Supposed to be used in
	 * the beginnig of the game.
	 */
	public Item getItemFromPanel(String itemString) {
		Item item = panel.removeItem(itemString);
		return item;
	}

	/**
	 * @author Tim
	 * @param itemString
	 * @param singleItem
	 * @return boolean
	 * 
	 * Inserts an item to the panel. This should be used when the user did not
	 * want to place the item on the map he had selected with getItemFromPanel.
	 */
	public boolean setItemToPanel(String itemString, Item singleItem) {
		return panel.insertItem(itemString, singleItem);
	}

	/**
	 * @author Tim
	 * @param x
	 * @param y
	 * @return Item
	 * 
	 * Removes an item from a field on the map
	 */
	public Item getItemFromMap(int x, int y) {
		return map.removeItem(x, y);
	}

	/**
	 * @author Tim
	 * @param x
	 * @param y
	 * @param item
	 * @return boolean
	 * 
	 * Places an item on a field on the map
	 */
	public boolean setItemToMap(int x, int y, Item item) {
		return map.insertItem(x, y, item);
	}

	/**
	 * @author Tim
	 * @return boolean
	 * 
	 * starts a combat.... when implemented
	 */
	public boolean doCombat() {
		return combat.init();
	}
}
