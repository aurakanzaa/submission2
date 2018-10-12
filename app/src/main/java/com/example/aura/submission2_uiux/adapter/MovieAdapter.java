package com.example.aura.submission2_uiux.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.aura.submission2_uiux.BuildConfig;
import com.example.aura.submission2_uiux.R;
import com.example.aura.submission2_uiux.menu.activity.DetailActivity;
import com.example.aura.submission2_uiux.models.ResultsMovie;

import java.util.ArrayList;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieViewHolder>{
    private Context context;
    private ArrayList<ResultsMovie> listMovie;
    

    public MovieAdapter(Context context, ArrayList<ResultsMovie> listMovie) {
        this.context = context;
        this.listMovie = listMovie;
    }


    @Override
    public MovieAdapter.MovieViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.movie_list,
                parent, false);
        return new MovieViewHolder(view);

    }

    @Override
    public void onBindViewHolder(final MovieAdapter.MovieViewHolder holder,final int position) {

        holder.judul.setText(listMovie.get(position).getTitle());
        holder.overview.setText(listMovie.get(position).getOverview());
        holder.date.setText(listMovie.get(position).getReleaseDate());
        holder.rating.setText(listMovie.get(position).getVoteAverage());

        Glide.with(context)
                .load(BuildConfig.Image + listMovie.get(position).getPosterPath())
                .into(holder.posterImg);

        holder.btndetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, DetailActivity.class);

                intent.putExtra("poster", BuildConfig.Image +listMovie.get(position).getPosterPath());
                intent.putExtra("judul", holder.judul.getText().toString().trim());
                intent.putExtra("overview", listMovie.get(position).getOverview());
                intent.putExtra("releasedate",listMovie.get(position).getReleaseDate());
                intent.putExtra("rating", listMovie.get(position).getVoteAverage());
                context.startActivity(intent);

            }
        });

        holder.btnshare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
                sharingIntent.setType("text/plain");
                String shareBody = holder.judul.getText().toString().trim();
                sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, context.getString(R.string.sub));
                sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, shareBody);
                context.startActivity(Intent.createChooser(sharingIntent, context.getString(R.string.share_via)));
            }
        });

    }

    @Override
    public int getItemCount() {
        return listMovie.size();
    }

    public class MovieViewHolder extends RecyclerView.ViewHolder {
        ImageView posterImg;
        TextView judul, date, overview, rating;
        CardView cardClick;
        Button btndetail, btnshare;
        public MovieViewHolder(View view) {
            super(view);
            posterImg = view.findViewById(R.id.poster);
            judul = view.findViewById(R.id.tittle);
            date = view.findViewById(R.id.date);
            overview = view.findViewById(R.id.deskripsi);
            rating = view.findViewById(R.id.rating);
            cardClick = view.findViewById(R.id.detailKlik);
            btndetail = view.findViewById(R.id.btnDetail);
            btnshare = view.findViewById(R.id.btnShare);
        }
    }
}
