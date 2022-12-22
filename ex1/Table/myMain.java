package Table;

import Cards.CardConvert;
import Cards.DeleteCard;
import Cards.Enviroment.Firestorm;
import Cards.Enviroment.Heart_Hound;
import Cards.Enviroment.Winterfell;
import Cards.Enviroment.enviroment;
import Cards.Heroes.*;
import Cards.Minions.minion;
import Cards.basicCard;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import fileio.ActionsInput;
import fileio.CardInput;
import fileio.GameInput;
import fileio.Input;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;


public final class myMain {
    public static void GameLogic(Input inputData, ArrayNode output){
        Table table = new Table();
        table.setTotalGames(0);
        table.setPlayerOneWins(0);
        table.setPlayerTwoWins(0);
        table.construct(table.game);
        ///multiple games-----
        ////------------------
        for(int i = 0; i < inputData.getGames().size(); i++){
            table.setNrCardsDeck1(inputData.getPlayerOneDecks().getNrCardsInDeck());
            table.setNrCardsDeck2(inputData.getPlayerTwoDecks().getNrCardsInDeck());

            table.setNrCardsDeck1(inputData.getPlayerOneDecks().getNrCardsInDeck());
            table.setNrCardsDeck2(inputData.getPlayerTwoDecks().getNrCardsInDeck());
            GameInput game = inputData.getGames().get(i);

            table.setDeck1(new ArrayList<CardInput>());
            table.setDeck2(new ArrayList<CardInput>());

            table.setDeck1(new ArrayList<CardInput>(inputData.getPlayerOneDecks().getDecks().get(game.getStartGame().getPlayerOneDeckIdx())));
            table.setDeck2(new ArrayList<CardInput>(inputData.getPlayerTwoDecks().getDecks().get(game.getStartGame().getPlayerTwoDeckIdx())));

            table.setSeed(game.getStartGame().getShuffleSeed());
            table.setCurrentTurn(game.getStartGame().getStartingPlayer());

            Collections.shuffle(table.getDeck1(), new Random(table.getSeed()) );
            Collections.shuffle(table.getDeck2(), new Random(table.getSeed()) );

            if(game.getStartGame().getPlayerOneHero().getName().equals("Lord Royce")){
                CardInput heroJSON = game.getStartGame().getPlayerOneHero();
                Lord_Royce hero1 = new Lord_Royce(heroJSON.getMana(),heroJSON.getDescription(),heroJSON.getColors(),heroJSON.getName());
                table.setHero1(hero1);
            }
            else if(game.getStartGame().getPlayerOneHero().getName().equals("Empress Thorina")){
                CardInput heroJSON = game.getStartGame().getPlayerOneHero();
                Empress_Thorina hero1 = new Empress_Thorina(heroJSON.getMana(),heroJSON.getDescription(),heroJSON.getColors(),heroJSON.getName());
                table.setHero1(hero1);
            }
            else if(game.getStartGame().getPlayerOneHero().getName().equals("King Mudface")){
                CardInput heroJSON = game.getStartGame().getPlayerOneHero();
                King_Mudface hero1 = new King_Mudface(heroJSON.getMana(),heroJSON.getDescription(),heroJSON.getColors(),heroJSON.getName());
                table.setHero1(hero1);
            }
            else if(game.getStartGame().getPlayerOneHero().getName().equals("General Kocioraw")){
                CardInput heroJSON = game.getStartGame().getPlayerOneHero();
                General_Kocioraw hero1 = new General_Kocioraw(heroJSON.getMana(),heroJSON.getDescription(),heroJSON.getColors(),heroJSON.getName());
                table.setHero1(hero1);
            }

            if(game.getStartGame().getPlayerTwoHero().getName().equals("Lord Royce")){
                CardInput heroJSON = game.getStartGame().getPlayerTwoHero();
                Lord_Royce hero2 = new Lord_Royce(heroJSON.getMana(),heroJSON.getDescription(),heroJSON.getColors(),heroJSON.getName());
                table.setHero2(hero2);
            }
            else if(game.getStartGame().getPlayerTwoHero().getName().equals("Empress Thorina")){
                CardInput heroJSON = game.getStartGame().getPlayerTwoHero();
                Empress_Thorina hero2 = new Empress_Thorina(heroJSON.getMana(),heroJSON.getDescription(),heroJSON.getColors(),heroJSON.getName());
                table.setHero2(hero2);
            }
            else if(game.getStartGame().getPlayerTwoHero().getName().equals("King Mudface")){
                CardInput heroJSON = game.getStartGame().getPlayerTwoHero();
                King_Mudface hero2 = new King_Mudface(heroJSON.getMana(),heroJSON.getDescription(),heroJSON.getColors(),heroJSON.getName());
                table.setHero2(hero2);
            }
            else if(game.getStartGame().getPlayerTwoHero().getName().equals("General Kocioraw")){
                CardInput heroJSON = game.getStartGame().getPlayerTwoHero();
                General_Kocioraw hero2 = new General_Kocioraw(heroJSON.getMana(),heroJSON.getDescription(),heroJSON.getColors(),heroJSON.getName());
                table.setHero2(hero2);
            }
            table.getHero1().setHasAttacked(0);
            table.getHero2().setHasAttacked(0);
            table.setMana1(1);
            table.setMana2(1);
            table.setCurrentRound(1);
            basicCard aux = CardConvert.convert(table.getDeck1().get(0));
            table.getDeck1().remove(0);
            table.setNrCardsDeck1(table.getNrCardsDeck1() - 1);
            if(aux instanceof minion)
                ((minion) aux).setAlreadyAttacked(0);
            while(table.getCardsInHand1().size() != 0)
                table.getCardsInHand1().remove(0);
            table.getCardsInHand1().add(aux);

            aux = CardConvert.convert(table.getDeck2().get(0));
            table.getDeck2().remove(0);
            table.setNrCardsDeck2(table.getNrCardsDeck2() - 1);
            if(aux instanceof minion)
                ((minion) aux).setAlreadyAttacked(0);

            while(table.getCardsInHand2().size() != 0)
                table.getCardsInHand2().remove(0);
            table.getCardsInHand2().add(aux);
            table.setStartingPlayer(table.getCurrentTurn());
            table.setHeroHasDied(0);
            Table.construct(table.game);
            for(ActionsInput action : game.getActions()){
                runAction(action, table, output);
            }
        }
    }

