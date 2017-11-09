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
import java.util.Collections;
import java.util.List;

public class FeedActivity extends AppCompatActivity {
    private DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("data");
    private ArrayList<String> urls, captions, users, timestamps;
    private FeedAdapter adapter;

    private ValueEventListener eventListener = new ValueEventListener() {
        @Override
        public void onDataChange(DataSnapshot dataSnapshot) {
            refresh();
            for (DataSnapshot timeData : dataSnapshot.getChildren()) {
                timestamps.add(timeData.getKey());
                for (DataSnapshot userData : timeData.getChildren()) {
                    String value = userData.getValue(String.class);
                    if (userData.getKey().equals("caption"))
                        captions.add(value);
                    else if (userData.getKey().equals("url"))
                        urls.add(value);
                    else
                        users.add(value);
                }
            }
            Collections.reverse(users);
            Collections.reverse(timestamps);
            Collections.reverse(urls);
            Collections.reverse(captions);
            adapter.notifyDataSetChanged();
        }

        @Override
        public void onCancelled(DatabaseError databaseError) {

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed);

        urls = new ArrayList<>();
        captions = new ArrayList<>();
        users = new ArrayList<>();
        timestamps = new ArrayList<>();

        setUpRecyclerView();
    }

    private void refresh() {
        urls.clear();
        captions.clear();
        timestamps.clear();
        users.clear();
    }

    private void setUpRecyclerView() {
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.feed_recycler_view);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new FeedAdapter(this, captions, urls, timestamps, users);
        recyclerView.setAdapter(adapter);
    }

    @Override
    protected void onPause() {
        super.onPause();
        databaseReference.removeEventListener(eventListener);
    }

    @Override
    protected void onResume() {
        super.onResume();
        refresh();
        databaseReference.addValueEventListener(eventListener);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_item, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.compose)
            startActivity(new Intent(this, ComposeActivity.class));

        return super.onOptionsItemSelected(item);
    }

    public class FeedHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView usernameTextview;
        TextView captionTextview;
        TextView timeTextview;

        public FeedHolder(View itemView) {
            super(itemView);
            imageView = (ImageView) itemView.findViewById(R.id.image_preview);
            usernameTextview = (TextView) itemView.findViewById(R.id.username_textview);
            captionTextview = (TextView) itemView.findViewById(R.id.caption_textview);
            timeTextview = (TextView) itemView.findViewById(R.id.time_stamp);
        }
    }

    public class FeedAdapter extends RecyclerView.Adapter<FeedHolder> {
        private List<String> urls, captions, users, timestamps;
        private Context context;

        public FeedAdapter(Context context, List<String> captions, List<String> urls,
                           List<String> timestamps, List<String> users) {
            this.context = context;
            this.urls = urls;
            this.captions = captions;
            this.timestamps = timestamps;
            this.users = users;
        }

        @Override
        public FeedHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
            View rootView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.feed_item, null);
            return new FeedHolder(rootView);
        }

        @Override
        public void onBindViewHolder(FeedHolder feedHolder, int i) {
            feedHolder.timeTextview.setText(timestamps.get(i));
            feedHolder.captionTextview.setText(captions.get(i));
            feedHolder.usernameTextview.setText(users.get(i));
            Picasso.with(context).load(urls.get(i)).into(feedHolder.imageView);
        }

        @Override
        public int getItemCount() {
            return users.size();
        }
    }
}
