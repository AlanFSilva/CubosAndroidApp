package com.example.cubosapp.model

import com.example.cubosapp.ApiCaller
import com.example.cubosapp.data.MovieCard
import com.example.cubosapp.data.MovieListRequest
import com.example.cubosapp.interfaces.MainInterfaces.*

class MainModel: Model {
    private var totalPages = mutableMapOf( 28 to 1, 18  to 1, 14  to 1, 878  to 1 )
    private var currentPage = mutableMapOf( 28 to 1, 18  to 1, 14  to 1, 878  to 1 )
    private var moviesList = mutableMapOf( 28 to arrayListOf<MovieCard>(), 18  to arrayListOf<MovieCard>(), 14  to arrayListOf<MovieCard>(), 878  to arrayListOf<MovieCard>() )
    private var apiCaller = ApiCaller()

    override fun fetchData( genre: Int, callBackFetchMovies: (List<MovieCard>?) -> Unit) {
        currentPage.set(genre, 1)
        apiCaller.getMoviesByGender(genre,currentPage.get(genre)!!, ::callBackFetchData, callBackFetchMovies)
    }

    override fun updateData( genre: Int, callBackFetchMovies: (List<MovieCard>?) -> Unit) {
        apiCaller.getMoviesByGender(genre, currentPage.get(genre)!!, ::callBackUpdateData, callBackFetchMovies)
    }

   override fun getLimitData(genre: Int): Boolean {
        return currentPage.get(genre)!! == totalPages.get(genre)!!
    }

    fun callBackFetchData(request: MovieListRequest?, genre: Int, callBackFetchMovies: (List<MovieCard>?) -> Unit) {
        if(request != null){
            moviesList.put(genre, ArrayList(request.results))
            totalPages.set(genre, request.total_pages)
            var temp = currentPage.get(genre)!! + 1
            currentPage.set(genre, temp)
            callBackFetchMovies(request.results)
        }
    }

    fun callBackUpdateData(request: MovieListRequest?, genre: Int, callBackFetchMovies: (List<MovieCard>?) -> Unit) {
        if(request != null){
            moviesList.put(genre, ArrayList(request.results))
            var temp = currentPage.get(genre)!! + 1
            currentPage.set(genre, temp)
            callBackFetchMovies(request.results)
        }
    }
}

