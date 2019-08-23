package com.example.cubosapp.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.cubosapp.R

import kotlinx.android.synthetic.main.activity_movie_detail.*

class MovieDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_detail)
        setSupportActionBar(toolbar)

    }

}
