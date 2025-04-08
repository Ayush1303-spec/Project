package ca.sheridancollege.project;

/**
 * A class that models each Player in the game. Players have an identifier, which should be unique.
 * Players also have a score that tracks their correct guesses.
 *
 * @author dancye
 * @author Paul Bonenfant Jan 2020
 */
public abstract class Player {

    private String name;
    private int score;

    // Constructor initializes the player's name and score
    public Player(String name) {
        this.name = name;
        this.score = 0;
    }

    public String getName() {
        return name;
    }

    public int getScore() {
        return score;
    }

    public void increaseScore() {
    score++;
}

public void increaseScore(int points) {
    score += points;
}



    // Handle the guess for higher or lower
    public boolean makeGuess(Card currentCard, Card nextCard, boolean guessHigher) {
        int currentCardValue = currentCard.getValue(); // No need for casting, assuming Card has getValue method
        int nextCardValue = nextCard.getValue(); // No need for casting

        boolean isCorrect = (guessHigher && nextCardValue > currentCardValue) || (!guessHigher && nextCardValue < currentCardValue);
        if (isCorrect) {
            increaseScore();
        }
        return isCorrect;
    }
}
