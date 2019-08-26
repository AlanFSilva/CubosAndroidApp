package com.example.cubosapp.data

data class MovieDetail (
    val title: String,
    val vote_average : Float,
    val id : Int,
    val genres : Array<Genre>,
    val overview : String,
    val release_date : String,
    val poster_path : String,
    val budget : Float,
    val status : String,
    val runtime : Int,
    val revenue : Float,
    val original_language : String
)