package strategisio.elements;

import strategisio.elements.figures.*;

/**
 * 
 * @author Tim
 * 
 */
public class Combat {

  /**
   * 
   * @param anAttacker
   * @param aDefender
   * @return the winner of a fight
   */
  public Figure init(Figure anAttacker, Figure aDefender) {
    Figure tmpPlaceable;
    Figure tmpAttacker = anAttacker;
    Figure tmpDefender = aDefender;
    int[] tmpAttackerAttacks = tmpAttacker.getAttacks();
    int[] tmpAttackerDefences = tmpAttacker.getDefences();
    int[] tmpDefenderAttacks = tmpDefender.getAttacks();
    int[] tmpDefenderDefences = tmpDefender.getDefences();

    if (tmpAttackerAttacks.length == 4 && tmpDefenderDefences.length < 4) {
      tmpPlaceable = anAttacker;
    } else if (tmpDefenderAttacks.length == 4 && tmpAttackerDefences.length < 4) {
      tmpPlaceable = aDefender;
    } else {
      tmpPlaceable = fight(anAttacker, aDefender);
    }
    return tmpPlaceable;
  }

  /**
   * 
   * @param anAttacker
   * @param aDefender
   * @return the winner of a fight
   */
  private Figure fight(Figure anAttacker, Figure aDefender) {
    int tmpAttackerAttack, tmpAttackerDefence;
    int tmpDefenderAttack, tmpDefenderDefence;
    Figure tmpWinner = null;
    boolean tmpFighting = true;

    while (tmpFighting) {
      tmpAttackerAttack = chooseAttack(anAttacker);
      tmpAttackerDefence = chooseDefence(anAttacker);
      tmpDefenderAttack = chooseAttack(aDefender);
      tmpDefenderDefence = chooseDefence(aDefender);

      // fight logic
      if (tmpAttackerAttack == tmpDefenderDefence && tmpDefenderAttack == tmpAttackerDefence) {
        // draw (don't know the word 'tie')
      } else {
        if (tmpAttackerAttack == tmpDefenderDefence) {
          tmpWinner = aDefender;
          tmpFighting = false;
        } else if (tmpDefenderAttack == tmpAttackerDefence) {
          tmpWinner = anAttacker;
          tmpFighting = false;
        } else {
          // draw (uuuuuuuh a reasonable empty else block - for a comment) xD
        }
      }
    }
    return tmpWinner;
  }

  private int chooseAttack(Figure aFigure) {

    int tmpAttack = 0;
    // TODO interaction with player

    return tmpAttack;
  }

  private int chooseDefence(Figure aFigure) {

    int tmpDefence = 0;
    // TODO interaction with player

    return tmpDefence;
  }
}
