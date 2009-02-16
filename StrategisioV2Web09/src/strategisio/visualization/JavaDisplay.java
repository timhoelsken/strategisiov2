package strategisio.visualization;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
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

  private JMenuItem tmpItem;

  /**
   * @param args
   */
  public static void main(String[] args) {
    // JavaDisplay tmpDisplay = new JavaDisplay();
  }

  /**
   * 
   */
  public JavaDisplay() {
    JMenuBar tmpMenuBar = new JMenuBar();
    JMenu tmpMenu = new JMenu("Datei");
    tmpItem = new JMenuItem("Beenden");
    tmpItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_0, InputEvent.CTRL_MASK));
    MyActionListener tmpActionListener = new MyActionListener();
    tmpItem.addActionListener(tmpActionListener);
    tmpMenu.add(tmpItem);
    tmpMenuBar.add(tmpMenu);
    setJMenuBar(tmpMenuBar);
    String tmpGroundPicture = "resources/pictures/grass.jpg";
    /*
     * Field[] tmpFieldArray = new Field[225]; int k = 0; for (int j = 32; j <
     * 320; j += 32) { for (int i = 32; i < 320; i += 32) { tmpFieldArray[k] =
     * new Field(tmpFile, i, j); add(tmpFieldArray[k]); k++; } }
     */
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
    // add(new Field(tmpGroundPicture, 32, 32));
    // add(new Field(tmpGroundPicture, 32, 32));
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  }

  private void closeWindow(ActionEvent anActionEvent) {
    setVisible(false);
    dispose();
    System.exit(0);
  }

  class MyActionListener implements ActionListener {

    /**
     * @param anActionEvent
     * 
     */
    public void actionPerformed(ActionEvent anActionEvent) {
      Object tmpObject = anActionEvent.getSource();
      if (tmpObject == tmpItem) {
        closeWindow(anActionEvent);
      }

    }

  }

  /**
   * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
   */
  public void actionPerformed(ActionEvent aArg0) {
    // TODO Auto-generated method stub

  }

}