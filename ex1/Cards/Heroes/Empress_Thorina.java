package Cards.Heroes;

import Cards.Minions.minion;

import java.util.ArrayList;

public class Empress_Thorina extends hero{
    public Empress_Thorina(int mana, String description, ArrayList<String> colors, String name) {
        super(mana, description, colors, name);
    }

    @Override
    public void special(ArrayList<minion> Target) {
        int maxHealth = 0;
        for (minion card : Target) {
            if(maxHealth < card.getHealth()) {
                maxHealth = card.getHealth();
            }
        }
        for (minion card : Target) {
            if(maxHealth == card.getHealth()) {
                card.setHealth(0);
                break;
            }
        }
    }
}
