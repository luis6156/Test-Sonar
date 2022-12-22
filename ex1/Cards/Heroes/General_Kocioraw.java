package Cards.Heroes;

import Cards.Minions.minion;

import java.util.ArrayList;

public class General_Kocioraw extends hero{
    public General_Kocioraw(int mana, String description, ArrayList<String> colors, String name) {
        super(mana, description, colors, name);
    }

    @Override
    public void special(ArrayList<minion> Target) {
        for (minion card : Target) {
            int currentAttack = card.getAttackDamage();
            currentAttack = currentAttack + 1;
            card.setAttackDamage(currentAttack);
        }
    }
}
