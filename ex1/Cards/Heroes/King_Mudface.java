package Cards.Heroes;

import Cards.Minions.minion;

import java.util.ArrayList;

public class King_Mudface extends hero{
    public King_Mudface(int mana, String description, ArrayList<String> colors, String name) {
        super(mana, description, colors, name);
    }

    @Override
    public void special(ArrayList<minion> Target) {
        for (minion card : Target) {
            int currentHealth = card.getHealth();
            currentHealth = currentHealth + 1;
            card.setHealth(currentHealth);
        }
    }
}
