/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ca.sheridancollege.project;

import java.util.Scanner;

/**
 *
 * @author ayush
 */
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Ask for player names
        System.out.print("Enter Player 1 name: ");
        String player1Name = scanner.nextLine();

        System.out.print("Enter Player 2 name: ");
        String player2Name = scanner.nextLine();

        // Create two players
        Player player1 = new HumanPlayer(player1Name);  // First player
        Player player2 = new HumanPlayer(player2Name);  // Second player

        // Create and start the game
        HighOrLowerGame game = new HighOrLowerGame("High or Lower Card Game", player1, player2);
        game.play();
        game.declareWinner();
    }
}