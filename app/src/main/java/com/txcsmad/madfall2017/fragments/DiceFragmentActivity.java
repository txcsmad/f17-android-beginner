package com.txcsmad.madfall2017.fragments;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.txcsmad.madfall2017.R;

public class DiceFragmentActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dice_fragment);

        // This view id is only in the portrait layout version.
        boolean isPortraitLayout = findViewById(R.id.container_dice) != null;

        if (isPortraitLayout) {
            // Don't need to re-add the fragment if the activity has already been created.
            if (savedInstanceState != null) return;

            DiceFragment diceFragment = new DiceFragment();
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container_dice, diceFragment)
                    .commit();
        } else {
            // Must be a landscape layout, need to set a description on the description fragment.
            DescriptionFragment descriptionFragment = (DescriptionFragment) getSupportFragmentManager().findFragmentById(R.id.fragment_dice_desc);

            String descDice = "Scarne's Dice: Lorem ipsum dolor sit amet....";
            Bundle args = new Bundle();
            args.putString(DescriptionFragment.EXTRA_DESC, descDice);
            descriptionFragment.setArguments(args);
        }
    }
}
