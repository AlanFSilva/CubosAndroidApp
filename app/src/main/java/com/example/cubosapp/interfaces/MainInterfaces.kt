package com.example.cubosapp.interfaces

import com.example.cubosapp.data.Genre
import com.example.cubosapp.data.MovieCard

interface MainInterfaces {

    interface View {
        fun initView(genres : List<Genre>)
        fun updateViewData(genres: List<Genre>)
        fun getSelectedItem()
        fun searchMovie()
    }

    interface Presenter {
        fun onResume()
        fun updateDataValue()
        fun selectedItem()
        fun getTabData(id : Int) : List<MovieCard>?
    }

    interface Model {
        fun getData(id : Int, page: Int, callBackFetchMovies: (movies: List<MovieCard>?, genre: Int)-> Unit)
        fun initModelData(callBackFetchMovies : (movies: List<MovieCard>?, genre: Int)-> Unit)
        fun getMoviesGenres() : List<Genre>

    }

}