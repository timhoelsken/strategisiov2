package strategisio;

import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 * public class for reading map XML files
 * 
 * @author Tim
 * 
 */
public class XmlReader {

  /**
   * Reads an XML file into a w3c Document
   * 
   * @author Tim
   * @param aFile
   * @return w3c Document
   */
  public Document getDocumentFrom(File aFile) {
    DocumentBuilderFactory tmpFactory = DocumentBuilderFactory.newInstance();
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
   * 
   * used for creating the map with dimensions and ground
   * 
   * @param aFile
   * @return the mapgrounds in a int[][]
   */
  public int[][] getMapdata(File aFile) {

    Document tmpDocument = getDocumentFrom(aFile);

    NodeList tmpNodeListForDimension = tmpDocument.getElementsByTagName("Map");
    NodeList tmpNodeListForColumns = tmpDocument.getElementsByTagName("Column");
    NodeList tmpYCoordinates;

    Node tmpNode = tmpNodeListForDimension.item(0);
    Node tmpNodeCopy;

    NamedNodeMap tmpNamedNodeMap = tmpNode.getAttributes();

    tmpNode = tmpNamedNodeMap.getNamedItem("x");

    int tmpDimension = Integer.parseInt(tmpNode.getNodeValue());

    // int[][] can be used with playmap.setfieldground
    int[][] tmpMapData = new int[tmpDimension][tmpDimension];

    for (int x = 0; x < tmpNodeListForColumns.getLength(); x++) {
      tmpNode = tmpNodeListForColumns.item(x);
      tmpYCoordinates = tmpNode.getChildNodes();

      for (int y = 1; y < tmpYCoordinates.getLength(); y++) {
        tmpNode = tmpYCoordinates.item(y);

        if (tmpNode.getNodeName().equals("Field")) {
          tmpNodeCopy = tmpNode;
          tmpNamedNodeMap = tmpNode.getAttributes();
          tmpNode = tmpNamedNodeMap.getNamedItem("y");

          tmpMapData[x][Integer.parseInt(tmpNode.getNodeValue())] = Integer.parseInt(tmpNodeCopy
              .getTextContent());
        }

      }
    }

    return tmpMapData;
  }

  /**
   * 
   * used for creating the team with number of figures
   * 
   * @param aFile
   * @return an int[] containing 0 - 8, each with number of figures
   */
  public int[] getTeamdata(File aFile) {

    /*
     * 0 = Fighters 1 = Climbers 2 = Divers 3 = Medics 4 = Spys 5 = Miners 6 =
     * FakeFlags 7 = Traps 8 = Bombs
     * 
     * A Flag is not read via XML (in this version)
     */
    int[] tmpTeamData = new int[9];

    Document tmpDocument = getDocumentFrom(aFile);

    NodeList tmpNodeListForFigures = tmpDocument.getElementsByTagName("Figures");
    NodeList tmpNodeListForItems = tmpDocument.getElementsByTagName("Items");

    Node tmpFigureNode = tmpNodeListForFigures.item(0);
    Node tmpItemNode = tmpNodeListForItems.item(0);
    Node tmpNode;

    tmpNodeListForFigures = tmpFigureNode.getChildNodes();
    tmpNodeListForItems = tmpItemNode.getChildNodes();

    for (int i = 0; i < tmpNodeListForFigures.getLength(); i++) {
      tmpNode = tmpNodeListForFigures.item(i);

      if (!tmpNode.getNodeName().equals("#text")) {

        if (tmpNode.getNodeName().equals("Fighter")) {
          tmpTeamData[0] = Integer.parseInt(tmpNode.getTextContent());
        } else if (tmpNode.getNodeName().equals("Climber")) {
          tmpTeamData[1] = Integer.parseInt(tmpNode.getTextContent());
        } else if (tmpNode.getNodeName().equals("Diver")) {
          tmpTeamData[2] = Integer.parseInt(tmpNode.getTextContent());
        } else if (tmpNode.getNodeName().equals("Medic")) {
          tmpTeamData[3] = Integer.parseInt(tmpNode.getTextContent());
        } else if (tmpNode.getNodeName().equals("Spy")) {
          tmpTeamData[4] = Integer.parseInt(tmpNode.getTextContent());
        } else if (tmpNode.getNodeName().equals("Miner")) {
          tmpTeamData[5] = Integer.parseInt(tmpNode.getTextContent());
        }

      }
    }

    for (int i = 0; i < tmpNodeListForItems.getLength(); i++) {
      tmpNode = tmpNodeListForItems.item(i);

      if (!tmpNode.getNodeName().equals("#text")) {

        if (tmpNode.getNodeName().equals("FakeFlag")) {
          tmpTeamData[6] = Integer.parseInt(tmpNode.getTextContent());
        } else if (tmpNode.getNodeName().equals("Trap")) {
          tmpTeamData[7] = Integer.parseInt(tmpNode.getTextContent());
        } else if (tmpNode.getNodeName().equals("Bomb")) {
          tmpTeamData[8] = Integer.parseInt(tmpNode.getTextContent());
        }

      }
    }

    return tmpTeamData;
  }
}
