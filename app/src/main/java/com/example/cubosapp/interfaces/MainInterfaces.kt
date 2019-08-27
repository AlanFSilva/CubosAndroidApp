package com.example.cubosapp.interfaces

import com.example.cubosapp.data.Genre
import com.example.cubosapp.data.MovieCard

interface MainInterfaces {

    interface View {
        fun initView(genres : List<Genre>)
        fun setViewData(genre: Genre?)
        fun updateViewData(genre: Genre?)
        fun getSelectedItem()
        fun searchMovie()
    }

    interface Presenter {
        fun updateDataValue(key : Int)
        fun setDataValue(key : Int)
        fun getTabData(id : Int) : List<MovieCard>?
    }

    interface Model {
        fun getData(id : Int, page: Int, callBackFetchMovies: (movies: List<MovieCard>?, genre: Int)-> Unit)
        fun initModelData(callBackFetchMovies : (movies: List<MovieCard>?, genre: Int)-> Unit)
        fun getMoviesGenres() : List<Genre>

    }

}