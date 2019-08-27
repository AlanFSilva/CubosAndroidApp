package com.example.cubosapp.presenter

import com.example.cubosapp.data.MovieCard
import com.example.cubosapp.interfaces.SearchInterfaces.*
import com.example.cubosapp.model.SearchModel

class SearchPresenter (var _searchView : View?): Presenter{

    private var _searchModel: Model = SearchModel()

    override fun searchMovie(movieTitle: String) {
        _searchModel.searchData(movieTitle, ::callBackRequest)
    }

    override fun updateData(){
        _searchModel.updateData(::callBackUpdateRequest)
    }

    override fun getData(): List<MovieCard>? {
        return _searchModel.getData()
    }

    override fun getLimitData(): Boolean{
        return _searchModel.getLimitData()
    }

    private fun callBackRequest(movies : List<MovieCard>?){
        if (movies != null) {
            _searchView?.setViewData(movies)
        }
    }

    private fun callBackUpdateRequest(movies: List<MovieCard>?){
        if (movies != null) {
            _searchView?.updateViewData(movies)
        }
    }



}

