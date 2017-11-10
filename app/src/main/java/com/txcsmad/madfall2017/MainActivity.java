package com.txcsmad.madfall2017;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.IntDef;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.lang.annotation.Retention;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static java.lang.annotation.RetentionPolicy.SOURCE;

public class MainActivity extends AppCompatActivity {

    @Retention(SOURCE)
    @IntDef ({DICE, SCHEDULE, KITTENS, MADGRAM})
    private  @interface AppActivity { }
    private static final int DICE = 0;
    private static final int SCHEDULE = 1;
    private static final int KITTENS = 2;
    private static final int MADGRAM = 3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        List<String> textList = Arrays.asList(
                "Scarnes Dice",
                "Schedule",
                "Infinite Kittens",
                "MADGram");

        ListView appList = (ListView) findViewById(R.id.app_listview);
        final Context context = this;
        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                                    context,
                                    R.layout.list_item,
                                    R.id.item_text,
                                    textList);
        appList.setAdapter(adapter);
        appList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, @AppActivity int i, long l) {
                launchActivity(i);
            }
        });

    }

    private void launchActivity(@AppActivity int activity) {
        Intent intent = null;
        switch (activity) {
            case DICE:
                intent = new Intent(this, DiceActivity.class);
                break;
            case SCHEDULE:
                intent = new Intent(this, ScheduleActivity.class);
                break;
            case KITTENS:
                intent = new Intent(this, InfiniteKittensActivity.class);
                break;
            case MADGRAM:
                intent = new Intent(this, FeedActivity.class);
                break;
        }
        startActivity(intent);
    }
}
