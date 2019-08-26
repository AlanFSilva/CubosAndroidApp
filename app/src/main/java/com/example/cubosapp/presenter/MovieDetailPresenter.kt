package com.example.cubosapp.presenter

import com.example.cubosapp.data.MovieDetail
import com.example.cubosapp.interfaces.MovieDetailInterfaces.*
import com.example.cubosapp.model.MovieDetailModel

class MovieDetailPresenter (var _detailView: View?): Presenter {

    private var _detailModel = MovieDetailModel()

    override fun fetchMovieData(movieId : Int){
        _detailModel.getData(movieId, ::callBackFetchMovie)
    }

    override fun backPage() {

    }

    private fun callBackFetchMovie(movie: MovieDetail?) {
        movie?.let { _detailView?.initView(it) }
    }

}