package com.txcsmad.madfall2017;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by Ali on 9/26/2017.
 */

public class GameObject {
    private int playerScore = 0;
    private int computerScore = 0;
    private int points = 0;
    private int roll = 1;

    private Context context;

    public GameObject(Context context) {
        this.context = context;
    }

    public void roll() {
        roll = (int) (Math.random() * 6) + 1;

        if (roll == 1) {
            points = 0;
            computerTurn();
        }
        else
            this.points += roll;
    }

    public void hold() {
        this.playerScore += this.points;
        this.points = 0;
        computerTurn();
    }

    public void reset() {
        playerScore = 0;
        computerScore = 0;
        points = 0;
    }

    private void computerTurn() {
        int turns = (int) (Math.random()*4) + 3;

        while (turns > 0) {
            roll = (int) (Math.random() * 6) + 1;

            if (roll == 1) {
                points = 0;
                Toast.makeText(context, "Computer rolled a 1!", Toast.LENGTH_SHORT).show();
                break;
            }
            this.points += roll;
            turns--;
        }
        computerScore += points;
        points = 0;
    }

    public int getPlayerScore() {
        return playerScore;
    }

    public int getComputerScore() {
        return computerScore;
    }

    public int getPoints() {
        return points;
    }

    public int getRoll() {
        return roll;
    }
}
