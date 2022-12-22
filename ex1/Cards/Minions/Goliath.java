package Cards.Minions;

import Cards.basicCard;

import java.util.ArrayList;

public class Goliath extends minion {

    public Goliath(int mana, int health, int attackDamage, String description, ArrayList<String> colors, String name) {
        super(mana, health, attackDamage, description, colors, name, 0, false);

        this.setRow(1);
    }

    @Override
    public void special(minion Target) {

    }
}
