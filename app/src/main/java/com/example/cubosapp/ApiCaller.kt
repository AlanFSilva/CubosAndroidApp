package com.example.cubosapp

import android.util.Log
import com.example.cubosapp.data.MovieCard
import com.example.cubosapp.data.MovieDetail
import com.example.cubosapp.interfaces.RetroMoviesInterface
import retrofit2.*
import retrofit2.converter.gson.GsonConverterFactory

class ApiCaller {

    private var apiKey = "b4a3bbd0b2057b678ea9497050e199ba"
    private var langauge = "pt-BR"
    private var TmdbService : RetroMoviesInterface? = null

    init{
        var retrofit = Retrofit.Builder()
            .baseUrl("https://api.themoviedb.org/3/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        TmdbService = retrofit.create(RetroMoviesInterface::class.java)
    }

    fun getMoviesByGender(genre: Int, page: Int): List<MovieCard> {
        var data = TmdbService?.getMoviesByGenre(genre, apiKey, langauge, page)?.execute()?.body()
        return if (data != null) data else arrayListOf<MovieCard>()
    }

    fun getMoviesByTitle(title: String, page: Int): List<MovieCard> {
        var data = TmdbService?.getMoviesByTitle(title, apiKey, langauge, page)?.execute()?.body()
        return if (data != null) data else arrayListOf<MovieCard>()
    }

    fun getMovieDetail(movieId: String): MovieDetail?  {
         return TmdbService?.getMovieDetails(movieId, apiKey, langauge)?.execute()?.body()
    }
}