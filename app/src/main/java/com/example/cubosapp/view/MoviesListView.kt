package com.example.cubosapp.view

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

class MoviesListView(var movies: List<MovieCard>?) : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView( inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        var fragment = inflater.inflate(R.layout.fragment_movies_list_view, container, false)
        return fragment;
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        var layoutManager = LinearLayoutManager(view.context)
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL)
        listMoviesRecycler.setLayoutManager( layoutManager)
        var listItemAdapter = ListItemAdapter(movies.orEmpty())
        listMoviesRecycler.setAdapter(listItemAdapter)
        listMoviesRecycler.setItemAnimator(DefaultItemAnimator())
    }
}
