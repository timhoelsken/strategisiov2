package old;

/**
 * 
 * @author Tim
 * 
 * This class defines an item
 */
public class Item {

	private String kind;

	private boolean active;

	private boolean visible;

	public Item(String aKind) {
		kind = aKind;
		active = false;
		visible = false;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean isActive) {
		active = isActive;
	}

	public String getKind() {
		return kind;
	}

	public void setKind(String aKind) {
		kind = aKind;
	}

	public boolean isVisible() {
		return visible;
	}

	public void setVisible(boolean isVisible) {
		visible = isVisible;
	}
}
