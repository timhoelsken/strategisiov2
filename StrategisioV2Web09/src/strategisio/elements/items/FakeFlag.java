package strategisio.elements.items;

/**
 * @author Tobias
 *
 * the fakeflag
 */
public class FakeFlag extends Item {
  /**
   * @return image of this item
   */
  public String getImage() {
    return new String("flf_set.png");
  }

  /**
   * @return name of the item type
   */
  @Override
  public String getItemType(){
	  return new String("fakeflag");
  }
}