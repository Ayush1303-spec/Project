/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ca.sheridancollege.project;

/**
 *
 * @author ayush
 */
public class HumanPlayer extends Player {

    public HumanPlayer(String name) {
        super(name);
    }

    @Override
    public boolean makeGuess(Card currentCard, Card nextCard, boolean guessHigher) {
        int currentCardValue = ((StandardCard) currentCard).getValue();
        int nextCardValue = ((StandardCard) nextCard).getValue();

        boolean isCorrect = (guessHigher && nextCardValue > currentCardValue) || (!guessHigher && nextCardValue < currentCardValue);
        if (isCorrect) {
            increaseScore();
        }
        return isCorrect;
    }
}
