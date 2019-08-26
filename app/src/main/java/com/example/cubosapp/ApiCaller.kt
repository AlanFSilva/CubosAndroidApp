package com.example.cubosapp

import android.util.Log
import com.example.cubosapp.data.MovieCard
import com.example.cubosapp.data.MovieDetail
import com.example.cubosapp.data.MovieListRequest
import com.example.cubosapp.interfaces.RetroMoviesInterface
import retrofit2.*
import retrofit2.converter.gson.GsonConverterFactory

class ApiCaller {

    private var apiKey = "b4a3bbd0b2057b678ea9497050e199ba"
    private var langauge = "pt-BR"

    private val retrofit = Retrofit.Builder()
            .baseUrl("https://api.themoviedb.org/3/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

   private val TmdbService = retrofit.create(RetroMoviesInterface::class.java)


    fun getMoviesByGender(genre: Int, page: Int, callBack: (movies: List<MovieCard>?, id : Int) -> Unit) {
        var response = TmdbService.getMoviesByGenre(genre, apiKey, langauge, page)
        response.enqueue( object : Callback<MovieListRequest?>{
            override fun onResponse(call: Call<MovieListRequest?>?, response: Response<MovieListRequest?>?) {
                response?.let {
                    val response: MovieListRequest? = it.body()
                    callBack(response?.results, genre)
                }
            }
            override fun onFailure(call: Call<MovieListRequest?>?, t: Throwable?) {
                Log.e("onFailure error", t?.message)
            }
        })
    }

    fun getMoviesByTitle(title: String, page: Int) {
        var data = TmdbService.getMoviesByTitle(title, apiKey, langauge, page).execute().body()

    }

    fun getMovieDetail(movieId: String): MovieDetail?  {
         return TmdbService.getMovieDetails(movieId, apiKey, langauge).execute().body()
    }
}