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
}
