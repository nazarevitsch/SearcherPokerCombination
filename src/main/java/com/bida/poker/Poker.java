package com.bida.poker;

import java.util.ArrayList;
import java.util.List;

public class Poker {

    private List<Card> deckOfCards;
    private List<Player> players;
    private Card[] table;

    public Poker(){
        table = new Card[5];
    }

    public void play(int peopleAmount){
        int counter = 0;
        players = new ArrayList<Player>();
        for (int i = 0; i < peopleAmount; i++){
            players.add(new Player());
        }
        while (true){
            counter++;
            createDeckOfCards();
            for (int i = 0; i < peopleAmount; i++) {
                int index = (int) (Math.random() * deckOfCards.size());
                players.get(i).setFirstCard(deckOfCards.remove(index));
                index = (int) (Math.random() * deckOfCards.size());
                players.get(i).setSecondCard(deckOfCards.remove(index));
            }
            for (int i = 0; i < table.length; i++){
                int index = (int) (Math.random() * deckOfCards.size());
                table[i] = deckOfCards.remove(index);
            }
            if (searchStrit(table, players)) {
                System.out.println("TRY: " + counter);
                printTable(table);
                printPlayersHand(players);
                break;
            }
        }
    }

    private void createDeckOfCards(){
        deckOfCards = new ArrayList<Card>();
        Value[] values = Value.values();
        Suit[] suits = Suit.values();
        for (int i = 0; i < suits.length; i++){
            for( int l = 0; l < values.length; l++){
                deckOfCards.add(new Card(suits[i], values[l]));
            }
        }
    }

    private boolean searchKare(Card[] table, List<Player> players){
        for (int i = 0; i < table.length; i++){
            int counter = 1;
            for (int l = i; l < table.length; l++){
                if (i != l){
                    if (table[i].getValue().getValue() == table[l].getValue().getValue()){
                        counter++;
                    }
                }
            }
            if (counter >= 2) {
                for (int l = 0; l < players.size(); l++) {
                    int playerCounter = counter;
                    if (players.get(l).getFirstCard().getValue().getValue() == table[i].getValue().getValue())
                        playerCounter++;
                    if (players.get(l).getSecondCard().getValue().getValue() == table[i].getValue().getValue())
                        playerCounter++;
                    if (playerCounter == 4) {
                        System.out.println(table[i]);
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private boolean searchStrit(Card[] table, List<Player> players){
        boolean answer = searchSequense(sortDeck(table, null));
        for (int i = 0; i < table.length; i++){
            answer = searchSequense(sortDeck(table, players.get(i)));
        }
        return answer;
    }

    public boolean searchSequense(Card[] cards){
        int count = 0;
        for (int i = cards.length - 1; i > 0; i--){
            if (cards[i].getValue().getValue() - cards[i - 1].getValue().getValue() <= 1) {
                count++;
            }
            else {
                count = 0;
            }
            if (count == 4){
                return true;
            }
        }
        return false;
    }

    private void printPlayersHand(List<Player> players){
        for (Player player : players){
            System.out.print(player + "  ");
        }
    }

    private void printTable(Card[] table){
        System.out.print("Table: {");
        for (Card card : table){
            System.out.print("  " + card);
        }
        System.out.println("  }");
    }

    private Card[] sortDeck(Card[] table, Player player){
        Card[] sortedDeck = null;
        if (player != null){
            sortedDeck = new Card[7];
            for (int i = 0; i < table.length; i++){
                sortedDeck[i] = table[i];
            }
            sortedDeck[5] = player.getFirstCard();
            sortedDeck[6] = player.getSecondCard();
        } else {
            sortedDeck = table;
        }
        for (int i = 0; i < sortedDeck.length; i++){
            for (int l = 0; l < sortedDeck.length - 1 - i; l++){
                if (sortedDeck[l].getValue().getValue() > sortedDeck[l + 1].getValue().getValue()){
                    Card card = sortedDeck[l];
                    sortedDeck[l] = sortedDeck[l + 1];
                    sortedDeck[l + 1] = card;
                }
            }
        }
        return sortedDeck;
    }
}
