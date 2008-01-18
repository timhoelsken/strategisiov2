package strategisio;

/**
 * 
 * @author Tim
 * 
 * This Class defines an item
 */
public class Item {

	private String kind;

	private boolean active;

	private boolean visible;

	public Item(String kind) {
		this.kind = kind;
		this.active = false;
		this.visible = false;
	}

	public String getKind() {
		return kind;
	}

	public void setKind(String kind) {
		this.kind = kind;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public boolean isVisible() {
		return visible;
	}

	public void setVisible(boolean visible) {
		this.visible = visible;
	}
}
