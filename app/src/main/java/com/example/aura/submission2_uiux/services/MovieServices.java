package com.example.aura.submission2_uiux.services;

import com.example.aura.submission2_uiux.BuildConfig;
import com.example.aura.submission2_uiux.models.Movie;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface MovieServices {
//    @GET("movie?api_key=0fe8ca3c33e758e0001314d6ed0415fc&language=en-US")
    @GET("movie?api_key=0fe8ca3c33e758e0001314d6ed0415fc")
    Call<Movie> getMovie(@Query("query") String query);

//    @GET("movie?api_key=0fe8ca3c33e758e0001314d6ed0415fc")
//    Call<Movie> getMovie(@Query("language") String language);

    @GET(BuildConfig.NowPlayingEng)
    Call<Movie>getMovieNowPlaying(@Query("language") String language);
    @GET(BuildConfig.UpComingEng)
    Call<Movie>getMovieUpComing(@Query("language") String language);
}
