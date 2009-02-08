package strategisio.elements;

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
  /**
   *
   * @param anAttacker
   * @param aDefender
   * @return the winner of a fight
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
   *
   * @param anAttacker
   * @param aDefender
   * @return
   */
  public Figure evaluate(){
	  if (attackerAttacks.length == 4 && defenderDefences.length < 4) {
	      combatWinner = attacker;
	    } else if (defenderAttacks.length == 4 && attackerDefences.length < 4) {
	      combatWinner = defender;
	    } else {
	      return null;
	    }
	    return combatWinner;
  }
  /**
   *
   * @param anAttacker
   * @param aDefender
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

  private int chooseAttack(Figure aFigure){// throws IOException {

    int[] tmpAllAttacks = aFigure.getAttacks();

    return getAttacks(tmpAllAttacks);
  }

  public int getAttacks(int[] tmpAllAttacks) {
	//tmpAttack
	return 0;
}

private int chooseDefence(Figure aFigure) {

    int tmpDefence = 0;
    //int[] tmpAllDefences = aFigure.getDefences();
    // TODO interaction with player

    return tmpDefence;
  }
}
