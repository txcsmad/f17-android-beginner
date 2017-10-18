package com.txcsmad.madfall2017;

import android.content.Context;
import android.support.v4.view.LayoutInflaterCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

public class InfiniteKittensActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_infinite_kittens);


        setupRecyclerView();
    }

    private void setupRecyclerView() {
        RecyclerView kittens = (RecyclerView) findViewById(R.id.kitten_recycler_view);
        kittens.setHasFixedSize(true);

        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);

        kittens.setLayoutManager(llm);

        KittenAdapter kittenAdapter = new KittenAdapter(10000);
        kittens.setAdapter(kittenAdapter);
    }


    public static class KittenHolder extends RecyclerView.ViewHolder {

        ImageView kittenImageView;

        public KittenHolder(View itemView) {
            super(itemView);
            kittenImageView = (ImageView) itemView.findViewById(R.id.kitten_image_view);
        }
    }

    public static class KittenAdapter extends RecyclerView.Adapter<KittenHolder> {

        private int size;

        public KittenAdapter(int size) {
            this.size = size;
        }

        @Override
        public KittenHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
            LayoutInflater layoutInflater  = LayoutInflater.from(viewGroup.getContext());
            View kittenView = layoutInflater.inflate(R.layout.kitten_view, null);
            return new KittenHolder(kittenView);
        }

        @Override
        public void onBindViewHolder(KittenHolder kittenHolder, int i) {
            String s1 = "http://thecatapi.com/api/images/get?format=src&type=png";
            String s2 = "http://theoldreader.com/kittens/1200/720";

            String kittenLink = i % 2 == 0 ? s1 : s2;

            Context context = kittenHolder.itemView.getContext();
            Picasso.with(context)
                    .load(kittenLink)
                    .into(kittenHolder.kittenImageView);
        }

        @Override
        public int getItemCount() {
            return size;
        }
    }

}
