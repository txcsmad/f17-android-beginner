package com.txcsmad.madfall2017.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.txcsmad.madfall2017.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Jacob on 11/28/17.
 */

public class DescriptionFragment extends Fragment {

    /**
     * The extra key for the bundle which accesses the text to display in the fragment.
     */
    public static final String EXTRA_DESC = ".fragments.DescriptionFragment.EXTRA_DESC";

    /**
     * Pseudo-constructor for creating fragment programatically.
     */
    public static DescriptionFragment newInstance(String description) {
        DescriptionFragment descriptionFragment = new DescriptionFragment();

        Bundle args = new Bundle();
        args.putString(EXTRA_DESC, description);

        descriptionFragment.setArguments(args);

        return descriptionFragment;
    }

    @BindView(R.id.desc_text) TextView descriptionText;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_description, container, false);

        ButterKnife.bind(this, view);

        return view;
    }

    @Override
    public void onStart() {
        super.onStart();

        Bundle bundle = getArguments();
        String desc = bundle.getString(EXTRA_DESC);

        descriptionText.setText(desc);
    }
}
