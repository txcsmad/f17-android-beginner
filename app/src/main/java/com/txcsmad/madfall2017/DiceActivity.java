package com.txcsmad.madfall2017;

import android.graphics.drawable.Drawable;
import android.support.annotation.DrawableRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class DiceActivity extends AppCompatActivity {

    private ImageView imageDice;
    private TextView playerTextview;
    private TextView computerTextview;
    private TextView pointsTextvew;

    private GameObject gameObject;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dice);

        imageDice = (ImageView) findViewById(R.id.image_dice);

        playerTextview = (TextView) findViewById(R.id.player_textview);
        computerTextview = (TextView) findViewById(R.id.computer_textview);
        pointsTextvew = (TextView) findViewById(R.id.points_textview);

        gameObject = new GameObject(this);
    }

    @DrawableRes
    private int getDiceImage(int number) {
        switch (number) {
            case 1: return R.drawable.dice1;
            case 2: return R.drawable.dice2;
            case 3: return R.drawable.dice3;
            case 4: return R.drawable.dice4;
            case 5: return R.drawable.dice5;
            case 6: return R.drawable.dice6;

            default: return R.drawable.dice1;
        }
    }

    public void rollDice(View view) {
        gameObject.roll();
        updateUI();
    }

    public void holdDice(View view) {
        gameObject.hold();
        updateUI();
    }

    public void resetGame(View view) {
        gameObject.reset();
        updateUI();
    }

    public void updateUI() {
        int playerScore = gameObject.getPlayerScore();
        int computerScore = gameObject.getComputerScore();
        int points = gameObject.getPoints();
        int roll = gameObject.getRoll();

        Drawable image = getResources()
                        .getDrawable(getDiceImage(roll));

        imageDice.setImageDrawable(image);

        playerTextview.setText("Player: " + playerScore);
        computerTextview.setText("Computer: " + computerScore);
        pointsTextvew.setText("Points in store: " + points);

        if (playerScore >= 100) {
            Toast.makeText(this, "Player won!", Toast.LENGTH_SHORT).show();
            resetGame(null);
        }
        else if (computerScore >= 100) {
            Toast.makeText(this, "Computer won!", Toast.LENGTH_SHORT).show();
            resetGame(null);
        }
    }
}
