package com.example.aura.submission2_uiux.menu.fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.aura.submission2_uiux.R;
import com.example.aura.submission2_uiux.adapter.MovieAdapter;
import com.example.aura.submission2_uiux.models.Movie;
import com.example.aura.submission2_uiux.models.ResultsMovie;
import com.example.aura.submission2_uiux.services.Client;
import com.example.aura.submission2_uiux.services.MovieServices;

import java.util.ArrayList;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class UpcomingFragment extends Fragment {

    public UpcomingFragment() {
        // Required empty public constructor
    }

    private ArrayList<ResultsMovie> listResult;
    private RecyclerView recyclerNp;
    private Button bSearch;
    private EditText eSearch;
    private MovieAdapter movieAdapter;




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_upcoming, container, false);

        bSearch = (Button) view.findViewById(R.id.btnSearch);
        eSearch = (EditText) view.findViewById(R.id.search);
        recyclerNp = (RecyclerView) view.findViewById(R.id.movies_recycler_view);

        listResult = new ArrayList<>();

        bSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getDataMovies();
            }
        });
        listResult = new ArrayList<>();
        Locale current = getResources().getConfiguration().locale;
        if (current.toString().equalsIgnoreCase("en_US")) {
            getMovieUpcomingEN();
        } else {
            getMovieUpcomingID();
        }

        return view;
    }

    private void getDataMovies() {
        MovieServices movieService = Client.getInstanceRetrofit();
        movieService.getMovie(eSearch.getText().toString().trim())
                .enqueue(new Callback<Movie>() {
                    @Override
                    public void onResponse(Call<Movie> call, Response<Movie> response) {
                        if (response.isSuccessful()) {
                            listResult = response.body().getResults();
                            MovieAdapter adapter = new MovieAdapter(getActivity(), listResult);
                            recyclerNp.setLayoutManager(new LinearLayoutManager(getActivity()));
                            recyclerNp.setAdapter(adapter);
                            Toast.makeText(getActivity(), "pencarian ditemukan", Toast.LENGTH_SHORT).show();
                        }
                        else {
                            Toast.makeText(getActivity(), "tidak dapat menemukan", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<Movie> call, Throwable t) {
                        Toast.makeText(getActivity(), "cek koneksi", Toast.LENGTH_SHORT).show();
                    }
                });
    }


    private void getMovieUpcomingEN() {
        MovieServices apiService = Client.getInstanceRetrofit();
        apiService.getMovieUpComing("en_US").enqueue(new Callback<Movie>() {
            @Override
            public void onResponse(Call<Movie> call, Response<Movie> response) {
                if (response.isSuccessful()) {
                    listResult = response.body().getResults();
                    movieAdapter = new MovieAdapter(getActivity(), listResult);
                    recyclerNp.setLayoutManager(new LinearLayoutManager(getActivity()));
                    recyclerNp.setAdapter(movieAdapter);
                }
            }

            @Override
            public void onFailure(Call<Movie> call, Throwable t) {

                Toast.makeText(getActivity(), "" + "periksa koneksi anda", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void getMovieUpcomingID() {
        MovieServices apiService = Client.getInstanceRetrofit();
        apiService.getMovieUpComing("id").enqueue(new Callback<Movie>() {
            @Override
            public void onResponse(Call<Movie> call, Response<Movie> response) {
                if (response.isSuccessful()) {
                    listResult = response.body().getResults();
                    movieAdapter = new MovieAdapter(getActivity(), listResult);
                    recyclerNp.setLayoutManager(new LinearLayoutManager(getActivity()));
                    recyclerNp.setAdapter(movieAdapter);
                }
            }

            @Override
            public void onFailure(Call<Movie> call, Throwable t) {
                Toast.makeText(getActivity(), "" + "periksa koneksi anda", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
