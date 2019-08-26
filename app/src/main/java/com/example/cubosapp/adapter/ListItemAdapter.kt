package com.example.cubosapp.adapter

import android.content.Context.WINDOW_SERVICE
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.cubosapp.data.MovieCard
import kotlinx.android.synthetic.main.movie_card_item.view.*
import com.squareup.picasso.Picasso
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Point
import android.os.AsyncTask
import android.util.DisplayMetrics
import android.view.WindowManager
import android.widget.ImageView
import java.io.IOException
import java.io.InputStream
import java.net.URL
import android.view.Display




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

        }

        fun bindMovies(newMovie: MovieCard) {
            this.movies = newMovie
            val uri = "https://image.tmdb.org/t/p/w342/"+newMovie.poster_path
            val size = Point().also {
                    (view.context.getSystemService(WINDOW_SERVICE) as WindowManager)
                        .defaultDisplay
                        .apply { getSize(it) }
                }

            view.cardItemText.text = newMovie.title
            Picasso.get().load(uri).into(view.cardItemPoster)
            view.cardItemPoster.getLayoutParams().height = ((size.x / 16) *12)
            view.cardItemPoster.requestLayout()
        }
    }
}
