package strategisio.elements.figures;

import strategisio.elements.PlayElement;

/**
 * Abstract class for all figures
 */
public abstract class Figure implements PlayElement {

  private int horizontalSteps;

  private int verticalSteps;

  private int diagonalSteps;

  private int horizontalView;

  private int verticalView;

  private int diagonalView;

  private int groundAuthority;

  private int[] attacks;

  private int[] defences;

  /**
   * @return
   */
  protected int getHorizontalSteps() {
    return horizontalSteps;
  }

  /**
   * @param horizontalSteps
   */
  protected void setHorizontalSteps(int horizontalSteps) {
    this.horizontalSteps = horizontalSteps;
  }

  /**
   * @return
   */
  protected int getVerticalSteps() {
    return verticalSteps;
  }

  /**
   * @param verticalSteps
   */
  protected void setVerticalSteps(int verticalSteps) {
    this.verticalSteps = verticalSteps;
  }

  /**
   * @return
   */
  protected int getDiagonalSteps() {
    return diagonalSteps;
  }

  /**
   * @param diagonalSteps
   */
  protected void setDiagonalSteps(int diagonalSteps) {
    this.diagonalSteps = diagonalSteps;
  }

  /**
   * @return
   */
  protected int getHorizontalView() {
    return horizontalView;
  }

  /**
   * @param horizontalView
   */
  protected void setHorizontalView(int horizontalView) {
    this.horizontalView = horizontalView;
  }

  /**
   * @return
   */
  protected int getVerticalView() {
    return verticalView;
  }

  /**
   * @param verticalView
   */
  protected void setVerticalView(int verticalView) {
    this.verticalView = verticalView;
  }

  /**
   * @return
   */
  protected int getDiagonalView() {
    return diagonalView;
  }

  /**
   * @param diagonalView
   */
  protected void setDiagonalView(int diagonalView) {
    this.diagonalView = diagonalView;
  }

  /**
   * @return
   */
  protected int getGroundAuthority() {
    return groundAuthority;
  }

  /**
   * @param groundAuthority
   */
  protected void setGroundAuthority(int groundAuthority) {
    this.groundAuthority = groundAuthority;
  }

  /**
   * @return
   */
  protected int[] getAttacks() {
    return attacks;
  }

  /**
   * @param attacks
   */
  protected void setAttacks(int[] attacks) {
    this.attacks = attacks;
  }

  /**
   * @return
   */
  protected int[] getDefences() {
    return defences;
  }

  /**
   * @param defences
   */
  protected void setDefences(int[] defences) {
    this.defences = defences;
  }
}
