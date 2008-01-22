package strategisio.elements.figures;

import strategisio.elements.Movable;

/**
 * Abstract class for all figures
 */
public abstract class Figure implements Movable {

  private int normalSteps;

  private int diagonalSteps;

  private int horizontalView;

  private int verticalView;

  private int diagonalView;

  private int groundAuthority;

  private int[] attacks;

  private int[] defences;

  private String id;

  /**
   * @return the normalSteps
   */
  protected int getNormalSteps() {
    return normalSteps;
  }

  /**
   * @param aNormalSteps the normalSteps to set
   */
  protected void setNormalSteps(int aNormalSteps) {
    normalSteps = aNormalSteps;
  }

  /**
   * @return the diagonalSteps
   */
  protected int getDiagonalSteps() {
    return diagonalSteps;
  }

  /**
   * @param aDiagonalSteps the diagonalSteps to set
   */
  protected void setDiagonalSteps(int aDiagonalSteps) {
    diagonalSteps = aDiagonalSteps;
  }

  /**
   * @return the horizontalView
   */
  protected int getHorizontalView() {
    return horizontalView;
  }

  /**
   * @param aHorizontalView the horizontalView to set
   */
  protected void setHorizontalView(int aHorizontalView) {
    horizontalView = aHorizontalView;
  }

  /**
   * @return the verticalView
   */
  protected int getVerticalView() {
    return verticalView;
  }

  /**
   * @param aVerticalView the verticalView to set
   */
  protected void setVerticalView(int aVerticalView) {
    verticalView = aVerticalView;
  }

  /**
   * @return the diagonalView
   */
  protected int getDiagonalView() {
    return diagonalView;
  }

  /**
   * @param aDiagonalView the diagonalView to set
   */
  protected void setDiagonalView(int aDiagonalView) {
    diagonalView = aDiagonalView;
  }

  /**
   * @return the groundAuthority
   */
  protected int getGroundAuthority() {
    return groundAuthority;
  }

  /**
   * @param aGroundAuthority the groundAuthority to set
   */
  protected void setGroundAuthority(int aGroundAuthority) {
    groundAuthority = aGroundAuthority;
  }

  /**
   * @return the attacks
   */
  protected int[] getAttacks() {
    return attacks;
  }

  /**
   * @param aAttacks the attacks to set
   */
  protected void setAttacks(int[] aAttacks) {
    attacks = aAttacks;
  }

  /**
   * @return the defences
   */
  protected int[] getDefences() {
    return defences;
  }

  /**
   * @param aDefences the defences to set
   */
  protected void setDefences(int[] aDefences) {
    defences = aDefences;
  }

  /**
   * @return the id
   */
  public String getId() {
    return id;
  }

  /**
   * @param aId the id to set
   */
  public void setId(String aId) {
    id = aId;
  }
}
