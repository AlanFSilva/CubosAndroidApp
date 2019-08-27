package com.example.cubosapp.model

import com.example.cubosapp.ApiCaller
import com.example.cubosapp.data.MovieCard
import com.example.cubosapp.data.MovieListRequest
import com.example.cubosapp.interfaces.SearchInterfaces.*

class SearchModel(): Model {
    private var apiCaller = ApiCaller()
    private var currentPage = 1
    private var totalPages = 1
    private var moviesList = arrayListOf<MovieCard>()
    private var previusSearched = ""

    override fun searchData( title: String, callBackFetchMovies: (List<MovieCard>?) -> Unit) {
        previusSearched = title
        currentPage = 1
        apiCaller.getMoviesByTitle(title, currentPage, ::callBackFetchData, callBackFetchMovies)
    }

    override fun updateData( callBackFetchMovies: (List<MovieCard>?) -> Unit) {
        apiCaller.getMoviesByTitle(previusSearched, currentPage, ::callBackUpdateData, callBackFetchMovies)
    }

    override fun getData(): List<MovieCard> {
        return moviesList
    }

    override fun getLimitData(): Boolean {
        return currentPage == totalPages
    }


    fun callBackFetchData(request: MovieListRequest?, callBackFetchMovies: (List<MovieCard>?) -> Unit) {
        if(request != null){
            moviesList = ArrayList(request?.results!!)
            totalPages = request?.total_pages!!
            currentPage +=1
            callBackFetchMovies(moviesList)
        }
    }

    fun callBackUpdateData(request: MovieListRequest?, callBackFetchMovies: (List<MovieCard>?) -> Unit) {
        if(request != null){
            moviesList.addAll(request.results)
            currentPage +=1
            callBackFetchMovies(request.results)
        }

    }

}

