package com.example.cubosapp.interfaces

import com.example.cubosapp.data.Genre
import com.example.cubosapp.data.MovieCard

interface MainInterfaces {

    interface View {
        fun initView(genres : List<Genre>)
        fun updateViewData()
        fun getSelectedItem()
        fun searchMovie()
    }

    interface Presenter {
        fun updateDataValue()
        fun selectedItem()
        fun getTabData(id : Int) : List<MovieCard>
    }

    interface Model {
        fun getData(id : Int) : List<MovieCard>
        fun loadData()
        fun getMoviesGenres() : List<Genre>
    }

}