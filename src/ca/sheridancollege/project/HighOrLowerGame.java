package ca.sheridancollege.project;

import java.util.Scanner;

public class HighOrLowerGame extends Game {
    private final GroupOfCards deck;
    private final Player player1;
    private final Player player2;
    private Player currentPlayer;

    private int player1Streak = 0;
    private int player2Streak = 0;

    private int player1LossStreak = 0;
    private int player2LossStreak = 0;

    private boolean player1SkipUsed = false;
    private boolean player2SkipUsed = false;

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
        int rounds = 10;
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

            boolean skipPlayer1 = false;
            boolean skipPlayer2 = false;

            if (player1LossStreak >= 3 && !player1SkipUsed) {
                System.out.println(player1.getName() + " activates SKIP! " + player2.getName() + " will miss this round.");
                player1SkipUsed = true;
                skipPlayer2 = true;
            }

            if (player2LossStreak >= 3 && !player2SkipUsed) {
                System.out.println(player2.getName() + " activates SKIP! " + player1.getName() + " will miss this round.");
                player2SkipUsed = true;
                skipPlayer1 = true;
            }

            boolean guessPlayer1Correct = false;
            boolean guessPlayer2Correct = false;

            // Player 1 turn
            if (!skipPlayer1) {
                guessPlayer1Correct = makePlayerGuess(player1, currentCard, nextCard, scanner, player1Streak >= 2);
                if (guessPlayer1Correct) {
                    player1Streak++;
                    player1LossStreak = 0;
                    if (player1Streak == 3) {
                        int doubledPoints = player1.getScore();
                        player1.increaseScore(doubledPoints);
                        System.out.println(player1.getName() + " earned DOUBLE POINTS for a 3-win streak!");
                    } else {
                        player1.increaseScore(1);
                    }
                } else {
                    player1Streak = 0;
                    player1LossStreak++;
                }
            } else {
                System.out.println(player1.getName() + "'s turn is skipped.");
            }

            // Player 2 turn
            if (!skipPlayer2) {
                guessPlayer2Correct = makePlayerGuess(player2, currentCard, nextCard, scanner, player2Streak >= 2);
                if (guessPlayer2Correct) {
                    player2Streak++;
                    player2LossStreak = 0;
                    if (player2Streak == 3) {
                        int doubledPoints = player2.getScore();
                        player2.increaseScore(doubledPoints);
                        System.out.println(player2.getName() + " earned DOUBLE POINTS for a 3-win streak!");
                    } else {
                        player2.increaseScore(1);
                    }
                } else {
                    player2Streak = 0;
                    player2LossStreak++;
                }
            } else {
                System.out.println(player2.getName() + "'s turn is skipped.");
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

        
    }

    private boolean makePlayerGuess(Player player, Card currentCard, Card nextCard, Scanner scanner, boolean showProbability) {
        int currentVal = ((StandardCard) currentCard).getValue();

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
    int score1 = player1.getScore();
    int score2 = player2.getScore();

    System.out.println("\nGame Over! Final scores:");
    System.out.println(player1.getName() + " score: " + score1 + " | " + player2.getName() + " score: " + score2);

    if (score1 > score2) {
        System.out.println( player1.getName() + " is the WINNER! Well played!");
    } else if (score2 > score1) {
        System.out.println(player2.getName() + " takes the victory! Amazing game!");
    } else {
        System.out.println("It's a TIE! What a close match! Well played both!");
    }
}

}
