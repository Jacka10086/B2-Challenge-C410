package Assignments.Com410.B2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class Card {
    String suit;
    int rank;

    public Card(String suit, int rank) {
        this.suit = suit;
        this.rank = rank;
    }

    @Override
    public String toString() {
        String[] ranks = {"2", "3", "4", "5", "6", "7", "8", "9", "10", "Jack", "Queen", "King", "Ace"};
        return ranks[this.rank - 2] + " of " + this.suit;
    }

    public int compareTo(Card other) {
        return other.rank - this.rank; // Descending order
    }
}

class Player {
    List<Card> hand = new ArrayList<>();

    public void receiveCard(Card card) {
        hand.add(card);
    }

    public void sortHand() {
        Collections.sort(hand, Card::compareTo);
    }

    public Card playCard() {
        return hand.remove(0); // Play the highest card
    }
}

class Cardball {
    Player player1 = new Player();
    Player player2 = new Player();

    public void dealCards() {
        String[] suits = {"Hearts", "Diamonds", "Clubs", "Spades"};
        List<Card> deck = new ArrayList<>();
        for (String suit : suits) {
            for (int rank = 2; rank <= 14; rank++) {
                deck.add(new Card(suit, rank));
            }
        }
        Collections.shuffle(deck);

        for (int i = 0; i < 5; i++) { // Deal 5 cards to each player
            player1.receiveCard(deck.remove(0));
            player2.receiveCard(deck.remove(0));
        }
    }

    public void playGame() {
        player1.sortHand();
        player2.sortHand();
        int score1 = 0, score2 = 0;

        for (int i = 0; i < 5; i++) {
            Card card1 = player1.playCard();
            Card card2 = player2.playCard();

            System.out.println("Player 1: " + card1);
            System.out.println("Player 2: " + card2);

            if (card1.compareTo(card2) > 0) {
                System.out.println("Goal to Player 1!");
                score1++;
            } else if (card1.compareTo(card2) < 0) {
                System.out.println("Goal to Player 2!");
                score2++;
            } else {
                System.out.println("No score");
            }
        }
        System.out.println("\nFINAL SCORE\nPlayer1 " + score1 + " Player2 " + score2);
    }

    public static void main(String[] args) {
        Cardball game = new Cardball();
        game.dealCards();
        game.playGame();
    }
}
