package com.example.cubosapp.model

import com.example.cubosapp.ApiCaller
import com.example.cubosapp.data.Genre
import com.example.cubosapp.data.MovieCard
import com.example.cubosapp.interfaces.MainInterfaces.*

class MainModel: Model {

    private var moviesGenres = listOf( Genre(id = 28, name = "Ação"), Genre(id = 18,name = "Drama"), Genre(id = 14, name = "Fantasia"), Genre(id = 878, name = "Ficção"))
    private var apiCaller = ApiCaller()

    override fun getData(id : Int, page : Int, callBackFetchMovies: (movies: List<MovieCard>?, genre: Int)-> Unit) {
        apiCaller.getMoviesByGender(id, page, callBackFetchMovies)
    }


    override fun initModelData (callBackFetchMovies : (movies: List<MovieCard>?, genre: Int)-> Unit){
        for (x in 0 until 4) {
            apiCaller.getMoviesByGender(moviesGenres[x].id, 1, callBackFetchMovies)
        }
    }

    override fun getMoviesGenres() : List<Genre>{
        return moviesGenres
    }

}

