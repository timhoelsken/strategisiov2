package strategisio.elements.items;

/**
 * @author Tobias
 *
 * the flag
 */
public class Flag extends Item {
  /**
   * @return image of this item
   */
  public String getImage() {
    return new String("flg_set.png");
  }

  /**
   * @return name of the item type
   */
  @Override
  public String getItemType(){
	  return new String("flag");
  }
}