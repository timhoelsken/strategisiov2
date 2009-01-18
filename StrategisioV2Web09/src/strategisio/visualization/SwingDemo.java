package strategisio.visualization;

import java.awt.Color;
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

  /**
   * @see javax.swing.JComponent#paintComponent(java.awt.Graphics)
   */
  @Override
  protected void paintComponent( Graphics g){
    //Field.drawField(g, getWidth() / 2, getHeight() / 2, 50, 6, true);
    //Field.drawField(g, getWidth() / 2, getHeight() / 2, 60, 6, true);
    Color tmpGrassColor = new Color(255,0,0);
    g.drawRect(10,10,480,480);
    g.setColor(tmpGrassColor);
    for (int j = 10; j<480; j+=40){
      for (int i = 10; i < 480; i+=40){
        g.fillRect(i, j, 40, 40);
          }
    }
  }

  /**
   *
   * @param args
   */
  public static void main(String[] args) {
    JFrame f = new JFrame();
    f.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE);
    f.add(new SwingDemo());
    f.setSize(800, 600);
    f.setVisible(true);
  }
}
