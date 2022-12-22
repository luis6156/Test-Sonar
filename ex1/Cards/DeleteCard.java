package Cards;

import Cards.Minions.minion;

import java.util.ArrayList;

public class DeleteCard {
    public static void delete(ArrayList<minion> array){
        ArrayList<Integer> poz = new ArrayList<Integer>();
        int i = 0;
        for(minion card: array) {
            if(card.getHealth() <= 0) {
                poz.add(i);
            }
            i++;
        }
        for(i = poz.size() - 1; i >=0; i--){
            array.remove(array.get(poz.get(i)));
        }
    }
}
