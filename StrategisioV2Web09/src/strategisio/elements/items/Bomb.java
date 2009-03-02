package strategisio.elements.items;

/**
 * @author Tobias
 *
 * the bomb
 */
public class Bomb extends Item {

  /**
   * @return image of this item
   */
  public String getImage() {
    return new String("bmb_set.png");
  }

  /**
   * @return name of the item type
   */
  @Override
  public String getItemType(){
	  return new String("bomb");
  }
}
