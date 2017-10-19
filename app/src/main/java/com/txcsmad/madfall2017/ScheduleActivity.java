package com.txcsmad.madfall2017;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Arrays;

public class ScheduleActivity extends AppCompatActivity {
    ArrayList<String> days;
    ArrayAdapter<String> adapter;

    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule);

        days = new ArrayList<String>(Arrays.asList("Monday", "Tuesday",
                "Wednesday", "Thursday", "Friday"));

        listView = (ListView) findViewById(R.id.schedule_list);
        adapter = new ArrayAdapter<String>(this, R.layout.list_item, R.id.item_text, days);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String element = (String) listView.getItemAtPosition(i);

                Intent intent = new Intent(ScheduleActivity.this, EventActivity.class);
                intent.putExtra("day", element);
                startActivity(intent);
            }
        });
    }
}
