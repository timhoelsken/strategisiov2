package strategisio.visualization;

import java.awt.Graphics;
import java.awt.Polygon;

/**
 * 
 * @author Tim
 *
 */
public class Field {

  /**
   * 
   */
  private static Polygon p = new Polygon();
  
  /**
   * 
   * @param g
   * @param x
   * @param y
   * @param r
   * @param n
   * @param filled
   */
  public static synchronized void drawField( Graphics g, int x, int y, int r, int n, boolean filled){
    p.reset();
    for (int i = 0; i < n; i++){
      p.addPoint((int) (x+r * Math.cos( i* 2 * Math.PI / n)),
          (int) (x+r * Math.sin( i* 2 * Math.PI / n)));
      
      if (filled){
        g.fillPolygon(p);
      }
      else{
        g.drawPolygon(p);
      }
    }
    
  }
}
