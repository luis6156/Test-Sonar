package Table;

import Cards.CardConvert;
import Cards.Heroes.hero;
import Cards.Minions.minion;
import Cards.basicCard;
import fileio.CardInput;

import java.awt.image.AreaAveragingScaleFilter;
import java.util.ArrayList;


public class Table {
    private ArrayList<basicCard> cardsInHand1 = new ArrayList<basicCard>();
    private ArrayList<basicCard> cardsInHand2 = new ArrayList<basicCard>();

    public ArrayList<minion>[] game = new ArrayList[4];
    private ArrayList<CardInput> deck1;
    private ArrayList<CardInput> deck2;
    protected int currentRound;
    protected int mana1;
    protected int mana2;
    private int nrCardsDeck1;
    private int nrCardsDeck2;
    protected int startingPlayer;
    private int seed;
    private int currentTurn;
    private hero hero1;
    private hero hero2;

    public int getHeroHasDied() {
        return heroHasDied;
    }

    public void setHeroHasDied(int heroHasDied) {
        this.heroHasDied = heroHasDied;
    }

    private int heroHasDied;

    public int getPlayerOneWins() {
        return playerOneWins;
    }

    public void setPlayerOneWins(int playerOneWins) {
        this.playerOneWins = playerOneWins;
    }

    public int getPlayerTwoWins() {
        return playerTwoWins;
    }

    public void setPlayerTwoWins(int playerTwoWins) {
        this.playerTwoWins = playerTwoWins;
    }

    public int getTotalGames() {
        return TotalGames;
    }

    public void setTotalGames(int totalGames) {
        TotalGames = totalGames;
    }

    private int playerOneWins;
    private int playerTwoWins;
    private int TotalGames;

    public int getNoTanks1() {
        return noTanks1;
    }

    public void setNoTanks1(int noTanks1) {
        this.noTanks1 = noTanks1;
    }

    public int getNoTanks2() {
        return noTanks2;
    }

    public void setNoTanks2(int noTanks2) {
        this.noTanks2 = noTanks2;
    }

    private int noTanks1;
    private int noTanks2;




    public static void construct(ArrayList<minion>[] game){
        for(int i = 0; i < 4; i++){
            game[i] = new ArrayList<minion>();
        }
    }
    public int getCurrentRound() {
        return currentRound;
    }
    public void setCurrentRound(int currentRound) {
        this.currentRound = currentRound;
    }
    public int getMana1() {
        return mana1;
    }
    public void setMana1(int mana1) {
        this.mana1 = mana1;
    }
    public int getMana2() {
        return mana2;
    }
    public void setMana2(int mana2) {
        this.mana2 = mana2;
    }
    public int getNrCardsDeck1() {
        return nrCardsDeck1;
    }
    public void setNrCardsDeck1(int nrCardsDeck1) {
        this.nrCardsDeck1 = nrCardsDeck1;
    }
    public int getNrCardsDeck2() {
        return nrCardsDeck2;
    }
    public void setNrCardsDeck2(int nrCardsDeck2) {
        this.nrCardsDeck2 = nrCardsDeck2;
    }
    public ArrayList<CardInput> getDeck1() {
        return deck1;
    }
    public void setDeck1(ArrayList<CardInput> deck1) {
        this.deck1 = deck1;
    }
    public ArrayList<CardInput> getDeck2() {
        return deck2;
    }
    public void setDeck2(ArrayList<CardInput> deck2) {
        this.deck2 = deck2;
    }
    public int getCurrentTurn() {
        return currentTurn;
    }
    public int getStartingPlayer() {
        return startingPlayer;
    }
    public void setStartingPlayer(int startingPlayer) {
        this.startingPlayer = startingPlayer;
    }
    public void setCurrentTurn(int currentTurn) {
        this.currentTurn = currentTurn;
    }
    public hero getHero1() {
        return hero1;
    }
    public void setHero1(hero hero1) {
        this.hero1 = hero1;
    }
    public hero getHero2() {
        return hero2;
    }
    public void setHero2(hero hero2) {
        this.hero2 = hero2;
    }
    public ArrayList<basicCard> getCardsInHand1() {
        return cardsInHand1;
    }
    public void setCardsInHand1(ArrayList<basicCard> cardsInHand1) {
        this.cardsInHand1 = cardsInHand1;
    }
    public ArrayList<basicCard> getCardsInHand2() {
        return cardsInHand2;
    }
    public void setCardsInHand2(ArrayList<basicCard> cardsInHand2) {
        this.cardsInHand2 = cardsInHand2;
    }
    public int getSeed() {
        return seed;
    }
    public void setSeed(int seed) {
        this.seed = seed;
    }
}
