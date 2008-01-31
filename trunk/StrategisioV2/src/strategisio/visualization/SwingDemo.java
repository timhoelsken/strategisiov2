package strategisio.visualization;

import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * 
 * @author Tim
 *
 */
public class SwingDemo extends JPanel{

  /**
   * 
   */
  private static final long serialVersionUID = 1L;

  protected void paintComponent( Graphics g){
    Field.drawField(g, getWidth() / 2, getHeight() / 2, 50, 6, true);
    Field.drawField(g, getWidth() / 2, getHeight() / 2, 60, 6, true);
  }
  
  /**
   * 
   * @param args
   */
  public static void main(String[] args) {
    JFrame f = new JFrame();
    f.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE);
    f.add(new SwingDemo());
    f.setSize(200, 200);
    f.setVisible(true);
  }
}
