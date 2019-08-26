package com.example.cubosapp.interfaces

import com.example.cubosapp.data.MovieDetail

interface MovieDetailInterfaces {

    interface View {
        fun initView(movieDetail: MovieDetail)
    }

    interface Presenter {
        fun getDataValue()
        fun backPage()
    }

    interface Model {
        fun getData(movieId : Int) : MovieDetail
    }

}