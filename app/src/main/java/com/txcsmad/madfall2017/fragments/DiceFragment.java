package com.txcsmad.madfall2017.fragments;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.DrawableRes;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.txcsmad.madfall2017.GameObject;
import com.txcsmad.madfall2017.R;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Jacob on 11/28/17.
 */

public class DiceFragment extends Fragment {

    private GameObject gameObject;

    private ImageView imageDice;
    private TextView playerTextView;
    private TextView computerTextView;
    private TextView pointsTextView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_dice, container, false);

        ButterKnife.bind(this, view);

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        // Setup which depends on the activity that holds the fragment should be done here.

        FragmentActivity activity = getActivity();

        this.gameObject = new GameObject(activity);

        this.imageDice = (ImageView) activity.findViewById(R.id.image_dice);
        this.playerTextView = (TextView) activity.findViewById(R.id.player_textview);
        this.computerTextView = (TextView) activity.findViewById(R.id.computer_textview);
        this.pointsTextView = (TextView) activity.findViewById(R.id.points_textview);
    }


    // Basically copy/paste below this point except for handling clicks in Java instead of in XML.
    // I used ButterKnife because boilerplate code sucks.

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

    @OnClick(R.id.roll_button)
    public void rollDice() {
        gameObject.roll();
        updateUI();
    }

    @OnClick(R.id.hold_button)
    public void holdDice() {
        gameObject.hold();
        updateUI();
    }

    @OnClick(R.id.reset_button)
    public void resetGame() {
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

        playerTextView.setText("Player: " + playerScore);
        computerTextView.setText("Computer: " + computerScore);
        pointsTextView.setText("Points in store: " + points);

        if (playerScore >= 100) {
            Toast.makeText(getActivity(), "Player won!", Toast.LENGTH_SHORT).show();
            resetGame();
        }
        else if (computerScore >= 100) {
            Toast.makeText(getActivity(), "Computer won!", Toast.LENGTH_SHORT).show();
            resetGame();
        }
    }
}
