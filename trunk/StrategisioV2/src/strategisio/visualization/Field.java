package strategisio.visualization;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.swing.ImageIcon;
import javax.swing.JButton;

/**
 * 
 * @author Tim
 *
 */
public class Field extends JButton{

private Image anImage;

/**
 * 
 * @param aGroundPicture
 * @param anX
 * @param aY
 */
public Field(String aGroundPicture, int anX, int aY){
  setIcon(new ImageIcon(aGroundPicture));
  setSize(anX, aY);
  //setImage(aGroundPicture, anX, aY);
  //setCoordinates(anX, aY);
}

  /**
   * 
   */
  private static final long serialVersionUID = 1L;
  
  /**
   * @param anImageString
   * @param anX
   * @param aY
   */
  public void setImage(String anImageString, int anX, int aY){
    anImage = Toolkit.getDefaultToolkit().getImage(anImageString);
    if (anImage != null){
      repaint();
    }
    
  }
  protected void paintComponent( Graphics g){
    if (anImage != null){
      //g.drawImage(anImage, anX, aY, this);
    }
  }
  
  
}
