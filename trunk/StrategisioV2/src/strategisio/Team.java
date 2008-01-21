package strategisio;

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

public class Team {

	private int id;

	private ArrayList<Figure> figures;

	private ArrayList<Item> items;

	public Team(int anId){
		id = anId;

		//TODO dynamic fill has to be implemented
		figures = new ArrayList<Figure>();
		figures.add(new Fighter());
		figures.add(new Fighter());
		figures.add(new Climber());
		figures.add(new Climber());
		figures.add(new Diver());
		figures.add(new Diver());
		figures.add(new Medic());
		figures.add(new Medic());
		figures.add(new Spy());

		items = new ArrayList<Item>();
		items.add(new Flag());
		items.add(new FakeFlag());
		items.add(new FakeFlag());
		items.add(new Trap());
		items.add(new Trap());
		items.add(new Bomb());
		items.add(new Bomb());
		items.add(new Bomb());

		checkTeam();
	}

	private void checkTeam(){
		int tmpFlagCounter = 1;
		for (Iterator<Item> i = items.iterator(); i.hasNext();) {
			if (i.next() instanceof Flag) {
				tmpFlagCounter--;
			}
		}
		if (tmpFlagCounter < 0){
			//TODO Exception: just one flag allowed
		}
	}
}
