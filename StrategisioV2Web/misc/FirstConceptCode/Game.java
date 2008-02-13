package old;

import java.io.File;

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
			if (map.importMap(new File(""))) {
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
		Game tmpGame = new Game();
		tmpGame.init();
	}

	/**
	 * @author Tim
	 * @param aFigure
	 * @return figure
	 * 
	 * Removes a figure from the collection in the Panel. Supposed to be used in
	 * the beginnig of the game.
	 */
	public Figure getFigureFromPanel(String aFigure) {
		Figure tmpFigure = panel.removeFigure(aFigure);
		return tmpFigure;
	}

	/**
	 * @author Tim
	 * @param aFigure
	 * @param aSingleFigure
	 * @return boolean
	 * 
	 * Inserts a figure to the panel. This should be used when the user did not
	 * want to place the figure on the map he had selected with
	 * getFigureFromPanel.
	 */
	public boolean setFigureToPanel(String aFigure, Figure aSingleFigure) {
		return panel.insertFigure(aFigure, aSingleFigure);
	}

	/**
	 * @author Tim
	 * @param anX
	 * @param aY
	 * @return figure
	 * 
	 * Removes a figure from a field on the map
	 */
	public Figure getFigureFromMap(int anX, int aY) {
		return map.removeFigure(anX, aY);
	}

	/**
	 * @author Tim
	 * @param anX
	 * @param aY
	 * @param aFigure
	 * @return boolean
	 * 
	 * Places a figure on a field on the map
	 */
	public boolean setFigureToMap(int anX, int aY, Figure aFigure) {
		return map.insertFigure(anX, aY, aFigure);
	}

	/**
	 * @author Tim
	 * @param anItem
	 * @return Item
	 * 
	 * Removes an item from the collection in the Panel. Supposed to be used in
	 * the beginnig of the game.
	 */
	public Item getItemFromPanel(String anItem) {
		Item tmpItem = panel.removeItem(anItem);
		return tmpItem;
	}

	/**
	 * @author Tim
	 * @param anItem
	 * @param aSingleItem
	 * @return boolean
	 * 
	 * Inserts an item to the panel. This should be used when the user did not
	 * want to place the item on the map he had selected with getItemFromPanel.
	 */
	public boolean setItemToPanel(String anItem, Item aSingleItem) {
		return panel.insertItem(anItem, aSingleItem);
	}

	/**
	 * @author Tim
	 * @param anX
	 * @param aY
	 * @return Item
	 * 
	 * Removes an item from a field on the map
	 */
	public Item getItemFromMap(int anX, int aY) {
		return map.removeItem(anX, aY);
	}

	/**
	 * @author Tim
	 * @param anX
	 * @param aY
	 * @param anItem
	 * @return boolean
	 * 
	 * Places an item on a field on the map
	 */
	public boolean setItemToMap(int anX, int aY, Item anItem) {
		return map.insertItem(anX, aY, anItem);
	}

	/**
	 * @author Tim
	 * @return boolean
	 * 
	 * starts a combat.... if implemented
	 */
	public boolean doCombat() {
		return combat.init();
	}
}
