package com.example.cubosapp.model

import com.example.cubosapp.ApiCaller
import com.example.cubosapp.data.Genre
import com.example.cubosapp.data.MovieCard
import com.example.cubosapp.interfaces.MainInterfaces.*

class MainModel: Model {

    private var moviesGenres = listOf( Genre(id = 28, name = "Ação"), Genre(id = 18,name = "Drama"), Genre(id = 14, name = "Fantasia"), Genre(id = 878, name = "Ficção"))
    private var moviesCollection = mutableMapOf( 28 to arrayListOf<MovieCard>(), 18  to arrayListOf<MovieCard>(), 14  to arrayListOf<MovieCard>(), 878  to arrayListOf<MovieCard>() )


    override fun getData(id : Int): ArrayList<MovieCard> {
        var data = moviesCollection.get(id)
        return if (data != null) data else arrayListOf<MovieCard>()
    }

    override fun loadData() {
        var values = ApiCaller().getMoviesByGender(moviesGenres[0].id, 1)
        moviesCollection
    }

    override fun getMoviesGenres() : List<Genre>{
        return moviesGenres
    }

}

