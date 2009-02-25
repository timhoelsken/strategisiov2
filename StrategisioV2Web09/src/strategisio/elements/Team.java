package strategisio.elements;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;

import strategisio.XmlReader;
import strategisio.elements.figures.Climber;
import strategisio.elements.figures.Diver;
import strategisio.elements.figures.Fighter;
import strategisio.elements.figures.Figure;
import strategisio.elements.figures.Medic;
import strategisio.elements.figures.Miner;
import strategisio.elements.figures.Spy;
import strategisio.elements.items.Bomb;
import strategisio.elements.items.FakeFlag;
import strategisio.elements.items.Flag;
import strategisio.elements.items.Item;
import strategisio.elements.items.Trap;
import strategisio.exceptions.FlagLimitOverflowException;

/**
 *
 * represents a player's Team
 *
 */
public class Team {

  private char id;

  private String name;

  private ArrayList<Figure> figures;

  private ArrayList<Item> items;

  /**
   * constructor for non XML
   *
   * @param anId
   *            a unique id for the team
   * @param aName
   *            a name for the team
   * @throws FlagLimitOverflowException
   */
  public Team(char anId, String aName) throws FlagLimitOverflowException {
    id = anId;
    name = aName;

    final int tmpNoOfFighters = 2;
    final int tmpNoOfClimbers = 2;
    final int tmpNoOfDivers = 2;
    final int tmpNoOfMedics = 2;
    final int tmpNoOfSpys = 2;

    figures = new ArrayList<Figure>();
    for (int i = 0; i < tmpNoOfFighters; i++) {
      Fighter tmpFighter = new Fighter();
      tmpFighter.setId(anId);
      figures.add(tmpFighter);
    }
    for (int i = 0; i < tmpNoOfClimbers; i++) {
      Climber tmpClimber = new Climber();
      tmpClimber.setId(anId);
      figures.add(tmpClimber);
    }
    for (int i = 0; i < tmpNoOfDivers; i++) {
      Diver tmpDiver = new Diver();
      tmpDiver.setId(anId);
      figures.add(tmpDiver);
    }
    for (int i = 0; i < tmpNoOfMedics; i++) {
      Medic tmpMedic = new Medic();
      tmpMedic.setId(anId);
      figures.add(tmpMedic);
    }
    for (int i = 0; i < tmpNoOfSpys; i++) {
      Spy tmpSpy = new Spy();
      tmpSpy.setId(anId);
      figures.add(tmpSpy);
    }

    final int tmpNoOfFakeFlags = 2;
    final int tmpNoOfTraps = 2;
    final int tmpNoOfBombs = 3;

    items = new ArrayList<Item>();
    Flag tmpFlag = new Flag();
    tmpFlag.setId(anId);
    items.add(tmpFlag);
    for (int i = 0; i < tmpNoOfFakeFlags; i++) {
      FakeFlag tmpFakeFlag = new FakeFlag();
      tmpFakeFlag.setId(anId);
      items.add(tmpFakeFlag);
    }
    for (int i = 0; i < tmpNoOfTraps; i++) {
      Trap tmpTrap = new Trap();
      tmpTrap.setId(anId);
      items.add(tmpTrap);
    }
    for (int i = 0; i < tmpNoOfBombs; i++) {
      Bomb tmpBomb = new Bomb();
      tmpBomb.setId(anId);
      items.add(tmpBomb);
    }

    checkTeam();
  }

