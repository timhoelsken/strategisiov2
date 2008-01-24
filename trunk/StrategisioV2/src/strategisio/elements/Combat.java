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
   * @param anAttackPlaceable
   * @param aDefendPlaceable
   * @return the winner of a fight
   */
  public Placeable init(Placeable anAttackPlaceable, Placeable aDefendPlaceable) {
    Placeable tmpPlaceable;
    Figure tmpAttacker = (Figure) anAttackPlaceable;
    Figure tmpDefender = (Figure) aDefendPlaceable;
    int[] tmpAttackerAttacks = tmpAttacker.getAttacks();
    int[] tmpAttackerDefences = tmpAttacker.getDefences();
    int[] tmpDefenderAttacks = tmpDefender.getAttacks();
    int[] tmpDefenderDefences = tmpDefender.getDefences();
    
    if (tmpAttackerAttacks.length == 4 && tmpDefenderDefences.length < 4) {
      tmpPlaceable = anAttackPlaceable;
    } else if (tmpDefenderAttacks.length == 4 && tmpAttackerDefences.length < 4) {
      tmpPlaceable = aDefendPlaceable;
    } else {
      tmpPlaceable = fight(anAttackPlaceable, aDefendPlaceable);
    }
    return tmpPlaceable;
  }

  /**
   * 
   * @param anAttackPlaceable
   * @param aDefendPlaceable
   * @return the winner of a fight
   */
  private Placeable fight(Placeable anAttackPlaceable, Placeable aDefendPlaceable) {
    int tmpAttackPlaceablesAttack, tmpAttackPlaceablesDefence;
    int tmpDefendPlaceablesAttack, tmpDefendPlaceablesDefence;
    Placeable tmpWinner = null;
    boolean tmpFighting = true;

    while (tmpFighting) {

      tmpAttackPlaceablesAttack = chooseAttack(anAttackPlaceable);
      tmpAttackPlaceablesDefence = chooseDefence(anAttackPlaceable);
      tmpDefendPlaceablesAttack = chooseAttack(aDefendPlaceable);
      tmpDefendPlaceablesDefence = chooseDefence(aDefendPlaceable);

      // Fight logic
      if (tmpAttackPlaceablesAttack == tmpDefendPlaceablesDefence && tmpDefendPlaceablesAttack == tmpAttackPlaceablesDefence) {
        // tie
      } else {
        if (tmpAttackPlaceablesAttack == tmpDefendPlaceablesDefence) {
          // DefendPlaceable did win
          tmpWinner = aDefendPlaceable;
          tmpFighting = false;
        } else if (tmpDefendPlaceablesAttack == tmpAttackPlaceablesDefence) {
          // AttackPlaceable did win
          tmpWinner = anAttackPlaceable;
          tmpFighting = false;
        } else {
          // tie
        }

      }
    }
    return tmpWinner;
  }

  private int chooseAttack(Placeable aPlaceable) {

    int tmpAttack = 0;
    // TODO interaction with player

    return tmpAttack;
  }

  private int chooseDefence(Placeable aPlaceable) {

    int tmpDefence = 0;
    // TODO interaction with player
    
    return tmpDefence;
  }
}
