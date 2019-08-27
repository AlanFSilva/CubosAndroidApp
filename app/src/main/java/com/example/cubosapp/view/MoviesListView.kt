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
import androidx.recyclerview.widget.RecyclerView

class MoviesListView(var movies: ArrayList<MovieCard>?, val genre: Int, val onScrollChanged: (id: Int) -> Unit) : Fragment() {

    private var listItemAdapter : ListItemAdapter? = null
    private var lastVisibleItemPosition: Int = 0

    override fun onCreateView( inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        val fragment = inflater.inflate(R.layout.fragment_movies_list_view, container, false)
        return fragment
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val layoutManager = GridLayoutManager (view.context, 2)
        layoutManager.orientation = LinearLayoutManager.VERTICAL
        listMoviesRecycler.layoutManager = layoutManager
        listItemAdapter = ListItemAdapter(ArrayList(movies.orEmpty()), {movieCard : MovieCard -> onItemClick(movieCard) })
        listMoviesRecycler.adapter = listItemAdapter
        listMoviesRecycler.itemAnimator = DefaultItemAnimator()
        listMoviesRecycler.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                val totalItemCount = recyclerView.layoutManager!!.itemCount
                if (totalItemCount <= (lastVisibleItemPosition + 20)) {
                    onScrollChanged(genre)
                }
                lastVisibleItemPosition = layoutManager.findLastVisibleItemPosition()
            }
        })
    }

    fun updateData(data: List<MovieCard>){
        movies?.addAll(data)
        listItemAdapter?.addAll(data)
    }

    fun onItemClick(movie : MovieCard ){
        val movieDetail = Intent(this.context, MovieDetailActivity::class.java)
        movieDetail.putExtra("MovieId" , movie.id)
        movieDetail.putExtra("MovieTitle", movie.title)
        startActivity(movieDetail)
    }
}
