package com.example.cubosapp.interfaces

import com.example.cubosapp.data.MovieCard
import com.example.cubosapp.data.MovieDetail
import com.example.cubosapp.data.MovieListRequest
import retrofit2.http.*
import retrofit2.Call

interface RetroMoviesInterface {
    @GET("discover/movie")
    fun getMoviesByGenre( @Query("with_genres") searchTerm: Int, @Query("api_key") apiKey: String, @Query("language") language: String, @Query("page") page: Int, @Query("include_adult") censorship: Boolean = false) : Call<MovieListRequest>

    @GET("search/movie")
    fun getMoviesByTitle( @Query("query") searchTerm: String, @Query("api_key") apiKey: String, @Query("language") language: String, @Query("page") page: Int, @Query("include_adult") censorship: Boolean = false) : Call<MovieListRequest>

    @GET("movie/{movieId}")
    fun getMovieDetails(@Path("movieId") movieId: Int, @Query("api_key") apiKey: String, @Query("language") language: String, @Query("append_to_response") append: String = "videos") : Call<MovieDetail>
}