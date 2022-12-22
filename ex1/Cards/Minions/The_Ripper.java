package Cards.Minions;

import java.util.ArrayList;

public class The_Ripper extends minion {


    public The_Ripper(int mana, int health, int attackDamage, String description, ArrayList<String> colors, String name) {
        super(mana, health, attackDamage, description, colors, name, 0, true);
        this.setRow(1);
    }

    @Override
    public void special(minion Target) {
        int currentAttack = Target.getAttackDamage();
        currentAttack = currentAttack - 2;
        if (currentAttack < 0)
            currentAttack = 0;
        Target.setAttackDamage(currentAttack);
    }
}
