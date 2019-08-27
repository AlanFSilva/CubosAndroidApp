package com.example.cubosapp.view

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_movies_list_view.*

import com.example.cubosapp.R
import com.example.cubosapp.adapter.ListItemAdapter
import com.example.cubosapp.data.MovieCard
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.GridLayoutManager

class MoviesListView(var movies: List<MovieCard>?) : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView( inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        val fragment = inflater.inflate(R.layout.fragment_movies_list_view, container, false)
        return fragment
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val layoutManager = GridLayoutManager (view.context, 2)
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL)
        listMoviesRecycler.setLayoutManager( layoutManager)
        val listItemAdapter = ListItemAdapter(ArrayList(movies.orEmpty()), {movieCard : MovieCard -> onItemClick(movieCard) })
        listMoviesRecycler.setAdapter(listItemAdapter)
        listMoviesRecycler.setItemAnimator(DefaultItemAnimator())
    }

    fun onItemClick(movie : MovieCard ){
        val movieDetail = Intent(this.context, MovieDetailActivity::class.java)
        movieDetail.putExtra("MovieId" , movie.id)
        movieDetail.putExtra("MovieTitle", movie.title)
        startActivity(movieDetail)
    }
}
