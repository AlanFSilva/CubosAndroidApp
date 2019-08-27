package com.example.cubosapp.presenter

import com.example.cubosapp.data.Genre
import com.example.cubosapp.data.MovieCard
import com.example.cubosapp.interfaces.MainInterfaces.*
import com.example.cubosapp.model.MainModel

class MainPresenter (var _mainView: View?): Presenter {

    private var moviesGenres = listOf( Genre(id = 28, name = "Ação"), Genre(id = 18,name = "Drama"), Genre(id = 14, name = "Fantasia"), Genre(id = 878, name = "Ficção"))
    private var _mainModel: Model = MainModel()
    private var dataIndex = 0
    private var updateId = 0

    fun onDestroy() {
        _mainView = null
    }

    fun initializeView() {
        getInitialData(moviesGenres[dataIndex].id)
        _mainView?.initView(moviesGenres)
    }

    private fun getInitialData(key: Int){
        _mainModel.fetchData(key, ::callBackFetchMovies)
    }

    override fun getLimitData(genre: Int): Boolean {
        return _mainModel.getLimitData(genre)
    }

    override fun getDataValue(key: Int){
        updateId = key
        _mainModel.updateData(key, ::callBackUpdateMovies)
    }

    override fun setDataValue(movies: List<MovieCard>) {
        val genre = moviesGenres[dataIndex-1]
        _mainView?.setViewData(genre, movies)
    }

    override fun updateDataValue(movies: List<MovieCard>?) {
        _mainView?.updateViewData(updateId, movies)
    }

    private fun callBackFetchMovies(movies: List<MovieCard>?) {
        if (movies != null) {
            dataIndex +=1
            setDataValue(movies)
            if (dataIndex < moviesGenres.size){
                val key = moviesGenres[dataIndex].id
                getInitialData(key)
            }
        }
        else{
            getInitialData(moviesGenres[dataIndex].id)
        }
    }

    private fun callBackUpdateMovies(movies: List<MovieCard>?) {
        if (movies != null) {
            updateDataValue(movies)
        }
    }
}