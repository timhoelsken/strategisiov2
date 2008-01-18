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
 * This class defines the map, which consists of an two-dimensional array of
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
	 * @param anX
	 * @param aY
	 * @return
	 * 
	 * With the help of an unique fieldID, a single field is repainted
	 */
	public boolean rePaintField(int anX, int aY) {
		return true;
	}

	/**
	 * @author Tim
	 * @param anX
	 * @param aY
	 * @param aField
	 * 
	 * Sets a single field on the map
	 */
	public void setField(int anX, int aY, Field aField) {
		fieldProperties[anX][aY] = aField;
	}

	/**
	 * @author Tim
	 * @param anX
	 * @param anY
	 * @return field
	 * 
	 * Gets a single field from the map
	 */
	public Field getField(int anX, int anY) {
		return fieldProperties[anX][anY];
	}

	/**
	 * @author Tim
	 * @param anX
	 * @param aY
	 * @return String
	 * 
	 * Returns the ground of a single field
	 */
	public String getSingleFieldGround(int anX, int aY) {

		return fieldProperties[anX][aY].getGround();
	}

	/**
	 * @author Tim
	 * @param aField
	 * @param aFigure
	 * @return boolean
	 * 
	 * Returns true if a field has no figure
	 */
	public boolean emptyFieldForFigure(Field aField, Figure aFigure) {
		if (aField.hasFigure()
				|| aField.hasItem()
				|| ((!aField.getGround().equals(aFigure.getAcceptedGround()) && !aField
						.getGround().equals("gras")))) {
			return false;
		} else {
			return true;
		}
	}

	/**
	 * @author Tim
	 * @param aField
	 * @param aFigure
	 * @return boolean
	 * 
	 * Returns true if a field has no item
	 */
	public boolean moveFigure(Field aField, Figure aFigure) {
		if (emptyFieldForFigure(aField, aFigure)) {
			aField.setFigure(aFigure);
			return true;
		}
		return false;
	}

	/**
	 * @author Tim
	 * @param anX
	 * @param aY
	 * @return Figure
	 * 
	 * Removes a figure from a field
	 */
	public Figure removeFigure(int anX, int aY) {
		Field tmpField = getField(anX, aY);
		Figure tmpFigure = tmpField.getFigure();
		tmpField.setFigure(null);
		return tmpFigure;
	}

	/**
	 * @author Tim
	 * @param anX
	 * @param aY
	 * @param aFigure
	 * @return boolean
	 * 
	 * Inserts a figure to a field
	 */
	public boolean insertFigure(int anX, int aY, Figure aFigure) {
		Field tmpField = getField(anX, aY);
		tmpField.setFigure(aFigure);
		return true;
	}

	/**
	 * @author Tim
	 * @param anX
	 * @param aY
	 * @return Item
	 * 
	 * Removes a figure form a field
	 */
	public Item removeItem(int anX, int aY) {
		Field tmpField = getField(anX, aY);
		Item tmpItem = tmpField.getItem();
		return tmpItem;
	}

	/**
	 * @author Tim
	 * @param anX
	 * @param aY
	 * @param anItem
	 * @return boolean
	 * 
	 * Inserts an item to a field
	 */
	public boolean insertItem(int anX, int aY, Item anItem) {
		Field tmpField = getField(anX, aY);
		tmpField.setItem(anItem);
		return true;
	}

	/**
	 * @author Tim
	 * @param aFile
	 * @return w3c Document
	 * 
	 * Reads an XML file into a w3c Document
	 */
	public Document getDocumentFrom(File aFile) {
		DocumentBuilderFactory tmpFactory = DocumentBuilderFactory
				.newInstance();
		Document tmpDocument = null;

		try {
			DocumentBuilder tmpBuilder = tmpFactory.newDocumentBuilder();
			tmpDocument = tmpBuilder.parse(aFile);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return tmpDocument;
	}

	/**
	 * @author Tim
	 * @param aFile
	 * @return
	 * 
	 * Imports map data from XML to field collection
	 */
	public boolean importMap(File aFile) {
		/*
		 * Read XML file from path, save data to field collection
		 * 
		 */

		Document tmpGameMapData = getDocumentFrom(aFile);
		NodeList tmpFieldList = tmpGameMapData.getElementsByTagName("field");
		Node tmpNode;
		NodeList tmpChilds;

		int x = 0;

		// GANZ DOLL BÄH !!!
		int z = (int) Math.sqrt((double) tmpFieldList.getLength());

		/*
		 * Structure in XML has to be the same as it is here. see mapdummy.xml
		 * x1.y1, x1.y2, x1.y3, x2.y1, x2.y2, etc.
		 */
		for (int y = 0; y < tmpFieldList.getLength(); y++) {
			tmpNode = tmpFieldList.item(y);
			tmpChilds = tmpNode.getChildNodes();

			if ((y % z == 0) && y > 0) {
				x += 1;
				fieldProperties[x][(y - (x * z))] = new Field(tmpChilds.item(0)
						.getTextContent(), new Figure(tmpChilds.item(1)
						.getTextContent()), new Item(tmpChilds.item(2)
						.getTextContent()));
			} else {
				fieldProperties[x][(y - (x * z))] = new Field(tmpChilds.item(0)
						.getTextContent(), new Figure(tmpChilds.item(1)
						.getTextContent()), new Item(tmpChilds.item(2)
						.getTextContent()));
			}
		}

		return true;
	}

}
