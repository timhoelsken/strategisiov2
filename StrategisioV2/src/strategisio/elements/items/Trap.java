package strategisio.elements.items;

import strategisio.Figure;

public class Trap extends Item {

	private Figure catched;

	protected Figure getCatched() {
		return catched;
	}

	protected void setCatched(Figure catched) {
		this.catched = catched;
	}
}
