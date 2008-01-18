package strategisio;

import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 * 
 * @author Tim
 * 
 * This Class defines the map, which consists of an two-dimensional array of
 * fields
 */
public class Map {

	private Field[][] fieldProperties;

	/**
	 * @author Tim
	 * @param
	 * @return HTML DIV including map
	 * 
	 * Paints the Map in HTML
	 */

	public String paint() {
		/*
		 * Erste Idee... kann man eigentlich knicken
		 * 
		 * Collection data = new Vector(); Vector field = new Vector(); String
		 * output = "<div class=\"map\"><table><tr>"; data =
		 * getFieldProperties(); int i;
		 * 
		 * Error, weil nicht korrekter Vector... in map ist Element1 (includes
		 * e1,e2,e3) bei field.add ist in field immernoch Element und nicht
		 * e1...
		 * 
		 * Abfrage für Zeilenumbruch fehlt noch
		 * 
		 * for (i = 0; i < data.size(); i++) { field.add(data.iterator());
		 * output += "<td class=\"" + field.elementAt(2) + "\" id=\"" +
		 * field.elementAt(0) + "_" + field.elementAt(1) + "\"></td>"; }
		 * output += "</tr></table></div>";
		 */
		return "";// output;

	};

	/**
	 * @author Tim
	 * @return
	 * 
	 * Repaints the whole map
	 */
	public boolean rePaintMap() {
		return true;
	};

	/**
	 * @author Tim
	 * @param x
	 * @param y
	 * @return
	 * 
	 * With the help of an unique fieldID, a single field is repainted
	 */
	public boolean rePaintField(int x, int y) {
		return true;
	}

	/**
	 * @author Tim
	 * @param x
	 * @param y
	 * @param field
	 * 
	 * Sets a single field on the map
	 */
	public void setField(int x, int y, Field field) {
		fieldProperties[x][y] = field;
	}

	/**
	 * @author Tim
	 * @param x
	 * @param y
	 * @return field
	 * 
	 * Gets a single field from the map
	 */
	public Field getField(int x, int y) {
		return fieldProperties[x][y];
	}

	/**
	 * @author Tim
	 * @param x
	 * @param y
	 * @return String
	 * 
	 * Returns the ground of a single field
	 */
	public String getSingleFieldGround(int x, int y) {

		return fieldProperties[x][y].getGround();
	}

	/**
	 * @author Tim
	 * @param field
	 * @param figure
	 * @return boolean
	 * 
	 * Returns true if a field has no figure
	 */
	public boolean emptyFieldForFigure(Field field, Figure figure) {
		if (field.hasFigure()
				|| field.hasItem()
				|| ((!field.getGround().equals(figure.getAcceptedGround()) && !field
						.getGround().equals("gras")))) {
			return false;
		} else {
			return true;
		}
	}

	/**
	 * @author Tim
	 * @param field
	 * @param figure
	 * @return boolean
	 * 
	 * Returns true if a field has no item
	 */
	public boolean moveFigure(Field field, Figure figure) {
		if (emptyFieldForFigure(field, figure)) {
			field.setFigure(figure);
			return true;
		}
		return false;
	}

	/**
	 * @author Tim
	 * @param x
	 * @param y
	 * @return Figure
	 * 
	 * Removes a figure from a field
	 */
	public Figure removeFigure(int x, int y) {
		Field field = getField(x, y);
		Figure figure = field.getFigure();
		field.setFigure(null);
		return figure;
	}

	/**
	 * @author Tim
	 * @param x
	 * @param y
	 * @param figure
	 * @return boolean
	 * 
	 * Inserts a figure to a field
	 */
	public boolean insertFigure(int x, int y, Figure figure) {
		Field field = getField(x, y);
		field.setFigure(figure);
		return true;
	}

	/**
	 * @author Tim
	 * @param x
	 * @param y
	 * @return Item
	 * 
	 * Removes a figure form a field
	 */
	public Item removeItem(int x, int y) {
		Field field = getField(x, y);
		Item item = field.getItem();
		return item;
	}

	/**
	 * @author Tim
	 * @param x
	 * @param y
	 * @param item
	 * @return boolean
	 * 
	 * Inserts an item to a field
	 */
	public boolean insertItem(int x, int y, Item item) {
		Field field = getField(x, y);
		field.setItem(item);
		return true;
	}

	/**
	 * @author Tim
	 * @param fileInput
	 * @return w3c Document
	 * 
	 * Reads an XML file into a w3c Document
	 */
	public Document getDocumentFrom(String fileInput) {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		Document tmpDocument = null;

		try {
			DocumentBuilder builder = factory.newDocumentBuilder();
			tmpDocument = builder.parse(new File(fileInput));
		} catch (Exception e) {
			e.printStackTrace();
		}

		return tmpDocument;
	}

	/**
	 * @author Tim
	 * @param path
	 * @return
	 * 
	 * Imports map data from XML to field collection
	 */
	public boolean importMap(String path) {
		/*
		 * Read XML file from path, save data to field collection
		 * 
		 */

		Document gameMapData = getDocumentFrom(path);
		NodeList fieldList = gameMapData.getElementsByTagName("field");
		Node tmpNode;
		NodeList childs;

		int x = 0;

		// GANZ DOLL BÄH !!!
		int z = (int) Math.sqrt((double) fieldList.getLength());

		/*
		 * Structure in XML has to be the same as it is here. see mapdummy.xml
		 * x1.y1, x1.y2, x1.y3, x2.y1, x2.y2, etc.
		 */
		for (int y = 0; y < fieldList.getLength(); y++) {
			tmpNode = fieldList.item(y);
			childs = tmpNode.getChildNodes();

			if ((y % z == 0) && y > 0) {
				x += 1;
				fieldProperties[x][(y - (x * z))] = new Field(childs.item(0)
						.getTextContent(), new Figure(childs.item(1)
						.getTextContent()), new Item(childs.item(2)
						.getTextContent()));
			} else {
				fieldProperties[x][(y - (x * z))] = new Field(childs.item(0)
						.getTextContent(), new Figure(childs.item(1)
						.getTextContent()), new Item(childs.item(2)
						.getTextContent()));
			}
		}

		return true;
	}

}
