package com.txcsmad.madfall2017;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.Arrays;
import java.util.List;

public class ScheduleActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule);

        Context context = this;

        List daysList = Arrays.asList("Sunday", "Monday", "Tuesday",
                                        "Wednesday", "Thursday", "Friday",
                                        "Saturday");

        ListView scheduleList = (ListView) findViewById(R.id.schedule_list);
        ArrayAdapter adapter = new ArrayAdapter(context, R.layout.list_item,
                                                R.id.item_text, daysList);

        scheduleList.setAdapter(adapter);
    }
}
