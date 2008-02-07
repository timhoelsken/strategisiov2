package strategisio.visualization;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.io.File;

import javax.swing.JComponent;

/**
 * 
 * @author Tim
 *
 */
public class Field extends JComponent{

private Image anImage;
private int anX, aY;

/**
 * 
 * @param aFile
 * @param anX
 * @param aY
 */
public Field(File aFile, int anX, int aY){
  setImage(aFile, anX, aY);
  setCoordinates(anX, aY);
}
  /**
   * 
   */
  private static final long serialVersionUID = 1L;
  
  /**
   * @param aFile
   * @param anX
   * @param aY
   */
  public void setImage(File aFile, int anX, int aY){
    anImage = Toolkit.getDefaultToolkit().getImage( aFile.getAbsolutePath());
    if (anImage != null){
      repaint();
    }
    
  }
  protected void paintComponent( Graphics g){
    if (anImage != null){
      g.drawImage(anImage, anX, aY, this);
    }
  }
  
  private void setCoordinates(int anX, int aY){
    this.anX = anX;
    this.aY = aY;
  }
}
