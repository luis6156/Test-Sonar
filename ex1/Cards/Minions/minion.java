package Cards.Minions;

import Cards.basicCard;

import java.util.ArrayList;

abstract public class minion extends basicCard {
    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getAttackDamage() {
        return attackDamage;
    }

    public void setAttackDamage(int attackDamage) {
        this.attackDamage = attackDamage;
    }
    protected int health;
    protected int attackDamage;

    public int getFreeze() {
        return freeze;
    }

    public void setFreeze(int freeze) {
        this.freeze = freeze;
    }

    protected int freeze;
    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    protected int row = 0;
    public boolean isSpecial() {
        return special;
    }

    public void setSpecial(boolean special) {
        this.special = special;
    }

    public int getAlreadyAttacked() {
        return alreadyAttacked;
    }

    public void setAlreadyAttacked(int alreadyAttacked) {
        this.alreadyAttacked = alreadyAttacked;
    }

    protected int alreadyAttacked;
    protected boolean special;

    public minion(int mana, int health, int attackDamage, String description, ArrayList<String> colors, String name, int freeze, boolean special){
        this.setMana(mana);
        this.health = health;
        this.attackDamage = attackDamage;
        this.setDescription(description);
        this.setColors(colors);
        this.setName(name);
        this.freeze = freeze;
        this.special = special;
    }

    public abstract void special(minion Target);
}
