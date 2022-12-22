package Cards.Minions;

import Cards.basicCard;

import java.util.ArrayList;

public class Berserker extends minion
{

    public Berserker(int mana, int health, int attackDamage, String description, ArrayList<String> colors, String name) {
        super(mana, health, attackDamage, description, colors, name, 0, false);
    }

    @Override
    public void special(minion Target) {

    }
}
