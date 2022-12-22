package Cards.Minions;

import java.util.ArrayList;

public class The_Cursed_One extends minion{

    public The_Cursed_One(int mana, int health, int attackDamage, String description, ArrayList<String> colors, String name) {
        super(mana, health, attackDamage, description, colors, name, 0, true);
    }

    @Override
    public void special(minion Target) {
        int targetAttack = Target.getAttackDamage();
        int targetHealth = Target.getHealth();
        Target.setHealth(targetAttack);
        Target.setAttackDamage(targetHealth);
    }
}
