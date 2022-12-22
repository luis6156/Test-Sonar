package Cards.Heroes;

import Cards.Minions.minion;
import Cards.basicCard;

import java.util.ArrayList;

abstract public class hero extends basicCard {
    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public hero(int mana, String description, ArrayList<String> colors, String name) {
        this.setMana(mana);
        this.setDescription(description);
        this.setColors(colors);
        this.setName(name);
    }

    public int getHasAttacked() {
        return hasAttacked;
    }

    public void setHasAttacked(int hasAttacked) {
        this.hasAttacked = hasAttacked;
    }

    protected int hasAttacked;
    protected int health = 30;
    public abstract void special(ArrayList<minion> Target);
}
