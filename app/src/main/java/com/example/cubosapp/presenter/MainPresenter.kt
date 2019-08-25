package com.example.cubosapp.presenter

import com.example.cubosapp.data.MovieCard
import com.example.cubosapp.interfaces.MainInterfaces.*
import com.example.cubosapp.model.MainModel

class MainPresenter (var _mainView: View?): Presenter {

    private var _mainModel: Model = MainModel()

    fun onResume() {
        _mainModel.loadData()
    }

    fun onDestroy() {
        _mainView = null
    }

    override fun updateDataValue() {

    }

    override fun getTabData(id : Int): List<MovieCard> {
        return _mainModel.getData(id)
    }

    override fun selectedItem(){

    }

    fun getSetTabsContent(){
        var genres = _mainModel.getMoviesGenres()
        _mainView?.initView(genres)
    }
}