/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ca.sheridancollege.project;

/**
 *
 * @author ayush
 */
public abstract class Game {
    private String name;
    private Player player;

    // Constructor
    public Game(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public Player getPlayer() {
        return player;
    }

    // Abstract methods
    public abstract void play();
    public abstract void declareWinner();
}