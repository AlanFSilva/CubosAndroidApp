package com.example.cubosapp.presenter

import com.example.cubosapp.data.MovieCard
import com.example.cubosapp.interfaces.MainInterfaces.*
import com.example.cubosapp.model.MainModel

class MainPresenter (var _mainView: View?): Presenter {

    private var _mainModel: Model = MainModel()
    private var moviesCollection = mutableMapOf( 28 to listOf<MovieCard>(), 18  to listOf<MovieCard>(), 14  to listOf<MovieCard>(), 878  to listOf<MovieCard>() )
    private var dataIndex = 0


    fun initializeView() {
        getInitialData(moviesCollection.keys.elementAt(dataIndex))
        val genres = _mainModel.getMoviesGenres()
        _mainView?.initView(genres)
    }

    private fun getInitialData(key: Int){
        _mainModel.getData(key, 1, ::callBackFetchMovies)
    }

    fun onDestroy() {
        _mainView = null
    }

    override fun setDataValue(key : Int) {
        val genre = _mainModel.getMoviesGenres().find{item -> item.id == key}
        _mainView?.setViewData(genre)
    }

    override fun updateDataValue(key : Int) {
        val genre = _mainModel.getMoviesGenres().find{item -> item.id == key}
        _mainView?.updateViewData(genre)
    }

    override fun getTabData(id : Int): List<MovieCard>? {
        return moviesCollection.get(id)
    }

    private fun callBackFetchMovies(movies: List<MovieCard>?, id: Int) {
        if (movies != null) {
            dataIndex +=1
            moviesCollection.put(id, movies)
            setDataValue(id)
            if (dataIndex < moviesCollection.size){
                val key = moviesCollection.keys.elementAt(dataIndex)
                getInitialData(key)
            }
        }
        else{
            getInitialData(moviesCollection.keys.elementAt(dataIndex))
        }
    }

    private fun callBackUpdateMovies(movies: List<MovieCard>?, id: Int) {
        if (movies != null) {
            moviesCollection.put(id, movies)
            setDataValue(id)
        }
    }
}