package webtest;

/**
 * @author Tobias
 *
 */
public class ColorContainer {
  private final String GREEN = "#00FF00";
  private final String RED = "#FF0000";

  private String color;

  public ColorContainer(){
    color = GREEN;
  }

  public void switchColor(){
    if (GREEN.equals(color)) {
      color = RED;
    } else {
      color = GREEN;
    }
  }

  public String getColor() {
    return color;
  }
}
