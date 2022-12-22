package Cards.Minions;

import java.util.ArrayList;

public class Disciple extends minion{

    public Disciple(int mana, int health, int attackDamage, String description, ArrayList<String> colors, String name) {
        super(mana, health, attackDamage, description, colors, name, 0, true);
    }

    @Override
    public void special(minion Target) {
        int targetHealth = Target.getHealth();
        targetHealth = targetHealth + 2;
        Target.setHealth(targetHealth);
    }

}