    public static void runAction(ActionsInput action,Table table, ArrayNode output) {
        if(action.getCommand().equals("endPlayerTurn")) {
            if(table.getHeroHasDied() == 1) return;
             basicCard aux;
             table.getHero2().setHasAttacked(0);
             table.getHero1().setHasAttacked(0);
            if(table.getCurrentTurn() == 1 ) {
                for(minion card: table.game[2]){
                    card.setFreeze(0);
                    card.setAlreadyAttacked(0);
                }
                for(minion card: table.game[3]){
                    card.setFreeze(0);
                    card.setAlreadyAttacked(0);
                }


                table.setCurrentTurn(2);
                if(table.getStartingPlayer() == table.getCurrentTurn()) {
                    table.setCurrentRound(table.getCurrentRound() + 1);
                    if(table.getCurrentRound() <= 10){
                        table.setMana2(table.getMana2() + table.getCurrentRound());
                        table.setMana1(table.getMana1() + table.getCurrentRound());
                    }
                    else{
                        table.setMana2(table.getMana2() + 10);
                        table.setMana1(table.getMana2() + 10);
                    }
                }
            }
            else if(table.getCurrentTurn() == 2) {
                for(minion card: table.game[0]){
                    card.setFreeze(0);
                    card.setAlreadyAttacked(0);
                }
                for(minion card: table.game[1]){
                    card.setFreeze(0);
                    card.setAlreadyAttacked(0);
                }


                table.setCurrentTurn(1);
                if(table.getStartingPlayer() == table.getCurrentTurn()) {
                    table.setCurrentRound(table.getCurrentRound() + 1);
                    if(table.getCurrentRound() <= 10){
                        table.setMana2(table.getMana2() + table.getCurrentRound());
                        table.setMana1(table.getMana1() + table.getCurrentRound());
                    }
                    else{
                        table.setMana2(table.getMana2() + 10);
                        table.setMana1(table.getMana2() + 10);
                    }
                }
            }
            if(table.getStartingPlayer() == table.getCurrentTurn()){

                if(table.getDeck2().size() != 0) {
                    aux = CardConvert.convert(table.getDeck2().get(0));
                    if(aux instanceof minion)
                        ((minion) aux).setAlreadyAttacked(0);
                    table.getCardsInHand2().add(aux);
                    table.getDeck2().remove(0);
                    table.setNrCardsDeck2(table.getNrCardsDeck2() - 1);
                }

                if(table.getDeck1().size() != 0) {
                    aux = CardConvert.convert(table.getDeck1().get(0));
                    if(aux instanceof minion)
                        ((minion) aux).setAlreadyAttacked(0);
                    table.getCardsInHand1().add(aux);
                    table.getDeck1().remove(0);
                    table.setNrCardsDeck1(table.getNrCardsDeck1() - 1);
                }
            }

        }
        if(action.getCommand().equals("getPlayerDeck")){
            if(action.getPlayerIdx() == 1) {
                ObjectNode newNode = output.addObject();
                newNode.put("command", "getPlayerDeck");
                newNode.put("playerIdx", action.getPlayerIdx());
                ArrayNode newArrayNode = newNode.putArray("output");
                for(CardInput card: table.getDeck1()){
                    ObjectNode cardNode = newArrayNode.addObject();
                    cardNode.put("mana", card.getMana());
                    basicCard aux = CardConvert.convert(card);
                    if(aux instanceof minion){
                        cardNode.put("attackDamage", card.getAttackDamage());
                        cardNode.put("health", card.getHealth());
                    }
                    cardNode.put("description", card.getDescription());
                    ArrayNode newArrayNode1 = cardNode.putArray("colors");
                    for(String color: card.getColors()){
                        newArrayNode1.add(color);
                    }
                    cardNode.put("name", card.getName());
                }
            }
            else if(action.getPlayerIdx() == 2){
                ObjectNode newNode = output.addObject();
                newNode.put("command", "getPlayerDeck");
                newNode.put("playerIdx", action.getPlayerIdx());
                ArrayNode newArrayNode = newNode.putArray("output");
                for(CardInput card: table.getDeck2()){
                    ObjectNode cardNode = newArrayNode.addObject();
                    cardNode.put("mana", card.getMana());
                    basicCard aux = CardConvert.convert(card);
                    if(aux instanceof minion){
                        cardNode.put("attackDamage", card.getAttackDamage());
                        cardNode.put("health", card.getHealth());
                    }
                    cardNode.put("description", card.getDescription());
                    ArrayNode newArrayNode1 = cardNode.putArray("colors");
                    for(String color: card.getColors()){
                        newArrayNode1.add(color);
                    }
                    cardNode.put("name", card.getName());

                }
            }
        }
        if(action.getCommand().equals("getPlayerHero")) {
            if(action.getPlayerIdx() == 1) {
                ObjectNode newNode = output.addObject();
                newNode.put("command", "getPlayerHero");
                newNode.put("playerIdx", action.getPlayerIdx());
                ObjectNode newArrayNode = newNode.putObject("output");
                newArrayNode.put("mana", table.getHero1().getMana());
                newArrayNode.put("description", table.getHero1().getDescription());
                ArrayNode newArrayNode1 = newArrayNode.putArray("colors");
                for(String color: table.getHero1().getColors()){
                    newArrayNode1.add(color);
                }
                newArrayNode.put("name", table.getHero1().getName());
                newArrayNode.put("health", table.getHero1().getHealth());
            }

            else if(action.getPlayerIdx() == 2){
                ObjectNode newNode = output.addObject();
                newNode.put("command", "getPlayerHero");
                newNode.put("playerIdx", action.getPlayerIdx());
                ObjectNode newArrayNode = newNode.putObject("output");
                newArrayNode.put("mana", table.getHero2().getMana());
                newArrayNode.put("description", table.getHero2().getDescription());
                ArrayNode newArrayNode1 = newArrayNode.putArray("colors");
                for(String color: table.getHero2().getColors()){
                    newArrayNode1.add(color);
                }
                newArrayNode.put("name", table.getHero2().getName());
                newArrayNode.put("health", table.getHero2().getHealth());
            }
        }
        if(action.getCommand().equals("getPlayerTurn")) {
            ObjectNode newNode = output.addObject();
            newNode.put("command", "getPlayerTurn");
            newNode.put("output", table.getCurrentTurn());
        }
        if(action.getCommand().equals("placeCard")){
            if(table.getHeroHasDied() == 1) return;
            if(table.getCurrentTurn() == 1 && table.getCardsInHand1().size() - 1 >= action.getHandIdx()) {
                basicCard aux = table.getCardsInHand1().get(action.getHandIdx());
                if(aux instanceof minion) {
                    ((minion) aux).setFreeze(0);
                    if(table.getMana1() - aux.getMana() >= 0) {
                        if(((minion) aux).getRow() == 0 && table.game[3].size() < 5) {
                            if(aux.getName().equals("Goliath") || aux.getName().equals("Warden")){
                                table.setNoTanks1(table.getNoTanks1() + 1);
                            }
                            table.game[3].add((minion) aux);
                            table.getCardsInHand1().remove(action.getHandIdx());
                            table.setMana1(table.getMana1() - aux.getMana());
                        }
                        else if(((minion) aux).getRow() == 1 && table.game[2].size() < 5){
                            if(aux.getName().equals("Goliath") || aux.getName().equals("Warden")){
                                table.setNoTanks1(table.getNoTanks1() + 1);
                            }
                            table.game[2].add((minion) aux);
                            table.getCardsInHand1().remove(action.getHandIdx());
                            table.setMana1(table.getMana1() - aux.getMana());
                        }
                        else {
                            ObjectNode newNode = output.addObject();
                            newNode.put("command", "placeCard");
                            newNode.put("handIdx", action.getHandIdx());
                            newNode.put("error","Cannot place card on table since row is full.");
                        }
                    }
                    else {
                        ObjectNode newNode = output.addObject();
                        newNode.put("command", "placeCard");
                        newNode.put("handIdx", action.getHandIdx());
                        newNode.put("error","Not enough mana to place card on table.");
                    }

                }
                else {
                    ObjectNode newNode = output.addObject();
                    newNode.put("command", "placeCard");
                    newNode.put("handIdx", action.getHandIdx());
                    newNode.put("error","Cannot place environment card on table.");
                }
            }
            else if(table.getCurrentTurn() == 2 && table.getCardsInHand2().size() - 1 >= action.getHandIdx()) {
                basicCard aux = table.getCardsInHand2().get(action.getHandIdx());
                if(aux instanceof minion) {
                    ((minion) aux).setFreeze(0);
                    if(table.getMana2() - aux.getMana() >= 0){
                        if(((minion) aux).getRow() == 0 && table.game[0].size() < 5) {
                            if(aux.getName().equals("Goliath") || aux.getName().equals("Warden")){
                                table.setNoTanks2(table.getNoTanks2() + 1);
                            }
                            table.game[0].add((minion) aux);
                            table.getCardsInHand2().remove(action.getHandIdx());
                            table.setMana2(table.getMana2() - aux.getMana());
                        }
                        else if(((minion) aux).getRow() == 1 && table.game[1].size() < 5){
                            if(aux.getName().equals("Goliath") || aux.getName().equals("Warden")){
                                table.setNoTanks2(table.getNoTanks2() + 1);
                            }
                            table.game[1].add((minion) aux);
                            table.getCardsInHand2().remove(action.getHandIdx());
                            table.setMana2(table.getMana2() - aux.getMana());
                        }
                        else {
                            ObjectNode newNode = output.addObject();
                            newNode.put("command", "placeCard");
                            newNode.put("handIdx", action.getHandIdx());
                            newNode.put("error","Cannot place card on table since row is full.");
                        }
                    }
                    else {
                        ObjectNode newNode = output.addObject();
                        newNode.put("command", "placeCard");
                        newNode.put("handIdx", action.getHandIdx());
                        newNode.put("error","Not enough mana to place card on table.");
                    }
                }
                else {
                    ObjectNode newNode = output.addObject();
                    newNode.put("command", "placeCard");
                    newNode.put("handIdx", action.getHandIdx());
                    newNode.put("error","Cannot place environment card on table.");
                }
            }
        }
        if(action.getCommand().equals("getCardsInHand")){
            if(action.getPlayerIdx() == 1) {
                ObjectNode newNode = output.addObject();
                newNode.put("command", "getCardsInHand");
                newNode.put("playerIdx", action.getPlayerIdx());
                ArrayNode newArrayNode = newNode.putArray("output");
                for(basicCard card: table.getCardsInHand1()){
                    ObjectNode cardNode = newArrayNode.addObject();
                    cardNode.put("mana", card.getMana());
                    if(card instanceof minion){
                        cardNode.put("attackDamage", ((minion) card).getAttackDamage());
                        cardNode.put("health", ((minion) card).getHealth());
                    }
                    cardNode.put("description", card.getDescription());
                    ArrayNode newArrayNode1 = cardNode.putArray("colors");
                    for(String color: card.getColors()){
                        newArrayNode1.add(color);
                    }
                    cardNode.put("name", card.getName());
                }
            }
            else if(action.getPlayerIdx() == 2){
                ObjectNode newNode = output.addObject();
                newNode.put("command", "getCardsInHand");
                newNode.put("playerIdx", action.getPlayerIdx());
                ArrayNode newArrayNode = newNode.putArray("output");
                for(basicCard card: table.getCardsInHand2()){
                    ObjectNode cardNode = newArrayNode.addObject();
                    cardNode.put("mana", card.getMana());
                    if(card instanceof minion){
                        cardNode.put("attackDamage", ((minion) card).getAttackDamage());
                        cardNode.put("health", ((minion) card).getHealth());
                    }
                    cardNode.put("description", card.getDescription());
                    ArrayNode newArrayNode1 = cardNode.putArray("colors");
                    for(String color: card.getColors()){
                        newArrayNode1.add(color);
                    }
                    cardNode.put("name", card.getName());
                }
            }
        }
        if(action.getCommand().equals("getPlayerMana")) {
            if(action.getPlayerIdx() == 1) {
                ObjectNode newNode = output.addObject();
                newNode.put("command", "getPlayerMana");
                newNode.put("playerIdx", action.getPlayerIdx());
                newNode.put("output", table.getMana1());
            }
            else if(action.getPlayerIdx() == 2) {
                ObjectNode newNode = output.addObject();
                newNode.put("command", "getPlayerMana");
                newNode.put("playerIdx", action.getPlayerIdx());
                newNode.put("output", table.getMana2());
            }
        }
        if(action.getCommand().equals("getCardsOnTable")) {
            ObjectNode newNode = output.addObject();
            newNode.put("command", "getCardsOnTable");
            ArrayNode newArrayNode = newNode.putArray("output");

            for (int i = 0; i < 4; i++) {
                ArrayNode rowArray = newArrayNode.addArray();
                for (basicCard card : table.game[i]) {

                    ObjectNode cardNode = rowArray.addObject();
                    cardNode.put("mana", card.getMana());
                    if (card instanceof minion) {
                        cardNode.put("attackDamage", ((minion) card).getAttackDamage());
                        cardNode.put("health", ((minion) card).getHealth());
                    }
                    cardNode.put("description", card.getDescription());
                    ArrayNode newArrayNode1 = cardNode.putArray("colors");
                    for (String color : card.getColors()) {
                        newArrayNode1.add(color);
                    }
                    cardNode.put("name", card.getName());
                }
            }
        }
        if(action.getCommand().equals("getCardAtPosition")){
            ObjectNode newNode = output.addObject();
            newNode.put("command", "getCardAtPosition");
            if(!(action.getY() >=0 && action.getY() <= table.game[action.getX()].size() - 1)){
                newNode.put("output", "No card available at that position.");
                newNode.put("x", action.getX());
                newNode.put("y", action.getY());
                return;
            }
            newNode.put("x", action.getX());
            newNode.put("y", action.getY());
            if(action.getX() >= 0 && action.getX() <= 3) {
                if(action.getY() >=0 && action.getY() <= table.game[action.getX()].size() - 1) {
                    basicCard card = table.game[action.getX()].get(action.getY());
                    ObjectNode cardNode = newNode.putObject("output");
                    cardNode.put("mana", card.getMana());
                    if(card instanceof minion){
                        cardNode.put("attackDamage", ((minion) card).getAttackDamage());
                        cardNode.put("health", ((minion) card).getHealth());
                    }
                    cardNode.put("description", card.getDescription());
                    ArrayNode newArrayNode1 = cardNode.putArray("colors");
                    for(String color: card.getColors()){
                        newArrayNode1.add(color);
                    }
                    cardNode.put("name", card.getName());
                }
            }
        }
        if(action.getCommand().equals("getEnvironmentCardsInHand")) {
            if(action.getPlayerIdx() == 1) {
                ObjectNode newNode = output.addObject();
                newNode.put("command", "getEnvironmentCardsInHand");
                newNode.put("playerIdx", action.getPlayerIdx());
                ArrayNode newArrayNode = newNode.putArray("output");
                for(basicCard card: table.getCardsInHand1()){
                    if(card instanceof enviroment){
                        ObjectNode cardNode = newArrayNode.addObject();
                        cardNode.put("mana", card.getMana());
                        cardNode.put("description", card.getDescription());
                        ArrayNode newArrayNode1 = cardNode.putArray("colors");
                        for(String color: card.getColors()){
                            newArrayNode1.add(color);
                        }
                        cardNode.put("name", card.getName());
                    }
                }
            }
            else if(action.getPlayerIdx() == 2){
                ObjectNode newNode = output.addObject();
                newNode.put("command", "getEnvironmentCardsInHand");
                newNode.put("playerIdx", action.getPlayerIdx());
                ArrayNode newArrayNode = newNode.putArray("output");
                for(basicCard card: table.getCardsInHand2()){
                    if(card instanceof enviroment){
                        ObjectNode cardNode = newArrayNode.addObject();
                        cardNode.put("mana", card.getMana());
                        cardNode.put("description", card.getDescription());
                        ArrayNode newArrayNode1 = cardNode.putArray("colors");
                        for(String color: card.getColors()){
                            newArrayNode1.add(color);
                        }
                        cardNode.put("name", card.getName());
                    }
                }
            }
        }
        if(action.getCommand().equals("useEnvironmentCard")) {
            if(table.getHeroHasDied() == 1) return;
            if(table.getCurrentTurn() == 1 && table.getCardsInHand1().size() - 1 >= action.getHandIdx()){
                basicCard aux = table.getCardsInHand1().get(action.getHandIdx());
                if(aux instanceof enviroment) {
                    if(table.getMana1() >= aux.getMana()) {
                        if(action.getAffectedRow() == 0 || action.getAffectedRow() == 1) {
                            if(aux.getName().equals("Firestorm")) {
                                Firestorm card = (Firestorm) aux;
                                card.special(table.game[action.getAffectedRow()], null);
                                table.getCardsInHand1().remove(action.getHandIdx());
                                table.setMana1(table.getMana1() - aux.getMana());
                            }
                            else if(aux.getName().equals("Winterfell")) {
                                Winterfell card = (Winterfell) aux;
                                card.special(table.game[action.getAffectedRow()], null);
                                table.getCardsInHand1().remove(action.getHandIdx());
                                table.setMana1(table.getMana1() - aux.getMana());
                            }
                            else if(aux.getName().equals("Heart Hound")){
                                Heart_Hound card = (Heart_Hound) aux;
                                if(action.getAffectedRow() == 0) {
                                    if(table.game[3].size() < 5)
                                        card.special(table.game[action.getAffectedRow()], table.game[3]);
                                    else {
                                        ObjectNode newNode = output.addObject();
                                        newNode.put("command", "useEnvironmentCard");
                                        newNode.put("handIdx", action.getHandIdx());
                                        newNode.put("affectedRow", action.getAffectedRow());
                                        newNode.put("error","Cannot steal enemy card since the player's row is full.");
                                    }
                                }
                                else if(action.getAffectedRow() == 1) {
                                    if(table.game[2].size() < 5)
                                        card.special(table.game[action.getAffectedRow()], table.game[2]);
                                    else {
                                        ObjectNode newNode = output.addObject();
                                        newNode.put("command", "useEnvironmentCard");
                                        newNode.put("handIdx", action.getHandIdx());
                                        newNode.put("affectedRow", action.getAffectedRow());
                                        newNode.put("error","Cannot steal enemy card since the player's row is full.");
                                    }
                                }
                                table.getCardsInHand1().remove(action.getHandIdx());
                                table.setMana1(table.getMana1() - aux.getMana());
                            }
                        }
                        else {
                            ObjectNode newNode = output.addObject();
                            newNode.put("command", "useEnvironmentCard");
                            newNode.put("handIdx", action.getHandIdx());
                            newNode.put("affectedRow", action.getAffectedRow());
                            newNode.put("error","Chosen row does not belong to the enemy.");
                        }
                    }
                    else {
                        ObjectNode newNode = output.addObject();
                        newNode.put("command", "useEnvironmentCard");
                        newNode.put("handIdx", action.getHandIdx());
                        newNode.put("affectedRow", action.getAffectedRow());
                        newNode.put("error","Not enough mana to use environment card.");
                    }
                }
                else {
                    ObjectNode newNode = output.addObject();
                    newNode.put("command", "useEnvironmentCard");
                    newNode.put("handIdx", action.getHandIdx());
                    newNode.put("affectedRow", action.getAffectedRow());
                    newNode.put("error","Chosen card is not of type environment.");
                }
            }
            else if(table.getCurrentTurn() == 2 && table.getCardsInHand2().size() - 1 >= action.getHandIdx()){
                basicCard aux = table.getCardsInHand2().get(action.getHandIdx());
                if(aux instanceof enviroment) {
                    if(table.getMana2() >= aux.getMana()) {
                        if(action.getAffectedRow() == 2 || action.getAffectedRow() == 3) {
                            if(aux.getName().equals("Firestorm")) {
                                Firestorm card = (Firestorm) aux;
                                card.special(table.game[action.getAffectedRow()], null);
                                table.getCardsInHand2().remove(action.getHandIdx());
                                table.setMana2(table.getMana2() - aux.getMana());
                            }
                            else if(aux.getName().equals("Winterfell")) {
                                Winterfell card = (Winterfell) aux;
                                card.special(table.game[action.getAffectedRow()], null);
                                table.getCardsInHand2().remove(action.getHandIdx());
                                table.setMana2(table.getMana2() - aux.getMana());
                            }
                            else if(aux.getName().equals("Heart Hound")){
                                Heart_Hound card = (Heart_Hound) aux;
                                if(action.getAffectedRow() == 2) {
                                    if(table.game[1].size() < 5){
                                        card.special(table.game[action.getAffectedRow()], table.game[1]);
                                        table.getCardsInHand2().remove(action.getHandIdx());
                                        table.setMana2(table.getMana2() - aux.getMana());
                                    }

                                    else {
                                        ObjectNode newNode = output.addObject();
                                        newNode.put("command", "useEnvironmentCard");
                                        newNode.put("handIdx", action.getHandIdx());
                                        newNode.put("affectedRow", action.getAffectedRow());
                                        newNode.put("error","Cannot steal enemy card since the player's row is full.");
                                    }
                                }
                                else if(action.getAffectedRow() == 3) {
                                    if(table.game[0].size() < 5) {
                                        card.special(table.game[action.getAffectedRow()], table.game[0]);
                                        table.getCardsInHand2().remove(action.getHandIdx());
                                        table.setMana2(table.getMana2() - aux.getMana());
                                    }
                                    else {
                                        ObjectNode newNode = output.addObject();
                                        newNode.put("command", "useEnvironmentCard");
                                        newNode.put("handIdx", action.getHandIdx());
                                        newNode.put("affectedRow", action.getAffectedRow());
                                        newNode.put("error","Cannot steal enemy card since the player's row is full.");
                                    }
                                }

                            }
                        }
                        else {
                            ObjectNode newNode = output.addObject();
                            newNode.put("command", "useEnvironmentCard");
                            newNode.put("handIdx", action.getHandIdx());
                            newNode.put("affectedRow", action.getAffectedRow());
                            newNode.put("error","Chosen row does not belong to the enemy.");
                        }
                    }
                    else {
                        ObjectNode newNode = output.addObject();
                        newNode.put("command", "useEnvironmentCard");
                        newNode.put("handIdx", action.getHandIdx());
                        newNode.put("affectedRow", action.getAffectedRow());
                        newNode.put("error","Not enough mana to use environment card.");
                    }
                }
                else {
                    ObjectNode newNode = output.addObject();
                    newNode.put("command", "useEnvironmentCard");
                    newNode.put("handIdx", action.getHandIdx());
                    newNode.put("affectedRow", action.getAffectedRow());
                    newNode.put("error","Chosen card is not of type environment.");
                }
            }
        }
        if(action.getCommand().equals("getFrozenCardsOnTable")) {
            ObjectNode newNode = output.addObject();
            newNode.put("command", "getFrozenCardsOnTable");
            ArrayNode newArrayNode = newNode.putArray("output");
            for (int i = 0; i < 4; i++) {
                for (minion card : table.game[i]) {
                    if(card.getFreeze() == 1) {
                        ObjectNode cardNode = newArrayNode.addObject();
                        cardNode.put("mana", card.getMana());
                        if (card instanceof minion) {
                            cardNode.put("attackDamage", ((minion) card).getAttackDamage());
                            cardNode.put("health", ((minion) card).getHealth());
                        }
                        cardNode.put("description", card.getDescription());
                        ArrayNode newArrayNode1 = cardNode.putArray("colors");
                        for (String color : card.getColors()) {
                            newArrayNode1.add(color);
                        }
                        cardNode.put("name", card.getName());
                    }

                }
            }
        }
        if(action.getCommand().equals("cardUsesAttack")){
            if(table.getHeroHasDied() == 1) return;
            if(table.getCurrentTurn() == 1) {
                int x = action.getCardAttacker().getX();
                int y = action.getCardAttacker().getY();
                if(x != 3 &&  x!= 2){
                    ObjectNode newNode = output.addObject();
                    newNode.put("command", "cardUsesAttack");
                    ObjectNode positionNode1 = newNode.putObject("cardAttacker");
                    positionNode1.put("x", action.getCardAttacker().getX());
                    positionNode1.put("y", action.getCardAttacker().getY());
                    ObjectNode positionNode2 = newNode.putObject("cardAttacked");
                    positionNode2.put("x", action.getCardAttacked().getX());
                    positionNode2.put("y", action.getCardAttacked().getY());
                    newNode.put("error","Attacked card does not belong to the enemy.");
                    return;
                }
                if(x == 3 && y > table.game[x].size()-1) return;
                if(x == 2 && y > table.game[x].size()-1) return;
                if(y < 0 ) return;
                x = action.getCardAttacked().getX();
                y = action.getCardAttacked().getY();
                if(x != 0 &&  x!= 1) {
                    ObjectNode newNode = output.addObject();
                    newNode.put("command", "cardUsesAttack");
                    ObjectNode positionNode1 = newNode.putObject("cardAttacker");
                    positionNode1.put("x", action.getCardAttacker().getX());
                    positionNode1.put("y", action.getCardAttacker().getY());
                    ObjectNode positionNode2 = newNode.putObject("cardAttacked");
                    positionNode2.put("x", action.getCardAttacked().getX());
                    positionNode2.put("y", action.getCardAttacked().getY());
                    newNode.put("error","Attacked card does not belong to the enemy.");
                    return;
                }
                if(x == 0 && y > table.game[x].size()-1) return;
                if(x == 1 && y > table.game[x].size()-1) return;
                if(y < 0 ) return;

                minion attacker = table.game[action.getCardAttacker().getX()].get(action.getCardAttacker().getY());
                minion attacked = table.game[action.getCardAttacked().getX()].get(action.getCardAttacked().getY());
                if(attacker.getAlreadyAttacked() == 0) {
                    if(attacker.getFreeze() == 0) {
                        if(table.getNoTanks2() != 0 && !(attacked.getName().equals("Goliath")) && !(attacked.getName().equals("Warden"))){
                            ObjectNode newNode = output.addObject();
                            newNode.put("command", "cardUsesAttack");
                            ObjectNode positionNode1 = newNode.putObject("cardAttacker");
                            positionNode1.put("x", action.getCardAttacker().getX());
                            positionNode1.put("y", action.getCardAttacker().getY());
                            ObjectNode positionNode2 = newNode.putObject("cardAttacked");
                            positionNode2.put("x", action.getCardAttacked().getX());
                            positionNode2.put("y", action.getCardAttacked().getY());
                            newNode.put("error","Attacked card is not of type 'Tank'.");
                            return;
                        }
                        attacked.setHealth(attacked.getHealth() - attacker.getAttackDamage());
                        attacker.setAlreadyAttacked(1);
                        DeleteCard.delete(table.game[action.getCardAttacked().getX()]);
                    }
                    else {
                        ObjectNode newNode = output.addObject();
                        newNode.put("command", "cardUsesAttack");
                        ObjectNode positionNode1 = newNode.putObject("cardAttacker");
                        positionNode1.put("x", action.getCardAttacker().getX());
                        positionNode1.put("y", action.getCardAttacker().getY());
                        ObjectNode positionNode2 = newNode.putObject("cardAttacked");
                        positionNode2.put("x", action.getCardAttacked().getX());
                        positionNode2.put("y", action.getCardAttacked().getY());
                        newNode.put("error","Attacker card is frozen.");
                    }
                }
                else {
                    ObjectNode newNode = output.addObject();
                    newNode.put("command", "cardUsesAttack");
                    ObjectNode positionNode1 = newNode.putObject("cardAttacker");
                    positionNode1.put("x", action.getCardAttacker().getX());
                    positionNode1.put("y", action.getCardAttacker().getY());
                    ObjectNode positionNode2 = newNode.putObject("cardAttacked");
                    positionNode2.put("x", action.getCardAttacked().getX());
                    positionNode2.put("y", action.getCardAttacked().getY());
                    newNode.put("error","Attacker card has already attacked this turn.");
                }
            }
            else if(table.getCurrentTurn() == 2) {
                int x = action.getCardAttacker().getX();
                int y = action.getCardAttacker().getY();
                if(x != 0 &&  x!= 1){
                    ObjectNode newNode = output.addObject();
                    newNode.put("command", "cardUsesAttack");
                    ObjectNode positionNode1 = newNode.putObject("cardAttacker");
                    positionNode1.put("x", action.getCardAttacker().getX());
                    positionNode1.put("y", action.getCardAttacker().getY());
                    ObjectNode positionNode2 = newNode.putObject("cardAttacked");
                    positionNode2.put("x", action.getCardAttacked().getX());
                    positionNode2.put("y", action.getCardAttacked().getY());
                    newNode.put("error","Attacked card does not belong to the enemy.");
                    return;
                }
                if(x == 0 && y > table.game[x].size()-1) return;
                if(x == 1 && y > table.game[x].size()-1) return;
                if(y < 0 ) return;
                x = action.getCardAttacked().getX();
                y = action.getCardAttacked().getY();
                if(x != 2 &&  x!= 3){
                    ObjectNode newNode = output.addObject();
                    newNode.put("command", "cardUsesAttack");
                    ObjectNode positionNode1 = newNode.putObject("cardAttacker");
                    positionNode1.put("x", action.getCardAttacker().getX());
                    positionNode1.put("y", action.getCardAttacker().getY());
                    ObjectNode positionNode2 = newNode.putObject("cardAttacked");
                    positionNode2.put("x", action.getCardAttacked().getX());
                    positionNode2.put("y", action.getCardAttacked().getY());
                    newNode.put("error","Attacked card does not belong to the enemy.");
                    return;
                }
                if(x == 2 && y > table.game[x].size()-1) return;
                if(x == 3 && y > table.game[x].size()-1) return;
                if(y < 0 ) return;

                minion attacker = table.game[action.getCardAttacker().getX()].get(action.getCardAttacker().getY());
                minion attacked = table.game[action.getCardAttacked().getX()].get(action.getCardAttacked().getY());
                if(attacker.getAlreadyAttacked() == 0) {
                    if(attacker.getFreeze() == 0) {
                        if(table.getNoTanks1() != 0 && !(attacked.getName().equals("Goliath")) && !(attacked.getName().equals("Warden"))){
                            ObjectNode newNode = output.addObject();
                            newNode.put("command", "cardUsesAttack");
                            ObjectNode positionNode1 = newNode.putObject("cardAttacker");
                            positionNode1.put("x", action.getCardAttacker().getX());
                            positionNode1.put("y", action.getCardAttacker().getY());
                            ObjectNode positionNode2 = newNode.putObject("cardAttacked");
                            positionNode2.put("x", action.getCardAttacked().getX());
                            positionNode2.put("y", action.getCardAttacked().getY());
                            newNode.put("error","Attacked card is not of type 'Tank'.");
                            return;
                        }
                        attacked.setHealth(attacked.getHealth() - attacker.getAttackDamage());
                        attacker.setAlreadyAttacked(1);
                        DeleteCard.delete(table.game[action.getCardAttacked().getX()]);
                    }
                    else {
                        ObjectNode newNode = output.addObject();
                        newNode.put("command", "cardUsesAttack");
                        ObjectNode positionNode1 = newNode.putObject("cardAttacker");
                        positionNode1.put("x", action.getCardAttacker().getX());
                        positionNode1.put("y", action.getCardAttacker().getY());
                        ObjectNode positionNode2 = newNode.putObject("cardAttacked");
                        positionNode2.put("x", action.getCardAttacked().getX());
                        positionNode2.put("y", action.getCardAttacked().getY());
                        newNode.put("error","Attacker card is frozen.");
                    }
                }
                else {
                    ObjectNode newNode = output.addObject();
                    newNode.put("command", "cardUsesAttack");
                    ObjectNode positionNode1 = newNode.putObject("cardAttacker");
                    positionNode1.put("x", action.getCardAttacker().getX());
                    positionNode1.put("y", action.getCardAttacker().getY());
                    ObjectNode positionNode2 = newNode.putObject("cardAttacked");
                    positionNode2.put("x", action.getCardAttacked().getX());
                    positionNode2.put("y", action.getCardAttacked().getY());
                    newNode.put("error","Attacker card has already attacked this turn.");
                }
            }
        }
        if(action.getCommand().equals("cardUsesAbility")){
            if(table.getHeroHasDied() == 1) return;
            int x = action.getCardAttacker().getX();
            int y = action.getCardAttacker().getY();
            //if(x != 3 &&  x!= 2) return;
            if(y > table.game[x].size()-1) return;
            if(y < 0 ) return;

            x = action.getCardAttacked().getX();
            y = action.getCardAttacked().getY();
            //if(x != 0 &&  x!= 1) return;
            if(y > table.game[x].size()-1) return;
            if(y < 0 ) return;

            minion attacker = table.game[action.getCardAttacker().getX()].get(action.getCardAttacker().getY());
            minion attacked = table.game[action.getCardAttacked().getX()].get(action.getCardAttacked().getY());

            if(attacker.getFreeze() == 1){
                ObjectNode newNode = output.addObject();
                newNode.put("command", "cardUsesAbility");
                ObjectNode positionNode1 = newNode.putObject("cardAttacker");
                positionNode1.put("x", action.getCardAttacker().getX());
                positionNode1.put("y", action.getCardAttacker().getY());
                ObjectNode positionNode2 = newNode.putObject("cardAttacked");
                positionNode2.put("x", action.getCardAttacked().getX());
                positionNode2.put("y", action.getCardAttacked().getY());
                newNode.put("error","Attacker card is frozen.");
                return;
            }
            if(attacker.getAlreadyAttacked() == 1){
                ObjectNode newNode = output.addObject();
                newNode.put("command", "cardUsesAbility");
                ObjectNode positionNode1 = newNode.putObject("cardAttacker");
                positionNode1.put("x", action.getCardAttacker().getX());
                positionNode1.put("y", action.getCardAttacker().getY());
                ObjectNode positionNode2 = newNode.putObject("cardAttacked");
                positionNode2.put("x", action.getCardAttacked().getX());
                positionNode2.put("y", action.getCardAttacked().getY());
                newNode.put("error","Attacker card has already attacked this turn.");
                return;
            }
            if(table.getCurrentTurn() == 1){
                if(attacker.getName().equals("Disciple") && (x == 0 || x == 1)){
                    ObjectNode newNode = output.addObject();
                    newNode.put("command", "cardUsesAbility");
                    ObjectNode positionNode1 = newNode.putObject("cardAttacker");
                    positionNode1.put("x", action.getCardAttacker().getX());
                    positionNode1.put("y", action.getCardAttacker().getY());
                    ObjectNode positionNode2 = newNode.putObject("cardAttacked");
                    positionNode2.put("x", action.getCardAttacked().getX());
                    positionNode2.put("y", action.getCardAttacked().getY());
                    newNode.put("error","Attacked card does not belong to the current player.");
                    return;
                }
                String nume = attacker.getName();
                x = action.getCardAttacked().getX();
                if((nume.equals("The Ripper") || nume.equals("Miraj") || nume.equals("The Cursed One")) && (x == 3 || x == 2)){
                    ObjectNode newNode = output.addObject();
                    newNode.put("command", "cardUsesAbility");
                    ObjectNode positionNode1 = newNode.putObject("cardAttacker");
                    positionNode1.put("x", action.getCardAttacker().getX());
                    positionNode1.put("y", action.getCardAttacker().getY());
                    ObjectNode positionNode2 = newNode.putObject("cardAttacked");
                    positionNode2.put("x", action.getCardAttacked().getX());
                    positionNode2.put("y", action.getCardAttacked().getY());
                    newNode.put("error","Attacked card does not belong to the enemy.");
                    return;
                }
                if(!(attacker.getName().equals("Disciple")) && table.getNoTanks2() != 0 && !(attacked.getName().equals("Goliath")) && !(attacked.getName().equals("Warden"))){
                    ObjectNode newNode = output.addObject();
                    newNode.put("command", "cardUsesAbility");
                    ObjectNode positionNode1 = newNode.putObject("cardAttacker");
                    positionNode1.put("x", action.getCardAttacker().getX());
                    positionNode1.put("y", action.getCardAttacker().getY());
                    ObjectNode positionNode2 = newNode.putObject("cardAttacked");
                    positionNode2.put("x", action.getCardAttacked().getX());
                    positionNode2.put("y", action.getCardAttacked().getY());
                    newNode.put("error","Attacked card is not of type 'Tank'.");
                    return;
                }
                attacker.special(attacked);
                attacker.setAlreadyAttacked(1);
                DeleteCard.delete(table.game[action.getCardAttacked().getX()]);
            }
            else if(table.getCurrentTurn() == 2){
                if(attacker.getName().equals("Disciple") && (x == 2 || x == 3)){
                    ObjectNode newNode = output.addObject();
                    newNode.put("command", "cardUsesAbility");
                    ObjectNode positionNode1 = newNode.putObject("cardAttacker");
                    positionNode1.put("x", action.getCardAttacker().getX());
                    positionNode1.put("y", action.getCardAttacker().getY());
                    ObjectNode positionNode2 = newNode.putObject("cardAttacked");
                    positionNode2.put("x", action.getCardAttacked().getX());
                    positionNode2.put("y", action.getCardAttacked().getY());
                    newNode.put("error","Attacked card does not belong to the current player.");
                    return;
                }
                String nume = attacker.getName();
                if((nume.equals("The Ripper") || nume.equals("Miraj") || nume.equals("The Cursed One")) && (x == 1 || x == 0)){
                    ObjectNode newNode = output.addObject();
                    newNode.put("command", "cardUsesAbility");
                    ObjectNode positionNode1 = newNode.putObject("cardAttacker");
                    positionNode1.put("x", action.getCardAttacker().getX());
                    positionNode1.put("y", action.getCardAttacker().getY());
                    ObjectNode positionNode2 = newNode.putObject("cardAttacked");
                    positionNode2.put("x", action.getCardAttacked().getX());
                    positionNode2.put("y", action.getCardAttacked().getY());
                    newNode.put("error","Attacked card does not belong to the enemy.");
                    return;
                }
                if(!(attacker.getName().equals("Disciple")) && table.getNoTanks1() != 0 && !(attacked.getName().equals("Goliath")) && !(attacked.getName().equals("Warden"))){
                    ObjectNode newNode = output.addObject();
                    newNode.put("command", "cardUsesAttack");
                    ObjectNode positionNode1 = newNode.putObject("cardAttacker");
                    positionNode1.put("x", action.getCardAttacker().getX());
                    positionNode1.put("y", action.getCardAttacker().getY());
                    ObjectNode positionNode2 = newNode.putObject("cardAttacked");
                    positionNode2.put("x", action.getCardAttacked().getX());
                    positionNode2.put("y", action.getCardAttacked().getY());
                    newNode.put("error","Attacked card is not of type 'Tank'.");
                    return;
                }
                attacker.special(attacked);
                attacker.setAlreadyAttacked(1);
                DeleteCard.delete(table.game[action.getCardAttacked().getX()]);
            }
        }
        if(action.getCommand().equals("useAttackHero")){
            int x = action.getCardAttacker().getX();
            int y = action.getCardAttacker().getY();
            //if(x != 3 &&  x!= 2) return;
            if(y > table.game[x].size()-1) return;
            if(y < 0 ) return;

            minion attacker = table.game[action.getCardAttacker().getX()].get(action.getCardAttacker().getY());

            if(attacker.getFreeze() == 1) {
                ObjectNode newNode = output.addObject();
                newNode.put("command", "useAttackHero");
                ObjectNode positionNode1 = newNode.putObject("cardAttacker");
                positionNode1.put("x", action.getCardAttacker().getX());
                positionNode1.put("y", action.getCardAttacker().getY());
                newNode.put("error","Attacker card is frozen.");
                return;
            }
            if(attacker.getAlreadyAttacked() == 1){
                ObjectNode newNode = output.addObject();
                newNode.put("command", "useAttackHero");
                ObjectNode positionNode1 = newNode.putObject("cardAttacker");
                positionNode1.put("x", action.getCardAttacker().getX());
                positionNode1.put("y", action.getCardAttacker().getY());
                newNode.put("error","Attacker card has already attacked this turn.");
                return;
            }
            if(table.getCurrentTurn() == 1){
                if(table.getNoTanks2() != 0){
                    ObjectNode newNode = output.addObject();
                    newNode.put("command", "useAttackHero");
                    ObjectNode positionNode1 = newNode.putObject("cardAttacker");
                    positionNode1.put("x", action.getCardAttacker().getX());
                    positionNode1.put("y", action.getCardAttacker().getY());
                    newNode.put("error","Attacked card is not of type 'Tank'.");
                    return;
                }
                table.getHero2().setHealth(table.getHero2().getHealth() - attacker.getAttackDamage());
                attacker.setAlreadyAttacked(1);
                if(table.getHero2().getHealth() <= 0) {
                    ObjectNode newNode = output.addObject();
                    newNode.put("gameEnded", "Player one killed the enemy hero.");
                    table.setHeroHasDied(1);
                    table.setPlayerOneWins(table.getPlayerOneWins() + 1);
                    table.setTotalGames(table.getTotalGames() + 1);
                }
            }
            else if(table.getCurrentTurn() == 2){
                if(table.getNoTanks1() != 0){
                    ObjectNode newNode = output.addObject();
                    newNode.put("command", "useAttackHero");
                    ObjectNode positionNode1 = newNode.putObject("cardAttacker");
                    positionNode1.put("x", action.getCardAttacker().getX());
                    positionNode1.put("y", action.getCardAttacker().getY());
                    newNode.put("error","Attacked card is not of type 'Tank'.");
                    return;
                }
                table.getHero1().setHealth(table.getHero1().getHealth() - attacker.getAttackDamage());
                attacker.setAlreadyAttacked(1);
                if(table.getHero1().getHealth() <= 0) {
                    ObjectNode newNode = output.addObject();
                    newNode.put("gameEnded", "Player two killed the enemy hero.");
                    table.setHeroHasDied(1);
                    table.setPlayerTwoWins(table.getPlayerTwoWins() + 1);
                    table.setTotalGames(table.getTotalGames() + 1);
                }
            }
        }
        if(action.getCommand().equals("useHeroAbility")){
            if(table.getCurrentTurn() == 1) {
                hero hero = table.getHero1();
                if(table.getMana1() - hero.getMana() < 0) {
                    ObjectNode newNode = output.addObject();
                    newNode.put("command", "useHeroAbility");
                    newNode.put("affectedRow", action.getAffectedRow());
                    newNode.put("error", "Not enough mana to use hero's ability.");
                    return;
                }
                if(hero.getHasAttacked() == 1) {
                    ObjectNode newNode = output.addObject();
                    newNode.put("command", "useHeroAbility");
                    newNode.put("affectedRow", action.getAffectedRow());
                    newNode.put("error", "Hero has already attacked this turn.");
                    return;
                }
                if((hero.getName().equals("Lord Royce") || hero.getName().equals("Empress Thorina")) && (action.getAffectedRow() == 2 || action.getAffectedRow() == 3)){
                    ObjectNode newNode = output.addObject();
                    newNode.put("command", "useHeroAbility");
                    newNode.put("affectedRow", action.getAffectedRow());
                    newNode.put("error", "Selected row does not belong to the enemy.");
                    return;
                }
                if((hero.getName().equals("General Kocioraw") || hero.getName().equals("King Mudface")) && (action.getAffectedRow() == 0 || action.getAffectedRow() == 1)){
                    ObjectNode newNode = output.addObject();
                    newNode.put("command", "useHeroAbility");
                    newNode.put("affectedRow", action.getAffectedRow());
                    newNode.put("error", "Selected row does not belong to the current player.");
                    return;
                }
                hero.special(table.game[action.getAffectedRow()]);
                hero.setHasAttacked(1);
                table.setMana1(table.getMana1() - hero.getMana());
                DeleteCard.delete(table.game[action.getAffectedRow()]);
            }
            else if(table.getCurrentTurn() == 2) {
                hero hero = table.getHero2();
                if(table.getMana2() - hero.getMana() < 0) {
                    ObjectNode newNode = output.addObject();
                    newNode.put("command", "useHeroAbility");
                    newNode.put("affectedRow", action.getAffectedRow());
                    newNode.put("error", "Not enough mana to use hero's ability.");
                    return;
                }
                if(hero.getHasAttacked() == 1) {
                    ObjectNode newNode = output.addObject();
                    newNode.put("command", "useHeroAbility");
                    newNode.put("affectedRow", action.getAffectedRow());
                    newNode.put("error", "Hero has already attacked this turn.");
                    return;
                }
                if((hero.getName().equals("Lord Royce") || hero.getName().equals("Empress Thorina")) && (action.getAffectedRow() == 0 || action.getAffectedRow() == 1)){
                    ObjectNode newNode = output.addObject();
                    newNode.put("command", "useHeroAbility");
                    newNode.put("affectedRow", action.getAffectedRow());
                    newNode.put("error", "Selected row does not belong to the enemy.");
                    return;
                }
                if((hero.getName().equals("General Kocioraw") || hero.getName().equals("King Mudface")) && (action.getAffectedRow() == 2 || action.getAffectedRow() == 3)){
                    ObjectNode newNode = output.addObject();
                    newNode.put("command", "useHeroAbility");
                    newNode.put("affectedRow", action.getAffectedRow());
                    newNode.put("error", "Selected row does not belong to the current player.");
                    return;
                }
                hero.special(table.game[action.getAffectedRow()]);
                hero.setHasAttacked(1);
                table.setMana2(table.getMana2() - hero.getMana());
                DeleteCard.delete(table.game[action.getAffectedRow()]);
            }
        }
        if(action.getCommand().equals("getPlayerOneWins")) {
            ObjectNode newNode = output.addObject();
            newNode.put("command", "getPlayerOneWins");
            newNode.put("output", table.getPlayerOneWins());
        }
        if(action.getCommand().equals("getPlayerTwoWins")) {
            ObjectNode newNode = output.addObject();
            newNode.put("command", "getPlayerTwoWins");
            newNode.put("output", table.getPlayerTwoWins());
        }
        if(action.getCommand().equals("getTotalGamesPlayed")) {
            ObjectNode newNode = output.addObject();
            newNode.put("command", "getTotalGamesPlayed");
            newNode.put("output", table.getTotalGames());
        }
    }
}
