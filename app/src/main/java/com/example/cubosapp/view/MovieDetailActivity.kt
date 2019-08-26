package com.example.cubosapp.view

import android.graphics.Point
import android.os.Bundle
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import com.example.cubosapp.R
import com.example.cubosapp.data.MovieDetail
import com.example.cubosapp.interfaces.MovieDetailInterfaces.*
import com.example.cubosapp.presenter.MovieDetailPresenter
import com.squareup.picasso.Picasso

import kotlinx.android.synthetic.main.activity_movie_detail.*
import java.text.DecimalFormat
import java.text.NumberFormat
import java.util.*

class MovieDetailActivity : AppCompatActivity(), View {

    private var presenter = MovieDetailPresenter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_detail)

        val movieId = intent?.getIntExtra("MovieId", 0)
        val title = intent?.getStringExtra("MovieTitle")

        setSupportActionBar(toolbar)
        supportActionBar?.setTitle(title)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        movieId?.let { presenter.fetchMovieData(it) }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    override fun initView(movieDetail: MovieDetail) {
        textOverview.text = movieDetail.overview
        textStatus.text = translateStatus(movieDetail.status)
        textLanguage.text = movieDetail.original_language
        textRuntime.text = convertDuration(movieDetail.runtime)
        textBudget.text = convertToCurrency(movieDetail.budget)
        textRevenue.text = convertToCurrency(movieDetail.revenue)
        textProfit.text = convertToCurrency((movieDetail.revenue - movieDetail.budget))

        val uri = "https://image.tmdb.org/t/p/w342/"+movieDetail.poster_path
        Picasso.get().load(uri).into(moviePoster)
        val size = Point().also {
            (this.getSystemService(WINDOW_SERVICE) as WindowManager)
                .defaultDisplay
                .apply { getSize(it) }
        }
        moviePoster.layoutParams.height = ((size.x / 8) *12)
        moviePoster.requestLayout()
    }


    fun convertToCurrency(value : Float) : String{
        val currency = NumberFormat.getNumberInstance(Locale.US).format(value).replace(',','.')
        return "$currency,00"
    }

    fun translateStatus(status  : String)  : String{
        return if (status == "In Production") "Em Produção" else if (status == "Released")  "Lançado" else status
    }

    fun convertDuration(runtime : Int) : String {
        val hour = kotlin.math.abs(runtime / 60)
        val minutes = runtime - (hour * 60)
        return "$hour h $minutes min"
    }
}
