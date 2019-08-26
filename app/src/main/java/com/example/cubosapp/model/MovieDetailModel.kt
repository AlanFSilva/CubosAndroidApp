package com.example.cubosapp.model

import com.example.cubosapp.ApiCaller
import com.example.cubosapp.data.MovieDetail
import com.example.cubosapp.interfaces.MovieDetailInterfaces.*

class MovieDetailModel: Model {

    private var apiCaller = ApiCaller()

    override fun getData(id : Int, callBackFetchMovie: (movie: MovieDetail?)-> Unit) {
        apiCaller.getMovieDetail(id, callBackFetchMovie)
    }

}

