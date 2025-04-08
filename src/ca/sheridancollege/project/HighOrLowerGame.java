package ca.sheridancollege.project;

import java.util.Scanner;

public class HighOrLowerGame extends Game {
    private final GroupOfCards deck;
    private final Player player1;
    private final Player player2;
    private Player currentPlayer;

    private int player1Streak = 0;
    private int player2Streak = 0;

    public HighOrLowerGame(String name, Player player1, Player player2) {
        super(name);
        this.player1 = player1;
        this.player2 = player2;
        this.currentPlayer = player1;
        deck = new GroupOfCards();
    }

    private void switchPlayer() {
        currentPlayer = (currentPlayer == player1) ? player2 : player1;
    }

@Override
public void play() {
    deck.shuffle();
    Scanner scanner = new Scanner(System.in);
    int rounds = 4;
    Card currentCard = deck.drawCard();
    int round = 1;

    System.out.println("Welcome to " + getName() + "!");
    System.out.println("Player 1: " + player1.getName());
    System.out.println("Player 2: " + player2.getName());

    while (round <= rounds) {
        if (currentCard == null) {
            System.out.println("Deck is empty. Reshuffling...");
            deck.resetDeck();
            currentCard = deck.drawCard();
        }

        Card nextCard = deck.drawCard();

        System.out.println("\nRound " + round + ":");
        System.out.println("Current card: " + currentCard);

        // Player 1 guess
        boolean guessPlayer1Correct = makePlayerGuess(player1, currentCard, nextCard, scanner, player1Streak >= 2);
        if (guessPlayer1Correct) {
            player1Streak++;
            if (player1Streak == 3) {
                // Win on third consecutive guess → double points
                int doubledPoints = player1.getScore();
                player1.increaseScore(doubledPoints);
                System.out.println(player1.getName() + " earned DOUBLE POINTS for a 3-win streak!");
            } else {
                player1.increaseScore(1);
            }
        } else {
            player1Streak = 0;
        }

        // Player 2 guess
        boolean guessPlayer2Correct = makePlayerGuess(player2, currentCard, nextCard, scanner, player2Streak >= 2);
        if (guessPlayer2Correct) {
            player2Streak++;
            if (player2Streak == 3) {
                int doubledPoints = player2.getScore();
                player2.increaseScore(doubledPoints);
                System.out.println(player2.getName() + " earned DOUBLE POINTS for a 3-win streak!");
            } else {
                player2.increaseScore(1);
            }
        } else {
            player2Streak = 0;
        }

        System.out.println("Next card: " + nextCard);
        System.out.println(player1.getName() + " guessed: " + (guessPlayer1Correct ? "Correct" : "Incorrect"));
        System.out.println(player2.getName() + " guessed: " + (guessPlayer2Correct ? "Correct" : "Incorrect"));

        System.out.println("Scores after Round " + round + ":");
        System.out.println(player1.getName() + " score: " + player1.getScore());
        System.out.println(player2.getName() + " score: " + player2.getScore());

        currentCard = nextCard;
        round++;
        switchPlayer();
    }

    System.out.println("\nGame Over! Final Scores:");
    System.out.println(player1.getName() + " score: " + player1.getScore() + " | " + player2.getName() + " score: " + player2.getScore());
    scanner.close();
}



    private boolean makePlayerGuess(Player player, Card currentCard, Card nextCard, Scanner scanner, boolean showProbability) {
        int currentVal = ((StandardCard) currentCard).getValue();

        // Probability Meter shown only if streak >= 2
        if (showProbability) {
            int higherCount = 0;
            int lowerCount = 0;
            int equalCount = 0;
            for (Card c : deck.getRemainingCards()) {
                int val = ((StandardCard) c).getValue();
                if (val > currentVal) higherCount++;
                else if (val < currentVal) lowerCount++;
                else equalCount++;
            }

            int totalRemaining = higherCount + lowerCount + equalCount;
            double higherProb = (totalRemaining == 0) ? 0 : (higherCount * 100.0 / totalRemaining);
            double lowerProb = (totalRemaining == 0) ? 0 : (lowerCount * 100.0 / totalRemaining);

            System.out.printf("%s, Probability Meter (Streak Bonus Unlocked):\n", player.getName());
            System.out.printf("Chance of Higher: %.2f%%\n", higherProb);
            System.out.printf("Chance of Lower : %.2f%%\n", lowerProb);
        }

        System.out.print(player.getName() + ", will the next card be Higher or Lower? (H/L): ");
        String guess = scanner.nextLine().toUpperCase();
        boolean guessHigher = guess.equals("H");

        int nextVal = ((StandardCard) nextCard).getValue();
        return (guessHigher && nextVal > currentVal) || (!guessHigher && nextVal < currentVal);
    }

    @Override
    public void declareWinner() {
        System.out.println("Game over! Final scores:");
        System.out.println(player1.getName() + " score: " + player1.getScore() + " | " + player2.getName() + " score: " + player2.getScore());
    }
}
