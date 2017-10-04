package com.txcsmad.madfall2017;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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

        listView = (ListView) findViewById(R.id.schedule_listview);
        adapter = new ArrayAdapter<String>(this, R.layout.app_list_item, R.id.text_view, days);
        listView.setAdapter(adapter);
    }
}
