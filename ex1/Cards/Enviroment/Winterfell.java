package Cards.Enviroment;

import Cards.Minions.minion;

import java.util.ArrayList;

public class Winterfell extends enviroment{

    public Winterfell(int mana, String description, ArrayList<String> colors, String name) {
        super(mana, description, colors, name);
    }

    @Override
    public void special(ArrayList<minion> Target, ArrayList<minion> Destination) {
        for (minion card : Target) {
            card.setFreeze(1);
        }
    }
}
