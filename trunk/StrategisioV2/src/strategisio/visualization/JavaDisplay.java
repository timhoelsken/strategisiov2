package strategisio.visualization;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.io.File;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JTextField;
import javax.swing.KeyStroke;

/**
 * @author Tim
 * 
 */
public class JavaDisplay extends JFrame implements ActionListener {

  /**
   * 
   */
  private static final long serialVersionUID = 1L;

  /**
   * 
   */
  public JavaDisplay() {
    JMenuBar tmpMenuBar = new JMenuBar();
    JMenu tmpMenu = new JMenu("Datei");
    JMenuItem tmpItem = new JMenuItem("Beenden");
    tmpItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_0, InputEvent.CTRL_MASK));
    tmpItem.addActionListener(this);
    tmpMenu.add(tmpItem);
    tmpMenuBar.add(tmpMenu);
    setJMenuBar(tmpMenuBar);
    String tmpGroundPicture = "resources/pictures/grass.jpg";
    /*Field[] tmpFieldArray = new Field[225];
    int k = 0;
    for (int j = 32; j < 320; j += 32) {
      for (int i = 32; i < 320; i += 32) {
        tmpFieldArray[k] = new Field(tmpFile, i, j);
        add(tmpFieldArray[k]);
        k++;
      }
    }*/
    setTitle("Strategisio");
    setSize(800, 600);
    setLayout(new GridLayout(12, 12));
    ImageIcon tmpIcon = new ImageIcon(tmpGroundPicture);
    JButton tmpButton = new JButton(tmpIcon);
    tmpButton.setSize(32, 32);
    Dimension tmpDimension = new Dimension();
    tmpDimension.setSize(32, 32);
    tmpButton.setPreferredSize(tmpDimension);
    add(tmpButton);
    //add(new Field(tmpGroundPicture, 32, 32));
    //add(new Field(tmpGroundPicture, 32, 32));
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  }

  /**
   * 
   * @param args
   */
  public static void main(String[] args) {

    new JavaDisplay().setVisible(true);
  }

  /*
   * (non-Javadoc)
   * 
   * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
   */
  @Override
  /**
   * 
   */
  public void actionPerformed(ActionEvent aArg0) {

  }
}
