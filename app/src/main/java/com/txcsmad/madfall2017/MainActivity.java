package com.txcsmad.madfall2017;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ArrayList<String> list;
    ArrayAdapter<String> adapter;

    ListView listView;

    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        list = new ArrayList<>();

        list.add("Scarne's Dice");
        list.add("Schedule App");

        listView = (ListView) findViewById(R.id.list_view);

        adapter = new ArrayAdapter<String>(this, R.layout.app_list_item, R.id.text_view, list);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                switch (i) {
                    case 0:
                        intent = new Intent(MainActivity.this, DiceActivity.class);
                        break;
                    case 1:
                        intent = new Intent(MainActivity.this, ScheduleActivity.class);
                        break;

                    default:
                        intent = new Intent(MainActivity.this, DiceActivity.class);
                        break;
                }
                startActivity(intent);
            }
        });
    }
}
