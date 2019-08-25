package com.example.cubosapp.data

import java.util.*

data class MovieDetail (
    val title: String,
    val vote_average : Float,
    val id : Int,
    val genres : Array<Genre>,
    val overview : String,
    val release_date : String,
    val poster_path : String,
    val budget : Int,
    val status : String,
    val runtime : Int,
    val revenue : Int,
    val profit : Int = (revenue - budget),
    val original_language : String
)