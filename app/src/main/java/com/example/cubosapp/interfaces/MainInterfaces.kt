package com.example.cubosapp.interfaces

import com.example.cubosapp.data.Genre
import com.example.cubosapp.data.MovieCard

interface MainInterfaces {

    interface View {
        fun initView(genres : List<Genre>)
        fun setViewData(genre: Genre?, movies: List<MovieCard>?)
        fun updateViewData(genre: Int, movies: List<MovieCard>?)
    }

    interface Presenter {

        fun getDataValue(key: Int)
        fun setDataValue(movies: List<MovieCard>)
        fun updateDataValue(movies: List<MovieCard>?)
        fun getLimitData(genre: Int): Boolean
    }

    interface Model {
        fun fetchData( genre: Int, callBackFetchMovies: (List<MovieCard>?) -> Unit)
        fun updateData( genre: Int, callBackFetchMovies: (List<MovieCard>?) -> Unit)
        fun getLimitData(genre: Int): Boolean
    }

}