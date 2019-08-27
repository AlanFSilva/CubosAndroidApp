package com.example.cubosapp.interfaces

import com.example.cubosapp.data.MovieCard

interface SearchInterfaces {

    interface View {
        fun setViewData(movies: List<MovieCard>)
        fun updateViewData(movies: List<MovieCard>)
    }

    interface Presenter {
        fun searchMovie(movieTitle: String)
        fun updateData()
        fun getData(): List<MovieCard>?
        fun getLimitData(): Boolean
    }

    interface Model {
        fun searchData(title: String, callBackFetchMovies: (List<MovieCard>?) -> Unit)
        fun updateData(callBackFetchMovies: (List<MovieCard>?) -> Unit)
        fun getData(): List<MovieCard>
        fun getLimitData(): Boolean
    }
}

