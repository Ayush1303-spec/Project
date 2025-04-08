/**
 * SYST 17796 Project Base code.
 * Students can modify and extend to implement their game.
 * Add your name as an author and the date!
 */
package ca.sheridancollege.project;

import java.util.ArrayList;
import java.util.Collections;

public class GroupOfCards {
    private ArrayList<Card> cards;

    // Constructor to initialize the deck of 52 cards
    public GroupOfCards() {
        cards = new ArrayList<>();
        populateDeck();  // Populate the deck with cards
    }

    // Draws the top card from the deck and removes it
    public Card drawCard() {
        if (cards.size() > 0) {
            return cards.remove(0);  // Remove the first card in the list (top of the deck)
        }
        return null;  
    }

    // Shuffle the deck
    public void shuffle() {
        Collections.shuffle(cards);  // Shuffles the cards randomly
    }

    // Method deck with 52 cards
    private void populateDeck() {
        String[] suits = {"Hearts", "Diamonds", "Clubs", "Spades"};
        String[] ranks = {"2", "3", "4", "5", "6", "7", "8", "9", "10", "Jack", "Queen", "King", "Ace"};

        for (String suit : suits) {
            for (String rank : ranks) {
                cards.add(new StandardCard(suit, rank));  // Add a card to the deck
            }
        }
    }

    // Reset the deck
    public void resetDeck() {
        cards.clear();  // Clear the current deck
        populateDeck();  // Populate the deck again
        shuffle();  // Shuffle the new deck
    }
    public ArrayList<Card> getRemainingCards() {
    return cards;
}

}
