package Cards;

import Cards.Enviroment.Firestorm;
import Cards.Enviroment.Heart_Hound;
import Cards.Enviroment.Winterfell;
import Cards.Minions.*;
import fileio.CardInput;

public class CardConvert {
    public static basicCard convert(CardInput card){
        if(card.getName().equals("The Ripper")){
            The_Ripper out = new The_Ripper(card.getMana(), card.getHealth(), card.getAttackDamage(), card.getDescription(),card.getColors(),card.getName());
            return out;
        }
        else if(card.getName().equals("Miraj")){
            Miraj out = new Miraj(card.getMana(), card.getHealth(), card.getAttackDamage(), card.getDescription(),card.getColors(),card.getName());
            return out;
        }
        else if(card.getName().equals("The Cursed One")){
            The_Cursed_One out = new The_Cursed_One(card.getMana(), card.getHealth(), card.getAttackDamage(), card.getDescription(),card.getColors(),card.getName());
            return out;
        }
        else if(card.getName().equals("Disciple")){
            Disciple out = new Disciple(card.getMana(), card.getHealth(), card.getAttackDamage(), card.getDescription(),card.getColors(),card.getName());
            return out;
        }
        else if(card.getName().equals("Firestorm")){
            Firestorm out = new Firestorm(card.getMana(), card.getDescription(),card.getColors(),card.getName());
            return out;
        }
        else if(card.getName().equals("Winterfell")){
            Winterfell out = new Winterfell(card.getMana(), card.getDescription(),card.getColors(),card.getName());
            return out;
        }
        else if(card.getName().equals("Heart Hound")){
            Heart_Hound out = new Heart_Hound(card.getMana(), card.getDescription(),card.getColors(),card.getName());
            return out;
        }
        else if(card.getName().equals("Sentinel")){
            Sentinel out = new Sentinel(card.getMana(), card.getHealth(), card.getAttackDamage(), card.getDescription(),card.getColors(),card.getName());
            return out;
        }
        else if(card.getName().equals("Berserker")){
            Berserker out = new Berserker(card.getMana(), card.getHealth(), card.getAttackDamage(), card.getDescription(),card.getColors(),card.getName());
            return out;
        }
        else if(card.getName().equals("Goliath")){
            Goliath  out = new Goliath (card.getMana(), card.getHealth(), card.getAttackDamage(), card.getDescription(),card.getColors(),card.getName());
            return out;
        }
        else if(card.getName().equals("Warden")){
            Warden out = new Warden(card.getMana(), card.getHealth(), card.getAttackDamage(), card.getDescription(),card.getColors(),card.getName());
            return out;
        }
        return null;

    }
}
