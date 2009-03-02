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

//TODO this may be wrong (in all items/figures)... or more... is
	// inconsequent to the other abstracts... here the method is overridden...
	// you may change the method to a "getType()" method in placeables? Because
	// this method is also needed for figures -- OR, more likely, we could
	// override the toString() method
  /**
   * @return name of the item type
   */
  @Override
  public String getItemType(){
	  return new String("bomb");
  }
}
