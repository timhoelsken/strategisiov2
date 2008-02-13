package strategisio.elements.figures;

import strategisio.elements.Placeable;

/**
 * Abstract class for all figures
 */
public abstract class Figure implements Placeable {

  private int normalSteps;

  private int diagonalSteps;

  private int normalView;

  private int diagonalView;

  private int[] groundAuthorities;

  private int[] attacks;

  private int[] defences;

  private int[] currentCoordinates = new int[2];

  private char id;

  /**
   * @return the normalSteps
   */
  public int getNormalSteps() {
    return normalSteps;
  }

  /**
   * @param aNormalSteps
   *            the normalSteps to set
   */
  protected void setNormalSteps(int aNormalSteps) {
    normalSteps = aNormalSteps;
  }

  /**
   * @return the diagonalSteps
   */
  public int getDiagonalSteps() {
    return diagonalSteps;
  }

  /**
   * @param aDiagonalSteps
   *            the diagonalSteps to set
   */
  protected void setDiagonalSteps(int aDiagonalSteps) {
    diagonalSteps = aDiagonalSteps;
  }

  /**
   * @return the normalView
   */
  public int getNormalView() {
    return normalView;
  }

  /**
   * @param aNormalView
   *            the normalView to set
   */
  protected void setNormalView(int aNormalView) {
    normalView = aNormalView;
  }

  /**
   * @return the diagonalView
   */
  public int getDiagonalView() {
    return diagonalView;
  }

  /**
   * @param aDiagonalView
   *            the diagonalView to set
   */
  protected void setDiagonalView(int aDiagonalView) {
    diagonalView = aDiagonalView;
  }

  /**
   * @return the groundAuthorities
   */
  public int[] getGroundAuthorities() {
    return groundAuthorities;
  }

  /**
   * @param aGroundAuthorities
   *            the groundAuthorities to set
   */
  protected void setGroundAuthorities(int[] aGroundAuthorities) {
    groundAuthorities = aGroundAuthorities;
  }

  /**
   * @return the attacks
   */
  public int[] getAttacks() {
    return attacks;
  }

  /**
   * @param aAttacks
   *            the attacks to set
   */
  protected void setAttacks(int[] aAttacks) {
    attacks = aAttacks;
  }

  /**
   * @return the defences
   */
  public int[] getDefences() {
    return defences;
  }

  /**
   * @param aDefences
   *            the defences to set
   */
  protected void setDefences(int[] aDefences) {
    defences = aDefences;
  }

  /**
   * @return the id
   */
  public char getId() {
    return id;
  }

  /**
   * @param aId
   *            the id to set
   */
  public void setId(char aId) {
    id = aId;
  }
  /**
   * @return the currentCoordinates
   */
  public int[] getCurrentCoordinates() {
    return currentCoordinates;
  }

  /**
   *
   * @param anX
   * @param aY
   */
  public void setCurrentCoordinates(int anX, int aY) {

    currentCoordinates[0] = anX;
    currentCoordinates[1] = aY;
  }
}
