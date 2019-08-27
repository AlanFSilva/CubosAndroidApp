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


    fun getMoviesByGender(genre: Int, page: Int, callBack: (movies: MovieListRequest?, id : Int, callBack: (List<MovieCard>?) -> Unit) -> Unit, presenterCallBack: (List<MovieCard>?) -> Unit) {
        val response = TmdbService.getMoviesByGenre(genre, apiKey, langauge, page)
        response.enqueue( object : Callback<MovieListRequest?>{
            override fun onResponse(call: Call<MovieListRequest?>?, response: Response<MovieListRequest?>?) {
                response?.let {
                    val data: MovieListRequest? = it.body()
                    callBack(data, genre, presenterCallBack)
                }
            }
            override fun onFailure(call: Call<MovieListRequest?>?, t: Throwable?) {
                Log.e("onFailure error", t?.message)
            }
        })
    }

    fun getMoviesByTitle(title: String, page: Int, callBack: (movies: MovieListRequest?, callBack: (List<MovieCard>?) -> Unit) -> Unit, presenterCallBack: (List<MovieCard>?) -> Unit) {
        val response = TmdbService.getMoviesByTitle(title, apiKey, langauge, page)
        response.enqueue( object : Callback<MovieListRequest?>{
            override fun onResponse(call: Call<MovieListRequest?>?, response: Response<MovieListRequest?>?) {
                response?.let {
                    val data: MovieListRequest? = it.body()
                    callBack(data, presenterCallBack)
                }
            }
            override fun onFailure(call: Call<MovieListRequest?>?, t: Throwable?) {
                Log.e("onFailure error", t?.message)
            }
        })
    }

    fun getMovieDetail(movieId: Int, callBack: (movies: MovieDetail?) -> Unit) {
        val response = TmdbService.getMovieDetails(movieId, apiKey, langauge)
        response.enqueue( object : Callback<MovieDetail?>{
            override fun onResponse(call: Call<MovieDetail?>?, response: Response<MovieDetail?>?) {
                response?.let {
                    val data: MovieDetail? = it.body()
                    callBack(data)
                }
            }
            override fun onFailure(call: Call<MovieDetail?>?, t: Throwable?) {
                Log.e("onFailure error", t?.message)
            }
        })
    }
}