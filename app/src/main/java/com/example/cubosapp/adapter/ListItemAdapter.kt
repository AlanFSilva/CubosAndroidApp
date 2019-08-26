package com.example.cubosapp.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.cubosapp.data.MovieCard
import kotlinx.android.synthetic.main.movie_card_item.view.*
import android.net.Uri

class ListItemAdapter(private val movies: List<MovieCard>): RecyclerView.Adapter<ListItemAdapter.MovieViewHolder>() {

    override fun getItemCount(): Int {
        return movies.size
    }

    override fun onBindViewHolder(holder: ListItemAdapter.MovieViewHolder, position: Int) {
        val itemMovie = movies[position]
        holder.bindMovies(itemMovie)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListItemAdapter.MovieViewHolder{
        val inflatedView = LayoutInflater.from(parent.context).inflate(com.example.cubosapp.R.layout.movie_card_item, parent, false)
        return MovieViewHolder(inflatedView)
    }

    class MovieViewHolder(v: View) : RecyclerView.ViewHolder(v) , View.OnClickListener {

        private var view : View = v
        private var movies : MovieCard? = null

        init {
            v.setOnClickListener {this}
        }

        override fun onClick(v: View?) {
            Log.d("RecyclerView", "CLICK!")
        }

        fun bindMovies(newMovie: MovieCard) {
            this.movies = newMovie
            val uri = Uri.parse("http://image.tmdb.org/t/p/w185/"+newMovie.poster_path)
            view.cardItemPoster.setImageURI(uri)
            view.cardItemText.text = newMovie.title
        }
    }
}