  /**
   * constructor for XML
   *
   * @param anId
   *            a unique id for the team
   * @param aName
   *            a name for the team
   * @param aFile
   * @throws FlagLimitOverflowException
   */
  public Team(char anId, String aName, File aFile) throws FlagLimitOverflowException {
    id = anId;
    name = aName;
    int[] tmpTeamData;

    XmlReader tmpReader = new XmlReader();
    tmpTeamData = tmpReader.getTeamdata(aFile);

    final int tmpNoOfFighters = tmpTeamData[0];
    final int tmpNoOfClimbers = tmpTeamData[1];
    final int tmpNoOfDivers = tmpTeamData[2];
    final int tmpNoOfMedics = tmpTeamData[3];
    final int tmpNoOfSpys = tmpTeamData[4];
    final int tmpNoOfMiners = tmpTeamData[5];

    figures = new ArrayList<Figure>();
    for (int i = 0; i < tmpNoOfFighters; i++) {
      Fighter tmpFighter = new Fighter();
      tmpFighter.setId(anId);
      figures.add(tmpFighter);
    }
    for (int i = 0; i < tmpNoOfClimbers; i++) {
      Climber tmpClimber = new Climber();
      tmpClimber.setId(anId);
      figures.add(tmpClimber);
    }
    for (int i = 0; i < tmpNoOfDivers; i++) {
      Diver tmpDiver = new Diver();
      tmpDiver.setId(anId);
      figures.add(tmpDiver);
    }
    for (int i = 0; i < tmpNoOfMedics; i++) {
      Medic tmpMedic = new Medic();
      tmpMedic.setId(anId);
      figures.add(tmpMedic);
    }
    for (int i = 0; i < tmpNoOfSpys; i++) {
      Spy tmpSpy = new Spy();
      tmpSpy.setId(anId);
      figures.add(tmpSpy);
    }
    for (int i = 0; i < tmpNoOfMiners; i++) {
      Miner tmpMiner = new Miner();
      tmpMiner.setId(anId);
      figures.add(tmpMiner);
    }

    // because there are a lot of null fields in the array... why ever
    figures.trimToSize();

    final int tmpNoOfFakeFlags = tmpTeamData[6];
    final int tmpNoOfTraps = tmpTeamData[7];
    final int tmpNoOfBombs = tmpTeamData[8];

    items = new ArrayList<Item>();

    Flag tmpFlag = new Flag();
    tmpFlag.setId(anId);
    items.add(tmpFlag);

    for (int i = 0; i < tmpNoOfFakeFlags; i++) {
      FakeFlag tmpFakeFlag = new FakeFlag();
      tmpFakeFlag.setId(anId);
      items.add(tmpFakeFlag);
    }
    for (int i = 0; i < tmpNoOfTraps; i++) {
      Trap tmpTrap = new Trap();
      tmpTrap.setId(anId);
      items.add(tmpTrap);
    }
    for (int i = 0; i < tmpNoOfBombs; i++) {
      Bomb tmpBomb = new Bomb();
      tmpBomb.setId(anId);
      items.add(tmpBomb);
    }

    // because there are a lot of null fields in the array... why ever
    items.trimToSize();

    checkTeam();
  }

  private void checkTeam() throws FlagLimitOverflowException {
    int tmpFlagCounter = 1;
    for (Iterator<Item> i = items.iterator(); i.hasNext();) {
      if (i.next() instanceof Flag) {
        tmpFlagCounter--;
      }
    }
    if (tmpFlagCounter < 0) {
      throw new FlagLimitOverflowException("Too many Flags in the Team!");
    }
  }

  /**
   * @return the id
   */
  public char getId() {
    return id;
  }

  /**
   * @param aId
   *            the id to set
   */
  public void setId(char aId) {
    id = aId;
  }

  /**
   * @return the name
   */
  public String getName() {
    return name;
  }

  /**
   * @param aName
   *            the name to set
   */
  public void setName(String aName) {
    name = aName;
  }

  /**
   * @return the figures
   */
  public ArrayList<Figure> getFigures() {
    return figures;
  }

  /**
   * @param aFigures
   *            the figures to set
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
   * @param aItems
   *            the items to set
   */
  public void setItems(ArrayList<Item> aItems) {
    items = aItems;
  }

  /**
   *
   * @param aFigure
   */
  public void removeFigureFromTeam(Figure aFigure){
	  for (int i=0; i<figures.size();i++){
		if (aFigure.getFigureType().equals(figures.get(i).getFigureType())){
			figures.remove(i);
			break;
		}
	  }
  }
}
