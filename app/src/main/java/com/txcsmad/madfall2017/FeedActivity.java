package com.txcsmad.madfall2017;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class FeedActivity extends AppCompatActivity {

    private DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("data");
    private ArrayList<String> urls = new ArrayList<>();
    private ArrayList<String> captions = new ArrayList<>();
    private ArrayList<String> users = new ArrayList<>();
    private ArrayList<String> timestamps = new ArrayList<>();
    private FeedAdapter adapter;

    private ValueEventListener eventListener = new ValueEventListener() {
        @Override
        public void onDataChange(DataSnapshot dataSnapshot) {
            refresh();



            for (DataSnapshot timeData : dataSnapshot.getChildren()) {
                timestamps.add(timeData.getKey().replace('\\', '/'));
                for (DataSnapshot userData : timeData.getChildren()) {
                    String value = userData.getValue(String.class);
                    if (userData.getKey().equals("caption")) {
                        captions.add(value);
                    } else if (userData.getKey().equals("url")) {
                        urls.add(value);
                    } else {
                        users.add(value);
                    }
                }
            }

            Collections.reverse(urls);
            Collections.reverse(captions);
            Collections.reverse(users);
            Collections.reverse(timestamps);

            adapter.notifyDataSetChanged(); // This tells the adapter that it has new data.
        }

        @Override
        public void onCancelled(DatabaseError databaseError) {

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed);

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.feed_recycler_view);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);

        this.adapter = new FeedAdapter(this, this.captions, this.urls, this.timestamps,
                this.users);
        recyclerView.setAdapter(this.adapter);
    }

    private void refresh() {
        urls.clear();
        captions.clear();
        users.clear();
        timestamps.clear();
    }

    @Override
    protected void onPause() {
        super.onPause();
        databaseReference.removeEventListener(this.eventListener);
    }

    @Override
    protected void onResume() {
        super.onResume();
        refresh();
        databaseReference.addValueEventListener(this.eventListener);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_item, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.compose) {
            startActivity(new Intent(this, ComposeActivity.class));
        }
        return super.onOptionsItemSelected(item);
    }

    private static class FeedAdapter extends RecyclerView.Adapter<FeedHolder> {

        private Context context;
        private List<String> urls, captions, users, timestamps;

        public FeedAdapter(Context context, List<String> captions, List<String> urls,
                           List<String> timestamps, List<String> users) {
            this.context = context;
            this.captions = captions;
            this.urls = urls;
            this.timestamps = timestamps;
            this.users = users;
        }

        @Override
        public FeedHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
            View root = LayoutInflater.from(this.context)
                    .inflate(R.layout.feed_item, null);
            return new FeedHolder(root);
        }

        @Override
        public void onBindViewHolder(FeedHolder feedHolder, int i) {
            String url = urls.get(i);
            String caption = captions.get(i);
            String timestamp = timestamps.get(i);
            String user = users.get(i);

            feedHolder.captionTextView.setText(caption);
            feedHolder.timeTextView.setText(timestamp);
            feedHolder.userNameTextView.setText(user);

            Picasso.with(this.context)
                    .load(url)
                    .into(feedHolder.imageView);
        }

        @Override
        public int getItemCount() {
            return users.size();
        }
    }

    private static class FeedHolder extends RecyclerView.ViewHolder {

        ImageView imageView;
        TextView userNameTextView;
        TextView captionTextView;
        TextView timeTextView;

        public FeedHolder(View itemView) {
            super(itemView);

            imageView = (ImageView) itemView.findViewById(R.id.image_preview);
            userNameTextView = (TextView) itemView.findViewById(R.id.username_textview);
            captionTextView = (TextView) itemView.findViewById(R.id.caption_textview);
            timeTextView = (TextView) itemView.findViewById(R.id.time_stamp);
        }
    }
}
