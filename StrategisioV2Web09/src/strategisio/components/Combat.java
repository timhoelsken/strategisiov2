package strategisio.components;

import strategisio.elements.figures.Figure;

/**
 *
 * @author Tim
 *
 */
public class Combat {

  private Figure combatWinner;
  private Figure attacker;
  private Figure defender;
  private int[] attackerAttacks;
  private int[] attackerDefences;
  private int[] defenderAttacks;
  private int[] defenderDefences;

  private int chosenAttackerAttack;
  private int chosenAttackerDefense;
  private int chosenDefenderAttack;
  private int chosenDefenderDefense;

  /**
   * Combat constructor
   *
   * @param anAttacker
   *            the attacker figure
   * @param aDefender
   *            the defender figure
   */
  public Combat(Figure anAttacker, Figure aDefender) {

    attacker = anAttacker;
    defender = aDefender;

    attackerAttacks = attacker.getAttacks();
    attackerDefences = attacker.getDefences();
    defenderAttacks = defender.getAttacks();
    defenderDefences = defender.getDefences();

  }

  /**
   * @return the winning Figure
   */
  public Figure evaluate() {

    if (attackerAttacks.length == 4 && defenderDefences.length < 4) {
      combatWinner = attacker;
    } else if (defenderAttacks.length == 4 && attackerDefences.length < 4) {
      combatWinner = defender;
    } else {
      return null;
    }
    return combatWinner;
  }

  // TODO rewrite fight method with the private variables. Getter / Setter
  // will be called in a loop in JSP. There it has to be cleared up if all 4
  // variables are filled. Then call fight and see the winner or set the
  // variables back to null and do all again.
  /**
   *
   * @param anAttackerAttack
   * @param anAttackerDefence
   * @param aDefenderAttack
   * @param aDefenderDefence
   *
   * @return the winner of a fight
   */
  public Figure fight(int anAttackerAttack, int anAttackerDefence, int aDefenderAttack, int aDefenderDefence) {

    // fight logic
    if (anAttackerAttack == aDefenderDefence && aDefenderAttack == anAttackerDefence) {
      // draw
    } else {
      if (anAttackerAttack == aDefenderDefence) {
        combatWinner = defender;
      } else if (aDefenderAttack == anAttackerDefence) {
        combatWinner = attacker;
      }
    }
    return combatWinner;
  }

  // TODO method to check if all fight variables are set
  /**
   * @return always false at the moment
   */
  public boolean allAttacksAndDefencesAreSet() {
    return false;
  }

  /**
   * @return the chosenAttackerAttack
   */
  public int getChosenAttackerAttack() {
    return chosenAttackerAttack;
  }

  /**
   * @param aChosenAttackerAttack
   *            the chosenAttackerAttack to set
   */
  public void setChosenAttackerAttack(int aChosenAttackerAttack) {
    chosenAttackerAttack = aChosenAttackerAttack;
  }

  /**
   * @return the chosenAttackerDefense
   */
  public int getChosenAttackerDefense() {
    return chosenAttackerDefense;
  }

  /**
   * @param aChosenAttackerDefense
   *            the chosenAttackerDefense to set
   */
  public void setChosenAttackerDefense(int aChosenAttackerDefense) {
    chosenAttackerDefense = aChosenAttackerDefense;
  }

  /**
   * @return the chosenDefenderAttack
   */
  public int getChosenDefenderAttack() {
    return chosenDefenderAttack;
  }

  /**
   * @param aChosenDefenderAttack
   *            the chosenDefenderAttack to set
   */
  public void setChosenDefenderAttack(int aChosenDefenderAttack) {
    chosenDefenderAttack = aChosenDefenderAttack;
  }

  /**
   * @return the chosenDefenderDefense
   */
  public int getChosenDefenderDefense() {
    return chosenDefenderDefense;
  }

  /**
   * @param aChosenDefenderDefense
   *            the chosenDefenderDefense to set
   */
  public void setChosenDefenderDefense(int aChosenDefenderDefense) {
    chosenDefenderDefense = aChosenDefenderDefense;
  }
}
