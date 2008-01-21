package strategisio.elements.fields;

import strategisio.elements.PlayElement;

/**
 *
 * represents a Field on the PlayMap
 * can hold a PlayElement
 *
 */
public abstract class Field {

	private PlayElement setter = null;

	public PlayElement getSetter() {
		return setter;
	}

	public void setSetter(PlayElement setter) {
		this.setter = setter;
	}
}
