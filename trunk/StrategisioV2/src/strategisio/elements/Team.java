package strategisio.elements;

import java.util.ArrayList;
import java.util.Iterator;

import strategisio.elements.figures.Climber;
import strategisio.elements.figures.Diver;
import strategisio.elements.figures.Fighter;
import strategisio.elements.figures.Figure;
import strategisio.elements.figures.Medic;
import strategisio.elements.figures.Spy;
import strategisio.elements.items.Bomb;
import strategisio.elements.items.FakeFlag;
import strategisio.elements.items.Flag;
import strategisio.elements.items.Item;
import strategisio.elements.items.Trap;

/**
 *
 * represents a player's Team
 *
 */
public class Team {

  private String id;

  private ArrayList<Figure> figures;

  private ArrayList<Item> items;

  /**
   * constructor
   *
   * @param anId
   *            a unique id for the team
   */
  public Team(String anId) {
    id = anId;

    // TODO dynamic fill has to be implemented
    final int tmpNoOfFighters = 2;
    final int tmpNoOfClimbers = 2;
    final int tmpNoOfDivers = 2;
    final int tmpNoOfMedics = 2;
    final int tmpNoOfSpys = 2;

    figures = new ArrayList<Figure>();
    for (int i = 0; i < tmpNoOfFighters; i++) {
      figures.add(new Fighter());
    }
    for (int i = 0; i < tmpNoOfClimbers; i++) {
      figures.add(new Climber());
    }
    for (int i = 0; i < tmpNoOfDivers; i++) {
      figures.add(new Diver());
    }
    for (int i = 0; i < tmpNoOfMedics; i++) {
      figures.add(new Medic());
    }
    for (int i = 0; i < tmpNoOfSpys; i++) {
      figures.add(new Spy());
    }

    final int tmpNoOfFakeFlags = 2;
    final int tmpNoOfTraps = 2;
    final int tmpNoOfBombs = 3;

    items = new ArrayList<Item>();
    items.add(new Flag());
    for (int i = 0; i < tmpNoOfFakeFlags; i++) {
      items.add(new FakeFlag());
    }
    for (int i = 0; i < tmpNoOfTraps; i++) {
      items.add(new Trap());
    }
    for (int i = 0; i < tmpNoOfBombs; i++) {
      items.add(new Bomb());
    }

    checkTeam();
  }

  private void checkTeam() {
    int tmpFlagCounter = 1;
    for (Iterator<Item> i = items.iterator(); i.hasNext();) {
      if (i.next() instanceof Flag) {
        tmpFlagCounter--;
      }
    }
    if (tmpFlagCounter < 0) {
      // TODO Exception: just one flag allowed
    }
  }

  /**
   * @return the id
   */
  public String getId() {
    return id;
  }

  /**
   * @param aId the id to set
   */
  public void setId(String aId) {
    id = aId;
  }

  /**
   * @return the figures
   */
  public ArrayList<Figure> getFigures() {
    return figures;
  }

  /**
   * @param aFigures the figures to set
   */
  public void setFigures(ArrayList<Figure> aFigures) {
    figures = aFigures;
  }

  /**
   * @return the items
   */
  public ArrayList<Item> getItems() {
    return items;
  }

  /**
   * @param aItems the items to set
   */
  public void setItems(ArrayList<Item> aItems) {
    items = aItems;
  }
}
