package com.txcsmad.madfall2017;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        List<String> textList = Arrays.asList("Scarnes Dice",
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
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = null;
                switch (i) {
                    case 0:
                        intent = new Intent(context, DiceActivity.class);
                        break;
                    case 1:
                        intent = new Intent(context, ScheduleActivity.class);
                        break;
                    case 2:
                        intent = new Intent(context, InfiniteKittensActivity.class);
                        break;
                    case 3:
                        intent = new Intent(context, FeedActivity.class);
                        break;
                    default:
                        intent = new Intent(context, DiceActivity.class);
                }
                startActivity(intent);
            }
        });

    }
}
