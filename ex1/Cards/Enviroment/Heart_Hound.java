package Cards.Enviroment;

import Cards.Minions.minion;

import java.util.ArrayList;

public class Heart_Hound extends enviroment{

    public Heart_Hound(int mana, String description, ArrayList<String> colors, String name) {
        super(mana, description, colors, name);
    }

    @Override
    public void special(ArrayList<minion> Target, ArrayList<minion> Destination) {
        int maxHealth = 0;
        int position = 0;
        int i = 0;
        for (minion card : Target) {
            if(maxHealth < card.getHealth()) {
                position = i;
                maxHealth = card.getHealth();
            }
            i = i + 1;
        }
        for (minion card : Target) {
            if(maxHealth == card.getHealth()) {
                if(Destination.size() < 5) {
                    Destination.add(card);
                    Target.remove(position);
                }
                break;
            }
        }
    }
}
