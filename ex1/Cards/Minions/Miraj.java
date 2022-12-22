package Cards.Minions;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class Miraj extends minion{
    public Miraj(int mana, int health, int attackDamage, String description, ArrayList<String> colors, String name) {
        super(mana, health, attackDamage, description, colors, name, 0, true);
        this.setRow(1);
    }

    @Override
    public void special(minion Target) {
        int myHealth = this.health;
        int targetHealth = Target.getHealth();
        this.health = targetHealth;
        Target.setHealth(myHealth);
    }

    @Override
    public void setAttackDamage(int attackDamage) {
        super.setAttackDamage(attackDamage);
    }

}
