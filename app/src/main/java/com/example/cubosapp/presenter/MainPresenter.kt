package com.example.cubosapp.presenter

import com.example.cubosapp.data.MovieCard
import com.example.cubosapp.interfaces.MainInterfaces.*
import com.example.cubosapp.model.MainModel

class MainPresenter (var _mainView: View?): Presenter {

    private var _mainModel: Model = MainModel()
    private var moviesCollection = mutableMapOf( 28 to listOf<MovieCard>(), 18  to listOf<MovieCard>(), 14  to listOf<MovieCard>(), 878  to listOf<MovieCard>() )

    init {
        for (item in moviesCollection) {
            _mainModel.getData(item.key, 1, ::callBackFetchMovies)
        }
    }

    fun initializeView() {
        val genres = _mainModel.getMoviesGenres()
        _mainView?.initView(genres)
    }

    override fun onResume() {

    }

    fun onDestroy() {
        _mainView = null
    }

    override fun updateDataValue() {
        _mainView?.updateViewData(_mainModel.getMoviesGenres())
    }

    override fun getTabData(id : Int): List<MovieCard>? {
        return moviesCollection.get(id)
    }

    override fun selectedItem(){

    }

    private fun callBackFetchMovies(movies: List<MovieCard>?, id: Int) {
        if (movies != null) {
            moviesCollection.put(id, movies)
            this.updateDataValue()
        }
    }
}