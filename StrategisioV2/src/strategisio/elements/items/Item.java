package strategisio.elements.items;


/**
 * Abstract class for all figures
 */
abstract class Item {

	private boolean active;

	private boolean visible;

	protected boolean isActive() {
		return active;
	}

	protected void setActive(boolean active) {
		this.active = active;
	}

	protected boolean isVisible() {
		return visible;
	}

	protected void setVisible(boolean visible) {
		this.visible = visible;
	}
}
