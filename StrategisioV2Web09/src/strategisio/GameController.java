package strategisio;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

import strategisio.exceptions.CoordinateOutOfIndexException;
import strategisio.exceptions.FlagLimitOverflowException;
import strategisio.exceptions.UnknownFieldGroundException;
import strategisio.visualization.ConsoleDisplay;

/**
 * 
 * @author Tim
 * 
 */
public class GameController {

  /**
   * @param args
   * @throws UnknownFieldGroundException
   * @throws FlagLimitOverflowException
   * @throws IOException
   * @throws CoordinateOutOfIndexException
   * @throws NumberFormatException
   */
  public static void main(String[] args) throws FlagLimitOverflowException, UnknownFieldGroundException,
      IOException, NumberFormatException, CoordinateOutOfIndexException {
    if (args.length != 0) {
      GameController tmpGameController = new GameController();
      if (args[0].equals("console")) {
        System.out.println("Consolen-Anwendung wird gestartet...");
        if (!tmpGameController.startConsoleGame(args)) {
          System.out.println("Sie haben das Spiel verlassen.");
        }
      }
    } else {
      System.out.println("Um ein Spiel zu starten müssen Sie Parameter übergeben!");
      // console resources/map_config.xml TeamA TeamB
      System.out.println("\"Art des Spiels\", \"URL der XML\", \"Teamname A\", \"Teamname B \"");

    }
  }

  /**
   * @param args
   * @return true if start was successfull
   * @throws FlagLimitOverflowException
   * @throws UnknownFieldGroundException
   * @throws IOException
   * @throws NumberFormatException
   * @throws CoordinateOutOfIndexException
   */
  protected boolean startConsoleGame(String[] args) throws FlagLimitOverflowException,
      UnknownFieldGroundException, IOException, NumberFormatException, CoordinateOutOfIndexException {
    if (args.length > 2) {

      String tmpPlayerInput = "";
      System.out.println("lade spiel");
      File tmpFile = new File(args[1]);
      Game tmpGame = new Game(tmpFile, args[2], args[3]);
      ConsoleDisplay tmpDisplayer = new ConsoleDisplay();
      tmpGame.generateMapAutomatically();
      tmpGame.setDisplayer(tmpDisplayer);
      tmpGame.display("wholeMap");

      System.out.println("Das Spiel kann beginnen, Sie können ziehen!");
      System.out.println("Zum bewegen den Befehl move eingeben, mit Anfangs- und Endkoordinaten:");
      System.out.println("z.B. \"move 1/2 2/2\"");
      System.out.println("- move x/y x/y");
      System.out.println("- HELP");
      System.out.println("- exit");
      InputStreamReader tmpStremReader = new InputStreamReader(System.in);
      BufferedReader tmpStdIn = new BufferedReader(tmpStremReader);
      while (!tmpPlayerInput.equals("exit")) {
        tmpPlayerInput = tmpStdIn.readLine();
        String[] tmpInputArray = tmpPlayerInput.split(" ");
        if (tmpInputArray.length > 0) {

          if (tmpInputArray[0].equals("move")) {
            System.out.println("Sie wollen sich bewegen.");
            System.out.println("anzahl Parameter = " + tmpInputArray.length);
            if (tmpInputArray.length > 2) {
              String[] tmpOldCoordinates = tmpInputArray[1].split("/");
              String[] tmpNewCoordinates = tmpInputArray[2].split("/");
              tmpGame.move(Integer.parseInt(tmpOldCoordinates[0]), Integer.parseInt(tmpOldCoordinates[1]),
                  Integer.parseInt(tmpNewCoordinates[0]), Integer.parseInt(tmpNewCoordinates[1]));
              System.out.println("Figur von " + tmpInputArray[1] + " nach " + tmpInputArray[2] + " bewegt!");
              tmpGame.display("wholeMap");
            } else {
              System.out.println("Sie haben zu wenig Parameter angegeben um eine Figur zu bewegen.");
            }
          } else if (tmpInputArray[0].equals("HELP")) {
            System.out.println("Sie wollen Hilfe.");
          } else if (tmpInputArray[0].equals("exit")) {
            // game exit
          } else {
            System.out.println("Ihr Befehl wurde nicht erkannt, bitte neu eingeben, HELP für Hilfe.");
          }
        } else {
          System.out.println("Ihr Befehl wurde nicht erkannt, bitte neu eingeben, HELP für Hilfe.");
        }
      }
      System.out.println("Spiel beendet.");
      return true;
    } else {
      System.out.println("Sie haben nicht genügend Parameter eingegeben um ein Spiel zu starten.");
      return false;
    }
  }
}
