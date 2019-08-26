package com.example.cubosapp.interfaces

import com.example.cubosapp.data.MovieDetail

interface MovieDetailInterfaces {

    interface View {
        fun initView(movieDetail: MovieDetail)
    }

    interface Presenter {
        fun backPage()
        fun fetchMovieData(movieId : Int)
    }

    interface Model {
        fun getData(id : Int, callBackFetchMovie: (movies: MovieDetail?)-> Unit)
    }

}