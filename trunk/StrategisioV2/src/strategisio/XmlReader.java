package strategisio;

import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;

/**
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

  // TODO Create DTD and a correct map.xml

  /**
   * 
   * @param aFile
   * @return
   */
  public int[][] getMapdata(File aFile) {
    // int[][] can be used with playmap.setfieldground
    int[][] tmpMapData = new int[1][1]; // has to be set dynamic!!! just to
    // avoid error

    // TODO Read XML File

    return tmpMapData;
  }

  /**
   * 
   * @param aFile
   * @return
   */
  public int[] getTeamdata(File aFile) {
    int[] tmpTeamData = new int[9];
    /*
     * 0 = Fighters 1 = Climbers 2 = Divers 3 = Medics 4 = Spys 5 = Miners 6 =
     * FakeFlags 7 = Traps 8 = Bombs
     * 
     * A Flag is not read via XML (in this version)
     */

    Document tmpDocument = getDocumentFrom(aFile);

    // TODO Read XML File
    tmpDocument.normalizeDocument(); // to avoid warning
    return tmpTeamData;
  }
}
