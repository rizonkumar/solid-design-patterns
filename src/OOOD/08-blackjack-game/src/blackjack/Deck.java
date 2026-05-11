package blackjack;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class Deck {
    int nextCardIndex = 0;
    List<Card> cards;

    // Constructor initializes the deck
    public Deck() {
        initializeDeck();
    }

    // Initializes the deck with all cards
    private void initializeDeck() {
        cards = new ArrayList<>();
        for (Suit suit : Suit.values()) {
            for (Rank rank : Rank.values()) {
                cards.add(new Card(rank, suit));
            }
        }
        nextCardIndex = 0; // Reset to start drawing from the first card
    }

    // Shuffles the deck using current time as seed
    public void shuffle() {
        Collections.shuffle(cards, new Random(System.currentTimeMillis()));
    }

    // Draws the next card from the deck
    public Card draw() {
        if (isEmpty() || nextCardIndex >= cards.size()) {
            throw new IllegalStateException("No more cards in deck");
        }
        Card drawCard = cards.get(nextCardIndex);
        nextCardIndex++;
        return drawCard;
    }

    // Returns the number of remaining cards in the deck
    public int getRemainingCardCount() {
        return cards.size() - nextCardIndex;
    }

    // Checks if the deck is empty
    public boolean isEmpty() {
        return getRemainingCardCount() == 0;
    }

    // Resets the deck to start drawing from the beginning
    public void reset() {
        nextCardIndex = 0;
    }
}
