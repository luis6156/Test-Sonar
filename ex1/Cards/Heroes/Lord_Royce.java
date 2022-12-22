package Cards.Heroes;

import Cards.Minions.minion;

import java.util.ArrayList;

public class Lord_Royce extends hero{

    public Lord_Royce(int mana, String description, ArrayList<String> colors, String name) {
        super(mana, description, colors, name);
    }

    @Override
    public void special(ArrayList<minion> Target) {
        int maxAttack = 0;
        for (minion card : Target) {
            if(maxAttack < card.getAttackDamage()) {
                maxAttack = card.getAttackDamage();
            }
        }
        for (minion card : Target) {
            if(maxAttack == card.getAttackDamage()) {
               card.setFreeze(1);
               break;
            }
        }
    }
}
