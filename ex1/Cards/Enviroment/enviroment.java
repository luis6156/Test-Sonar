package Cards.Enviroment;

import Cards.Minions.minion;
import Cards.basicCard;

import java.util.ArrayList;

abstract public class enviroment extends basicCard {
    abstract void special(ArrayList<minion> Target, ArrayList<minion> Destination);
    public enviroment(int mana, String description, ArrayList<String> colors, String name){
        this.setMana(mana);
        this.setDescription(description);
        this.setColors(colors);
        this.setName(name);
    }
}
