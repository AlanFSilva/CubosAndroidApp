package com.example.cubosapp.adapter

import android.content.Context
import android.content.Context.WINDOW_SERVICE
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.cubosapp.data.MovieCard
import kotlinx.android.synthetic.main.movie_card_item.view.*
import com.squareup.picasso.Picasso
import android.graphics.Point
import android.view.WindowManager
import androidx.core.content.ContextCompat.startActivity
import com.example.cubosapp.view.MovieDetailActivity


class ListItemAdapter(private var movies: ArrayList<MovieCard>, val clickListener: (MovieCard) -> Unit): RecyclerView.Adapter<ListItemAdapter.MovieViewHolder>() {

    override fun getItemCount(): Int {
        return movies.size
    }

    fun addAll(newMovies : List<MovieCard>){
        movies.addAll(newMovies)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val itemMovie = movies[position]
        holder.bindMovies(itemMovie, clickListener)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder{
        val inflatedView = LayoutInflater.from(parent.context).inflate(com.example.cubosapp.R.layout.movie_card_item, parent, false)
        return MovieViewHolder(inflatedView)
    }

    inner class MovieViewHolder(holder: View) : RecyclerView.ViewHolder(holder) {

        private var view : View = holder
        private var movies : MovieCard? = null

        fun bindMovies(newMovie: MovieCard, clickListener: (MovieCard) -> Unit) {
            this.movies = newMovie
            view.setOnClickListener {clickListener(newMovie)}
            val uri = "https://image.tmdb.org/t/p/w342/"+newMovie.poster_path
            val size = Point().also {
                    (view.context.getSystemService(WINDOW_SERVICE) as WindowManager)
                        .defaultDisplay
                        .apply { getSize(it) }
                }
            view.cardItemText.text = newMovie.title
            Picasso.get().load(uri).into(view.cardItemPoster)
            view.cardItemPoster.layoutParams.height = ((size.x / 16) *12)
            view.cardItemPoster.requestLayout()
        }
    }
}
